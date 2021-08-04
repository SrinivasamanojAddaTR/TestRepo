package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.driver.framework.WebDriverDiscovery;

/*  =====================================================================================================================
    This library contains the below functions
    1) fileUpload - This function works for the File upload on the PLC web site(eg: Form E page while uploading a PDF)
    2) switchWindow -  This function is to switch to a new window
    3) closeWindow -  This function closes the window with a specific title
    4) fileDownload - This function downloads file in different browsers.
    ====================================================================================================================== */
public class WindowHandler {

    private WebDriverDiscovery driver = new CommonMethods().getWebDriverDiscovery();

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
}
