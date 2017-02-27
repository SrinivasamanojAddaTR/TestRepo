package com.thomsonreuters.pageobjects.rest.model.request.delivery.completeDeliveryInitialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.common.delivery.AllDeliveryItems;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 18.11.2015.
 */
public class CompleteDeliveryInitializationRequest extends ABaseRequest {

    private static final String INITIATE_SUCCESSFUL = "InitiateSuccessful";
    private static final String INITIALIZATION_PENDING = "InitializationPending";
    private static final String REQUEST_ID = "RequestId";
    private static final String TOTAL_POSSIBLE_COUNT = "TotalPossibleCount";
    private static final String TOTAL_BLOCKED_DOCUMENT_COUNT = "TotalBlockedDocumentCount";
    private static final String TOTAL_BLOCKED_ON_PRODUCT_COUNT = "TotalBlockedOnProductCount";
    private static final String ERROR_DOCUMENT_COUNT = "ErrorDocumentCount";
    private static final String TOTAL_RESTRICTED_DOCUMENT_COUNT = "TotalRestrictedDocumentCount";
    private static final String DELIVERY_BLOCKED = "DeliveryBlocked";
    private static final String PLAN_EXCEPTION_ITEMS = "PlanExceptionItems";
    private static final String ITEMS_BLOCKED_OR_WITH_ERROR = "ItemsBlockedOrWithError";
    private static final String TOO_LARGE_ITEMS = "TooLargeItems";
    private static final String MAXIMUM_TOTAL_DELIVERY_SIZE_EXCEEDED = "MaximumTotalDeliverySizeExceeded";
    private static final String ITEMS_RESTRICTED = "ItemsRestricted";
    private static final String CONTAINS_SURCHARGE_WARNING = "ContainsSurchargeWarning";
    private static final String DELIVERY_CHECK_LINE_LIMIT = "DeliveryCheckLineLimit";
    private static final String CONTAINS_BILL_TRACKING_ADDENDUMS = "ContainsBillTrackingAddendums";
    private static final String PATRON_ACCESS_DELIVERY_LIMITED = "PatronAccessDeliveryLimited";
    private static final String PLAN_EXCEPTION_ORCHESTRATIONS = "PlanExceptionOrchestrations";
    private static final String PLAN_EXCEPTION_ITEM = "PlanExceptionItem";
    private static final String ALL_DELIVERY_ITEMS = "AllDeliveryItems";

    // Default values
    private boolean initiateSuccessful = false;
    private boolean initializationPending = true;
    private String requestId = "";
    private int totalPossibleCount = 0;
    private int totalBlockedDocumentCount = 0;
    private int totalBlockedOnProductCount = 0;
    private int errorDocumentCount = 0;
    private int totalRestrictedDocumentCount = 0;
    private boolean deliveryBlocked = false;
    private List<String> planExceptionItems = new ArrayList<>(); // List of STRINGS?
    private List<String> itemsBlockedOrWithError = new ArrayList<>(); // List of STRINGS?
    private List<String> tooLargeItems = new ArrayList<>(); // List of STRINGS?
    private boolean maximumTotalDeliverySizeExceeded = false;
    private List<String> itemsRestricted = new ArrayList<>(); // List of STRINGS?
    private boolean containsSurchargeWarning = false;
    private boolean deliveryCheckLineLimit = false;
    private boolean containsBillTrackingAddendums = false;
    private boolean patronAccessDeliveryLimited = false;
    private List<String> planExceptionOrchestrations = new ArrayList<>(); // List of STRINGS?
    private List<AllDeliveryItems> allDeliveryItems;

    public CompleteDeliveryInitializationRequest setInitiateSuccessful(boolean initiateSuccessful) {
        this.initiateSuccessful = initiateSuccessful;
        return this;
    }

    public CompleteDeliveryInitializationRequest setInitializationPending(boolean initializationPending) {
        this.initializationPending = initializationPending;
        return this;
    }

    public CompleteDeliveryInitializationRequest setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public CompleteDeliveryInitializationRequest setTotalPossibleCount(int totalPossibleCount) {
        this.totalPossibleCount = totalPossibleCount;
        return this;
    }

    public CompleteDeliveryInitializationRequest setTotalBlockedDocumentCount(int totalBlockedDocumentCount) {
        this.totalBlockedDocumentCount = totalBlockedDocumentCount;
        return this;
    }

    public CompleteDeliveryInitializationRequest setTotalBlockedOnProductCount(int totalBlockedOnProductCount) {
        this.totalBlockedOnProductCount = totalBlockedOnProductCount;
        return this;
    }

    public CompleteDeliveryInitializationRequest setErrorDocumentCount(int errorDocumentCount) {
        this.errorDocumentCount = errorDocumentCount;
        return this;
    }

    public CompleteDeliveryInitializationRequest setTotalRestrictedDocumentCount(int totalRestrictedDocumentCount) {
        this.totalRestrictedDocumentCount = totalRestrictedDocumentCount;
        return this;
    }

    public CompleteDeliveryInitializationRequest setDeliveryBlocked(boolean deliveryBlocked) {
        this.deliveryBlocked = deliveryBlocked;
        return this;
    }

