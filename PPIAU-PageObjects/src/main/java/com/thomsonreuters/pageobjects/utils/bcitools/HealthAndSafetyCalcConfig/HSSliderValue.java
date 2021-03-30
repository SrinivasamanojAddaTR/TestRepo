package com.thomsonreuters.pageobjects.utils.bcitools.HealthAndSafetyCalcConfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SliderValueList")
@XmlAccessorType(XmlAccessType.FIELD)
public class HSSliderValue {

	@XmlElement(name = "Item")
	private List<HSSliderValueItem> values;

	public List<HSSliderValueItem> getValues() {
		return values;
	}

	public void setValues(List<HSSliderValueItem> values) {
		this.values = values;
	}

}
