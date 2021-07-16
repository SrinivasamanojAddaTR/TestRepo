package com.thomsonreuters.step_definitions.breadcrumb;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;

import com.thomsonreuters.pageobjects.pages.landing_page.SearchScopeControl;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.TopicPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;

import cucumber.api.java.en.When;

import java.util.List;

public class breadcrumbSearchResultPage extends BaseStepDef {

    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private TopicPage topicPage = new TopicPage();
    private SearchScopeControl searchScopeControl = new SearchScopeControl();
    //private BreadCrumbPage breadcrumbPage = new BreadCrumbPage();

    @When("^the user selects facets$")
    public void theUserSelectsFacets(List<String> facets) throws Throwable {
        if (knowHowSearchResultsPage.isSelectMultipleFiltersPresent()) {
            knowHowSearchResultsPage.selectMultipleFilters().click();
        }
       // facets.forEach(facet -> knowHowSearchResultsPage.knowHowFacetCheckbox(facet).click());
        knowHowSearchResultsPage.applyFiltersButton().click();
        knowHowSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^user clicks on Page \"(.*?)\" of the Topic page results list to see breadcrumb$")
    public void userClicksOnPageOfTheTopicPageResultsListBreadcrumb(int pageNum) throws Throwable {
        topicPage.waitForPageToLoadAndJQueryProcessing();
        topicPage.waitForPageSourceChangedAfterClick(topicPage.pageNumber(pageNum));
        topicPage.waitForPageToLoadAndJQueryProcessing();
        //breadcrumbPage.waitForBreadcrumbToBeDisplayed();
        assertThat(Integer.parseInt(topicPage.currentPageSelected()), Is.is(pageNum));
    }

    @When("^user selects  dropdown option \"(.*?)\"$")
    public void userSelectsTheDropdownOption(String option) throws Throwable {
        searchScopeControl.scopedSearchDropdownOptions(option).click();
    }

    @When("^the user can display scoped search dropdown menu options$")
    public void theUserCanDisplayScopedSearchDropdownMenuOptions() throws Throwable {
        searchScopeControl.scopedSearchDropdownMenuButton().click();
    }
}

