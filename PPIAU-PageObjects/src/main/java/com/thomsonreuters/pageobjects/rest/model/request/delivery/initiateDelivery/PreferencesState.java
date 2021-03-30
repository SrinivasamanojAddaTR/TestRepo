package com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

class PreferencesState extends ABaseRequest {

    private static final String KEY_CITE_TREATMENT_MODIFIED = "KeyCiteTreatmentModified";
    private static final String INCLUDE_HEADNOTES_MODIFIED = "IncludeHeadnotesModified";
    private static final String FOOTNOTES_MODIFIED = "FootnotesModified";
    private static final String FULL_KEY_NUMBER_HIERARCHY_MODIFIED = "FullKeyNumberHierarchyModified";
    private static final String ORIGINAL_IMAGE_LINK_MODIFIED = "OriginalImageLinkModified";
    private static final String RIGHT_NOTE_MARGIN_MODIFIED = "RightNoteMarginModified";
    private static final String TERM_HIGHLIGHTING_MODIFIED = "TermHighlightingModified";
    private static final String COVER_PAGE_MODIFIED = "CoverPageModified";
    private static final String DUAL_COLUMNS_MODIFIED = "DualColumnsModified";
    private static final String DUAL_COLUMNS_NEWSLETTERS_MODIFIED = "DualColumnsNewslettersModified";
    private static final String FILE_CONTAINER_MODIFIED = "FileContainerModified";
    private static final String FILE_FORMAT_MODIFIED = "FileFormatModified";
    private static final String ONLY_PAGES_WITH_TERMS_MODIFIED = "OnlyPagesWithTermsModified";
    private static final String EMAIL_ADDRESS_MODIFIED = "EmailAddressModified";
    private static final String EMAIL_C_C_ADDRESS_MODIFIED = "EmailCCAddressModified";
    private static final String WHAT_TO_DELIVER_MODIFIED = "WhatToDeliverModified";
    private static final String LINK_COLOR_MODIFIED = "LinkColorModified";
    private static final String LINK_UNDERLINE_MODIFIED = "LinkUnderlineModified";
    private static final String FONT_SIZE_MODIFIED = "FontSizeModified";
    private static final String FONT_NAME_MODIFIED = "FontNameModified";
    private static final String USER_SELECTED_STAND_ALONE_PRINTER = "UserSelectedStandAlonePrinter";
    private static final String STATUTORY_TEXT_ONLY_MODIFIED = "StatutoryTextOnlyModified";

    private boolean keyCiteTreatmentModified = false;
    private boolean includeHeadnotesModified = false;
    private boolean footnotesModified = false;
    private boolean fullKeyNumberHierarchyModified = false;
    private boolean originalImageLinkModified = false;
    private boolean rightNoteMarginModified = false;
    private boolean termHighlightingModified = false;
    private boolean coverPageModified = false;
    private boolean dualColumnsModified = false;
    private boolean dualColumnsNewslettersModified = false;
    private boolean fileContainerModified = false;
    private boolean fileFormatModified = false;
    private boolean onlyPagesWithTermsModified = false;
    private boolean emailAddressModified = false;
    private boolean emailCCAddressModified = false;
    private boolean whatToDeliverModified = false;
    private boolean linkColorModified = false;
    private boolean linkUnderlineModified = false;
    private boolean fontSizeModified = false;
    private boolean fontNameModified = false;
    private boolean userSelectedStandAlonePrinter = false;
    private boolean statutoryTextOnlyModified = false;

    public PreferencesState setKeyCiteTreatmentModified(boolean keyCiteTreatmentModified) {
        this.keyCiteTreatmentModified = keyCiteTreatmentModified;
        return this;
    }

    public PreferencesState setIncludeHeadnotesModified(boolean includeHeadnotesModified) {
        this.includeHeadnotesModified = includeHeadnotesModified;
        return this;
    }

    public PreferencesState setFootnotesModified(boolean footnotesModified) {
        this.footnotesModified = footnotesModified;
        return this;
    }

    public PreferencesState setFullKeyNumberHierarchyModified(boolean fullKeyNumberHierarchyModified) {
        this.fullKeyNumberHierarchyModified = fullKeyNumberHierarchyModified;
        return this;
    }

    public PreferencesState setOriginalImageLinkModified(boolean originalImageLinkModified) {
        this.originalImageLinkModified = originalImageLinkModified;
        return this;
    }

    public PreferencesState setRightNoteMarginModified(boolean rightNoteMarginModified) {
        this.rightNoteMarginModified = rightNoteMarginModified;
        return this;
    }

    public PreferencesState setTermHighlightingModified(boolean termHighlightingModified) {
        this.termHighlightingModified = termHighlightingModified;
        return this;
    }

    public PreferencesState setCoverPageModified(boolean coverPageModified) {
        this.coverPageModified = coverPageModified;
        return this;
    }

