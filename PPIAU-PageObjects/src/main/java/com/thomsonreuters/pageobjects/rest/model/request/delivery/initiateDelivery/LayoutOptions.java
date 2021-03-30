package com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

class LayoutOptions extends ABaseRequest {

    private static final String HEADNOTES_NAME = "Headnotes";
    private static final String RIGHT_NOTE_MARGIN_NAME = "RightNoteMargin";
    private static final String WEST_LAW_LINKS_NAME = "WestlawLinks";
    private static final String TERM_HIGHLIGHTING_NAME = "TermHighlighting";
    private static final String COVER_PAGE_NAME = "CoverPage";
    private static final String SNIPPETS_WITHIN_LISTS_NAME = "SnippetsWithinLists";
    private static final String COVER_PAGE_COMMENT_NAME = "CoverPageComment";
    private static final String KEY_CITE_TREATMENT_NAME = "KeyCiteTreatment";
    private static final String ORIGINAL_IMAGE_LINK_NAME = "OriginalImageLink";
    private static final String SELECTED_FOOTNOTES_FORMAT_NAME = "SelectedFootnotesFormat";
    private static final String LINK_COLOR_NAME = "LinkColor";
    private static final String LINK_UNDERLINE_NAME = "LinkUnderline";
    private static final String FONT_SIZE_NAME = "FontSize";
    private static final String FONT_NAME_NAME = "FontName";
    private static final String DELIVERY_DUPLICATES_NAME = "DeliveryDuplicates";
    private static final String STAR_PAGE_RANGES_NAME = "StarPageRanges";
    private static final String TABLE_OF_CONTENTS_NAME = "TableOfContents";
    private static final String REPORT_TABLE_OF_CONTENTS_NAME = "ReportTableOfContents";
    private static final String CLIENT_ID_NAME = "ClientId";
    private static final String CONFIDENCE_SCORES_NAME = "ConfidenceScores";
    private static final String INCLUDE_INLINE_KEY_CITE_FLAGS_NAME = "IncludeInlineKeyCiteFlags";
    private static final String INCLUDE_MARKMAN_DISPLAY_NAME = "IncludeMarkmanDisplay";
    private static final String INCLUDE_ABRIDGMENT_CLASSIFICATION_NAME = "IncludeAbridgmentClassification";
    private static final String INCLUDE_CASE_ANNOTATION_NAME = "IncludeCaseAnnotation";
    private static final String INCLUDE_NON_WEST_HEADNOTES_NAME = "IncludeNonWestHeadnotes";
    private static final String INCLUDE_VERDICT_AND_SETTLEMENT_DOC_LIST_NAME = "IncludeVerdictAndSettlementDocList";
    private static final String INCLUDE_TRIAL_COURT_MEMORANDA_DOC_LIST_NAME = "IncludeTrialCourtMemorandaDocList";
    private static final String INCLUDE_APPELLATE_COURT_DOC_LIST_NAME = "IncludeAppellateCourtDocList";
    private static final String INCLUDE_APPELLATE_DECISIONS_DOC_LIST_NAME = "IncludeAppellateDecisionsDocList";
    private static final String INCLUDE_MEDICAL_ILLUSTRATIONS_DOC_LIST_NAME = "IncludeMedicalIllustrationsDocList";
    private static final String INCLUDE_MEDICAL_RESOURCES_DOC_LIST_NAME = "IncludeMedicalResourcesDocList";

    private String headnotes = "None";
    private boolean rightNoteMargin = false;
    private boolean westlawLinks = false;
    private boolean termHighlighting = false;
    private boolean coverPage = false;
    private boolean snippetsWithinLists = false;
    private boolean coverPageComment = false;
    private boolean keyCiteTreatment = false;
    private boolean originalImageLink = false;
    private String selectedFootnotesFormat = "EndOfDocument";
    private String linkColor = "Blue";
    private boolean linkUnderline = false;
    private String fontSize = "Normal";
    private String fontName = "Times New Roman";
    private String deliveryDuplicates = "Identify";
    private String starPageRanges = "";
    private boolean tableOfContents = true;
    private boolean reportTableOfContents = true;
    private boolean clientId = true;
    private boolean confidenceScores = true;
    private boolean includeInlineKeyCiteFlags = false;
    private boolean includeMarkmanDisplay = false;
    private boolean includeAbridgmentClassification = false;
    private boolean includeCaseAnnotation = false;
    private boolean includeNonWestHeadnotes = false;
    private boolean includeVerdictAndSettlementDocList = false;
    private boolean includeTrialCourtMemorandaDocList = false;
    private boolean includeAppellateCourtDocList = false;
    private boolean includeAppellateDecisionsDocList = false;
    private boolean includeMedicalIllustrationsDocList = false;
    private boolean includeMedicalResourcesDocList = false;

