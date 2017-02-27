package com.thomsonreuters.pageobjects.utils.bcitools.FoodSafetyCalcConfig;

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


@XmlRootElement(name = "FoodSafety")
@XmlAccessorType(XmlAccessType.FIELD)
public class FSCalcConfig implements CalcConfig {


	@XmlElement(name = "SliderValueList")
	private List<FSSliderValue> sliderValue;
	
	@XmlElement(name = "SeriousnessFactorReducingList")
	private List<SeriousnessFactorReducing> seriousnessFactorReducing;
	
	@XmlElement(name = "SeriousnessFactorIncreasingList")
	private List<SeriousnessFactorIncreasing> seriousnessFactorIncreasing;
	
	@XmlElement(name = "TipsList")
	private List<TipsList> tipsList;


	public List<FSSliderValue> getSliderValue() {
		return sliderValue;
	}

	public void setsliderValue(List<FSSliderValue> sliderValue) {
		this.sliderValue = sliderValue;
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
