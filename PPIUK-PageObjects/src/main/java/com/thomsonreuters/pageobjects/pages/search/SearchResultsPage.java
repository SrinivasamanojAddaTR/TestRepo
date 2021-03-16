package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.ListFunctions;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;

import java.util.*;

/**
 * This is the generic search results page for the search 1 project.
 * This search results page relates to searches conducted from the PracticalLawUKCategoryPage
 */
public class SearchResultsPage extends AbstractPage {

    CommonMethods commonMethods = new CommonMethods();

    private static final String MINIMIZE_BTN_ID = "coid_deliveryWaitMessage_minimizeButton";
	private ListFunctions listFunctions = new ListFunctions();
    private final String EMAIL_ARCHIVE_TEXT = "Browse the archive of weekly and monthly emails";
    private final static By mostDetailOptionLocator = By.xpath("//ul[@class='co_dropDownMenuList']//a[contains(., 'Most') and contains(., 'etail')]");
    private static final String ALL_RESULT_LINKS_XPATH = "//span[@class='co_searchCount']/following-sibling::*[(local-name()='a' and contains(@id, 'cobalt_result')) or local-name()='strong']";
    private static final String ALL_DOC_GUID_LINKS_XPATH = "//a[@docguid and not(contains(@class, 'hide'))]";
    private static final String VISIBLE_FACET_NAMES_BY_GROUP_XPATH = "//div[starts-with(@id, 'facet') and ./h4[contains(., '%s')]]//label[not(ancestor::ul[contains(@class, 'hide')])]";
    private static final String FACET_NAME_BY_GROUP_XPATH = "//div[starts-with(@id, 'facet') and ./h4[contains(., '%1$s')]]//label[contains(., '%2$s')]";

    private static final int TIMEOUT_IN_SECONDS = 60;
    private static final int POLLING_TIME_IN_MILLISECONDS = 100;

    public SearchResultsPage() {
    }

    public enum SliderSelector {
        MORE,
        MOST,
        LESS;
    }

    /**
     * The header above the results list
     */
    public WebElement resultsListHeader() {
        return waitForExpectedElement(By.xpath("//div[@class='co_search_result_heading_content']"), 60);
    }

    /**
     * The header above the results filter
     */
    public WebElement filterHeader() {
        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBox']"), 30);
    }

    /**
     * The number of the first search result on a page, for example on the 1st page, it would be 1
     */
    public WebElement theFirstSearchResult() {
        return waitForExpectedElement(By.xpath("//span[@class='co_searchCount']"));
    }

    /**
     * this is the number of an individual search result displayed on the page e.g. no.50
     */
    public WebElement resultNumber(String cobaltSearchResult) {
        return waitForExpectedElement(By.xpath("//li[contains(@id,'cobalt_search_results')]//span[@class='co_searchCount'][text()='" + cobaltSearchResult + ".']"));
    }

    public String getTextUnderHeaderForEmailArchive(){
        return getUnderHeaderForEmailArchiveElement().getText();
    }

    public WebElement getUnderHeaderForEmailArchiveElement(){
        return waitForExpectedElement(By.cssSelector("span#categoryPageScope"));
    }

    /**
     * object to return current page number as an interger
     */
    public Integer currentSelectedPageNumber() {
        Integer currentPageInteger;
        WebElement currentPageElement;
        currentPageElement = waitForExpectedElement(By.xpath("//span[@id='co_search_footer_pagination_current']/strong"));
        currentPageInteger = Integer.parseInt(currentPageElement.getText());
        return currentPageInteger;
    }

    public WebElement resourceTypeDescription() {
        return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_1 div.co_search_detailLevel_1:nth-of-type(1)"));
    }

    public WebElement backToCategoryPageLink() {
        return waitForExpectedElement(By.id("coid_website_BacktoCategoryPageLink"));
    }

    /**
     * This is the option to sort by relevance or date and this object only represents the displayed value
     */
    public WebElement sortByDropdownList() {
        return waitForExpectedElement(By.cssSelector("#co_search_sortDropDownControl option[selected='selected']"));
    }

    /**
     * This element is the selected option from sort by relevance or date dropdown
     */
    public WebElement sortByDropdownSelectedOption() {
        return waitForExpectedElement(By.xpath("//div[@id='co_search_sortDropDownControl']//a[@id='co_search_sortOptions']//span/.."));
    }

