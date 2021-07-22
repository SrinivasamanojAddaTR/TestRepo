package com.thomsonreuters.pageobjects.rest.model.request.linkbuilder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

/**
 * Created by Pavel_Ardenka on 12/12/2016.
 */
public class GenerateLinkRequest extends ABaseRequest {

    private String clientId;
    private String resultGuid;
    private String type;

    private static final String CLIENT_ID = "clientId";
    private static final String RESULT_GUID = "resultGuid";
    private static final String DEFAUL_TYPE = "type";

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResultGuid() {
        return resultGuid;
    }

    public static String getDefaulType() {
        return DEFAUL_TYPE;
    }

    public void setResultGuid(String resultGuid) {
        this.resultGuid = resultGuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode generateLinkNode = objectMapper.createObjectNode();
        generateLinkNode.put(CLIENT_ID, clientId);
        generateLinkNode.put(RESULT_GUID, resultGuid);
        generateLinkNode.put(DEFAUL_TYPE, type);
        return generateLinkNode;
    }
}
