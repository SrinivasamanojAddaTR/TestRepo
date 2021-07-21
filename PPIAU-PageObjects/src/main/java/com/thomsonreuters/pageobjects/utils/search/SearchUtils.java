package com.thomsonreuters.pageobjects.utils.search;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.Link;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.DraftingNotes;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.KHResourcePage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document_navigation.DocumentNavigationPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_researchsearch.BaseResultsPage;
import com.thomsonreuters.pageobjects.pages.search.*;
import com.thomsonreuters.pageobjects.rest.LinkingBaseUtils;
import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.LegislationDocument;
import com.thomsonreuters.pageobjects.utils.document.ResourceType;
import com.thomsonreuters.pageobjects.utils.legal_updates.CalendarAndDate;
import com.thomsonreuters.utils.TimeoutUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by Pavel_Ardenka on 10.11.2015.
 * Utils for working with search results
 */

public class SearchUtils {

    private static final String GLOSSARY_SEARCH_HIGHLIGHT_COLOR = "rgba(249, 249, 193, 1)";
    private static final Logger LOG = LoggerFactory.getLogger(SearchUtils.class);
    private static final String DOC_VERSION_PLACEHOLDER_START_DATE = "{startDate}";
    private static final String DOC_VERSION_PLACEHOLDER_END_DATE = "{endDate}";
    private static final String DOC_VERSION_PLACEHOLDER_VERSION = "{version}";
    private static final String DOC_VERSION_PLACEHOLDER_VERSIONS = "{versions}";
    private static final String DOC_VERSION_PLACEHOLDER_NO_TEXT = "{noText}";
    private static final String DOC_VERSION_DATE_FORMAT = "d MMMM yyyy"; // e.g. 2 April 2016
    private static final String SEARCH_QUERY_PARAM_NAME = "query";
    private WLUKSearchResultsPage wlukSearchResultsPage = new WLUKSearchResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private KHResourcePage resourcePage = new KHResourcePage();
    private KnowHowDocumentPage knowHowDocumentPage = new KnowHowDocumentPage();
    private WhatsMarketDocumentPage whatsMarketDocumentPage = new WhatsMarketDocumentPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private SearchHomePage searchHomePage = new SearchHomePage();
    private DocumentNavigationPage documentNavigationPage = new DocumentNavigationPage();
    private LinkingBaseUtils linkingUtils = new LinkingBaseUtils();
    private CommonMethods commonMethods = new CommonMethods();

    private String focusedTermId = "";

