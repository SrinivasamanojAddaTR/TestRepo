package com.thomsonreuters.pageobjects.pages.legalUpdates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LegalUpdatesPracticeAreaPage extends LegalUpdatesBasePage {

    public WebElement legalUpdatesWidget() {
        return waitForExpectedElement(By.id("coid_website_browseRightColumn"));
    }

    public WebElement specificPracticeAreaLink(String specificPracticeArea) {
        return waitForExpectedElement(By.linkText(specificPracticeArea));
    }
    
    public WebElement legalUpdatesAllWidgetsLink() {
    	return waitForExpectedElement(By.linkText("Legal Updates All Widgets"));
    }

}
