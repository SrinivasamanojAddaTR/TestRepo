package com.thomsonreuters.pageobjects.pages.legalUpdates;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


public class LegalUpdatesWidgetPage extends AbstractPage {
	
	private List<String> updatesTitles;
	
	private List<String> updatesDates;

	public LegalUpdatesWidgetPage() {
	}

	public WebElement subscribeButton() {
		return waitForExpectedElement(By.id("SubscribeButton"));

	}

	public WebElement veiwAllUpdatesLink() {
		return waitForExpectedElement(By.linkText("View all"));
	}
	
	public WebElement veiwAllUpdatesLink(String widgetName) {
    	return waitForExpectedElement(By.xpath(String.format("//div[@id='coid_website_browseRightColumn']//h3[contains(text(),'%s')]/following-sibling::div[@class='co_genericBoxFooter']//a", widgetName)));
    }

	public WebElement emailPreferencesLink() {
		return waitForExpectedElement(By.linkText("email preferences"));
	}
	
	public WebElement rssButton() {
		return waitForExpectedElement(By.xpath("//a[text()='RSS']"));
	}
	
	public List<WebElement> getAll5UpdatesLinks(String widgetName) {
		return waitForExpectedElements(By.xpath(String.format("//div[@id='coid_website_browseRightColumn']//h3[text()='%s']/following-sibling::div[@class='co_genericBoxContent']//div[@class='co_artifactContent']//a", widgetName)));
	}
	
	public List<WebElement> getAllDates(String widgetName) {
		return waitForExpectedElements(By.xpath(String.format("//div[@id='coid_website_browseRightColumn']//h3[text()='%s']/following-sibling::div[@class='co_genericBoxContent']//div[@class='co_artifactContent']//h4//span[@class='co_date']", widgetName)));
	}
	
	public List<String> getAll5UpdatesTitles(String widgetName) {
		updatesTitles = new ArrayList<String>();
		for(WebElement el : getAll5UpdatesLinks(widgetName)) {
			LOG.info("Adding LU title from widget: " + el.getText());
			updatesTitles.add(el.getText().trim());
		}
		return updatesTitles;
	}
	
	public List<String> getAllDatesFromWidget(String widgetName) {
		updatesDates = new ArrayList<String>();
		for(WebElement el : getAllDates(widgetName)) {
			LOG.info("Adding LU date from widget: " + el.getText());
			updatesDates.add(el.getText());
		}
		return updatesDates;
	}
	
	public boolean isRssButtonDisplayed() {
		return isElementDisplayed(By.xpath("//a[text()='RSS']"));
	}

	public boolean isLUWidgetPresent() {
		return isExists(By.xpath("//div[@id='coid_website_browseRightColumn']//h3[contains(text(),'Legal updates')]"));
	}

    public boolean isOutOfPlanPresent() {
        return isExists(By.xpath("//div[@id='coid_website_browseRightColumn']//div[@class='co_outOfPlanLabel']"));
    }
}
