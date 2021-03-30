package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddressBookPage extends AbstractPage {

    private CommonMethods comMethods = new CommonMethods();

    public WebElement createNewContact() {
        return comMethods.waitForElementToBeVisible(By.xpath("//*[text()=' Create new contact']"), 10000);
    }

    public WebElement closeAddressBook() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[text()=' Close']"), 10000);
    }

    public WebElement removeContactConfirmation() {
        return waitForExpectedElement(By.xpath("//button[@id='actionButton' and text()='Yes, delete it']"));
    }

    public WebElement removeContact(String contact) {
        return comMethods.waitForElementToBeVisible(
                By.xpath("(//a[text()=' " + contact + "']/following::button[text()='Delete'])[1]"), 10000);
    }

    public boolean isContactPresents(String contactName) {
    	return isElementDisplayed(By.xpath("//a[text()=' " + contactName + "']"));
    }
    
    public boolean isContactAbsent(String contactName) {
        return waitForElementToDissappear(By.xpath("//a[text()=' " + contactName + "']"));
    }

}
