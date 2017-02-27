package com.thomsonreuters.pageobjects.pages.mailinator;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MailinatorPage extends AbstractPage {

    public MailinatorPage() {
    }

    public WebElement userMailBoxHeading() {
        return waitForExpectedElement(By.id("publicInboxCtrl"));
    }

    public WebElement checkMailTextBox() {
        return waitForExpectedElement(By.id("inboxfield"));
    }

    public List<WebElement> emailFrom() {
        return waitForExpectedElements(By.xpath("//div[contains(@onclick, 'Message')]//div[contains(@class, 'outermail')][1]"), 90);
    }

    public List<WebElement> emailSubject() {
        return waitForExpectedElements(By.xpath("//div[contains(@onclick, 'Message')]//div[contains(@class, 'outermail')][2]"), 90);
    }

    public List<WebElement> emailTimeDesc() {
        return waitForElementsVisible(By.xpath("//div[contains(@onclick, 'Message')]//div[contains(@class, 'outermail')][3]"));
    }

    public WebElement checkItButton() {
        return waitForElementVisible(By.xpath("//button[contains(text(),'Go!')]"));
    }

    public WebElement deleteEmailButton() {
        return waitForElementVisible(By.id("public_delete_button"));
    }

    public List<WebElement> getMailCheckboxes() {
        return waitForExpectedElements(By.xpath("//div[./input[@type='checkbox']]//label"));
    }

}
