package com.thomsonreuters.pageobjects.pages.login;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class WelcomePage extends AbstractPage {

    private static final By CONTINUE_BUTTON = By.id("co_clientIDContinueButton");

    /**
     * This method does the waiting for the complete page loading once click on continue button.
     */
    public void waitUntilPageLoadedAfterContinue() {
        try {
            waitForElementInvisible(CONTINUE_BUTTON);
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to wait to disappear the continue button :" + te.getMessage());
        }
    }

    public WebElement continueButton() {
        return waitForExpectedElement(CONTINUE_BUTTON);
    }

    public boolean isContinueButtonPresent() {
        return isExists(CONTINUE_BUTTON);
    }

    public WebElement clientID() {
        return waitForExpectedElement(By.id("co_clientIDTextbox"));
    }

    public void fillClientID(String clientID) {
        clientID().clear();
        clientID().sendKeys(clientID);
        continueButton().click();

    }
    public boolean isContinueButtonLoading(){
        return isExists(By.xpath("//*[contains(@class, 'co_buttonLoading')]"));
    }
}
