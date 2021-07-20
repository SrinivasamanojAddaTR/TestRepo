package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class SearchHomePage extends AbstractPage {

    public WebElement searchTextBox() {
        return waitForExpectedElement(By.id("searchInputId"));
    }

    public WebElement searchHistoryDropdown() {
        return waitForExpectedElement(By.id("co_searchLast10Link"));
    }

    public WebElement jurisdictionDropdown() {
        return waitForExpectedElement(By.id("jurisdictionIdInner"));
    }

    public WebElement jurisdictionSelectionPopup() {
        return waitForExpectedElement(By.id("co_jurisdictionSelectorForm"));
    }

    public WebElement saveJurisdictionPopupButton() {
        return waitForExpectedElement(By.id("co_jurisdictionSave"));
    }

    public WebElement cancelJurisdictionPopupLink() {
        return waitForExpectedElement(By.id("co_jurisdictionSave"));
    }

    public WebElement searchButton() {
        return waitForExpectedElement(By.id("searchButton"));
    }

    public boolean isSearchErrorAlertDisplayed() {
        try {
            waitForTextPresent(By.cssSelector("div.co_infoBox_message"), "Please enter a query.", 3);
            return true;
        } catch (Exception te) {
            LOG.info("Search Error warning tip is not displayed.", te);
        }
        return false;
    }

    public WebElement advanceSearchLink() {
        return waitForExpectedElement(By.id("co_search_advancedSearchLink"));
    }

    /**
     * Get list with auto-suggestion which appeared under search field when user enter first 3 symbols
     * @return Auto-suggestion list element
     */
    public WebElement suggestionList() {
        return waitForElementVisible(By.id("co_searchSuggestion"));
    }

    public Boolean isSuggestionListDisplayed() {
        return isElementDisplayed(By.xpath("//*[@class='co_searchSuggestionContainer']"));
    }

    public WebElement individualSearchSuggestion(String text) {
        return waitForExpectedElement(By.xpath("//*[@id='co_searchSuggestion']/li[contains(.,'" + text + "')]"));
    }



}