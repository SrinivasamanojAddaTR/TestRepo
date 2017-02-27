package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


/**
 * Created by Steph Armytage on 28/07/2014. This is actually a page on the UK site not on westlaw next. Reason being that Chris B created
 * a link from demo site to existing UK site in order to enable checking of product information in resource details - not something
 * that is currently possible on the demo site - hence the link to the UK site for equivalent search results
 */


public class UKPLCSitePage extends AbstractPage {

    public UKPLCSitePage() {
    }

    /**
     * show resource details link in right hand panel - this is not a frame
     */
    public WebElement showResourceDetailsLink() {
        return waitFluentForElement(By.linkText("Show resource details"));
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
    	return isElementDisplayed(By.xpath(".//div[text()='Topics']/../ul/li/a[text()='"+topicName+"']"));
    }

}
