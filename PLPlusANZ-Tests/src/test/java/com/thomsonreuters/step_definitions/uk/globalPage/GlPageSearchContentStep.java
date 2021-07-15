package com.thomsonreuters.step_definitions.uk.globalPage;

import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.globalPage.GlobalCategoryPage;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawUKCategoryPage;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowDocumentPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.MorePopUpPage;
import com.thomsonreuters.pageobjects.pages.search.WhatsMarketSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.globalPage.GlobalPageUtils;
import com.thomsonreuters.step_definitions.ResponsiveCommonSteps;
import com.thomsonreuters.step_definitions.uk.search.UKKnowHowSelectDeselectSingleFacetTest1;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GlPageSearchContentStep {

	private static final String PATH_TO_GLOBAL_PAGE = "/Browse/Home/Global";

	protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GlPageSearchContentStep.class);

	private NavigationCobalt navigation = new NavigationCobalt();
	private GlobalCategoryPage globalCategoryPage = new GlobalCategoryPage();
	private GlobalPageUtils globalPageUtils = new GlobalPageUtils();
	private KnowHowDocumentPage knowHowDocumentPage = new KnowHowDocumentPage();
	private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
	private HomePage homePage = new HomePage();
	private WhatsMarketSearchResultsPage whatsMarketSearchResultsPage = new WhatsMarketSearchResultsPage();
	private UKKnowHowSelectDeselectSingleFacetTest1 selectDeselectSingleFacetTest = new UKKnowHowSelectDeselectSingleFacetTest1();
	private PracticalLawUKCategoryPage practicalLawUKCategoryPage = new PracticalLawUKCategoryPage();
	private MorePopUpPage morePopUpPage = new MorePopUpPage();
	private ResponsiveCommonSteps responsiveCommonSteps = new ResponsiveCommonSteps();

	@When("^the user navigates to the Global Page$")
	public void theUserNavigatesToTheGlobalPage() throws Throwable {
		navigation.navigateToPLUKPlus(PATH_TO_GLOBAL_PAGE);
		globalCategoryPage.waitForPageToLoad();
	}

	@Then("^the Global Page opens correctly$")
	public void theGlobalPageOpensCorrectly() throws Throwable {
		theCategoryPageOpensCorrectly();
	}

	@Then("^the document coded to \"(.*?)\" Practice Area$")
	public void theDocumentCodedToPracticeArea(String practiceArea) throws Throwable {
		assertTrue("The document doesn't coded to " + practiceArea + " Practice Area ", knowHowDocumentPage
				.knowHowProductCodes(practiceArea).isDisplayed());
	}

	@When("^the user selects \"(.*?)\" tab and clicks on \"(.*?)\" link$")
	public void theUserSelectsTabAndClicksOnLink(String tab, String country) throws Throwable {
		globalCategoryPage.waitForPageToLoad();
		homePage.specificTab(tab).click();
		globalCategoryPage.countryInCoutriesTab(country).click();
	}

	@Then("^the user sees '(\\d+)-(\\d+)' search result count$")
	public void theUserSeesSearchResultCount(int start, int end) throws Throwable {
		assertTrue(
				"User doesn't see search result content in " + start + "-" + end + "format",
				knowHowSearchResultsPage.searchResultCountLabel().isDisplayed()
						&& knowHowSearchResultsPage.searchResultCountLabel().getText()
								.equals(Integer.toString(start) + " - " + Integer.toString(end)));
	}

	@Then("^the jurisdiction section contains the following countries$")
	public void theJurisdictionSectionContainsTheFollowingCountries(List<String> facets) throws Throwable {
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(globalCategoryPage.jurisdictionsInTheLeftHandSide().size() == facets.size())
				.overridingErrorMessage("Size of list of countries is not right").isTrue();
		for (int i = 0; i < globalCategoryPage.jurisdictionsInTheLeftHandSide().size(); i++) {
			softly.assertThat(
					globalCategoryPage.jurisdictionsInTheLeftHandSide().get(i).getText().equals(facets.get(i)))
					.overridingErrorMessage(
							globalCategoryPage.jurisdictionsInTheLeftHandSide().get(i).getText() + " not equals to "
									+ facets.get(i)).isTrue();
			softly.assertThat(globalCategoryPage.jurisdictionsInTheLeftHandSide().get(i).isDisplayed()).isTrue()
					.overridingErrorMessage(
							globalCategoryPage.jurisdictionsInTheLeftHandSide().get(i) + " is not displayed");
		}
		softly.assertAll();
	}

	@When("^the user verifies that the following facet is selected$")
	public void theUserVerifiesThatTheKnowHowFollowingFacetIsSelected(List<String> facets) throws Throwable {
		for (String facet : facets) {
			boolean result = false;
			try {
				result = knowHowSearchResultsPage.knowHowFacetCheckbox(facet).isSelected();
			} catch (Exception e) {
				LOG.info("Element Know how facet checkbox was not found", e);
			}
			assertTrue(result);
		}
	}

	@Then("^the jurisdiction and resource type facets are displayed$")
	public void theJurisdictionAndResourceTypeFacetsAreDisplayed(List<String> facets) throws Throwable {
		SoftAssertions softly = new SoftAssertions();
		for (int i = 0; i < whatsMarketSearchResultsPage.getFacetGroupNames().size(); i++) {
			softly.assertThat(whatsMarketSearchResultsPage.getFacetGroupNames().get(i).equals(facets.get(i)))
					.overridingErrorMessage(
							whatsMarketSearchResultsPage.getFacetGroupNames().get(i) + " is not equals to "
									+ facets.get(i)).isTrue();
		}

		softly.assertAll();
	}

	@When("^the user selects the \"(.*?)\" facet \"(.*?)\"$")
	public void theUserSelectsTheFacet(String facetType, String facet) throws Throwable {
		globalCategoryPage.checkBox(facetType, facet).click();
	}

	@When("^the user apply filters$")
	public void theUserApplyFilters() throws Throwable {
		globalCategoryPage.waitForPageToLoadAndJQueryProcessing();
		knowHowSearchResultsPage.clickOnSelectMultipleFilters();
		selectDeselectSingleFacetTest.theUserSelectsTheKnowHowOptionToApplyFilters();
	}

	@Then("^the results is refined to only include that \"(.*?)\" jurisdiction$")
	public void theResultsIsRefinedToOnlyIncludeThatJurisdiction(String jurisdiction) throws Throwable {
		SoftAssertions softly = new SoftAssertions();
		for (int i = 0; i < globalCategoryPage.jurisdictionsInTheReturnedSearchResults().size(); i++) {
			softly.assertThat(
					globalCategoryPage.jurisdictionsInTheReturnedSearchResults().get(i).getText()
							.contains(jurisdiction))
					.overridingErrorMessage(
							globalCategoryPage.jurisdictionsInTheReturnedSearchResults().get(i).getText()
									+ " does not contain " + jurisdiction).isTrue();
		}

		softly.assertAll();
	}

	@Then("^the results is refined to only include that \"(.*?)\" resource type$")
	public void theResultsIsRefinedToOnlyIncludeThatResourceType(String resourceType) throws Throwable {
		SoftAssertions softly = new SoftAssertions();
		for (int i = 0; i < globalCategoryPage.resourceTypes().size(); i++) {
			softly.assertThat(globalCategoryPage.resourceTypes().get(i).getText().contains(resourceType))
					.overridingErrorMessage(
							globalCategoryPage.resourceTypes().get(i).getText() + " does not contain " + resourceType)
					.isTrue();
		}
		softly.assertAll();
	}

	@When("^the user enter \"(.*?)\" on the search field$")
	public void theUserEnterOnTheSearchField(String character) throws Throwable {
		practicalLawUKCategoryPage.freeTextField().sendKeys(character);
	}

	@Then("^the list of suggested terms does not appear$")
	public void theListOfSuggestedTermsDoesNotAppear() throws Throwable {
		List<String> actualSuggestions =  knowHowSearchResultsPage.getSearchSuggestions();
		assertTrue("The suggestions appear ",actualSuggestions.size() == 0);
	}

	@Then("^the returned results contain the search \"(.*?)\"$")
	public void theReturnedResultsContainTheSearch(String term) throws Throwable {
		globalCategoryPage.waitForPageToLoad();
		globalCategoryPage.waitForPageToLoadAndJQueryProcessing();
		responsiveCommonSteps.theUserSelectsThePerPagefromPerPageDropdown("20");
		SoftAssertions softly = new SoftAssertions();
		List<String>searchResults = globalPageUtils.getLinkNamesFromWebElementList(globalCategoryPage.returnedSearchResults());
		for (String result : searchResults) {
			softly.assertThat(result.toLowerCase().contains(term))
					.overridingErrorMessage("%s does not contain tax ", result).isTrue();
		}
		softly.assertAll();
	}

	@Then("^the following list of jurisdictions is displayed$")
	public void theFollowingListOfJurisdictionsIsDisplayed(List<String> facets) throws Throwable {
		theJurisdictionSectionContainsTheFollowingCountries(facets);
	}

	@When("^the user selects the following facets in more jurisdictions in popup box in global search result page$")
	public void theUserSelectsTheFollowingFacetsInMoreJurisdictionsInPopupBoxInGlobalSearchResultPage(
			List<String> facets) throws Throwable {
		if (!knowHowSearchResultsPage.isCancelButtonExists()) { // Filter state can be stored after previous sessions
            knowHowSearchResultsPage.selectMultipleFilters().click();
        }
		globalPageUtils.clickMoreOptionOnKnowHowGlobalJurisdiction();
		morePopUpPage.selectSearchItem(facets);
		globalCategoryPage.continueButton().click();
	}

	@Then("^the delivery widget is not displayed$")
	public void theDeliveryWidgetIsNotDisplayed() throws Throwable {
		assertFalse("The deliery widget is displayed", globalPageUtils.isTheDeliveryWidgetIsDisplayed());
	}
	
	@Then("^the Category Page opens correctly$")
	public void theCategoryPageOpensCorrectly() throws Throwable {
		globalCategoryPage.waitForPageToLoad();
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(!globalCategoryPage.header().isEmpty()).overridingErrorMessage("Header is empty").isTrue();
		softly.assertThat(!globalCategoryPage.globalPageHeader().isEmpty())
				.overridingErrorMessage("Page header is empty").isTrue();
		softly.assertThat(!globalCategoryPage.globalPageBody().isEmpty()).overridingErrorMessage("Page body is empty")
				.isTrue();
		softly.assertThat(!globalCategoryPage.globalPageFooter().isEmpty()).overridingErrorMessage("Footer is empty")
				.isTrue();
		softly.assertAll();
	}
}
