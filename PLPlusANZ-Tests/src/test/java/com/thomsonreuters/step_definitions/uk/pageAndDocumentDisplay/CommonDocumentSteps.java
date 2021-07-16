package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.TopicPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.CaseDocumentPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.ProvisionPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document_navigation.DocumentNavigationPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document_navigation.LegislationDocumentNavigationPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.enums.DocumentPrimaryLink;
import com.thomsonreuters.pageobjects.pages.pl_plus_researchsearch.*;
import com.thomsonreuters.pageobjects.pages.search.SearchHomePage;
import com.thomsonreuters.pageobjects.utils.document.DocumentObject;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
import com.thomsonreuters.pageobjects.utils.document.StandardDocumentUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.StandardDocumentPage.ResourceType.PRACTICE_NOTES;


public class CommonDocumentSteps {

    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();
    private CommonMethods commonMethods = new CommonMethods();
    private DocumentNavigationPage documentNavigationPage = new DocumentNavigationPage();
    private TopicPage topicPage = new TopicPage();
    private SearchUtils searchUtils = new SearchUtils();
    private StandardDocumentUtils standardDocumentUtils = new StandardDocumentUtils();
    private static Map<String, DocumentObject> docsMap = new HashMap<String, DocumentObject>();
    private DocumentObject documentObject;
    private BaseResultsPage resultsPage = new BaseResultsPage();
    private CaseDocumentPage caseDocumentPage = new CaseDocumentPage();
    private LegislationDocumentNavigationPage legislationDocumentNavigationPage = new LegislationDocumentNavigationPage();
    private UKNewContentTypeClick ukNewContentTypeClick = new UKNewContentTypeClick();
    private ProvisionPage provisionPage = new ProvisionPage();
    private Legislationpage legislation = new Legislationpage();
    private CasesPage cases = new CasesPage();
    private SearchHomePage searchHomePage = new SearchHomePage();
    private ExpandCollapse expandCollapse = new ExpandCollapse();
    public String[] searchTerms;

    private static final Logger LOG = LoggerFactory.getLogger(CommonDocumentSteps.class);

    @When("^user enters search with \"(.*?)\"$")
    public void enterSearchTerm(String docName) throws Throwable {
        standardDocumentUtils.goToDocument(docName);
        documentObject = docsMap.get(docName);
        searchUtils.enterSearchText("adv: \"" + documentObject.getDocName() + "\"");
    }

    @When("^User selects \"(.*?)\"$")
    public void userSelects(String docName) throws Throwable {
        assertTrue(docName + " is missing/not able to find in search results",
                resultsPage.isResultItemDisplayed(documentObject.getDocName()));
        resultsPage.clickOnResultItem(documentObject.getDocName());
        standardDocumentUtils.selectViewDocument();
    }

    @Then("^Document displayed in three column layout$")
    public void documentShowsInThreeColumnLayout() throws Throwable {
        assertTrue("Document column layout is wrong", caseDocumentPage.isDocumentDisplayedInThreeColumnLayout());
    }


    @Then("^\"(.*?)\" menu will be expanded$")
    public void menuWillBeExpanded(String primaryMenuLink) throws Throwable {
        assertTrue(primaryMenuLink + "Primary link is not expanded",
                legislationDocumentNavigationPage.isPrimaryMenuExpanded(DocumentPrimaryLink.getLink(primaryMenuLink)));
    }

    @When("^user enters search specific document \"(.*?)\"$")
    public void userEnterSearchSpecificDocument(String searchTerm) throws Throwable {
        LOG.info("Search item : " + searchTerm);
        searchTerms = searchTerm.split("_");
        searchUtils.enterSearchText("adv: \"" + searchTerms[0] + "\"");
    }

    @When("^User selects specific \"(.*?)\" based on name$")
    public void clickOnDocument(String docName) throws Throwable {
        if (searchTerms.length > 1) {
            resultsPage.clickOnResultItem(searchTerms[1]);
        } else {
            resultsPage.clickOnResultItem(searchTerms[0]);
        }
    }

