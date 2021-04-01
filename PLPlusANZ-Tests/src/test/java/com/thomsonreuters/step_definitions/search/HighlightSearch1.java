package com.thomsonreuters.step_definitions.search;

import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
import cucumber.api.java.en.Then;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class HighlightSearch1 extends BaseStepDef {

    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private SearchUtils searchUtils = new SearchUtils();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();

    @Then("^for each search result \"(.*?)\" the matching search term is highlighted \"(.*)\" in snippet$")
    public void forEachSearchResultTheMatchingSearchTermIsHighlightedInSnippet(String rank, List<String> searchTerms) throws Throwable {
        assertTrue("Highlighted Search term does not contain search term", searchUtils.verifyHighlightedSearchTerms(searchTerms, searchResultsPage.highlightedSearchTermsInFirstSnippet(rank)));
    }

    @Then("^for search result the matching search term is highlighted \"(.*)\" in folders search results$")
    public void forEachSearchResultTheMatchingSearchTermIsHighlightedInFoldersSearch(List<String> searchTerms) throws Throwable {
        assertTrue("Highlighted Search term does not contain search term", searchUtils.verifyHighlightedSearchTerms(searchTerms, researchOrganizerPage.totalSelectedSearchTerm()));
    }
}