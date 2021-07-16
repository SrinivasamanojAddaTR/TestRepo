package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.landing_page.UnitedKingdomLandingPage;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class UKOpenWebTestSteps extends BaseStepDef {

    private UnitedKingdomLandingPage unitedKingdomLandingPage = new UnitedKingdomLandingPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private HomePage homePage = new HomePage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();


    @When("^the user selects the link entitled Whats Market UK Home$")
    public void theUserSelectsTheLinkEntitledWhatsMarketUkHome() {
        homePage.selectResourceTab();
        homePage.selectLinkPresentOnTab("What's Market");
    }

    @Then("^the user is presented with a message confirming that the user does not have access to the page$")
    public void theUserIsPresentedWithAMessageConfirmingThatTheUserDoesNotHaveAccessToThePage() {
        WebElement box = unitedKingdomLandingPage.blockedContentMessage();
        assertTrue(box.getText().contains("You do not have access to this page"));
    }

    @Then("^the user is able to verify that a page of search results is displayed$")
    public void theUserIsAbleToVerifyThatAPageOfSearchResultsIsDisplayed() {
        assertTrue(knowHowSearchResultsPage.knowHowSearchResultTitle("1").isDisplayed());
    }


    @Then("^the user verifies the presence of the link entitled \"(.*?)\"$")
    public void theUserVerifiesThePresenceOfTheLinkEntitled(String link) {
        assertTrue(searchResultsPage.backToLink(link).isDisplayed());
    }

}
