package com.thomsonreuters.pageobjects.rest.model.response.linkbuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Pavel_Ardenka on 12/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerateLinkResponse {

    @JsonProperty("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GenerateLinkResponse{" +
                "url='" + url + '\'' +
                '}';
    }
}
