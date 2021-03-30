package com.thomsonreuters.pageobjects.askReWrite;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AskRewriteQueryViewPage extends AbstractPage {
    private static final By QUERY_VIEW_PAGE_HEADER = By.cssSelector(":not(.topbar-item)>h1");
    private static final By QUESTION_TEXT = By.cssSelector("[ng-bind-html*='queryView.questionText']");
    private static final By QUESTION_TITLE = By.cssSelector("[ng-bind-html*='queryView.questionTitle']");
    private static final By QUESTION_DATETIME = By.xpath("//*[contains(@ng-bind-html, 'queryView.questionText')]/..//small");
    private static final By FOLLOWUP_SECTION = By.cssSelector("div[ng-repeat*='followUp in queryView']");
    private static final By DATETIME_IN_FOLLOWUP_SECTION = By.cssSelector("small:not(.ng-hide)");
    private static final By STATUS_IN_FOLLOWUP_SECTION = By.cssSelector("small:not(.ng-hide)");
    private static final By MESSAGE_IN_FOLLOWUP_SECTION = By.tagName("span");

    private static String QUESTION_HEADER = "//label[contains(text(), '%s')]";
    private static String TITLE_HEADER = "//*[contains(@ng-bind-html, 'queryView.questionTitle')]/..//*[contains(text(), '%s')]";
    private static String TITLE_IN_FOLLOWUP_SECTION = "//label[contains(text(), '%s') and not(contains(@class, 'ng-hide'))]";
    private static String FOLLOWUP_SECTION_BY_DIRECTION = "//*[contains(@ng-show, '%s') and not(contains(@class, 'ng-hide'))]/..";

    public WebElement queryViewPageHeader() {
        return waitForExpectedElement(QUERY_VIEW_PAGE_HEADER);
    }

    public WebElement questionTextInQueryView() {
        return waitForExpectedElement(QUESTION_TEXT);
    }

    public WebElement questionDatetimeInQueryView() {
        return waitForExpectedElement(QUESTION_DATETIME);
    }

    public WebElement questionHeaderInQueryView(String headerText) {
        return waitForExpectedElement(By.xpath(String.format(QUESTION_HEADER, headerText)));
    }

    public WebElement titleHeaderInQueryView(String headerText) {
        return waitForExpectedElement(By.xpath(String.format(TITLE_HEADER, headerText)));
    }

    public WebElement titleInQueryView() {
        return waitForElementPresent(QUESTION_TITLE);
    }

    public List<WebElement> allFollowupSectionsInQueryView() {
        return waitForExpectedElements(FOLLOWUP_SECTION);
    }

    public WebElement followupSectionInQueryView() {
        return waitForExpectedElement(FOLLOWUP_SECTION);
    }

    public List<WebElement> allFollowupSectionsInQueryViewByDirection(String direction) {
        return waitForExpectedElements(By.xpath(String.format(FOLLOWUP_SECTION_BY_DIRECTION, direction)));
    }

    public List<WebElement> allFollowupMessagesInQueryView() {
        List<WebElement> followupMessagesInQueryView = new ArrayList<>();
        for (WebElement followup : allFollowupSectionsInQueryView()) {
            followupMessagesInQueryView.add(findChildElement(followup, MESSAGE_IN_FOLLOWUP_SECTION));
        }
        return followupMessagesInQueryView;
    }

    public List<WebElement> allFollowupDatesInQueryView() {
        List<WebElement> followupDatesInQueryView = new ArrayList<>();
        for (WebElement followup : allFollowupSectionsInQueryView()) {
            followupDatesInQueryView.add(findChildElement(followup, DATETIME_IN_FOLLOWUP_SECTION));
        }
        return followupDatesInQueryView;
    }

    public By messageOfFollowup() {
        return MESSAGE_IN_FOLLOWUP_SECTION;
    }

    public By titleOfFollowup(String titleText) {
        return By.xpath(String.format(TITLE_IN_FOLLOWUP_SECTION, titleText));
    }

    public By dateAndTimeOfFollowup() {
        return DATETIME_IN_FOLLOWUP_SECTION;
    }

    public By statusOfFollowup() {
        return STATUS_IN_FOLLOWUP_SECTION;
    }
}