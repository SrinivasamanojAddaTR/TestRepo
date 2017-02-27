package com.thomsonreuters.pageobjects.pages.plPlusResearchSearch;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


public class BaseResultsPage extends AbstractPage {

    private static final String RESULT_ITEM_CSS = ".//span[text()='%s']";
    private static final String CASES_OFFICIAL_TRANSCRIPT_RESULT_ITEM_CSS = ".//h3/a/span[contains(text(),'%S')]/../../../div[@class='co_searchResults_citation']/div[@class='childLinks']/a/span[text()='Official Transcript']";

    public WebElement titleDisplayed() {
        return waitForExpectedElement(By.cssSelector("h3 #cobalt_result_internationalCase_title1>span"));
    }

    public WebElement courtDisplay() {
        return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_1 span:nth-child(3)"));
    }

    public WebElement dateDisplayed() {
        return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_1 span:nth-child(4)"));
    }

    public WebElement subjectDisplayed() {
        return waitForExpectedElement(By.xpath("//*[@id='co_searchResults_citation_1']/div[1]/span[contains(text(),'Subject')]"));
           }

    public WebElement keyWordsDisplayed() {
        return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_1 div.co_search_detailLevel_2:nth-of-type(2)>span"));
    }

    public WebElement whereReportedDisplayed() {
        return waitForExpectedElement(By.cssSelector("div.co_search_detailLevel_1:nth-of-type(3)>span"));
    }

    public WebElement snippetDisplayed() {
        return waitForExpectedElement(By.cssSelector("div.co_snippet.co_search_detailLevel_2"));
    }

    public WebElement termsInContext() {
        return findElement(By.cssSelector("div.co_snippet.co_search_detailLevel_2 span.co_searchTerm"));
    }

    public WebElement summaryDisplayed() {
        return waitForExpectedElement(By.xpath("//*[@id='co_searchResults_summary_1']/ul/li"));
    }

    public WebElement casesOfficialTranscriptDisplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='cobalt_result_internationalCase_title1']/span[contains(text(),'Official Transcript')]"));
    }

    public WebElement pdfDisplayed(){
        return waitForExpectedElement(By.xpath(".//*[@id='cobalt_result_internationalCase_title1']/img"));
    }

    public WebElement caseAnalysDisplayed(){
        return waitForExpectedElement(By.xpath(".//*[@id='cobalt_result_internationalCase_title1']/span[contains(text(),'Case Analysis')]"));
    }

    public WebElement lawReportDisplayed(){
        return waitForExpectedElement(By.cssSelector("#cobalt_result_internationalCase_title1 .co_searchTerm"));
    }

    public WebElement journalsTitleDisplayed() {
        return waitForExpectedElement(By.cssSelector("h3 #cobalt_result_internationalJournal_title1>span"));
    }

    public WebElement journalsCitationDisplayed() {
        return waitForExpectedElement(By.cssSelector("#co_searchResults_citation_1 div.co_search_detailLevel_1 span:nth-child(1)"));
    }

    public WebElement journalsSubjectTitleDislayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='co_searchResults_citation_1']/div[2]/span[1]"));
    }

    public WebElement journalsSubjectDisplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='co_searchResults_citation_1']/div[2]/span[2]"));
    }

    public WebElement journalsKeyWordsTitleDisplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='co_searchResults_citation_1']/div[3]/span[1]"));
    }

    public WebElement journalsKeyWordsDisplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='co_searchResults_citation_1']/div[3]/span[2]"));
    }

    public WebElement journalsSnippetDisplayed() {
        return waitForExpectedElement(By.cssSelector("#cobalt_result_internationalJournal_snippet_1_1"));
    }

    public WebElement journalsSummaryDisplayed() {
        return waitForExpectedElement(By.xpath("//*[@id='co_searchResults_summary_1']/ul/li"));
    }

    public WebElement journalsFullTextDisplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='cobalt_result_internationalJournal_title1']/span[contains(text(),'Full Text')]"));
    }

    public WebElement journalsAlternateTextDIsplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='cobalt_result_internationalJournal_title1']/span[contains(text(),'Alternate Text')]"));
    }

    public WebElement legislationAdvancedSearchClick() {
        return waitForExpectedElement(By.id("co_search_advancedSearchLink"));
    }

    public WebElement legislationTitleDisplayed() {
        return waitForExpectedElement(By.cssSelector("h3 #cobalt_result_internationalLegislation_title1>span"));
    }

    public WebElement legislationDetailsDisplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='co_searchResults_citation_1']/span[3]"));
    }

    public WebElement legislationVersionDisplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='co_searchResults_citation_1']/div[1]/span"));
    }

    public WebElement legislationFullTextDisplayed() {
        return waitForExpectedElement(By.xpath(".//*[@id='cobalt_result_internationalLegislation_title1']/span[contains(text(),'Full Text')]"));
    }

    /**
     * This method verifies whether the result item is displayed or not, based on the given result item name.
     * And returns the boolean value.
     *
     * @param name
     * @return boolean
     */
    public boolean isResultItemDisplayed(String name) {
    	return isElementDisplayed(By.xpath(String.format(RESULT_ITEM_CSS, name)));
    }

    /**
     * This method does the clicking on the result item link based on the given result item name.
     *
     * @param name
     */
    public void clickOnResultItem(String name) {
        retryingFindElement(By.xpath(String.format(RESULT_ITEM_CSS, name))).click();
    }

    /**
     * This private method does the finding the Result item link webelement based on given result row number.
     *
     * @param rowNum
     * @return WebElement
     */
    private WebElement getResultLinkElement(String rowNum) {
        try {
            String docType = getDocumentType();
            if (docType.contains("UK-CASES")) {
                return retryingFindElement(By.cssSelector("h3 #cobalt_result_internationalCase_title" + rowNum + " span"));
            } else if (docType.contains("UK-LEGISLATION")) {
                return retryingFindElement(By.cssSelector("#cobalt_search_results_ukLegislation" + rowNum + " h3 a"));
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the Result Link Element based on row index : " + rowNum + " : " + te.getMessage());
        }
        throw new PageOperationException("Error in finding the Result Link Element based on row index : " + rowNum);
    }

    public String getDocumentType() {
        try {
            return waitFluentForElement(By.cssSelector(".co_search_result_heading_content>h1")).getText();
        } catch (TimeoutException te){
            LOG.info("context", te);
        }
        return StringUtils.EMPTY;
    }

    /**
     * This method find out the given row number result item is present or not.
     *
     * @param rowNum
     * @return boolean
     */
    public boolean isResultItemPresentOnGivenRow(String rowNum) {
    	String docType = getDocumentType();
    	switch (docType) {
		case "UK-CASES":
			return isElementDisplayed(By.cssSelector("h3 #cobalt_result_internationalCase_title" + rowNum + " span"));
		case "UK-LEGISLATION":
			return isElementDisplayed(By.cssSelector("#cobalt_search_results_ukLegislation" + rowNum + " h3 a"));
		default:
			throw new RuntimeException("Undefined doc type : " + docType);
		}
    }

    /**
     * This method does the clicking on the result item link based on the given result row number.
     *
     * @param rowNum
     */
    public void clickOnResultLinkElement(String rowNum) {
        getResultLinkElement(rowNum).click();
    }

    /**
     * This method gets the text of the given row number result item.
     *
     * @param rowNum
     * @return String
     */
    public String getResultLinkElementText(String rowNum) {
        try {
            return getResultLinkElement(rowNum).getText();
        } catch (PageOperationException pe) {
            LOG.info("contest", pe);
        }
        return StringUtils.EMPTY;
    }

    public List<String> getResultItems() {
        List<String> results = new ArrayList<String>();
        try {
            for (WebElement item : retryingFindElements(By.cssSelector(".co_searchResult_list li h3 a"))) {
                results.add(item.getText());
            }
        } catch (PageOperationException pe) {
            LOG.info("contest", pe);
        }
        return results;
    }

    public WebElement applyFiltersButton() {
        return waitForExpectedElement(By.cssSelector("#co_multifacet_selector_2 > a.co_multifacet_apply"));
    }

    public WebElement clearFilters() {
        return waitForExpectedElement(By.xpath("//*[@id='co_undoAllSelections']/a[text()='Clear all filters']"));
    }

    /**
     * This method is to find and click on the cases official transcript link present on search result item.
     * @param searchTerm
     */
    public void navigateToOfficialTranscript(String searchTerm) {
        try {
            waitForElementPresent(By.xpath(String.format(CASES_OFFICIAL_TRANSCRIPT_RESULT_ITEM_CSS,searchTerm))).click();
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the official transcript link."+searchTerm+" ; "+te.getMessage());
        }
    }

    /**
     * Get list contains full text per result.
     * E.g., for one result will be included: title, doc type, Status, snippet text, ...
     * @return List of results
     */
    public List<WebElement> getResultListWithFullText() {
        return  waitAndFindElements(By.xpath("//li[contains(@id, 'cobalt_search_results')]"));
    }
}