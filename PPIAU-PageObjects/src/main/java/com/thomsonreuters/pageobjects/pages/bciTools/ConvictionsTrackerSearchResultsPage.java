package com.thomsonreuters.pageobjects.pages.bciTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.thomsonreuters.pageobjects.pages.search.WhatsMarketSearchResultsPage;

public class ConvictionsTrackerSearchResultsPage extends WhatsMarketSearchResultsPage{
	
    /**
     * This is the title of the convictions tracker search result in position X
     */
    public WebElement convictionsTrackerSearchResultTitle(String result) {
        return waitForExpectedElement(By.id("cobalt_result_convictionsTracker_title" + result));
    }
    
    public boolean isResultsListDisplayed() {
    	return isElementDisplayed(By.id("cobalt_search_convictionsTracker_results"));
    }

    
    /**
     * object representing the checkbox associated with each result item on the convictions tracker search results page
     */
    public WebElement resultCheckboxConvictionsTracker(String number) {
        return waitForExpectedElement(By.xpath("//input[@id='cobalt_search_convictionsTracker_checkbox_" + number + "']"));
    }
    
    /**
     * Object representing date on convictions tracker  result in search result list
     */
    
    private By convictionsTrackerResultDateLocator(String number) {
        return By.xpath("//li[@id='cobalt_search_results_convictionsTracker" + number + "']//span[@class='co_search_detailLevel_2'][1]");
    }

    public WebElement convictionsTrackerResultDate(String number) {
        return waitForExpectedElement(convictionsTrackerResultDateLocator(number));
    }
    
    public boolean isConvictionsTrackerResultDateDisplayed(String number) {
        return isElementDisplayed(convictionsTrackerResultDateLocator(number));
    }
        
    /**
     * Object representing deal value on whats market result in search result list
     */

    private By convictionsTrackerResultValueLocator(String number) {
        return By.xpath("//li[@id='cobalt_search_results_convictionsTracker" + number + "']//span[@class='co_search_detailLevel_2'][2]");
    }
    public WebElement convictionsTrackerResultValue(String number) {
        return waitForExpectedElement(convictionsTrackerResultValueLocator(number));
    }
    
    public boolean isConvictionsTrackerResultValueDisplayed(String number) {
        return isElementDisplayed(convictionsTrackerResultValueLocator(number));
    }

    public boolean isResultSummaryDisplayed(String rowNumber) {
        return isExists(By.xpath("//div[@id='co_searchResults_summary_" + rowNumber + "']"));
    }
       
    
}
