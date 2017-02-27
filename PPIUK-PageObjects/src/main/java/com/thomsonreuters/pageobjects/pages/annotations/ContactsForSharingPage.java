package com.thomsonreuters.pageobjects.pages.annotations;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.*;

import java.util.List;

public class ContactsForSharingPage extends AbstractPage {
    public static final String CHECKBOX_PATH = "//a[@role='checkbox'][text()='%s']";

    public WebElement contactsLink() {
        return waitForExpectedElement(By.xpath("//a[contains (@class, 'contacts' )]"));
    }

    public WebElement groupField() {
        return waitForExpectedElement(By.id("coid_contacts_groups_searchBoxInput"));
    }

    public WebElement addGroupOption() {
        return waitForExpectedElement(By.id("groupListBoxWidgetAddGroup"));
    }

    public WebElement groupNameField() {
        return waitForExpectedElement(By.id("coid_contacts_newGroupName"));
    }

    public WebElement searchByNameField() {
        return waitForExpectedElement(By.id("coid_contacts_people_searchBoxInput_contacts_edit"));
    }

    public WebElement searchResult(String contact) {
        return waitForExpectedElement(By.xpath(String.format(CHECKBOX_PATH, contact)));
    }

    public WebElement findGroup(String group) {
        return waitForElementToBeClickableAndReturnElement(By.xpath(String.format(CHECKBOX_PATH, group)));
    }

    public WebElement saveGroupButton() {
        return waitForExpectedElement(By.id("coid_contacts_newGroup_saveButton"));
    }

    public boolean isGroupsItemPresent(String group) {
        return isElementDisplayed(By.xpath(String.format("//a[@role='checkbox'][@class = 'co_checkbox_unselected'][text()='%s']", group)));
    }

    public WebElement insertButton() {
        return waitForExpectedElement(By.id("coid_contacts_closeButton"));
    }
}
