package com.thomsonreuters.pageobjects.rest.model.response.document.annotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.response.ABaseResponse;

/**
 * Created by Pavel_Ardenka on 28/06/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Annotation extends ABaseResponse {

    @JsonProperty("AnnotationSid")
    private String annotationSid;

    @JsonProperty("ItemGuid")
    private String documentGuid;

    @JsonProperty("CreatedByCurrentUser")
    private boolean isCreatedByCurrentUser;

    public String getAnnotationSid() {
        return annotationSid;
    }

    public void setAnnotationSid(String annotationSid) {
        this.annotationSid = annotationSid;
    }

    public String getDocumentGuid() {
        return documentGuid;
    }

    public void setDocumentGuid(String documentGuid) {
        this.documentGuid = documentGuid;
    }

    public boolean isCreatedByCurrentUser() {
        return isCreatedByCurrentUser;
    }

    public void setCreatedByCurrentUser(boolean createdByCurrentUser) {
        isCreatedByCurrentUser = createdByCurrentUser;
    }
}
