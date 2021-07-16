package com.thomsonreuters.pageobjects.utils.bcitools.fraud_bribery_money_offence_calcconfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CulpabilityList")
@XmlAccessorType(XmlAccessType.FIELD)
public class FBMOCulpability {

	@XmlElement(name = "Item")
	private List<FBMOCulpabilityItem> items;

	public List<FBMOCulpabilityItem> getItems() {
		return items;
	}

	public void setItems(List<FBMOCulpabilityItem> items) {
		this.items = items;
	}

}
