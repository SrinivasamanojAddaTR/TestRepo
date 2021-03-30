package com.thomsonreuters.pageobjects.rest.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderCreationResponse extends JsonObject {

    private String categoryId;
    private String message;

    public String getCategoryId() {
        return categoryId;
    }

    public String getMessage() {
        return message;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
