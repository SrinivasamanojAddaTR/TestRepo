package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * This is the page that the user arrives at after
 * clicking on the link to Practical Law - UK. It is a category page for the uk search project and is
 * equivalent to the main practical law search page in the uk
 * a link from demo site to existing UK site in order to enable checking of product information in resource details - not something
 * that is currently possible on the demo site - hence the link to the UK site for equivalent search results
 * This page will also represent the US and global category pages - ie the pages displayed after the user has clicked US Know How or Global Know How instead
 * of Practical Law - UK - will also represent the pages encountered for subject areas after a user has selected a particular practice area link
 */
public class PracticalLawUKCategoryPage extends AbstractPage {

    private CommonMethods commonMethods = new CommonMethods();
    private static final By RESULTS_PER_PAGE_SELECTOR_ID = By.id("selectedDisplayItemCount");

    public PracticalLawUKCategoryPage() {
    }

    public WebElement freeTextField() {
        return waitForExpectedElement(By.id("searchInputId"),10);
    }

    public void freeTextFieldJavaScript(String value) {
        WebElement element = freeTextField();
        executeScript("arguments[0].value = '" + value + "'", element);
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
     * this is the replacement option to select the number of results per page
     */
    public WebElement select50ResultsPerPage() {
        return waitForExpectedElement(By.xpath("//div[@id='co_search_footerToolbar']/div/div[2]/p/span[2][text()='50']"));
    }

    public WebElement select20ResultsPerPage() {
        return waitForExpectedElement(By.xpath("//div[@id='co_search_footerToolbar']/div/div[2]/p/span[text()='20']"));
    }

    public WebElement select100ResultsPerPage() {
        return waitForExpectedElement(By.xpath("//div[@id='co_search_footerToolbar']/div/div[2]/p/span[3][text()='100']"));
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
    /**
     * Returning By element using ID for selecting a details view option
     */
    public WebElement detailSliderButton()
    {

        return waitForExpectedElement(By.id("detailSliderSelectorSelectedOption"));
    }

    /**
     * Returning By element using link text for selecting an option from details view option dropdown
     */

    public WebElement detailSliderOption(String OptionString)
    {

        return waitForExpectedElement(By.linkText(OptionString));
    }

    /**
     * Returning By element using xpath for event Title displayed in the  search results page
     */
    public WebElement eventTitle()
    {
        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_keyDateUK_title1']/span"));
    }

    /**
     * Returning By element using xpath for event Date displayed in the  search results page
     */
    public WebElement eventDate()
    {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_keyDateUK1']/div/div/div"));
    }
    /**
     * Returning By element using xpath for event Type displayed in the  search results page
     */
    public WebElement eventType()
    {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_keyDateUK1']/div/div[2]/div/div[2]"));
    }

    /**
     * Returning By element using xpath for event Jurisdiction displayed in the  search results page
     */
    public WebElement eventJurisdiction()
    {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_keyDateUK1']/div/div[2]/div/div[3]"));
    }

    /**
     * Returning By element using id for event Summary displayed in the  search results page
     */
    public WebElement eventSummary()
    {
        return waitForExpectedElement(By.id("co_searchResults_summary_1"));
    }

    /**
     * Returning By element using xpath for event AssociatedLink displayed in the  search results page
     */
    public WebElement eventAssociatedLink()
    {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_keyDateUK1']/div/div[2]/div/div[4]/a"));
    }

}