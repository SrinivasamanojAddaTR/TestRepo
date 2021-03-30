package com.thomsonreuters.pageobjects.rest.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostForRegKeysResponse extends JsonObject {

    @JsonProperty("Profile")
    Profile profile;

    @JsonProperty("SeamlessToken")
    String seamlessToken;

    public String getSeamlessToken() {
        return seamlessToken;
    }

    public void setSeamlessToken(String seamlessToken) {
        this.seamlessToken = seamlessToken;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "FirstResponse{" + "profile=" + profile + ", seamlessToken='" + seamlessToken + '\'' + '}';
    }

}