    public PreferencesState setDualColumnsModified(boolean dualColumnsModified) {
        this.dualColumnsModified = dualColumnsModified;
        return this;
    }

    public PreferencesState setDualColumnsNewslettersModified(boolean dualColumnsNewslettersModified) {
        this.dualColumnsNewslettersModified = dualColumnsNewslettersModified;
        return this;
    }

    public PreferencesState setFileContainerModified(boolean fileContainerModified) {
        this.fileContainerModified = fileContainerModified;
        return this;
    }

    public PreferencesState setFileFormatModified(boolean fileFormatModified) {
        this.fileFormatModified = fileFormatModified;
        return this;
    }

    public PreferencesState setOnlyPagesWithTermsModified(boolean onlyPagesWithTermsModified) {
        this.onlyPagesWithTermsModified = onlyPagesWithTermsModified;
        return this;
    }

    public PreferencesState setEmailAddressModified(boolean emailAddressModified) {
        this.emailAddressModified = emailAddressModified;
        return this;
    }

    public PreferencesState setEmailCCAddressModified(boolean emailCCAddressModified) {
        this.emailCCAddressModified = emailCCAddressModified;
        return this;
    }

    public PreferencesState setWhatToDeliverModified(boolean whatToDeliverModified) {
        this.whatToDeliverModified = whatToDeliverModified;
        return this;
    }

    public PreferencesState setLinkColorModified(boolean linkColorModified) {
        this.linkColorModified = linkColorModified;
        return this;
    }

    public PreferencesState setLinkUnderlineModified(boolean linkUnderlineModified) {
        this.linkUnderlineModified = linkUnderlineModified;
        return this;
    }

    public PreferencesState setFontSizeModified(boolean fontSizeModified) {
        this.fontSizeModified = fontSizeModified;
        return this;
    }

    public PreferencesState setFontNameModified(boolean fontNameModified) {
        this.fontNameModified = fontNameModified;
        return this;
    }

    public PreferencesState setUserSelectedStandAlonePrinter(boolean userSelectedStandAlonePrinter) {
        this.userSelectedStandAlonePrinter = userSelectedStandAlonePrinter;
        return this;
    }

    public PreferencesState setStatutoryTextOnlyModified(boolean statutoryTextOnlyModified) {
        this.statutoryTextOnlyModified = statutoryTextOnlyModified;
        return this;
    }


    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode preferencesStateNode = objectMapper.createObjectNode();
        preferencesStateNode.put(KEY_CITE_TREATMENT_MODIFIED, keyCiteTreatmentModified);
        preferencesStateNode.put(INCLUDE_HEADNOTES_MODIFIED, includeHeadnotesModified);
        preferencesStateNode.put(FOOTNOTES_MODIFIED, footnotesModified);
        preferencesStateNode.put(FULL_KEY_NUMBER_HIERARCHY_MODIFIED, fullKeyNumberHierarchyModified);
        preferencesStateNode.put(ORIGINAL_IMAGE_LINK_MODIFIED, originalImageLinkModified);
        preferencesStateNode.put(RIGHT_NOTE_MARGIN_MODIFIED, rightNoteMarginModified);
        preferencesStateNode.put(TERM_HIGHLIGHTING_MODIFIED, termHighlightingModified);
        preferencesStateNode.put(COVER_PAGE_MODIFIED, coverPageModified);
        preferencesStateNode.put(DUAL_COLUMNS_MODIFIED, dualColumnsModified);
        preferencesStateNode.put(DUAL_COLUMNS_NEWSLETTERS_MODIFIED, dualColumnsNewslettersModified);
        preferencesStateNode.put(FILE_CONTAINER_MODIFIED, fileContainerModified);
        preferencesStateNode.put(FILE_FORMAT_MODIFIED, fileFormatModified);
        preferencesStateNode.put(ONLY_PAGES_WITH_TERMS_MODIFIED, onlyPagesWithTermsModified);
        preferencesStateNode.put(EMAIL_ADDRESS_MODIFIED, emailAddressModified);
        preferencesStateNode.put(EMAIL_C_C_ADDRESS_MODIFIED, emailCCAddressModified);
        preferencesStateNode.put(WHAT_TO_DELIVER_MODIFIED, whatToDeliverModified);
        preferencesStateNode.put(LINK_COLOR_MODIFIED, linkColorModified);
        preferencesStateNode.put(LINK_UNDERLINE_MODIFIED, linkUnderlineModified);
        preferencesStateNode.put(FONT_SIZE_MODIFIED, fontSizeModified);
        preferencesStateNode.put(FONT_NAME_MODIFIED, fontNameModified);
        preferencesStateNode.put(USER_SELECTED_STAND_ALONE_PRINTER, userSelectedStandAlonePrinter);
        preferencesStateNode.put(STATUTORY_TEXT_ONLY_MODIFIED, statutoryTextOnlyModified);
        return preferencesStateNode;
    }
}
