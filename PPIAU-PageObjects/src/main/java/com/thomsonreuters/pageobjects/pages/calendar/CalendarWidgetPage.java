package com.thomsonreuters.pageobjects.pages.calendar;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CalendarWidgetPage extends AbstractPage {

    public WebElement calendarWidget() {
        return waitForExpectedElement(By.xpath("//div[@class='co_calendar_gridContainer']"));
    }

    public WebElement calendarWidgetMonthDropDown() {
        return waitForExpectedElement(By.xpath("//select[@class='co_calender_monthSelector']"));
    }

    public WebElement calendarWidgetKeyDate(String date) {
        return waitForExpectedElement(By.xpath("//tr//a[@class='co_calendar_eventday'][normalize-space(.)='"+date+"']"));
    }

    public List<WebElement> calendarWidgetKeyDateList() {
        return waitForExpectedElements(By.xpath("//tr//a[@class='co_calendar_eventday']"));
    }

    public WebElement calendarWidgetKeyDatePopUp() {
        return waitForExpectedElement(By.id("calendarLegalUpdatesLightbox"));
    }

    public List<WebElement> calendarWidgetKeyDatePopUpLinks() {
        return waitForExpectedElements(By.xpath("//ul[@class='calendarLegalUpdates_EventDetails']//li//a"));
    }

    public WebElement calendarWidgetViewAllButton() {
        return waitForExpectedElement(By.xpath("//div[@id='keyDatesCalendarContainer']//a[contains(@class,'co_defaultBtn')]"));
    }

    public List<WebElement> calendarWidgetPopupMetaDataList(int eventNo) {
        return waitForExpectedElements(By.xpath("//li[@class='event']["+eventNo+"]//ul[@class='eventMetadata']//li"));
    }

    public WebElement calendarWidgetViewAllDisabledButton() {
        return waitForExpectedElement(By.xpath("//div[@id='keyDatesCalendarContainer']//div[contains(@class,'co_disabled')]"));
    }

    public WebElement calendarWidgetNextArrow() {
        return waitForExpectedElement(By.xpath("//a[@class='navigation navigation-next']"));
    }

    public WebElement calendarWidgetPreviousArrow() {
        return waitForExpectedElement(By.xpath("//a[@class='navigation navigation-previous']"));
    }

    public WebElement viewAllLink(){
        return waitForExpectedElement(By.xpath("//div[@class='co_calendar']/a"));
    }

    public WebElement calendarWidgetAddToOutlookLink(){
        return waitForExpectedElement(
                By.xpath("//form[@class='co_addToOutlookFormInline']/button"));
    }

    public WebElement calendarWidgetAddToOutlookLinkGuid(){
        return waitForElementPresent(
                By.xpath("//form[@class='co_addToOutlookFormInline']/input[@name='documentGuid']"));
    }

    public WebElement calendarWidgetAddToOutlookLinkStartDate(){
        return waitForElementPresent(
                By.xpath("//form[@class='co_addToOutlookFormInline']/input[@name='startDate']"));
    }

    public WebElement calendarWidgetAddToOutlookLinkEndDate(){
        return waitForElementPresent(
                By.xpath("//form[@class='co_addToOutlookFormInline']/input[@name='endDate']"));
    }

    public WebElement calendarWidgetCurrentDay(){
        return waitForElementPresent(By.xpath("//div[@class='co_calendar_gridContainer']//td[@class='co_calendar_todayDate']//span"));
    }

    public boolean isCalendarOpenWebPopupViewDisplayed(){
        return isElementDisplayed(By.id("legalEventOpenWebPopupView"));
    }

    /**
     * Returning By element using id for event Preview Panel close button
     */
    public  WebElement calendarEventPopupCloseButton() {
        return waitForElementPresent(By.id("CalendarLegalUpdatesLightbox_CloseButton"));
    }

    /**
     * Returning By element using xpath for selected date in the event Preview Panel
     */
    public  WebElement calendarSelectedDate(){
        return waitForElementPresent(By.xpath("//*[contains(@Class,'co_calendar_eventday selectedKeyDate')]"));
    }

    /**
     * Returning By element using css selector for date event summary displayed in the event Preview Panel
     */
    public  WebElement calendarKeyDateDocSummaryOnPreviewPanel(){
        return waitForElementPresent(By.cssSelector("div.eventAbstract"));
    }
}
