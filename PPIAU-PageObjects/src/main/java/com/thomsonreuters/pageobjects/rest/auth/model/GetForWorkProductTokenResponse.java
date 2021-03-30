package com.thomsonreuters.pageobjects.rest.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetForWorkProductTokenResponse extends JsonObject {

    @JsonProperty("WorkProductToken")
    String workProductToken;

    public String getWorkProductToken() {
        return workProductToken;
    }

    public void setWorkProductToken(String workProductToken) {
        this.workProductToken = workProductToken;
    }

    @Override
    public String toString() {
        return "SixResponse{" + "workProductToken='" + workProductToken + '\'' + '}';
    }

}
