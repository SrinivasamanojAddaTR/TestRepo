package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

/**
 * Created by Steph Armytage on 03/11/2014.
 */
public class UKKnowHowSortByDateS2_58Test extends BaseStepDef {

    public SearchResultsPage searchResultsPage = new SearchResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();


    @Then("^the user can select the option to sort by \"(.*?)\"$")
    public void the_user_can_select_the_option_to_sort_by_(String sortOption) throws Throwable {
        searchResultsPage.sortByDropdownLink().click();
        searchResultsPage.sortByDropDownOption(sortOption).click();
        knowHowSearchResultsPage.waitForSearchResults();
    }

    @When("^the user verifies that the option for sorting by date is displayed$")
    public void the_user_verifies_that_the_option_for_sorting_by_date_is_displayed() throws Throwable {
        WebElement dropdown = searchResultsPage.sortByDropdownSelectedOption();
        assertTrue(dropdown.getText().trim().equals("Date"));
    }


}
