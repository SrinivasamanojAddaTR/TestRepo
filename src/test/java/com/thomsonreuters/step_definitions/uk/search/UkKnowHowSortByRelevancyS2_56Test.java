package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UkKnowHowSortByRelevancyS2_56Test extends BaseStepDef {

    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();

    private String firstResultItem;
    private String secondResultItem;
    private String thirdResultItem;


    @When("^the user verifies that the option for sorting by relevance is displayed by default$")
    public void theUserVerifiesThatTheOptionForSortingByRelevanceIsDisplayedByDefault() throws Throwable {
        assertTrue(searchResultsPage.sortByDropdownSelectedOption().getText().trim().equals("Relevance"));
    }

    @When("^the user obtains the title of the first result and stores it$")
    public void theUserObtainsTheTitleOfTheFirstResultAndStoresIt() throws Throwable {
        firstResultItem = searchResultsPage.getResultItem("1");
    }

    @When("^the user obtains the title of the second result and stores it$")
    public void theUserObtainsTheTitleOfTheSecondResultAndStoresIt() throws Throwable {
        secondResultItem = searchResultsPage.getResultItem("2");
    }

    @When("^the user obtains the title of the third result and stores it$")
    public void theUserObtainsTheTitleOfTheThirdResultAndStoresIt() throws Throwable {
        thirdResultItem = searchResultsPage.getResultItem("3");
    }

    @When("^the user can verify that the title of the first result is not the same as the stored value$")
    public void theUserCanVerifyThatTheTitleOfTheFirstResultIsNotTheSameAsTheStoredValue() throws Throwable {
        assertFalse(searchResultsPage.getResultItem("1").equals(firstResultItem));
    }

    @When("^the user can verify that the title of the second result is not the same as the stored value$")
    public void theUserCanVerifyThatTheTitleOfTheSecondResultIsNotTheSameAsTheStoredValue() throws Throwable {
        assertFalse(searchResultsPage.getResultItem("2").equals(secondResultItem));
    }

    @When("^the user can verify that the title of the third result is not the same as the stored value$")
    public void theUserCanVerifyThatTheTitleOfTheThirdResultIsNotTheSameAsTheStoredValue() throws Throwable {
        assertFalse(searchResultsPage.getResultItem("3").equals(thirdResultItem));
    }

    @When("^the user can select the option to sort by relevance$")
    public void theUserCanSelectTheOptionToSortByRelevance() throws Throwable {
        WebElement dropdown = searchResultsPage.sortByDropdownList();
        assertTrue(dropdown.getText().equals("Date"));
        searchResultsPage.selectDropDownByVisibleText(searchResultsPage.getSortDropDownList(), "Relevance");
        knowHowSearchResultsPage.waitForSearchResults();
    }

    @Then("^the user is able to verify that the title of the first result is the same as the stored value$")
    public void theUserIsAbleToVerifyThatTheTitleOfTheFirstResultIsTheSameAsTheStoredValue() throws Throwable {
        assertTrue(searchResultsPage.getResultItem("1").equals(firstResultItem));
    }

    @Then("^the user is able to verify that the title of the second result is the same as the stored value$")
    public void theUserIsAbleToVerifyThatTheTitleOfTheSecondResultIsTheSameAsTheStoredValue() throws Throwable {
        assertTrue(searchResultsPage.getResultItem("2").equals(secondResultItem));
    }

    @Then("^the user is able to verify that the title of the third result is the same as the stored value$")
    public void theUserIsAbleToVerifyThatTheTitleOfTheThirdResultIsTheSameAsTheStoredValue() throws Throwable {
        assertTrue(searchResultsPage.getResultItem("3").equals(thirdResultItem));
    }

}