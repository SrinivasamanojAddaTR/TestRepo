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
    private List<EntityIdentity> entityIdentitiesList = new ArrayList<>();

    public List<EntityIdentity> getEntityIdentitiesList() {
        return entityIdentitiesList;
    }

    public void setEntityIdentitiesList(List<EntityIdentity> entityIdentitiesList) {
        this.entityIdentitiesList = entityIdentitiesList;
    }
}
