package com.thomsonreuters.pageobjects.pages.legal_updates;

import com.thomsonreuters.pageobjects.utils.legal_updates.CalendarAndDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LegalUpdatesResultsPage extends LegalUpdatesBasePage {

    public final static String TWENTY_PER_PAGE_SELECT_OPTION = "20 per page";
    public final static String FIFTY_PER_PAGE_SELECT_OPTION = "50 per page";
    public final static String ONE_HUNDRED_PER_PAGE_SELECT_OPTION = "100 per page";
    private final String resultsHeaderLocator = "//div[@id='co_search_headerToolbar']";
    private final String resultsFooterLocator = "//div[@id='co_search_footerToolbar']";

    private List<String> legalUpdatesStatuses;

    private List<Date> publishingDates;

    private List<String> first5LUtitles;

    private HashMap<String, By> paginationArrowMap;

    public LegalUpdatesResultsPage() {
    	initPaginationArrowsMap();
    }

    private void initPaginationArrowsMap() {
        paginationArrowMap = new HashMap<String, By>();
        paginationArrowMap.put("<<", By.id("co_search_footer_pagination_first"));
        paginationArrowMap.put("<", By.id("co_search_footer_pagination_prev"));
        paginationArrowMap.put(">>", By.id("co_search_footer_pagination_last"));
        paginationArrowMap.put(">", By.id("co_search_footer_pagination_next"));
    }

    public WebElement emailIcon() {
        return waitForExpectedElement(By.id("deliveryLinkRow1Email"));
    }

    public WebElement printIcon() {
        return findElement(By.id("deliveryLinkRow1Print"));
    }

    public WebElement downloadIcon() {
        return findElement(By.id("deliveryLinkRow1Download"));
    }

    public WebElement resultsPageHeader() {
        return waitForExpectedElement(By.xpath(resultsHeaderLocator));
    }

    public WebElement findElementOnResultsPageHeader(String elementXpath) {
        return waitForExpectedElement(By.xpath(resultsHeaderLocator + elementXpath));
    }

    public WebElement resultsPageFooter() {
        return waitForExpectedElement(By.xpath(resultsFooterLocator));
    }

    public WebElement findElementOnResultsPageFooter(String elementXpath) {
        return waitForExpectedElement(By.xpath(resultsFooterLocator + elementXpath));
    }

    public WebElement subscribeLink() {
        return waitForExpectedElement(By.xpath("//a[text()='Subscription']"));
    }

    public WebElement rssLink() {
        return waitForExpectedElement(By.linkText("RSS"));
    }

    public WebElement saveForLaterLink() {
        return waitForExpectedElement(By.xpath("//div[@id='saveToFolder']//a[text()='Save for Later']"));
    }

    public WebElement deliveryMethodLink() {
        return waitForExpectedElement(By.xpath("//a[text()='Deliver']"));
    }

    public boolean isDeliveryMethodLinkPresent() {
    	return isElementDisplayed(By.xpath("//a[text()='Deliver']"));
    }
    
    public WebElement resultsPerPageLink() {
        return waitForExpectedElement(By.id("selectedDisplayItemCount"));
    }

    public void selectShownResultsNumberPerPage(String numberOfResultsPerPage) {
        super.selectDropDownByVisibleText(findElement(By.xpath("//select[@id='coid_search_pagination_size']")), numberOfResultsPerPage);
    }

    public int getUpdatesResultCount() {
        return waitForExpectedElements(By.xpath("//span[@class='co_searchCount']")).size();
    }

    public WebElement legalUpdatesResultsContentList() {
        return waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']"));
    }

    public WebElement specificLegalUpdateCheckBox(int legalUpdateNumber) {
        return waitForExpectedElement(By.xpath("//input[@id='cobalt_search_knowhow_checkbox_" + legalUpdateNumber + "'" + " and @type='checkbox']"));
    }

    public WebElement specificLegalUpdateTitle(int legalUpdateNumber) {
        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_knowhow_title" + legalUpdateNumber + "']"));
    }

    public WebElement successfulSaveForLaterNotificationMessage() {
        return waitForExpectedElement(By.xpath("//div[@class='co_foldering_popupMessageContainer']//div[@class='co_infoBox_message']"));
    }

    public WebElement saveForLaterWidget() {
        return waitForExpectedElement(By.xpath("//div[@class='co_overlayBox_container co_folderAction co_saveTo_folderAction']"));
    }

    public WebElement currentPageNumberLabel() {
        return waitForExpectedElement(By.id("co_search_footer_pagination_current"));
    }

    public WebElement specifiedPageNumberLink(String pageNumber) {
        return waitForExpectedElement(By.id("co_search_footer_pagination_page" + pageNumber));
    }

    public WebElement pageNumberLinksContainer() {
        return waitForExpectedElement(By.id("co_navigationFooter"));
    }

    public WebElement nextPageButton() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_next' and text()='Next Page']"));
    }

    public WebElement lastPageButton() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_last' and text()='Last Page']"));
    }

    public WebElement previousPageButton() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_prev' and text()='Previous Page']"));
    }

    public WebElement firstPageButton() {
        return waitForExpectedElement(By.xpath("//a[@id='co_search_footer_pagination_first' and text()='First Page']"));
    }

    public WebElement getPaginationArrowLocatorByArrow(String paginationArrow) {
        return waitForElementPresent(paginationArrowMap.get(paginationArrow));
    }

    public WebElement deliverHederLink() {
        return waitForExpectedElement(By.xpath("//div[@id='co_search_headerToolbar']//a[text()='Deliver']"));
    }

    public WebElement deliverFooterLink() {
        return waitForExpectedElement(By.xpath("//div[@id='co_search_footerToolbar']//a[text()='Deliver']"));
    }

    public WebElement deliverWidgetHeaderMenu() {
        return waitForExpectedElement(By.xpath("//div[@id='deliveryWidgetMenu1' and @class='co_dropDownMenuContent']"));
    }

    public WebElement deliverWidgetFooterMenu() {
        return waitForExpectedElement(By.xpath("//div[@id='deliveryWidgetMenu2' and @class='co_dropDownMenuContent']"));
    }

    public WebElement deliverHeaderWidgetEmailLink() {
        return waitForExpectedElement(By.xpath("//div[@id='deliveryWidgetMenu1']//a[@title='Email' and @class='co_deliveryLinkOptions']"));
    }

    public WebElement deliverHeaderWidgetPrintLink() {
        return waitForExpectedElement(By.xpath("//div[@id='deliveryWidgetMenu1']//descendant::a[@title='Print' and @class='co_deliveryLinkOptions']"));
    }

    public WebElement deliverHeaderWidgetDownloadLink() {
        return waitForExpectedElement(By.xpath("//div[@id='deliveryWidgetMenu1']//descendant::a[@title='Download' and @class='co_deliveryLinkOptions']"));
    }

    public WebElement deliverFooterWidgetEmailLink() {
        return waitForExpectedElement(By.xpath("//div[@id='deliveryWidgetMenu2']//a[@title='Email' and @class='co_deliveryLinkOptions']"));
    }

    public WebElement deliverFooterWidgetPrintLink() {
        return waitForExpectedElement(By.xpath("//div[@id='deliveryWidgetMenu2']//descendant::a[@title='Print' and @class='co_deliveryLinkOptions']"));
    }

    public WebElement deliverFooterWidgetDownloadLink() {
        return waitForExpectedElement(By.xpath("//div[@id='deliveryWidgetMenu2']//descendant::a[@title='Download' and @class='co_deliveryLinkOptions']"));
    }

    public WebElement downloadButton1() {
        return waitForExpectedElement(By.id("co_deliveryDownloadButton"));
    }

    public WebElement downloadButton2() {
        return waitForExpectedElement(By.id("coid_deliveryWaitMessage_downloadButton"));
    }

    public WebElement resourceTypeFilter() {
        return waitForExpectedElement(By.xpath("//div[@id='facet_div_documentType']//h4[@id='co_facetHeaderdocumentType']"));
    }
    
    public WebElement unitedKingdomLegalUpdatesBreadCrumbLink() {
        return waitForExpectedElement(By.linkText("United Kingdom: Legal Updates"));
    }

    public WebElement moreDetailBox() {
        return waitForExpectedElement(By.xpath("//div[@class='co_searchResults_summary co_search_detailLevel_2']"),10);
    }

    public WebElement resultsList() {
        return waitForExpectedElement(By.id("cobalt_search_knowHowPlc_results"),10);
    }

    public String getfacetSubTitleText() {
        return waitForExpectedElement(By.xpath("//span[@class='co_facet_subtitle']")).getText();
    }

    public WebElement childTopicsFacets() {
        return waitForExpectedElement(By.id("facet_div_knowHowPracticeAreaSummary"));
    }

    public List<WebElement> allPAFacetsChildCheckBoxes() {
        return waitForExpectedElements(By.xpath("//div[@id='facet_div_knowHowPracticeAreaSummary']//input[@type='checkbox']"));
    }

    public List<WebElement> getAllUpdatesTitles() {
        return waitForExpectedElements(By.xpath("//a[contains(@id,'cobalt_result_knowhow_title')]"));
    }
    
    public WebElement sortDropDown() {
    	return waitForExpectedElement(By.id("co_search_sortOptions"));
    }

    public List<String> getFirstLU5Titles() {
        first5LUtitles = new ArrayList<String>();
        List<WebElement> allTitles = getAllUpdatesTitles();
        for (int i = 0; i < 5; i++) {
            LOG.info("Adding LU Title from results page: " + allTitles.get(i).getText());
            first5LUtitles.add(allTitles.get(i).getText().trim());
        }
        return first5LUtitles;
    }
    
    public boolean isSortDropDownDisplayed() {
    	return isElementDisplayed(By.id("co_search_sortOptions"));
    }
    

    public boolean isChildTopicsFacetsDisplayed() {
    	return isElementDisplayed(By.id("facet_div_knowHowPracticeAreaSummary"));
    }
    
    
    public boolean isResultsListDisplayed() {
    	return isElementDisplayed(By.id("cobalt_search_knowHowPlc_results"));
    }

    public List<String> legalUpdatesStatuses() {
        legalUpdatesStatuses = new ArrayList<String>();
        for (WebElement el : waitForExpectedElements(By.xpath("//span[@class='co_greenStatus']"))) {
            legalUpdatesStatuses.add(el.getText());
        }
        return legalUpdatesStatuses;
    }

    public List<Date> getPublishingDatesFromStatuses(List<String> statuses) {
        publishingDates = new ArrayList<Date>();
        Pattern p = Pattern.compile("\\d{2}\\s\\w+\\s\\d{4}");
        for (String status : statuses) {
            Matcher m = p.matcher(status);
            if (m.find()) {
                try {
                    publishingDates.add(CalendarAndDate.convertPublishedDateStringInstanceToDateInstanceFromLUPage(m.group()));
                } catch (ParseException e) {
                    LOG.info("context", e);
                }
            }
        }
        return publishingDates;
    }
    
    public boolean isResourceTypeFilterDisplayed() {
    	return isElementDisplayed(By.xpath("//div[@id='facet_div_documentType']//h4[@id='co_facetHeaderdocumentType']"));
    }
    
    public boolean ismoreDetailBoxDisplayed() {
    	return isElementDisplayed(By.xpath("//div[@class='co_searchResults_summary co_search_detailLevel_2']"));
    }

    public boolean isPaginationArrowDisplayed(String paginationArrow) {
    	return isElementDisplayed(paginationArrowMap.get(paginationArrow));
    }

}
