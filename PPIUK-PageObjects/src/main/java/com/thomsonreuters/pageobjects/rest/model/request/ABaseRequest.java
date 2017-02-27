package com.thomsonreuters.pageobjects.rest.model.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

public abstract class ABaseRequest extends JsonObject {

    /**
     * Maybe will be useful for some pageobjects request logic processing
     */
    public abstract ObjectNode getNode();

    public String getRequest() {
        return getNode().toString();
    }

}
