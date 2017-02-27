package com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

/**
 * Created by Pavel_Ardenka on 26.10.2015.
 */
class ContentLimitOptions extends ABaseRequest {

    private static final String ONLY_PAGES_WITH_SEARCH_TERMS_NAME = "OnlyPagesWithSearchTerms";
    private static final String STAR_PAGES_NAME = "StarPages";
    private static final String DUAL_COLUMNS_FOR_CASES_NAME = "DualColumnsForCases";
    private static final String DUAL_COLUMNS_FOR_NEWSLETTERS_NAME = "DualColumnsForNewsletters";
    private static final String SYNOPSIS_AND_HEADNOTES_ONLY_NAME = "SynopsisAndHeadnotesOnly";
    private static final String STATUTORY_TEXT_ONLY_NAME = "StatutoryTextOnly";
    private static final String SHOULD_FOLDER_NAME = "ShouldFolder";

    private boolean onlyPagesWithSearchTerms = false;
    private boolean starPages = false;
    private boolean dualColumnsForCases = false;
    private boolean dualColumnsForNewsletters = false;
    private boolean synopsisAndHeadnotesOnly = false;
    private boolean statutoryTextOnly = false;
    private boolean shouldFolder = false;

    public ContentLimitOptions setOnlyPagesWithSearchTerms(boolean onlyPagesWithSearchTerms) {
        this.onlyPagesWithSearchTerms = onlyPagesWithSearchTerms;
        return this;
    }

    public ContentLimitOptions setStarPages(boolean starPages) {
        this.starPages = starPages;
        return this;
    }

    public ContentLimitOptions setDualColumnsForCases(boolean dualColumnsForCases) {
        this.dualColumnsForCases = dualColumnsForCases;
        return this;
    }

    public ContentLimitOptions setDualColumnsForNewsletters(boolean dualColumnsForNewsletters) {
        this.dualColumnsForNewsletters = dualColumnsForNewsletters;
        return this;
    }

    public ContentLimitOptions setSynopsisAndHeadnotesOnly(boolean synopsisAndHeadnotesOnly) {
        this.synopsisAndHeadnotesOnly = synopsisAndHeadnotesOnly;
        return this;
    }

    public ContentLimitOptions setStatutoryTextOnly(boolean statutoryTextOnly) {
        this.statutoryTextOnly = statutoryTextOnly;
        return this;
    }

    public ContentLimitOptions setShouldFolder(boolean shouldFolder) {
        this.shouldFolder = shouldFolder;
        return this;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode contentLimitOptions = objectMapper.createObjectNode();
        contentLimitOptions.put(ONLY_PAGES_WITH_SEARCH_TERMS_NAME, onlyPagesWithSearchTerms);
        contentLimitOptions.put(STAR_PAGES_NAME, starPages);
        contentLimitOptions.put(DUAL_COLUMNS_FOR_CASES_NAME, dualColumnsForCases);
        contentLimitOptions.put(DUAL_COLUMNS_FOR_NEWSLETTERS_NAME, dualColumnsForNewsletters);
        contentLimitOptions.put(SYNOPSIS_AND_HEADNOTES_ONLY_NAME, synopsisAndHeadnotesOnly);
        contentLimitOptions.put(STATUTORY_TEXT_ONLY_NAME, statutoryTextOnly);
        contentLimitOptions.put(SHOULD_FOLDER_NAME, shouldFolder);
        return contentLimitOptions;
    }

}
