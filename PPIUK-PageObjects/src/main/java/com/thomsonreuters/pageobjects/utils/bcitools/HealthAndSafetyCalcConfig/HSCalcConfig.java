package com.thomsonreuters.pageobjects.utils.bcitools.HealthAndSafetyCalcConfig;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.thomsonreuters.pageobjects.utils.bcitools.CalcConfig.CalcConfig;
import com.thomsonreuters.pageobjects.utils.bcitools.CalcConfig.SeriousnessFactorIncreasing;
import com.thomsonreuters.pageobjects.utils.bcitools.CalcConfig.SeriousnessFactorReducing;
import com.thomsonreuters.pageobjects.utils.bcitools.CalcConfig.TipsItem;
import com.thomsonreuters.pageobjects.utils.bcitools.CalcConfig.TipsList;


@XmlRootElement(name = "HealthAndSafetyCalcConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class HSCalcConfig implements CalcConfig {

	@XmlElement(name = "HarmFigureList")
	private List<HSHarmFigure> hsHarmFigure;

	@XmlElement(name = "SliderValueList")
	private List<HSSliderValue> hsSliderValue;
	
	@XmlElement(name = "SeriousnessFactorReducingList")
	private List<SeriousnessFactorReducing> seriousnessFactorReducing;
	
	@XmlElement(name = "SeriousnessFactorIncreasingList")
	private List<SeriousnessFactorIncreasing> seriousnessFactorIncreasing;
	
	@XmlElement(name = "TipsList")
	private List<TipsList> tipsList;

	public List<HSHarmFigure> getHsHarmFigure() {
		return hsHarmFigure;
	}

	public void setHsHarmFigure(List<HSHarmFigure> hsHarmFigure) {
		this.hsHarmFigure = hsHarmFigure;
	}

	public List<HSSliderValue> getHsSliderValue() {
		return hsSliderValue;
	}

	public void setHsSliderValue(List<HSSliderValue> hsSliderValue) {
		this.hsSliderValue = hsSliderValue;
	}
	
	public List<SeriousnessFactorIncreasing> getSeriousnessFactorIncreasing() {
		return seriousnessFactorIncreasing;
	}

	public void setSeriousnessFactorIncreasing(List<SeriousnessFactorIncreasing> seriousnessFactorIncreasing) {
		this.seriousnessFactorIncreasing = seriousnessFactorIncreasing;
	}
	
	public List<SeriousnessFactorReducing> getSeriousnessFactorReducing() {
		return seriousnessFactorReducing;
	}

	public void setSeriousnessFactorReducing(List<SeriousnessFactorReducing> seriousnessFactorReducing) {
		this.seriousnessFactorReducing = seriousnessFactorReducing;
	}
	
	public List<TipsList> getTipsList(){
		return tipsList;
	}
	
	public void setTipsList(List<TipsList> tipsList){
		this.tipsList = tipsList;
	}

}
