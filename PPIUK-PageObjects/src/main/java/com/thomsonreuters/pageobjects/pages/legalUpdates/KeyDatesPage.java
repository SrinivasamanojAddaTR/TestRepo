package com.thomsonreuters.pageobjects.pages.legalUpdates;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.util.ArrayList;
import java.util.List;


public class KeyDatesPage extends AbstractPage {

	public KeyDatesPage() {

	}

	public WebElement eventsWithoutDatesWidget() {
		return waitForExpectedElement(By.id("eventHighlightsContainerBox"));
	}

	public WebElement eventSectionByName(String sectionName) {
		return waitForExpectedElement(By.xpath("//div[@ng-repeat='period in allEvents']//div//b[contains(text(), '" + sectionName + "')]"));
	}

	public List<String> getAllSections() {
		List<WebElement> sectionElements = waitForExpectedElements(By.xpath("//div[@ng-repeat='period in allEvents']//div//b[@class='ng-binding']"));
		List<String> allSections = new ArrayList<String>();
		for (WebElement section : sectionElements) {
			allSections.add(section.getText());
		}
		return allSections;
	}
	
	public List<String> getOptionsInInfoDetailsButton() {
		List<WebElement> optionsButtons =  waitForExpectedElements(By.xpath("//li[contains(@id, 'co_searchDetailSliderListItem_')]/a"));
		List<String> allOptins = new ArrayList<String>();
		for (WebElement option : optionsButtons) {
			allOptins.add(option.getAttribute("title"));
		}
		return allOptins;
	} 
	
	public WebElement infoDetailButton() {
		return waitForExpectedElement(By.id("co_searchDetailSliderLink"));
	}
	
	public WebElement dateRangeLeftArrow() {
		return waitForExpectedElement(By.xpath("//span[@class='left-pointer']"));
	}

	public WebElement dateRangeRightArrow() {
		return waitForExpectedElement(By.xpath("//span[@class='right-pointer']"));
	}
	
	public List<String> getOptionsInDateRangeDropDown() {
		Select dropdown =  new Select(waitForExpectedElement(By.id("intervalDropdownElement")));
		List<String> allOptins = new ArrayList<String>();
		List<WebElement> options = dropdown.getOptions();
		for (WebElement option : options) {
			allOptins.add(option.getText());
		}
		return allOptins;
	}
}
