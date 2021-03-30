package com.thomsonreuters.pageobjects.utils.bcitools.CalcConfig;

import java.util.List;

public interface CalcConfig {
	public List<SeriousnessFactorIncreasing> getSeriousnessFactorIncreasing();

	public void setSeriousnessFactorIncreasing(List<SeriousnessFactorIncreasing> seriousnessFactorIncreasing);
	
	public List<SeriousnessFactorReducing> getSeriousnessFactorReducing();

	public void setSeriousnessFactorReducing(List<SeriousnessFactorReducing> seriousnessFactorReducing);
	
	public List<TipsList> getTipsList();
	
	public void setTipsList(List<TipsList> tipsItem);
}