    public LayoutOptions setHeadnotes(String headnotes) {
        this.headnotes = headnotes;
        return this;
    }

    public LayoutOptions setRightNoteMargin(boolean rightNoteMargin) {
        this.rightNoteMargin = rightNoteMargin;
        return this;
    }

    public LayoutOptions setWestlawLinks(boolean westlawLinks) {
        this.westlawLinks = westlawLinks;
        return this;
    }

    public LayoutOptions setTermHighlighting(boolean termHighlighting) {
        this.termHighlighting = termHighlighting;
        return this;
    }

    public LayoutOptions setCoverPage(boolean coverPage) {
        this.coverPage = coverPage;
        return this;
    }

    public LayoutOptions setSnippetsWithinLists(boolean snippetsWithinLists) {
        this.snippetsWithinLists = snippetsWithinLists;
        return this;
    }

    public LayoutOptions setCoverPageComment(boolean coverPageComment) {
        this.coverPageComment = coverPageComment;
        return this;
    }

    public LayoutOptions setKeyCiteTreatment(boolean keyCiteTreatment) {
        this.keyCiteTreatment = keyCiteTreatment;
        return this;
    }

    public LayoutOptions setOriginalImageLink(boolean originalImageLink) {
        this.originalImageLink = originalImageLink;
        return this;
    }

    public LayoutOptions setSelectedFootnotesFormat(String selectedFootnotesFormat) {
        this.selectedFootnotesFormat = selectedFootnotesFormat;
        return this;
    }

    public LayoutOptions setLinkColor(String linkColor) {
        this.linkColor = linkColor;
        return this;
    }

    public LayoutOptions setLinkUnderline(boolean linkUnderline) {
        this.linkUnderline = linkUnderline;
        return this;
    }

