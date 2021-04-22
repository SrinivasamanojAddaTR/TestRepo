package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewProjectPopup extends AbstractPage {

    private static final String PROJECT_NAME = "//input[@id='projectName']";
    private static final String DOCUMENT_NAME = "//input[@id='docName']";
    private static final String CREATE_PROJECT_BUTTON = "//input[@id='createDocument']";

    public WebElement documentName() {
        return waitForExpectedElement(By.xpath(DOCUMENT_NAME));
    }

    public WebElement projectName() {
        return findElement(By.xpath(PROJECT_NAME));
    }

    public WebElement create() {
        return findElement(By.xpath(CREATE_PROJECT_BUTTON));
    }
}