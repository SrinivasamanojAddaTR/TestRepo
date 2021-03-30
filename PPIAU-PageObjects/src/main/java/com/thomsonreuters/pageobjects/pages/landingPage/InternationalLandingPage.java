package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Steph Armytage on 01/09/2015.
 */

public class InternationalLandingPage extends AbstractPage {
	
	final private static String SESSION_PAUSE="Your session was paused.";

    public InternationalLandingPage() {
    }

    public WebElement countryNameLink(String country) {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_browseMainColumn']//li/a[contains(text(),'" + country + "')]"));
    }

    public WebElement globalGuidesLink() {

        return waitForExpectedElement(By.partialLinkText("Global guides"));
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

    public WebElement internationalLink(String link) {
		return waitForExpectedElement(By.xpath("//*[contains(@id,'coid_categoryBoxTabPanel3')]//a[contains(text(),'"
				+ link + "')]"));
    }



}
