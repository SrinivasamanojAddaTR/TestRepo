package com.thomsonreuters.pageobjects.pages.wlau;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WLAUDocumentPage extends AbstractPage {
    private static final By DOCUMENT_BODY = By.id("docContent");
    private static final By SIGNOF_LINK = By.xpath("//div[@id='navigationTree']//a[@id='logoutLink']");
    private static final By POPUP = By.xpath("//div[@id='modal-splash']//div[@class='modal-content']");
    private static final By CLOSE_POPUP_BUTTON = By.xpath("//div[@id='modal-splash']//button[@class='close']");

    public WebElement documentBody() {
        return waitForExpectedElement(DOCUMENT_BODY);
    }

    public WebElement signoutLink() {
        return waitForExpectedElement(SIGNOF_LINK);
    }

    public WebElement closePopUpButton() {
        return waitForExpectedElement(CLOSE_POPUP_BUTTON);
    }

    public boolean isPopUpInWLAUDisplayed() {
        return isElementDisplayed(POPUP);
    }
}
