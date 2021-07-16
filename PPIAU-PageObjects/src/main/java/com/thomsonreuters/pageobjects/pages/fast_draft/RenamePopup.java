package com.thomsonreuters.pageobjects.pages.fast_draft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RenamePopup extends AbstractPage {

    private static final String ELEMENT_NAME_INPUT = "//input[@id='NIName']";

    private CommonMethods comMethods = new CommonMethods();

    public WebElement projectOrDocument() {
        return comMethods.waitForElementToBeVisible(By.xpath(ELEMENT_NAME_INPUT));
    }

    public WebElement renameButton() {
        return findElement(By.xpath("//button[@id='NIButton']"));
    }

    public WebElement cancelButton() {
        return findElement(By.xpath("//*[@id='NameItemModel']//button[text()='Cancel']"));
    }

}
