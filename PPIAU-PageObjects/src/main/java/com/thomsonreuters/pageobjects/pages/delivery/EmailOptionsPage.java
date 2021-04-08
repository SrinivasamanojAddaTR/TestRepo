package com.thomsonreuters.pageobjects.pages.delivery;

import com.thomsonreuters.pageobjects.utils.delivery.DeliveryFormField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * This is the pop up which appears when the user selects the email option within delivery.
 */

public class EmailOptionsPage extends CommonDeliveryOptionsPage {

    /**
     * This is the email address field
     */
    public WebElement emailAddressField() {
        return waitForExpectedElement(DeliveryFormField.TO.getBy());
    }

    /**
     * This is the subject field
     */
    public WebElement subjectField() {
        return waitForExpectedElement(DeliveryFormField.SUBJECT.getBy());
    }

    public WebElement emailNote() {
        return findElement(DeliveryFormField.EMAIL_NOTE.getBy());
    }

    /**
     * This is the format dropdown
     */
    public WebElement formatDropdown() {
        return findElement(DeliveryFormField.FORMAT.getBy());
    }

    /**
     * This is the email button for submission of the request
     */
    public WebElement emailButton() {
        return waitForExpectedElement(By.id("co_deliveryEmailButton"));
    }

    public WebElement deliveryMessage() {
        return waitForExpectedElement(By.id("co_deliveryWaitMessageTitle"));
    }

    public WebElement waitForSuccessDeliveryMessage(String text) {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryWaitMessageTitle'][text()=\"" + text + "\"]"));
    }

    /**
     * This is dropdown message, that is showing when user enters invalid email in 'To' field on 'Email' delivery popup.
     */
    public WebElement emailAddressErrorMessage() {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryEmailAddressErrorPlaceHolder']//div[text()='Check address formatting.']"));}

    
	public boolean isFormatDropdownBlockWithStylingDisplayed() {
		return isElementDisplayed(By
				.xpath(".//*[contains(@class,'co_deliveryContentBottom') and contains(@class, 'co_clear')]//*[text()='Format'] "));
	}
    
}