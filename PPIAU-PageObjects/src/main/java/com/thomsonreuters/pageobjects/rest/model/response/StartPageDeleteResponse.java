package com.thomsonreuters.pageobjects.rest.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Pavel_Ardenka on 12/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StartPageDeleteResponse extends ABaseResponse {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
