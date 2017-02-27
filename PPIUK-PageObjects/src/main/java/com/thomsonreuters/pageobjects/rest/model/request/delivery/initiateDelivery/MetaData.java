package com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

import java.util.ArrayList;
import java.util.List;

class MetaData extends ABaseRequest {

    private static final String COMPANY_ENTITIES_NAME = "CompanyEntities";
    private static final String DATA_ORCHESTRATION_GUID_NAME = "DataOrchestrationGuid";
    private static final String PEOPLE_ENTITIES_NAME = "PeopleEntities";

    // Default values
    private final List<String> companyEntities = new ArrayList<>(); // String?
    private boolean isDataOrchestrationGuid = false;
    private final List<String> peopleEntities = new ArrayList<>(); // String?

    public MetaData addCompanyEntity(String companyEntity) {
        companyEntities.add(companyEntity);
        return this;
    }

    public MetaData addPeopleEntity(String peopleEntity) {
        peopleEntities.add(peopleEntity);
        return this;
    }

    public MetaData setDataOrchestrationGuid(boolean isDataOrchestrationGuid) {
        this.isDataOrchestrationGuid = isDataOrchestrationGuid;
        return this;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode metaDataNode = objectMapper.createObjectNode();
        ArrayNode companyEntitiesNode = objectMapper.valueToTree(companyEntities);
        ArrayNode peopleEntitiesNode = objectMapper.valueToTree(peopleEntities);
        metaDataNode.set(COMPANY_ENTITIES_NAME, companyEntitiesNode);
        metaDataNode.put(DATA_ORCHESTRATION_GUID_NAME, isDataOrchestrationGuid);
        metaDataNode.set(PEOPLE_ENTITIES_NAME, peopleEntitiesNode);
        return metaDataNode;
    }
}
