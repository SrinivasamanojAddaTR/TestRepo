package com.thomsonreuters.pageobjects.rest.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostForUserGuidResponse extends JsonObject {

    @JsonProperty("EndDate")
    String endDate;

    @JsonProperty("PrismAuthStatusCode")
    int prismAuthStatusCode;

    @JsonProperty("PrismAuthToken")
    String prismAuthToken;

    @JsonProperty("PrismGuid")
    String prismGuid;

    @JsonProperty("PrismUserId")
    String prismUserId;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPrismAuthStatusCode() {
        return prismAuthStatusCode;
    }

    public void setPrismAuthStatusCode(int prismAuthStatusCode) {
        this.prismAuthStatusCode = prismAuthStatusCode;
    }

    public String getPrismAuthToken() {
        return prismAuthToken;
    }

    public void setPrismAuthToken(String prismAuthToken) {
        this.prismAuthToken = prismAuthToken;
    }

    public String getPrismGuid() {
        return prismGuid;
    }

    public void setPrismGuid(String prismGuid) {
        this.prismGuid = prismGuid;
    }

    public String getPrismUserId() {
        return prismUserId;
    }

    public void setPrismUserId(String prismUserId) {
        this.prismUserId = prismUserId;
    }

    @Override
    public String toString() {
        return "SecondResponse{" + "endDate='" + endDate + '\'' + ", prismAuthStatusCode=" + prismAuthStatusCode + ", prismAuthToken='" + prismAuthToken + '\'' + ", prismGuid='" + prismGuid + '\'' + ", prismUserId='" + prismUserId + '\'' + '}';
    }

}
