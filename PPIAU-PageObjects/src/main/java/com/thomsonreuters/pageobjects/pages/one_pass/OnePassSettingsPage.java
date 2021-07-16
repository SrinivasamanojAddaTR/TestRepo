package com.thomsonreuters.pageobjects.pages.one_pass;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class OnePassSettingsPage extends AbstractPage {

    public WebElement storedAccessTab() {
        return waitForExpectedElement(By.xpath("//a/span[contains(text(),'Stored Access')]"));
    }

    public boolean isStoredAccessTabPresent() {
    	return isElementDisplayed(By.xpath("//a/span[contains(text(),'Stored Access')]"));
    }

    public boolean isRemoveLinkPresent() {
    	return isElementDisplayed(By.xpath("//a[contains(text(),'Remove')]"));
    }

    public WebElement removeLink() {
        return waitForExpectedElement(By.xpath("//a[contains(text(),'Remove')]"));
    }

    public WebElement returnToPLCUKLink() {
        return waitForExpectedElement(By.xpath("//a/span[contains(text(),'Return To PLC UK')]"));
    }

}