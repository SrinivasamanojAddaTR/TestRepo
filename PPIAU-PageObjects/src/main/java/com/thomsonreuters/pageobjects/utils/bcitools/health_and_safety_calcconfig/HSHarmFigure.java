package com.thomsonreuters.pageobjects.utils.bcitools.health_and_safety_calcconfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HarmFigureList")
@XmlAccessorType(XmlAccessType.FIELD)
public class HSHarmFigure {

	@XmlElement(name = "Item")
	private List<HSHarmFigureItem> items;

	public List<HSHarmFigureItem> getItems() {
		return items;
	}

	public void setItems(List<HSHarmFigureItem> items) {
		this.items = items;
	}

}
