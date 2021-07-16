package com.thomsonreuters.pageobjects.pages.search_browse;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Pavel_Ardenka on 02/12/2016.
 * Common implementation related to all WLUK topic pages
 */
public abstract class AbstractWlukTopicPage extends AbstractPage {

    public WebElement getTopicLink(String topicName) {
        return waitForExpectedElement(By.xpath("//ul[@class='plcukTopicHierarchyList']//a[contains(., '" + topicName + "')]"));
    }

    public WebElement getTopicPageTitle() {
        return waitForExpectedElement(By.id("co_browsePageLabel"));
    }

}
