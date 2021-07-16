package com.thomsonreuters.pageobjects.utils.bcitools.food_safety_calcconfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SliderValueList")
@XmlAccessorType(XmlAccessType.FIELD)
public class FSSliderValue {

	@XmlElement(name = "Item")
	private List<FSSliderValueItem> values;

	public List<FSSliderValueItem> getValues() {
		return values;
	}

	public void setValues(List<FSSliderValueItem> values) {
		this.values = values;
	}

}
