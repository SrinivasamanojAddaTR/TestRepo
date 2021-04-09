package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.common.SortOptions;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawUKCategoryPage;
import com.thomsonreuters.pageobjects.pages.search.*;
import com.thomsonreuters.utils.TimeoutUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UKKnowHowSearchResultsPerPageTestSteps extends BaseStepDef {

    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private PracticalLawUKCategoryPage practicalLawUKCategoryPage = new PracticalLawUKCategoryPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private WhatsMarketSearchResultsPage resultsPage = new WhatsMarketSearchResultsPage();
    private MorePopUpPage morePopUpPage = new MorePopUpPage();
    private CommonMethods commonMethods = new CommonMethods();
    private CasesDocumentPage casesDocumentPage = new CasesDocumentPage();
    private PageActions pageActions = new PageActions();

    private List<String> actualSuggestions;

    @When("^the user selects the relevant dropdown$")
    public void theUserSelectsTheRelevantDropdown() {
    }

    @When("^the user selects the option to display \"([^\"]*)\" of results per page$")
    public void theUserSelectsTheOptionToDisplayOfResultsPerPage(String number) {
        practicalLawUKCategoryPage.resultsPerPageDropdown(number);
        knowHowSearchResultsPage.waitForSearchResults();
    }

    @When("^the user runs a free text cobalt search$")
    public void theUserRunsAFreeTextCobaltSearch() {
        practicalLawUKCategoryPage.freeTextField().clear();
        practicalLawUKCategoryPage.freeTextField().sendKeys();
        /**
         * TODO: Pressing enter as searchButton click is failing in Chrome browser. Revert the code once it is working fine Chrome.
         */
        if (getDriver().getClass().equals(ChromeDriver.class)) {
            pageActions.keyPress(Keys.ENTER);
        } else {
            practicalLawUKCategoryPage.searchButton().click();
        }
    }

    @When("^the user runs a free text cobalt search with query \"(.*?)\"$")
    public void theUserRunsAFreeTextCobaltSearch(String query) {
        practicalLawUKCategoryPage.freeTextField().clear();
        practicalLawUKCategoryPage.freeTextField().sendKeys(query);
        practicalLawUKCategoryPage.searchButton().click();
        knowHowSearchResultsPage.waitForSearchResults();
        if (knowHowSearchResultsPage.isSelectMultipleFiltersPresent()) {
            knowHowSearchResultsPage.clickOnSelectMultipleFilters();
        }
    }

    @Then("^the user is able to verify the presence of text confirming that results \"([^\"]*)\" are displayed on the page$")
    public void theUserIsAbleToVerifyThePresenceOfTextConfirmingThatResultsAreDisplayedOnThePage(String results) {
        WebElement heading = searchResultsPage.resultsPerPageText();
        assertTrue(heading.getText().contains(results));
    }

    @Then("^the user is able to verify the presence of whats market search result \"(.*?)\"$")
    public void theUserIsAbleToVerifyThePresenceOfWhatsMarketSearchResult(String rank) {
        searchResultsPage.resultNumberWM(rank).isDisplayed();
    }

    @Then("^the user is able to verify the presence of know how search result \"(.*?)\"$")
    public void theUserIsAbleToVerifyThePresenceOfKnowHowSearchResult(String rank) {
        searchResultsPage.resultNumberKH(rank).isDisplayed();
    }

    @Then("^user verifies the \"(.*?)\" is present in displayed results title$")
    public void theUserVerifiesResultInTitle(String title) {
        for (String contentTitle : searchResultsPage.getAllResultsTitle()) {
            assertTrue(contentTitle.toLowerCase().contains(title));
        }
    }

    @Then("^user verifies the \"(.*?)\" is present in displayed results description text$")
    public void theUserVerifiesResultInText(String title) {
        for (int i = 1; i <= 5; i++) {
            assertTrue(searchResultsPage.getResultItemDescription(i).toLowerCase().contains(title));
        }
    }

    @Then("the user is able to verify that the jurisdiction facets by default 5 facets are displayed")
    public void verifyTheJurisdictionFacetsLimit() {
        for (String facetGroupName : resultsPage.getFacetGroupNames()) {
            if (facetGroupName.equalsIgnoreCase("Jurisdiction")) {
                int childFacets = resultsPage.getChildFacetsSize(facetGroupName);
                assertFalse(childFacets > 5);
                if (childFacets < 5) {
                    assertFalse(knowHowSearchResultsPage.isMoreOptionAvailableForKnowHowJurisdiction());
                }
            }
        }
    }

    @When("^the user selects the following facets in more jurisdictions in popup box$")
    public void openMorePopUp(List<String> facets) {
        knowHowSearchResultsPage.clickMoreOptionOnKnowHowJurisdiction();
        morePopUpPage.selectSearchItem(facets);
        List<String> selectedFacets = morePopUpPage.getSelectedFacetNames();
        for (String facet : facets) {
            assertTrue(selectedFacets.contains(facet));
        }
        morePopUpPage.selectContinue();
    }

    @And("^the user selects the following facets in more jurisdictions in popup box and cancel changes$")
    public void cancelPopupChanges(List<String> facets) {
        knowHowSearchResultsPage.clickMoreOptionOnKnowHowJurisdiction();
        morePopUpPage.selectSearchItem(facets);
        List<String> selectedFacets = morePopUpPage.getSelectedFacetNames();
        for (String facet : facets) {
            assertTrue(selectedFacets.contains(facet));
        }
        morePopUpPage.selectCancelButton();

        knowHowSearchResultsPage.clickMoreOptionOnKnowHowJurisdiction();
        selectedFacets = morePopUpPage.getSelectedFacetNames();
        for (String facet : facets) {
            assertFalse(selectedFacets.contains(facet));
        }
        morePopUpPage.selectCancelButton();
    }

    @When("^the user verifies that the following facet is not selected$")
    public void theUserVerifiesThatTheKnowHowFollowingFacetIsSelected(List<String> facets) {
        for (String facet : facets) {
            boolean result = false;
            try {
                result = knowHowSearchResultsPage.knowHowFacetCheckbox(facet).isSelected();
            } catch (Exception e) {
                LOG.info("Element Know how facet checkbox was not found", e);
            }
            assertFalse(result);
        }
    }

    @Then("^the user should be presented with the below search suggestions$")
    public void theUserShouldBePresentedWithTheBelowSearchSuggestions(List<String> expectedSuggestions) {
        actualSuggestions = knowHowSearchResultsPage.getSearchSuggestions();
        for (String suggestion : expectedSuggestions) {
            assertTrue(actualSuggestions.contains(suggestion.toUpperCase()));
        }
    }

    @Then("^the suggested search terms are displayed in alphabetical order$")
    public void theUserShouldBePresentedWithTheSearchSuggestionsInAlphabeticalOrder() {
        assertTrue(commonMethods.isSorted(actualSuggestions, SortOptions.ASC));
    }

    @Then("^the user should not be presented with the below search suggestions$")
    public void theUserShouldNotBePresentedWithTheBelowSearchSuggestions(List<String> expectedSuggestions) {
        actualSuggestions = knowHowSearchResultsPage.getSearchSuggestions();
        for (String suggestion : expectedSuggestions) {
            assertFalse(actualSuggestions.contains(suggestion.toUpperCase()));
        }
    }

    @And("^the user closes the pop up$")
    public void theUserClosesThePopUp() {
        casesDocumentPage.viewDocumentLink().click();
    }


    @And("^the user verifies the presence of page number \"(.*?)\"$")
    public void theUserVerifiesThePresenceOfPageNumber(String arg1) {
        searchResultsPage.currentSelectedPage(arg1).isDisplayed();
    }

    @And("^the user verifies that page \"(.*?)\" is selected$")
    public void theUserVerifiesThatPageIsSelected(String arg1) {
        searchResultsPage.currentSelectedPage(arg1).isDisplayed();
    }

    @And("^the user verifies the presence of a next page navigation arrow$")
    public void theUserVerifiesThePresenceOfANextPageNavigationArrow() {
        searchResultsPage.nextPageNavigationArrow().isDisplayed();
    }

    @And("^the user verifies the presence of a last page navigation arrow$")
    public void theUserVerifiesThePresenceOfALastPageNavigationArrow() {
        searchResultsPage.lastPageNavigationArrow().isDisplayed();
    }


    @And("^the user verifies the presence of a previous page navigation arrow$")
    public void theUserVerifiesThePresenceOfAPreviousPageNavigationArrow() {
        searchResultsPage.previousPageNavigationArrow().isDisplayed();
    }

    @And("^the user verifies the presence of a first page navigation arrow$")
    public void theUserVerifiesThePresenceOfAFirstPageNavigationArrow() {
        searchResultsPage.firstPageNavigationArrow().isDisplayed();
    }

    @And("^the user selects page number \"(.*?)\"$")
    public void theUserSelectsPageNumber(String pageToClick) {
        TimeoutUtils.sleepInSeconds(5);
        searchResultsPage.nonSelectedPage(pageToClick).click();
    }
}




