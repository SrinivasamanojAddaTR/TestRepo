package com.thomsonreuters.pageobjects.pages.plcLegacy;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;



public class PLCLegacyLoginScreen extends AbstractPage {

	private final static String ONE_PASS = "//a[text()='Log in using OnePass' or @id='loginWithOnePass']";

	public void onePass() {
		waitForExpectedElement(By.xpath(ONE_PASS)).click();
		waitForPageToLoad();
	}

}
