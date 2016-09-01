package com.thomsonreuters.step_definitions.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.step_definitions.uk.search.facetJavaTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by UC181137 on 23/10/2015.
 */
public class AnzSearchFacetTest extends BaseStepDef {

    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private facetJavaTest facetjavaTest = new facetJavaTest();

    @When("^the user selects the know how following parent facets with their respective count saved$")
    public void theUserSelectsTheKnowHowFollowingParentFacets(List<String> facets) throws Throwable {

        for (String facet : facets) {

            knowHowSearchResultsPage.knowHowFacetCheckbox(facet).click();
            assertTrue("Check box not selected..!", facetjavaTest.isCheckboxSeleted(facet));
        }
    }

    @When("^the user verifies that the know how following facet is selected and their count is equal to total count$")
    public void theUserVerifiesThatTheKnowHowFollowingFacetIsSelected(List<String> facets) throws Throwable {
        int facetCount=0;
        String totalSearchCount = knowHowSearchResultsPage.knowHowSearchResultCount().getText();
        totalSearchCount = totalSearchCount.replaceAll("[^0-9]", "");
        int totalCount=Integer.parseInt(totalSearchCount);

        for (String facet : facets) {
            totalSearchCount=knowHowSearchResultsPage.facetCount(facet).getText().replaceAll("[^0-9]", "");;
            facetCount= facetCount+Integer.parseInt(totalSearchCount);
            assertTrue(knowHowSearchResultsPage.knowHowFacetCheckbox(facet).isSelected());
        }
        assertTrue("Facet Count "+facetCount+" not displayed..!",facetCount==totalCount);
    }

	@Then("^all the facet counts are less or equal to the total number of search results$")
	public void allTheFacetCountsAreLessOrEqualToTheTotalNumberOfSearchResults() {
		String totalCountString = knowHowSearchResultsPage.knowHowSearchResultCount().getText().replaceAll("[^0-9]", "");
		int totalCount = Integer.parseInt(totalCountString);

		List<String> facets = knowHowSearchResultsPage.getAllFacets();
		for (String facet : facets) {
			String facetCountString = knowHowSearchResultsPage.facetCount(facet).getText().replaceAll("[^0-9]", "");
			int facetCount = Integer.parseInt(facetCountString);
			assertTrue("Facet count for facet '" + facet + "' is " + facetCount + " while total count is " + totalCount,
					facetCount <= totalCount);
		}

	}
	
	@Then("^the know how facet \"(.*?)\" is displayed$")
	public void knowHowFacetIsDisplayed(String facetName) {
		assertTrue("Legal updates facet is not displayed..!",knowHowSearchResultsPage.isElementDisplayed(knowHowSearchResultsPage.knowHowFacetCheckbox(facetName)));
	}
    @When("^the user verifies the presence of the know how facet groups for PLAU$")
    public void theUserVerifiesThePresenceOfTheKnowHowFacetGroups() throws Throwable {
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderResourceType().isDisplayed());
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderPracticeArea().isDisplayed());
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderPLAUJurisdiction().isDisplayed());
    }
    @When("^the user verifies the presence of following know how facet groups for PLAU$")
    public void theUserVerifiesThePresenceOfTheKnowHowFacetGroups(List<String> facetHeadings) throws Throwable {
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderResourceType().isDisplayed());
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderPracticeArea().isDisplayed());
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderPLAUJurisdiction().isDisplayed());
    }

}