    public CompleteDeliveryInitializationRequest setPlanExceptionItems(List<String> planExceptionItems) {
        this.planExceptionItems = planExceptionItems;
        return this;
    }

    public CompleteDeliveryInitializationRequest setItemsBlockedOrWithError(List<String> itemsBlockedOrWithError) {
        this.itemsBlockedOrWithError = itemsBlockedOrWithError;
        return this;
    }

    public CompleteDeliveryInitializationRequest setTooLargeItems(List<String> tooLargeItems) {
        this.tooLargeItems = tooLargeItems;
        return this;
    }

    public CompleteDeliveryInitializationRequest setMaximumTotalDeliverySizeExceeded(boolean maximumTotalDeliverySizeExceeded) {
        this.maximumTotalDeliverySizeExceeded = maximumTotalDeliverySizeExceeded;
        return this;
    }

    public CompleteDeliveryInitializationRequest setItemsRestricted(List<String> itemsRestricted) {
        this.itemsRestricted = itemsRestricted;
        return this;
    }

    public CompleteDeliveryInitializationRequest setContainsSurchargeWarning(boolean containsSurchargeWarning) {
        this.containsSurchargeWarning = containsSurchargeWarning;
        return this;
    }

    public CompleteDeliveryInitializationRequest setDeliveryCheckLineLimit(boolean deliveryCheckLineLimit) {
        this.deliveryCheckLineLimit = deliveryCheckLineLimit;
        return this;
    }

    public CompleteDeliveryInitializationRequest setContainsBillTrackingAddendums(boolean containsBillTrackingAddendums) {
        this.containsBillTrackingAddendums = containsBillTrackingAddendums;
        return this;
    }

    public CompleteDeliveryInitializationRequest setPatronAccessDeliveryLimited(boolean patronAccessDeliveryLimited) {
        this.patronAccessDeliveryLimited = patronAccessDeliveryLimited; return this;
    }

    public CompleteDeliveryInitializationRequest setPlanExceptionOrchestrations(List<String> planExceptionOrchestrations) {
        this.planExceptionOrchestrations = planExceptionOrchestrations;
        return this;
    }

    public CompleteDeliveryInitializationRequest setAllDeliveryItems(List<AllDeliveryItems> allDeliveryItems) {
        this.allDeliveryItems = allDeliveryItems;
        return this;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode completeDeliveryInitNode = objectMapper.createObjectNode();
        completeDeliveryInitNode.put(INITIATE_SUCCESSFUL, initiateSuccessful);
        completeDeliveryInitNode.put(INITIALIZATION_PENDING, initializationPending);
        completeDeliveryInitNode.put(REQUEST_ID, requestId);
        completeDeliveryInitNode.put(TOTAL_POSSIBLE_COUNT, totalPossibleCount);
        completeDeliveryInitNode.put(TOTAL_BLOCKED_DOCUMENT_COUNT, totalBlockedDocumentCount);
        completeDeliveryInitNode.put(TOTAL_BLOCKED_ON_PRODUCT_COUNT, totalBlockedOnProductCount);
        completeDeliveryInitNode.put(ERROR_DOCUMENT_COUNT, errorDocumentCount);
        completeDeliveryInitNode.put(TOTAL_RESTRICTED_DOCUMENT_COUNT, totalRestrictedDocumentCount);
        completeDeliveryInitNode.put(DELIVERY_BLOCKED, deliveryBlocked);
        completeDeliveryInitNode.set(PLAN_EXCEPTION_ITEMS, objectMapper.valueToTree(planExceptionItems));
        completeDeliveryInitNode.set(ITEMS_BLOCKED_OR_WITH_ERROR, objectMapper.valueToTree(itemsBlockedOrWithError));
        completeDeliveryInitNode.set(TOO_LARGE_ITEMS, objectMapper.valueToTree(tooLargeItems));
        completeDeliveryInitNode.put(MAXIMUM_TOTAL_DELIVERY_SIZE_EXCEEDED, maximumTotalDeliverySizeExceeded);
        completeDeliveryInitNode.set(ITEMS_RESTRICTED, objectMapper.valueToTree(itemsRestricted));
        completeDeliveryInitNode.put(CONTAINS_SURCHARGE_WARNING, containsSurchargeWarning);
        completeDeliveryInitNode.put(DELIVERY_CHECK_LINE_LIMIT, deliveryCheckLineLimit);
        completeDeliveryInitNode.put(CONTAINS_BILL_TRACKING_ADDENDUMS, containsBillTrackingAddendums);
        completeDeliveryInitNode.set(PATRON_ACCESS_DELIVERY_LIMITED, objectMapper.valueToTree(patronAccessDeliveryLimited));
        completeDeliveryInitNode.set(PLAN_EXCEPTION_ORCHESTRATIONS, objectMapper.valueToTree(planExceptionOrchestrations));
        completeDeliveryInitNode.set(PLAN_EXCEPTION_ITEM, objectMapper.createObjectNode());
        completeDeliveryInitNode.set(ALL_DELIVERY_ITEMS, objectMapper.valueToTree(allDeliveryItems));
        return completeDeliveryInitNode;
    }
}
