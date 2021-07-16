package com.thomsonreuters.pageobjects.pages.fast_draft;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SaveProjectPopup extends AbstractPage {

    private static final String PROJECT_NAME = "//input[@id='NIName2']";
    private static final String DOCUMENT_NAME = "//input[@id='NIName']";
    private static final String SAVE_PROJECT_BUTTON = "//button[@id='NIButton']";

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