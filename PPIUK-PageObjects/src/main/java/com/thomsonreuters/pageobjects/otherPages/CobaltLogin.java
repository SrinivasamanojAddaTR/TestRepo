package com.thomsonreuters.pageobjects.otherPages;

import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
//used for getting access to string values from AbstractPage class for setAnnotation User method
//the methods from the class are not used anywhere
public class CobaltLogin extends AbstractPage {

    private OnepassLogin loginPage;

    private WelcomePage welcome;

    private static final By SIGN_OFF_LOCATOR = By.xpath(".//li[@id='co_signOffContainer']/a[text()='Sign Off']");
    private static final By SIGN_ON_BUTTON_AFTER_SIGN_OFF = By.id("coid_website_signOffRegion");

    public CobaltLogin() {
    }

    public void loginToCobalt(String username, String password) {
        loginPage.usernameTextField().sendKeys(username);
        loginPage.passwordTextField().sendKeys(password);
        loginPage.signOnButton().click();
    }

    //TODO [Phase1] method is unused need to test and remove
    /*public void loginWithClientId(String username, String password, String clientID) {
        loginToCobalt(username, password);
        welcome.clientID().clear();
        welcome.clientID().sendKeys(clientID);
        welcome.continueButton().click();
    }*/

    /**
     * This method does the login into Pl+ site with default username, password and client id.
     */
    //TODO [Phase1] method is unused need to test and remove
    /*public void loginWithClientId() {
        loginToCobalt(defaultUserName, defaultPassWord);
        welcome.clientID().clear();
        welcome.clientID().sendKeys(clientID);
        welcome.continueButton().click();
        welcome.waitUntilPageLoadedAfterContinue();
    }*/

    /**
     * This method verifies the signOff button present or not, so that returning boolean value
     *
     * @return boolean
     */
    //TODO [Phase1] method is unused need to test and remove
    /*public boolean isUserLoggedInAlready() {
        try {
            //waitForExpectedElement(SIGN_OFF_LOCATOR, 10).isDisplayed();
            return (waitForExpectedElement(By.linkText(loginPage.defaultUserName), 10).isDisplayed());
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return false;
    }*/

    //TODO [Phase1] method is unused need to test and remove
    /*public void loginToCobalt() {
        loginToCobalt(loginPage.defaultUserName, loginPage.defaultPassWord);
    }*/

    //TODO [Phase1] method is unused need to test and remove
   /* public void clickSignOnButtonOnSignOffPage() {
        waitForExpectedElement(SIGN_ON_BUTTON_AFTER_SIGN_OFF).click();
    }*/

    //TODO [Phase1] method is unused need to test and remove
   /* public boolean isSignOnButtonOnSignOffPage() {
        try {
            return waitForExpectedElement(SIGN_ON_BUTTON_AFTER_SIGN_OFF, 5).isDisplayed();
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return false;
    }*/

}