    @When("^User navigates to the \"(.*?)\" \"(.*?)\"$")
    public void userNavigatesToTheDocument(String docType, String searchTerm) throws Throwable {
        ukNewContentTypeClick.clickUKNewContentType().click();
        if (docType.contains("Legislation")) {
            legislation.ukLegislationClick().click();
        } else if (docType.contains("Case")) {
            cases.ukCasesClick().click();
        }
        searchTerms = searchTerm.split("_");
        searchUtils.enterSearchText("adv: \"" + searchTerms[0] + "\"");
        searchHomePage.searchButton().click();
        expandCollapse.sortByRelevency();
        if (docType.contains("Judgment")) {
            expandCollapse.dropDown().click();
            expandCollapse.less().click();
            resultsPage.navigateToOfficialTranscript(searchTerm);
        } else {
            if (searchTerms.length > 1) {
                resultsPage.clickOnResultItem(searchTerms[1]);
            } else {
                resultsPage.clickOnResultItem(searchTerms[0]);
            }
        }
    }

    @When("^User selects \"(.*?)\" as child menu$")
    public void userSelectsAsChildMenu(String childLink) throws Throwable {
        documentNavigationPage.selectChildMenuLink(childLink);
    }

    @When("^User selects \"(.*?)\" as primary menu$")
    public void selectAnyDocumentLink(String primaryMenuName) throws Throwable {
        documentNavigationPage.selectPrimaryMenuLink(primaryMenuName);
    }

    @Then("^Verify the \"(.*?)\" is present$")
    public void verifyThePrimaryMenuLinkIsPresent(String link) throws Throwable {
        assertTrue(legislationDocumentNavigationPage.isPrimaryMenuPresent(link));
    }


    @When("^User selects child menu \"(.*?)\"$")
    public void userSelectsTheChildMenuLinksUnderPrimaryMenu(String childLinkName) throws Throwable {
        documentNavigationPage.selectChildMenuLink(childLinkName);
    }

    @Then("^Verify the \"(.*?)\" menu link is not present$")
    public void verifyThePrimaryMenuLinkIsNotPresent(String link) throws Throwable {
        assertFalse(documentNavigationPage.isPrimaryMenuPresent(link));
    }

    @Then("^User is able to see StarPagings$")
    public void seeStarPaging(List<String> starPagingWords) throws Throwable {
        for (String starPagingWord : starPagingWords) {
            assertTrue(caseDocumentPage.isStarPagingWordPresent(starPagingWord));
        }
    }

    @Then("^the user click on View Document button$")
    public void theUserClickOnViewDocumentButton() throws Throwable {
        standardDocumentPage.clickOnViewDocumentButton();
    }

    @Then("^document summary contains \"(.*?)\"$")
    public void documentSummaryContains(String summary) {
        String actualSummary = standardDocumentPage.getDocumentSummary().getText();

        assertTrue("Document summary '" + commonMethods.firstHundredChars(actualSummary) + "' does not contain expected text",
                actualSummary.contains(summary));

    }

    @Then("^the full text document will be displayed including \"(.*?)\"$")
    public void theFullTextDocumentWillBeDisplayed(String text) {
        String documentBody = standardDocumentPage.getFullDocumentBody().getText();
        assertTrue("Document '" + commonMethods.firstHundredChars(documentBody) + "' does not contain expected text",
                documentBody.contains(text));

    }

    @Then("^the resource ID \"(.*?)\" will be displayed$")
    public void theResourceIDWillBeDisplayed(String id) {
        String resourceId = standardDocumentPage.getResourceId().getText();
        assertTrue("Resource id '" + resourceId + "' does not contain expected value", resourceId.contains(id));
    }

