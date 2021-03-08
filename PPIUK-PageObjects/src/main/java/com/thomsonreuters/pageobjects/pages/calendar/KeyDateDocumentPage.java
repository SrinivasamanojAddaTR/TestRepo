package com.thomsonreuters.pageobjects.pages.calendar;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.DocumentDisplayAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;


public class KeyDateDocumentPage extends DocumentDisplayAbstractPage {

    CommonMethods commonMethods = new CommonMethods();

    public WebElement keyDateDocMetaData() {
        return findElement(By.id("co_docContentMetaInfo"));
    }

    public WebElement viewResourceHistoryLink() {
        return findElement(By.id("co_docContentMetaInfo")).findElement(By.linkText("View Resource History"));
    }

    //TODO: Change methodname
    private List<WebElement> jurisdictions() {
        return findElements(By.cssSelector("#co_docContentMetaInfoJurisdictions ul li"));
    }


    public WebElement jurisdictionViewAllLink() {
        return findElement(By.id("co_docContentMetaInfoJurisdictionsAllButton"));
    }

    public WebElement relatedContentLink() {
        return waitForExpectedElement(By.id("co_docContentMetaInfo")).findElement(By.linkText("Related Content"));
    }

    public boolean isViewAllLinkDisplayed() {
        return findElements(By.id("co_docContentMetaInfoJurisdictionsAllButton")).size() > 0;
    }

    public boolean isDocEventDatePresent() {
        return isExists(By.id("co_eventDate"));
    }

    public WebElement metaDataDateIcon() {
        return waitForElementVisible(By.id("co_eventDateIconDay"));
    }

    public WebElement metaDataEventTypeHeading() {
        return waitForElementVisible(By.xpath("//div[@class='co_documentType']//b[normalize-space(.)='Event Type']"));
    }
    public WebElement metaDataEventTypeValue(String eventType) {
        return waitForElementVisible(By.xpath("//div[@class='co_documentType']//span[normalize-space(.)='"+eventType+"']"));
    }
    public WebElement metaDataResourceTypeHeading() {
        return waitForElementVisible(By.xpath("//div[@class='co_documentType']//b[normalize-space(.)='Resource Type']"));
    }

    public WebElement metaDataResourceTypeValue(String resourceType) {
        return waitForElementVisible(By.xpath("//div[@class='co_documentType']//span[normalize-space(.)='"+resourceType+"']"));
    }

    public WebElement metaDataJurisdictionHeading() {
        return waitForElementVisible(By.xpath("//div[@id='co_docContentMetaInfoJurisdictions']//b[normalize-space(.)='Jurisdiction']"));
    }
    public List<WebElement> metaDataJurisdictionValueList() {
        return waitForElementsVisible(By.xpath("//div[@id='co_docContentMetaInfoJurisdictions']//ul//li"));
    }

    public WebElement keyDateDocumentBody() {
        return waitForElementVisible(By.xpath("//div[@id='co_docContentBody']//div[@class='co_paragraph']"));
    }
    public List<WebElement> resourceLinkList() {
        return waitForElementsVisible(By.xpath("//div[@class='co_resourceLinks']//li/a[not(contains(@target,'_blank'))]"));
    }

    public WebElement keyDateDocumentTitle() {
        return waitForElementVisible(By.xpath("//h1[contains(@class,'co_title')]"));
    }

    public WebElement keyDateCalendarFacet(){
        return waitForExpectedElement(By.id("calendarFacet"));
    }

    public WebElement keyDateRangeDropDownList(){
        return waitForExpectedElement(By.id("calendarFacetRange"));
    }

    public WebElement keyDateDropDownListSelectedOption(){
        return waitForExpectedElement(By.xpath("//button[@class='co_dropDownButton']"));
    }

    public boolean isDefaultValueInDateDropDownExist(String defaultValue){
        return isExists(By.xpath("//div[@class='co_dropDownMenuContent' and contains(.,'"+defaultValue+"')]"));
    }

    public List<WebElement> keyDateDropDownOptionsList(){
        return waitForExpectedElements(By.xpath("//div[@class='co_dropDownMenuContent' and contains(.,'Future')]/ul/li/a"));
    }

    public WebElement keyDateDropDownOption(String option){
        return waitForExpectedElement(By.xpath("//div[@class='co_dropDownMenuContent' and contains(.,'Future')]//a[contains(.,'" + option + "')]"));
    }

