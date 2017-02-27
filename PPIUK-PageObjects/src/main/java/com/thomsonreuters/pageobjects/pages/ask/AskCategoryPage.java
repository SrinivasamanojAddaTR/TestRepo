package com.thomsonreuters.pageobjects.pages.ask;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


public class AskCategoryPage extends AbstractPage {

    public WebElement askDisclaimerText() {
        return waitForExpectedElement(By.className("ask-disclaimer"));
    }

    public WebElement askDisclaimerTextOnPracticeArea() {
        return waitForExpectedElement(By.cssSelector("#coid_website_browseMainColumn > div:nth-of-type(2) .co_genericBoxContent"));
    }

    public WebElement askDisclaimerTextOnAskTab() {
        return waitForExpectedElement(By.cssSelector("#coid_categoryBoxTab4SubPanel1-0-main div:nth-of-type(1) .co_genericBoxContent"));
    }

    public WebElement askOnThePosition(int position) {
        return waitForExpectedElement(By.xpath("//*[@id='ContentBlock" + (position - 1) + "']//a"));
    }

    public WebElement askTitle() {
        return waitForExpectedElement(By.xpath("//*[@id='co_docHeaderContainer']/h1"));
    }

    public WebElement askDisclaimerTextOnAskLandingPage(String withText) {
        return waitForExpectedElement(By.xpath("//div[@class='ask-disclaimer' and contains(., '" + withText + "')]"));
    }

    public WebElement askFeaturedQueriesQuestText() {
        return waitForExpectedElement(By.cssSelector("#coid_website_browseMainColumn > div:nth-of-type(3) .co_genericBoxContent"));
    }

    public WebElement askPracticeAreaWidgetHeader() {
        return waitForExpectedElement(By.cssSelector("#coid_website_browseMainColumn > div:nth-of-type(3) .co_genericBoxContent"));
    }

    public WebElement askFeaturedQueriesHeader() {
        return waitForExpectedElement(By.cssSelector("#coid_website_browseMainColumn > div:nth-of-type(3) > h3"));
    }

    public WebElement recentQueriesTab() {
        return waitForElementPresent(By.linkText("Recent queries"));
    }

    public WebElement featuredQueriesTab() {
        return waitForExpectedElement(By.linkText("Featured queries"));
    }

    public WebElement activeTab() {
        return waitForExpectedElement(By.cssSelector("ul[class='co_tabs co_categoryTabs'] li[class*='co_tabLeft co_tabActive']"));
    }

    public WebElement paginationBarPageSetNo(int index) {
        if(index==0){
            return waitForExpectedElement(By.cssSelector("a[class='co_prev']"));
        }else if (index ==999){
            return waitForExpectedElement(By.cssSelector("a[class='co_next']"));
        }
        return waitForExpectedElement(By.cssSelector("a[class*='pageNumber-" + index + "']"));
    }

    public WebElement activePage() {
        return waitForExpectedElement(By.xpath("//a[contains(@class, 'pageNumber-') and contains(@class, 'active')]"));
    }

    public WebElement askViewAllLink() {
        WebElement parent=findElement(By.cssSelector("div[class='co_genericBox co_expandedState co_dataFeedWidget styled']"));
        return findChildElement(parent, By.linkText("View all"));
    }

    public List<WebElement> askResources() {
        return waitForExpectedElements(By.cssSelector("div[id*='coid_categoryBoxTab1SubPanel1'] div .co_genericBoxContent"));
    }

    public List<WebElement> recentQueries() {
        // TODO Refactor this
        return waitForExpectedElements(By.xpath("//div[contains(@class, 'genericBox') and (contains(., 'Recent') or contains(., 'ueries'))]//a | //div[contains(@id, 'TabPanel') and contains(@class, 'Show')]//div[@class='co_artifactContent']//a"));
    }

    public List<WebElement> getCommentLabelsForRecentQueries() {
        // TODO Refactor this
        return waitForExpectedElements(By.xpath("//div[contains(@class, 'genericBox') and (contains(., 'Recent') or contains(., 'ueries'))]//span[@class='co_comments_count'] | //div[contains(@id, 'TabPanel') and contains(@class, 'Show')]//div[@class='co_artifactContent']//span[@class='co_comments_count']"));
    }

    public List<WebElement> recentQueriesOnAskTab() {
        return waitForExpectedElements(By.cssSelector("#coid_categoryBoxTab4SubPanel1-0-main div:nth-of-type(2) .co_genericBoxContent li"));
    }

    public List<WebElement> recentQueriesOnAskTabDatesOnly() {
        return waitForExpectedElements(By.cssSelector("#coid_categoryBoxTab4SubPanel1-0-main div:nth-of-type(2) .co_genericBoxContent li h4 .co_date"));
    }

