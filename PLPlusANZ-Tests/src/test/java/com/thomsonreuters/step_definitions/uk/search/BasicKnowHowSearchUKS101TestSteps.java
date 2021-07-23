package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.pages.landing_page.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.landing_page.PracticalLawUKCategoryPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.WhatsMarketSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class BasicKnowHowSearchUKS101TestSteps extends BaseStepDef {

    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private PracticalLawUKCategoryPage practicalLawUKCategoryPage = new PracticalLawUKCategoryPage();
    private PageActions pageActions = new PageActions();
    private WhatsMarketSearchResultsPage whatsMarketSearchResultsPage = new WhatsMarketSearchResultsPage();
    private FacetJavaTest1 facetJava = new FacetJavaTest1();


    @Given("^has selected the link to United Kingdom$")
    public void hasSelectedTheLinkToUnitedKingdom() throws Throwable {
        practicalLawHomepage.unitedKingdomContentLink().click();
    }

    @Given("^has selected the link to Resources$")
    public void hasSelectedTheLinkToResources() throws Throwable {
        practicalLawHomepage.resourcesLink().click();
    }

    @When("^the user runs a free text search for the query \"(.*)\"$")
    public void theUserRunsAFreeTextSearchForTheQuery(String query) throws Throwable {
        // Ensure the search button has rendered
        practicalLawUKCategoryPage.searchButton().isDisplayed();
        practicalLawUKCategoryPage.freeTextField().clear();

        //Paste the clipboard text if the query contains brackets or ampersand
        if (query.contains("(")) {
            practicalLawUKCategoryPage.freeTextField().sendKeys(query.replace("(", Keys.chord(Keys.SHIFT, "9")));
        } else {
            practicalLawUKCategoryPage.freeTextField().sendKeys(query);
        }

        if (getDriver().getClass().equals(ChromeDriver.class)) {
            pageActions.keyPress(Keys.ENTER);
        } else {
            practicalLawUKCategoryPage.searchButton().click();
        }

        // Wait for the results list to display
        theUserVerifiesThatTheResultsListPageIsDisplayed();
        LOG.info("The user has run a free text search for the query " + query);

    }

    @When("^the user verifies that the results list page is displayed$")
    public void theUserVerifiesThatTheResultsListPageIsDisplayed() throws Throwable {
        //Waiting for page to refresh
        searchResultsPage.waitForPageToLoad();
        try {
            searchResultsPage.resultsListHeader().isDisplayed();
            searchResultsPage.filterHeader().isDisplayed();
        } catch (Exception e) {
            LOG.error("The result page isn't displayed", e);
        }
        LOG.info("The user has verified that the results list page is displayed");
    }

    @Then("^the user is able to verify the presence of the title of the first result$")
    public void theUserIsAbleToVerifyThePresenceOfTheTitleOfTheFirstResult() throws Throwable {
        searchResultsPage.firstResultTitle().isDisplayed();
    }

    @Then("^the user is able to verify the presence of a resource type description$")
    public void theUserIsAbleToVerifyThePresenceOfAResourceTypeDescription() throws Throwable {
        searchResultsPage.resourceTypeDescription().isDisplayed();
    }

    @Then("^the user is able to verify a brief description of the content$")
    public void theUserIsAbleToVerifyABriefDescriptionOfTheContent() throws Throwable {
        assertTrue(searchResultsPage.firstResultAbstractText().isDisplayed());
    }

    @Then("^the user is able to verify that jurisdiction information is displayed$")
    public void theUserIsAbleToVerifyThatJurisdictionInformationIsDisplayed() throws Throwable {
        assertTrue(searchResultsPage.jurisdictionsForFirstResult().isDisplayed());
    }

    @Then("^the user is able to verify that the content is either maintained or non maintained$")
    public void theUserIsAbleToVerifyThatTheContentIsEitherMaintainedOrNonMaintained() throws Throwable {
        String statusText = searchResultsPage.statusForFirstResult().getText();
        assertTrue(statusText.contains("Maintained") || statusText.contains("Published") || statusText.contains("Law"));
    }

    @Then("^the user is able to verify the presence of search results$")
    public void verifyTheResultsAreDisplayed() {
        assertTrue(searchResultsPage.getAllResultsTitle().size() > 0);
    }

    @Then("^the user is able to check whether the option to apply filters is displayed and  if not to ensure that it is$")
    public void theUserIsAbleToCheckWhetherTheOptionToApplyFiltersIsDisplayedAndIfNotToEnsureThatItIs() {
        assertTrue("Select multiple filters button was not displayed", knowHowSearchResultsPage.isSelectMultipleFiltersPresent());
    }

    @Then("^the user is presented with a message confirming that the user needs a whats market subscription to view the results$")
    public void theUserIsPresentedWithAMessageConfirmingThatTheUserNeedsAWhatsMarketSubscriptionToViewTheResults() throws Throwable {
        whatsMarketSearchResultsPage.subscriptionErrorMessage().isDisplayed();
    }


    @Then("^the user can select the filter mode cancel option$")
    public void theUserCanSelectTheFilterModeCancelOption() throws Throwable {
        searchResultsPage.cancelFilters().click();
    }

    @Then("^the user can verify the presence of the option entitled select multiple filters$")
    public void theUserCanVerifyThePresenceOfTheOptionEntitledSelectMultipleFilters() throws Throwable {
        searchResultsPage.selectMultipleFilters().isDisplayed();
    }


    @When("^the user is on a know how search results page following a search for the term \"(.*?)\" and has stored the result count$")
    public void theUserIsOnAKnowHowSearchResultsPageFollowingASearchForTheTermAndHasStoredTheResultCount(String query) throws Throwable {
        theUserRunsAFreeTextSearchForTheQuery(query);
        facetJava.theUserGetsTheKnowHowSearchResultCountAndStoresItAsCount(1);

    }

    @Then("^the user verifies that the facet is instantly applied and the search result count updated accordingly$")
    public void theUserVerifiesThatTheFacetIsInstantlyAppliedAndTheSearchResultCountUpdatedAccordingly() throws Throwable {
        searchResultsPage.facetCheckbox("Family").isSelected();
        facetJava.theUserGetsTheCasesSearchResultCountAndStoresItAsCount(2);
        facetJava.theUserVerifiesThatTheKnowHowSearchResultCountIsLessThan(2, 1);

    }

    @When("^the user clicks on the first link in results$")
    public void theUserClicksOnTheFirstLinkInResults() {
        searchResultsPage.firstResultTitle().click();
        searchResultsPage.waitForPageToLoad();
    }

}