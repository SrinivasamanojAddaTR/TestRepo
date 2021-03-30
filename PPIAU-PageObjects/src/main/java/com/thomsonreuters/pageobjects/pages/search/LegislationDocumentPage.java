package com.thomsonreuters.pageobjects.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by u4400015 on 22/06/2015.
 */

public class LegislationDocumentPage extends CommonDocumentPage {

/**
 * This method gets the title of the full text legislation document
 */

    public WebElement legislationFullText() {

        return waitForExpectedElement(By.xpath("//div[@id='co_document_0']"));
    }


    /**
     * Object representing out of plan document pop up
     */

    public WebElement viewDocumentLink() {

        return waitForExpectedElement(By.id("co_WarnViewOkButton"));
    }


    /**
     * Object representing advanced search link
     */

    public WebElement advancedSearchLink() {

        return waitForExpectedElement(By.id("co_search_advancedSearchLink"));
    }


    /**
     * Object representing advanced search template act/si field
     */

    public WebElement advancedLegislationTitleSearchField() {

        return waitForExpectedElement(By.id("co_search_advancedSearch_TI"));
    }

    /**
     * Object representing advanced search template provision type field
     */

    public WebElement advancedLegislationProvisionTypeSearchField() {

        return waitForExpectedElement(By.id("co_search_advancedSearch_PT"));
    }

    /**
     * Object representing advanced search template provision number field
     */

    public WebElement advancedLegislationProvisionNumberSearchField() {

        return waitForExpectedElement(By.id("co_search_advancedSearch_PR"));
    }

    /**
     * Object representing free text search field
     */

    public WebElement freeTextField() {

        return waitForExpectedElement(By.id("searchInputId"));
    }

    /**
     * Object representing list of search suggestions
     */

    public WebElement searchSuggestionsList() {

        return waitForExpectedElement(By.id("co_searchSuggestion"));
    }


}
