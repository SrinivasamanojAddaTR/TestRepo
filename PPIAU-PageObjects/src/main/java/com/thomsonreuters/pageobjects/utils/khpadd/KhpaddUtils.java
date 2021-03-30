package com.thomsonreuters.pageobjects.utils.khpadd;

import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import com.thomsonreuters.pageobjects.common.CommonMethods;

public class KhpaddUtils {

	private WebDriverDiscovery webDriverDiscovery = new CommonMethods().getWebDriverDiscovery();

	public String getUrlForCountry(String country) {
	    return country + "." + webDriverDiscovery.getCurrentRootAddress(false);
	}
	
}
