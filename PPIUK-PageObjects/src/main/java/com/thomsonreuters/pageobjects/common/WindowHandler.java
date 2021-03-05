package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/*  =====================================================================================================================
    This library contains the below functions
    1) fileUpload - This function works for the File upload on the PLC web site(eg: Form E page while uploading a PDF)
    2) switchWindow -  This function is to switch to a new window
    3) closeWindow -  This function closes the window with a specific title
    4) fileDownload - This function downloads file in different browsers.
    ====================================================================================================================== */
public class WindowHandler {

    private WebDriverDiscovery driver = new CommonMethods().getWebDriverDiscovery();

    public void fileUpload(String path) throws AWTException {
        StringSelection stringSelection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        //native key strokes for CTRL, V and ENTER keys
        Robot robot = new Robot();
        robot.delay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);
    }

    public void fileDownloadAutomatically(WebElement fileDownloadElement) throws InterruptedException, AWTException {
        Capabilities capabilities = ((RemoteWebDriver) driver.getWebDriver()).getCapabilities();
        String browserName = capabilities.getBrowserName();
        if (browserName.equalsIgnoreCase("firefox")) {
            fileDownloadElement.click();
            Thread.sleep(25000L);
            // create robot object
            Robot robot = new Robot();
            Thread.sleep(1000L);
            // Click "Enter" Button to download file
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(5000L);
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            fileDownloadElement.click();
            Thread.sleep(20000L);
        }
    }

    public void fileDownload(WebElement fileDownloadElement) throws InterruptedException, AWTException {
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
            Thread.sleep(20000L);
        }
    }

    public boolean switchWindow(String windowTitle) {
        boolean switched = false;
        for (String handle : driver.getWebDriver().getWindowHandles()) {
            driver.getWebDriver().switchTo().window(handle);
            String ActualWindowTitle = driver.getWebDriver().getTitle();
            if (ActualWindowTitle.toLowerCase().contains(windowTitle.toLowerCase())) {
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
            String ActualWindowTitle = driver.getWebDriver().getTitle();
            if (ActualWindowTitle.toLowerCase().contains(windowTitle.toLowerCase())) {
                driver.getWebDriver().close();
                switched = true;
            }
        }
        for (String handle : driver.getWebDriver().getWindowHandles()) {
            driver.getWebDriver().switchTo().window(handle);
        }
        return switched;
    }

    private void fileDownloadInIE(WebElement fileDownloadElement) throws InterruptedException, AWTException {
        //FOR IE8
        Capabilities capabilities = ((RemoteWebDriver) driver.getWebDriver()).getCapabilities();
        if (capabilities.getVersion().equals("8")) {
            Thread.sleep(1000);
            // create robot object
            Robot robot = new Robot();

            // Get the focus on the element..don't use click since it stalls the driver
            fileDownloadElement.sendKeys("");

            // simulate pressing enter
            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // Wait for the download manager to open
            Thread.sleep(15000);

            // Switch to download manager
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            Thread.sleep(10000);
            // Click Save Manager Button
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(10000);
            // Click Save Explorer Button
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(5000L);

            // Close Download status window

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            Thread.sleep(10000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        // FOR IE9-11
        else {

            Thread.sleep(1000);
            // create robot object
            Robot robot = new Robot();

            // Get the focus on the element..don't use click since it stalls the driver
            fileDownloadElement.sendKeys("");

            // simulate pressing enter
            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // Wait for the download manager to open
            Thread.sleep(15000);

            // Switch to download manager tray via Alt+N
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_N);

            Thread.sleep(10000);
            robot.keyRelease(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_ALT);
            Thread.sleep(10000);
            // Switch to save
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            Thread.sleep(10000);
            // Click Save Button
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(5000L);
        }
    }

    private void fileDownloadInFF(WebElement fileDownloadElement) throws InterruptedException, AWTException {
        fileDownloadElement.click();
        Thread.sleep(20000L);
        // create robot object
        Robot robot = new Robot();
        Thread.sleep(1000L);
        // Click Down Arrow Key to select "Save File" Radio Button
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000L);
        // Click 3 times Tab to take focus on "OK" Button
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        // Click do this automatically
        robot.keyPress(KeyEvent.VK_SPACE);
        Thread.sleep(1000L);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        // Click "Enter" Button to download file
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(5000L);
    }

}
