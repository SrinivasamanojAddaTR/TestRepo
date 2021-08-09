package com.thomsonreuters.pageobjects.pages.pl_plus_researchsearch;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This page Object is created to navigate to Cases Search Page
 */

public class CasesPage extends AbstractPage {

    public WebElement ukCasesClick() {
        return waitForExpectedElement(By.cssSelector("a[href*='UKCASES']"));
    }
}