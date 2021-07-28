package com.thomsonreuters.pageobjects.utils.document;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.CaseDocumentPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.ViewDocumentPopUpPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.rest.LinkingBaseUtils;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardDocumentUtils {

    private LinkingBaseUtils linkingUtils = new LinkingBaseUtils();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();
    private CommonMethods comMethods = new CommonMethods();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private ViewDocumentPopUpPage viewDocumentPopUpPage = new ViewDocumentPopUpPage();
    private CaseDocumentPage caseDocumentPage = new CaseDocumentPage();

    // Error message for Cucumber report
    private String paLinksCheckErrMsg = "";

    private static final Logger LOG = LoggerFactory.getLogger(StandardDocumentUtils.class);

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
        if (LOG.isDebugEnabled()) {
            LOG.debug(buildLogMessage(sectionName, linkToCheck, hrefValue));
        }

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
    private String buildLogMessage(String sectionName, Element linkToCheck, String hrefValue) {
        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append("Checking link '");
        logMessageBuilder.append(linkToCheck.text());
        logMessageBuilder.append("' and href '");
        logMessageBuilder.append(hrefValue);
        logMessageBuilder.append("', section '");
        logMessageBuilder.append(sectionName);
        logMessageBuilder.append("'");
        return logMessageBuilder.toString();
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

    public boolean linkContainsTextAndHrefAttribute(String position, String text, String url) {
        standardDocumentPage.waitForPageToLoad();
        WebElement actualDocument = researchOrganizerPage.getLinkToDocumentAtRowPosition(position);
        String currentText = actualDocument.getText();
        String currentUrl = actualDocument.getAttribute("href");
        text = FoldersUtils.makeDocumentShorterForFoldersAndHistoryChecks(text);

        return currentUrl.contains(url) && currentText.contains(text);
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
