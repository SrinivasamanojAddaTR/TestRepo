package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Steph Armytage on 11/02/2015.
 */


public class JournalsSearchResultsPage extends AbstractPage {

    public JournalsSearchResultsPage() {
    }

    /**
     * Object representing the total search result count for the search
     */

    public WebElement journalsSearchResultCount() {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_searchAvailableFacets']//span[@class='co_search_titleCount']"));
    }


    /**
     * this is the search term highlighted in the whole result item (including all terms in context displayed on the result list)
     */

    public WebElement highlightedJournalsSearchTermInWholeResult(
            String rank,
            String highlightedTerm) {

        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_ukJournal" + rank + "']//*[contains(text(),'" + highlightedTerm + "')]"));
    }


    public List<String> getMainPracticeAreaFacets() {

        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : retryingFindElements(By.cssSelector(".co_facet_tree>li>label"))) {

                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }


    /**
     * This method is used to click on ResultItem link present on results page based on given resultIndex.
     *
     * @param resultIndex
     */
    public void clickOnResultItem(int resultIndex) {
        retryingFindElement(By.xpath("//a[@id='cobalt_result_internationalJournal_title" + resultIndex + "']")).click();


    }


    /**
     * Object representing highlighted search terms within terms in context
     */


    public WebElement highlightedSearchTermTermsInContext(String rank, String highlightedTerm) {

        return waitForExpectedElement(By.xpath("//a[contains(@id,'snippet_" + rank + "')]/span[@class='co_searchTerm'][contains(text(),'" + highlightedTerm + "')]"));

    }


    /**
     * Object representing search result
     */

    public WebElement journalsResult(String result) {
        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_internationalJournal_title" + result + "']"));

    }



    /**
     * Object representing list of search suggestions
     */

    public WebElement searchSuggestionsList() {

        return waitForExpectedElement(By.id("co_searchSuggestion"));
    }


    /**
     * Object representing free text search field
     */

    public WebElement freeTextField() {

        return waitForExpectedElement(By.id("searchInputId"));
    }

    /**
     * method to count number of search suggestions displayed to user
     */

    public int countSearchSuggestions() {

        List<WebElement> eList =
                findElements(By.xpath("//ul[@id='co_searchSuggestion']//li"));
        return eList.size();

    }

    /**
     * method to return search suggestions as a list for use when e.g. verifying the order
     */

    public List wordWheelList() {

        String currentTextValue;
        List<String> textList = new ArrayList<String>();

        List<WebElement> eList = findElements(By.xpath("//ul[@id='co_searchSuggestion']//li"));

        for (int counter = 0; counter < eList.size(); counter++) {
            // Get the text value from each element
            currentTextValue = eList.get(counter).getText();
            // Add the text value to the output list which is a string
            textList.add(currentTextValue);
        }

        return textList;
    }

    /**
     * Object representing journal article title link
     */

    public WebElement journalArticleTitleLink(String rank, String title) {

        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_internationalJournal_title" + rank + "']/span[contains(text(),'" + title + "')]"));
    }


    /**
     * Object representing journal citation - this is the actual citation not the prefix text "Citation"
     */

    public WebElement journalCitation(String rank, String citation) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/div[1]//span[contains(text(),'" + citation + "')]"));
    }

    /**
     * Object representing subject text - this is the actual subject information not the prefix "Subject"
     */

    public WebElement journalSubject(String rank, String subject) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/div[2]//span[contains(text(),'" + subject + "')]"));
    }

    /**
     * Object representing keyword text - this is the actual keyword information not the prefix "Keywords"
     */

    public WebElement journalKeywords(String rank, String keyword) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/div[3]//span[contains(text(),'" + keyword + "')]"));
    }

    /**
     * Object representing link to Legal Journals Index Abstract
     */

    public WebElement journalsAbstractLink(String rank) {

        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_internationalJournal_title" + rank + "']/span[contains(text(),'Legal Journals Index Abstract')]"));
    }

    /**
     * Object representing link to Full text article
     */

    public WebElement journalsFullTextArticleLink(String rank) {

        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_internationalJournal_title" + rank + "']/span[contains(text(),'Full Text Article')]"));
    }

    /**
     * Object representing journal document summary text
     */

    public WebElement journalDocumentSummary(String rank) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_summary_" + rank + "']/ul/li"));
    }

    /**
     * Object representing first snippet for terms in context
     */

    public WebElement journalsTermsinContextFirstSnippet(String rank) {

        return waitForExpectedElement(By.xpath("//div[@id='co_snippet_" + rank + "_1']"));
    }

    /**
     * This method returns the displayed search suggestions as list of strings.
     *
     * @return List<String>
     */
    public List<String> getSearchSuggestions() {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement element : retryingFindElements(By.cssSelector("#searchBoxIndexSpan li"))) {
                list.add(element.getText());
            }
            return list;
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return Collections.emptyList();




        }
    }


    


}