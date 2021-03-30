package com.thomsonreuters.pageobjects.pages.ask;

import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class AskSearchResultsPage extends SearchResultsPage {

    @Override
    public String getResultItem(String resultItemNumber) {
        return waitForExpectedElement(By.cssSelector("#cobalt_result_knowhow_ask_uk_title" + resultItemNumber)).getText();
    }

    public WebElement askQuestionCheckBox(int index) {

        return waitForExpectedElement(By.id("cobalt_search_knowhow_ask_uk_checkbox_" + index));
    }

    public WebElement askQuestionLink(int index) {

        return waitForExpectedElement(By.id("cobalt_result_knowhow_ask_uk_title" + index));
    }

    public List<WebElement> askSearchResultListOnTopicPage() {
    return waitForExpectedElements(By.xpath("//*[contains(@id, 'cobalt_search_results_knowhowAskUK')]"));
    }

    public WebElement downloadWidget() {
        return waitForExpectedElement(By.cssSelector("#deliveryRow1Download a"));
    }

    public WebElement emailWidget() {
        return waitForExpectedElement(By.cssSelector("#deliveryRow1Email a"));
    }

    public WebElement printWidget() {
        return waitForExpectedElement(By.cssSelector("#deliveryRow1Print a"));
    }
}
