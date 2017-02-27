package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProjectPage extends AbstractPage {

    private final static String DOCUMENT = "//tr[@data-title='%s']";
    private final static String ACTIONS_BUTTON = DOCUMENT + "//a[text()=' Actions ']";
    private final static String DELETE_DOCUMENT_BUTTON = DOCUMENT + "//a[@class='doc-delete']";
    private final static String RENAME_DOCUMENT_BUTTON = DOCUMENT + "//a[@class='doc-rename']";
    private final static String UPLOAD_FORM_E_BUTTON = DOCUMENT + "//button[text()=' Upload']";

    private CommonMethods comMethods = new CommonMethods();

    public WebElement openDocument(String document) {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[text()='" + document + "']"), 10000);
    }

    public WebElement createNewDocument() {
        return comMethods.waitForElementToBeVisible(By.xpath("//*[text()=' Create new document']"), 10000);
    }

    public WebElement deleteDocumentButton(String documentName) {
        return waitForExpectedElement(By.xpath(String.format(DELETE_DOCUMENT_BUTTON, documentName)));
    }

    public WebElement confirmationYes() {
        return waitForExpectedElement(By.xpath("//*[@id='ConfirmActionModal']//*[@id='actionButton' and text()='Yes, delete it']"));
    }

    public WebElement confirmationCancel() {
        return waitForExpectedElement(By.xpath("//*[@id='ConfirmActionModal']//*[text()='Cancel']"));
    }

    public boolean isDocumentPresent(String documentName) {
        WebElement project = null;
        try {
            project = comMethods.waitForElementToBeVisible(By.xpath(String.format(DOCUMENT, documentName)), 10000);
            project.isDisplayed();
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public WebElement actionsButton(String elementName) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(ACTIONS_BUTTON, elementName)), 10000);
    }

    public WebElement renameDocument(String documentName) {
        return waitForExpectedElement(By.xpath(String.format(RENAME_DOCUMENT_BUTTON, documentName)));
    }

    public WebElement uploadFormE(String documentName) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(UPLOAD_FORM_E_BUTTON, documentName)), 10000);
    }

    public WebElement selectUploadFormE() {
        return comMethods.waitForElementToBeVisible(By.id("UFform"), 10000);
    }

    public WebElement upload() {
        return comMethods.waitForElementToBeVisible(By.id("UFbutton"), 10000);
    }

    public boolean isDocumentAbsent(String documentName) {
        return waitForElementAbsent(By.xpath(String.format(DOCUMENT, documentName)));
    }

}
