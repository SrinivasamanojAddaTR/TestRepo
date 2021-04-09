package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import java.util.ArrayList;
import java.util.List;

public class WhatsMarketSearchResultsPage extends AbstractPage {

    private static final String NARROW_RESULTS_CO_DIVIDER = "#co_narrowResultsBy .co_divider";
    private static final String RESULTS_DATE_PATTERN = "//li[@id='cobalt_search_results_whatsmarketUK%s']//span[@class='co_search_detailLevel_2']";
    private static final String WM_FACET_WITH_NAME_PATTERN = "//div[@id='co_narrowResultsBy']//label[contains(text(),'%s')]";
    private static final String CONTEXT = "context";

    /**
     * this is the facet name - pass in the facet name as a string e.g. Standard clauses
     */
    public WebElement whatsMarketFacetName(String name) {
        return waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[contains(text(),'" + name + "')]"), 20);
    }
    
    /**
     * this is the facet name in specific facet group - pass in the facet name as a string e.g. Standard clauses
     */
    public WebElement whatsMarketFacetNameInFacetGroup(String facetName, String facetGroupName) {
        return waitForExpectedElement(By.xpath("//h4[text()=\"" + facetGroupName + "\"]//ancestor::div/ul/li/label[text()=\"" + facetName + "\"]"), 20);
    }

    /**
     * Object representing any whats market facet checkbox as identified by facet name
     */
    public WebElement whatsMarketFacetCheckbox(String facetName) {
        WebElement findlabel = waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[text()='" + facetName + "']"),8);
        String labelFor = findlabel.getAttribute("for");
        return waitForExpectedElement(By.id(labelFor),8);
    }

    /**
     * expand a facet
     */
    public WebElement expandWhatsMarketFacet(String facetname) {
        return waitForExpectedElement(By.xpath(String.format(WM_FACET_WITH_NAME_PATTERN, facetname) + "/../a[@class='co_facet_expand']"));
    }

    /**
     * collapse a facet
     */
    public WebElement collapseWhatsMarketFacet(String facetname) {
        return waitForExpectedElement(By.xpath(String.format(WM_FACET_WITH_NAME_PATTERN, facetname) + "/../a[@class='co_facet_collapse']"));
    }

    /**
     * This is an object representing the facet count associated with each facet (any facet on the know how page)
     */
    public WebElement whatsMarketFacetCount(String facetname) {
        return waitForExpectedElement(By.xpath(String.format(WM_FACET_WITH_NAME_PATTERN, facetname) + "/../span[@class='co_facetCount']"));
    }

    /**
     * Get the accompanying text - excludes the title - deal types etc etc
     */
    public Boolean isWhatsMarketResultTextPresent(String position, String text) {
    	return isElementDisplayed(By.xpath("//div[@id='co_searchResults_citation_" + position + "']//span[contains(text(),'" + text + "')]"));
    }

    /**
     * This method gets the results meta data based on the given result itme index.
     *
     * @param index
     * @return String
     */
    public String getResultItemMetaData(String index) {
        try {
            return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_" + index + " span:nth-child(2)"), 15).getText();
        } catch (PageOperationException pe) {
            LOG.info(CONTEXT, pe);
            return StringUtils.EMPTY;
        }
    }

