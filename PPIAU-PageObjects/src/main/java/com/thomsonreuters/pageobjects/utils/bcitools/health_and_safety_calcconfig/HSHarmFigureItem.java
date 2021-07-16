package com.thomsonreuters.pageobjects.utils.bcitools.health_and_safety_calcconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class HSHarmFigureItem {

	@XmlElement(name = "Likelihood")
	private String likelihood;
	@XmlElement(name = "Seriousness")
	private String seriousness;
	@XmlElement(name = "Figure")
	private String figure;
	
	
	public String getLikelihood() {
		return likelihood;
	}
	public void setLikelihood(String likelihood) {
		this.likelihood = likelihood;
	}
	public String getSeriousness() {
		return seriousness;
	}
	public void setSeriousness(String seriousness) {
		this.seriousness = seriousness;
	}
	public String getFigure() {
		return figure;
	}
	public void setFigure(String figure) {
		this.figure = figure;
	}


}
