package com.thomsonreuters.pageobjects.utils.bcitools.CalcConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class TipsItem {
	
	@XmlElement(name = "Element")
	private String element;
	
	@XmlElement(name = "Tips")
	private String tips;

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}
	
	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	
	


}
