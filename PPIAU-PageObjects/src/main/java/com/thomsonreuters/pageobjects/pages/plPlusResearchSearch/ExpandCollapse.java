package com.thomsonreuters.pageobjects.pages.plPlusResearchSearch;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * This Page object is developed for Expanding , Collapsing and sorting Information
 */

public class ExpandCollapse extends AbstractPage {

    public ExpandCollapse() {
    }

    public WebElement dropDown() {
        return waitForExpectedElement(By.id("co_searchDetailSliderLink"));
    }

    public WebElement most() {
        return waitForExpectedElement(By.cssSelector("a[title*='Most Detail']"));
    }

    public WebElement more() {
        return waitForExpectedElement(By.cssSelector("a[title*='More Detail']"));
    }

    public WebElement less() {
        return waitForExpectedElement(By.cssSelector("a[title*='Less Detail']"));
    }

    public WebElement sortOptionsDropDown() {
        return waitForExpectedElement(By.id("co_search_sortOptions"));
    }

    public void sortByDate() {
        selectDropDown(sortOptionsDropDown(), "UK_RESEARCH_DATE_DESC");
    }

    public void sortByRelevency() {
        selectDropDown(sortOptionsDropDown(), "UK_RESEARCH_RELEVANCE");
    }

    public void sortByLegislationType() {
        selectDropDown(sortOptionsDropDown(), "UK_RESEARCH_BY_TYPE");
    }

}