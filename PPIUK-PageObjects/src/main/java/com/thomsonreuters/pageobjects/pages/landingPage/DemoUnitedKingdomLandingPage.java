package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * This is the page that the user arrives at after
 * Selecting the United Kingdom link - It contains links to practical law and also to research content
 */

public class DemoUnitedKingdomLandingPage extends AbstractPage {

    public DemoUnitedKingdomLandingPage() {
    }

    public WebElement freeTextField() {
        return getDriver.findElement(By.id("searchInputId"));
    }

    public WebElement searchButton() {
        return getDriver.findElement(By.id("searchButton"));
    }

    public WebElement knowHowGlobalLink() {
        return retryingFindElement(By.partialLinkText("Know How Global"));
    }

    public WebElement combinedKnowHowUKLink() {
        return retryingFindElement(By.partialLinkText("Combined Know How UK"));
    }

    public WebElement blockedContentMessage() {
        return waitForExpectedElement(By.id("co_blockedBox"));
    }

    public WebElement journalsLink() {
        return waitForExpectedElement(By.linkText("UK-JOURNALS"));
    }

    public WebElement legislationLink() {
        return waitForExpectedElement(By.linkText("UK-LEGISLATION"));
    }

    public WebElement casesLink() {
        return waitForExpectedElement(By.linkText("UK-CASES"));
    }

}