    public List<WebElement> recentQueriesDatesOnly() {
        try {
            return waitForExpectedElements(By.xpath("//span[@class='co_date_message']/../../span[@class='co_date']"));
        } catch (NoSuchElementException | TimeoutException e) {
            return waitForExpectedElements(By.cssSelector(".co_date"));
        }
    }
    public List<WebElement> FQdatelist() {
        return waitForExpectedElements(By.xpath("//div[@id='coid_categoryBoxTabPanel1']//span[@class='co_date']"));
    }

    public List<WebElement> FQCommentslist() {
        return waitForExpectedElements(By.xpath("//div[@id='coid_categoryBoxTabPanel1']//span[@class='co_comments_count']"));
    }
    public List<WebElement> RQdatelist() {
        return waitForExpectedElements(By.xpath("//div[@id='coid_categoryBoxTabPanel2']//span[@class='co_date']"));
    }

    public List<WebElement> RQCommentslist() {
        return waitForExpectedElements(By.xpath("//div[@id='coid_categoryBoxTabPanel2']//span[@class='co_comments_count']"));
    }

    public List<WebElement> askQueriesInTopicPageDatesOnly() {
        return waitForExpectedElements(By.cssSelector("#cobalt_search_knowhowAskUK_results > ol li div:nth-of-type(1) div[id*='co_searchResults_citation'] .co_search_detailLevel_1 span:nth-of-type(1)"));
    }

    public List<WebElement> askQueriesInTopicPageReplyOnly() {
        return waitForExpectedElements(By.cssSelector("#cobalt_search_knowhowAskUK_results > ol li div:nth-of-type(1) div[id*='co_searchResults_citation'] .co_search_detailLevel_1 span:nth-of-type(2)"));
    }

    public List<WebElement> featuredQueries() {
        return waitForExpectedElements(By.cssSelector("div[id*='coid_categoryBoxTab1SubPanel1'] div .co_genericBoxContent div[id*='ContentBlock']"));


    }

    public List<WebElement> recentQueriesPanel() {
        return waitForExpectedElements(By.xpath("//div[@id='coid_categoryBoxTabPanel2']//div[@class='co_genericBoxContent']//li"));


    }

    public List<WebElement> recentPaginatedQueries() {
        return waitForExpectedElements(By.cssSelector("#coid_website_browseMainColumn div li[class*='container-page-block container-page-'][style='display: block;']"));
    }

    public List<WebElement> recentPaginatedQueriesLinksOnly() {
        return waitForExpectedElements(By.cssSelector("#coid_website_browseMainColumn div li[class*='container-page-block container-page-'][style='display: block;'] a"));
    }

    public List<WebElement> recentPaginatedQueriesDatesOnly() {
        return waitForExpectedElements(By.cssSelector(".co_date"));
    }

    public List<WebElement> askResourceHeaders() {
        return waitForExpectedElements(By.cssSelector("#coid_website_browseMainColumn div .co_genericBoxHeader"));
    }

    public WebElement askOurPeopleWidget() {
        return waitForExpectedElement(By.id("coid_website_browseRightColumn"));
    }

    public WebElement askOurPeoplePaHeadNameText() {
        return waitForExpectedElement(By.xpath("//div[@class='co_featureBoxInner']//h3"));
    }

    public WebElement askOurPeopleHeadPlLawText() {
        return waitForExpectedElement(By.xpath("//div[@class='co_featureBoxInner']//p"));
    }

    public WebElement topicGroupHeader(String header) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_categoryBoxTabPanel1']/div/div[@class='co_genericBox']/h3[@class='co_genericBoxHeader']/span[text()='"+header+"']"));
    }

    public WebElement topicLinkInTopicGroupHeader(String header, String topicLink) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_categoryBoxTabPanel1']/div/div[@class='co_genericBox']/h3[@class='co_genericBoxHeader']/span[text()='"+header+"']/../..//a[text()='"+topicLink+"']"));
    }

    public List<WebElement> getTopicWebElementLinks(){
        return waitForExpectedElements(By.xpath("//div[@id='coid_website_browseMainColumn']//a"));
    }

    public List<String> getTopicLinks(){
        List<String> links= new ArrayList<String>();
        for (WebElement element:getTopicWebElementLinks()){
            links.add(element.getText());
        }
        return links;
    }

    public WebElement getFeaturedQuery(String queryLinkText) {
        return waitForExpectedElement(By.xpath("//div[contains(@class, 'expandedState')]//a[contains(., '" + queryLinkText + "')]"));
    }

    /**
     * Get link for tab name
     *
     * @param tabName Tab name ("Topics", "Resources", ...)
     * @return WebElement with tab link
     */
    public WebElement getCategoryPageTab(String tabName) {
        return waitForExpectedElement(By.xpath("//li[contains(@id, 'categoryTab')]//a[contains(., '" + tabName + "')]"));
    }
}

