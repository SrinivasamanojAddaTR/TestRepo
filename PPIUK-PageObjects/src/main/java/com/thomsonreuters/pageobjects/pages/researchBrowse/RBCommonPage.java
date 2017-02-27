package com.thomsonreuters.pageobjects.pages.researchBrowse;

import com.thomsonreuters.pageobjects.utils.researchBrowse.ResearchContentTypeEnum;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;

import java.util.List;


public class RBCommonPage extends AbstractPage {

    // Search
    public WebElement activeSearchTab() {
        return findElement(By.cssSelector("#coid_website_searchWidget li.co_tabActive a"));
    }

    public WebElement pageHeading() {
        return waitAndFindElement(By.id("co_browsePageLabel"));
    }

    public WebElement contentTypeLink(ResearchContentTypeEnum contentType) {
        return waitAndFindElement(By.linkText(contentType.getLinkText()));
    }

    public WebElement practiceAreaLink(String practiceArea) {
    	String path1 = String.format("//div[@id = 'coid_categoryBoxTabPanel1']//a[contains(.,'%s')]", practiceArea);
    	String path2 = String.format("//a[contains(.,'%s') and contains(@onclick, 'javascript')]", practiceArea);
    	try{
    	    return waitForExpectedElement(By.xpath(path1), 30);
    	} catch (TimeoutException e) {
    	    return waitForExpectedElement(By.xpath(path2), 30);	
    	}
    }

    public WebElement searchResultHeading() {
        return waitAndFindElement(By.cssSelector("div.co_search_result_heading_content h1"));
    }

    // Static Text and Marketing Pages
    public WebElement description() {
        return waitAndFindElement(By.cssSelector("div.co_searchResults_summary"));
    }

    public WebElement findOutMoreLink() {
        return waitAndFindElement(By.linkText("Find out more"));
    }

    public WebElement waitForPageHeadingText(String text) {
        return waitAndFindElement(By.xpath("//h1[@id='co_browsePageLabel'][text()=\"" + text + "\"]"));
    }

    public WebElement firstResentHistoryLink(){
        return waitForExpectedElement(By.xpath(String.format("//*[@id='cobalt_foldering_ro_item_name_0']")));
    }

    public List<WebElement> resentHistoryLinks(){
        return waitForExpectedElements(By.xpath("//td[@class='co_detailsTable_content']//a"));
    }

    public List<WebElement> resentHistoryTypeLinks(String type){
        return waitForExpectedElements(By.xpath("//td[contains(@class, 'co_detailsTable_event') and contains(.,'" + type + "')]/following-sibling::td//a"));
    }

    public WebElement waitForMarketingPageHeadingText() {
        return waitAndFindElement(By.xpath("//h3[text()=\"This is a marketing page\"]"));
    }

}
