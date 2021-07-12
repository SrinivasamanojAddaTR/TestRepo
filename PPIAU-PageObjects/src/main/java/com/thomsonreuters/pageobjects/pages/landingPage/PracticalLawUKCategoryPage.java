package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PracticalLawUKCategoryPage extends AbstractPage {

    private CommonMethods commonMethods = new CommonMethods();
    private static final By RESULTS_PER_PAGE_SELECTOR_ID = By.id("selectedDisplayItemCount");

    public WebElement freeTextField() {
        return waitForExpectedElement(By.id("searchInputId"),10);
    }

    public WebElement searchButton() {
        return waitForExpectedElement(By.id("searchButton"));
    }
    
    public boolean isSearchButtonDisplayed(){
    	return isExists(By.id("searchButton"));
    }

    /**
     * looks like this one has disappeared as at 16/12/14
     */
    public void resultsPerPageDropdown(String resultPerPage) {
        waitForExpectedElement(RESULTS_PER_PAGE_SELECTOR_ID).click();
        waitForElementsVisible(By.cssSelector("#displayItemCount .co_dropDownMenuList"));
        waitForExpectedElement(By.linkText(resultPerPage)).click();
    }

    /**
     * Get results per page selector.
     * Can be also used to get the selected (active) results per page option, e.g. "20 per page"
     *
     * @return Element with results per page selector
     */
    public WebElement resultsPerPageSelector() {
        return waitForExpectedElement(RESULTS_PER_PAGE_SELECTOR_ID);
    }

    /**
     * method for selecting a suggested term listed below the free text search field
     */
    public WebElement suggestedTerm(String text) {
        return waitForExpectedElement(By.xpath("//ul[@id='co_searchSuggestion']/li[normalize-space(.)='" + text + "']"));
    }

    /**
     * method for selecting a suggested term listed below the free text search field
     */
    public void mouseOverOnSuggestedSearchResult(String text) {
        commonMethods.mouseOver(waitForExpectedElement(By.xpath("//*[@id='co_searchSuggestion']/li[text()='" + text + "']")));
    }

    /**
     * method for selecting a practice area link from the homepage
     */
    public WebElement practiceAreaLink(String name) {
        return waitForExpectedElement(By.partialLinkText(name));
    }

    /**
     * method for selecting a topic (subject) link from the practice area page
     */
    public WebElement topicAreaLink(String name) {
        return waitForExpectedElement(By.partialLinkText(name));
    }

    /**
     * method for selecting a resource link from the resources page
     */
    public WebElement resourceLink(String name) {
        return waitForExpectedElement(By.partialLinkText(name));
    }

    /**
     * Returning By element for selecting a suggested term listed below the free text search field
     */
    public By suggestedByTerm(String text) {
        return By.xpath("//ul[@id='co_searchSuggestion']/li[text()='" + text + "']");
    }
}