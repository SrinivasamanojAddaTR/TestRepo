package com.thomsonreuters.pageobjects.pages.alerts;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AlertsHistoryPage extends AbstractPage {
    private static final By ALERTS_HISTORY_TITLE = By.xpath("//div[@id='co_alerts']//*[contains(text(),'WestClip Alert History')]");

    public boolean isAlertsHistoryTitleDisplayed() {
        return isElementDisplayed(ALERTS_HISTORY_TITLE);
    }

}
