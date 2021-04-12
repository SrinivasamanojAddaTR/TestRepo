package com.thomsonreuters.pageobjects.pages.annotations;

import com.thomsonreuters.driver.exception.PageOperationException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;


/**
 * This page object is to handle all operations and elements present on Insert/Edit Link popup present on Tinymce Editor.
 * <p/>
 * Created by UC186961 on 16/09/2015.
 */

public class InsertEditLink extends TinyMceEditor {

    public boolean isPopUpDisplayed() {
        return isExists(By.cssSelector(".mce-window.mce-in div.mce-title"))
                && waitForExpectedElement(By.cssSelector(".mce-window.mce-in div.mce-title")).getText()
                .contains("Insert link");
    }


    public boolean isTextDisplayed(String input) {
        return isExists(By.xpath("//label[text()='Text to display']/..//input"))
                && waitForExpectedElement(By.xpath("//label[text()='Text to display']/..//input")).getAttribute("value")
                .contains(input);
    }


    public boolean isTitleFieldDisplayed() {
        return isExists(By.xpath("//div[contains(@class,'mce-window mce-in')]//label[text()='Title']"));
    }


    /**
     * Verifies the Title field is displayed in Insert/Edit Link popup is displayed.
     * Returns boolean value as true if title is displayed otherwise false.
     *
     * @return boolean
     */

    public boolean isTargetFieldDisplayed() {
        return isExists(By.xpath("//div[contains(@class,'mce-window mce-in')]//label[text()='Target']"));
    }


    /**
     * Verifies the given target field is displayed in Insert/Edit Link popup is displayed.
     * Returns boolean value as true if target field is displayed otherwise false.
     *
     * @return boolean
     */

    public boolean isTextFieldDisplayed() {
        return isExists(By.xpath("//div[contains(@class,'mce-window mce-in')]//label[text()='Text to display']"));
    }


    /**
     * Verifies the text field is displayed in Insert/Edit Link popup is displayed.
     * Returns boolean value as true if text field is displayed otherwise false.
     *
     * @return boolean
     */

    public boolean isUrlFieldDisplayed() {
        return isExists(By.xpath("//div[contains(@class,'mce-window mce-in')]//label[text()='Url']"));
    }


    /**
     * Verifies the given url field is displayed in Insert/Edit Link popup is displayed.
     * Returns boolean value as true if url field is displayed otherwise false.
     *
     * @return boolean
     */


    public boolean isOKButtonDisplayed() {
        return isExists(By.xpath("//div[contains(@class,'mce-window mce-in')]//button[text()='Ok']"));
    }

    /**
     * Verifies the OK button is displayed in Insert/Edit Link popup is displayed.
     * Returns boolean value as true if Ok button is displayed otherwise false.
     *
     * @return boolean
     */


    public boolean isCancelButtonDisplayed() {
        return isExists(By.xpath("//div[contains(@class,'mce-window mce-in')]//button[text()='Cancel']"));
    }

    /**
     * Verifies the Cancel button is displayed in Insert/Edit Link popup is displayed.
     * Returns boolean value as true if cancel button is displayed otherwise false.
     *
     * @return boolean
     */


    /**
     * Enters the given url into url field present on Insert/Edit Link
     *
     * @param url
     */
    public void enterUrl(String url) {
        try {
            waitForExpectedElement(By.xpath("//div[contains(@class,'mce-window mce-in')]//label[text()='Url']/..//input")).sendKeys(url);
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the Url field on InserEditLink popup");
        }
    }

    /**
     * Clicks on OK button present on Insert/Edit Link popup
     */
    public void clickOK() {
        try {
            waitForExpectedElement(By.xpath("//div[contains(@class,'mce-window mce-in')]//button[text()='Ok']")).click();
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the OK button on InserEditLink popup");
        }
    }

    /**
     * Clicks on Cancel button present on Insert/Edit Link Popup
     */
    public void clickCancel() {
        try {
            waitForExpectedElement(By.xpath("//div[contains(@class,'mce-window mce-in')]//button[text()='Cancel']")).click();
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the Cancel button on InserEditLink popup");
        }
    }
}