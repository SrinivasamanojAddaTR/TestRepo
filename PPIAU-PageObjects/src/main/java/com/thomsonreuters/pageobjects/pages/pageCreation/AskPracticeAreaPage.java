package com.thomsonreuters.pageobjects.pages.pageCreation;

import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.DocumentDisplayAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AskPracticeAreaPage extends DocumentDisplayAbstractPage {

    public WebElement askResourcesLink() {
        return waitForExpectedElement(By.id("coid_ask"));
    }

    public WebElement askTopicLink(String link) {
       // return waitForExpectedElement(By.xpath("//div[@class='co_featureBoxInner']//li/a[text()=\"" + link + "\"]"));
        //return waitForExpectedElement(By.xpath("//ul/li/a[text()=\"" + link + "\"]"));
        return waitForExpectedElement(By.xpath("//div[@class='co_genericBoxContent']//ul/li/a[normalize-space(.)=\""+link+"\"]"));
    }

    public WebElement askTopicLinkHeading() {
        return waitForExpectedElement(By.xpath("//h1"));
    }

}