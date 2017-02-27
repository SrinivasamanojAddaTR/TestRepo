package com.thomsonreuters.pageobjects.pages.polls;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.thomsonreuters.driver.framework.AbstractPage;

public class AllPollsPage extends AbstractPage {

	private static final String ALL_POLLS_PAGE_TITLE = "All Polls";
	private static final String HIDE_RESULTS_BUTTON_TITLE = "Hide Results";
	private static final String VIEW_RESULTS_BUTTON_TITLE = "View Results";

	public boolean hasAllPollsPageTitle() {
		return isElementPresent(By.xpath("//h1[text()='" + ALL_POLLS_PAGE_TITLE + "']"));
	}
	
	public List<WebElement> getPolls() {
		return findElements(By.cssSelector("div.poll"));
	}
	
	public WebElement getPollHeading(WebElement pollElement) {
		return pollElement.findElement(By.cssSelector("div.pollHeading"));
	}

	public WebElement getPollVotesNumber(WebElement pollHeadingElement) {
		return pollHeadingElement.findElement(By.cssSelector("span.answers"));
	}

	public WebElement getPollStatus(WebElement pollElement) {
		return pollElement.findElement(By.cssSelector("span.pollStatus"));
	}

	public WebElement getPollQuestion(WebElement pollElement) {
		return pollElement.findElement(By.cssSelector("div.questionText"));
	}

	public WebElement getViewResultsButton(Integer pollId) {
		return findElement(By.xpath("//div[@id='" + pollId + "']/div[@class='pollFooter']/a[text()='" + VIEW_RESULTS_BUTTON_TITLE + "']"));
	}

	public WebElement getHideResultsButton(Integer pollId) {
		return findElement(By.xpath("//div[@id='" + pollId + "']/div[@class='pollFooter']/a[text()='" + HIDE_RESULTS_BUTTON_TITLE + "']"));
	}
	
	public void waitForPollResultsVisible(Integer pollId) {
		waitForElementVisible(By.xpath("//div[@id='" + pollId + "']/div[@class='pollContent']/div[@class='pollResultOuter']"));
	}

	public void waitForPollResultsInvisible(Integer pollId) {
		waitForElementInvisible(By.xpath("//div[@id='" + pollId + "']/div[@class='pollContent']/div[@class='pollResultOuter']"));
	}

	public WebElement getPollResultsDataHeading(WebElement pollElement) {
		return pollElement.findElement(By.cssSelector("div.pollResult h2"));
	}
	
	public WebElement getPollResultsChart(WebElement pollElement) {
		return pollElement.findElement(By.cssSelector("div.pollResult div.pollChart"));		
	}
	
	public WebElement getPollResultsData(WebElement pollElement) {
		return pollElement.findElement(By.cssSelector("div.pollResult div.pollResultData"));
	}
	
	public List<WebElement> getStatusFilterOptions() {
		return findElements(By.cssSelector("ul#statusFilter li"));
	}

	public WebElement getStatusFilterOptionCheckbox(WebElement statusFilterOptionElement) {
		return statusFilterOptionElement.findElement(By.cssSelector("input"));
	}
	
	public WebElement getStatusFilterOptionLabel(WebElement statusFilterOptionElement) {
		return statusFilterOptionElement.findElement(By.cssSelector("label"));
	}
	
	public WebElement getPollSearchTextInput() {
		return findElement(By.cssSelector("input.pollsSearch"));
	}
	
	public WebElement getSortingOrderDropdownButton() {
		return findElement(By.cssSelector("div#pollsOrderDropdown a.co_dropDownAnchor"));
	}
	
	public List<WebElement> getSortingOrderDropdownOptions() {
		return waitForExpectedElements(By.cssSelector("div#pollsOrderDropdownContent li.orderMenuItems a"));
	}

	public WebElement getDateFilterDropdownButton() {
		return findElement(By.cssSelector("a#co_dateWidget_poll_dropdown"));
	}
	
	public List<WebElement> getDateFilterOptions() {
		return waitForExpectedElements(By.cssSelector("ul#co_dateWidgetFixedList_poll li"));
	}

	public WebElement getChosenDateFilterOption() {
		return waitForExpectedElement(By.cssSelector("ul#co_dateWidgetFixedList_poll li.co_atState"));
	}
	
	public WebElement getDateFilterOptionCount(WebElement dateFilterOptionElement) {
		return dateFilterOptionElement.findElement(By.cssSelector("span.co_facetCount"));
	}
}
