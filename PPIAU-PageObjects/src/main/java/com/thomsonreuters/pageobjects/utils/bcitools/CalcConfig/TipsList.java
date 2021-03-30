package com.thomsonreuters.pageobjects.utils.bcitools.CalcConfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TipsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class TipsList {
	
	@XmlElement(name = "Item")
	private List<TipsItem> items;

	public List<TipsItem> getItems() {
		return items;
	}

	public void setItems(List<TipsItem> items) {
		this.items = items;
	}
	
	


}
