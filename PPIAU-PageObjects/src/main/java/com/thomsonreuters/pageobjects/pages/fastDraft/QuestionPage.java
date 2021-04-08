package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class QuestionPage extends AbstractPage {

    private CommonMethods comMethods = new CommonMethods();

    private static final String PAGE = "//*[@class='zevon-question-pages']//a[text()=\"%s\"]";
    private static final String TEXT_FIELD = "//input[@id='%s']";
    private static final String DROPDOWN_FIELD = "//select[@id='%s']";

    public WebElement page(String page) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(PAGE, page)));
    }

    public WebElement textField(String fieldId) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(TEXT_FIELD, fieldId)));
    }

    public WebElement dropDownField(String fieldId) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(DROPDOWN_FIELD, fieldId)));
    }

    public WebElement dropDownFieldOption(String fieldId, String optionValue) {
        return findElement(By.xpath(String.format(DROPDOWN_FIELD, fieldId) + "//option[@value='" + optionValue + "']"));
    }

    public void checkQuestionPageForDocument(String documentName) {
        comMethods.waitForElementToBeVisible(By.xpath("//*[contains(text(),'" + documentName + " Questions')]"));
    }

    public WebElement saveProject() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[@title='Save the document']"));
    }

    public WebElement save() {
        return waitForExpectedElement(By.xpath("//button[text()='Save']"));
    }

    public WebElement updateContactField(String contactToUpdate) {
        return comMethods.waitForElementToBeVisible(By.xpath("//div[@id='" + contactToUpdate + "']//button"));
    }

    public WebElement chooseContact(String contactToSet) {
        return comMethods.waitForElementToBeVisible(
                By.xpath("//a[text()='" + contactToSet + "']/parent::td//preceding-sibling::td/input"));
    }

    public void checkContactHasValue(String contactToUpdate, String contactToSet) {
    	WebElement contact = comMethods.waitForElementToBeVisible(
                By.xpath("//div[@id='" + contactToUpdate + "']//a[@class='item-edit' and text()='" + contactToSet
                        + "']"));
		Assert.assertNotNull(contact, "Contact '" + contactToUpdate + "' has no value '" + contactToSet + "'");
    }

    public void checkRemoveContactButtonPresents(String contactToUpdate, String contactToSet) {
    	WebElement contact = comMethods.waitForElementToBeVisible(
                By.xpath("//div[@id='" + contactToUpdate + "']//button[@data-label='" + contactToSet
                        + "' and text()='Remove']"));
		Assert.assertNotNull(contact, "Contact '" + contactToUpdate + "' has no value '" + contactToSet + "'");
    }

}
