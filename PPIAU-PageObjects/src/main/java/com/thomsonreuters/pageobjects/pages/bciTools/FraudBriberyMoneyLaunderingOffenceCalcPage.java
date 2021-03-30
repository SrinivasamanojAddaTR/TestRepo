package com.thomsonreuters.pageobjects.pages.bciTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FraudBriberyMoneyLaunderingOffenceCalcPage extends CalculatorsTab {

	public WebElement harmFigureInput() {
		return waitForExpectedElement(By.id("harmFigureInput"));
	}

}
