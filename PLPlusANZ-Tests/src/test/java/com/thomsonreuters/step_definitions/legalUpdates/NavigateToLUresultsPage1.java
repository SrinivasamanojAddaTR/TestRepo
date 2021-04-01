package com.thomsonreuters.step_definitions.legalUpdates;

import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class NavigateToLUresultsPage1 extends BaseStepDef {

	private HomePage homepage = new HomePage();
	private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();

	@When("^the user select Legal Updates link on Resource tab area$")
	public void aUserIsOnALegalUpdatesResultsPage() throws Throwable {
		homepage.selectResourceTab();
		homepage.legalUpdatesContentLink().click();
		homepage.waitForPageToLoadAndJQueryProcessing();
	}
	
	@Then("^list of practice areas will be displayed$")
	public void listOfPAisDisplayed(List<String> practiceAreas) throws Throwable {
		LOG.info("List of expected practice areas " + practiceAreas);
		LOG.info("List of actual practice areas " + homepage.getPracticeAreasLinks());
		assertTrue("The list of PA is not displayed",homepage.getPracticeAreasLinks().containsAll(practiceAreas));
	}

    @When("^the user verifies the presence of the legul updates facet groups for PLAU$")
    public void theUserVerifiesThePresenceOfTheKnowHowFacetGroups() throws Throwable {
        assertTrue("The practice area facets are not displayed", knowHowSearchResultsPage.facetGroupHeaderPracticeArea().isDisplayed());
        assertTrue("The jurisdiction facets are not displayed", knowHowSearchResultsPage.facetGroupHeaderPLAUJurisdiction().isDisplayed());
		assertTrue("The resource type facets are displayed ",knowHowSearchResultsPage.isFacetGroupHeaderResourceTypeDisplayed());
    }
    
    @When("^the user verifies that facets not selected$")
    public void theUserVerifiesfacetsNotSelected() throws Throwable {
    	SoftAssertions softly = new SoftAssertions();
		for (WebElement checkbox : knowHowSearchResultsPage.searchFilterFacetCheckbox()) {
			softly.assertThat(checkbox.isSelected()).overridingErrorMessage("checkbox is selected").isFalse();
		}
		softly.assertAll();
    }
}
