package com.thomsonreuters.pageobjects.other_pages;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WelcomePage extends AbstractPage {

    private static final By CONTINUE_BUTTON = By.id("co_clientIDContinueButton");

    public WebElement continueButton() {
        return waitForExpectedElement(CONTINUE_BUTTON);
    }

    public WebElement clientID() {
        return waitForExpectedElement(By.id("co_clientIDTextbox"));
    }

    public void fillClientID(String clientID) {
        clientID().clear();
        clientID().sendKeys(clientID);
        continueButton().click();
    }
    
    public boolean isClientIdPageOnLoginDisplayed(){
		return isElementDisplayed(By.id("co_clientIdLightbox"));
	}
}
