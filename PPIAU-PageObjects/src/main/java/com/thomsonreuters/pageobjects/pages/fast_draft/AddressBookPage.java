package com.thomsonreuters.pageobjects.pages.fast_draft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddressBookPage extends AbstractPage {

    private CommonMethods comMethods = new CommonMethods();

    public WebElement createNewContact() {
        return comMethods.waitForElementToBeVisible(By.xpath("//*[text()=' Create new contact']"));
    }

    public WebElement closeAddressBook() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[text()=' Close']"));
    }

    public WebElement removeContactConfirmation() {
        return waitForExpectedElement(By.xpath("//button[@id='actionButton' and text()='Yes, delete it']"));
    }

    public WebElement removeContact(String contact) {
        return comMethods.waitForElementToBeVisible(
                By.xpath("(//a[text()=' " + contact + "']/following::button[text()='Delete'])[1]"));
    }

    public boolean isContactPresents(String contactName) {
    	return isElementDisplayed(By.xpath("//a[text()=' " + contactName + "']"));
    }
    
    public boolean isContactAbsent(String contactName) {
        return waitForElementToDissappear(By.xpath("//a[text()=' " + contactName + "']"));
    }

}
