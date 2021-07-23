package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UKKnowHowSearchResultChangeDetailLevelS2_90TestSteps extends BaseStepDef {

    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Then("^the user can select the option to show most detail$")
    public void theUserCanSelectTheOptionToShowMostDetail() throws Throwable {
        searchResultsPage.moreOrLessDetailAnchor().click();
        searchResultsPage.mostDetailOption().click();
    }

    @Then("^the user can select the option to show less detail$")
    public void theUserCanSelectTheOptionToShowLessDetail() throws Throwable {
        searchResultsPage.moreOrLessDetailAnchor().click();
        searchResultsPage.lessDetailOption().click();
        searchResultsPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^the user is able to verify a brief description of the content is not displayed$")
    public void theUserIsAbleToVerifyABriefDescriptionOfTheContentIsNotDisplayed() throws Throwable {
        assertFalse(searchResultsPage.firstResultAbstractText().isDisplayed());
    }

    @Then("^the user can verify that the more detail icon is displayed$")
    public void theUserCanVerifyThatTheMoreDetailIconForTermsInContextIsDisplayed() throws Throwable {
        assertTrue(searchResultsPage.isSliderSelectorDisplayed(SearchResultsPage.SliderSelector.MORE));
    }

    @Then("^the user is able to verify that terms in context are displayed appropriate to the more setting$")
    public void theUserIsAbleToVerifyThatTermsInContextAreDisplayedAppropriateToTheMoreSetting() throws Throwable {
        searchResultsPage.firstSnippetPara().isDisplayed();
        assertFalse(searchResultsPage.secondSnippetPara().isDisplayed());
    }

    @Then("^the user can verify that the most detail icon is displayed$")
    public void theUserCanVerifyThatTheMostDetailIconForTermsInContextIsDisplayed() throws Throwable {
        assertTrue(searchResultsPage.isSliderSelectorDisplayed(SearchResultsPage.SliderSelector.MOST));
    }

    @Then("^the user is able to verify that terms in context are displayed appropriate to the most setting$")
    public void theUserIsAbleToVerifyThatTermsInContextAreDisplayedAppropriateToTheMostSetting() throws Throwable {
        assertTrue(searchResultsPage.firstSnippetPara().isDisplayed());
        assertTrue(searchResultsPage.secondSnippetPara().isDisplayed());
    }

    @Then("^the user can verify that the less detail icon is displayed$")
    public void theUserCanVerifyThatTheLessDetailIconForTermsInContextIsDisplayed() throws Throwable {
        assertTrue(searchResultsPage.isSliderSelectorDisplayed(SearchResultsPage.SliderSelector.LESS));
    }

    @Then("^the user is able to verify that terms in context are displayed appropriate to the less setting$")
    public void theUserIsAbleToVerifyThatTermsInContextAreDisplayedAppropriateToTheLessSetting() throws Throwable {
        assertFalse(searchResultsPage.firstResultAbstractText().isDisplayed());
        assertFalse(searchResultsPage.firstSnippetPara().isDisplayed());
    }

    @When("^the user can select the option to show more detail$")
    public void theUserCanSelectTheOptionToShowMoreDetail() throws Throwable {
        searchResultsPage.moreOrLessDetailAnchor().click();
        searchResultsPage.moreDetailOption().click();
    }

    @When("^the user is able to log off$")
    public void theUserIsAbleToLogOff() throws Throwable {
        searchResultsPage.signOffLink().click();
    }

    @When("^the user selects the Practical Law Home logo")
    public void theUserSelectsThePracticalLawHomeLogo() throws Throwable {
        searchResultsPage.practicalLawLogo().click();

    }

}