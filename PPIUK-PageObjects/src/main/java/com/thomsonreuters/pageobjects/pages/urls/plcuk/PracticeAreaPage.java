package com.thomsonreuters.pageobjects.pages.urls.plcuk;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class PracticeAreaPage extends AbstractPage {

    public WebElement getTopicLink(String topicName) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_categoryBoxTabPanel1']//a[text()='"+topicName+"']"));
    }

}
