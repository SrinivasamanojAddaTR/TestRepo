package com.thomsonreuters.step_definitions.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
import com.thomsonreuters.step_definitions.uk.search.BasicKnowHowSearchUKS101TestSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by UC181137 on 23/10/2015.
 */
public class AnzSearchResultsLessMoreMostTestSteps extends BaseStepDef {

    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private SearchUtils searchUtils = new SearchUtils();
    private BasicKnowHowSearchUKS101TestSteps basicKnowHowSearchUKS101TestSteps = new BasicKnowHowSearchUKS101TestSteps();
    private FooterUtils footerUtils = new FooterUtils();


    @When("^the user should verify the presence of following search structure for \"(.*)\" option$")
    public void theUserObtainsTheTitleOfTheFirstResultAndStoresIt(String option, List<String> searchStructure) throws Throwable {

        for (String structure : searchStructure) {

            if (structure.equalsIgnoreCase("Title")) {
                assertTrue(structure + " is not displayed..!", searchResultsPage.firstResultTitle().isDisplayed());
            } else if (structure.equalsIgnoreCase("Resource Type")) {
                assertTrue(structure + " is not displayed..!", searchResultsPage.resourceTypeDescription().isDisplayed());
            } else if (structure.equalsIgnoreCase("Jurisdiction ")) {
                assertTrue(structure + " is not displayed..!", searchResultsPage.jurisdictionsForFirstResult().isDisplayed());
            } else if (structure.equalsIgnoreCase("Status")) {
                String statusText = searchResultsPage.statusForFirstResult().getText();
                assertTrue(structure + " is not displayed..!", statusText.contains("Maintained") || statusText.contains("Published") || statusText.contains("Law stated as at") || statusText.contains("Law stated as of"));
            }
            if (option.equalsIgnoreCase("less")) {
                assertFalse("In " + option + " abstact is displayed..!", searchResultsPage.firstResultAbstractText().isDisplayed());
                //             assertFalse("In "+option+" first snippet is displayed..!",commonMethods.waitForElement(searchResultsPage.firstSnippetByPara(),1000)==null);
            } else if (option.equalsIgnoreCase("more")) {
                assertTrue("In " + option + " abstact is not displayed..!", searchResultsPage.firstResultAbstractText().isDisplayed());
                assertTrue("In " + option + " first snippet not displayed..!", searchResultsPage.firstSnippetPara().isDisplayed());
                assertFalse("In " + option + " second snippet is displayed..!", searchResultsPage.secondSnippetPara().isDisplayed());
            } else if (option.equalsIgnoreCase("most")) {
                assertTrue("In " + option + " abstact is not displayed..!", searchResultsPage.firstResultAbstractText().isDisplayed());
                assertTrue("In " + option + " first snippet is not displayed..!", searchResultsPage.firstSnippetPara().isDisplayed());
                assertTrue("In " + option + " second snippet is not displayed..!", searchResultsPage.secondSnippetPara().isDisplayed());
            } else if (option.equalsIgnoreCase("more in OpenWeb")) {
                assertTrue("In " + option + " abstact is not displayed..!", searchResultsPage.firstResultAbstractText().isDisplayed());
            }
        }
    }

    @Then("^the user should see the each search result with search term \"(.*)\" in the search result snippet$")
    public void theUserShouldSeeTheEachSearchResultAccordingToTheTerm(String searchTerm) throws Throwable {
        footerUtils.closeDisclaimerMessage();
        searchResultsPage.waitForExpectedElement(knowHowSearchResultsPage.searchResultByCountLabel(), 3000);
        assertTrue("Search term row count is not matching..!", searchUtils.isTermFound(searchTerm));
    }

    @When("^the user runs specific operator search for the query \"(.*)\"$")
    public void theUserRunsATextSearchForTheQuery(String query) throws Throwable {
        String searchTermArray[] = query.split(" ");
        if (searchTermArray.length > 2) {
            query = "adv: " + query;
            searchTermArray[0] = searchTermArray[0].toLowerCase();
            searchTermArray[2] = searchTermArray[2].toLowerCase();
        }
        basicKnowHowSearchUKS101TestSteps.theUserRunsAFreeTextSearchForTheQuery(query);
    }

    @When("^user should see the search results$")
    public void theUserClicksOnTheFirstLinkInResults() {
        assertTrue("Search results is not showing..!", searchResultsPage.firstResultTitle().isDisplayed());
    }
}
