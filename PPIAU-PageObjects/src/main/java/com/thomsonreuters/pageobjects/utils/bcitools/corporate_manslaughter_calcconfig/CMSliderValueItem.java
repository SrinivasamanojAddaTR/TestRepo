package com.thomsonreuters.pageobjects.utils.bcitools.corporate_manslaughter_calcconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class CMSliderValueItem {

	@XmlElement(name = "Organization")
	private String organization;
	@XmlElement(name = "OffenceCategory")
	private String offenceCategory;
	@XmlElement(name = "StartingPoint")
	private String startingPoint;
	@XmlElement(name = "From")
	private String from;
	@XmlElement(name = "To")
	private String to;
	@XmlElement(name = "Step")
	private String step;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOffenceCategory() {
		return offenceCategory;
	}

	public void setOffenceCategory(String offenceCategory) {
		this.offenceCategory = offenceCategory;
	}

	public String getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}
}
