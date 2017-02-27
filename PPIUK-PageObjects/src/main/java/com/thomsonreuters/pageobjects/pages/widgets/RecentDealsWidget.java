package com.thomsonreuters.pageobjects.pages.widgets;

import com.thomsonreuters.driver.framework.AbstractPage;

import org.openqa.selenium.By;

public class RecentDealsWidget extends AbstractPage {

	private static final String WIDGET_NAME = "Recent deals";

	public boolean isHeaderPresent() {
		return isElementPresent(By.xpath("//h3[text()='" + WIDGET_NAME + "']"));
	}

	public int getDocumentLinksCount() {
		return findElements(
				By.xpath("//h3[text()='" + WIDGET_NAME + "']/following-sibling::div[@class='co_genericBoxContent']//a"))
						.size();
	}

}
