package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InternationalLandingPage extends AbstractPage {

    public WebElement countryNameLink(String country) {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_browseMainColumn']//li/a[contains(text(),'" + country + "')]"));
    }

	public WebElement internationalSubscriptionForCountry(String country) {
		return waitForExpectedElement(By.xpath("//span[text()='International subscriptions']/../..//li/a[contains(text(),'" + country + "')]"));
	}
	
    public boolean internationalTabIsOpened() {
    	return (isExists(By.xpath("//li[contains(@class,'co_tabActive')]//a[text()='International']")));
    }

	public boolean sessionIsNotPause() {
		return (!isExists(By.id("co_suspendBillingPauseMessage")));
	}

}
