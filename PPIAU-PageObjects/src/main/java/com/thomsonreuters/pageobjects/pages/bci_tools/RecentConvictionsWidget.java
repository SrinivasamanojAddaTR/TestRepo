package com.thomsonreuters.pageobjects.pages.bci_tools;

import java.util.List;

import com.thomsonreuters.pageobjects.pages.legal_updates.LegalUpdatesWidgetPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RecentConvictionsWidget extends LegalUpdatesWidgetPage {
	
	public List<WebElement> getAllProsecutingAuthorities(String widgetName) {
		return waitForExpectedElements(By.xpath(String.format("//div[@id='coid_website_browseRightColumn']//h3[text()='%s']/following-sibling::div[@class='co_genericBoxContent']//div[@class='co_artifactContent']//h4//span[@class='co_resource_name']", widgetName)));
	}

}
