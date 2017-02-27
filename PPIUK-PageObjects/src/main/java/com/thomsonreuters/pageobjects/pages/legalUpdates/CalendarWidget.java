package com.thomsonreuters.pageobjects.pages.legalUpdates;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


public class CalendarWidget extends AbstractPage {
	
	private List<String> dateOptions;
	
	public CalendarWidget() {
		
	}
	
	public WebElement currentMonthLabel() {
		return findElement(By.xpath("//div[@class='month-container']//span[@id='month']"));
	}
	
	public WebElement eventMarker() {
		return findElement(By.xpath("//div[@class='calendar-widget']//input[@class='marker']"));
	}
	
	public List<WebElement> getAlEeventMarkers() {
		return findElements(By.xpath("//div[@class='calendar-widget']//input[@class='marker']"));
	}
	
	public WebElement eventLightBox() {
		return waitForExpectedElement(By.id("containerBox"));
	}
	
	public WebElement eventTitleInLightBox() {
		return waitForExpectedElement(By.xpath("//a[@class='co_linkBlue ng-binding']"));
	}
	
	public WebElement eventSnippetInLightBox() {
		return waitForExpectedElement(By.xpath("//td[@class='eventsPopupSectionPadding']//div[@class='ng-binding']"));
	}
	
	public WebElement closeEventLightBoxButton() {
		return waitForExpectedElement(By.id("co_search_lightbox_closeButton"));
	}
	
	public WebElement eventLightBoxTitle() {
		return findElement(By.id("popupHeader"));
	}
	
	public WebElement miniCalendarMonthDropDown() {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']"));
	}
	
	public WebElement miniCalendarCurrentMonth() {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']/li[@class='selected-option']"));
	}
	
	public WebElement miniCalendarSelectedOptionInDropDown() {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']//li[@class='selected-option']//span"));
	}
	
	public WebElement currentMonthInMiniCalendarDropDown() {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']//li[@class='expanded-option']//span[@class='current-month']"));
	}
	
	public WebElement selectMonthInMiniCalendarMonthDropDown(String month) {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']//li[@class='expanded-option']//span[contains(text(),'" + month + "')]"));
	}
	
	public List<String> dateOptionsList() {
		dateOptions = new ArrayList<String>();
			for(WebElement option : waitForExpectedElements(By.xpath("//ul[@id='month' and @class='month-dropdown']//li[@class='expanded-option']"))) {
				dateOptions.add(option.getText());
			}
			return dateOptions;
	}
	
	public WebElement addToOutlookLink() {
		return waitForExpectedElement(By.partialLinkText("Add to Outlook"));
	}
	
	public WebElement rightArrowButton() {
		return waitForExpectedElement(By.id("page_right"));
	}
	
	public WebElement leftArrowButton() {
		return waitForExpectedElement(By.id("page_left"));
	}
	
	private List<WebElement> getAllRowsOnMiniCalendar() {
		return waitForExpectedElements(By.xpath("//div[@data-calendar-grid]/div/div"));
	}
	
	public boolean isCorrectBeginOfMonthOnMiniCalendar(int dayOfWeek) {
		return isElementDisplayed(waitForElementPresent(By.xpath("//div[@data-calendar-grid]/div/div[2]/div[" + dayOfWeek + "]/input[@value='1']")));
	}
	
	public boolean isCorrectEndOfMonthOnMiniCalendar(int dayOfWeek, int lastDay) {
		List<WebElement> allRaws = getAllRowsOnMiniCalendar();
		return isElementDisplayed(waitForElementPresent(By.xpath("//div[@data-calendar-grid]/div/div[" + allRaws.size() + "]/div[" + dayOfWeek + "]/input[@value='" + lastDay + "']")));
	}
	
	public WebElement upArrowInMonthSelectMiniCalendar() {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']/li[@class='previous-month dropdown-nav expanded-option']"));
	}
	
	public WebElement downArrowInMonthSelectMiniCalendar() {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']/li[@class='previous-month dropdown-nav expanded-option']"));
	}
	
	public WebElement fifthMonthInPastFromMiniCalendarSelectOption() {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']/li[@class='previous-month dropdown-nav expanded-option']/following::li[1]"));
	}

	public WebElement fifthMonthInFutureFromMiniCalendarSelectOption() {
		return waitForExpectedElement(By.xpath("//ul[@id='month' and @class='month-dropdown']/li[@class='next-month dropdown-nav expanded-option']/preceding-sibling::li[1]"));
	}

}
