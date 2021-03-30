package com.thomsonreuters.pageobjects.rest.model.request;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

public class DocumentMetaInfoRequeset extends JsonObject {
	
	private static final String DOC_GUID = "docGuid";
	private static final String NOVUS_SEARCH_HANDLE = "novusSearchHandle";
	private static final String DOCUMENT_GUIDS = "DocumentGUIDS";
	
	public String createMetaInfoRequest(String docGuid, String novusSearchHandle) {
		JsonFactory factory = new JsonFactory();
		ObjectMapper om = new ObjectMapper(factory);
		factory.setCodec(om);
		ObjectNode docs = om.createObjectNode();
		ObjectNode docInfo = om.createObjectNode();
		docInfo.put(DOC_GUID, docGuid);
		docInfo.put(NOVUS_SEARCH_HANDLE, novusSearchHandle);

		ArrayNode an = om.createArrayNode();
		an.add(docInfo);
		docs.set(DOCUMENT_GUIDS, an);
		return docs.toString();
	}

}