    /**
     * This method to get the all facet group names.
     *
     * @return List<String>
     */
    public List<String> getFacetGroupNames() {
        List<String> list = new ArrayList<>();
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector("#co_narrowResultsBy .co_divider h4"),8)) {
                list.add(element.getText());
            }
        } catch (StaleElementReferenceException se) {
            LOG.info(CONTEXT, se);
            list = new ArrayList<>();
            getFacetGroupNames();
        }
        return list;
    }

    /**
     * This method gets the displayed child facets size under given facet group name.
     *
     * @param facetGroupName
     * @return int
     */
    public int getChildFacetsSize(String facetGroupName) {
        int size = 0;
        String h4Text = "//h4[text()=";
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector(NARROW_RESULTS_CO_DIVIDER), 8)) {
                size = element.findElements(By.xpath(h4Text + Quotes.escape(facetGroupName) + "]/../ul/li")).size();
                WebElement extraFacets = element.findElement(By.xpath(h4Text + Quotes.escape(facetGroupName) + "]/../div"));

                String extraFacetsClassAttribute = extraFacets.getAttribute("class");
                if (extraFacetsClassAttribute == null || (!extraFacetsClassAttribute.equals("co_hideState"))) {
                    size += element.findElements(By.xpath(h4Text + Quotes.escape(facetGroupName) + "]/../div/ul/li")).size();
                    return size;
                }
            }
        } catch (StaleElementReferenceException se) {
            LOG.info(CONTEXT, se);
            getChildFacetsSize(facetGroupName);
        } catch (NoSuchElementException nse) {
            LOG.info(CONTEXT, nse);
        }
        return size;
    }

    /**
     * This method clicks on the more option present under the given facet group name.
     *
     * @param facetGroupName
     */
    public void clickMoreOption(String facetGroupName) {
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector(NARROW_RESULTS_CO_DIVIDER),8)) {
                if (element.findElement(By.cssSelector("h4")).getText().equals(facetGroupName)) {
                    scrollIntoViewAndClick(element.findElement(By.linkText("More")));
                }
            }
        } catch (StaleElementReferenceException se) {
            LOG.info(CONTEXT, se);
            clickMoreOption(facetGroupName);
        }
    }

    public List<WebElement> searchResultRobustDates() {
        List<WebElement> searchResultsDates;
        try {
            searchResultsDates = waitForExpectedElements(By.xpath("//ol[@class='co_searchResult_list']/li[contains(@id,'cobalt_search_results')]//div[contains(@class,'co_search_detailLevel')]//span[contains(.,'Published on ')]"), 10);
        } catch (Exception e) {
            // Try the left aligned dates instead of published dates
            searchResultsDates = waitForExpectedElements(By.xpath("//ol[@class='co_searchResult_list']/li[contains(@id,'cobalt_search_results')]//div[contains(@id,'co_searchResults_citation')]/span[1]"), 10);
        }
        return searchResultsDates;
    }

    public List<WebElement> leftAlignedDates() {
        return waitForExpectedElements(By.xpath("//ol[@class='co_searchResult_list']/li[contains(@id,'cobalt_search_results')]//div[contains(@id,'co_searchResults_citation')]/span[1]"), 10);
    }

    public List<WebElement> searchResultPublishedDates() {
        return waitForExpectedElements(By.xpath("//ol[@class='co_searchResult_list']/li[contains(@id,'cobalt_search_results')]//div[contains(@class,'co_search_detailLevel')]//span[contains(.,'Published on ')]"), 10);
    }

    /**
     * This is the title of the whats market search result in position X
     */
    public WebElement whatsMarketSearchResultTitle(String result) {
        return waitForExpectedElement(By.id("cobalt_result_whatsmarket_title" + result));
    }

    /**
     * Object representing any whats market facet group heading
     */
    public WebElement whatsMarketFacetGroupName(String title) {


            String text = "'" + title + "'";
            if (title.contains("'")) {
                title = "\"" + title + "\"";
                text = title;

        }

        return waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//h4[contains(text()," + text + ")]"));
    }

    /**
     * This method clicks on the result item link from the displayed results list using the index of the result.
     *
     * @param index
     */
    public void selectResultItemByIndex(String index) {
        waitForExpectedElement(By.xpath("//span[@class='co_searchCount'][text()='" + index + ".']/following-sibling::a"),10).click();
    }

    /**
     * Object representing date on whats market result in search result list
     */

    public WebElement resultDate(String number) {
        return waitForExpectedElement(By.xpath(String.format(RESULTS_DATE_PATTERN, number) + "//self::span[contains(text(),'20')]"));

    }

    /**
     * Object representing displaying of date on whats market result in search result list
     */

    public boolean isResultDatePresent(String number) {
        return isElementDisplayed(By.xpath(String.format(RESULTS_DATE_PATTERN, number) + "//self::span[contains(text(),'20')]"));
    }

    /**
     * Object representing deal value on whats market result in search result list
     */

    public WebElement resultValue(String number) {
        return waitForExpectedElement(By.xpath(String.format(RESULTS_DATE_PATTERN, number) + "//self::span[contains(text(),'illion')]"));
    }

    /**
     * Object representing displaying of result value on whats market result in search result list
     */

    public boolean isResultValuePresent(String number) {
        return isElementDisplayed(By.xpath(String.format(RESULTS_DATE_PATTERN, number) + "//self::span[contains(text(),'illion')]"));
    }

    /**
     * Object representing deal summary on whats market result in search result list
     */

    public WebElement resultSummary(String rowNumber) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_summary_"+rowNumber+"']"));
    }

    /**
     * Object representing deal summary displayed on whats market result in search result list
     */

    public boolean isResultSummaryForResultPresent(String rowNumber) {
        return isElementDisplayed(By.xpath("//div[@id='co_searchResults_summary_" + rowNumber + "']"));
    }

    /**
     * Object representing deal type in whats market result in search result list
     */

    public WebElement resultDealType(String number, String dealtype) {
        return waitForExpectedElement(By.xpath(String.format(RESULTS_DATE_PATTERN, number) + "[contains(text(),'" + dealtype + "')]"));
    }

    /**
     * Object representing the error message displayed to a user who is either open web or has a subscription which has no access to whats market
     */
    public By subscriptionErrorMessageLocator() {
        return By.xpath("//div[@id='cobalt_search_no_results']/p[contains(text(),'subscription to view the search results on this page')]");
    }
    
    public boolean isSubscriptionErrorMessageDisplayed() {
        return isElementDisplayed(subscriptionErrorMessageLocator());
    }  

    public WebElement subscriptionErrorMessage() {
        return waitForExpectedElement(By.xpath("//div[@id='cobalt_search_no_results']/p[contains(text(),'subscription to view the search results on this page')]"));
    }

    /**
     * Object representing Compare button on whats market search result page
     */

    public WebElement compareButton() {
        return waitForExpectedElement(By.id("compareDocsButton"));
    }

    /**
     * Object representing the filter/continue button on the facet group popup after clicking more
     */

    public WebElement filterButtonOnPopup() {
        return waitForElementVisible(By.xpath("//div[@id='co_facet_wmUKDealTypeFacet_popup']//input[@value='Continue']"));
    }

    /**
     * Object representing the available facets on the facet group popup after clicking more
     */

    public WebElement facetOnPopup(String facet) {
        return waitForExpectedElement(By.xpath("//div[@class='co_collectorScrollOne co_scrollWrapper']//span[@class='name'][contains(text(),'" + facet + "')]"));
    }

    /**
     * object representing error message on pop up - you can only compare deals of the same type displayed when user
     * attempts to compare differing deal types
     */

    public WebElement errorMessage() {

        return waitForExpectedElement(By.xpath("//div[@id='co_simpleMessagePopup']//div[contains(text(),'You can only compare deals of the same type')]"));
    }

    /**
     * Object representing OK button on error message pop up
     */

    public WebElement okButton() {

        return waitForExpectedElement(By.id("ContinueButton"));
    }

    /**
     * List of all the Search Results TitleLinks
     */

    public List<WebElement> searchResultsTitleLinks() {
        return waitForExpectedElements(By.xpath("//ol/li[contains(@id,'cobalt_search_results_whatsmarket')]//h3/a"));
    }

    public By searchResultsByTitleLink(int rowNumber) {
        return By.id("cobalt_result_whatsmarket_title"+rowNumber);
    }

    public boolean isSearchResultsByTitleLinkPresent(String number) {
        return isElementDisplayed(searchResultsByTitleLink(Integer.parseInt(number)));
    }
}