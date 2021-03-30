package com.thomsonreuters.pageobjects.rest.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile extends JsonObject {

    @JsonProperty("RegistrationKeys")
    List<RegistrationKey> registrationKeys = new ArrayList<>();

    public List<RegistrationKey> getRegistrationKeys() {
        return registrationKeys;
    }

    public void setRegistrationKeys(List<RegistrationKey> registrationKeys) {
        this.registrationKeys = registrationKeys;
    }

    @Override
    public String toString() {
        return "Profile{" + "registrationKeys=" + registrationKeys + '}';
    }

}
