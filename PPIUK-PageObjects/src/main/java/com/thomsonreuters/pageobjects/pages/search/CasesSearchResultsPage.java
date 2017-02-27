package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


public class CasesSearchResultsPage extends AbstractPage {

    public CasesSearchResultsPage() {
    }

    /**
     * Object representing a facet checkbox as identified by facet name
     */
    public WebElement facetCheckbox(String facetName) {
        WebElement findlabel = waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[text()='" + facetName + "']"));
        String labelFor = findlabel.getAttribute("for");
        return waitForExpectedElement(By.id(labelFor));
    }

    /**
     * Object representing the total search result count for the search
     */
    public WebElement searchResultCount() {
        return retryingFindElement(By.xpath("//div[@id='coid_website_searchAvailableFacets']//span[@class='co_search_titleCount']"));
    }

    public WebElement expandFacet(String facetname) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_topicSummary']//label[contains(text(),'" + facetname + "')]/../a[@class='co_facet_expand']"));
    }

    /**
     * Object representing expand cases jurisdiction facet
     */
    public WebElement expandCasesJurisdictionFacet(String facetname) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_jurisdictionSummary']//label[contains(text(),'" + facetname + "')]/../a[@class='co_facet_expand']"));
    }

    public WebElement collapseFacet(String facetname) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_topicSummary']//label[contains(text(),'" + facetname + "')]/../a[@class='co_facet_collapse']"));
    }

    /**
     * Object representing a given facet by name (any facet)
     */
    public WebElement facetName(String facetname) {
        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetname + "')]"));
    }

    /**
     * This method gets the title of the cases search result item based on the given resultItem index on the results list.
     */
    public String getResultItemCases(String resultItemNumber) {
        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_internationalCase_title" + resultItemNumber + "']/span")).getText();
    }

    /**
     * This method is an object for the case title alone (clickable element)
     */
    public WebElement caseTitle(String resultItemNumber) {
        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_internationalCase_title" + resultItemNumber + "']/span"));
    }

    /**
     * This method is an object representing the Apply Filters button
     */
    public WebElement applyFiltersButton() {
        return waitForExpectedElement(By.linkText("Apply filters"),10);
    }

    /**
     * This method gets the entire text of the cases search result item based on the given resultItem index on the results list.
     */
    public String getWholeResultItemCases(String resultItemNumber) {
        return waitForExpectedElement(By.id("cobalt_search_results_ukCase" + resultItemNumber)).getText();
    }

    /**
     * this is the search term highlighted in the whole result item (including all terms in context displayed on the result list)
     */
    public WebElement highlightedSearchTermInWholeResult(String rank, String highlightedTerm) {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_ukCase" + rank + "']//*[contains(text(),'" + highlightedTerm + "')]"));
    }

    /**
     * this is the jurisdiction facet group header
     */
    public WebElement jurisdictionFacetGroupHeader() {
        return waitForExpectedElement(By.id("co_facetHeaderjurisdictionSummary"));
    }

    /**
     * This is an object for all the listed cases jurisdiction facets
     */
    public List<String> getJurisdictionFacets() {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : retryingFindElements(By.xpath("//div[@id='co_narrowResultsBy']/div/h4[contains(text(), 'Jurisdiction')]/../ul/li/label"))) {
                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    public List<String> getJurisdictionSubFacets(String mainFacet) {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : retryingFindElements(By.xpath("//div[@id='co_narrowResultsBy']/div/h4[contains(text(), 'Jurisdiction')]/../ul/li/label[text()='" + mainFacet + "']/../div//label"))) {
                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    /**
     * Object representing the total search result count for the search
     */
    public WebElement casesSearchResultCount() {
        return retryingFindElement(By.xpath("//div[@id='coid_website_searchAvailableFacets']//span[@class='co_search_titleCount']"));
    }

    /**
     * This method is used to click on ResultItem link present on results page based on given resultIndex.
     *
     * @param resultIndex
     */
    public void clickOnResultItem(int resultIndex) {
        retryingFindElement(By.xpath("//a[@id='cobalt_result_internationalCase_title" + resultIndex + "']/span")).click();
    }

    /**
     * Object representing the court information for first result
     */
    public WebElement courtDetailFirstResult(String rank) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/span[3]"));
    }

    /**
     * Object representing the date information for first result
     */
    public WebElement dateDetailFirstResult(String rank) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/span[4]"));
    }

    /**
     * Object representing highlighted search terms within terms in context
     */
    public WebElement highlightedSearchTermTermsInContext(String rank, String highlightedTerm) {
        return waitForExpectedElement(By.xpath("//div[contains(@id,'snippet_" + rank + "')]/a/span[@class='co_searchTerm'][contains(text(),'" + highlightedTerm + "')]"));
    }

    /**
     * Object representing highlighted search terms within document summary
     */
    public WebElement highlightedSearchTermDocSummary(String rank, String highlightedTerm) {
        return waitForExpectedElement(By.xpath("//div[contains(@id,'co_searchResults_summary_" + rank + "')]/ul/li)"));
    }

    /**
     * Object representing the subject information for first result
     */
    public WebElement subjectDetailFirstResult(String subject) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_1']/div[3]/span[contains(text(),'" + subject + "']"));
    }

    /**
     * Object representing the keyword information for first result
     */
    public WebElement keywordDetailFirstResult(String keyword) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_1']/div[4]/span[contains(text(),'" + keyword + "']"));
    }

    /**
     * Object representing the citation information for first result
     */
    public WebElement citationDetailFirstResult(String citation) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_1']/div[1]/span[contains(text(),'" + citation + "']"));
    }

    /**
     * Object representing the "hit" in the first search result whether case analysis or full text judgment (specify actual citation or Official Transcript as an argument)
     */

    public WebElement documentHit(String document) {

        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_internationalCase_title1']/span[@class='co_searchTerm'][contains(text(),'" + document + "')]"));
    }

    /**
     * Object representing the guid for the "hit" in the first search result whether case analysis or full text judgment (specify actual citation or Official Transcript as an argument)
     */

    public WebElement documentHitGuid(String document) {

        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_internationalCase_title1']/span[@class='co_searchTerm'][contains(text(),'" + document + "')]"));
    }

}
