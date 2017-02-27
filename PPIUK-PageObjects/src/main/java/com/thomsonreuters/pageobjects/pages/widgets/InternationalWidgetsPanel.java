package com.thomsonreuters.pageobjects.pages.widgets;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InternationalWidgetsPanel extends AbstractPage {

    public WebElement widgetWithTitle(String title) {
        return waitForElementPresent(By.xpath("//div[@id='coid_website_browseRightColumn']//h3[contains(text(),'" + title + "')]/.."));
    }

    public boolean isWidgetWithTitlePresent(String title) {
        return isElementPresent(By.xpath("//div[@id='coid_website_browseRightColumn']//h3[contains(text(),'" + title + "')]"));
    }

    public WebElement buttonOnWidgetWithTitle(String widgetTitle, String buttonText) {
        WebElement widget = widgetWithTitle(widgetTitle);
        return widget.findElement(By.xpath(".//a[contains(text(),'" + buttonText + "')]"));
    }

    public String textOnWidgetWithTitle(String widgetTitle) {
        WebElement widget = widgetWithTitle(widgetTitle);
        return widget.findElement(By.tagName("p")).getText().trim();
    }

}
