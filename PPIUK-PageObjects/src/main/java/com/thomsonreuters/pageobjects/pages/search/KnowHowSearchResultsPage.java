package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.util.StringUtils;

import java.util.*;


public class KnowHowSearchResultsPage extends AbstractPage {


    private static final String CONTINUE_BTN_ON_TIMEOUT_WARNING_POP_UP_XPATH = "//div[@id='co_websiteTimeoutWarning']//input[contains(@value,'Continue')]";
    private static final String EXPAND_FACET_XPATH = "//div[@id='co_narrowResultsBy']//label[contains(text(),'%s')]/../a[@class='co_facet_expand']";
    public static final String KNOW_HOW_FACET_CHECKBOX_PATH = "//label[(normalize-space(text())='%1$s')]/parent::li//input[@type='checkbox'] | //*[.//*[text()='%1$s']|text()='%1$s' and contains(@class,'SearchFacet')]/preceding-sibling::input";
    public static final String FACET_CHECKBOX_BUTTON_COLLAPSED_PATTERN = "//span[contains(.,\"%s\")]/parent::button[@aria-expanded='false']";
    private CommonMethods commonMethods = new CommonMethods();

    public KnowHowSearchResultsPage() {
    }

    /**
     * expand a facet
     */
    public WebElement expandCollapsedFacet(String facetName) {
        return waitForExpectedElement(By.xpath(String.format(FACET_CHECKBOX_BUTTON_COLLAPSED_PATTERN, facetName)));
    }

    public boolean isFacetCheckboxCollapsed(String facetName) {
        return isElementDisplayed(By.xpath(String.format(FACET_CHECKBOX_BUTTON_COLLAPSED_PATTERN, facetName)));
    }

    /**
     * Is expand a facet icon is displayed for the facet with given name
     *
     * @param facetName The facet name
     * @return True - if expand facet icon is displayed for expected facet, false - otherwise.
     */
    public boolean isExpandFacetDisplayed(String facetName) {
        return isElementDisplayed(By.xpath(String.format(EXPAND_FACET_XPATH, facetName)));
    }

    public boolean isDeliveryPanelForSearchResultPresent() {
        return isExists(By.cssSelector(".co_navSelect"));
    }