    /**
     * This element is the sort by link
     */
    public WebElement sortByDropdownLink() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_sortOptions']"));
    }

    /**
     * This element is returns you the link in the dropdown options (either 'Relevancy' or 'Date')
     */
    public WebElement sortByDropDownOption(String option) {
        return waitForExpectedElement(By.xpath("//div[@id='co_search_sortDropDownControl']//div[contains(@class,'dropDownMenuContent')]//a[text()[normalize-space() = '" + option + "']]"));
    }

    /**
     * This method is to get the selected dropdown item as string value from the sorting dropdown list in search results page.
     *
     * @return String
     */
    public String getSelectedDropDownList() {
        try {
            return listFunctions.getSelectedValueList(waitForExpectedElement(By.cssSelector("div#co_search_sortDropDownControl a#co_search_sortOptions")));
        } catch (StaleElementReferenceException se) {
            LOG.info("Finding the select option dropdown item again as there is a Stale Element Exception occured.", se);
            getSelectedDropDownList();
        }
        throw new PageOperationException("Unable to find the selected item from dropdown list");
    }

    /**
     * This is the option to sort by relevance or date and this object gets the whole element so is the one you need when you are making a selection
     */
    public WebElement getSortDropDownList() {
        return waitForExpectedElement(By.id("co_search_sortOptions"));
    }

    /**
     * This is the free text search field
     */
    public WebElement freeTextSearchField() {
        return waitForExpectedElement(By.id("searchInputId"));
    }

    public boolean isFreeTextSearchFieldDisplayed(){
        return isElementDisplayed(By.id("searchInputId"));
    }

    public WebElement signOffLink() {
        return waitForExpectedElement(By.id("coid_website_signOffRegion"));
    }

    public WebElement firstResultTitle() {
        return waitForExpectedElement(By.id("cobalt_result_knowhow_title1"));
    }

    public WebElement resultByNumber(String number) {
        return waitForExpectedElement(By.id("cobalt_result_knowhow_title"+number));
    }

    public WebElement firstResultAbstractText() {
        return waitForElementPresent(By.xpath("//div[@id='co_searchResults_summary_1']/ul/li"));
    }

    public boolean isFirstResultAbstractTextDisplayed() {
    	return isElementDisplayed(By.xpath("//div[@id='co_searchResults_summary_1']/ul/li"));
    }

    public boolean isFirstResultTitleDisplayed() {
    	return isElementDisplayed(By.id("cobalt_result_knowhow_title1"));
    }

    public WebElement sorryNoResultsMessage() {
        return waitForExpectedElement(By.xpath("//div[@id='cobalt_search_no_results']/p"));
    }

    public WebElement searchTips() {
        return waitForExpectedElement(By.xpath("//div[@class='search_tips']"));
    }

    public WebElement searchTitleAndCount() {
        return waitForExpectedElement(By.xpath("//div[@id='coid_website_searchAvailableFacets']//h1"));
    }

    public List<WebElement> archiveResultsDocumentTypes(){
        return waitForElementsVisible(By.xpath("//div[contains(@id,'co_searchResults_citation')]//div[contains(.,'Document Type')]"));
    }

    public List<WebElement> searchResultCaseLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_case_results']//h3//a"));
    }

    public List<WebElement> searchResultKeyNumLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_keyNumbers_results']//a[@class='co_keyNumberResultItemLink']"));
    }

    public List<WebElement> searchResultTrialCourtLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_tco_results']//h3//a"));
    }

    public List<WebElement> searchResultStatutesLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_statute_results']//h3//a"));
    }

    public List<WebElement> searchResultRegulationsLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_regulation_results']//h3//a"));
    }

    public List<WebElement> searchResultAdminDecisionGuideLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_adminDecision_results']//h3//a"));
    }

    public List<WebElement> searchResultSecondarySourceLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_adminDecision_results']//h3//a"));
    }

    public List<WebElement> searchResultFormsLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_form_results']//h3//a"));
    }

    public List<WebElement> searchBriefsLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_brief_results']//h3//a"));
    }

    public List<WebElement> searchTrialCourtDocsLinks() {
        return waitForElementsVisible(By.xpath("//div[@id='cobalt_search_pmm_results']//h3//a"));
    }

    public WebElement searchWithinResultsHeader() {
        return waitForExpectedElement(By.id("co_searchWithinWidget_header"));
    }

    public WebElement resourceTypeForFirstResult() {
        return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_1 div.co_search_detailLevel_1:nth-of-type(1)"));
    }

    public WebElement jurisdictionsForFirstResult() {
        return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_1 div.co_search_detailLevel_1:nth-of-type(3)"));
    }

    public WebElement statusForFirstResult() {
        return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_1 div.co_search_detailLevel_1:nth-of-type(2)"));
    }

    public WebElement plcRefIdForNthSearchResult(int n) {
        return waitForExpectedElement(By.cssSelector("li#cobalt_search_results_knowhowukdebug" + n + " #co_searchResults_citation_1 div.co_search_detailLevel_1:nth-of-type(4) span a"));
    }

    /**
     * this is the text that appears at the top of the search results page and states 1-20 or 1-50 - its an
     * indication of how many results are displayed per page
     */
    public WebElement resultsPerPageText() {
        return waitForExpectedElement(By.xpath("//ol[@id='co_navigationPages']/li[1]/span"));
    }

    /**
     * this is the number of an individual know how search result displayed on the page e.g. no.50
     */
    public WebElement resultNumberKH(String cobaltSearchResult) {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_knowHowPlc" + cobaltSearchResult + "']//span[@class='co_searchCount']"));
    }

    /**
     * this is the number of an individual whats marketsearch result displayed on the page e.g. no.50
     */
    public WebElement resultNumberWM(String cobaltSearchResult) {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_whatsmarketUK" + cobaltSearchResult + "']//span[@class='co_searchCount']"));
    }

    /**
     * this is the page numbering displayed at the base of the page
     */
    public WebElement pagination(String number) {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_page" + number + "']"));
    }

    /**
     * this is the option to select the next page
     */
    public WebElement selectNextPage() {
        return waitForElementPresent(By.id("co_search_footer_pagination_next"));
    }

    /**
     * this is the by element to select the next page
     */
    public By selectNextPageByLink() {
        return By.id("co_search_footer_pagination_next");
    }

    /**
     * this is the option to select the previous page
     */
    public WebElement selectPreviousPage() {
        return waitForExpectedElement(By.id("co_search_footer_pagination_Page2"));
    }

    /**
     * this is the "Resource Type" header within the facet section
     */
    public WebElement resourceTypeHeader() {
        return waitForExpectedElement(By.id("co_facetHeaderdocumentType"));
    }

    /**
     * List of search results
     *
     * @return
     */
    public List<WebElement> searchResultsList() {
        return waitForExpectedElements(By.xpath("//ol[@class='co_searchResult_list']/li[contains(@id,'cobalt_search_results')]"), 10);
        //By.cssSelector(".co_searchResult_list li[id^=\"cobalt_search_results\"]")
    }

    /**
     * This method gets the title of the know how search result item based on the given resultItem index on the results list.
     */
    public String getResultItem(String resultItemNumber) {
        return waitForExpectedElement(By.cssSelector("#cobalt_result_knowhow_title" + resultItemNumber)).getText();
    }

    /**
     * This method gets the entire text of the know how search result item based on the given resultItem index on the results list.
     * This method is DEPRECATED and should not be used
     */
    public String getWholeResultItem(String resultItemNumber) {
        return waitForExpectedElement(By.id("cobalt_search_results_knowHowPlc" + resultItemNumber)).getText();
    }

    /**
     * This method gets the entire text of the know how search result item based on the given resultItem index on the results list.
     * Have just amended the title of this method to make it clear it is specific to know how
     */
    public String getWholeResultItemKnowHow(String resultItemNumber) {
        return waitForExpectedElement(By.id("cobalt_search_results_knowHowPlc" + resultItemNumber)).getText();
    }

    /**
     * This method gets the title of the whats market search result item based on the given resultItem index on the results list.
     */
    public String getResultItemWhatsMarket(String resultItemNumber) {
        return waitForExpectedElement(By.cssSelector("#cobalt_result_whatsmarket_title" + resultItemNumber)).getText();
    }

    /**
     * This method gets the entire text of the whats market search result item based on the given resultItem index on the results list.
     */
    public String getWholeResultItemWhatsMarket(String resultItemNumber) {
        return waitForElementPresent(By.id("cobalt_result_whatsmarket_title" + resultItemNumber)).getText();
    }


    /**
     * this is the icon for the terms in context options - less detail
     */
    public WebElement lessDetailIcon() {
        return waitForExpectedElement(By.xpath("//span[@class='icon25 icon_details_1']/self::*[text()='Less Detail']"));
    }

    /**
     * this is the icon for the terms in context options - more detail
     */
    public WebElement moreDetailIcon() {
        return waitForExpectedElement(By.xpath("//span[@class='icon25 icon_details_2']/self::*[text()='More Detail']"));
    }

    /**
     * This method gets the default selected selector text and verfies against the given selector and returns the boolean value accordingly
     *
     * @param sliderSelector
     * @return boolean
     */
    public boolean isSliderSelectorDisplayed(SliderSelector sliderSelector) {
        String text = null;
        if (sliderSelector.equals(SliderSelector.LESS)) {
            text = "Less Detail";
        } else if (sliderSelector.equals(SliderSelector.MORE)) {
            text = "More Detail";
        } else if (sliderSelector.equals(SliderSelector.MOST)) {
            text = "Most Detail";
        }
        text = text.toUpperCase();
        try {
            String textReturned = waitForExpectedElement(By.id("detailSliderSelectorSelectedOption"), 20).getText();
            System.out.println(" ...The text returned from the selector is: " + textReturned);
            return textReturned.toUpperCase().contains(text);
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    /**
     * this is the icon for the terms in context options - most detail
     */
    public WebElement mostDetailIcon() {
        return waitForExpectedElement(By.xpath("//span[@class='icon25 icon_details_3']/self::*[text()='Most detail']"));
    }

    /**
     * this is the arrow icon to select when changing the terms in context selection
     */
    public WebElement changeTermsInContextArrowIcon() {
        return waitForExpectedElement(By.cssSelector("#co_searchDetailSliderLink"), 10);
    }

    /**
     * This is a method for the drop down anchor for displaying more or less details on the search results page.
     */
    public WebElement moreOrLessDetailAnchor() {
        return waitForExpectedElement(By.id("detailSliderSelectorSelectedOption"));
    }

    public boolean ismoreOrLessDetailAnchorDisplayed() {
    	return isElementDisplayed(By.id("detailSliderSelectorSelectedOption"));
    }

    /**
     * this is the dropdown displayed when the user selects the arrow to change terms in context
     * setting - this object is the first option for less detail
     */
    public WebElement lessDetailOption() {
        return waitForExpectedElement(By.xpath("//ul[@class='co_dropDownMenuList']//a[contains(., 'Less') and contains(., 'etail')]"));
    }

    /**
     * this is the dropdown displayed when the user selects the arrow to change terms in context
     * setting - this object is the first option for less detail
     */
    public WebElement moreDetailOption() {
        return waitForExpectedElement(By.xpath("//ul[@class='co_dropDownMenuList']//a[contains(., 'More') and contains(., 'etail')]"));
    }

    /**
     * this is the dropdown displayed when the user selects the arrow to change terms in context
     * setting - this object is the first option for less detail
     */
    
    public WebElement mostDetailOption() {
        return waitForExpectedElement(mostDetailOptionLocator);
    }
    
    public boolean isMostDetailOptionPresent() {
        return isExists(mostDetailOptionLocator);
    }

    public WebElement detailSliderDropdown() {
        return waitForExpectedElement(By.xpath("//a[@id='co_searchDetailSliderLink']//span[2]"));
    }

    /**
     * this is the first terms in context paragraph or snippet
     */
    public WebElement firstSnippetPara() {
        return waitForElementPresent(By.id("cobalt_result_knowhow_snippet_1_1"));
    }

    public boolean isFirstSnippetParaDisplayed() {
    	return isElementDisplayed(By.id("cobalt_result_knowhow_snippet_1_1"));
    }

    public boolean isSecondSnippetParaDisplayed() {
    	return isElementDisplayed(By.id("cobalt_result_knowhow_snippet_1_2"));
    }

    /**
     * this is the second terms in context paragraph or snippet
     */
    public WebElement secondSnippetPara() {
        return waitForElementPresent(By.id("cobalt_result_knowhow_snippet_1_2"));
    }

    /**
     * this is the 'Did You Mean' text on the search results page
     */
    public WebElement didYouMeanText() {
        return waitForExpectedElement(By.xpath("//div[@id='co_search_results_inner']/div[1]/div[1]/h2[contains(text(),'Did you mean')]"));
    }

    /**
     * This method is to find the Did you mean block is dipalyed or not and returns the boolean value accordingly.
     *
     * @return boolean
     */
    public boolean isDidYouMeanTextDisplayed() {
    	return isElementDisplayed(By.className("co_search_didyoumean"));
    }

    /**
     * this is the link to the corrected spelling for did you mean
     */
    public WebElement correctedResultsLink() {
        return waitForExpectedElement(By.xpath("//a [@id='co_search_spellCheck']/em"));
    }

    /**
     * this is the search term highlighted in snippet text (terms in context displayed on the result list)
     */
    public WebElement highlightedSearchTerm(String rank, String highlightedTerm) {
        return waitForExpectedElement(By.xpath("//div[contains(@id,'co_snippet_1_" + rank + "')]/a/span[1][text()='" + highlightedTerm + "']"));
    }

    public List<WebElement> highlightedSearchTermsInSummary(String rank) {
        return waitForExpectedElements(By.xpath("//div[@id='co_searchResults_summary_" + rank + "']//span[@class='co_searchTerm']"));
    }

    public List<WebElement> highlightedSearchTermsInFirstSnippet(String rank) {
        return waitForExpectedElements(By.xpath("//div[@id='co_snippet_" + rank + "_1']//span[@class='co_searchTerm']"));
    }

    /**
     * this is the know how link on search results
     */
    public WebElement knowHowLink() {
        return waitForExpectedElement(By.xpath("//div[@id='contentTypeTabsPlcuk']//li/a[contains(text(),'Know-How')]"));
    }

    /**
     * this is the whats market link on search results
     */
    public WebElement whatsMarketLink() {
        return waitForExpectedElement(By.xpath("//div[@id='contentTypeTabsPlcuk']//li/a[contains(text(),'Market UK')]"), 10);
    }


    /**
     * these are the know how link, whats market links etc when they are selected on the search results page (so the link is no longer active)
     */
    public WebElement knowHowOptionSelected() {
        return waitForExpectedElement(By.xpath("//div[@id='contentTypeTabsPlcuk']//li[@class='co_tabItem co_tabActive']/a[contains(text(),'Know-How')]"));
    }

    public WebElement whatsMarketOptionSelected() {
        return waitForExpectedElement(By.xpath("//div[@id='contentTypeTabsPlcuk']//li[@class='co_tabItem co_tabActive']/a[contains(text(),'Market UK')]"));
    }


    /**
     * this is the search result total at the top of the search results page
     */
    public WebElement searchResultsTotal(String number) {
        return waitForExpectedElement(By.xpath("//ol[@id='co_navigationPages']/li/span[contains(text(),'" + number + "')]"), 10);
    }

    /**
     * this is the text "No Documents Found" displayed following an unsuccessful search
     */
    public WebElement noDocumentsFoundText() {
        return waitForExpectedElement(By.id("cobalt_search_no_results"));
    }

    /**
     * this is the arrow associated with the envelope icon for selecting delivery options
     */
    public WebElement deliveryDropButton() {
        return waitForExpectedElement(By.xpath("//li[contains(@id,'deliveryWidget')]//span[contains(@class,'icon_down_blue_arrow')]"), 10);
    }

    /**
     * this is the email option accessed via the dropdown above
     */
    public WebElement emailDeliveryOption() {
        return waitForExpectedElement(By.id("deliveryLinkRow1Email"), 8);
    }

    /**
     * this is the print option accessed via the dropdown above
     */
    public WebElement printDeliveryOption() {
        return waitForExpectedElement(By.id("deliveryRow1Print"), 8);
    }

    /**
     * this is the download option accessed via the dropdown above
     */
    public WebElement downloadDeliveryOption() {
        return waitForExpectedElement(By.id("deliveryRow1Download"), 8);
    }

    public boolean isDeliveryDropButtonPresent() {
    	return isElementDisplayed(By.xpath("//li[contains(@id,'deliveryWidget')]//span[contains(@class,'icon_down_blue_arrow')]"));
    }

    public boolean isEmailDeliveryOptionPresent() {
    	return isElementDisplayed(By.id("deliveryLinkRow1Email"));
    }

    public boolean isDownloadDeliveryOptionPresent() {
    	return isElementDisplayed(By.id("deliveryRow1Download"));
    }

    public boolean isPrintDeliveryOptionPresent() {
    	return isElementDisplayed(By.id("deliveryRow1Print"));
    }

    /**
     * this is the kindle download option accessed via the dropdown above
     */
    public WebElement kindleDeliveryOption() {
        return waitForExpectedElement(By.id("deliveryRow1Kindle"));
    }

    /**
     * this is the option/icon to add to a folder
     */
    public WebElement addToFolderOption() {
        return waitForExpectedElement(By.xpath("//a[contains(@class,'co_dropdownTitle')][text()='Save To Folder']"));
    }

    /**
     * this is the facet count - pass in the facet name as a string e.g. Standard clauses
     */
    public WebElement facetCount(String name) {
        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + name + "')]/../span[@class='co_facetCount']"));
    }

    /**
     * object representing the link More Resource Types
     */
    public WebElement moreResourceTypesLink() {
        return waitForExpectedElement(By.id("co_facet_selectLink_documentType"));
    }

    /**
     * object representing the Resource Type heading
     */
    public WebElement resourceTypeFacetGroupHeading() {
        return waitForExpectedElement(By.id("co_facetHeaderdocumentType"));
    }

    /**
     * object representing facet checkbox - pass in the facet name as a string
     */
    public WebElement facetCheckbox(String facetName) {
        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetName + "')]/../input[@type='checkbox']"));
    }

    /**
     * object representing whats market result title
     */
    public WebElement whatsMarketResultTitle(String result) {
        return waitForExpectedElement(By.id("cobalt_result_whatsmarket_title'" + result + "'"));
    }

    /**
     * object representing whats market deal type in a result e.g. Listing Rules Transactions
     */
    public WebElement whatsMarketResultDealType(String result) {
        return waitForExpectedElement(By.xpath("//div[@id=co_searchResults_citation_'" + result + "']/span[contains(@id,'co_search_search_detail')]"));
    }


    public WebElement searchResultPosition(String position) {
        return waitForExpectedElement(By.xpath("//*[@id='coid_website_searchResults']//a[contains(@href,'Document') and @rank='" + position + "']"), 30);
    }

    public WebElement searchResultPositionCheckbox(int position) {
        return waitForExpectedElement(
                By.xpath("(//*[@id='co_search_results_inner']//input[@type='checkbox'])[" + String.valueOf(position) + "]"), 15);
    }

    public WebElement saveToFolder() {
        return waitForExpectedElement(By.xpath("(//*[@id='co_saveToWidget' or @id='saveToFolder']//a)[1]"), 10);
    }

    public WebElement folderingPopupMessage() {
        return waitForExpectedElement(By.xpath("//*[@class='co_foldering_popupMessageContainer']//*[@class='co_infoBox_message']"), 40);
    }

    public void waitWhileFolderingPopupMessageBeVisible() {
        waitForElementVisible(By.xpath("//*[@class='co_foldering_popupMessageContainer']//*[@class='co_infoBox_message']"));
    }

    /**
     * This method is to find the all facets displayed with count values and returned as map with facetname as key and count as value.
     *
     * @return Map<String, Integer>
     */
    public Map<String, Integer> getAllFacetsWithCountValues() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector("div.co_divider>ul>li[id^='co_facet']"), 10)) {
                map.put(element.findElement(By.cssSelector("label")).getText(), Integer.valueOf(element.findElement(By.cssSelector("span")).getText().trim()));
            }
            return map;
        } catch (NumberFormatException | TimeoutException | NoSuchElementException e) {
            LOG.info("context", e);
            return Collections.emptyMap();
        }
    }

    /**
     * This method verifies the facet type headers are displayed or not and returns the boolean value accordingly.
     *
     * @return boolean
     */
    public boolean isFacetTypeHeadersDisplayed() {
    	return isElementDisplayed(By.cssSelector("div.co_genericBoxContent div#co_narrowResultsBy div"));
    }

    /**
     * This method gets the titles of the displayed results in search result page.
     *
     * @return List<String>
     */
    public List<String> getAllResultsTitle() {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement searchResult : waitForExpectedElements(By.cssSelector("a[id^='cobalt_result_knowhow_title']"), 10)) {
                list.add(searchResult.getText());
            }
        } catch (PageOperationException te) {
            LOG.info("context", te);
        }
        return list;
    }

    /**
     * This method returns the description displayed as part of the result item.
     *
     * @param index
     * @return String
     */
    public String getResultItemDescription(int index) {
        try {
            return waitForExpectedElement(By.cssSelector("#cobalt_search_results_knowhowukdebug" + index + " div[class^='co_snippet co_search_detailLevel']>a")).getText();
        } catch (TimeoutException te) {
            LOG.info("context", te);
            return StringUtils.EMPTY;
        }
    }

    /**
     * This method find the result item based on given name and opens the PLC window by clicking on plc link in result item.
     *
     * @param resultItemTitle
     */
    public void clickOnResultItemToOpenInPLCWindow(String resultItemTitle) {
        waitForExpectedElement(By.xpath(".//label[contains(text(),'" + resultItemTitle + "')]/../*[@class='co_searchResults_citation']/div/span/a[@target='plc_window']"), 8).click();
    }

    /**
     * This method is to select the PLC link present under the given result item number displayed in search results
     *
     * @param resultIndex
     */
    public void openPLCLinkFromResultItem(String resultIndex) {
        waitForExpectedElement(By.cssSelector("#cobalt_search_results_knowhowuk" + resultIndex + " a[target='plc_window']"), 8).click();
    }

    /**
     * object representing the Practical Law home logo on all search result pages
     */
    public WebElement practicalLawLogo() {
        return waitForExpectedElement(By.xpath("//div[@id='header_lower_logo']/h1/a[contains(text(),'Practical Law')]"));
    }

    /**
     * object representing the Clear all filters option
     */
    public WebElement clearAllFilters() {
        return waitForExpectedElement(By.xpath("//div[@id='co_undoAllSelections']/a[@class='co_btnGray co_btnBack']"));
    }

    /**
     * object representing the option to Select Multiple Filters
     */
    public WebElement selectMultipleFilters() {
        return waitForExpectedElement(By.linkText("Select multiple filters"));
    }

    /**
     * object representing the option to Cancel all filters
     */
    public WebElement cancelFilters() {
        return waitForExpectedElement(By.xpath("//div[@id='co_multifacet_selector_1']/a[@title='Cancel Multiple Filters Mode']"));
    }

    /**
     * object representing the option to Apply Filters
     */
    public WebElement applyFilters() {
        return waitForExpectedElement(By.xpath("//div[@id='co_multifacet_selector_1']/a[@class='co_multifacet_apply']"));
    }

    /**
     * object representing the heading e.g. Media & Telecoms displayed under the heading Practice Area in the facet section
     * of the search results page - when the user has conducted a practice area search (so only the practice area facet and it's children are displayed)
     */
    public WebElement practiceAreaFacetHeader(String name) {
        return waitForExpectedElement(By.xpath("//div[@id='co_facetSubHeaderknowHowPracticeAreaSummary']/span/b[contains(text(),'" + name + "')]"));
    }

    /**
     * object representing the topic facet checkboxes following a practice area scoped search
     */
    public WebElement practiceAreaTopicFacetCheckbox(String name) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_knowHowPracticeAreaSummary']//label[contains(text(),'" + name + "')]"));
    }

    /**
     * this is the link to practical law production from a subject area search - these links will be removed once the product is live
     */
    public WebElement testLink(int n) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + n + "']/div[4]/span/a"));
    }

    /**
     * object representing the download delivery icon on the search results page
     */
    public WebElement downloadDeliveryIcon() {
        return waitForExpectedElement(By.xpath("//a[@id='deliveryLinkRow1Download']"), 5);
    }

    /**
     * object representing the email delivery icon on the search results page
     */
    public WebElement emailDeliveryIcon() {
        return waitForExpectedElement(By.xpath("//a[@id='deliveryLinkRow1Email']/span"));
    }

    /**
     * object representing the print delivery icon on the search results page
     */
    public WebElement printDeliveryIcon() {
        return waitForExpectedElement(By.xpath("//a[@id='deliveryLinkRow1Print']"));
    }

    /**
     * object representing the download delivery pop up with the heading Download Documents
     */
    public WebElement downloadDocumentsPopUp() {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryLightbox']/div[1]//h3[contains(text(),'Download Documents')]"), 8);
    }

    /**
     * object representing the email delivery pop up with the heading Email Documents
     */
    public WebElement emailDocumentsPopUp() {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryLightbox']/div[1]//h3[contains(text(),'Email Documents')]"));
    }

    /**
     * object representing the email delivery pop up with the heading Email This Document
     */
    public WebElement emailSingleDocumentPopUp() {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryLightbox']//h3[contains(text(),'Email This Document')]"));
    }

    /**
     * object representing the print delivery pop up with the heading Print Documents
     */
    public WebElement printDocumentsPopUp() {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryLightbox']/div[1]//h3[contains(text(),'Print Documents')]"), 10);
    }

    /**
     * object representing the print delivery pop up with the heading Print This Document
     */
    public WebElement printSingleDocumentPopUp() {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryLightbox']//h3[contains(text(),'Print This Document')]"), 10);
    }

    /**
     * object representing the delivery pop up displayed when the user selects only one checkbox on the result list with the heading Download This Document
     */
    public WebElement downloadSingleDocumentPopUp() {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryLightbox']/div[1]//h3[contains(text(),'Download This Document')]"));
    }

    /**
     * object representing the basic tab on download and email delivery pop up
     */
    public WebElement basicTab() {
        return waitForExpectedElement(By.xpath("//li[@id='co_deliveryOptionsTab1']/div/h2/a[contains(text(),'Basic')]"));
    }

    /**
     * object representing the advanced tab on download and email delivery pop up
     */
    public WebElement advancedTab() {
        return waitForExpectedElement(By.linkText("Advanced"));
    }

    /**
     * object representing the option to select a list of items on download and email delivery pop up
     */
    public WebElement listOfItemsOption() {
        return waitForExpectedElement(By.id("co_deliveryWhatToDeliverList"));
    }

    /**
     * object representing the option to select documents on download and email delivery pop up
     */
    public WebElement documentsOption() {
        return waitForExpectedElement(By.id("co_deliveryWhatToDeliverDocumentOnly"), 5);
    }

    /**
     * object representing the option to include a table of contents on download and email delivery pop up
     */
    public WebElement tableOfContentsOption() {
        return waitForExpectedElement(By.id("coid_chkDdcLayoutTableOfContents"));
    }

    /**
     * object representing the format dropdown options on download and email delivery pop up when the document option is selected
     */
    public WebElement formatDropdownDocuments() {
        return waitForExpectedElement(By.xpath(("//select[contains(@id,'co_delivery_format_fulltext')]")));
    }

    /**
     * object representing the format dropdown options on download and email delivery pop up when the list of items option is selected
     */
    public WebElement formatDropdownList() {
        return waitForExpectedElement(By.xpath(("//select[contains(@id,'co_delivery_format_list')]")));
    }

    /**
     * object representing the format dropdown options on download and email delivery pop up
     */
    public WebElement formatDropdownOptionList(String option) {
        return waitForExpectedElement(By.xpath("//select[contains(@id,'co_delivery_format_list')]/option[contains(text(),'" + option + "')]"));
    }

    /**
     * object representing the format dropdown options on download and email delivery pop up is displayed
     */
    public boolean isFormatDropdownOptionListPresent(String option) {
        return isExists(By.xpath("//select[contains(@id,'co_delivery_format_list')]/option[contains(text(),'" + option + "')]"));
    }


    /**
     * object representing the format dropdown options on download and email delivery pop up
     */
    public WebElement formatDropdownOptionDocuments(String option) {
        return waitForExpectedElement(By.xpath("//select[contains(@id,'co_delivery_format_fulltext')]/option[contains(text(),'" + option + "')]"));
    }

    /**
     * object representing the As dropdown on the download and email delivery pop up
     */
    public WebElement asDropdown() {
        return waitForExpectedElement(By.id("co_delivery_fileContainer"));
    }

    /**
     * object representing the as dropdown options on the download and email delivery pop up
     */
    public WebElement asDropdownOption(String option) {
        return waitForExpectedElement(By.xpath("//select[@id='co_delivery_fileContainer']/option[contains(text(),'" + option + "')]"));
    }

    /**
     * object representing the download radio button on download delivery pop up
     */
    public WebElement downloadButton() {
        return waitForExpectedAndClickableElement(By.xpath("//input[@id='co_deliveryDownloadButton'][@type='button']"));
    }

    /**
     * object representing the download button on download delivery pop up - Once the download is ready
     */
    public WebElement downloadButtonWhenReady() {
        return waitForExpectedElement(By.xpath("//input[contains(@id,'_downloadButton')][@type='button'][@class='co_primaryBtn']"), 10);
    }

    /**
     * object representing the cancel radio button on the download and email delivery pop up
     */
    public WebElement cancelButton() {
        return waitForExpectedElement(By.id("co_deliveryCancelButton"));
    }

    /**
     * object representing the term highlighting checkbox on download and email delivery pop up
     */
    public WebElement termHighlightingCheckbox() {
        return waitForExpectedElement(By.id("coid_chkDdcLayoutTermHighlighting"));
    }

    /**
     * object representing the expanded margin for notes checkbox on download and email delivery pop up
     */
    public WebElement expandedMarginCheckbox() {
        return waitForExpectedElement(By.id("coid_chkDdcLayoutRightNoteMarging"), 5);
    }

    public boolean isExpandedMarginCheckboxPresent() {
        return isElementDisplayed(By.id("coid_chkDdcLayoutRightNoteMarging"));
    }

    /**
     * object representing the cover page checkbox on download and email delivery pop up
     */
    public WebElement coverPageCheckbox() {
        return waitForExpectedElement(By.id("coid_chkDdcLayoutCoverPage"));
    }

    /**
     * object representing the checkbox associated with each know how result item on the know how search results page
     */
    public WebElement resultCheckbox(String number) {
        return waitForExpectedElement(By.xpath("//input[@id='cobalt_search_knowhow_checkbox_" + number + "']"));
    }

    /**
     * object representing the checkbox associated with each result item on the whats market search results page
     */
    public WebElement resultCheckboxWhatsMarket(String number) {
        return waitForExpectedElement(By.xpath("//input[@id='cobalt_search_whatsmarket_checkbox_" + number + "']"), 15);
    }

    public WebElement resultCheckboxKeyDate(String number){
        return waitForExpectedElement(By.xpath("//input[@id='cobalt_search_keyDateUK_checkbox_" + number + "']"));
    }

    public WebElement resultCheckboxForAnyResultType(String number) {
        return waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']/li[" + number + "]//input[@type='checkbox']"));
    }

    /**
     * object representing the "What to Deliver" heading displayed when the basic tab is selected on the download delivery pop up
     */
    public WebElement whatToDeliverHeader() {
        return waitForExpectedElement(By.xpath("//div[@id='co_delivery_whatToDeliverContainer']/h3[contains(text(),'What to Deliver')]"));
    }

    /**
     * object representing the "Cover Page Comment" field on the advanced tab of the download and email delivery pop up
     */
    public WebElement coverPageField() {
        return waitForExpectedElement(By.id("coid_DdcLayoutCoverPageComment"));
    }

    /**
     * object representing the Font Size dropdown on advanced tab of download and email delivery pop up
     */
    public WebElement fontSizeDropdown() {
        return waitForExpectedElement(By.id("co_delivery_fontSize"));
    }

    /**
     * object representing the Font Size dropdown options on advanced tab of download and email delivery pop up
     */
    public WebElement fontSizeDropdownOption(String option) {
        return waitForExpectedElement(By.xpath("//select[@id='co_delivery_fontSize']/option[contains(text(),'" + option + "')]"));
    }

    /**
     * object representing the Links dropdown on advanced tab of download and email delivery pop up
     */
    public WebElement linksDropdown() {
        return waitForExpectedElement(By.id("co_delivery_linkColor"));
    }

    /**
     * object representing the links dropdown options on advanced tab of download and email delivery pop up
     */
    public WebElement linksDropdownOption(String option) {
        return waitForExpectedElement(By.xpath("//select[@id='co_delivery_linkColor']/option[contains(text(),'" + option + "')]"));
    }

    /**
     * object representing the underline checkbox on download and email delivery pop up
     */
    public WebElement underlineCheckbox() {
        return waitForExpectedElement(By.id("co_delivery_linkUnderline"));
    }

    /**
     * object representing the message displayed to the user following a successful download request
     */
    public WebElement downloadReadyMessage() {
        return waitForExpectedElement(By.xpath("//div[@id='co_deliveryWaitMessageTitle'][contains(text(),'The items are ready to download')]"), 8);
    }

    /**
     * object representing the message displayed to the user following a successful download request
     */
    public WebElement downloadSingleDocReadyMessage() {
        return waitForExpectedElement(By.xpath("//div[@id='co_headerMessage'][contains(text(),'Ready For Download')]"), 8);
    }

    public WebElement readyOverlayContentMessage() {
        return commonMethods.waitForExpectedElement(By.xpath("//div[contains(@class,'co_overlayBox')]//*[contains(text(),'ready') or contains(text(), 'will')]"),
                TIMEOUT_IN_SECONDS, POLLING_TIME_IN_MILLISECONDS);
    }

    public WebElement prepareOverlayContentMessage() {
        return commonMethods.waitForExpectedElement(By.xpath("//div[@id='co_deliveryWaitMessageTitle' and contains(.,'prepared')]"),
                TIMEOUT_IN_SECONDS, POLLING_TIME_IN_MILLISECONDS);
    }

    /**
     * object representing the "To" field on the email delivery pop up
     */
    public WebElement emailToField() {
        return waitForExpectedElement(By.xpath("//textarea[@id='co_delivery_emailAddress']"));
    }

    /**
     * object representing the "Subject" field on the email delivery pop up
     */
    public WebElement emailSubjectField() {
        return waitForExpectedElement(By.xpath("//input[@id='co_delivery_subject']"));
    }

    /**
     * object representing the "Email Note" field on the email delivery pop up
     */
    public WebElement emailNoteField() {
        return waitForExpectedElement(By.id("co_delivery_note"));
    }

    /**
     * object representing the email radio button on the email delivery pop up
     */
    public WebElement emailButton() {
        return waitForExpectedElement(By.id("co_deliveryEmailButton"));
    }

    /**
     * object representing the print radio button on the print delivery pop up
     */
    public WebElement printButton() {
        return waitForExpectedElement(By.id("co_deliveryPrintButton"), 10);
    }

    /**
     * object representing the title which sits immediately above search results e.g. on a country page it will say the country name e.g. Italy
     */
    public WebElement resultPageTitle(String title) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_website_searchAvailableFacets']//h1[contains(text()," + title + ")]"));
    }

    /**
     * object representing the jurisdiction field for search results (know how) identified by a particular result number
     */
    public WebElement jurisdiction(String result, String jurisdiction) {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_knowHowPlc" + result + "']//div[@class='co_search_detailLevel_1'][contains(text()," + jurisdiction + ")]"));
    }

    /**
     * Getting all the Search Results Title Links without using any for loop or passing any id in the argument
     */
    public List<WebElement> getAllSearchTitleLinks() {
        return waitForExpectedElements(By.xpath(ALL_RESULT_LINKS_XPATH));
    }

    /**
     * Getting the Search Results Title Link for result number
     */
    public WebElement getSearchTitleLink(int resultNumber) {
        return waitForExpectedElement(
                By.xpath("(" + ALL_RESULT_LINKS_XPATH + ")[" + resultNumber + "]"));
    }

    /**
     * object representing the last page navigation arrow at the base of the search results page
     */
    public WebElement lastPageNavigationArrow() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_last'][contains(text(),'Last Page')]"), 15);
    }

    public boolean isLastPageNavigationArrowPresent() {
        return isExists(By.xpath("//a[@id='co_search_footer_pagination_last'][contains(text(),'Last Page')]"));
    }

    /**
     * object representing a check on whether last page navigation arrow is present
     */

    public boolean checkLastPageNavigationArrowNotDisplayed() {

        return isExists(By.xpath("//a[@id='co_search_footer_pagination_next'][contains(text(),'Last Page')]"));
    }

    /**
     * object representing the next page navigation arrow at the base of the search results page
     */
    public WebElement nextPageNavigationArrow() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_next'][contains(text(),'Next Page')]"), 15);
    }
    public boolean isNextPageNavigationArrowPresent() {
        return isExists(By.xpath("//a[@id='co_search_footer_pagination_next'][contains(text(),'Next Page')]"));
    }
    /**
     * object representing a check on whether the next page navigation arrow is displayed
     */

    public boolean checkNextPageNavigationArrowNotDisplayed() {

        return isExists(By.xpath("//a[@id='co_search_footer_pagination_next'][contains(text(),'Next Page')]"));
    }


    /**
     * object representing the first page navigation arrow at the base of the search results page
     */
    public WebElement firstPageNavigationArrow() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_first'][contains(text(),'First Page')]"), 15);
    }

    public boolean isFirstPageNavigationArrowPresent() {
        return isExists(By.xpath("//a[@id='co_search_footer_pagination_first'][contains(text(),'First Page')]"));
    }
    /**
     * object representing a check on whether the first page navigation arrow is displayed
     */

    public boolean checkFirstPageNavigationArrowNotDisplayed() {

        return isExists(By.xpath("//a[@id='co_search_footer_pagination_next'][contains(text(),'First Page')]"));
    }


    /**
     * object representing the previous page navigation arrow at the base of the search results page
     */
    public WebElement previousPageNavigationArrow() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_prev'][contains(text(),'Previous Page')]"), 15);
    }
    public boolean isPreviousPageNavigationArrowPresent() {
        return isExists(By.xpath("//a[@id='co_search_footer_pagination_prev'][contains(text(),'Previous Page')]"));
    }
    /**
     * object representing a check on whether the previous page navigation arrow is displayed
     */

    public boolean checkPreviousPageNavigationArrowNotDisplayed() {

        return isExists(By.xpath("//a[@id='co_search_footer_pagination_next'][contains(text(),'Previous Page')]"));
    }

    /**
     * object representing the currently selected page on the search results page
     */
    public WebElement currentSelectedPage(String number) {
        return waitForExpectedElement(By.xpath("//span[@id='co_search_footer_pagination_current']/strong[contains(text(),'" + number + "')]"), 15);
    }
    public boolean isCurrentSelectedPagePagePresent(String number) {
        return isExists(By.xpath("//span[@id='co_search_footer_pagination_current']/strong[contains(text(),'" + number + "')]"));
    }
    /**
     * object representing a non selected page on the search results page
     */
    public WebElement nonSelectedPage(String number) {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_page" + number + "']"), 20);
    }
    public boolean isNonSelectedPagePresent(String number) {
        return isExists(By.xpath("//a[@id='co_search_footer_pagination_page" + number + "']"));
    }
    /**
     * Object representing the Back to What's Market/Home/Practice Area/Deal type link etc
     */
    public WebElement backToLink(String title) {

        String text = "'" + title + "'";
        if (title.contains("'")) {
            title = "\"" + title + "\"";
            text = title;
        }
        return waitForExpectedElement(By.xpath("//div[@id='subHeader']//a[contains(text()," + text + ")]"));
    }

    /**
     * element that provides the selected dropdown option
     */
    public WebElement selectedPerPageDropdownOption() {
        return waitForElementPresent(By.xpath("//div[@id='displayItemCount']//span[contains(@class,'icon_checkmark')]/../..//a"));
    }

    /**
     * Open document with specified title and position number in search results
     *
     * @param positionNumber Number of document in results
     * @param title          Document title
     */
    public void clickOnDocumentWithTitleAndNumber(String positionNumber, String title) {
        waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']/li[" + positionNumber + "]//a[contains(@id, 'title') and contains(., '" + title + "')]")).click();
    }

    /**
     * Get element with link from search results
     *
     * @param titlePart Part or full expected link title
     * @return WebElement with result link
     */
    public WebElement getResultItemByTitle(String titlePart) {
        return waitForExpectedElement(By.xpath("//h3//a[contains(@id, 'cobalt_result') and contains(., '" + titlePart + "')]"));
    }

    /**
     * this object is SPECIFIC to the email archive search results page and is the header e.g. Email archive | Construction
     */
    public WebElement emailArchiveHeader(String practiceArea) {
        return waitForExpectedElement(By.xpath("//h1[@id='co_browsePageLabel'][contains(text(),'" + practiceArea + "')][contains(text(),'Email archive')]"));
    }

    /**
     * this object is SPECIFIC to the email archive search results page and is the document type which always states Emails for this
     * page
     */
    public WebElement documentTypeText() {
        return waitForExpectedElement(By.xpath("//li[@id='cobalt_search_results_knowHowPlc1']//div[contains(text(),'Emails')]//strong[contains(text(),'Document Type')]"));
    }

    public void clickToFirstItemIntoResultList(){
        getFirstItemIntoResultList().click();
    }

    public WebElement getFirstItemIntoResultList(){
        return waitForExpectedElement(By.cssSelector("li#cobalt_search_results_knowHowPlc1 h3 a"));
    }

    public WebElement errorMessage() {
        return waitForExpectedElement(errorMessageBy());
    }

    public By errorMessageBy() {
        return By.id("coid_website_errorsSummaryPlaceHolder");
    }

    public WebElement selectAllCheckbox() {
        return waitForExpectedElement(By.xpath("//input[@id='co_searchHeader_selectAll']"));
    }

    public List<WebElement> getLinksToPages() {
        return findElements(By.xpath("//*[@id='co_navigationFooter']/li"));
    }

    public void clickOnDocumentInGroupByIndex(int positionNumber, String groupName) {
        waitForExpectedAndClickableElement(By.xpath("//*[@class='co_search_header' and text()='" + groupName + "']/following-sibling::ol/li[" + positionNumber + "]/div[@class='co_searchContent']/h3/a")).click();
    }

    public WebElement getDocumentLinkByName(String name){
        return waitForExpectedAndClickableElement(By.xpath("//div[@class='co_searchContent']//a[contains(.,'" + name + "')]"));
    }

    public List<WebElement> getDocumentsList(){
        return waitForExpectedElements(By.xpath("//div[@class='co_searchContent']//a"));
    }

    public List<WebElement> getCurrentCategoryLinks(String groupName) {
        return waitForElementsToBeClickableAndReturnElement(By.xpath("//*[@class='co_search_header' and text()='" + groupName + "']/following-sibling::ol/li"));
    }

    public WebElement searchPageLabel() {
        return waitForExpectedElement((By.xpath("//h1[@id='co_browsePageLabel']")));
    }
    
    public void clickOnMinimizeButtonIfPresent() {
    	if(isExists(By.id(MINIMIZE_BTN_ID))){
    		waitForExpectedElement(By.id(MINIMIZE_BTN_ID)).click();;	
    	}
    }
    

    public List<WebElement> getTopicPageFacetsAsList() {
        return waitForElementsClickable(By.cssSelector("#ukplc_topic_facet_links li a"));
    }

    public List<WebElement> getSearchFacets() {
        return findElements(By.id("co_website_searchFacets"));

    }

    /**
     * this object represents the practical law tab on the page of search results passing in the expected name as a variable
     */

    public WebElement resultsTab(String tabname) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchContentTabs']//a[contains(text(),'" + tabname + "')]"));
    }


    /**
     * this object represents the "active" tab on the page of search results passing through the name of the tab as a variable
     */

    public WebElement activeResultsTab(String tabname) {

        return waitForExpectedElement(By.xpath("//*[@id='co_searchContentTabs']//li[contains(@class,'co_tabActive')]//a[contains(text(),'" + tabname + "')]"));

    }


    public boolean isTabPresent(String tabname) {

        return isExists(By.xpath("//div[@id='co_searchContentTabs']//a[contains(text(),'" + tabname + "')]"));
    }


    public By searchByTitleAndCount() {
        return By.xpath("//div[@id='coid_website_searchAvailableFacets']//h1");
    }

    /**
     * this object represents the 0 results for message presented when the user does a search and gets nil results
     */

    public WebElement nilResultsMessage() {

        return waitForExpectedElement(By.xpath(".//*[@id='coid_website_searchAvailableFacets']//span[contains(text(),'0 Results for')]"));
    }

    /**
     * this object represents the original query text displayed to the user when the user does a search and gets nil results along with a
     * did you mean suggestion
     */

    public WebElement failedQuery(String query) {

        return waitForExpectedElement(By.xpath("//*[@id='coid_website_searchAvailableFacets']//span[contains(text(),'" + query + "')]"));
    }


    /**
     * this object represents the did you mean text displayed to the user when the user does a search and gets nil results along with a
     * did you mean suggestion
     */

    public WebElement didYouMeanMessage() {

        return waitForExpectedElement(By.xpath("//*[@id='co_fermiSearchResult_data']//h2[contains(text(),'Did you mean:')]"));
    }

    /**
     * this object represents the actual did you mean suggestion displayed to the user when the user does a search and gets nil results
     */


    public WebElement didYouMeanSuggestion() {

        return waitForExpectedElement(By.xpath("//*[@id='co_search_spellCheck']/em"));
    }

    /**
     * Wait while all elements which indicate about search results loading will be absent.
     * Order:
     * 1. Grey overlay (which appears when users changes sort order or facets)
     * 2. Loading picture for the facets
     * 3. Loading picture for the search results
     * 4. Loading picture for the facets again (due to point 2 and 3 above may be go in the different order)
     */
    public void waitLoadingElementsAbsent() {
        waitForElementToDissappear(By.xpath("//div[contains(@id, 'coid_lightboxOverlay')]"));
        waitForElementToDissappear(By.xpath("//*[contains(@class, 'co_progressIndicator')]"));
        waitForElementToDissappear(By.xpath("//*[contains(@class, 'co_search_ajaxLoading')]"));
        waitForElementToDissappear(By.xpath("//*[contains(@class, 'co_progressIndicator')]"));
    }

    /**
     * Wait while loading picture for the search results / facets or search results or "Did you mean...?" page appears
     */
    public void waitForLoadingPictureOrResults() {
        waitForExpectedElement(
                By.xpath("//*[contains(@class, 'co_search_ajaxLoading') or contains(@id, 'co_searchWaitProgress') or contains(@class, 'co_progressIndicator') or contains(@id, 'coid_lightboxOverlay')] | " +
                        "//ol[@class='co_searchResult_list'] | " +
                        "//span[@class='zero-results']"));
    }

    /**
     * Get all search results as blocks with result number, title, data and so on (top elements for each search result)
     *
     * @return List with elements for search results blocks
     */
    public List<WebElement> getResultsBlocks() {
        return waitForExpectedElements(By.xpath("//li[contains(@id, 'cobalt_search_results')]"));
    }

    /**
     * Get all links which contain "docguid" attribute to obtain the found document guids. Not every title can contains
     * this. For example, Act/SI title is not link
     *
     * @return List of WebElements with links which contain "docguid" attribute
     */
    public List<WebElement> getDocGuidLinksFromResults() {
        return waitForExpectedElements(By.xpath(ALL_DOC_GUID_LINKS_XPATH));
    }

    public WebElement getDocGuidLinkFromResults(int resultNumber) {
        return waitForExpectedElement(By.xpath("(" + ALL_DOC_GUID_LINKS_XPATH + ")[" + resultNumber + "]"));
    }

    /**
     * Get element with necessary meta-data (Subject, Keywords, etc.) for the result (parameter name will be included)
     *
     * @param resultNum Search result number
     * @param dataName Parameter name (e.g. Subject, Keywords, ...)
     * @return Element with parameter text (name will be included, e.g. "Subject: VAT")
     */
    public WebElement getDataForResult(String resultNum, String dataName) {
        return waitForExpectedElement(
                By.xpath("//ol[@class='co_searchResult_list']/li[" + resultNum + "]" +
                        "//div[contains(@class, 'detailLevel') and contains(., '" + dataName + "') and not(contains(@class, 'hide'))]"));
    }


    /**
     * Is necessary meta-data (Subject, Keywords, etc.) for the result exists
     *
     * @param resultNum Search result number
     * @param dataName Parameter name (e.g. Subject, Keywords, ...)
     * @return True - is data is exists, false - otherwise
     */
    public boolean isDataForResultExists(String resultNum, String dataName) {
        return isExists(
                By.xpath("//ol[@class='co_searchResult_list']/li[" + resultNum + "]" +
                        "//div[contains(@class, 'detailLevel') and contains(., '" + dataName + "') and not(contains(@class, 'hide'))]"));
    }

    /**
     * Get link for the delivery option
     *
     * @param option Delivery option: Email, Download, Print
     * @return Element for delivery option
     */
    public WebElement getDeliveryOption(String option) {
        return waitForExpectedElement(By.xpath("//a[contains(@id, 'deliveryLink') and contains(@id, '" + option + "')]/span"));
    }

    /**
     * Get all checkbox elements ftom the search results page
     *
     * @return List of elements for facet checkboxes
     */
    public List<WebElement> getAllFacetCheckBoxes() {
        return waitForExpectedElements(By.xpath("//div[@id='co_website_searchFacets']//input[@type='checkbox']"));
    }

    /**
     * Get the list of all names for visible facet checkboxes for given facet group (Topic, Jurisdiction, Resource Type, ...)
     * This method is not depends on current compartment
     *
     * @param facetGroupName Facet group name
     * @return The list with names for facets
     */
    public List<WebElement> getVisibleFacetNamesForGroup(String facetGroupName) {
        return waitForElementsVisible(By.xpath(String.format(VISIBLE_FACET_NAMES_BY_GROUP_XPATH, facetGroupName)));
    }

    /**
     * Check if any facet from given group is displayed
     * This method is not depends on current compartment
     *
     * @param facetGroupName Facet group name
     * @param facetName Facet name to verify
     * @return True - if the facet within given facet group and given name is displayed, false - otherwise
     */
    public boolean isFacetForGroupDisplayed(String facetGroupName, String facetName) {
        return isElementDisplayed(By.xpath(String.format(FACET_NAME_BY_GROUP_XPATH, facetGroupName, facetName)));
    }
}