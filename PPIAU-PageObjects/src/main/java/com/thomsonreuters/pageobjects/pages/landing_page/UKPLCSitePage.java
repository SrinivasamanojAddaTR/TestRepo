package com.thomsonreuters.pageobjects.pages.landing_page;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UKPLCSitePage extends AbstractPage {

    /**
     * show resource details link in right hand panel - this is not a frame
     */
    public WebElement showResourceDetailsLink() {
        return waitForExpectedElement(By.linkText("Show resource details"));
    }

    /**
     * product information section e.g. PLC UK Corporate
     */
    public WebElement productsSection() {
        return waitForExpectedElement(By.xpath("//ul[@id='resource_details']//span[text()='Products:']/.."));
    }

    /**
     * topic section e.g. Trusts
     */

    public boolean isTopicAvailable(String topicName){
    	return isElementDisplayed(By.xpath(String.format(".//div[text()='Topics']/../ul/li/a[text()='%s']", topicName)));
    }

}
