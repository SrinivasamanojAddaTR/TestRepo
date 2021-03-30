package com.thomsonreuters.pageobjects.askReWrite;


import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AskRewriteAdminPage extends AbstractPage{
    private static final By PRACTICE_AREA_CREATE_BUTTON = By.xpath("//*[contains(@class, 'active')]//a[text()[contains(.,'Create new practice area')]]");
    private static final By PRACTICE_AREA_FORM = By.cssSelector(".active form[name=\"paDetails.form\"]");
    private static final By PRACTICE_AREA_SAVE_BUTTON = By.cssSelector(".active button[type='submit']");
    private static final By PRACTICE_AREA_CANCEL_BUTTON = By.cssSelector(".active .form-group a.btn");
    private static final By PRACTICE_AREA_TABLE_ROW = By.cssSelector(".active #practice-area-grid .ui-grid-row .ui-grid-cell:nth-of-type(2)");

    private static final String ADMIN_VIEW_TAB = "li[heading='%s']";

    public WebElement adminViewTabs(String tabName) {
        return waitForExpectedElement(By.cssSelector(String.format(ADMIN_VIEW_TAB, tabName)));
    }

    public WebElement createNewPracticeAreaButton() {
        return waitForExpectedElement(PRACTICE_AREA_CREATE_BUTTON);
    }

    public WebElement practiceAreaTableRow() {
        return waitForExpectedElement(PRACTICE_AREA_TABLE_ROW);
    }

    public WebElement practiceAreaForm() {
        return waitForExpectedAndClickableElement(PRACTICE_AREA_FORM);
    }

    public WebElement practiceAreaNameField() {
        return waitForExpectedElement(PracticeAreaFormField.NAME.getBy());
    }

    public WebElement practiceAreaValueOnFormField() {
        return waitForExpectedElement(PracticeAreaFormField.VALUE_ON_ASK_FORM.getBy());
    }

    public WebElement saveButton(){
        return waitForExpectedElement(PRACTICE_AREA_SAVE_BUTTON);
    }

    public WebElement cancelButton(){
        return waitForExpectedElement(PRACTICE_AREA_CANCEL_BUTTON);
    }

    public boolean isPracticeAreaFormPresent() {
        return isExists(PRACTICE_AREA_FORM);
    }

    public void saveChanges() {
        saveButton().click();
        waitForElementToDissappear(PRACTICE_AREA_SAVE_BUTTON);
    }

    public void cancelChanges() {
        cancelButton().click();
        waitForElementToDissappear(PRACTICE_AREA_CANCEL_BUTTON);
    }
}