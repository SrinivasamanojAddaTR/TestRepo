package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SaveProjectPopup extends AbstractPage {

    private final static String PROJECT_NAME = "//input[@id='NIName2']";
    private final static String DOCUMENT_NAME = "//input[@id='NIName']";
    private final static String SAVE_PROJECT_BUTTON = "//button[@id='NIButton']";

    public WebElement documentName() {
        return waitForExpectedElement(By.xpath(DOCUMENT_NAME));
    }

    public WebElement projectName() {
        return waitForExpectedElement(By.xpath(PROJECT_NAME));
    }

    public WebElement save() {
        return waitForExpectedElement(By.xpath(SAVE_PROJECT_BUTTON));
    }
}