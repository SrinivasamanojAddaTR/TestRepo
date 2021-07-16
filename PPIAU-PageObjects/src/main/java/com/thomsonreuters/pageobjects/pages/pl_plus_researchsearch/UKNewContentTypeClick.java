package com.thomsonreuters.pageobjects.pages.pl_plus_researchsearch;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * This page Object is created to navigate to Research Area
 */

public class UKNewContentTypeClick extends AbstractPage {

    public WebElement clickUKNewContentType() {
        return waitForExpectedElement(By.cssSelector("a[href*='UKNewContentTypes']"));
    }

    public WebElement closeButton() {
        return waitForExpectedElement(By.cssSelector("input[value='Close']"));
    }

    public WebElement clickUnitedKingdom() {
        return waitForExpectedElement(By.linkText("United Kingdom"));

    }
    public WebElement combinedKnowHowUKClick()
    {
        return waitForExpectedElement(By.cssSelector("a[href*='CombinedKnowHowUK']"));
    }
    public WebElement logOut(){
        return waitForExpectedElement(By.cssSelector("#coid_website_signOffRegion"));
    }
}
