package com.thomsonreuters.pageobjects.pages.alerts.creation_bellow;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;


public class SelectContentBellowPage extends AbstractPage {
    private CommonMethods commonMethods = new CommonMethods();

    private static final String CONTENT_TYPE_CHECKBOX = "//li[@id='co_wizardStep_left_Home_WestlawUK_%s']/i";
    private static final By CONTENT_TYPE_LINKS = By.xpath("//div[@class='co_3Column']//li/a");
    private static final String CONTENT_TYPE_LINK = "//div[@class='co_3Column']//li/a[text()='%s']";
    private static final String YOUR_SELECTIONS_LINK = "//ul[@id='selectedItemsControlId']/li[contains(.,'%s')]";
    private static final By NO_CONTENT_ERROR_MESSAGE = By.xpath("//*[@id='contentValidationMessageBox']//*[contains(@class,'co_infoBox_message')]");
    private static final By CONTENT_TYPES_SET_TO_ALERT = By.xpath("//*[@id='contentSummaryDiv']//span");

    public WebElement contentTypeCheckbox(String contentTypeKeyword) {
        return waitForExpectedElement(By.xpath(String.format(CONTENT_TYPE_CHECKBOX, contentTypeKeyword.replaceAll("\\s",""))));
    }

    public List<String> contentTypeLinksList() {
        return commonMethods.getTextsFromWebElements(waitForExpectedElements(CONTENT_TYPE_LINKS));
    }

    public WebElement contentTypeLinkFromList(String contentType) {
        return waitForExpectedElement(By.xpath(String.format(CONTENT_TYPE_LINK, contentType)));
    }

    public boolean isContentTypeSelected(String contentType) {
        return contentTypeCheckbox(contentType).getAttribute("class").contains("co_checked");
    }

    public boolean isContentTypeDisplayedInYourSelections(String contentType) {
        return isElementDisplayed(By.xpath(String.format(YOUR_SELECTIONS_LINK, contentType)));
    }

    public WebElement contentTypeLinkInYourSelection(String contentType) {
        return waitForExpectedElement(By.xpath(String.format(YOUR_SELECTIONS_LINK, contentType)));
    }

    public String noContentInfoMessageText(){
        return waitForExpectedElement(NO_CONTENT_ERROR_MESSAGE).getText();
    }

    public List<String> contentTypesSetToAlert() {
        return commonMethods.getTextsFromWebElements(waitForExpectedElements(CONTENT_TYPES_SET_TO_ALERT));
    }
}
