package com.thomsonreuters.pageobjects.rest.model.response.delivery.completeDeliveryInitialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.response.ABaseResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompleteDeliveryInitializationResponse extends ABaseResponse {

    @JsonProperty("TransactionId")
    private String transactionId;

    @JsonProperty("InitiateSuccessful")
    private boolean initiateSuccessful;

    @JsonProperty("InitializationPending")
    private boolean initializationPending;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isInitiateSuccessful() {
        return initiateSuccessful;
    }

    public void setInitiateSuccessful(boolean initiateSuccessful) {
        this.initiateSuccessful = initiateSuccessful;
    }

    public boolean isInitializationPending() {
        return initializationPending;
    }

    public void setInitializationPending(boolean initializationPending) {
        this.initializationPending = initializationPending;
    }
}