    @Then("^the copyright will be displayed$")
    public void theCopyrightWillBeDisplayed() {
        WebElement copyright = standardDocumentPage.getCopyright();
        Assert.assertNotNull("Could not find copyright", copyright);
    }

    @Then("link in related content is present with title \"(.*?)\" and status \"(.*?)\"")
    public void linkInRelatedContentIsPresent(String title, String status) {
        String actualStatus = standardDocumentPage.getStatusForLinkInRelatedContent(title).getText();
        assertTrue("Document status '" + actualStatus + "' does not contain expected text", actualStatus.contains(status));
    }

    @When("the user clicks on link in related content with title \"(.*?)\"")
    public void theUserClicksOnLinkInRelatedContent(String title) {
        standardDocumentPage.getLinkInRelatedContent(title).click();
    }

    @Then("there will be text informing the user to login to view full text document")
    public void thereWillBeTextInformingToLogin() {
        String documentBody = standardDocumentPage.getFullDocumentBody().getText();
        String message = "To access this resource, log in below or register for free access";
        assertTrue("Document '" + commonMethods.firstHundredChars(documentBody) + "' does not contain expected text",
                documentBody.contains(message));
    }

    @Then("\"(.*?)\" button is present in document body")
    public void buttonIsPresentInDocumentBody(String title) {
        WebElement button = standardDocumentPage.getLinkFromSection("", title);
        assertTrue("Button with text '" + title + "' is not present in document", button.isDisplayed());
    }

    @When("^the user clicks on button with title \"(.*?)\"$")
    public void theUserClicksOnButtonWithTitle(String title) {
        WebElement button = standardDocumentPage.getLinkFromSection("", title);
        standardDocumentPage.scrollIntoViewAndClick(button);
    }

    @Then("document body does not contain text \"(.*?)\"")
    public void documentBodyDoesNotContainText(String text) {
        String documentBody = standardDocumentPage.getFullDocumentBody().getText();
        assertFalse("Document '" + commonMethods.firstHundredChars(documentBody) + "' contains text that should not be there",
                documentBody.contains(text));
    }

    @Then("document body contains lines")
    public void documentBodyContainsStrings(List<String> lines) {
        String documentBody = standardDocumentPage.getFullDocumentBody().getText();
        SoftAssertions softly = new SoftAssertions();
        for (String line : lines) {
            softly.assertThat(documentBody.contains(line))
                    .overridingErrorMessage(
                            "Document '" + commonMethods.firstHundredChars(documentBody) + "' does not contain text: " + line).isTrue();
        }
        softly.assertAll();
    }


    @Then("^the user should see the Practice Note document with the title \"(.*?)\"$")
    public void checkThatPracticeNoteWithTitlePresent(String documentTitle) {
        assertTrue("Document title is not '" + documentTitle + "'", standardDocumentPage.isDocumentTitleEquals(documentTitle));
        assertTrue("Document resource type is not '" + PRACTICE_NOTES.getName() + "'", standardDocumentPage.isResourceTypeEquals(PRACTICE_NOTES));
    }

    @Then("^the user sees Also found in section$")
    public void theUserSeesAlsoFoundInSection() {
        assertTrue("'Also found in' section is not present", documentNavigationPage.linkInAlsoFoundInSection().isDisplayed());
    }

    @Then("^Also Found In section includes link to the relevant Topic page$")
    public void alsoFoundInSectionIncludeLinkToTheRelevantTopicPage() {
        String linkToRelevantTopicPage = documentNavigationPage.linkInAlsoFoundInSection().getAttribute("href").split
                ("\\?")[0];
        documentNavigationPage.linkInAlsoFoundInSection().click();
        topicPage.waitForPageToLoad();
        String actualUrl = topicPage.getCurrentUrl().split("\\?")[0];
        assertEquals("'Also Found In' section has link to irrelevant Topic page", linkToRelevantTopicPage, actualUrl);
    }

}

