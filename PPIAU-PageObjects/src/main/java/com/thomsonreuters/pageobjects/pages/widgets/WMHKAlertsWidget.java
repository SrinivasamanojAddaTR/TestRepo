package com.thomsonreuters.pageobjects.pages.widgets;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;

public class WMHKAlertsWidget extends AbstractPage {

	private static final String HEADER = "What's Market Daily Alert";

	public boolean isHeaderPresent() {
		return isExists(By.xpath("//h3[text()=\"" + HEADER + "\"]"));
	}

	public boolean isInfoPresent() {
		return isExists(By.xpath("//p[contains(text(),'Sign up to be notified of the deals published on What') and contains(text(),'s Market Hong Kong')]"));
	}

	public boolean isLinkToEmailPerferencesPresent() {
		return isExists(By.xpath("//a[contains(@href,'emailpreferences') and text()='Sign up here']"));
	}

}
