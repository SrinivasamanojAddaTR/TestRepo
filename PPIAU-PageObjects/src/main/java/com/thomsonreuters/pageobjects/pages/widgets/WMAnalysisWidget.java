package com.thomsonreuters.pageobjects.pages.widgets;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;

public class WMAnalysisWidget extends AbstractPage {

	public boolean isHeaderPresent() {
		return isExists(By.xpath("//h3[text()=\"What's Market analysis\"]"));
	}

}
