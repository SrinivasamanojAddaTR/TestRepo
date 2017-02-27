package com.thomsonreuters.pageobjects.pages.searchBrowse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Pavel_Ardenka on 02/12/2016.
 * Page object class represents WLUK Topic Level 1, 2, 3... Page
 */
public class WLUKChildTopicPage extends AbstractWlukTopicPage {

    public WebElement topicLink(String topic) {
        return waitForExpectedElement(By.xpath("//*[@id='coid_website_browseMainColumn']//a[contains(text(),'" + topic + "')]"));
    }

    public void waitTopicsToLoad() {
        waitForElementInvisible(By.className("co_searchWaitProgressImg"));
    }

}
