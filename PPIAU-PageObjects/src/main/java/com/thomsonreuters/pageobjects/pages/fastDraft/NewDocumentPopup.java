package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewDocumentPopup extends AbstractPage {

    private static final String DOCUMENT_NAME = "//input[@id='docName']";
    private static final String CREATE_DOCUMENT_BUTTON = "//input[@id='createDocument']";

    public WebElement documentName() {
        return waitForExpectedElement(By.xpath(DOCUMENT_NAME));
    }

    public WebElement createDocument() {
        return findElement(By.xpath(CREATE_DOCUMENT_BUTTON));
    }

}