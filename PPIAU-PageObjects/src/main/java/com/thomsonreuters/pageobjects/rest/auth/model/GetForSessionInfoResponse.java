package com.thomsonreuters.pageobjects.rest.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetForSessionInfoResponse extends JsonObject {

    @JsonProperty("SessionId")
    String SessionId;

    @JsonProperty("ProductView")
    String productView;

    public String getProductView() {
        return productView;
    }

    public void setProductView(String productView) {
        this.productView = productView;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

}
