package com.thomsonreuters.step_definitions.uk.legalUpdates.web;

import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesPracticeAreaPage;
import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesWidgetPage;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbilityToLinkToRSSInfoPageFromLUWidget1 extends BaseStepDef {

    private HomePage practicalLawHomepage = new HomePage();
    private LegalUpdatesWidgetPage legalUpdatesWidget = new LegalUpdatesWidgetPage();
    private LegalUpdatesPracticeAreaPage legalUpdatesPracticeAreaPage = new LegalUpdatesPracticeAreaPage();
    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();

    @Given("^a user is on the Legal Updates Home page$")
    public void aUserIsOnTheLegalUpdatesHomePage() throws Throwable {
        practicalLawHomepage.selectResourceTab();
        practicalLawHomepage.legalUpdatesContentLink().click();
        legalUpdatesPracticeAreaPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Given("^the user is presented with the Legal Updates widget$")
    public void theUserIsPresentedWithTheLegalUpdatesWidget() throws Throwable {
        assertTrue("Legal Updates Widget is not displayed", legalUpdatesPracticeAreaPage.legalUpdatesWidget().isDisplayed());
    }

    @Given("^the user should be presented with an 'RSS' Link$")
    public void theUserShouldBePresentedWithAnRSSLink() throws Throwable {
        assertTrue("RSS button is absent on Legal Updates Widget", legalUpdatesWidget.isRssButtonDisplayed());
    }

    @When("^the user clicks on the RSS link$")
    public void theUserClicksOnTheRSSLink() throws Throwable {
        legalUpdatesWidget.rssButton().click();
        standardDocumentPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Given("^the user should not be presented with an 'RSS' Link$")
    public void theUserShouldNotBePresentedWithAnRSSLink() throws Throwable {
        assertFalse("RSS button is present on Legal Updates Topic Widget", legalUpdatesWidget.isRssButtonDisplayed());
    }

}