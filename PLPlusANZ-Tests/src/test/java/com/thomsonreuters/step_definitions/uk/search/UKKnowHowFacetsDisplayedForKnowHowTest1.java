package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import gherkin.formatter.model.DataTableRow;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UKKnowHowFacetsDisplayedForKnowHowTest1 {

    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Then("^the user is able to verify the presence of the facet group heading Resource Type$")
    public void theUserIsAbleToVerifyThePresenceOfTheFacetGroupHeadingResourceType() throws Throwable {
        knowHowSearchResultsPage.facetGroupHeaderResourceType().isDisplayed();
    }

    @Then("^the user is able to verify the presence of the facet group heading Practice Area$")
    public void theUserIsAbleToVerifyThePresenceOfTheFacetGroupHeadingPracticeArea() throws Throwable {
        knowHowSearchResultsPage.facetGroupHeaderPracticeArea().isDisplayed();
    }

    @Then("^the user is able to verify the (presence|absence) of the facet group heading Jurisdiction$")
    public void theUserIsAbleToVerifyThePresenceOfTheFacetGroupHeadingJurisdiction(String presence) throws Throwable {
        boolean mustBeDisplayed = presence.equals("presence");
        try {
            boolean displayed = knowHowSearchResultsPage.facetGroupHeaderJurisdiction().isDisplayed();
            assertTrue(displayed == mustBeDisplayed);
        } catch (TimeoutException e) {
            if (presence.equals("presence")) {
                throw new Exception("TimeoutException occurred", e);
            }
        }
    }

    @Then("^the user is able to verify the presence of the know how facet \"(.*?)\"$")
    public void theUserIsAbleToVerifyThePresenceOfTheKnowHowFacet(String arg1) throws Throwable {
        knowHowSearchResultsPage.facetName(arg1).isDisplayed();
    }

    @Then("^the user is able to verify the presence of the know how facets$")
    public void theUserIsAbleToVerifyThePresenceOfTheKnowHowFacet(List<String> list) throws Throwable {
        for (String facet : list) {
            assertTrue(knowHowSearchResultsPage.facetName(facet).isDisplayed());
        }
    }

    @Then("^the user is able to verify the presence of the title of the first know how result$")
    public void theUserIsAbleToVerifyThePresenceOfTheTitleOfTheFirstKnowHowResult() throws Throwable {
        assertTrue(searchResultsPage.firstResultTitle().isDisplayed());
    }

    @Then("^the user is able to verify the presence of a know how resource type description$")
    public void theUserIsAbleToVerifyThePresenceOfAKnowHowResourceTypeDescription() throws Throwable {
        searchResultsPage.resourceTypeDescription().isDisplayed();
    }

    @Then("^User verifies facets are displayed under the given facet group$")
    public void userVerifiesFacetAreDisplayed(DataTable table) throws Throwable {
        for (DataTableRow row : table.getGherkinRows()) {
            assertTrue(knowHowSearchResultsPage.isFacetDisplayed(row.getCells().get(0), row.getCells().get(1).split("_")));
        }
    }

    @Then("^User verifies that facets are not displayed under the given facet group$")
    public void userVerifiesFacetAreNotDisplayed(Map<String, String> map) throws Throwable {
        for (String key : map.keySet()) {
            assertFalse(knowHowSearchResultsPage.isFacetNotDisplayed(key, map.get(key).split("_")));
        }
    }

    @Then("^User verifies Parent facets are displayed under the given facet group have atleast one child facet$")
    public void userVerifiesParentFacetAreDisplayedWihMoreChildFacets(Map<String, String> map) throws Throwable {
        for (String key : map.keySet()) {
            assertFalse(knowHowSearchResultsPage.isParentHasAtLeastOneChildFacet(key, map.get(key).split("_")));
        }
    }

    @Then("^User verifies that no facets are displayed$")
    public void userVerifiesNoFacetsAreDisplayed() throws Throwable {
        assertTrue(knowHowSearchResultsPage.isNoFacetsAreAvailable());
    }

    @Then("^User verifies that  Resource type Facet Type are displayed in the order$")
    public void resourceTypeFacetsAreDisplayedInTheOrder(List<String> list) {
        List<String> expectedParentFacets = new ArrayList<String>();
        List<String> childFacets = null;
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<String, List<String>>();
        String parentFacet;
        String[] facets;

        /**
         * Retrieving the test data from feature file and arranging int list and
         * map as parent and child facets map
         */
        for (String parent : list) {
            childFacets = new ArrayList<String>();
            facets = parent.split("_");
            parentFacet = facets[0];

            for (int i = 1; i < facets.length; i++) {
                childFacets.add(facets[i]);
            }

            expectedParentFacets.add(parentFacet);
            map.put(parentFacet, childFacets);
        }

        /**
         * Getting the parent facets from the application
         */
        List<String> actualParentFacets = knowHowSearchResultsPage.getFacetNamesUnderFacetType("Resource Type", null);

        assertTrue(actualParentFacets.size() > 0);
        assertTrue(expectedParentFacets.size() == actualParentFacets.size());

        /**
         * Verifying the parent facets are in order
         */
        for (int i = 0; i < expectedParentFacets.size(); i++) {
            assertTrue(expectedParentFacets.get(i).trim().equals(actualParentFacets.get(i).trim()));
        }

        /**
         * Verifying the child facets under whose parent has child facets
         */
        for (String expectedParentFacet : expectedParentFacets) {
            childFacets = map.get(expectedParentFacet);
            if (childFacets.size() > 0) {
                /**
                 * Getting the childfacets from the application
                 */
                List<String> childActualFacets = knowHowSearchResultsPage.getFacetNamesUnderFacetType("Resource Type", expectedParentFacet);

                assertTrue(childActualFacets.size() > 0);
                assertTrue(childFacets.size() == childActualFacets.size());

                for (int i = 0; i < childFacets.size(); i++) {
                    assertTrue(childFacets.get(i).trim().equals(childActualFacets.get(i).trim()));
                }
            }
        }
    }

    @Then("^User verifies that Country Q&A is displayed under Resource type Facet Type$")
    public void userVerifiesResourctTypeFacetDisplayed() {
        assertTrue(knowHowSearchResultsPage.isFacetDisplayed("Resource Type", "Country Q&A"));
    }
}
