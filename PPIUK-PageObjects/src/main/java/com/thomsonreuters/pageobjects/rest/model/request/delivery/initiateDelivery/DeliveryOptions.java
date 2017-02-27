package com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

/**
 * Created by Pavel_Ardenka on 27.10.2015.
 */
class DeliveryOptions extends ABaseRequest {

    private static final String VERSION_NAME = "Version";
    private static final String WEBSITE_REQUEST_ID_NAME = "WebsiteRequestId";
    private static final String EMAIL_AND_PRINT_NAME = "EmailAndPrint";
    private static final String DETAIL_LEVEL_NAME = "DetailLevel";
    private static final String LIST_AS_FULL_TEXT_NAME = "ListAsFullText";
    private static final String ITEMS_TO_DELIVER_NAME = "ItemsToDeliver";
    private static final String ANNOTATIONS_SELECTION_NAME = "AnnotationsSelection";
    private static final String INCLUDE_PUBLIC_ANNOTATIONS_NAME = "IncludePublicAnnotations";
    private static final String WHAT_TO_DELIVER_OPTION_NAME = "WhatToDeliverOption";
    private static final String FORMAT_NAME = "Format";
    private static final String STANDALONE_PRINTER_ID_NAME = "StandalonePrinterId";
    private static final String FILE_CONTAINER_NAME = "FileContainer";
    private static final String IS_ORIGINAL_USER_DOCUMENT_NAME = "IsOriginalUserDocument";
    private static final String INCLUDE_EXECUTIVE_SUMMARY_NAME = "IncludeExecutiveSummary";
    private static final String ALERTS_AUTO_ACCEPT_OUT_OF_PLAN_NAME = "AlertsAutoAcceptOutOfPlan";
    private static final String ALERTS_DELIVERY_ITEMS_AS_LIST_NAME = "AlertsDeliveryItemsAsList";
    private static final String ALERTS_DELIVERY_ITEMS_AS_FULL_TEXT_NAME = "AlertsDeliveryItemsAsFullText";
    private static final String HAS_SEEN_CONTENT_TO_APPEND_TAB_NAME = "HasSeenContentToAppendTab";
    private static final String BRIEF_IT_NAME = "BriefIt";
    private static final String RECIPIENT_OPTIONS_NAME = "RecipientOptions";
    private static final String LAYOUT_OPTIONS_NAME = "LayoutOptions";
    private static final String CONTENT_LIMIT_OPTIONS_NAME = "ContentLimitOptions";
    private static final String ADDITIONAL_CONTENT_OPTIONS_NAME = "AdditionalContentOptions";

    // Default values
    private int version = 0;
    private String websiteRequestId = "";
    private boolean emailAndPrint = false;
    private String detailLevel = "0";
    private boolean listAsFullText = true;
    private int itemsToDeliver = 1;
    private String annotationsSelection = "DocumentAndPersonalAnnotations";
    private boolean includePublicAnnotations = true;
    private String whatToDeliverOption = "DocumentAndPersonalAnnotations";
    private String format = "Pdf";
    private String standalonePrinterId = "";
    private String fileContainer = "SingleMergedFile";
    private boolean isOriginalUserDocument = false;
    private String includeExecutiveSummary = "false";
    private boolean alertsAutoAcceptOutOfPlan = false;
    private int alertsDeliveryItemsAsList = 0;
    private int alertsDeliveryItemsAsFullText = 0;
    private boolean hasSeenContentToAppendTab = false;
    private boolean briefIt = false;
    private RecipientOptions recipientOptions = new RecipientOptions();
    private LayoutOptions layoutOptions = new LayoutOptions();
    private ContentLimitOptions contentLimitOptions = new ContentLimitOptions();
    private AdditionalContentOptions additionalContentOptions = new AdditionalContentOptions();

    public DeliveryOptions setVersion(int version) {
        this.version = version;
        return this;
    }

    public DeliveryOptions setWebsiteRequestId(String websiteRequestId) {
        this.websiteRequestId = websiteRequestId;
        return this;
    }

    public DeliveryOptions setEmailAndPrint(boolean emailAndPrint) {
        this.emailAndPrint = emailAndPrint;
        return this;
    }

    public DeliveryOptions setDetailLevel(String detailLevel) {
        this.detailLevel = detailLevel;
        return this;
    }

    public DeliveryOptions setListAsFullText(boolean listAsFullText) {
        this.listAsFullText = listAsFullText;
        return this;
    }

    public DeliveryOptions setItemsToDeliver(int itemsToDeliver) {
        this.itemsToDeliver = itemsToDeliver;
        return this;
    }

    public DeliveryOptions setAnnotationsSelection(String annotationsSelection) {
        this.annotationsSelection = annotationsSelection;
        return this;
    }

    public DeliveryOptions setIncludePublicAnnotations(boolean includePublicAnnotations) {
        this.includePublicAnnotations = includePublicAnnotations;
        return this;
    }

    public DeliveryOptions setWhatToDeliverOption(String whatToDeliverOption) {
        this.whatToDeliverOption = whatToDeliverOption;
        return this;
    }

    public DeliveryOptions setFormat(String format) {
        this.format = format;
        return this;
    }

