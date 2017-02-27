package com.thomsonreuters.pageobjects.pages.plPlusResearchSearch;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class ResearchDelivery extends AbstractPage {

    public ResearchDelivery() {
    }

    public void selectMultiDocuments() {
        for (int i = 1; i <= 3; i++) {
            getDriver.findElement(By.id("cobalt_search_internationalCase_checkbox_" + i + "")).click();
        }
    }

    public WebElement saveToFolder() {
        return waitForExpectedElement(By.xpath(".//*[@id='co_saveToWidget']/div/a"));
    }

    public WebElement enterNewFolderName() {
        return waitForExpectedElement(By.id("cobalt_ro_folder_action_textbox"));
    }

    public WebElement clickNewFolder() {
        return retryingFindElement(By.cssSelector(".co_saveToNewFolder"));
    }

    public WebElement clickOkButton() {
        return waitForExpectedElement(By.cssSelector(".co_dropdownBox_ok"));
    }

}