package com.thomsonreuters.pageobjects.rest.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

public abstract class JsonObject {

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JsonObject.class);

    @Override
    public String toString() {
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOG.info("context", e);
        }
        return json;
    }
}
