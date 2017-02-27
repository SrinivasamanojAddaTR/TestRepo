package com.thomsonreuters.pageobjects.rest.model.response.delivery.initiateDelivery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.common.delivery.AllDeliveryItems;
import com.thomsonreuters.pageobjects.rest.model.response.ABaseResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InitiateDeliveryResponse extends ABaseResponse {

    @JsonProperty("TransactionId")
    private String transactionId;

    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("AllDeliveryItems")
    private List<AllDeliveryItems> allDeliveryItems;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<AllDeliveryItems> getAllDeliveryItems() {
        return allDeliveryItems;
    }

    public void setAllDeliveryItems(List<AllDeliveryItems> allDeliveryItems) {
        this.allDeliveryItems = allDeliveryItems;
    }
}
