package com.thomsonreuters.pageobjects.utils.bcitools.FraudBriberyMoneyOffenceCalcConfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class FBMOCulpabilityItem {

	@XmlElement(name = "Value")
	private List<FBMOCulpabilityValue> values;

	@XmlElement(name = "Title")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FBMOCulpabilityValue> getValues() {
		return values;
	}

	public void setValues(List<FBMOCulpabilityValue> values) {
		this.values = values;
	}

}
