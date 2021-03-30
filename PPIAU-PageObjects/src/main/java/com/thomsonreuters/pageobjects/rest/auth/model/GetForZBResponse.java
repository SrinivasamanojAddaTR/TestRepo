package com.thomsonreuters.pageobjects.rest.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetForZBResponse extends JsonObject {

    @JsonProperty("OfficeId")
    String Zb;

    public String getOfficeId() {
        return Zb;
    }

    public void setOfficeId(String zb) {
    	Zb = zb;
    }

}
