package com.thomsonreuters.step_definitions.uk.legal_updates.web;

import com.thomsonreuters.pageobjects.pages.legal_updates.LegalUpdatesPracticeAreaPage;
import com.thomsonreuters.pageobjects.pages.legal_updates.LegalUpdatesWidgetPage;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.StandardDocumentPage;
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
       if(legalUpdatesWidget.isRssButtonDisplayed()==true){
        assertTrue("RSS button is present on Legal Updates Topic Widget", legalUpdatesWidget.isRssButtonDisplayed());
        }
        }
      
  
    @When("^the user clicks on the RSS link$")
    public void theUserClicksOnTheRSSLink() throws Throwable {
        legalUpdatesWidget.rssButton().click();
        standardDocumentPage.waitForPageToLoadAndJQueryProcessing();
    }

   
}