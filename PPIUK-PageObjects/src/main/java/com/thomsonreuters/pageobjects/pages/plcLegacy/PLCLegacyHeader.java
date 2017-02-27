package com.thomsonreuters.pageobjects.pages.plcLegacy;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;



public class PLCLegacyHeader extends AbstractPage {

	private final static String LOGIN = "//a[text()='Log in' or text()='Log in as a different user']";

	public void login() {
		waitForPageToLoad();
		waitForExpectedElement(By.xpath(LOGIN)).click();
		waitForPageToLoad();
	}

}
