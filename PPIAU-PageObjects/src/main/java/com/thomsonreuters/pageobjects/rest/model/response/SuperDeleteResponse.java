package com.thomsonreuters.pageobjects.rest.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperDeleteResponse extends JsonObject {

    private String status;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
