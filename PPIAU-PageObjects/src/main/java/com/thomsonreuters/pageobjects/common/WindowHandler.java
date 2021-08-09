package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import com.thomsonreuters.utils.TimeoutUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WindowHandler {

    private WebDriverDiscovery driver = new CommonMethods().getWebDriverDiscovery();

    public void fileDownloadAutomatically(WebElement fileDownloadElement) throws AWTException {
        Capabilities capabilities = ((RemoteWebDriver) driver.getWebDriver()).getCapabilities();
        String browserName = capabilities.getBrowserName();
        if (browserName.equalsIgnoreCase("firefox")) {
            fileDownloadElement.click();
            TimeoutUtils.sleepInSeconds(25);
            // create robot object
            Robot robot = new Robot();
            TimeoutUtils.sleepInSeconds(1);
            // Click "Enter" Button to download file
            robot.keyPress(KeyEvent.VK_ENTER);
            TimeoutUtils.sleepInSeconds(5);
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            fileDownloadElement.click();
            TimeoutUtils.sleepInSeconds(20);
        }
    }

    public void fileDownload(WebElement fileDownloadElement) throws AWTException {
        Capabilities capabilities = ((RemoteWebDriver) driver.getWebDriver()).getCapabilities();
        String browserName = capabilities.getBrowserName();
        if (browserName.equalsIgnoreCase("internet explorer")) {
            fileDownloadInIE(fileDownloadElement);
        }
        if (browserName.equalsIgnoreCase("firefox")) {
            fileDownloadInFF(fileDownloadElement);
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            fileDownloadElement.click();
            TimeoutUtils.sleepInSeconds(20);
        }
    }

    public boolean switchWindow(String windowTitle) {
        boolean switched = false;
        for (String handle : driver.getWebDriver().getWindowHandles()) {
            driver.getWebDriver().switchTo().window(handle);
            String actualWindowTitle = driver.getWebDriver().getTitle();
            if (actualWindowTitle.toLowerCase().contains(windowTitle.toLowerCase())) {
                switched = true;
                break;
            }
        }
        return switched;
    }

    public boolean closeWindow(String windowTitle) {
        boolean switched = false;
        for (String handle : driver.getWebDriver().getWindowHandles()) {
            driver.getWebDriver().switchTo().window(handle);
            String actualWindowTitle = driver.getWebDriver().getTitle();
            if (actualWindowTitle.toLowerCase().contains(windowTitle.toLowerCase())) {
                driver.getWebDriver().close();
                switched = true;
            }
        }
        for (String handle : driver.getWebDriver().getWindowHandles()) {
            driver.getWebDriver().switchTo().window(handle);
        }
        return switched;
    }

    private void fileDownloadInIE(WebElement fileDownloadElement) throws AWTException {
        TimeoutUtils.sleepInSeconds(2);
        // create robot object
        Robot robot = new Robot();

        // Get the focus on the element..don't use click since it stalls the driver
        fileDownloadElement.sendKeys("");

        // simulate pressing enter
        TimeoutUtils.sleepInSeconds(5);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Wait for the download manager to open
        TimeoutUtils.sleepInSeconds(15);

        // Switch to download manager tray via Alt+N
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_N);

        TimeoutUtils.sleepInSeconds(10);
        robot.keyRelease(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_ALT);
        TimeoutUtils.sleepInSeconds(10);
        // Switch to save
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        TimeoutUtils.sleepInSeconds(10);
        // Click Save Button
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        TimeoutUtils.sleepInSeconds(10);
    }

    private void fileDownloadInFF(WebElement fileDownloadElement) throws AWTException {
        fileDownloadElement.click();
        TimeoutUtils.sleepInSeconds(20);
        // create robot object
        Robot robot = new Robot();
        TimeoutUtils.sleepInSeconds(1);
        // Click Down Arrow Key to select "Save File" Radio Button
        robot.keyPress(KeyEvent.VK_DOWN);
        TimeoutUtils.sleepInSeconds(1);
        // Click 3 times Tab to take focus on "OK" Button
        robot.keyPress(KeyEvent.VK_TAB);
        TimeoutUtils.sleepInSeconds(1);
        robot.keyPress(KeyEvent.VK_TAB);
        TimeoutUtils.sleepInSeconds(1);
        // Click do this automatically
        robot.keyPress(KeyEvent.VK_SPACE);
        TimeoutUtils.sleepInSeconds(1);
        robot.keyPress(KeyEvent.VK_TAB);
        TimeoutUtils.sleepInSeconds(1);
        // Click "Enter" Button to download file
        robot.keyPress(KeyEvent.VK_ENTER);
        TimeoutUtils.sleepInSeconds(5);
    }

}
