package com.thomsonreuters.pageobjects.pages.landing_page;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UnitedKingdomLandingPage extends AbstractPage {

    public WebElement freeTextField() {
        return getDriver.findElement(By.id("searchInputId"));
    }

    public WebElement searchButton() {
        return getDriver.findElement(By.id("searchButton"));
    }

    public WebElement knowHowGlobalLink() {
        return waitForExpectedElement(By.partialLinkText("Know How Global"));
    }

    public WebElement combinedKnowHowUKLink() {
        return waitForExpectedElement(By.partialLinkText("Combined Know How UK"));
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