    public DeliveryOptions setStandalonePrinterId(String standalonePrinterId) {
        this.standalonePrinterId = standalonePrinterId;
        return this;
    }

    public DeliveryOptions setFileContainer(String fileContainer) {
        this.fileContainer = fileContainer;
        return this;
    }

    public DeliveryOptions setIsOriginalUserDocument(boolean isOriginalUserDocument) {
        this.isOriginalUserDocument = isOriginalUserDocument;
        return this;
    }

    public DeliveryOptions setIncludeExecutiveSummary(String includeExecutiveSummary) {
        this.includeExecutiveSummary = includeExecutiveSummary;
        return this;
    }

    public DeliveryOptions setAlertsAutoAcceptOutOfPlan(boolean alertsAutoAcceptOutOfPlan) {
        this.alertsAutoAcceptOutOfPlan = alertsAutoAcceptOutOfPlan;
        return this;
    }

    public DeliveryOptions setAlertsDeliveryItemsAsList(int alertsDeliveryItemsAsList) {
        this.alertsDeliveryItemsAsList = alertsDeliveryItemsAsList;
        return this;
    }

    public DeliveryOptions setAlertsDeliveryItemsAsFullText(int alertsDeliveryItemsAsFullText) {
        this.alertsDeliveryItemsAsFullText = alertsDeliveryItemsAsFullText;
        return this;
    }

    public DeliveryOptions setHasSeenContentToAppendTab(boolean hasSeenContentToAppendTab) {
        this.hasSeenContentToAppendTab = hasSeenContentToAppendTab;
        return this;
    }

    public DeliveryOptions setBriefIt(boolean briefIt) {
        this.briefIt = briefIt;
        return this;
    }

    public DeliveryOptions setRecipientOptions(RecipientOptions recipientOptions) {
        this.recipientOptions = recipientOptions;
        return this;
    }

    public DeliveryOptions setLayoutOptions(LayoutOptions layoutOptions) {
        this.layoutOptions = layoutOptions;
        return this;
    }

    public DeliveryOptions setContentLimitOptions(ContentLimitOptions contentLimitOptions) {
        this.contentLimitOptions = contentLimitOptions;
        return this;
    }

    public DeliveryOptions setAdditionalContentOptions(AdditionalContentOptions additionalContentOptions) {
        this.additionalContentOptions = additionalContentOptions;
        return this;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode deliveryOptionsNode = objectMapper.createObjectNode();
        deliveryOptionsNode.put(VERSION_NAME, version);
        deliveryOptionsNode.put(WEBSITE_REQUEST_ID_NAME, websiteRequestId);
        deliveryOptionsNode.put(EMAIL_AND_PRINT_NAME, emailAndPrint);
        deliveryOptionsNode.put(DETAIL_LEVEL_NAME, detailLevel);
        deliveryOptionsNode.put(LIST_AS_FULL_TEXT_NAME, listAsFullText);
        deliveryOptionsNode.put(ITEMS_TO_DELIVER_NAME, itemsToDeliver);
        deliveryOptionsNode.put(ANNOTATIONS_SELECTION_NAME, annotationsSelection);
        deliveryOptionsNode.put(INCLUDE_PUBLIC_ANNOTATIONS_NAME, includePublicAnnotations);
        deliveryOptionsNode.put(WHAT_TO_DELIVER_OPTION_NAME, whatToDeliverOption);
        deliveryOptionsNode.put(FORMAT_NAME, format);
        deliveryOptionsNode.put(STANDALONE_PRINTER_ID_NAME, standalonePrinterId);
        deliveryOptionsNode.put(FILE_CONTAINER_NAME, fileContainer);
        deliveryOptionsNode.put(IS_ORIGINAL_USER_DOCUMENT_NAME, isOriginalUserDocument);
        deliveryOptionsNode.put(INCLUDE_EXECUTIVE_SUMMARY_NAME, includeExecutiveSummary);
        deliveryOptionsNode.put(ALERTS_AUTO_ACCEPT_OUT_OF_PLAN_NAME, alertsAutoAcceptOutOfPlan);
        deliveryOptionsNode.put(ALERTS_DELIVERY_ITEMS_AS_LIST_NAME, alertsDeliveryItemsAsList);
        deliveryOptionsNode.put(ALERTS_DELIVERY_ITEMS_AS_FULL_TEXT_NAME, alertsDeliveryItemsAsFullText);
        deliveryOptionsNode.put(HAS_SEEN_CONTENT_TO_APPEND_TAB_NAME, hasSeenContentToAppendTab);
        deliveryOptionsNode.put(BRIEF_IT_NAME, briefIt);
        deliveryOptionsNode.set(RECIPIENT_OPTIONS_NAME, recipientOptions.getNode());
        deliveryOptionsNode.set(LAYOUT_OPTIONS_NAME, layoutOptions.getNode());
        deliveryOptionsNode.set(CONTENT_LIMIT_OPTIONS_NAME, contentLimitOptions.getNode());
        deliveryOptionsNode.set(ADDITIONAL_CONTENT_OPTIONS_NAME, additionalContentOptions.getNode());
        return deliveryOptionsNode;
    }
}
