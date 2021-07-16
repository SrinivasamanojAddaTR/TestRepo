package com.thomsonreuters.pageobjects.pages.landing_page;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Steph Armytage on 26/01/2015. This is the whats market homepage - accessed as at 26/01/15 by
 * selecting the link What's Market - UK - Home from the link on the combined know how uk page (which in turn
 * is accessed from the United Kingdom link)
 */

public class WhatsMarketHomePage extends AbstractPage {

    public WebElement freeTextField() {
        return waitForElementVisible(By.xpath("//div[@id='co_searchInputContainer']//textarea[@id='searchInputId']"));
    }

    public WebElement searchButton() {

        return waitForExpectedElement(By.id("searchButton"));
    }

    public WebElement administrations() {

        return waitForExpectedElement(By.id("coid_administrations"));

    }

    public WebElement publicMandALink() {

            return waitForExpectedElement(By.id("coid_public_m___a"));

    }

    public WebElement recentDealsViewAllLink(){
        return waitForExpectedElement(By.xpath("//div[contains(@class,'co_genericBox') and contains(.,'Recent deals')]//a[contains(.,'View all')]"));
    }

}
