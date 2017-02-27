package com.thomsonreuters.pageobjects.pages.onePass;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class OnePassSettingsPage extends AbstractPage {

    public WebElement storedAccessTab() {
        return retryingFindElement(By.xpath("//a/span[contains(text(),'Stored Access')]"));
    }

    public boolean isStoredAccessTabPresent() {
    	return isElementDisplayed(By.xpath("//a/span[contains(text(),'Stored Access')]"));
    }

    public boolean isRemoveLinkPresent() {
    	return isElementDisplayed(By.xpath("//a[contains(text(),'Remove')]"));
    }

    public WebElement removeLink() {
        return retryingFindElement(By.xpath("//a[contains(text(),'Remove')]"));
    }

    public WebElement returnToPLCUKLink() {
        return retryingFindElement(By.xpath("//a/span[contains(text(),'Return To PLC UK')]"));
    }

}