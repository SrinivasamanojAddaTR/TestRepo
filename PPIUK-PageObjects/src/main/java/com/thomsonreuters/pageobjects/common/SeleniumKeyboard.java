package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

/**
 * Class for sending key events to browser
 */
public class SeleniumKeyboard {

    private WebDriverDiscovery webDriverDiscovery = new CommonMethods().getWebDriverDiscovery();

    public void sendEscape() {
        Actions action = new Actions(webDriverDiscovery.getWebDriver());
        action.sendKeys(Keys.ESCAPE);
    }

}