package com.thomsonreuters.pageobjects.rest.model.response.enitityid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thomsonreuters.pageobjects.rest.model.response.ABaseResponse;

/**
 * Created by Pavel_Ardenka on 12/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityIdentity extends ABaseResponse {

    private String docGuid;

    public String getDocGuid() {
        return docGuid;
    }

    public void setDocGuid(String docGuid) {
        this.docGuid = docGuid;
    }
}
