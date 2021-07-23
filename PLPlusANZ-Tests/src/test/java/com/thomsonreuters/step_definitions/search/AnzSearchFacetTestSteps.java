package com.thomsonreuters.step_definitions.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
import cucumber.api.Delimiter;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by UC181137 on 23/10/2015.
 */
public class AnzSearchFacetTestSteps extends BaseStepDef {

    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private SearchUtils searchUtils = new SearchUtils();

    @When("^the user selects the know how following parent facets with their respective count saved$")
    public void theUserSelectsTheKnowHowFollowingParentFacets(List<String> facets) throws Throwable {

        for (String facet : facets) {

            knowHowSearchResultsPage.knowHowFacetCheckbox(facet).click();
            assertTrue("Check box not selected..!", searchUtils.isCheckboxSeleted(facet));
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

    @Then("^the know how facet \"(.*?)\" is displayed under \"(.*?)\"$")
    public void knowHowFacetIsDisplayed(String expectedFacetName, @Delimiter(" > ")List<String> facetNames) {
        facetNames.forEach(facetName -> searchUtils.expandFacet(facetName));
        assertTrue("Legal updates facet is not displayed..!",knowHowSearchResultsPage.isElementDisplayed(knowHowSearchResultsPage.knowHowFacetCheckbox(expectedFacetName)));
    }

    @When("^the user verifies the presence of the know how facet groups for PLAU$")
    public void theUserVerifiesThePresenceOfTheKnowHowFacetGroups() throws Throwable {
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderResourceType().isDisplayed());
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderPracticeArea().isDisplayed());
        assertTrue(knowHowSearchResultsPage.facetGroupHeaderPLAUJurisdiction().isDisplayed());
    }
    @When("^the user verifies the presence of following know how facet groups for PLAU$")
    public void theUserVerifiesThePresenceOfTheKnowHowFacetGroups(List<String> facetHeadings) {
        SoftAssert softAssert = new SoftAssert();
        facetHeadings.forEach(facetName ->
                assertThat(knowHowSearchResultsPage.isElementDisplayed(knowHowSearchResultsPage.knowHowFacet(facetName)))
                        .overridingErrorMessage("%s facet is not displayed", facetName).isTrue()
        );
        softAssert.assertAll();
    }

}
