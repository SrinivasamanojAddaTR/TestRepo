package com.thomsonreuters.pageobjects.utils.bcitools.EnvironmentOffenceCalcConfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HarmFigureList")
@XmlAccessorType(XmlAccessType.FIELD)
public class EOHarmFigure {
	
	@XmlElement(name = "Item")
	private List<EOHarmFigureItem> items;

	public List<EOHarmFigureItem> getItems() {
		return items;
	}

	public void setItems(List<EOHarmFigureItem> items) {
		this.items = items;
	}

}
