package com.thomsonreuters.pageobjects.pages.alerts;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;

import org.springframework.util.StringUtils;

/**
 * This class describes the elements and the actions on the Alert Summary page from the Edit Alert flow
 * Created by Olga_Nadenenko on 2/13/2017.
 */
public class AlertSummaryPage extends AbstractPage{
    private static final By ALERT_NAME_HEADER = By.id("summaryAlertName");
    private static final String EDIT_BELLOW_HEADER = "%sBellowHeader";
    private static final By SCHEDULE_END_DATE_VALUE = By.id("summaryEndDate");

    public String alertNameInSummaryPage() {
        return waitForExpectedElement(ALERT_NAME_HEADER).getText();
    }

    public boolean isBellowHeaderDisplayed(String bellowName) {
        return isElementDisplayed(By.id(String.format(EDIT_BELLOW_HEADER, StringUtils.uncapitalize(bellowName))));
    }

    public String scheduleEndDateValue() {
        return waitForExpectedElement(SCHEDULE_END_DATE_VALUE).getText();
    }
}
