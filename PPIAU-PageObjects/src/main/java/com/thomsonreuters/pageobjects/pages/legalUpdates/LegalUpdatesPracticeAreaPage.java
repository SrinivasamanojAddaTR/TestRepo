package com.thomsonreuters.pageobjects.pages.legalUpdates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;


public class LegalUpdatesPracticeAreaPage extends LegalUpdatesBasePage {

    public LegalUpdatesPracticeAreaPage() {

    }

    public List<WebElement> practiceAreasLinks() {
        return waitForExpectedElements(By.xpath("//div[@class='co_browseContent']//a"));
    }

    public WebElement legalUpdatesWidget() {
        return waitForExpectedElement(By.id("coid_website_browseRightColumn"));
    }

    public WebElement specificPracticeAreaLink(String specificPracticeArea) {
        return waitForExpectedElement(By.linkText(specificPracticeArea));
    }
    
    public WebElement legalUpdatesAllWidgetsLink() {
    	return waitForExpectedElement(By.linkText("Legal Updates All Widgets"));
    }
    
    public WebElement calendarWidget() {
    	return waitForExpectedElement(By.xpath("//div[@id='calendar_container']//div[@class='calendar-widget']"));
    }

    public WebElement updateWithNumberLink(int number) {
        return waitForExpectedElement(By.xpath("//*[@id='co_rightColumn']//*[@id='ContentBlock"+(number-1)+"']//descendant::a"));
    }

}
