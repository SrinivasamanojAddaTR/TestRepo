package com.thomsonreuters.pageobjects.pages.pl_plus_researchsearch;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This page Object is created to navigate to legislation Search Page
 */

public class Legislationpage extends AbstractPage {

    public WebElement ukLegislationClick() {
        return waitForExpectedElement(By.cssSelector("a[href*='UKLEGISLATION']"));
    }
}