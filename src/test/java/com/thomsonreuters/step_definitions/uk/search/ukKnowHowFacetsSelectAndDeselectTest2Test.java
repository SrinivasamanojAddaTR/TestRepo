package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;

public class UKKnowHowFacetsSelectAndDeselectTest2Test {

    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();

    @When("^the user verifies that the know how parent facet \"(.*?)\" is not selected$")
    public void theUserVerifiesThatTheKnowHowParentFacetIsNotSelected(String arg1) throws Throwable {
        assertFalse(knowHowSearchResultsPage.knowHowFacetCheckbox(arg1).isSelected());
    }

    @When("^the user verifies that the know how child facet \"(.*?)\" is not selected$")
    public void theUserVerifiesThatTheKnowHowChildFacetIsNotSelected(String arg1) throws Throwable {
        assertFalse(knowHowSearchResultsPage.knowHowFacetCheckbox(arg1).isSelected());
    }

    @When("^the user verifies that the know how grandchild facet \"(.*?)\" is not selected$")
    public void theUserVerifiesThatTheKnowHowGrandchildFacetIsNotSelected(String arg1) throws Throwable {
        assertFalse(knowHowSearchResultsPage.knowHowFacetCheckbox(arg1).isSelected());
    }

    @When("^the user selects the know how child facet \"(.*?)\"$")
    public void theUserSelectsTheKnowHowChildFacet(String arg1) throws Throwable {
        knowHowSearchResultsPage.knowHowFacetCheckbox(arg1).click();
    }

}
