package com.thomsonreuters.pageobjects.rest.model.response.enitityid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thomsonreuters.pageobjects.rest.model.response.ABaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 12/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityIdEntities extends ABaseResponse {
    private List<EntityIdentity> entityIdentities = new ArrayList<>();

    public List<EntityIdentity> getEntityIdentities() {
        return entityIdentities;
    }

    public void setEntityIdentities(List<EntityIdentity> entityIdentities) {
        this.entityIdentities = entityIdentities;
    }
}
