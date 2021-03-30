package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Steph Armytage on 26/01/2015. This is the whats market homepage - accessed as at 26/01/15 by
 * selecting the link What's Market - UK - Home from the link on the combined know how uk page (which in turn
 * is accessed from the United Kingdom link)
 */

public class WhatsMarketHomePage extends AbstractPage {

    public WhatsMarketHomePage() {
    }

    public WebElement freeTextField() {

        return waitForElementVisible(By.xpath("//div[@id='co_searchInputContainer']//textarea[@id='searchInputId']"));
    }

    public WebElement searchButton() {

        return waitForExpectedElement(By.id("searchButton"));
    }


    public WebElement whatsMarketDealTypeLink(String dealtype) {

        return waitForExpectedElement(By.linkText("WMDealTypeTest"));
    }

    public void selectLinkOnWhatsMarketHome(String linkName) {

        waitForExpectedElement(By.linkText(linkName)).click();
    }


    /**
     * object representing the deal type links on the WM homepage e.g. Listing Rules Transactions etc
     */

    public WebElement dealTypeLink(String dealtype) {

        return waitForExpectedElement(By.xpath("//div[@id='coid_categoryBoxTabPanel1']//a[contains(text(),'" + dealtype + "')]"),10);

    }

    public WebElement Administrations() {

        return waitForExpectedElement(By.id("coid_administrations"));

    }

    public WebElement PublicMandALink() {

            return waitForExpectedElement(By.id("coid_public_m___a"));

    }

    public WebElement recentDealsViewAllLink(){
        return waitForExpectedElement(By.xpath("//div[contains(@class,'co_genericBox') and contains(.,'Recent deals')]//a[contains(.,'View all')]"));
    }

}
