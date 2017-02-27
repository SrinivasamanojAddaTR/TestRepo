package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steph Armytage on 03/02/2015. This page is the search results page for legislation only
 */


public class LegislationSearchResultsPage extends AbstractPage {

    public LegislationSearchResultsPage() {
    }

    /**
     * Object representing the total search result count for the search
     */

    public WebElement legislationSearchResultCount() {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_searchAvailableFacets']//span[@class='co_search_titleCount']"));
    }


    /**
     * this is the search term highlighted in the whole result item (including all terms in context displayed on the result list)
     */

    public WebElement highlightedLegislationSearchTermInWholeResult(
            String rank,
            String highlightedTerm) {

        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_ukLegislation" + rank + "']//*[contains(text(),'" + highlightedTerm + "')]"));
    }


    /**
     * expand a legislation facet
     */


    public WebElement expandFacet(String facetname) {

        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetname + "')]/../a[@class='co_facet_expand']"));

    }




    /**
     * collapse a legislation facet
     */


    public WebElement collapseFacet(String facetname) {

        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetname + "')]/../a[@class='co_facet_collapse']"));
    }



    /**
     * this is the legislation facet name - pass in the facet name as a string e.g. Standard clauses
     */


    public WebElement facetName(String Name) {

        return waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[contains(text(),'" + Name + "')]"));

    }

    /**
     * this is the legislation facet name for jurisdiction only - pass in the facet name as a string
     */


    public WebElement jurisdictionFacetName(String Name) {

        return waitForExpectedElement(By.xpath("//div[@id='facet_div_jurisdictionSummary']//label[contains(text(),'" + Name + "')]"));

    }


    /**
     * this is the legislation facet name but in a non visible state - pass in the facet name as a string e.g. Standard clauses
     */


    public WebElement nonVisibleFacetName(String Name) {

        return waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[contains(text(),'" + Name + "')]/parent::*/parent::ul[@class='co_hideState']"));

    }

    /**
     * Object representing the total legislation search result count for the search
     */


    public WebElement knowHowSearchResultCount() {

        return waitForExpectedElement(By.xpath("//div[@id='coid_website_searchAvailableFacets']//span[@class='co_search_titleCount']"));
    }


    /**
     * Object representing any know how legislation facet checkbox as identified by facet name
     */



    public WebElement legislationFacetCheckbox(String facetName) {

        //Find the text label
        WebElement findlabel = waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[text()='"+ facetName +"']"));

        //then get the id of the checkbox it belongs to
        String labelFor = findlabel.getAttribute("for");

        //then return the matching checkbox using its ID
        return waitForExpectedElement(By.id(labelFor));

    }



    /**
     * Object representing Jurisdiction heading for legislation facet group
     */



    public WebElement facetGroupHeaderJurisdiction() {

        return waitForExpectedElement(By.id("co_facetHeaderjurisdictionSummary"));
    }


    /**
     * This is an object representing the Apply Filters button
     */

    public WebElement applyFiltersButton() {

        return waitForExpectedElement(By.linkText("Apply Filters"));
    }

    /**
     * This is an object representing the facet count associated with each facet (any facet on the know how page)
     */

    public WebElement facetCount(String facetname) {

        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetname + "')]/../span[@class='co_facetCount']"));
    }


    /**
     * This is an object representing a facet in an expanded state
     */

    public WebElement expandedLegislationFacet(String facetname) {

        return waitForExpectedElement(By.xpath("//ul[@class='co_facet_tree']//label[text()='" + facetname + "']/preceding-sibling::a[@class='co_facet_collapse']"));
    }

    /**
     * This is an object representing a facet in a collapsed state
     */

    public WebElement collapsedLegislationFacet(String facetname) {

        return waitForExpectedElement(By.xpath("//ul[@class='co_facet_tree']//label[text()='" + facetname + "']/preceding-sibling::a[@class='co_facet_expand']"));
    }

    /**
     * This method is used to click on ResultItem link present on results page based on given resultIndex.
     *
     * @param resultIndex
     */
    public void clickOnResultItem(int resultIndex) {
        retryingFindElement(By.xpath("//a[@id='cobalt_result_internationalLegislation_title" + resultIndex +"']/span")).click();

    }


    /**
     * This is an object representing the less detail option
     */

    public WebElement lessDetailOption() {
        return waitForExpectedElement(By.xpath("//li[@id='co_searchDetailSliderListItem_1']/a"));

    }

    /**
     * This is an object representing the more detail option
     */

    public WebElement moreDetailOption() {
        return waitForExpectedElement(By.xpath("//li[@id='co_searchDetailSliderListItem_2']/a"));

    }

    /**
     * This is an object representing the most detail option
     */

    public WebElement mostDetailOption() {
        return waitForExpectedElement(By.xpath("//li[@id='co_searchDetailSliderListItem_3']/a"));

    }

    /**
     * This is an object representing the icon indicating less detail selected - this is not the option to select less more or most
     */

    public WebElement lessDetailSelectedIcon() {
        return waitForExpectedElement(By.xpath("//a[@id='co_searchDetailSliderLink']/span[contains(text(),'Less Detail')]"));

    }

    /**
     * This is an object representing the icon indicating more detail selected - this is not the option to select less more or most
     */

    public WebElement moreDetailSelectedIcon() {
        return waitForExpectedElement(By.xpath("//a[@id='co_searchDetailSliderLink']/span[contains(text(),'More Detail')]"));

    }

    /**
     * This is an object representing the icon indicating most detail selected - this is not the option to select less more or most
     */

    public WebElement mostDetailSelectedIcon() {
        return waitForExpectedElement(By.xpath("//a[@id='co_searchDetailSliderLink']/span[contains(text(),'Most Detail')]"));

    }

    /**
     * Object representing highlighted search terms within terms in context
     */


    public WebElement highlightedSearchTermTermsInContext(String rank, String highlightedTerm) {

        return waitForExpectedElement(By.xpath("//a[contains(@id,'snippet_" + rank + "')]/span[@class='co_searchTerm'][contains(text(),'" + highlightedTerm + "')]"));

    }

    /**
     * Object representing act/si/provision title
     */

    public WebElement legTitle(String rank, String title) {

        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_ukLegislation" + rank + "']//h3//span[contains(text(),'" + title + "')]"));

    }

    /**
     * Object representing sub title e.g. of part - Evidence of children and other vulnerable witnesses: special measures
     */

    public WebElement subtitle(String rank, String title) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/h3[2][contains(text(),'" + title + "')]"));
    }

    /**
     * Object representing provision link
     */

    public WebElement provisionLink(String rank, String name) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/h3/div/a/span[contains(text(),'" + name + "')]"));

    }

    /**
     * Object representing version in force information
     */

    public WebElement versionInForce(String rank, String text) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/div[contains(text(),'" + text + "')]"));
    }

    /**
     * Object representing status icon image
     */


    public WebElement statusIcon(String rank) {

        return waitForExpectedElement(By.xpath("//img[@id='co_search_internationalLegislation_citatorFlagImage_" + rank + "']"));
    }

    /**
     * Object representing text description to accompany status icon image
     */

    public WebElement statusIconDescription(String rank, String expectedText) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/div[3]/strong//span[contains(text(),'" + expectedText + "')]"));
    }


    /**
     * Object representing first snippet from terms in context
     */

    public WebElement termsInContextFirstSnippet(String rank) {

        return waitForExpectedElement(By.xpath("//div[@id='co_snippet_" + rank + "_1']"));
    }


    /**
     * Object representing the link to the arrangement
     */

    public WebElement legislationArrangementLink(String rank, String name) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + rank + "']/h3/div/a/span[contains(text(),'" + name + "')]"));

    }

    /**
     * Object representing list of search suggestions
     */

    public WebElement searchSuggestionsList() {

        return waitForExpectedElement(By.id("co_searchSuggestion"));
    }

    /**
     * method to return search suggestions as a list for use when e.g. verifying the order
     */

    public List wordWheelList() {

        String currentTextValue;
        List<String> textList = new ArrayList<String>();

        List<WebElement> eList = findElements(By.xpath("//ul[@id='co_searchSuggestion']//li"));

        for (int counter=0;counter < eList.size();counter++)
        {
            // Get the text value from each element
            currentTextValue = eList.get(counter).getText();
            // Add the text value to the output list which is a string
            textList.add(currentTextValue);
        }

        return textList;
    }

    /**
     * method to count number of search suggestions displayed to user
     */

    public int countSearchSuggestions() {

        List<WebElement> eList =
                findElements(By.xpath("//ul[@id='co_searchSuggestion']//li"));
        return eList.size();

    }


}
