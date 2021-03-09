package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import org.junit.AfterClass;

@SuppressWarnings("java:S1118")
public class BaseCucumberTestRunner {

    @AfterClass
    public static void closeBrowser() {
        WebDriverDiscovery.close();
    }

}
