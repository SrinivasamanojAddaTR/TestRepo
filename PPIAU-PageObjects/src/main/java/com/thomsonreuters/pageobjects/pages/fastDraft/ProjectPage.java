package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProjectPage extends AbstractPage {

    private static final String DOCUMENT = "//tr[@data-title='%s']";
    private static final String ACTIONS_BUTTON = DOCUMENT + "//a[text()=' Actions ']";
    private static final String DELETE_DOCUMENT_BUTTON = DOCUMENT + "//a[@class='doc-delete']";
    private static final String RENAME_DOCUMENT_BUTTON = DOCUMENT + "//a[@class='doc-rename']";
    private static final String UPLOAD_FORM_E_BUTTON = DOCUMENT + "//button[text()=' Upload']";

    private CommonMethods comMethods = new CommonMethods();

    public WebElement openDocument(String document) {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[text()='" + document + "']"));
    }

    public WebElement createNewDocument() {
        return comMethods.waitForElementToBeVisible(By.xpath("//*[text()=' Create new document']"));
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
            project = comMethods.waitForElementToBeVisible(By.xpath(String.format(DOCUMENT, documentName)));
            project.isDisplayed();
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public WebElement actionsButton(String elementName) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(ACTIONS_BUTTON, elementName)));
    }

    public WebElement renameDocument(String documentName) {
        return waitForExpectedElement(By.xpath(String.format(RENAME_DOCUMENT_BUTTON, documentName)));
    }

    public WebElement uploadFormE(String documentName) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(UPLOAD_FORM_E_BUTTON, documentName)));
    }

    public WebElement selectUploadFormE() {
        return comMethods.waitForElementToBeVisible(By.id("UFform"));
    }

    public WebElement upload() {
        return comMethods.waitForElementToBeVisible(By.id("UFbutton"));
    }

    public boolean isDocumentAbsent(String documentName) {
        return waitForElementToDissappear(By.xpath(String.format(DOCUMENT, documentName)));
    }

}
