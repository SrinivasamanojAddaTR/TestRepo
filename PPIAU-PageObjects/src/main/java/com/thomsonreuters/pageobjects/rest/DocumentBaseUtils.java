package com.thomsonreuters.pageobjects.rest;

import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceDocumentImpl;

public class DocumentBaseUtils {

	private RestServiceDocumentImpl restService = new RestServiceDocumentImpl();

	public String getDocumentContentType(String documentGuid) {
		String contentType = restService.getMetaInfo(documentGuid).getContentType();
		System.out.println("Document '" + documentGuid + "' Content type is '" + contentType + "'");
		return contentType.toString();
	}

	public String getDocumentResourceType(String documentGuid) {
		String resourceType = restService.getMetaInfo(documentGuid).getPlcResourceName();
		System.out.println("Document '" + documentGuid + "' Resource type is '" + resourceType + "'");
		return resourceType.toString();
	}

	public String getDocumentXML(String documentGuid) {
		return restService.getDocumentXML(documentGuid);
	}

}
