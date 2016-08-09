package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.driver.configuration.Hosts;
import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.CaseDocumentPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.ProvisionPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.ViewDocumentPopUpPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.LegislationDocumentNavigationPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchSearch.*;
import com.thomsonreuters.pageobjects.pages.search.SearchHomePage;
import com.thomsonreuters.pageobjects.utils.RoutingPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public abstract class DocumentDisplayStep extends BaseStepDef {

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(DocumentDisplayStep.class);

    protected static Map<String, DocumentObject> docsMap = new HashMap<String, DocumentObject>();
    protected static int routingCount = 0;
    protected DocumentObject documentObject;

    protected UKNewContentTypeClick ukNewContentTypeClick = new UKNewContentTypeClick();
    protected Legislationpage legislation = new Legislationpage();
    protected CasesPage cases = new CasesPage();
    protected RoutingPage routingPage = new RoutingPage();
    protected ViewDocumentPopUpPage viewDocumentPopUpPage = new ViewDocumentPopUpPage();
    protected CaseDocumentPage caseDocumentPage = new CaseDocumentPage();
    protected NavigationCobalt navigationCobalt = new NavigationCobalt();
    protected ProvisionPage provisionPage = new ProvisionPage();
    protected Hosts hosts = new Hosts();
    protected SearchHomePage searchHomePage = new SearchHomePage();
    protected BaseResultsPage resultsPage = new BaseResultsPage();
    protected LegislationDocumentNavigationPage legislationDocumentNavigationPage = new LegislationDocumentNavigationPage();
    protected ExpandCollapse expandCollapse = new ExpandCollapse();

    private String baseUrl = System.getProperty("base.url");

    /**
     * This method does the routing to access the documents directly in PPI.
     */
    protected void doRouting() {
        if (routingCount == 0) {
            try {
                routingPage.navigate(hosts.getPlcukProductBase() + baseUrl + hosts.getPlcukDomain() + "/routing");
                routingPage.showFeatureSelectionsLink().click();

                WebElement ignore = routingPage.ignoreAuthorizationBlocksDropdown();
                routingPage.selectDropDownByVisibleText(ignore, "Grant");

                WebElement pre = routingPage.preReleaseContentDropdown();
                routingPage.selectDropDownByVisibleText(pre, "Grant");

                WebElement bypass = routingPage.wlnByPass100KAncillaryDropdown();
                routingPage.selectDropDownByVisibleText(bypass, "Grant");

                routingPage.saveChangesAndSignOnButton().click();

                routingCount++;

            } catch (Throwable t) {
                logger.warn("Error is processing routing, so routing aborted.");
                navigationCobalt.navigateToPLUKPlus();
            }
        }
    }

    /**
     * This private method is used for accessing document directly and to store the case analysis document primary and secondary links as a java object.
     * And directly place the user on the case document screen.
     *
     * @param document
     */
    protected void goToDocument(String document) {
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
    private DocumentObject getDocument(String document) {

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
    protected DocumentObject getDocumentAsObject(String document) {
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
                logger.warn(" Unable to find the file.");
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

//    /**
//     * This is an reusable method for all the documents. And its used to login into PLC UK site if the user is not logged in already.
//     */
//    protected void loginIntoPlcUK()
//    {
//        if(!loginPage.isUserLoggedInAlready()) {
//            navigationCobalt.navigateToPLUKPlus();
//
//            doRouting();
//
//            if(loginPage.isSignOnButtonOnSignOffPage()){
//                loginPage.clickSignOnButtonOnSignOffPage();
//            }
//
//            loginPage.loginWithClientId();
//        }else{
//            goToHomePage();
//        }
//    }

    /**
     * User should be logged in and selected the document to view.
     * This method is used to select the viewButton present on View document popup.
     */
    protected void selectViewDocument() {
        if (viewDocumentPopUpPage.isViewDocumentButtonPresent()) {
            viewDocumentPopUpPage.waitUntilPopupPageLoaded();
            viewDocumentPopUpPage.clickOnViewDocumentButton();
            caseDocumentPage.waitUntilPageLoaded();
        }
    }

    protected void goToHomePage() {
        navigationCobalt.navigateToHomePage();
    }

    public List<String> getFolderPathAsList(String folderPath) {
        return Arrays.asList(folderPath.split("/"));
    }

}