    public WebElement keyDateCalendarDay(String day){
        return waitForExpectedElement(By.xpath(String.format("//div[@class='co_calendar_gridContainer']//a[normalize-space(.)='%s']", day)));
    }

    public List<WebElement> keyDatePracticeAreaFacets(){
        return waitForExpectedElements(By.id("co_facetSubHeaderknowHowPracticeAreaSummary"));
    }

    public WebElement keyDateCalendarInterval(){
        return waitForExpectedElement(By.className("calenderDateTitle"));
    }

    public WebElement selectedMonth(){
        return waitForExpectedElement(By.xpath("//*[@class='co_calender_monthSelector']/option[@selected]"));
    }

    public WebElement detailsSliderSelector(){
        return waitForExpectedElement(By.xpath("//div[@id='detailSliderSelectorPLC']/div[@class='co_dropDownButton']/a"));
    }

    public List<WebElement> detailsResourceLinksList(){
        return waitForExpectedElements(By.xpath("//div[@class='co_search_detailLevel_3']"));
    }

    public WebElement dateMonthIconForSingleDateEvent(String name){
        return waitForExpectedElement(By.xpath("//div[contains(@class,'co_searchContent') and contains(.,'" + name + "')]//div[@class='co_searchResults_dateIconMonth']"));
    }

    public boolean hasEventIcon(String name){
        return isElementDisplayed(By.xpath("//div[contains(@class,'co_searchResults_dateIcon')]"));
    }

    public List<WebElement> getEventNames(){
        return waitForExpectedElements(By.xpath("//li[contains(@id, 'cobalt_search_results_keyDate')]/div[@class='co_searchContent']//a[contains(@id, 'cobalt_result_keyDateUK')]/span"));
    }

    public List<WebElement> getSingleDayEventNames(){
        return waitForExpectedElements(By.xpath("//li[contains(@id,'cobalt_search_results_keyDate') and .//div[@class='co_searchResults_dateIcon']]//a[contains(@id, 'cobalt_result_keyDateUK')]/span"));
    }

    public WebElement dateIconForMultiDateEvent(String name){
        return waitForExpectedElement(
                By.xpath("//div[contains(@class,'co_searchContent') and contains(.,'" + name + "')]//div[contains(@class,'co_searchResults_noFixedDateIcon')]"));
    }

    public List<WebElement> singleDaySearchResultItems(){
        return waitForExpectedElements(By.xpath("//li[contains(@id,'cobalt_search_results_keyDateUK') and .//div[@class='co_searchResults_dateIcon']]"));
    }

    public List<WebElement> getEventsList(){
        return waitForExpectedElements(By.xpath("//*[@class='co_searchResult_list']/li//*[contains(@id,'cobalt_result_keyDateUK_title')]"));
    }

    public boolean isAddToOutlookLinkPresentForEachDoc(){
        for (WebElement item: singleDaySearchResultItems()) {
            if(!commonMethods.isElementDisplayed(item.findElement(By.xpath(".//form[@class='co_addToOutlookForm']")))){
                return false;
            }
        }
        return true;
    }

    public WebElement addToOutlookLink(String eventName){
        return waitForExpectedElement(By.xpath("//form[@class='co_addToOutlookForm' and input[@value='"+ eventName +"']]"));
    }

    public WebElement addToOutlookGuidParameter(String eventName){
        return waitForElementPresent(
                By.xpath("//*[contains(., '"+eventName+"')]//preceding-sibling::*[@name='documentGuid']"));
    }

    public WebElement addToOutlookStartDateParameter(String eventName){
        return waitForElementPresent(
                By.xpath("//*[contains(., '"+eventName+"')]//preceding-sibling::*[@name='startDate']"));
    }

    public WebElement addToOutlookEndDateParameter(String eventName){
        return waitForElementPresent(
                By.xpath("//*[contains(., '"+eventName+"')]//preceding-sibling::*[@name='endDate']"));
    }

    public boolean isFacetPresent(String name){
        return commonMethods.isElementDisplayed(facetInput(name));
    }

    public WebElement facetInput(String name){
        return waitForExpectedElement(By.xpath("//div[contains(@id,'facet_div')]//li[./label[.='" + name + "']]/input"));
    }

    public List<WebElement> getEventParameter(String name){
        return waitForExpectedElements(By.xpath("//div[@class='co_search_detailLevel_1' and ./strong[.='" + name + ":']]"));
    }
}
