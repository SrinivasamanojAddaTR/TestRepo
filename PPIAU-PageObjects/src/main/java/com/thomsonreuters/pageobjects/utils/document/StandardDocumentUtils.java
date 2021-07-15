package com.thomsonreuters.pageobjects.utils.document;

import com.thomsonreuters.driver.configuration.Hosts;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.CaseDocumentPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.ViewDocumentPopUpPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.rest.LinkingBaseUtils;
import com.thomsonreuters.pageobjects.utils.RoutingPage;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class StandardDocumentUtils {

    private LinkingBaseUtils linkingUtils = new LinkingBaseUtils();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();
    private CommonMethods comMethods = new CommonMethods();
    private FoldersUtils foldersUtils = new FoldersUtils();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private RoutingPage routingPage = new RoutingPage();
    private ViewDocumentPopUpPage viewDocumentPopUpPage = new ViewDocumentPopUpPage();
    private CaseDocumentPage caseDocumentPage = new CaseDocumentPage();
    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private Hosts hosts = Hosts.getInstance();
    private DocumentObject documentObject;
    private String baseUrl = System.getProperty("base.url");

    // Error message for Cucumber report
    private String paLinksCheckErrMsg = "";

    private static final Logger LOG = LoggerFactory.getLogger(StandardDocumentUtils.class);
    private static Map<String, DocumentObject> docsMap = new HashMap<String, DocumentObject>();
    private static int routingCount = 0;

    /**
     * Check if all links for document in section are valid
     *
     * @param sectionElement WebElement link with section name
     * @param checkThreshold Links to validity check of.
     * @return True - if check passed. Otherwise - false.
     */
    public boolean isLinksAreValidInSection(WebElement sectionElement, int checkThreshold) {
        String hrefAttr = sectionElement.getAttribute("href");
        // Check if doc for section contains links. If not - we can't work with it anymore
        if (!linkingUtils.isLinkReturnsTheDocument(hrefAttr)) {
            paLinksCheckErrMsg = "No documents for section '" + sectionElement.getText().trim() + "', link '" + hrefAttr + "'";
            LOG.warn(paLinksCheckErrMsg);
            return false;
        }
        // Get document page source to check it links
        String documentPageSource = linkingUtils.getPageSourceForLink(hrefAttr);
        try {
            // Get all links from the document
            Elements links = linkingUtils.getElementsFromHtml(documentPageSource, ".co_link");
            int linksCount = links.size();
            // just counter for loops count
            int i = 0;
            // Check every link from document
            for (Element link : links) {
                if (!isDocumentLinksAreValid(sectionElement.getText().trim(), link)) {
                    return false;
                }
                // If threshold reached - break loop
                if (i >= checkThreshold) {
                    break;
                }
                i++;
            }
            paLinksCheckErrMsg = "Document for section '" + sectionElement.getText().trim() + "', link '" + hrefAttr + "' " +
                    "does not contains any links";
            // if there is no related links, for loop will be skipped and we can't return just true
            return linksCount > 0;
        } catch (Exception e) {
            paLinksCheckErrMsg = "Something went wrong: " + e.getMessage();
            LOG.warn(paLinksCheckErrMsg, e);
            return false;
        }
    }

    /**
     * Get error message for cucumber report if any link in document is invalid (use this in step definition class)
     *
     * @return Error message, if any method of this utils gets error. Otherwise - empty string.
     */
    public String getPracticeAreaLinksErrMsg() {
        return paLinksCheckErrMsg;
    }

    public void setPracticeAreaLinksErrMsg(String paLinksCheckErrMsg) {
        this.paLinksCheckErrMsg = paLinksCheckErrMsg;
    }

    /**
     * Check that link from document points to other correct document
     *
     * @param sectionName Practice Area Section name which related with verifiable document (just for log / report message)
     * @param linkToCheck Element with one link of current document
     * @return True - if check passed. Otherwise - false.
     */
    private boolean isDocumentLinksAreValid(String sectionName, Element linkToCheck) {
        String hrefValue = linkToCheck.attr("href");
        LOG.debug("Checking link '" + linkToCheck.text() + "' and href '" + hrefValue + "', section '"
                + sectionName + "'");

        Elements header = linkingUtils.getElementsFromHtml(linkingUtils.getPageSourceForLink(hrefValue), ".co_title");

        // If document not returned (response status code != 200) or header is absent then wrong page is opened
        if (!linkingUtils.isLinkReturnsTheDocument(hrefValue) || header.isEmpty()) {
            paLinksCheckErrMsg = "Link '" + linkToCheck.text() + "' and href '" + hrefValue + "' is invalid, section '"
                    + sectionName + "'";
            LOG.warn(paLinksCheckErrMsg);
            return false;
        }
        return true;
    }

    public Document openSearchResultLinkAtPositionAndStoreItsTitleAndGuid(String linkPosition) {
        searchResultsPage.waitForPageToLoad();
        Document singleDocument = new Document();
        searchResultsPage.searchResultPosition(linkPosition).click();
        standardDocumentPage.documentTitle().isDisplayed();
        singleDocument.setTitle(standardDocumentPage.documentTitle().getText());
        singleDocument.setGuid(comMethods.getDocumentGUIDFromCurrentURL());
        return singleDocument;
    }

    public String getDocumentGUID() {
        return standardDocumentPage.documentMetaInfo().getAttribute("id").split("_")[3];
    }

    public boolean linkContainsTextAndHrefAttribute(String position, String text, String url) throws Throwable {
        standardDocumentPage.waitForPageToLoad();
        WebElement actualDocument = researchOrganizerPage.getLinkToDocumentAtRowPosition(position);
        String currentText = actualDocument.getText();
        String currentUrl = actualDocument.getAttribute("href");
        text = foldersUtils.makeDocumentShorterForFoldersAndHistoryChecks(text);

        return currentUrl.contains(url) && currentText.contains(text);
//        SoftAssertions softAssertions = new SoftAssertions();
//        softAssertions.assertThat(currentUrl)
//                .withFailMessage("Current URL '" + currentUrl + "' does not contain expected '" + url + "'")
//                .contains(url);
//        softAssertions
//                .assertThat(currentText)
//                .withFailMessage(
//                        "Current document title '" + currentText + "' does not contain expected text '" + text + "'")
//                .contains(text);
//        softAssertions.assertAll();
    }

    /**
     * This private method is used for accessing document directly and to store the case analysis document primary and secondary links as a java object.
     * And directly place the user on the case document screen.
     *
     * @param document
     */
    public void goToDocument(String document) {
        documentObject = getDocument(document);
        /*String documentLink = getPlcUKUrl() + "/Document/" + documentObject.getDocId() + "/View/FullText.html";
        logger.info("Document Link : " + documentLink);
        webDriverDiscovery.getURL(documentLink);*/
    }

    /**
     * This private method is used for document, to store the case/legislation document primary and secondary links as a java object.
     *
     * @param document
     * @return DocumentObject
     */
    public DocumentObject getDocument(String document) {

        // Loading the properties based on the document name
        if (!docsMap.keySet().contains(document)) {

            // Setting the properties into java object and maintaining the document objects as a map for future use.
            DocumentObject docObject = getDocumentAsObject(document);
            docsMap.put(document, docObject);
        }
        return documentObject;
    }

    /*
     * This method is used for CaseAnalysis and Legislation documents only. And read the properties file based on the given file name and move the properties into docbase java bean.
     *
     * @param document - name of the document.
     * @return CaseAnalysisDoc object.
     */
    public DocumentObject getDocumentAsObject(String document) {
        DocumentObject docObject = new DocumentObject();
        Properties caseProps = new Properties();

        // Reading the properties from the given document properties file.

        try {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("src/test/resources/actData/" + document + ".properties");
                caseProps.load(fis);
            } finally {
                if (null != fis) {
                    fis.close();
                }
            }
        } catch (IOException e) {
            try {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream("src/test/resources/actData/" + document + ".properties");
                    caseProps.load(fis);
                } finally {
                    if (null != fis) {
                        fis.close();
                    }
                }
            } catch (IOException e1) {
                LOG.warn(" Unable to find the file.");
            }
        }

        // Setting the properties into java object.

        if (caseProps != null) {
            int nameCount = 0, idCount = 0;
            Map<String, List<String>> map = new HashMap<String, List<String>>();

            for (Object mapKey : caseProps.keySet()) {
                if (mapKey != null) {
                    if (mapKey.equals("name")) {
                        docObject.setDocName((String) caseProps.get(mapKey));
                        nameCount++;
                    } else if (mapKey.equals("docid")) {
                        docObject.setDocId((String) caseProps.get(mapKey));
                        idCount++;
                    } else {
                        String caseAnalysisChildMenu = (String) caseProps.get(mapKey);
                        List<String> childMenus = new ArrayList<String>();
                        for (String str : caseAnalysisChildMenu.split(";")) {
                            childMenus.add(str);
                        }

                        map.put((String) mapKey, childMenus);
                    }
                }
            }
            if (map.size() > 0) {
                docObject.setPrimaryLinks(map.keySet());
                docObject.setSecondaryLinks(map);
            }
        }
        return docObject;
    }

    public void selectViewDocument() {
        if (viewDocumentPopUpPage.isViewDocumentButtonPresent()) {
            viewDocumentPopUpPage.waitUntilPopupPageLoaded();
            viewDocumentPopUpPage.clickOnViewDocumentButton();
            caseDocumentPage.waitUntilPageLoaded();
        }
    }

    public boolean verifyTabTitle(String windowHandle, String expectedPageTitle) {
        caseDocumentPage.waitForPageToLoadAndJQueryProcessing();
        caseDocumentPage.switchToWindow(windowHandle);
        return expectedPageTitle.equals(caseDocumentPage.getPageTitle());
    }
}
