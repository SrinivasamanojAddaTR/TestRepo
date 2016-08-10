package com.thomsonreuters.step_definitions.search;

import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class highlightSearch extends BaseStepDef {
	
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();

	@Then("^for each search result \"(.*?)\" the matching search term is highlighted \"(.*)\" in snippet$")
	public void forEachSearchResultTheMatchingSearchTermIsHighlightedInSnippet(String rank,List<String> searchTerms) throws Throwable {
		verifyHighlightedSearchTerms(searchTerms, searchResultsPage.highlightedSearchTermsInFirstSnippet(rank));
	}
	
	@Then("^for search result the matching search term is highlighted \"(.*)\" in folders search results$")
	public void forEachSearchResultTheMatchingSearchTermIsHighlightedInFoldersSearch(List<String> searchTerms) throws Throwable {
		verifyHighlightedSearchTerms(searchTerms, researchOrganizerPage.totalSelectedSearchTerm());
	}
	
	public void verifyHighlightedSearchTerms(List<String> searchTerms, List<WebElement> highlightedTermElements){
		List<String> highlightedSearchTerms = new ArrayList<String>();
		SoftAssertions softly = new SoftAssertions();
		boolean isHighlightedTermContainsSearchTerm = false;
		for(WebElement element : highlightedTermElements) {
			highlightedSearchTerms.add(element.getText());
		}
		for(String highlightedTerm : highlightedSearchTerms) {
			isHighlightedTermContainsSearchTerm = false;
			Iterator<String> itr = searchTerms.iterator();
			while(itr.hasNext()) {
				String term = itr.next();
				if(highlightedTerm.toLowerCase().contains(term.toLowerCase())){
					isHighlightedTermContainsSearchTerm = true;
				}
			} softly.assertThat(isHighlightedTermContainsSearchTerm).isTrue().overridingErrorMessage("Highlighted Search term <%s> does not contain search term", highlightedTerm);
		}
		softly.assertAll();
		}
	}