    /**
     * Check that every result contains one string from text. Method will check the part of word (without last two letters) because
     * word form can be another (e.g., user searches "company", but result contains "companies" word).
     * Method checks results with case ignoring
     *
     * @param resultItems List with result elements {@link BaseResultsPage#getResultListWithFullText()}.
     * @param text        List with one of expected strings which should be exists in every result
     * @return True if check passed. Otherwise - false.
     */
    public boolean isEveryResultContains(List<WebElement> resultItems, List<String> text) {
        for (WebElement oneResult : resultItems) {
            if (!isWordFoundIn(oneResult, text)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get search results count as int
     *
     * @return Search results count
     */
    public int getSearchResultsCountAsInt() {
        return commonMethods.getIntFromString(knowHowSearchResultsPage.waitKnowHowSearchResultCount().getText());
    }

    public void applySearchFilters() {
        if (wlukSearchResultsPage.isMultipleFiltersToggleSelected()) {
            wlukSearchResultsPage.applyFLUKFiltersButton().click();
            wlukSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
        } else {
            LOG.info("Multiple selection mode wasn't active - facets weren't applied");
        }
    }

    /**
     * Check that user query in result element is highlighted. Method check that all <span> elements in result element contain search query
     * and are highlighted using css-style.
     * Method checks results with case ignoring
     *
     * @param element One of result elements {@link com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.GlossaryPage#glossaryTermsWithSearchTermList()}.
     * @param term    String with user query which should be highlighted in every result
     * @return True if check passed. Otherwise - false.
     */
    public boolean isSearchWordHighlightedInGlossaryPage(WebElement element, String term) {
        for (WebElement spanElement : element.findElements(By.xpath("./span"))) {
            if ((!spanElement.getText().trim().toLowerCase().contains(term)) && (!GLOSSARY_SEARCH_HIGHLIGHT_COLOR.equals(spanElement.getCssValue("background-color"))))
                return false;
        }
        return true;
    }
    public void chooseSingleOrMultipleFacetSelectionMode(FacetSelectionMode mode) {
        if (mode.equals(FacetSelectionMode.SINGLE) && wlukSearchResultsPage.isMultipleFiltersToggleSelected() ||
                mode.equals(FacetSelectionMode.MULTIPLE) && !wlukSearchResultsPage.isMultipleFiltersToggleSelected()) {
            wlukSearchResultsPage.clickMultipleFiltersToggle();
            LOG.info("Changing WLUK facet selection mode to {}", mode);
        } else {
            LOG.info("WLUK facet selection mode is {} as required", mode);
        }
    }
    /**
     * Get collection of document GUIDs from the search results links
     *
     * @return Collection of the Strings with documents GUIDS.
     */
    public List<String> getGuidsFromSearchResults() {
        List<String> result = new ArrayList<>();
        List<WebElement> resultsLinks = searchResultsPage.getDocGuidLinksFromResults();
        for (WebElement resultLink : resultsLinks) {
            result.add(resultLink.getAttribute("docguid"));
        }
        return result;
    }

    /**
     * Verify that search results sorted by date.
     *
     * @param guids        Given guids of documents
     * @param resourceType Resource type for document collection to get proper sorting date.
     *                     Use {@link ResourceType#GENERIC} for combined results but please note, that in this case you
     *                     may get non-relevant results
     * @return True - if check passed. False - otherwise.
     */
    public boolean areTheSearchResultsSortedByDate(List<String> guids, ResourceType resourceType) {
        List<Document> documents = linkingUtils.getDocumentsFromNovus(guids, resourceType);
        int documentsCount = documents.size();
        for (int i = 0; i < documentsCount; i++) {
            Date prevDate = documents.get(i).getSortingDate();
            Date nextDate = (i + 1 < documentsCount) ? documents.get(i + 1).getSortingDate() : new Date(Long.MIN_VALUE);
            LOG.info("Document {} {}, date {}, resource type {}",
                    (i + 1), documents.get(i).getFullTitle(), documents.get(i).getSortingDate(), documents.get(i).getResourceType());
            if (prevDate.compareTo(nextDate) < 0) {
                LOG.info("Document {} '{}' is out of order by Date. Previous date {}, next date {}",
                        (i + 1), documents.get(i).getFullTitle(), prevDate, nextDate);
                return false;
            }
        }
        return true;
    }

    /**
     * Verify that search results sorted by date for combined search results
     *
     * @param guids Given guids of documents
     * @return True - if check passed. False - otherwise.
     */
    public boolean areTheSearchResultsSortedByDate(List<String> guids) {
        return areTheSearchResultsSortedByDate(guids, ResourceType.GENERIC);
    }

    /**
     * Perform the search operation (enter search term to search field and click Search button)
     *
     * @param searchQuery Search term
     */
    public void searchFor(String searchQuery) {
        enterSearchText(searchQuery);
        searchHomePage.searchButton().sendKeys(Keys.ENTER);
        waitSearchResultsToLoad();
    }

    /**
     * Enter text to the search field.
     *
     * @param searchTerm Search term
     */
    public void enterSearchText(String searchTerm) {
        WebElement search = searchHomePage.searchTextBox();
        eraseField(search);
        if (SeleniumVersion.isThird()) {
            search.sendKeys(searchTerm);
        } else {
            // Workaround for bug in the firefox when text before left parenthesis is not entering
            search.sendKeys(searchTerm.replace("(", Keys.chord(Keys.SHIFT, "9")));
        }
    }

    /**
     * Erase the content of search field
     */
    public void eraseSearchField() {
        eraseField(searchHomePage.searchTextBox());
    }

    /**
     * Check if every search result contains snippet with highlighted searched term
     * WARNING: there is necessary to select 'Most detail' in the detail selector to proper work of this method
     *
     * @return True - if every result has highlighted term. False - otherwise.
     */
    public boolean isEverySearchResultHasSnippetWithHighlightedTerm() {
        int resultsCount = searchResultsPage.getAllSearchTitleLinks().size();
        if (resultsCount == 0) {
            return false;
        }
        for (int i = 1; i <= resultsCount; i++) {
            try {
                if (searchResultsPage.highlightedSearchTermsInFirstSnippet(String.valueOf(i)).isEmpty()) {
                    return false;
                }
            } catch (TimeoutException e) {
                LOG.info("Unable to find highlighted terms for result number " + i + ". Please, make sure that " +
                        "you have selected 'Most detail' in the detail selector", e);
                return false;
            }
        }
        return true;
    }

    public boolean isSearchTermsPresentInParagraph(CommonDocumentPage.TermsInSequence termsInSequence, String firstTerm, String secondTerm) {
        if (commonMethods.isCurrentDocumentFromKnowHow()) {
            return knowHowDocumentPage.isSearchTermsPresentInParagraph(termsInSequence, firstTerm, secondTerm);
        } else {
            return whatsMarketDocumentPage.isSearchTermsPresentInParagraph(termsInSequence, firstTerm, secondTerm);
        }
    }

    public boolean isSearchTermsPresentInSentence(CommonDocumentPage.TermsInSequence termsInSequence, String firstTerm, String secondTerm) {
        return knowHowDocumentPage.isSearchTermsPresentInSentence(termsInSequence, firstTerm, secondTerm);
    }

    public boolean isSearchTermsPresentInParagraphWithInNumberOfWords(CommonDocumentPage.TermsInSequence termsInSequence, int noOfTerms, String firstTerm, String secondTerm) {
        if (commonMethods.isCurrentDocumentFromKnowHow()) {
            openDraftingNotesIfPresent();
            return knowHowDocumentPage.isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, noOfTerms, firstTerm, secondTerm);
        } else {
            return whatsMarketDocumentPage.isSearchTermsPresentInParagraphWithInNumberOfWords(termsInSequence, noOfTerms, firstTerm, secondTerm);
        }
    }

    /**
     * Check if current highlighted term was changed after navigating by term navigation type on searched document page
     *
     * @return True - if focused term was changed. False - otherwise.
     */
    public boolean isActiveHighlightedTermChanged() {
        String actualFocusedTermId = documentNavigationPage.getFocusedHighlightedTerm().getAttribute("id");
        if (actualFocusedTermId.equals(focusedTermId)) {
            return false;
        } else {
            focusedTermId = actualFocusedTermId;
            return true;
        }
    }

    public boolean verifyHighlightedSearchTerms(List<String> searchTerms, List<WebElement> highlightedTermElements) {
        List<String> highlightedSearchTerms = new ArrayList<>();
        boolean isHighlightedTermContainsSearchTerm = false;
        for (WebElement element : highlightedTermElements) {
            highlightedSearchTerms.add(element.getText());
        }
        for (String highlightedTerm : highlightedSearchTerms) {
            isHighlightedTermContainsSearchTerm = false;
            Iterator<String> itr = searchTerms.iterator();
            while (itr.hasNext()) {
                String term = itr.next();
                if (highlightedTerm.toLowerCase().contains(term.toLowerCase())) {
                    isHighlightedTermContainsSearchTerm = true;
                }
            }
        }
        return isHighlightedTermContainsSearchTerm;
    }

    /**
     * Check if opened from search page document has expected highlighted terms
     *
     * @param terms List of expected terms
     * @return True - if document has expected terms among highlighted terms. False - otherwise
     */
    public boolean isDocumentHasHighlightedTerms(List<String> terms) {
        for (String term : terms) {
            if (!isDocumentHasHighlightedTerm(term)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if opened from search page document has expected highlighted term
     *
     * @param term Term
     * @return True - if document has expected term among highlighted terms. False - otherwise
     */
    public boolean isDocumentHasHighlightedTerm(String term) {
        List<WebElement> highlightedTerms = documentNavigationPage.getAllHighlightedTerms();
        for (WebElement highlightedTerm : highlightedTerms) {
            if (highlightedTerm.getText().contains(term)) {
                return true;
            }
        }
        LOG.info("Term '{}' is absent among highlighted terms", term);
        return false;
    }

    public boolean isTermFound(String searchTerm) {
        String[] searchTermArray = searchTerm.split(StringUtils.SPACE);
        int searchRowCount = getSearchRowCount();

        int searchTermRowCount = 0;
        boolean isFound = false;
        for (int row = 1; row <= searchRowCount; row++) {
            isFound = isSearchTermFound(searchTermArray, row);

            if (isFound) {
                searchTermRowCount = searchTermRowCount + 1;
            } else {
                if (searchTermArray.length == 2) {
                    navigateAndCheckTheSearchTermInDocument(row, searchTermArray[0], searchTermArray[1]);
                    searchTermRowCount = searchTermRowCount + 1;
                } else if (searchTermArray.length > 2 && searchTermArray[1].equalsIgnoreCase("And")) {
                    navigateAndCheckTheSearchTermInDocument(row, searchTermArray[0], searchTermArray[2]);
                    searchTermRowCount = searchTermRowCount + 1;
                }
            }
        }
        return (searchRowCount == searchTermRowCount);
    }

    private int getSearchRowCount() {
        int searchRowCount = searchResultsPage.getAllSearchTitleLinks().size();
        if (searchRowCount > 3) {
            return 3;
        }
        return searchRowCount;
    }

    private boolean isSearchTermFound(String[] searchTermArray, int row) {
        for (WebElement selectedTerm : knowHowSearchResultsPage.snippetSearchTermList(row)) {
            if (searchTermArray.length == 1) {
                if (selectedTerm.getText().equalsIgnoreCase(searchTermArray[0])) {
                    return true;
                }
            } else {
                if (searchTermArray[1].equalsIgnoreCase("OR")
                        && (selectedTerm.getText().toLowerCase().contains(searchTermArray[0].toLowerCase()) ||
                        selectedTerm.getText().toLowerCase().contains(searchTermArray[2].toLowerCase()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void navigateAndCheckTheSearchTermInDocument(int rowNumber, String searchTerm1, String searchTerm2) {
        searchTerm1 = searchTerm1.toLowerCase();
        searchTerm2 = searchTerm2.toLowerCase();
        knowHowSearchResultsPage.knowHowSearchResultTitle(String.valueOf(rowNumber)).click();
        String docText = getFullText().toLowerCase();
        if (!docText.contains(searchTerm1) && !docText.contains(searchTerm2)) {
            throw new PageOperationException("The search result doesn't contain the search terms and it isn't in the full text");
        }
        knowHowSearchResultsPage.waitForPageToLoad();
        knowHowSearchResultsPage.getElementByLinkText("Search Results").click();
        knowHowSearchResultsPage.waitForExpectedElement(knowHowSearchResultsPage.searchResultByCountLabel(), 3000);

    }

    public String getFullText() {
        String docText;
        if (commonMethods.isCurrentDocumentFromKnowHow()) {
            openDraftingNotesIfPresent();
            docText = knowHowDocumentPage.getFullText();
        } else {
            docText = whatsMarketDocumentPage.getFullText();
        }
        return docText;
    }

    public void openDraftingNotesIfPresent() {
        if (resourcePage.isDraftingNotesDeliveryIconExist()) {
            resourcePage.selectShowAndHideDraftingNotesLink();
            resourcePage.selectOptionFromDraftingNotes(DraftingNotes.SHOW_ALL);
        }
    }

    /**
     * Wait for page results to load
     */
    public void waitSearchResultsToLoad() {
        searchResultsPage.waitForPageToLoad();

        // Wait while loading picture will be present to start waiting of it absence. In the following method
        // search results are counting too, so if search results appear instead of loading picture, we won't receive any exceptions
        searchResultsPage.waitForLoadingPictureOrResults();

        // When Thread.sleep() is used, a kitty dies somewhere in the world, but currently have no another way :(
        TimeoutUtils.sleepInSeconds(1);

    }

    public void selectDetailsLevel(SearchResultsPage.SliderSelector sliderSelector) {
        switch (sliderSelector) {
            case LESS:
                searchResultsPage.lessDetailOption().click();
                break;
            case MORE:
                searchResultsPage.moreDetailOption().click();
                break;
            case MOST:
                searchResultsPage.mostDetailOption().click();
                break;
            default:
                throw new IllegalArgumentException("Unknown option " + sliderSelector);
        }
    }

    /**
     * Get parsed string for the expected document version text from feature. Available placeholders are:
     * - {@link #DOC_VERSION_PLACEHOLDER_START_DATE} - for the document start date
     * - {@link #DOC_VERSION_PLACEHOLDER_END_DATE} - for the document end date
     * - {@link #DOC_VERSION_PLACEHOLDER_VERSION} - for the document version number
     * - {@link #DOC_VERSION_PLACEHOLDER_VERSIONS} - for the document version count
     * - {@link #DOC_VERSION_PLACEHOLDER_NO_TEXT} - if version text should be empty
     *
     * @param stringWithPlaceholders String with expected version text and placeholders
     * @param legislationDocument    Legislation document object
     * @return String for version text with the data from the document object instead of placeholders
     */
    public String getParsedVersionString(String stringWithPlaceholders, LegislationDocument legislationDocument) {
        return stringWithPlaceholders
                .replace(DOC_VERSION_PLACEHOLDER_START_DATE,
                        CalendarAndDate.convertDateToString(legislationDocument.getStartDate(), DOC_VERSION_DATE_FORMAT))
                .replace(DOC_VERSION_PLACEHOLDER_END_DATE,
                        CalendarAndDate.convertDateToString(legislationDocument.getEndDate(), DOC_VERSION_DATE_FORMAT))
                .replace(DOC_VERSION_PLACEHOLDER_VERSION, String.valueOf(legislationDocument.getVersion()))
                .replace(DOC_VERSION_PLACEHOLDER_VERSIONS, String.valueOf(legislationDocument.getVersionsCount()))
                .replace(DOC_VERSION_PLACEHOLDER_NO_TEXT, "");
    }

    /**
     * Get total amount of the documents for the facet group
     *
     * @param facetsGroupName Facet group name
     * @return Sum of the documents count for ach facet
     */
    public int getFacetsGroupTotalCount(String facetsGroupName) {
        int totalDocCount = 0;
        List<Integer> facetsGroupCount = knowHowSearchResultsPage.getFacetCounts(facetsGroupName);
        for (Integer facetDocCount : facetsGroupCount) {
            totalDocCount += facetDocCount;
        }
        return totalDocCount;
    }

    /**
     * Get search query from the search results page URL. Useful since the search field is absent on the search results
     * page for WLUK
     *
     * @return String with search query or empty string if there was unable to get search query from URL
     */
    public String getSearchQueryFromUrl() {
        List<NameValuePair> params = URLEncodedUtils.parse(commonMethods.getCurrentURL(), StandardCharsets.UTF_8);
        for (NameValuePair param : params) {
            // Needs to be endsWith() there because the first param is going with URL, e.g.
            // https://law.demo.thomsonreuters.co.uk/Search/Results.html?query=tax
            if (param.getName().endsWith(SEARCH_QUERY_PARAM_NAME)) {
                return param.getValue();
            }
        }
        return "";
    }

    //////////////////
    // Private methods
    //////////////////

    /**
     * Check if one word from list contains in text of given WebElement. Method will check the part of word
     * (without last two letters) because word form can be another
     * (e.g., the user searches "company", but result contains "companies" word).
     *
     * @param where    WebElement which text should contain one word from list
     * @param wordList Word list
     * @return True if check passed. Otherwise - false.
     */
    private boolean isWordFoundIn(WebElement where, List<String> wordList) {
        for (String oneWord : wordList) {
            String expectedWord = oneWord.substring(0, oneWord.length() - 2).toLowerCase();
            if (where.getText().toLowerCase().contains(expectedWord)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCheckboxSeleted(String facet) {
        for (int i = 0; i < 2; i++) {//magic number 3 !!!!!!
            knowHowSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
            if (!knowHowSearchResultsPage.knowHowFacetCheckbox(facet).isSelected()) {
                knowHowSearchResultsPage.practiceAreaFacetLabel().click();
                knowHowSearchResultsPage.knowHowFacetCheckbox(facet).click();
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Erase the content of given text field using Ctrl+A and Backspace key press emulation.
     * {@link WebElement#clear()} method may modifies text field and then its behaviour may be strange (e.g.,
     * background text "Search all Westlaw UK" may became active and unchangeable if some modifications were done
     * in the search options popop). This issue is exists for Selenium 3 (GeckoDriver)
     *
     * @param fieldElement Text field which content should be deleted
     */
    private void eraseField(WebElement fieldElement) {
        fieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        fieldElement.sendKeys(Keys.BACK_SPACE);
    }

    public List<Link> createLinksFromTextListAndHrefList(List<String> textLinks, List<String> hrefLinks){
        List<Link> links = new ArrayList<>();
        for(int i=0; i<textLinks.size(); i++){
            links.add(new Link(hrefLinks.get(i), textLinks.get(i)));
        }
        return links;
    }

    public List<Link> deleteLinksByHref(List<Link> links, String unwantedHref){
        List<Link> updatedLinkList = new ArrayList<>(links);
        for(Link link: links){
            if(link.getHref().contains(unwantedHref)){
                updatedLinkList.remove(link);
            }
        }
        return updatedLinkList;
    }

    public void expandFacet(String facetName) {
        if (knowHowSearchResultsPage.isFacetCheckboxCollapsed(facetName)) {
            knowHowSearchResultsPage.clickElementUsingJS(knowHowSearchResultsPage.expandCollapsedFacet(facetName));
            knowHowSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
        }
    }

    public enum FacetSelectionMode {
        SINGLE, MULTIPLE
    }

}