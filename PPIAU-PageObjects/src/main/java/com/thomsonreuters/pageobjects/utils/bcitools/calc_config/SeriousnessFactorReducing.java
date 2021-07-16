package com.thomsonreuters.pageobjects.utils.bcitools.calc_config;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SeriousnessFactorReducingList")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeriousnessFactorReducing {
	
	@XmlElement(name = "Item")
	private List<SeriousnessFactorItem> items;

	public List<SeriousnessFactorItem> getItems() {
		return items;
	}

	public void setItems(List<SeriousnessFactorItem> items) {
		this.items = items;
	}
	
	


}
