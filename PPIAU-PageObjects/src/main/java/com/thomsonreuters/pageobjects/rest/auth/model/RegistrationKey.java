package com.thomsonreuters.pageobjects.rest.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationKey extends JsonObject {

    @JsonProperty("RegistrationKey")
    String regKey;

    public String getRegKey() {
        return regKey;
    }

    public void setRegKey(String regKey) {
        this.regKey = regKey;
    }

    public String getPassword() {
        return regKey.split("-")[1];
    }

    public String getUserId() {
        return regKey.split("-")[0];
    }

    @Override
    public String toString() {
        return "RegistrationKey{" + "regKey='" + regKey + '\'' + '}';
    }

}
