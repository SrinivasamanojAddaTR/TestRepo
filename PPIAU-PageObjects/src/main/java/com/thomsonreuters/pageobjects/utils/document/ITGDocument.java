package com.thomsonreuters.pageobjects.utils.document;

public class ITGDocument extends Document {

	private String selectedCountry;
	private String qAndAGuid;

	public String getqAndAGuid() {
		return qAndAGuid;
	}

	public void setqAndAGuid(String qAndAGuid) {
		this.qAndAGuid = qAndAGuid;
	}

	public String getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(String jurisdiction) {
		this.selectedCountry = jurisdiction;
	}

}
