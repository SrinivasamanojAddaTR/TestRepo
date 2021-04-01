package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.List;

public class UKKnowHowPaginationS2_27Test extends BaseStepDef {

    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Given("^the user is able to verify the presence of page number \"(.*?)\"$")
    public void theUserIsAbleToVerifyThePresenceOfPageNumber(String number) throws Throwable {
        searchResultsPage.pagination(number).isDisplayed();
    }

    @Then("^the user is able to verify the presence of below page numbers$")
    public void theUserIsAbleToVerifyThePresenceOfPageNumbers(List<String> numbers) throws Throwable {
        for (String number : numbers) {
            searchResultsPage.pagination(number).isDisplayed();
        }
    }

    @Given("^the user is able to select the link to page \"(.*?)\"$")
    public void theUserIsAbleToSelectTheLinkToPage(String arg1) throws Throwable {
        searchResultsPage.pagination(arg1).click();
    }

}