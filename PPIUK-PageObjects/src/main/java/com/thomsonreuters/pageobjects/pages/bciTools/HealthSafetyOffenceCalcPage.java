package com.thomsonreuters.pageobjects.pages.bciTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HealthSafetyOffenceCalcPage extends CalculatorsTab {
	
	 public WebElement likelihoodOfHarmDropDown() {
	    	return waitForExpectedElement(By.id("likelihoodHarm"));
	    }
	 
	 public WebElement seriousnessOfHarmDropDown() {
	    	return waitForExpectedElement(By.id("seriousnessHarm"));
	    }

}