    public LayoutOptions setFontSize(String fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public LayoutOptions setFontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public LayoutOptions setDeliveryDuplicates(String deliveryDuplicates) {
        this.deliveryDuplicates = deliveryDuplicates;
        return this;
    }

    public LayoutOptions setStarPageRanges(String starPageRanges) {
        this.starPageRanges = starPageRanges;
        return this;
    }

    public LayoutOptions setTableOfContents(boolean tableOfContents) {
        this.tableOfContents = tableOfContents;
        return this;
    }

    public LayoutOptions setReportTableOfContents(boolean reportTableOfContents) {
        this.reportTableOfContents = reportTableOfContents;
        return this;
    }

    public LayoutOptions setClientId(boolean clientId) {
        this.clientId = clientId;
        return this;
    }

    public LayoutOptions setConfidenceScores(boolean confidenceScores) {
        this.confidenceScores = confidenceScores;
        return this;
    }

    public LayoutOptions setIncludeInlineKeyCiteFlags(boolean includeInlineKeyCiteFlags) {
        this.includeInlineKeyCiteFlags = includeInlineKeyCiteFlags;
        return this;
    }

    public LayoutOptions setIncludeMarkmanDisplay(boolean includeMarkmanDisplay) {
        this.includeMarkmanDisplay = includeMarkmanDisplay;
        return this;
    }

    public LayoutOptions setIncludeAbridgmentClassification(boolean includeAbridgmentClassification) {
        this.includeAbridgmentClassification = includeAbridgmentClassification;
        return this;
    }

    public LayoutOptions setIncludeCaseAnnotation(boolean includeCaseAnnotation) {
        this.includeCaseAnnotation = includeCaseAnnotation;
        return this;
    }

    public LayoutOptions setIncludeNonWestHeadnotes(boolean includeNonWestHeadnotes) {
        this.includeNonWestHeadnotes = includeNonWestHeadnotes;
        return this;
    }

    public LayoutOptions setIncludeVerdictAndSettlementDocList(boolean includeVerdictAndSettlementDocList) {
        this.includeVerdictAndSettlementDocList = includeVerdictAndSettlementDocList;
        return this;
    }

    public LayoutOptions setIncludeTrialCourtMemorandaDocList(boolean includeTrialCourtMemorandaDocList) {
        this.includeTrialCourtMemorandaDocList = includeTrialCourtMemorandaDocList;
        return this;
    }

    public LayoutOptions setIncludeAppellateCourtDocList(boolean includeAppellateCourtDocList) {
        this.includeAppellateCourtDocList = includeAppellateCourtDocList;
        return this;
    }

    public LayoutOptions setIncludeAppellateDecisionsDocList(boolean includeAppellateDecisionsDocList) {
        this.includeAppellateDecisionsDocList = includeAppellateDecisionsDocList;
        return this;
    }

    public LayoutOptions setIncludeMedicalIllustrationsDocList(boolean includeMedicalIllustrationsDocList) {
        this.includeMedicalIllustrationsDocList = includeMedicalIllustrationsDocList;
        return this;
    }

    public LayoutOptions setIncludeMedicalResourcesDocList(boolean includeMedicalResourcesDocList) {
        this.includeMedicalResourcesDocList = includeMedicalResourcesDocList;
        return this;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode layoutOptions = objectMapper.createObjectNode();
        layoutOptions.put(HEADNOTES_NAME, headnotes);
        layoutOptions.put(RIGHT_NOTE_MARGIN_NAME, rightNoteMargin);
        layoutOptions.put(WEST_LAW_LINKS_NAME, westlawLinks);
        layoutOptions.put(TERM_HIGHLIGHTING_NAME, termHighlighting);
        layoutOptions.put(COVER_PAGE_NAME, coverPage);
        layoutOptions.put(SNIPPETS_WITHIN_LISTS_NAME, snippetsWithinLists);
        layoutOptions.put(COVER_PAGE_COMMENT_NAME, coverPageComment);
        layoutOptions.put(KEY_CITE_TREATMENT_NAME, keyCiteTreatment);
        layoutOptions.put(ORIGINAL_IMAGE_LINK_NAME, originalImageLink);
        layoutOptions.put(SELECTED_FOOTNOTES_FORMAT_NAME, selectedFootnotesFormat);
        layoutOptions.put(LINK_COLOR_NAME, linkColor);
        layoutOptions.put(LINK_UNDERLINE_NAME, linkUnderline);
        layoutOptions.put(FONT_SIZE_NAME, fontSize);
        layoutOptions.put(FONT_NAME_NAME, fontName);
        layoutOptions.put(DELIVERY_DUPLICATES_NAME, deliveryDuplicates);
        layoutOptions.put(STAR_PAGE_RANGES_NAME, starPageRanges);
        layoutOptions.put(TABLE_OF_CONTENTS_NAME, tableOfContents);
        layoutOptions.put(REPORT_TABLE_OF_CONTENTS_NAME, reportTableOfContents);
        layoutOptions.put(CLIENT_ID_NAME, clientId);
        layoutOptions.put(CONFIDENCE_SCORES_NAME, confidenceScores);
        layoutOptions.put(INCLUDE_INLINE_KEY_CITE_FLAGS_NAME, includeInlineKeyCiteFlags);
        layoutOptions.put(INCLUDE_MARKMAN_DISPLAY_NAME, includeMarkmanDisplay);
        layoutOptions.put(INCLUDE_ABRIDGMENT_CLASSIFICATION_NAME, includeAbridgmentClassification);
        layoutOptions.put(INCLUDE_CASE_ANNOTATION_NAME, includeCaseAnnotation);
        layoutOptions.put(INCLUDE_NON_WEST_HEADNOTES_NAME, includeNonWestHeadnotes);
        layoutOptions.put(INCLUDE_VERDICT_AND_SETTLEMENT_DOC_LIST_NAME, includeVerdictAndSettlementDocList);
        layoutOptions.put(INCLUDE_TRIAL_COURT_MEMORANDA_DOC_LIST_NAME, includeTrialCourtMemorandaDocList);
        layoutOptions.put(INCLUDE_APPELLATE_COURT_DOC_LIST_NAME, includeAppellateCourtDocList);
        layoutOptions.put(INCLUDE_APPELLATE_DECISIONS_DOC_LIST_NAME, includeAppellateDecisionsDocList);
        layoutOptions.put(INCLUDE_MEDICAL_ILLUSTRATIONS_DOC_LIST_NAME, includeMedicalIllustrationsDocList);
        layoutOptions.put(INCLUDE_MEDICAL_RESOURCES_DOC_LIST_NAME, includeMedicalResourcesDocList);
        return layoutOptions;
    }
}
