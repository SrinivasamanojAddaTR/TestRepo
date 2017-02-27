package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by u4400015 on 26/10/2015.
 */


public class GlobalGuidesLandingPage extends AbstractPage {

    public GlobalGuidesLandingPage() {
    }


    /**
     * This is the link to the country Q&A comparison tool
     */

    public WebElement startComparingLink() {

        return retryingFindElement(By.xpath("//a[@aria-label='Start comparing']"));
    }


}
