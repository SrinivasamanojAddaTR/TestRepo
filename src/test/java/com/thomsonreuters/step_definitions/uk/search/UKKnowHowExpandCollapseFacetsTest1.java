package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;

/**
 * Created by Steph Armytage on 11/02/2015.
 */
public class UKKnowHowExpandCollapseFacetsTest1 {

    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();

    @When("^the user expands the know how facet \"(.*?)\"$")
    public void theUserExpandsTheKnowHowFacet(String facetName) throws Throwable {
        knowHowSearchResultsPage.expandCollapsedFacet(facetName).click();
    }


    @And("^the user can verify the presence of a child topic entitled \"(.*?)\"$")
    public void theUserCanVerifyThePresenceOfAChildTopicEntitled(String arg1) throws Throwable {
        knowHowSearchResultsPage.facetName(arg1).isDisplayed();
    }

    @When("^the user collapses the know how facet \"(.*?)\"$")
    public void theUserCollapsesTheKnowHowFacet(String arg1) throws Throwable {
        knowHowSearchResultsPage.collapseFacet(arg1).click();
    }

    @And("^the user can verify that the topic is no longer displayed \"(.*?)\"$")
    public void theUserCanVerifyThatTheTopicIsNoLongerDisplayed(String arg1) throws Throwable {
        assertFalse(knowHowSearchResultsPage.facetName(arg1).isDisplayed());
    }
}
