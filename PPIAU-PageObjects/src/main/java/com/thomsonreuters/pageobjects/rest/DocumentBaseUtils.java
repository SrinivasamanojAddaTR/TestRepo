package com.thomsonreuters.pageobjects.rest;

import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceDocumentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class DocumentBaseUtils {

	private RestServiceDocumentImpl restService = new RestServiceDocumentImpl();
	private static final Logger LOG = LoggerFactory.getLogger(DocumentBaseUtils.class);

	public String getDocumentContentType(String documentGuid) {
		String contentType = restService.getMetaInfo(documentGuid).getContentType();
		LOG.info("Document '{}' Content type is '{}'",documentGuid,contentType);
		return contentType;
	}

	public String getDocumentResourceType(String documentGuid) {
		String resourceType = restService.getMetaInfo(documentGuid).getPlcResourceName();
		LOG.info("Document '{}' Resource type is '{}'",documentGuid,resourceType);
		return resourceType;
	}

	public String getDocumentXML(String documentGuid) {
		return restService.getDocumentXML(documentGuid);
	}

}
