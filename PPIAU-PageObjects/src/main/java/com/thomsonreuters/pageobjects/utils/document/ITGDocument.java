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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		ITGDocument that = (ITGDocument) o;
		return selectedCountry.equals(that.selectedCountry) &&
				qAndAGuid.equals(that.qAndAGuid);
	}

}
