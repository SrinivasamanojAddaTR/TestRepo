package com.thomsonreuters.pageobjects.utils.search;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.Link;
import com.thomsonreuters.pageobjects.common.SortOptions;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.CommonResourcePage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.DraftingNotes;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.KHResourcePage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.DocumentNavigationPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchSearch.BaseResultsPage;
import com.thomsonreuters.pageobjects.pages.search.*;
import com.thomsonreuters.pageobjects.rest.LinkingBaseUtils;
import com.thomsonreuters.pageobjects.utils.document.CaseDocument;
import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.document.LegislationDocument;
import com.thomsonreuters.pageobjects.utils.document.ResourceType;
import com.thomsonreuters.pageobjects.utils.document.metadata.Jurisdiction;
import com.thomsonreuters.pageobjects.utils.legalUpdates.CalendarAndDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static final String HISTORY_SEARCH_DATE_FORMAT = "yyyyMMdd"; // e.g. 20160402
    private static final String DATE_PICKER_INPUT_DATE_FORMAT = "dd-MM-yyyy"; // e.g. 02-04-2016
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final String SEARCH_QUERY_PARAM_NAME = "query";
    private WLUKSearchResultsPage wlukSearchResultsPage = new WLUKSearchResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private KHResourcePage resourcePage = new KHResourcePage();
    private KnowHowDocumentPage knowHowDocumentPage = new KnowHowDocumentPage();
    private WhatsMarketDocumentPage whatsMarketDocumentPage = new WhatsMarketDocumentPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private SearchHomePage searchHomePage = new SearchHomePage();
    private DocumentNavigationPage documentNavigationPage = new DocumentNavigationPage();
    private WestlawCombinedResultsPage westlawCombinedResultsPage = new WestlawCombinedResultsPage();
    private CommonResourcePage commonResourcePage = new CommonResourcePage();
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

    /**
     * Check that user query in result element is highlighted. Method check that all <span> elements in result element contain search query
     * and are highlighted using css-style.
     * Method checks results with case ignoring
     *
     * @param element One of result elements {@link com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.GlossaryPage#glossaryTermsWithSearchTermList()}.
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
            LOG.info("Document " + (i + 1) + " " + documents.get(i).getFullTitle() + ", date " +
                    documents.get(i).getSortingDate() + ", resource type " + documents.get(i).getResourceType());
            if (prevDate.compareTo(nextDate) < 0) {
                LOG.info("Document " + (i + 1) + " '" + documents.get(i).getFullTitle() + "' is out of order by Date. " +
                        "Previous date " + prevDate + ", next date " + nextDate);
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
     * Verify that all search results have a resource type
     *
     * @param resourceType Resource Type
     * @return True - if check passed. False - otherwise.
     */
    public boolean areTheSearchResultsHaveResourceType(String resourceType) {
        List<Document> documents = getDocumentsFromNovus();
        int i = 1; // counter for the document
        for (Document document : documents) {
            if (!document.getResourceType().equals(resourceType)) {
                LOG.info("Document " + i + " '" + document.getFullTitle() + "' has a wrong resource type " + document.getResourceType() +
                        "But expected is " + resourceType);
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * This method verifies that every search result contains expected Jurisdiction
     *
     * @param expectedJurisdiction Jurisdiction which should be present for the documents
     * @return True - if every search result contains expected Jurisdiction, false - otherwise.
     */
    public boolean areTheSearchResultsHaveJurisdiction(String expectedJurisdiction) {
        List<Document> documents = getDocumentsFromNovus();
        int i = 1; // counter for the document
        for (Document document : documents) {
            boolean isJurisdictionFound = false;
            for (Jurisdiction actualJurisdiction : document.getJurisdictions()) {
                if (actualJurisdiction.getName().equals(expectedJurisdiction)) {
                    isJurisdictionFound = true;
                    break;
                }
            }
            if (!isJurisdictionFound) {
                LOG.info("Result number " + i + "  has no expected  jurisdiction " + expectedJurisdiction + " in the " +
                        "jurisdiction list " + document.getJurisdictions());
                return false;
            }
            i++;
        }
        return true;
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

    public boolean isSearchTermsPresentInParagraph(KnowHowDocumentPage.TermsInSequence termsInSequence, String firstTerm, String secondTerm) {
        if (commonMethods.isCurrentDocumentFromKnowHow()) {
            return knowHowDocumentPage.isSearchTermsPresentInParagraph(termsInSequence, firstTerm, secondTerm);
        } else {
            return whatsMarketDocumentPage.isSearchTermsPresentInParagraph(termsInSequence, firstTerm, secondTerm);
        }
    }

    public boolean isSearchTermsPresentInSentence(KnowHowDocumentPage.TermsInSequence termsInSequence, String firstTerm, String secondTerm) {
        return knowHowDocumentPage.isSearchTermsPresentInSentence(termsInSequence, firstTerm, secondTerm);
    }

    public boolean isSearchTermsPresentInParagraphWithInNumberOfWords(KnowHowDocumentPage.TermsInSequence termsInSequence, int noOfTerms, String firstTerm, String secondTerm) {
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
        List<String> highlightedSearchTerms = new ArrayList<String>();
        //SoftAssertions softly = new SoftAssertions();
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
            } //softly.assertThat(isHighlightedTermContainsSearchTerm).isTrue().overridingErrorMessage("Highlighted Search term <%s> does not contain search term", highlightedTerm);
        }
        return isHighlightedTermContainsSearchTerm;
        //softly.assertAll();
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
        LOG.info("Term '" + term + "' is absent among highlighted terms");
        return false;
    }

    public boolean isTermFound(String searchTerm) {
        String searchTermArray[] = searchTerm.split(" ");
        int searchRowCount = searchResultsPage.getAllSearchTitleLinks().size();
        if (searchRowCount > 3) {
            searchRowCount = 3;
        }
        int searchTermRowCount = 0;
        boolean isFound = false;
        for (int row = 1; row <= searchRowCount; row++) {
            for (WebElement selectedTerm : knowHowSearchResultsPage.snippetSearchTermList(row)) {
                if (searchTermArray.length == 1) {
                    if (selectedTerm.getText().equalsIgnoreCase(searchTermArray[0])) {
                        isFound = true;
                        break;
                    }
                } else {
                    if (searchTermArray[1].equalsIgnoreCase("OR")) {
                        if (selectedTerm.getText().toLowerCase().contains(searchTermArray[0].toLowerCase()) ||
                                selectedTerm.getText().toLowerCase().contains(searchTermArray[2].toLowerCase())) {
                            isFound = true;
                            break;
                        }
                    }
                }
            }
            if (isFound) {
                searchTermRowCount = searchTermRowCount + 1;
                isFound = false;
            } else {
                if (searchTermArray.length == 2) {
                    navigateAndCheckTheSearchTermInDocument(row, searchTermArray[0], searchTermArray[1]);
                    searchTermRowCount = searchTermRowCount + 1;
                    isFound = false;
                } else if (searchTermArray.length > 2 && searchTermArray[1].equalsIgnoreCase("And")) {
                    navigateAndCheckTheSearchTermInDocument(row, searchTermArray[0], searchTermArray[2]);
                    searchTermRowCount = searchTermRowCount + 1;
                    isFound = false;
                }
            }
        }
        return (searchRowCount == searchTermRowCount);
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
        knowHowSearchResultsPage.getElementByLinkText("Return to list").click();
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

        // Wait till loading picture will disappears
        searchResultsPage.waitLoadingElementsAbsent();
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
     * Verify that documents with the same sorting date are sorted alphabetically by title
     *
     * @return True - if check passed. False - otherwise.
     */
    public boolean isSecondaryOrderingAlphabeticalByTitle() {
        Map<Date, List<Document>> groupedDocuments = getDocumentsGroupedByDate(getDocumentsFromNovus());
        if (groupedDocuments.isEmpty()) {
            LOG.info("There are no documents to check ordering");
            return false;
        }
        for (Date sortingDate : groupedDocuments.keySet()) {
            List<String> documentFullTitles = getDocumentFullTitlesFromDocuments(groupedDocuments.get(sortingDate));
            if (!commonMethods.isSorted(documentFullTitles, SortOptions.ASC)) {
                LOG.info("Documents for the " + sortingDate + " sorting date group are not in alphabetical order by titles: " +
                        documentFullTitles.toString());
                return false;
            }
        }
        return true;
    }

    /**
     * Verify that all cases sorted alphabetically by party names.
     * WARNING: before using this method filter the search results by "Cases"
     *
     * @return True - if check passed. False - otherwise.
     */
    public boolean isSecondaryOrderingAlphabeticalByPartyNamesForCases() {
        Map<Date, List<Document>> groupedDocuments = getDocumentsGroupedByDate(getCasesDocumentsFromNovus());
        if (groupedDocuments.isEmpty()) {
            LOG.info("There are no documents to check ordering");
            return false;
        }
        for (Date sortingDate : groupedDocuments.keySet()) {
            List<String> documentPartyNames = getCasesPartyNamesFromDocumentsInLowerCase(groupedDocuments.get(sortingDate));
            if (!commonMethods.isSorted(documentPartyNames, SortOptions.ASC)) {
                LOG.info("Documents for the " + sortingDate + " sorting date group are not in alphabetical order by party names: " +
                        documentPartyNames.toString() + ". The document result which grouped by date are: " +
                        groupedDocuments.get(sortingDate));
                return false;
            }
        }
        return true;
    }

    /**
     * Verify that all legislation SI groups with the same Royal Assent date sorted alphabetically by
     * the governing legislation title
     * WARNING: before using this method filter the search results by "Legislation"
     *
     * @return True - if check passed. False - otherwise or if there are no SI groups with the same Royal Assent date
     * presented on the search results page.
     */
    public boolean isSecondaryOrderingAlphabeticalBySiGroupsTitle() {
        Map<String, List<Document>> groupedDocuments = getLegislationDocumentsAsGroupsBeforeSi(getLegislationDocumentsFromNovus());
        if (groupedDocuments.isEmpty()) {
            LOG.info("There are no documents to check ordering");
            return false;
        }
        List<String> groupedDocumentTitles = new ArrayList<>(groupedDocuments.keySet());
        int groupsCount = groupedDocumentTitles.size();
        boolean areGroupsWithSameDateFound = false;
        for (int i = 0; i < groupsCount; i++) {
            String currentDocumentTitle = groupedDocumentTitles.get(i);
            String nextDocumentTitle = (i + 1 < groupsCount) ? groupedDocumentTitles.get(i + 1) : null;
            Document currentFirstGroupDocument = groupedDocuments.get(currentDocumentTitle).get(0);
            Document nextFirstGroupDocument = (nextDocumentTitle != null) ? groupedDocuments.get(nextDocumentTitle).get(0) : null;
            Date currentGroupSortingDate = currentFirstGroupDocument.getSortingDate();
            Date nextGroupSortingDate = (nextFirstGroupDocument != null) ? nextFirstGroupDocument.getSortingDate() : null;
            if (nextGroupSortingDate != null && currentGroupSortingDate.compareTo(nextGroupSortingDate) == 0) {
                areGroupsWithSameDateFound = true;
                LOG.info("Groups with the same Royal Assent date found: '" + currentDocumentTitle + "' and '" + nextDocumentTitle + "'");
                List<String> titles = new ArrayList<>();
                titles.add(currentDocumentTitle);
                titles.add(nextDocumentTitle);
                boolean isSorted = commonMethods.isSorted(titles, SortOptions.ASC);
                if (!isSorted) {
                    LOG.info("The groups with the same date are not sorted alphabetically: '" + currentDocumentTitle +
                            "', '" + nextDocumentTitle + "'");
                    return false;
                }
            }
        }
        if (!areGroupsWithSameDateFound) {
            LOG.info("WARNING: there are no SI groups found with the same Royal Assent date. Use another search team.");
            return false;
        }
        return true;
    }

    /**
     * Verify that legislation documents with the same Royal Assent date presented as groups before SI's
     * WARNING: before using this method filter the search results by "Legislation"
     *
     * @return True - if check passed. False - otherwise.
     */
    public boolean areLegislationDocumentsGroupedBeforeSi() {
        Map<String, List<Document>> groupedLegisDocsByTitle =
                getLegislationDocumentsAsGroupsBeforeSi(getLegislationDocumentsFromNovus());
        List<WebElement> searchResultsLinks = searchResultsPage.getAllSearchTitleLinks();
        Set<String> uniqueSearchResultsTitles = new HashSet<>(commonMethods.getTextsFromWebElements(searchResultsLinks));
        uniqueSearchResultsTitles.removeAll(groupedLegisDocsByTitle.keySet());
        if (uniqueSearchResultsTitles.isEmpty()) {
            return true;
        } else {
            LOG.info("These documents are out of group before SI: " + uniqueSearchResultsTitles.toString());
            return false;
        }
    }

    /**
     * Verify that count of legislation documents within groups are the same as count of search results presented on
     * the search results page
     * WARNING: before using this method filter the search results by "Legislation"
     *
     * @return True - if check passed. False - otherwise.
     */
    public boolean isLegislationDocumentsFromGroupsCountEqualsToSearchResultsCount() {
        int expectedResultsCount = searchResultsPage.getAllSearchTitleLinks().size();
        Map<String, List<Document>> groupedLegisDocsByTitle =
                getLegislationDocumentsAsGroupsBeforeSi(getLegislationDocumentsFromNovus());
        int actualResultsCount = 0;
        Set<String> docTitles = groupedLegisDocsByTitle.keySet();
        for (String docTitle : docTitles) {
            actualResultsCount += groupedLegisDocsByTitle.get(docTitle).size();
        }
        if (actualResultsCount == expectedResultsCount) {
            return true;
        } else {
            LOG.info("Some results are absent. The following groups are expected on the page: " + docTitles.toString());
            return false;
        }
    }

    /**
     * Verify that legislation documents inside one SI group have a correct rank by provision
     * WARNING: before using this method filter the search results by "Legislation"
     *
     * @return True - if check passed. False - otherwise.
     */
    public boolean areLegislationDocsSortedByProvisionInSiGroup() {
        Map<String, List<Document>> groupedLegisDocsByAct =
                getLegislationDocumentsAsGroupsBeforeSi(getLegislationDocumentsFromNovus());
        List<Integer> provisionsInSiGroup = new ArrayList<>();
        // Prefix for provision, e.g.: para, reg, s, ...
        String previousPrefix = StringUtils.EMPTY;
        String currentPrefix;
        for (String arrangementOfAct : groupedLegisDocsByAct.keySet()) {
            List<String> legisTitles = getLegislationSearchResultLinkTitlesFromDocuments(groupedLegisDocsByAct.get(arrangementOfAct));
            for (String legisTitle : legisTitles) {
                currentPrefix = getLegisProvisionPrefix(legisTitle);
                // Provision title is always lower cased (e.g.: para. 1, c. 19, s. 19, ...)
                if (Character.isLowerCase(legisTitle.charAt(0)) && (previousPrefix.isEmpty() || previousPrefix.equals(currentPrefix))) {
                    // The first group - will be a provision number which should be used for ranking
                    provisionsInSiGroup.add(
                            Integer.valueOf(commonMethods.getRegExpGroupValue("(\\d+)", legisTitle).get(0)));
                } else {
                    if (!commonMethods.isSorted(provisionsInSiGroup, SortOptions.ASC)) {
                        LOG.info("The provisions for Acts/SI group '" + arrangementOfAct +
                                "' is wrong: " + provisionsInSiGroup);
                        return false;
                    }
                    provisionsInSiGroup.clear();
                }
                previousPrefix = getLegisProvisionPrefix(legisTitle);
            }
        }
        return true;
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
     * Are legislation provision subsections present under the search result titles
     *
     * @return True - if the subtitles are present for the legislation provision search result
     */
    public boolean areLegislationProvisionSectionsPresent() {
        List<Document> documents = getLegislationDocumentsFromNovus();
        if (documents.isEmpty()) {
            LOG.info("No documents were obtained from Novus");
            return false;
        }
        int resultNumber = 1;
        for (Document document : documents) {
            LegislationDocument legislationDocument = (LegislationDocument) document;
            if (!legislationDocument.getSectionsDocuments().isEmpty()) {
                List<String> actualLegisSections =
                        commonMethods.getTextsFromWebElements(westlawCombinedResultsPage.legislationProvisionSections("" + resultNumber));
                List<String> expectedLegisSections = getDocumentFullTitlesFromDocuments(legislationDocument.getSectionsDocuments());
                if (!actualLegisSections.equals(expectedLegisSections)) {
                    LOG.info("The result No " + resultNumber + " has no expected sections " + expectedLegisSections +
                            ", but has " + actualLegisSections + " under the title.");
                    return false;
                }
            }
            resultNumber++;
        }
        return true;
    }

    /**
     * Get search query from the search results page URL. Useful since the search field is absent on the search results
     * page for WLUK
     *
     * @return String with search query or empty string if there was unable to get search query from URL
     */
    public String getSearchQueryFromUrl() {
        List<NameValuePair> params = URLEncodedUtils.parse(commonMethods.getCurrentURL(), UTF_8);
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

    /**
     * Get documents as objects from Novus
     *
     * @param guids        List of guids
     * @param resourceType Necessary resource type
     * @return List of the obtained documents
     */
    private List<Document> getDocumentsFromNovus(List<String> guids, ResourceType resourceType) {
        return linkingUtils.getDocumentsFromNovus(guids, resourceType);
    }

    /**
     * Get documents as objects from Novus for the all results presented on the search results page.
     *
     * @return List of the obtained documents with generic {@link Document} type
     */
    private List<Document> getDocumentsFromNovus() {
        return getDocumentsFromNovus(getGuidsFromSearchResults(), ResourceType.GENERIC);
    }

    /**
     * Get case documents as objects from Novus for the all results presented on the search results page.
     *
     * @return List of the obtained documents
     */
    private List<Document> getCasesDocumentsFromNovus() {
        return getDocumentsFromNovus(getGuidsFromSearchResults(), ResourceType.CASES);
    }

    /**
     * Get legislation documents as objects from Novus for the all results presented on the search results page.
     *
     * @return List of the obtained documents
     */
    private List<Document> getLegislationDocumentsFromNovus() {
        return getDocumentsFromNovus(getGuidsFromSearchResults(), ResourceType.LEGISLATION);
    }

    /**
     * Group document objects by date to the LinkedHashMap (to preserve the order)
     *
     * @param documents List of the documents which should be grouped
     * @return Map with the sorting date as key and lists of the document objects (which have the same sorting date)
     * as value
     */
    private Map<Date, List<Document>> getDocumentsGroupedByDate(List<Document> documents) {
        Map<Date, List<Document>> result = new LinkedHashMap<>();
        if (documents.isEmpty()) {
            return result;
        }
        List<Document> tmpGroupedDocuments = new ArrayList<>();
        int documentsCount = documents.size();
        for (int i = 0; i < documentsCount; i++) {
            Date currentDocSortingDate = documents.get(i).getSortingDate();
            // Check if we have a next document. If not - current i document is the last. Assign the null
            Date nextDocSortingDate = (i + 1 < documentsCount) ? documents.get(i + 1).getSortingDate() : null;
            tmpGroupedDocuments.add(documents.get(i));
            if (nextDocSortingDate == null || currentDocSortingDate.compareTo(nextDocSortingDate) != 0) {
                result.put(currentDocSortingDate, new ArrayList<>(tmpGroupedDocuments));
                tmpGroupedDocuments.clear();
            }
        }
        return result;
    }

    /**
     * Group document objects by SI title (as groups before SI's) to the LinkedHashMap (to preserve the order)
     *
     * @param documents List of the documents which should be grouped
     * @return Map with the SI document tile as key and lists of the document objects (which have the same SI title)
     * as value
     */
    private Map<String, List<Document>> getLegislationDocumentsAsGroupsBeforeSi(List<Document> documents) {
        Map<String, List<Document>> result = new LinkedHashMap<>();
        if (documents.isEmpty()) {
            return result;
        }
        List<Document> tmpGroupedDocuments = new ArrayList<>();
        int documentsCount = documents.size();
        for (int i = 0; i < documentsCount; i++) {
            String currentDocTitle = documents.get(i).getFullTitle();
            // Check if we have a next document. If not - current i document is the last. Assign the null
            String nextDocTitle = (i + 1 < documentsCount) ? documents.get(i + 1).getFullTitle() : null;
            tmpGroupedDocuments.add(documents.get(i));
            if (nextDocTitle == null || !currentDocTitle.equals(nextDocTitle)) {
                result.put(currentDocTitle, new ArrayList<>(tmpGroupedDocuments));
                tmpGroupedDocuments.clear();
            }
        }
        return result;
    }

    /**
     * Get the list of full document titles {@link Document#getFullTitle()}
     *
     * @param documents List with {@link Document} objects
     * @return The list with the full titles
     */
    private List<String> getDocumentFullTitlesFromDocuments(List<Document> documents) {
        List<String> result = new ArrayList<>();
        for (Document document : documents) {
            result.add(document.getFullTitle());
        }
        return result;
    }

    /**
     * Get the list of cases party names {@link CaseDocument#getPartyNames()} in lower case
     * WARNING: given list should contains {@link CaseDocument} instances
     *
     * @param documents List with {@link CaseDocument} objects
     * @return The list with the party names for the given documents
     * @throws ClassCastException if list contains wong object types
     */
    private List<String> getCasesPartyNamesFromDocumentsInLowerCase(List<Document> documents) {
        List<String> result = new ArrayList<>();
        for (Document document : documents) {
            result.add(((CaseDocument) document).getPartiesAsString().toLowerCase());
        }
        return result;
    }

    /**
     * Get the list of search result link titles for {@link LegislationDocument#getSearchResultLinkTitle()} for legislation
     * WARNING: given list should contains {@link LegislationDocument} instances
     *
     * @param documents List with {@link LegislationDocument} objects
     * @return The list with the legislation search result link titles for the given documents
     * @throws ClassCastException if list contains wong object types
     */
    private List<String> getLegislationSearchResultLinkTitlesFromDocuments(List<Document> documents) {
        List<String> result = new ArrayList<>();
        for (Document document : documents) {
            result.add(((LegislationDocument) document).getSearchResultLinkTitle());
        }
        return result;
    }

    /**
     * Verify that all presented facet checkboxes are not selected
     *
     * @return True - if there are no selected facet checkboxes, false - otherwise
     * @throws RuntimeException in case if there are no checkboxes were found on the page. In this case,
     *                          check the selector for checkboxes {@link SearchResultsPage#getAllFacetCheckBoxes} and if it is OK,
     *                          check the waiter - probably there is necessary to wait while search results and facets will be loaded
     */
    public boolean areFacetsNotSelectedOnSearchReultsPage() {
        List<WebElement> checkboxes = searchResultsPage.getAllFacetCheckBoxes();
        if (checkboxes.isEmpty()) {
            throw new RuntimeException("No facet checkboxes were obtained from the search results page");
        }
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                return false;
            }
        }
        return true;
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
     * Get expected date field for search item on the history page
     *
     * @param datePicker Date picker item {@link SearchResultsDatePicker}
     * @param dateFrom   Date before / after / specific / from for:
     *                   All Dates Before, All Dates After, Specific Date, Date Range
     * @param dateUntil  Date until for Date Range
     * @return
     */
    public String getDateInFieldForSearchInHistory(SearchResultsDatePicker datePicker, String dateFrom, String dateUntil) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat historyDateFormat = new SimpleDateFormat(HISTORY_SEARCH_DATE_FORMAT);
        SimpleDateFormat datePickerDateFormat = new SimpleDateFormat(DATE_PICKER_INPUT_DATE_FORMAT);
        try {
            switch (datePicker) {
                case LAST_6_MONTHS:
                    // Current day minus half a year plus a day
                    calendar.add(Calendar.MONTH, -6);
                    calendar.add(Calendar.DAY_OF_MONTH, 1); // TODO Pavel: To clarify date including / excluding at Santi
                    return historyDateFormat.format(calendar.getTime()) + ":" + historyDateFormat.format(new Date());
                case LAST_12_MONTHS:
                    // Current day minus a year plus a day
                    calendar.add(Calendar.YEAR, -1);
                    calendar.add(Calendar.DAY_OF_MONTH, 1); // TODO Pavel: To clarify date including / excluding at Santi
                    return historyDateFormat.format(calendar.getTime()) + ":" + historyDateFormat.format(new Date());
                case LAST_3_YEARS:
                    // Current day minus 3 years plus a day
                    calendar.add(Calendar.YEAR, -3);
                    calendar.add(Calendar.DAY_OF_MONTH, 1); // TODO Pavel: To clarify date including / excluding at Santi
                    return historyDateFormat.format(calendar.getTime()) + ":" + historyDateFormat.format(new Date());
                case ALL_DATES_BEFORE:
                    return "BEF " + historyDateFormat.format(datePickerDateFormat.parse(dateFrom));
                case ALL_DATES_AFTER:
                    return "AFT " + historyDateFormat.format(datePickerDateFormat.parse(dateFrom));
                case SPECIFIC_DATE:
                    return historyDateFormat.format(datePickerDateFormat.parse(dateFrom));
                case DATE_RANGE:
                    return historyDateFormat.format(datePickerDateFormat.parse(dateFrom)) + ":" +
                            historyDateFormat.format(datePickerDateFormat.parse(dateUntil));
            }
        } catch (ParseException e) {
            LOG.info("Unable to parse date from / until", e);
        }
        return "";
    }

    /**
     * Get legislation provision title prefix. For example, for "reg. 8 Qualifying paid work" it will be "reg"
     *
     * @param provisionTitle Legislation provision title
     * @return Title prefix or empty string if given provision title not a title of legislation provision section
     */
    private String getLegisProvisionPrefix(String provisionTitle) {
        return Character.isLowerCase(provisionTitle.charAt(0)) ? provisionTitle.replaceAll("\\..*", "") : StringUtils.EMPTY;
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