package com.thomsonreuters.step_definitions.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by UC181137 on 23/10/2015.
 */
public class AnzSearchSort extends BaseStepDef {

    private String firstResultItem;
    private String secondResultItem;
    private String thirdResultItem;

    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();


    @When("^the user obtains the title of the first, second and third result and stores it$")
    public void theUserObtainsTheTitleOfTheFirstResultAndStoresIt() throws Throwable {
        firstResultItem = searchResultsPage.getResultItem("1");
        secondResultItem = searchResultsPage.getResultItem("2");
        thirdResultItem = searchResultsPage.getResultItem("3");
    }

    @When("^the user can verify that the title of the first, second and third results should not be the same as the stored values$")
    public void theUserCanVerifyThatTheTitleOfTheFirstResultIsNotTheSameAsTheStoredValue() throws Throwable {
        assertFalse(searchResultsPage.getResultItem("1").trim().equals(firstResultItem));
        assertFalse(searchResultsPage.getResultItem("2").trim().equals(secondResultItem));
        assertFalse(searchResultsPage.getResultItem("3").trim().equals(thirdResultItem));
    }

    @Then("^the user is able to verify that the title of the first,second and third results should be the same as the stored values$")
    public void theUserIsAbleToVerifyThatTheTitleOfTheFirstResultIsTheSameAsTheStoredValue() throws Throwable {
        assertTrue(searchResultsPage.getResultItem("1").trim().equals(firstResultItem));
        assertTrue(searchResultsPage.getResultItem("2").trim().equals(secondResultItem));
        assertTrue(searchResultsPage.getResultItem("3").trim().equals(thirdResultItem));
    }

    @When("^the user verifies the search results count \"(.*)\"$")
    public void theUserVerifiesTheSearchResultCount(String count) throws Throwable {
        String expectedCount=count.replaceAll("[^0-9]","");
        System.out.println("Expected Count : "+expectedCount);
        String actualCount = knowHowSearchResultsPage.knowHowSearchResultCount().getText();
        actualCount = actualCount.replaceAll("[^0-9]", "");
        System.out.println("Actual Count : "+actualCount);
        assertTrue(expectedCount+" not matching..!",actualCount.equalsIgnoreCase(expectedCount));

    }


}
