package com.thomsonreuters.pageobjects.rest.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentMetaInfoResponse extends JsonObject {
	
	@JsonProperty("contentType")
	private String contentType;
	
	@JsonProperty("plcResourceName")
	private String plcResourceName;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getPlcResourceName() {
		return plcResourceName;
	}

	public void setPlcResourceName(String plcResourceName) {
		this.plcResourceName = plcResourceName;
	}

}
