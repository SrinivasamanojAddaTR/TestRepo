package com.thomsonreuters.pageobjects.pages.company;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;


import java.util.List;


public class AboutCompanyPage extends AbstractPage {

	private static final String TITLE_PATTERN = "//*[contains(@class, 'Heading') and contains(text(), %s)]";
	
	public WebElement specifiedTabInTableOfContents(String tabName) {
		return waitForExpectedElement(By.xpath("//div[@class='nav-list']//a[text()='" + tabName + "']"));
	}
	
	public List<WebElement> tabsInTableOfContents() {
		return waitForExpectedElements(By.xpath("//div[@class='nav-list']//a"));
	}
	
	public WebElement activeTabInTableOfContents() {
		return waitForExpectedElement(By.xpath("//div[@class='nav-list']//li[@class='active']"));
	}
	
	public WebElement specifiedTab(String tabName) {
		return waitForExpectedElement(AboutCompanyPageTabs.get(tabName).getXpath());
	}

	public boolean isPageHeaderDisplayed(String headerTitle){
		return isElementDisplayed(By.xpath(String.format(TITLE_PATTERN, Quotes.escape(headerTitle))));
	}
	
	public WebElement activeTab() {
		return waitForExpectedElement(By.xpath("//li[@class='co_tabLeft co_tabActive']//a"));
	}
	
	public WebElement textSection() {
		return waitForExpectedElement(By.id("one-column-page"));
	}

}
