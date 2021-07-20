package com.thomsonreuters.pageobjects.utils.novus;

import com.westgroup.novus.productapi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Pavel_Ardenka on 15/07/2016.
 * Base abstract class for Novus utils which help to get documents from Novus
 */
public abstract class BaseNovusUtils {

    private final Novus novus;
    protected static final Logger LOG = LoggerFactory.getLogger(BaseNovusUtils.class);
    private static final String NOVUS_ENV = "PROD";
    private static final int NOVUS_TIMEOUT_MILLIS = 60000;
    private static final int NOVUS_PROGRESS_WAIT_MILLIS = 100;
    protected static final int DEFAULT_NOVUS_RESULTS_LIMIT = 100;
    private int novusResultLimit = 100;
    private static final int NOVUS_DEFAULT_SYNTAX_TYPE = Search.NATIVE;
    private static final String NOVUS_ROUTE_TAG = "cobalt";
    private static final String NOVUS_QUEUE_MANAGER = "novusprdjms";
    private static final boolean SAME_SEARCH_OBJECT = true;
    private static final String TAG_DOCUMENT = "n-document";
    private List<String> documentCollections;
    private List<String> documentCollectionSets;
    private Search paginatedSearch;
    private SearchResult paginatedSearchResult;
    private SearchResult novusSearch;

    /**
     * Create {@link Novus} object to communicate with Novus
     */
    public BaseNovusUtils() {
        Novus tempNovus = new Novus();
        tempNovus.setRouteTag(NOVUS_ROUTE_TAG);
        tempNovus.setQueueCriteria(NOVUS_QUEUE_MANAGER, NOVUS_ENV);
        tempNovus.setResponseTimeout(NOVUS_TIMEOUT_MILLIS);
        tempNovus.useLatestPit();
        this.novus = tempNovus;
    }

    /**
     * Get list of collection names which is used for the document search
     *
     * @return List with collection names
     */
    protected List<String> getDocumentCollections() {
        return documentCollections;
    }

    /**
     * Set collection names which will be used for the document search
     * WARNING: either collection or collection set should be set for the proper work
     * NOTICE: to get appropriate documentCollections see {@link com.thomsonreuters.pageobjects.utils.document.ResourceType} enum
     *
     * @param documentCollections List with collection names
     */
    protected void setDocumentCollections(List<String> documentCollections) {
        this.documentCollections = documentCollections;
    }

    /**
     * Get collection set names which is used for the document search
     *
     * @return Collection set name
     */
    protected List<String> getDocumentCollectionSets() {
        return documentCollectionSets;
    }

    /**
     * Set the list of collection set names which will be used for the document search
     * WARNING: either collection or collection set should be set for the proper work
     *
     * @param collectionSets List with collection set names
     */
    protected void setDocumentCollectionSets(List<String> collectionSets) {
        this.documentCollectionSets = collectionSets;
    }

    /**
     * Get {@link #novus} object
     *
     * @return Novus object
     */
    protected Novus getNovus() {
        return novus;
    }

    /**
     * Get search object to perform a search
     *
     * @return {@link Search} object
     */
    private Search getInitialSearchObject() {
        Search search = novus.getSearch();
        search.setPaginationEnabled(false);
        search.setDocumentLimit(novusResultLimit);
        search.setSyntaxType(NOVUS_DEFAULT_SYNTAX_TYPE);
        if (documentCollections != null) {
            for (String collection : documentCollections) {
                search.addCollection(collection);
            }
        } else if (documentCollectionSets != null) {
            for (String collectionSet : documentCollectionSets) {
                search.addCollectionSet(collectionSet);
            }
        } else {
            throw new UnsupportedOperationException("Either collection or collection set should be set for a search");
        }
        return search;
    }

    /**
     * Get search result for the search
     *
     * @param search Search object with set data to search (collection or collection set, search type and search query)
     * @return Search result for content or NULL if there are some errors occurred during the search
     */
    private SearchResult getSearchResult(Search search) {
        try {
            Progress progress = search.submit(SAME_SEARCH_OBJECT);
            while (!progress.isComplete()) {
                progress = search.getProgress(progress.getDetachedHandle(), NOVUS_PROGRESS_WAIT_MILLIS);
            }
            novusSearch = search.getSearchResult();
            return search.getSearchResult();
        } catch (NovusException e) {
            LOG.info("Search was not finished. ", e);
        }
        return novusSearch;
    }

    /**
     * Get content from the search result
     *
     * @param searchResult Search result with performed search
     * @return Document array from the search result. Every content object has Meta Data in String representation or
     *         NULL if there are any errors occurred during the obtaining content from the search result
     */
    private Document[] getSearchResultDocuments(SearchResult searchResult) {
        Document[] document={};
        try {
            return searchResult.getAllDocuments();
        } catch (NovusException e) {
            LOG.info("Unable to get documents from the search results. ", e);
            return document;
        }
    }

