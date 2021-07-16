package com.thomsonreuters.pageobjects.pages.alerts.creation_bellow;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ScheduleAlertBellowPage extends AbstractPage {
    private static final By SCHEDULE_FREQUENCY_DROPDOWN = By.id("frequencySelect");
    private static final By SCHEDULE_END_DATE_LABEL = By.xpath("//*[@id='scheduleLeftColumn']//label[@for='endDate']");
    private static final By SCHEDULE_END_DATE_DROPDOWN = By.id("endDate");
    private static final By SCHEDULE_SAVE_ALERT_BUTTON = By.id("co_button_saveAlert");
    private static final By SCHEDULE_END_DATE_DATEPICKER = By.id("ui-datepicker-div");
    private static final By SCHEDULE_END_DATE_DATEPICKER_TITLE = By.xpath("//div[@class='ui-datepicker-title']");
    private static final By DATEPICKER_CURRENT_DATE = By.xpath("//a[contains(@class,'ui-state-highlight')]");
    private static final By DATEPICKER_PREVIOUS_BUTTON = By.xpath("//*[@id='ui-datepicker-div']//a[@title='Prev']");
    private static final By DATEPICKER_NEXT_BUTTON = By.xpath("//*[@id='ui-datepicker-div']//a[@title='Next']");
    private static final By DATEPICKER_PREV_MONTH_BUTTON_DISABLED = By.xpath("//*[@id='ui-datepicker-div']//a[contains(@class,'ui-state-disabled')]");

    public boolean isScheduleFrequencyDropdownDisplayed() {
        return isElementDisplayed(SCHEDULE_FREQUENCY_DROPDOWN);
    }

    public boolean isScheduleEndDateLabelDisplayed() {
        return isElementDisplayed(SCHEDULE_END_DATE_LABEL);
    }

    public WebElement scheduleEndDateDropdown() {
        return waitForExpectedElement(SCHEDULE_END_DATE_DROPDOWN);
    }

    public boolean isScheduleEndDateDropdownDisplayed() {
        return isElementDisplayed(SCHEDULE_END_DATE_DROPDOWN);
    }

    public String getSelectedEndDate() {
        return scheduleEndDateDropdown().getAttribute("value");
    }

    public boolean isScheduleEndDatePickerDisplayed() {
        return isElementDisplayed(SCHEDULE_END_DATE_DATEPICKER);
    }

    public boolean isScheduleSaveAlertButtonDisplayed() {
        return isElementDisplayed(SCHEDULE_SAVE_ALERT_BUTTON);
    }

    public WebElement scheduleSaveAlertButton() {
        return waitForExpectedElement(SCHEDULE_SAVE_ALERT_BUTTON);
    }

    public WebElement scheduleEndDatePickerTitle() {
        return waitForExpectedElement(SCHEDULE_END_DATE_DATEPICKER_TITLE);
    }

    public WebElement datePickerCurrentDate() {
        return waitForExpectedElement(DATEPICKER_CURRENT_DATE);
    }

    public WebElement datePickerNextMonthButton() {
        return waitForExpectedElement(DATEPICKER_NEXT_BUTTON);
    }

    public boolean isDatePickerNextMonthButtonDisplayed() {
        return isElementDisplayed(DATEPICKER_NEXT_BUTTON);
    }

    public boolean isDatePickerPrevMonthButtonDisplayed() {
        return isElementDisplayed(DATEPICKER_PREVIOUS_BUTTON);
    }

    public boolean isDatePickerDateLinkDisplayed(String linkName) {
        return isElementDisplayed(By.linkText(linkName));
    }

    public boolean isDatePickerPrevMonthButtonDisabled() {
        return isElementDisplayed(DATEPICKER_PREV_MONTH_BUTTON_DISABLED);
    }
}
