package com.thomsonreuters.pageobjects.pages.alerts.creationBellow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.thomsonreuters.driver.framework.AbstractPage;


public class CustomizeDeliveryBellowPage extends AbstractPage {
    public WebElement emailDeliverySettingsButton() {
        return waitAndFindElement(By.id("coid_subBellow_collapselink"));
    }

    public WebElement contactsMessageListItem() {
        return waitAndFindElement(By.id("co_contacts_message"));
    }

    public WebElement inputedEmailListItem() {
		return waitForExpectedElement(By.xpath("//li[@class='co_contacts_addedContactsGroup']/a"), 5);
    }

    public WebElement emailSubscribersInput() {
        return waitAndFindElement(By.cssSelector("li.co_contacts_collector_addNew > input"));
    }

    public Select formatToDeliverSelect() {
        return new Select(getDriver.findElement(By.id("co_delivery_format_list")));
    }

    public Select detailLevelSelect() {
        return new Select(getDriver.findElement(By.id("coid_chkDdcDetailLevel")));
    }

    public WebElement continueDeliveryButton() {
        return waitAndFindElement(By.id("co_button_continue_Delivery"));
    }

    public List<WebElement> selectDeliveryCheckboxes() {
        return waitForExpectedElements(By.xpath("//ul[@id='DeliveryEnableCheckboxes']/li//input"));
    }

    public WebElement selectDeliveryCheckbox(String nameOfDelivery) {
        return waitAndFindElement(By.xpath("//ul[@id='DeliveryEnableCheckboxes']/li//label[contains(text(),'" + nameOfDelivery + "')]/input"));
    }
}
