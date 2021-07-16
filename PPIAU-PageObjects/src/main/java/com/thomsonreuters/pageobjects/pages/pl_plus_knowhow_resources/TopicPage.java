package com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.*;

public class TopicPage extends AbstractPage {

    private static final By KNOW_HOW_TITLE = By.xpath("//a[contains(@id,'cobalt_result_knowhow_title')]");

    public Map<String, String> getEditorPicksAsMap() {
        waitForExpectedElement(By.cssSelector("#coid_website_browseTopColumn"));
        List<WebElement> editorPicks = waitForExpectedElements(By.cssSelector("#coid_website_browseTopColumn div[id^=ContentBlock]"));
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < editorPicks.size(); i++) {
            String resourceLink = waitForExpectedElement(By.cssSelector("#coid_website_browseTopColumn div#ContentBlock" + i + " h3>a")).getText().trim();
            String metadata = waitForExpectedElement(By.cssSelector("#coid_website_browseTopColumn div#ContentBlock" + i + "  h4")).getText().trim();
            map.put(resourceLink, metadata);
        }
        return map;
    }

    public List<String> getTopicPageFacetsAsList() {
        List<WebElement> facetsList = findElements(By.cssSelector("#ukplc_topic_facet_links li a"));
        List<String> facetNameLinkedList = new LinkedList<>();
        for (int i = 0; i < facetsList.size(); i++) {
            String facet = facetsList.get(i).getText().trim();
            String facetName = facet.split("\n")[0];
            facetNameLinkedList.add(facetName);
        }
        return facetNameLinkedList;
    }

    public List<String> getResourcesList(String resourceType) {
        List<String> list = new ArrayList<>();
        int titleNumber = 0;
        String idTitle = "";
        List<WebElement> resourceList = waitForExpectedElements(By.xpath("//div[@id='cobalt_search_knowHowTopicPlc_" + resourceType + "']//ol/li"));
        for (WebElement result : resourceList) {
            idTitle = result.getAttribute("id");
            titleNumber = Integer.parseInt(idTitle.substring(idTitle.length() - 1));
            LOG.info(result.findElement(By.id("cobalt_result_knowhow_title" + titleNumber)).getText());
            list.add(result.findElement(By.id("cobalt_result_knowhow_title" + titleNumber)).getText());
        }
        return list;
    }

    public List<String> optionalBlockTitle() {

        List<WebElement> blocks = findElements(By.cssSelector("#coid_website_browseRightColumn .plplus_topic_container"));
        List<String> blockNames = new ArrayList<>();

        for (WebElement element : blocks) {
            if (!element.getAttribute("class").contains("co_hideState")) {
                String blockVisible = element.findElement(By.cssSelector(".co_genericBoxHeader")).getText().trim();
                blockNames.add(blockVisible);
            }
        }
        return blockNames;
    }

    public WebElement pageNumber(int pageNum) {
        return findElement(By.id("co_search_footer_pagination_page" + pageNum));
    }

    public String currentPageSelected() {
        WebElement currentSelectedPage = waitForExpectedElement(By.id("co_search_footer_pagination_current"));
        return currentSelectedPage.getText();
    }

    public void selectEditorsPickResourceByTitle(String title) {
        WebElement element = waitForExpectedElement(By.linkText(title));
        String idValue = element.getAttribute("id");
        String index = idValue.substring(idValue.lastIndexOf('_'));
        findElement(By.id("cobalt_artifact_delivery_checkbox_NaN" + index)).click();
    }

    public void selectTopicPageResourceByTitle(String title) {
        waitForExpectedElement(By.xpath("//li[contains(@id,'cobalt_search_results_knowHowTopicPlc') and contains(.,'" + title + "')]//input")).click();
    }

    public WebElement clickResourceLinkOnEditorsPick(String resourceLink) {
        return waitForExpectedElement(By.linkText(resourceLink));
    }

	public WebElement topicPageTitle() {
		return waitForExpectedElement(By.id("co_browsePageLabel"));
	}

    public WebElement deliverLink() {
        return findElement(By.linkText("Deliver"));
    }

    public int totalResourcesOnFirstPage() {
        List<WebElement> resourceList = findElements(By.cssSelector("#coid_website_searchResults li[id^=cobalt_search_results_knowHowTopic]"));
        return resourceList.size();
    }

    public WebElement clickTopicLinkOnPAPage(String resourceLink) {
        return waitForExpectedElement(By.linkText(resourceLink));
    }

    public String getGuidFromLinkIntoResultList(String linkNumber){
        return getLinkFromResult(linkNumber).getAttribute("docguid");
    }

    public WebElement getLinkFromResult(String linkNumber){
        return waitForExpectedElement(By.cssSelector("li#cobalt_search_results_knowHowTopicPlc" + linkNumber + " h3 a"));
    }

    public boolean noEditorsPickWidget() {
        try {
            findElement(By.cssSelector("#coid_website_browseTopColumn>div"));
        } catch (NoSuchElementException nse) {
            return true;
        }
        return false;
    }

    public boolean noMetadataDisplayed(){
        return isExists(By.id("co_searchResults_citation_1"));
    }

    public boolean noSummaryDisplayed(){
        return isExists(By.id("co_searchResults_summary_1"));
    }

    public WebElement facetNameLink(String facet) {
        return waitForExpectedElement(By.xpath(String.format(".//ul[contains(@id,'plc_topic_facet_links')]//a[normalize-space(text())='%s']", facet)));
    }

    public WebElement resultsByResourceType(String resourceType) {
        return waitForExpectedElement(By.id("cobalt_search_knowHowTopicPlc_" + resourceType));
    }

	public WebElement getChoosePracticeAreaPageTitle() {
		return waitForExpectedElement(By.xpath(".//div[@id='coid_topic_practice_areas']/h3"));
	}
    public WebElement resourceHeading(String resourceName) {
        return waitForExpectedElement(By.xpath("//div[@id='cobalt_search_knowHowTopicPlc_results']//h2[contains(text(),'" + resourceName + "')]"));
    }
    public WebElement specificFacetCount(String facetName) {
        return waitForElementPresent(By.xpath(String.format("//span[.='%s']/parent::label[@class='SearchFacet-label']//span[@class='SearchFacet-outputTextValue']",facetName)));
    }

    public List<WebElement> resourceDocsTitleList(String resourceName) {
        return waitForExpectedElements(By.xpath("//h2[text()='" + resourceName + "']/..//a[contains(@id,'cobalt_result_knowhow_title')]"));
    }
    public List<WebElement> resourceDocTitleAllList() {
        return waitForExpectedElements(KNOW_HOW_TITLE);
    }

    public By resourceDocByTitleList() {
        return KNOW_HOW_TITLE;
    }

    public By resourceDocByTitle() {
        return KNOW_HOW_TITLE;
    }

    public List<WebElement> facetNameLinksList() {
        return waitForExpectedElements(By.xpath("//div[@class='SearchFacet-listItemGroup']/label/span[contains(@class,'labelText')]"));
    }

    public boolean facetGroupCollapsedOrExpanded(String type) {
        return Boolean.valueOf(waitForExpectedElement(By.xpath(String.format("//button[@class='SearchFacet-buttonToggle']/span[.='%s']/..",type))).getAttribute("aria-expanded"));
    }

    public void expandFacetGroup(String type) {
        waitForExpectedElement(By.xpath(String.format("//button[@class='SearchFacet-buttonToggle']/span[.='%s']/..",type))).click();
    }
}
