package com.thomsonreuters.pageobjects.utils.bcitools.corporate_manslaughter_calcconfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SliderValueList")
@XmlAccessorType(XmlAccessType.FIELD)
public class CMSliderValue {

	@XmlElement(name = "Item")
	private List<CMSliderValueItem> values;

	public List<CMSliderValueItem> getValues() {
		return values;
	}

	public void setValues(List<CMSliderValueItem> values) {
		this.values = values;
	}

}
