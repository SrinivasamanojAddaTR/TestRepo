package com.thomsonreuters.pageobjects.pages.alerts;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AlertsHeader extends AbstractPage {
    private static final By CLEAR_SELECTED_OPTION = By.id("co_alerts_clearSelected");
    private static final By DELETE_ALERT_OPTION = By.id("cobalt_ro_detail_trash");
    private static final By ITEMS_SELECTED_MESSAGE = By.id("co_ItemsSelected");
    private static final By INFO_ALERT_MESSAGE = By.xpath("//*[@id='co_alertMessagePlaceHolder']//div[contains(@class,'co_infoBox_message')]");

    public boolean isClearSelectedOptionDisplayed() {
        return isElementDisplayed(CLEAR_SELECTED_OPTION);
    }

    public WebElement theDeleteAlertOption() {
        return waitForExpectedElement(DELETE_ALERT_OPTION);
    }

    public boolean isDeleteAlertOptionDisplayed() {
        return isElementDisplayed(DELETE_ALERT_OPTION);
    }

    public WebElement itemsSelectedMessage() {
        return waitForExpectedElement(ITEMS_SELECTED_MESSAGE);
    }

    public String infoAlertMessage() {
        return waitForExpectedElement(INFO_ALERT_MESSAGE).getText().trim();
    }

}
