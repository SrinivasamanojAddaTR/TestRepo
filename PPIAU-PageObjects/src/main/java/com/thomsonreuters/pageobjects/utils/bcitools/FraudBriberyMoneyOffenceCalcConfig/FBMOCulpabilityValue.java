package com.thomsonreuters.pageobjects.utils.bcitools.FraudBriberyMoneyOffenceCalcConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Value")
@XmlAccessorType(XmlAccessType.FIELD)
public class FBMOCulpabilityValue {

	@XmlElement(name = "Start")
	private String start;
	@XmlElement(name = "From")
	private String from;
	@XmlElement(name = "To")
	private String to;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
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
}
