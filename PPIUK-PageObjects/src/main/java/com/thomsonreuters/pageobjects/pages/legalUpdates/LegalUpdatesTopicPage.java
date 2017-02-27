package com.thomsonreuters.pageobjects.pages.legalUpdates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class LegalUpdatesTopicPage extends LegalUpdatesPracticeAreaPage {

    public LegalUpdatesTopicPage() {

    }

    public WebElement legalUpdatesWidget() {
        return waitForExpectedElement(By.xpath("//div[@class='co_genericBox co_dataFeedWidget styled co_expandedState']"));
    }

    public WebElement topicsLink(String topicLink) {
        return retryingFindElement(By.xpath("//a[contains(text(),'" + topicLink + "')]"));
    }
    
    public WebElement specifiedTab(String tabName) {
    	return retryingFindElement(By.xpath("//a[@class='co_tabLink' and text()='" + tabName + "']"));
    }

    public WebElement specificTopicLink(String specificTopic) {
        return super.specificPracticeAreaLink(specificTopic);
    }
    
    public WebElement linkOnTab(String linkName) {
       return retryingFindElement(By.linkText(linkName));
    }

}