    /**
     * Perform the search and get searched documents
     *
     * @param searchTerm Searched term or Easel-like formula. For the latest {@link NovusSearchCriteria} can be used as
     *                   helper
     * @param queryType Search type. Possible values from {@link Search} interface:
     *                  - Search.BOOLEAN - if there is a boolean search with connectors or Easel-like query
     *                  - Search.NATURAL_LANGUAGE - if there is a free search
     * @return Document array from the search result. Every document object has Meta Data and Doc body in String
     *         representation of XML or NULL if there are any errors occurred during the obtaining document from
     *         the search result
     * @throws IllegalArgumentException in case when there are no results for search criteria
     */
    protected Document[] getResultsFor(String searchTerm, int queryType) {
        Search search = getInitialSearchObject();
        search.setQueryType(queryType);
        search.setQueryText(searchTerm);
        SearchResult searchResult = getSearchResult(search);
        Document[] results = getSearchResultDocuments(searchResult);
        if (results == null) {
            throw new IllegalArgumentException("There are no results for the query '" + searchTerm + "', search type " + queryType);
        } else {
            return results;
        }
    }

    /**
     * Perform the paginated search and get searched documents for the next page. If method was invoked at first time or
     * {@link #resetPaginatedSearch()} method was invoked before, then search results for the first page will return.
     *
     * @param searchTerm Searched term or Easel-like formula. For the latest {@link NovusSearchCriteria} can be used as
     *                   helper
     * @param queryType Search type. Possible values from {@link Search} interface:
     *                  - Search.BOOLEAN - if there is a boolean search with connectors or Easel-like query
     *                  - Search.NATURAL_LANGUAGE - if there is a free search
     * @return Document array from the search result. Every document object has Meta Data and Doc body in String
     *         representation of XML or NULL if there are any errors occurred during the obtaining document from
     *         the search result
     * @throws IllegalArgumentException in case when there are no results for search criteria
     */
    protected Document[] getResultsForPaginatedSearch(String searchTerm, int queryType) {
        if (paginatedSearch == null) {
            paginatedSearch = getInitialSearchObject();
            paginatedSearch.setQueryType(queryType);
            paginatedSearch.setQueryText(searchTerm);
            paginatedSearch.setPaginationEnabled(true);
            paginatedSearchResult = getSearchResult(paginatedSearch);
            return getSearchResultDocuments(paginatedSearchResult);
        }
        paginatedSearch = paginatedSearchResult.getNextPaginatedSearch(novusResultLimit);
        IllegalArgumentException nullResultsException =
                new IllegalArgumentException("There are no paginated results for the query '" + searchTerm + "', search type " + queryType);
        if (paginatedSearch == null) {
            throw nullResultsException;
        } else {
            SearchResult searchResult = getSearchResult(paginatedSearch);
            paginatedSearchResult = searchResult;
            Document[] resultsContent = getSearchResultDocuments(searchResult);
            if (resultsContent == null) {
                throw nullResultsException;
            } else {
                return resultsContent;
            }
        }
    }

    /**
     * Reset paginated search
     */
    protected void resetPaginatedSearch() {
        paginatedSearch = null;
        paginatedSearchResult = null;
    }

    /**
     * Get metadata from Document as String
     *
     * @param document Document object after search (from the search result) which extends Content interface as wee
     * @return String representation of XML meta-data or empty string if there are any errors occurred during the
     *         obtaining Meta Data
     */
    protected String getContentMetaData(Content document) {
        try {
            return document.getMetaData();
        } catch (NovusException e) {
            LOG.info("Could not get meta-data. ", e);
            return "";
        }
    }

    /**
     * Get document text as XML string <n-docbody>...</n-docbody>
     *
     * @param document Document after search
     * @return String representation of XML document text or empty string if there are any errors occurred during the
     *         document processing
     */
    protected String getDocumentText(Document document) {
        try {
            return document.getText();
        } catch (NovusException e) {
            LOG.info("Could not get document text. ", e);
            return "";
        }
    }

    /**
     * Set results limit (100) to search
     *
     * @param resultsCount Desirable documents count to search
     */
    protected void setResultsLimit(int resultsCount) {
        novusResultLimit = resultsCount;
    }

    /**
     * Get full document as XML String (Meta data + text) by document GUID
     *
     * @param guid Document GUID
     * @return String representation of document or empty string if there are some errors were occurred during processing
     */
    protected String getRawXmlDocumentByGuid(String guid) {
        Find find = novus.getFind();
        Document document = find.getDocument(null, guid);
        return "<" +
                TAG_DOCUMENT +
                ">" +
                getContentMetaData(document) +
                getDocumentText(document) +
                "</" +
                TAG_DOCUMENT +
                ">";
    }

    /**
     * Transform Novus documents to document object (and it inheritances) which are using in the TAF
     *
     * @param documents Content array from the search result
     * @return List of the document objects
     */
    protected abstract List<com.thomsonreuters.pageobjects.utils.document.Document> transformDocs(Content[] documents);
}
