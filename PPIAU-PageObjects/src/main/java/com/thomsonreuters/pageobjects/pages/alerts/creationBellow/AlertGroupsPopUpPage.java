package com.thomsonreuters.pageobjects.pages.alerts.creationBellow;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class describes elements and actions on the Alert Groups pop up that is available from Basics bellow of 'Create alert' page.
 * Created by Olga_Nadenenko on 1/9/2017.
 */
public class AlertGroupsPopUpPage extends AbstractPage {
    private static final By POP_UP_HEADER = By.xpath("//div[@id='coid_lightboxOverlay']//*[text()='Alert Groups']");
    private static final By ASSIGN_BUTTON = By.id("coid_alerts_widgets_alertGroups_lightbox_assignButton");
    private static final By CANCEL_BUTTON = By.id("coid_alerts_widgets_alertGroups_lightbox_cancelLink");
    private static final By CREATE_GROUP_BUTTON = By.id("coid_alerts_widgets_alertGroups_createButton");
    private static final By ALERT_GROUPS_LIST = By.id("coid_alerts_widgets_alertGroups_listItems");
    private static final By CREATE_GROUP_INPUT = By.id("coid_alerts_widgets_alertGroups_createInput");
    private static final By CREATE_GROUP_SAVE_BUTTON = By.id("coid_alerts_widgets_alertGroups_createSave");
    private static final String ALERT_GROUP_FROM_LIST_BY_NAME = "//*[@id='coid_alerts_widgets_alertGroups_listItems']//a[@title='%s']";

    public boolean isPopUpHeadedDisplayed() {
        return isElementDisplayed(POP_UP_HEADER);
    }

    public WebElement assignButton() {
        return waitForExpectedElement(ASSIGN_BUTTON);
    }

    public boolean isAssignButtonDisplayed() {
        return isElementDisplayed(ASSIGN_BUTTON);
    }

    public boolean isCancelButtonDisplayed() {
        return isElementDisplayed(CANCEL_BUTTON);
    }

    public WebElement createGroupButton() {
        return waitForExpectedElement(CREATE_GROUP_BUTTON);
    }

    public boolean isCreateGroupButtonDisplayed() {
        return isElementDisplayed(CREATE_GROUP_BUTTON);
    }

    public boolean isAlertGroupsListDisplayed() {
        return isElementDisplayed(ALERT_GROUPS_LIST);
    }

    public WebElement createGroupInput() {
        return waitForExpectedElement(CREATE_GROUP_INPUT);
    }

    public WebElement createGroupSaveButton() {
        return waitForExpectedElement(CREATE_GROUP_SAVE_BUTTON);
    }

    public WebElement alertGroupFromListByName(String alertGroupName) {
        return waitForExpectedElement(By.xpath(String.format(ALERT_GROUP_FROM_LIST_BY_NAME, alertGroupName)));
    }

    public void createAlertGroup(String alertGroupName) {
        waitForPageToLoadAndJQueryProcessing();
        createGroupButton().click();
        createGroupInput().sendKeys(alertGroupName);
        createGroupSaveButton().click();
        waitForPageToLoad();
    }
}
