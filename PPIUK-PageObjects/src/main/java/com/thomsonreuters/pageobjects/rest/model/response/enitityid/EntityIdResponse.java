package com.thomsonreuters.pageobjects.rest.model.response.enitityid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thomsonreuters.pageobjects.rest.model.response.ABaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 12/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityIdResponse extends ABaseResponse {
    private List<EntityIdEntities> responses = new ArrayList<>();

    public List<EntityIdEntities> getResponses() {
        return responses;
    }

    public void setResponses(List<EntityIdEntities> responses) {
        this.responses = responses;
    }
}
