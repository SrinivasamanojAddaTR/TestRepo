package com.thomsonreuters.pageobjects.pages.bciTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EnviromentalOffenceCalc extends CalculatorsTab {

	public WebElement harmFigureDropDown() {
		return waitForExpectedElement(By.id("harmFigure"));
	}

}
