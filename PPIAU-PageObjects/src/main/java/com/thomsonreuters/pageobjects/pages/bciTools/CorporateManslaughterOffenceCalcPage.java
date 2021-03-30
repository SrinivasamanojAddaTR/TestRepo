package com.thomsonreuters.pageobjects.pages.bciTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CorporateManslaughterOffenceCalcPage extends CalculatorsTab {
	

	 public WebElement offenceCategoryDropDown() {
	    	return waitForExpectedElement(By.id("offenceCategory"));
	    }

}
