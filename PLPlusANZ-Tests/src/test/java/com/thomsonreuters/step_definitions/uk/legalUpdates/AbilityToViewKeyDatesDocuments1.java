package com.thomsonreuters.step_definitions.uk.legalUpdates;

import com.thomsonreuters.pageobjects.pages.legal_updates.LegalUpdatesTopicPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class AbilityToViewKeyDatesDocuments1 extends BaseStepDef {

    private LegalUpdatesTopicPage legalUpdatesTopicPage = new LegalUpdatesTopicPage();
    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();

    @When("^the user clicks on the tab \"(.*?)\"$")
    public void theUserClicksOnTheTab(String tabName) throws Throwable {
        legalUpdatesTopicPage.specifiedTab(tabName).click();
        legalUpdatesTopicPage.waitForPageToLoad();
    }

    @When("^clicks on document link \"(.*?)\"$")
    public void clicksOnDocumentLink(String documentLink) throws Throwable {
        legalUpdatesTopicPage.linkOnTab(documentLink).click();
        legalUpdatesTopicPage.waitForPageToLoad();
    }

    @Then("^user should be presented with proper document \"(.*?)\"$")
    public void userShouldBePresentedWithProperDocument(String documentTitle) throws Throwable {
        assertTrue("Document title is incorrect. Actual title: " + standardDocumentPage.documentTitle().getText().trim(), standardDocumentPage.documentTitle().getText().trim().contains(documentTitle));
    }

}