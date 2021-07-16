package com.thomsonreuters.pageobjects.pages.alerts.creation_bellow;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasicsBellowPage extends AbstractPage {

    private static final By ALERT_NAME_INPUT = By.id("optionsAlertName");
    private static final By ALERT_DESCRIPTION_INPUT = By.id("optionsAlertDescription");
    private static final By ALERT_NAME_ERROR_MESSAGE = By.xpath("//div[@id='inputAlertNameInfoBox']//div[contains(@class,'co_infoBox_message')]");
    private static final By ALERT_NAME_VALIDATION_BUTTON = By.xpath("//button[@id='co_button_continue_Basics' and .='Validating']");
    private static final By CLIENT_ID_LABEL = By.className("co_clientIDInline_label");
    private static final By ALERT_CLIENT_ID = By.className("co_clientIDInline_recent");
    private static final By CLIENT_ID_CHANGE_OPTION = By.className("co_clientIDInline_change");
    private static final By CLIENT_ID_CHANGE_POP_UP = By.id("co_changeClientId_lightbox");
    private static final By CLIENT_ID_CHANGE_POP_UP_CHANGE_BTN = By.id("co_clientIDOOPContinueButton");
    private static final By CLIENT_ID_CHANGE_POP_UP_CANCEL_BTN = By.id("co_clientIDOOPCancelLink");
    private static final By CLIENT_ID_CHANGE_POP_UP_TEXTBOX = By.id("co_clientIDOOPTextbox");
    private static final By CLIENT_ID_CHANGE_POP_UP_RECENTS_DROPDOWN = By.id("co_clientIDOOPRecentsDropdown");
    private static final By CLIENT_ID_CHANGE_POP_UP_RECENTS_LIST = By.id("co_clientIDOOPAutocomplete");
    private static final By ASSIGN_TO_ALERT_GROUP_OPTION = By.id("coid_alerts_widgets_alertGroups_selector_assignToLink");
    private static final By ASSIGNED_ALERT_GROUP_NAME = By.id("coid_alerts_widgets_alertGroups_selector_assignToPath");
    private static final By EDIT_ALERT_GROUP_OPTION = By.id("coid_alerts_widgets_alertGroups_selector_editAlertGroupsLink");
    private static final By REMOVE_ALERT_GROUP_OPTION = By.id("coid_alerts_widgets_alertGroups_selector_cancelAlertGroupsLink");


    public WebElement alertNameInput() {
        return waitForExpectedElement(ALERT_NAME_INPUT);
    }

    public boolean isAlertNameInputDisplayed() {
        return isElementDisplayed(ALERT_NAME_INPUT);
    }

    public WebElement alertNameErrorMessage() {
        return waitForExpectedElement(ALERT_NAME_ERROR_MESSAGE);
    }

    public WebElement alertDescriptionInput() {
        return waitForExpectedElement(ALERT_DESCRIPTION_INPUT);
    }

    public boolean isAlertDescriptionInputDisplayed() {
        return isElementDisplayed(ALERT_DESCRIPTION_INPUT);
    }

    public WebElement alertNameValidationButton() {
        return waitForExpectedElement(ALERT_NAME_VALIDATION_BUTTON);
    }

    public boolean isAlertNameValidationButtonDisplayed() {
        return isElementDisplayed(ALERT_NAME_VALIDATION_BUTTON);
    }

    public boolean isClientIDLabelDisplayed() {
        return isElementDisplayed(CLIENT_ID_LABEL);
    }

    public WebElement actualAlertClientID() {
        return waitForExpectedElement(ALERT_CLIENT_ID);
    }

    public boolean isActualClientIDDisplayed() {
        return isElementDisplayed(ALERT_CLIENT_ID);
    }

    public WebElement clientIDChangeOption() {
        return waitForExpectedElement(CLIENT_ID_CHANGE_OPTION);
    }

    public boolean isClientIDChangeOptionDisplayed() {
        return isElementDisplayed(CLIENT_ID_CHANGE_OPTION);
    }

    public WebElement assignToAlertGroupOption() {
        return waitForExpectedElement(ASSIGN_TO_ALERT_GROUP_OPTION);
    }

    public boolean isAssignToAlertGroupOptionDisplayed() {
        return isElementDisplayed(ASSIGN_TO_ALERT_GROUP_OPTION);
    }

    public boolean isClientIDChangePopUpDisplayed() {
        return isElementDisplayed(CLIENT_ID_CHANGE_POP_UP);
    }

    public WebElement clientIDChangePopUpCancel() {
        return waitForExpectedElement(CLIENT_ID_CHANGE_POP_UP_CANCEL_BTN);
    }

    public boolean isClientIDChangePopUpCancelDisplayed() {
        return isElementDisplayed(CLIENT_ID_CHANGE_POP_UP_CANCEL_BTN);
    }

    public WebElement clientIDChangePopUpChange() {
        return waitForExpectedElement(CLIENT_ID_CHANGE_POP_UP_CHANGE_BTN);
    }

    public boolean isClientIDChangePopUpChangeDisplayed() {
        return isElementDisplayed(CLIENT_ID_CHANGE_POP_UP_CHANGE_BTN);
    }

    public WebElement clientIDChangePopUpTextbox() {
        return waitForExpectedElement(CLIENT_ID_CHANGE_POP_UP_TEXTBOX);
    }

    public boolean isClientIDChangePopUpTextboxDisplayed() {
        return isElementDisplayed(CLIENT_ID_CHANGE_POP_UP_TEXTBOX);
    }

    public String getTextboxSelectedClientIDValue() {
        return clientIDChangePopUpTextbox().getAttribute("value");
    }

    public WebElement clientIDChangePopUpRecentsDropdown() {
        return waitForExpectedElement(CLIENT_ID_CHANGE_POP_UP_RECENTS_DROPDOWN);
    }

    public boolean isClientIDChangePopUpRecentsDropdownDisplayed() {
        return isElementDisplayed(CLIENT_ID_CHANGE_POP_UP_RECENTS_DROPDOWN);
    }

    public WebElement clientIDChangePopUpRecentsList() {
        return waitForExpectedElement(CLIENT_ID_CHANGE_POP_UP_RECENTS_LIST);
    }

    public boolean isClientIDChangePopUpRecentListDisplayed() {
        return isElementDisplayed(CLIENT_ID_CHANGE_POP_UP_RECENTS_LIST);
    }

    public WebElement assignedAlertGroupName() {
        return waitForExpectedElement(ASSIGNED_ALERT_GROUP_NAME);
    }

    public boolean isEditAlertGroupLinkDisplayed() {
        return isElementDisplayed(EDIT_ALERT_GROUP_OPTION);
    }

    public boolean isRemoveAlertGroupLinkDisplayed() {
        return isElementDisplayed(REMOVE_ALERT_GROUP_OPTION);
    }
}
