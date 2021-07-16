package com.thomsonreuters.pageobjects.utils.bcitools.fraud_bribery_money_offence_calcconfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GuiltyPleaReductionList")
@XmlAccessorType(XmlAccessType.FIELD)
public class FBMOGuiltyPleaReduction {

	@XmlElement(name = "Item")
	private List<FBMOGuiltyPleaReductionItem> items;

	public List<FBMOGuiltyPleaReductionItem> getItems() {
		return items;
	}

	public void setItems(List<FBMOGuiltyPleaReductionItem> items) {
		this.items = items;
	}

}
