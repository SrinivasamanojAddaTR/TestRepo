package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Steph Armytage on 26/01/2015. This is the combined know how uk page which is accessed from the United Kingdom link
 */

public class CombinedKnowHowUKLandingPage extends AbstractPage {

    public CombinedKnowHowUKLandingPage() {
    }

    public WebElement whatsMarketLink() {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_browseMainColumn']//a[contains(text(),'Home')]"));
    }


}
