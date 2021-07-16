package com.thomsonreuters.pageobjects.pages.site_structure;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LinkBuilderPage extends AbstractPage {

    private static final By COPY_LINK_ID = By.id("co_linkBuilderLightbox_CopyButton");
    private static final By CANCEL_ID = By.id("co_linkBuilderLightbox_CancelButton");
    private static final By LINK_FIELD_ID = By.id("co_LinkBuilderUrl");
    private static final By CLOSE_ID = By.id("co_linkBuilderLightbox_CloseButton");

    public WebElement linkbuildericon() {
        return waitForExpectedElement(By.xpath("//a[@id='co_linkBuilder']"));
    }

    public WebElement upgradeMessage() {
        return waitForExpectedElement(By.xpath("//div[@id='co_unsubscribedContentUpgrade']"));
    }

    public boolean isLinkBuilderIconPresent() {
        return isExists(By.id("co_linkBuilder"));
    }

    public boolean isUpgradeMessagePresent() {
        return isExists(By.xpath("//div[@id='co_unsubscribedContentUpgrade']"));
    }

    public WebElement linkbuilderNotificationMessage(){
        return waitForExpectedElement(By.xpath("//div[@class='co_infoBox_message' and contains(.,'The link has been copied successfully')]"));
    }

    public boolean isLinkbuilderNotificationMessagePresent() {
        return isExists(By.xpath("//div[@class='co_infoBox_message' and contains(.,'The link has been copied successfully')]"));
    }

    public boolean isLinkBuilderPopupPresent() {
        return isElementDisplayed(By.id("co_linkBuilderLightbox"));
    }

    public boolean isCopyLinkButtonDisplayed() {
        return isElementDisplayed(COPY_LINK_ID);
    }

    public boolean isCancelButtonDisplayed() {
        return isElementDisplayed(CANCEL_ID);
    }

    public boolean isFieldWithUrlDisplayed() {
        return isElementDisplayed(LINK_FIELD_ID);
    }

    public boolean isCloseButtonDisplayed() {
        return isElementDisplayed(CLOSE_ID);
    }

    public WebElement getFieldWithUrl() {
        return waitForExpectedElement(LINK_FIELD_ID);
    }

    public WebElement getCopyLinkButton() {
        return waitForExpectedElement(COPY_LINK_ID);
    }

    public WebElement getCancelButton() {
        return waitForExpectedElement(CANCEL_ID);
    }

    public WebElement getCloseButton() {
        return waitForExpectedElement(CLOSE_ID);
    }

    public WebElement getTestInput() {
        String inputIdName = "LB_TEST_TEXT";
        By inputId = By.id(inputIdName);
        if (!isExists(inputId)) {
            executeScript("$('header').prepend('<input type=\"text\" id=\"" + inputIdName + "\" size=\"200\"/>')");
        }
        return waitForExpectedElement(inputId);
    }
}