    /**
     * collapse a facet
     */
    public WebElement collapseFacet(String facetName) {
        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetName + "')]/../a[@class='co_facet_collapse']"), 10);
    }

    /**
     * this is the facet name - pass in the facet name as a string e.g. Standard clauses
     */
    public WebElement facetName(String Name) throws Exception {
        return waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[contains(text(),'" + Name + "')]"), 10);
    }

    /**
     * check that facet name is not displayed
     */

    public boolean checkFacetNameDisplayed(String Name) {
        return isExists(By.xpath("//div[contains(@id,'narrowResultsBy')]//ul[not(contains(@class, 'hide'))]/li/label[contains(text(),'" + Name + "')]"));
    }


    /**
     * Object representing the total search result count for the search
     */
    public WebElement knowHowSearchResultCount() {
        return waitForExpectedElement(By.xpath("//div[@id='coid_website_searchAvailableFacets']//span[@class='co_search_titleCount']"), 10);
    }

    /**
     * Object representing any know how facet checkbox as identified by facet name
     */
    public WebElement knowHowFacetCheckbox(String facetName) {
        return waitForExpectedElement(By.xpath(String.format(KNOW_HOW_FACET_CHECKBOX_PATH, facetName)));
    }

    public boolean isKnowHowFacetCheckboxExist(String facetName) {
        return isExists(By.xpath(String.format(KNOW_HOW_FACET_CHECKBOX_PATH, facetName)));
    }

    /**
     * Object representing Resource Type heading for know how facet group
     */
    public WebElement facetGroupHeaderResourceType() {
        return waitForExpectedElement(By.id("co_facetHeaderknowHowResourceTypeSummary"));
    }

    /**
     * By Object representing Resource Type heading for know how facet group
     */
    public By resourceTypeFacetGroupByTitle() {
        return By.id("co_facetHeaderknowHowResourceTypeSummary");
    }

    /**
     * Object representing Practice Area heading for know how facet group
     */
    public WebElement facetGroupHeaderPracticeArea() {
      //  return waitForExpectedElement(By.id("co_facetHeaderknowHowPracticeAreaSummary"));
        return waitForExpectedElement(By.id("co_facetHeaderknowHowAuPracticeAreaSummary"));
    }

    /**
     * Object representing Jurisdiction heading for know how facet group
     */
    public WebElement facetGroupHeaderJurisdiction() {
        return waitForExpectedElement(By.id("co_facetHeaderknowHowJurisdictionSummary"));
    }

    /**
     * This is an object representing the Apply Filters button
     */
    public WebElement applyFiltersButton() {
        return waitForExpectedElement(By.partialLinkText("Apply filters"), 8);
    }

    /**
     * This is an object representing the facet count associated with each facet (any facet on the know how page)
     */
    public WebElement facetCount(String facetname) {
        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetname + "')]/../span[@class='co_facetCount']"), 10);
    }

    /**
     * This is an object for all the listed know how jurisdiction facets
     */
    public List<String> getJurisdictionFacets() {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : waitForExpectedElements(By.xpath("//div[@id='co_narrowResultsBy']/div/h4[contains(text(), 'Jurisdiction')]/../ul/li/label"), 10)) {
                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    /**
     * This is an object representing a facet in an expanded state
     */
    public WebElement expandedKnowHowFacet(String facetname) {
        return waitForExpectedElement(By.xpath("//ul[@class='co_facet_tree']//label[text()='" + facetname + "']/preceding-sibling::a[@class='co_facet_collapse']"));
    }

    /**
     * This is an object representing a facet in an collapsed state
     */
    public WebElement collapsedKnowHowFacet(String facetname) {
        return waitForExpectedElement(By.xpath("//ul[@class='co_facet_tree']//label[text()='" + facetname + "']/preceding-sibling::a[@class=' co_facet_expand']"));
    }

    public List<String> getAllFacets() {

        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : waitForExpectedElements(By.xpath("//div[@id='co_narrowResultsBy']//ul[@class='co_facet_tree']//label"), 10)) {
                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    /**
     * This is an object representing the practice area facets as a group (necessary when checking ordering for example)
     */
    public List<String> getMainPracticeAreaFacets(String facetHeaderName) {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : waitForExpectedElements(By.xpath("//h4[contains(@class,'facet_header')]/self::h4[text()='" + facetHeaderName + "']/../ul/li/label"), 10)) {
                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    /**
     * This is an object representing the child practice area facets as a group (necessary when checking ordering for example)
     */
    public List<String> getChildPracticeAreaFacets(String facetHeaderName) {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : waitForExpectedElements(By.xpath("//div[@id='facet_div_knowHowPracticeAreaSummary']//label[text()='" + facetHeaderName + "']/../div/ul/li/label"), 10)) {
                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    /**
     * This is an object representing the grandchild practice area facets as a group (necessary when checking ordering for example)
     */
    public List<String> getGrandchildPracticeAreaFacets(String facetHeaderName) {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : waitForExpectedElements(By.xpath("//div[@id='facet_div_knowHowPracticeAreaSummary']//label[text()='" + facetHeaderName + "']/../div/ul/li/label"), 10)) {
                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    /**
     * This is an object representing the facet counts as a group (necessary when checking ordering for example)
     */
    public List<Integer> getFacetCounts(String facetHeaderName) {
        List<Integer> list = new ArrayList<Integer>();
        try {
            for (WebElement facetCounts : waitForExpectedElements(By.xpath("//h4[contains(@class,'facet_header')]/self::h4[text()='" + facetHeaderName + "']/../ul/li/span"), 10)) {
                int facetCounts2 = Integer.parseInt(facetCounts.getText().replace(",", ""));
                list.add(facetCounts2);
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    /**
     * This is for "Clear all" link element
     */
    public WebElement clearAllLink() {
        return waitForExpectedElement(By.xpath("//div[@id='co_undoAllSelections']/a"), 10);
    }

    /**
     * This is for "Combined Know How UK" title element
     */
    public WebElement combinedKnowHowUKTitle() {
        return waitForExpectedElement(By.xpath("//h1[@id='co_browsePageLabel' and text()='Combined Know How UK']"));
    }

    /**
     * This is for "Practice Area" facet label element
     */
    public WebElement practiceAreaFacetLabel() {
        return waitForExpectedElement(By.id("co_facetHeaderknowHowPracticeAreaSummary"));
    }

    /**
     * This is the dropdown to select search results per page
     */
    public WebElement resultDropdown() {
        return waitForExpectedElement(By.id("coid_search_pagination_size"), 8);
    }

    /**
     * This is the generic method to verify the any facet under the any group and returns the bolean value accordingly.
     *
     * @param facetGroup (Mention the facet group ex., ResourceType/Jurisdiction/Practice Area)
     * @param facetNames (path to the facet eg., StandardDocumentsAndClauses > Drafting notes)
     * @return boolean
     */
    public boolean isFacetDisplayed(String facetGroup, String... facetNames) {
        return isElementDisplayed(By.xpath(String.format(generateFacetXpath(facetGroup, facetNames).toString(), facetNames[facetNames.length - 1])));
    }

    /**
     * This is the generic method to verify that given facet is not expected to display under the any group and returns the bolean value accordingly.
     *
     * @param facetGroup (Mention the facet group ex., ResourceType/Jurisdiction/Practice Area)
     * @param facetNames (path to the facet eg., StandardDocumentsAndClauses > Drafting notes)
     * @return boolean
     */
    public boolean isFacetNotDisplayed(String facetGroup, String[] facetNames) {
        return !isElementDisplayed(By.xpath(String.format(generateFacetXpath(facetGroup, facetNames).toString(), facetNames[facetNames.length - 1])));
    }

    private StringBuilder generateFacetXpath(String facetGroup, String[] facetNames) {
        StringBuilder xpath = getFacetTypeXpath(facetGroup);
        String tempStr = "/label[text()='";
        try {
            for (int i = 0; i < facetNames.length - 1; i++) {
                WebElement checkbox = waitForExpectedElement(By.xpath(xpath + tempStr + facetNames[i] + "']/../a"), 8);
                if (checkbox.getAttribute("class").equals("co_facet_expand")) {
                    checkbox.click();
                }
                xpath.append("/div/ul/li");
            }
            xpath.append("/label[contains(text(),'%s')]");
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the facet" + facetNames[facetNames.length - 1]);
        }
        return xpath;
    }

    private StringBuilder getFacetTypeXpath(String facetGroup) {
        StringBuilder xpath = new StringBuilder();
        if (facetGroup.equals("Resource Type")) {
            xpath.append(".//div[@id='facet_div_knowHowResourceTypeSummary']/ul/li");
        } else if (facetGroup.equals("Practice Area")) {
            xpath.append(".//div[@id='facet_div_knowHowPracticeAreaSummary']/ul/li");
        } else if (facetGroup.equals("Jurisdiction")) {
            xpath.append(".//div[@id='facet_div_knowHowJurisdictionSummary']/ul/li");
        }
        return xpath;
    }

    /**
     * This is the generic method to verify the any parent facet has child facets or not and returns the bolean value accordingly.
     *
     * @param facetGroup (Mention the facet group ex., ResourceType/Jurisdiction/Practice Area)
     * @param facetNames (path to the parent facet eg., StandardDocumentsAndClauses > Drafting notes)
     * @return boolean
     */
    public boolean isParentHasAtLeastOneChildFacet(String facetGroup, String[] facetNames) {
        return isElementDisplayed(By.xpath(String.format(generateFacetXpath(facetGroup, facetNames).toString() + "/../div/ul", facetNames[facetNames.length - 1])));
    }

    /**
     * This is to verify that no facets are diaplyed on search results page when there is 0 results.
     *
     * @return boolean
     */
    public boolean isNoFacetsAreAvailable() {
        return (!(isAnyResourceTypeFacetsDisplayed() || isAnyPracticeAreaFacetsDisplayed() || isAnyJuriscitionFacetsDisplayed()));
    }

    private boolean isAnyResourceTypeFacetsDisplayed() {
        return isElementDisplayed(By.cssSelector("div#facet_div_knowHowResourceTypeSummary/ul"));
    }

    private boolean isAnyPracticeAreaFacetsDisplayed() {
        return isElementDisplayed(By.cssSelector("div#facet_div_knowHowPracticeAreaSummary/ul"));
    }

    private boolean isAnyJuriscitionFacetsDisplayed() {
        return isElementDisplayed(By.cssSelector("div#facet_div_knowHowJurisdictionSummary/ul"));
    }

    /**
     * This is the first result structure of the search result page
     */
    public List<WebElement> searchFirstResultElementsList() {
        return waitForExpectedElements(By.xpath("//div[@id='co_searchResults_citation_1']//div"));
    }

    /**
     * This is the first result summary of the search result page
     */
    public WebElement searchFirstResultElementsSummary() {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_summary_1']"));
    }

    /**
     * This method is to get the displayed facet names under given Parent Facet name.
     * Whenever Parent Facet name is not present it will return the all parent facets under given facet type group.
     *
     * @param facetType
     * @param parentFacetNames
     * @return List<String>
     */
    public List<String> getFacetNamesUnderFacetType(String facetType, String... parentFacetNames) {
        List<String> list = new ArrayList<String>();
        StringBuilder xpath = new StringBuilder();
        if (StringUtils.isEmpty(facetType)) {
            throw new IllegalArgumentException("FacetType should not be blank or null");
        }
        try {
            xpath = getFacetTypeXpath(facetType);
            if (!StringUtils.isEmpty(parentFacetNames)) {
                for (int i = 0; i < parentFacetNames.length; i++) {
                    WebElement checkbox = waitForExpectedElement(By.xpath(xpath + "/label[text()='" + parentFacetNames[i].trim() + "']/../a"), 8);
                    if (checkbox.getAttribute("class").equals("co_facet_expand")) {
                        checkbox.click();
                    }
                    xpath.append("/label[text()='" + parentFacetNames[parentFacetNames.length - 1].trim() + "']/../div/ul/li");
                }
            }
            xpath.append("/label");
            for (WebElement element : waitForExpectedElements(By.xpath(xpath.toString()), 8)) {
                list.add(element.getText());
            }
            if (!StringUtils.isEmpty(parentFacetNames)) {
                xpath = getFacetTypeXpath(facetType);
                for (int i = 0; i < parentFacetNames.length; i++) {
                    WebElement checkbox = waitForExpectedElement(By.xpath(xpath + "/label[text()='" + parentFacetNames[i].trim() + "']/../a"), 8);
                    if (!checkbox.getAttribute("class").equals("co_facet_expand")) {
                        checkbox.click();
                    }
                }
            }
        } catch (TimeoutException te) {
            LOG.info("Exceeded time to find the Resource Type facets", te);
        }
        return list;
    }

    /**
     * This method does the waiting until results to be loaded on Search results page.
     */
    public void waitForSearchResults() {
        try {
            try {
                waitForElementVisible(By.cssSelector(".co_search_ajaxLoading"));
                try {
                    waitForElementInvisible(By.cssSelector(".co_search_ajaxLoading"));
                } catch (TimeoutException | NoSuchElementException te) {
                    LOG.info("Page loading issue...." + te.getMessage());
                }
            } catch (TimeoutException | NoSuchElementException te) {
                LOG.info("context", te);
            }
            //waitForExpectedElements(By.cssSelector("h3 a"));
        } catch (PageOperationException | TimeoutException te) {
            throw new PageOperationException("Exceeded time to locate the results on search results page" + te.getMessage());
        }
    }

    public void selectFacetCheckBox(String facetGroup, String... facetNames) {
        getCheckBox(facetGroup, facetNames).click();
    }

    private WebElement getCheckBox(String facetGroup, String[] facetNames) {
        try {
            String xpath = "//../input";
            return waitForExpectedElement(By.xpath(String.format(generateFacetXpath(facetGroup, facetNames).toString(), facetNames[facetNames.length - 1]) + xpath), 8);
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the facet count for : ");
        }
    }

    /**
     * This is the generic method to verify the any facet is selected under the any group and returns the bolean value accordingly.
     *
     * @param facetGroup (Mention the facet group ex., ResourceType/Jurisdiction/Practice Area)
     * @param facetNames (path to the facet eg., StandardDocumentsAndClauses > Drafting notes)
     * @return boolean
     */
    public boolean isFacetSelected(String facetGroup, String... facetNames) {
        try {
            return getCheckBox(facetGroup, facetNames).isSelected();
        } catch (PageOperationException p) {
            LOG.info("context", p);
            return false;
        }
    }

    /**
     * This method verifies the more option is avialable or not and returns the boolean accordingly.
     *
     * @return boolean
     */
    public boolean isMoreOptionAvailableForKnowHowJurisdiction() {
        return isElementDisplayed(By.linkText("More Jurisdictions"));
    }

    /**
     * This method clicks on the more option present under the given facet group name.
     */
    public void clickMoreOptionOnKnowHowJurisdiction() {
        try {
            waitForExpectedElement(By.linkText("More Jurisdictions"), 8).click();
            waitForElementVisible(By.id("co_facet_knowHowJurisdictionSummary_popup"));
        } catch (TimeoutException se) {
            LOG.info("context", se);
            throw new PageOperationException("Exceeded time to find the More popup.");
        }
    }

    /**
     * This is the method to enable the Apply Filters button by clicking on Select Multiple Filters button on results page.
     */
    public void clickOnSelectMultipleFilters() {
        try {
            WebElement button = waitForExpectedElement(By.linkText("Select multiple filters"), 2);
            button.click();
        } catch (Exception e) {
            LOG.info("context", e);
        }
    }

    public boolean isSelectMultipleFiltersPresent() {
        return isElementDisplayed(By.xpath("//div[@id='co_multifacet_selector_1']/a[contains(@class, 'co_multifacet_select_multiple')]"));
    }

    /**
     * This is the object representing the Select multiple filters button
     *
     * @deprecated use selectMultipleFilters()
     */
    @Deprecated
    public WebElement selectMultipleFiltersButton() {
        return waitForExpectedElement(By.linkText("Select multiple filters"), 8);
    }

    public WebElement selectMultipleFilters() {
        return waitForExpectedElement(By.xpath("//div[@id='co_multifacet_selector_1']/a[contains(@class, 'co_multifacet_select_multiple')]"));
    }

    /**
     * This is the title of the know how search result in position X
     */
    public WebElement knowHowSearchResultTitle(String result) {
        return waitForExpectedElement(By.id("cobalt_result_knowhow_title" + result), 8);
    }

    /**
     * This method is used to click on ResultItem link present on results page based on given resultIndex.
     *
     * @param resultIndex
     */
    public void clickOnResultItem(int resultIndex) {
        waitForExpectedElement(By.id("cobalt_result_knowhow_title" + resultIndex), 8).click();
    }

    /**
     * This method returns the displayed search suggestions as list of strings.
     *
     * @return List<String>
     */
    public List<String> getSearchSuggestions() {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector("#searchBoxIndexSpan li"), 8)) {
                list.add(element.getText());
            }
            return list;
        } catch (PageOperationException | TimeoutException poe) {
            LOG.info("context", poe);
            return Collections.emptyList();
        }
    }

    public void useKeyBoard(List<Keys> keys, WebElement element) {
        for (Keys key : keys) {
            element.sendKeys(key);
        }
    }

    /**
     * Page object for the date on the first result
     */


    public WebElement date() {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_1']//div/strong/span"));
    }

    public List<WebElement> listOfDate() {
        return waitForExpectedElements(By.xpath("//div[@id='co_searchResults_citation_1']//div/strong/span"));
    }

    public boolean isFacetCountPresent(String count) {
        try {
            for (WebElement element : waitForExpectedElements(By.xpath("//div[@id='co_narrowResultsBy']//span[@class='co_facetCount']"), 8)) {
                if (element.getText().equals(count)) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.info("Facet count is not present", e);
        }
        return false;
    }

    /**
     * Page per dropdown link
     */
    public WebElement searchPerPageDrodownLink() {
        return waitForExpectedElement(By.xpath("//a[@id='selectedDisplayItemCount']"));
    }

    /**
     * Page per dropdown list items (20,50,100)
     */
    public List<WebElement> searchPerPageDrodownListItemLinks() {
        return waitForExpectedElements(By.xpath("//div[@id='displayItemCount']//ul[@class='co_dropDownMenuList']//li//a"));
    }

    /**
     * Page per pagination list items (1,2,3...> >>)
     */
    public List<WebElement> searchPaginationItemLinks() {
        return waitForExpectedElements(By.xpath("//ul[@id='co_navigationFooter']//li//a"));
    }

    /**
     * Search Result page heading
     */
    public WebElement searchResultHeading() {
        return waitForExpectedElement(By.xpath("//div[@id='co_subHeader']//h1"));
    }

    /**
     * Search Result page heading
     */
    public WebElement searchResultHeadingWithString(String headingText) {
        return waitForExpectedElement(By.xpath("//div[@id='co_subHeader']//h1[contains(.,'" + headingText + "')]"));
    }

    /**
     * List of Results after first 12 results
     */
    public List<WebElement> searchResultsItemsList() {
        return waitForExpectedElements(By.xpath("//ol[@class='co_searchResult_list']//li[contains(@id,'cobalt_search_results_know')]"));
    }

    /**
     * Results count like "1-20"
     */
    public WebElement searchResultCountLabel() {
        return waitForExpectedElement(By.xpath("//ol[@id='co_navigationPages']//span"));
    }

    /**
     * Results count like "1-20"
     */
    public By searchResultByCountLabel() {
        return By.id("selectedDisplayItemCount");
    }

    /**
     * Searching wrong result gives you the no result following paragraph
     */
    public WebElement searchNoResultParagraph() {
        return waitForExpectedElement(By.id("cobalt_search_no_results"));
    }

    /**
     * Whole filter facet element
     */
    public WebElement searchFilterFacet() {
        return waitForExpectedElement(By.id("co_website_searchFacets"));
    }

    public boolean isSearchFilterFacetDisplayed() {
        return isElementDisplayed(By.id("co_website_searchFacets"));
    }

    /**
     * This method is to find the notes added icon is displayed or not based on the given result positions
     *
     * @param position
     * @return boolean
     */
    public boolean isNotesAddedLinkPresent(String position) {
        return isElementDisplayed(By.cssSelector("#cobalt_search_results_knowHowPlc" + position + " .co_document_icon_notes>img"));
    }

    /**
     * This is for "Clear all" link element for history page
     */
    public WebElement historyPageClearAllFiltersLink() {
        return waitForExpectedElement(By.xpath("//div[@id='co_undoAllSelectionsHistory']/a[@class='co_btnGray co_btnBack']"));
    }

    /**
     * This is for "Cliend ID " facets element for history page
     */
    public WebElement clientIDFacetCheckbox(String facetName) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_Client_IDs']//label[contains(.,'" + facetName + "')]/../input"));
    }

    /**
     * This is for "Cliend ID " facets element for history page
     */
    public WebElement clientIDByFacetCheckbox(String facetName) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_Client_IDs']//label[contains(.,'" + facetName + "')]/../input"));
    }

    /**
     * This is for "Event" facets element for history page
     */
    public WebElement eventFacetCheckbox(String facetName) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_Event']//label[contains(.,'" + facetName + "')]/../input"));
    }

    /**
     * This is for "Cliend ID " facets count element for history page
     */
    public WebElement clientIDFacetCount(String facetName) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_Client_IDs']//label[contains(.,'" + facetName + "')]/../span"));
    }

    /**
     * This is for "Event" facets element for history page
     */
    public WebElement eventFacetCount(String facetName) {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_Event']//label[contains(.,'" + facetName + "')]/../span"));
    }

    /**
     * This is for List of elements of clientID column history page
     */
    public List<WebElement> clientIDCellList() {
        return waitForExpectedElements(By.xpath("//table[@class='co_detailsTable']//tr//td[@class='co_detailsTable_clientId']//span"));
    }

    /**
     * This is for List of elements of Event column history page
     */
    public List<WebElement> eventCellList() {
        return waitForExpectedElements(By.xpath("//table[@class='co_detailsTable']//tr//td[@class='co_detailsTable_event']//span"));
    }

    /**
     * Get results count for the search after waiting
     *
     * @return Results count
     */
    public WebElement waitKnowHowSearchResultCount() {
        // knowHowSearchResultCount() method is inapplicable here because for current method need more waiting,
        // when element will be visible, without any retries, just wait
        return waitForElementVisible(By.xpath("//div[@id='coid_website_searchAvailableFacets']//span[@class='co_search_titleCount']"));
    }

    /**
     * Get check-box element of given facet name
     *
     * @param facetName Facet name
     * @return Check-box element of given facet name
     */
    public WebElement getFacetCheckbox(String facetName) {
        return waitForElementVisible(By.xpath("//div[@id='co_narrowResultsBy']//li[contains(., '" + facetName + "')]/input[@type='checkbox']"));
    }

    /**
     * Indicate if "Cancel" button exists for facets
     *
     * @return True - if check passed, otherwise - false.
     */
    public boolean isCancelButtonExists() {
        return isExists(By.xpath("//a[contains(@onclick, 'cancelMultiFacetMode') and not(contains(@class, 'hideState'))]"));
    }

    /**
     * Get "Cancel" button for facets
     *
     * @return WebElement with "Cancel" button
     */
    public WebElement getCancelButton() {
       return waitForExpectedElement(By.id("co_multifacet_selector_1_cancelFacetFilter"));
    }

    /**
     * Get check-box element of given facet name
     *
     * @param snippetRowNumber to get specific row number
     * @return
     */
    public List<WebElement> snippetSearchTermList(int snippetRowNumber) {
        return waitForElementsVisible(By.xpath("//a[@id='cobalt_result_knowhow_snippet_" + snippetRowNumber + "_1']//span[@class='co_searchTerm']"));
    }

    /**
     * This method returns the parsed int value as result of search results count.
     *
     * @return int
     */
    public int getSearchResultsCount() {
        String totalSearchCount = knowHowSearchResultCount().getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(totalSearchCount);
    }


    public void clickContinueBtnOnWarningPopUpIfPresent() {
        if (isExists(By.xpath(CONTINUE_BTN_ON_TIMEOUT_WARNING_POP_UP_XPATH))) {
            waitForExpectedElement(By.xpath(CONTINUE_BTN_ON_TIMEOUT_WARNING_POP_UP_XPATH)).click();
        }
    }

    public By moreJuridictionByLink() {
        return By.id("clientanchor");
    }

    /**
     * facet group heading on Search result page for PLAU
     *
     * @return
     */
    public WebElement facetGroupHeaderPLAUJurisdiction() {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_knowHowJurisdictionSummary' or @id='facet_div_knowHowAuJurisdictionSummary']//h4"));
    }

    /**
     * This method is to verify the resource type facets is displayed ot not.
     *
     * @return boolean
     */
    public boolean isFacetGroupHeaderResourceTypeDisplayed() {
        return commonMethods.isElementDisplayed(waitForElementPresent(resourceTypeFacetGroupByTitle()));
    }

    /**
     * Whole checkbox for filter facet element
     */
    public List<WebElement> searchFilterFacetCheckbox() {
        return waitForExpectedElements(By.xpath("//div[@id='co_website_searchFacets']//input[@type='checkbox']"));
    }

    /**
     * Get all facets as WebElement
     *
     * @return List<WebElement> with all facets
     */
    public List<WebElement> getAllFacetsElements() {
        return waitForExpectedElements(By.xpath("//div[@id='co_narrowResultsBy']//ul[@class='co_facet_tree']/li"));
    }

    /**
     * Return all search results on the page
     *
     * @return List<WebElement> with search results on the page
     */
    public List<WebElement> searchResultsTitles() {
        return waitForExpectedElements(By.xpath("//a[contains(@id,'cobalt_result_knowhow_title')]"));
    }

    /**
     * Get name of facet from full-facet WebElement
     *
     * @return String of facet name
     */
    public String getFacetNameTextValue(WebElement facet) {
        return facet.findElement(By.xpath(".//label")).getText();
    }

    /**
     * Get count of facet from full-facet WebElement
     *
     * @return String of facet count
     */
    public String getFacetCountTextValue(WebElement facet) {
        return facet.findElement(By.xpath(".//span")).getText();
    }

    /**
     * Get Map for all facet on the page with their name and count
     *
     * @return Map<String, String>, where first String is facet Name, second String is facet count
     */
    public Map<String, String> getMapWIthFacetsNameAndCount() {
        Map<String, String> mapForFacetsNamesAndCounts = new HashMap<String, String>();

        for (WebElement facet : this.getAllFacetsElements()) {
            mapForFacetsNamesAndCounts.put(this.getFacetNameTextValue(facet)
                    , this.getFacetCountTextValue(facet));
        }
        return mapForFacetsNamesAndCounts;
    }

    /**
     * Get List for all search results
     *
     * @return Mist<String>, where  String is title of search result on the page
     */
    public List<String> getListWithSearchResultsForFirstPage() {
        List<String> listForSearchResultsTitles = new ArrayList<String>();

        for (WebElement searchResult : this.searchResultsTitles()) {
            listForSearchResultsTitles.add(searchResult.getText());
        }
        return listForSearchResultsTitles;
    }

}