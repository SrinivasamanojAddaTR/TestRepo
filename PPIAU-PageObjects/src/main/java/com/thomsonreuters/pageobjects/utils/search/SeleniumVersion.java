package com.thomsonreuters.pageobjects.utils.search;

/**
 * Created by Pavel_Ardenka on 30/01/2017.
 * DO NOT USE! Class specific for S&B project only and will be removed in future. There is no universal way found
 * to determine which Selenium version is in use.
 * Determining selenium version in runtime. Based on system properties defined in the profiles sel2 and sel3
 * in the project POM file
 */
public final class SeleniumVersion {

    private SeleniumVersion() {
    }

    /**
     * Is selenium 3 used
     *
     * @return True - if current build was started using selenium 3. False - otherwise.
     */
    public static boolean isThird() {
        return System.getProperty("selenium.version", "2").equalsIgnoreCase("3");
    }

}
