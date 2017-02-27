package com.thomsonreuters.pageobjects.pages.ask;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.utils.ask.AskFormField;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;



public class AskFormPage extends AbstractPage {

    public WebElement submitButton() {
     return findElement(By.name("Submit"));
    }

    public boolean isAskFormDisplayed() {
        try {
            findElement(By.xpath("//div[@id='form']//self::div[@style='display: block;']"));
        } catch (NoSuchElementException nse) {
            return false;
        }
        return true;
    }

    public WebElement disclaimerTermsCheckbox() {
        return findElement(By.className("disclamer-check"));
    }

    public WebElement askFormPageTitle() {
        return waitForExpectedElement(By.xpath("//div[@class='page-contentContainer']/div/h1[text()='Ask a question']"));
    }

    public WebElement disclaimerText() {
        return waitForExpectedElement(By.id("disclaimer-text"));
    }

    public WebElement getContactDetailsLabel() {
        return waitForExpectedElement(By.xpath("//div[@id='form']//span[text()='(all fields are required)']"));
    }

    public WebElement getFieldErrorLabel(AskFormField askFormField) {
        return findElement(askFormField.getErrorBy());
    }
}
