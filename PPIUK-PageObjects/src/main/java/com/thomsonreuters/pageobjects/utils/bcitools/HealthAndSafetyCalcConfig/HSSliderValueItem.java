package com.thomsonreuters.pageobjects.utils.bcitools.HealthAndSafetyCalcConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class HSSliderValueItem {

	@XmlElement(name = "Organization")
	private String organization;
	@XmlElement(name = "Culpability")
	private String culpability;
	@XmlElement(name = "Harm")
	private String harm;
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

	public String getCulpability() {
		return culpability;
	}

	public void setCulpability(String culpability) {
		this.culpability = culpability;
	}

	public String getHarm() {
		return harm;
	}

	public void setHarm(String harm) {
		this.harm = harm;
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
