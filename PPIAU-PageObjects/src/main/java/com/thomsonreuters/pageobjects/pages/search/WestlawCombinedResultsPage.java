package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by u4400015 on 30/06/2016. This is the combined search results page for westlaw uk research content
 * on practical law - there are many objects on this page which are already defined on the page searchResultsPage
 * and these objects work for both knowhow/what's market and research - those that are not already defined
 * or which are different etc for research content are listed here.
 */
public class WestlawCombinedResultsPage extends AbstractPage {

    private final String WHERE_REPORTED = ".//*[@id='cobalt_search_ukCombinedResearch_checkbox_%s']/following-sibling::div[@class='co_searchContent']//strong[contains(text(),'Where Reported')]";

    /**
     * APPLICABLE TO ALL CONTENT TYPES
     */

    /**
     * terms in context heading
     */

    public WebElement termsInContextHeading() {

        return waitForExpectedElement(By.xpath("//div[contains(@class,'co_search_detailLevel']//strong[contains(.,Terms')]"));
    }

    /**
     * terms in context text by result position - gets all the terms in context text for a particular result
     */

    public List<WebElement> termsInContext(String position) {
        return waitForExpectedElements(By.xpath("//div[starts-with(@id,'co_snippet_" + position + "_')]//a"));
    }

    /**
     * Check the presence of terms in context for result with number
     *
     * @param position Result number
     * @return True - is the search result has the terms of context. False - otherwise.
     */
    public boolean areTheTermsInContextPresent(String position) {
        return isExists(By.xpath("//div[starts-with(@id,'co_snippet_" + position + "_')]"));
    }

    /**
     * Check the display of terms in context for result with number
     *
     * @param position Result number
     * @return True - is the search result has the terms of context. False - otherwise.
     */
    public boolean areTheTermsInContextDisplayed(String position) {
        return isElementDisplayed(By.xpath("//div[starts-with(@id,'co_snippet_" + position + "_')]"));
    }


    /**
     * facet pane - heading for resource type within which individual content types are listed
     */

    public WebElement resourceTypeHeader() {

    return waitForExpectedElement(By.xpath("//h4[@id='co_facetHeaderresearchContentTypeSummary']"));

    }

    /**
     * facet pane - heading for jurisdiction
     */

    public WebElement jurisdictionHeader() {

        return waitForExpectedElement(By.xpath("//h4[@id='co_facetHeaderjurisdictionSummary']"));
    }


    /**
     * facet pane - heading for topic
     */

    public WebElement topicHeader() {

        return waitForExpectedElement(By.xpath("//h4[@id='co_facetHeadertopicSummary']"));
    }


	public boolean isNonLinkResultTitleDisplayed(String position) {
		return isElementDisplayed(By.xpath("//li[@id='cobalt_search_results_ukCombinedResearch" + position
				+ "']//div[@class='co_search_results_item_title']"));
	}

    /**
     * grabs the title by position only - no expected text passed in as a variable - can be any result (cases, legislation or journals etc)
     * NOTE: this is not always a title but a link. For some documents (i.e. provisions) title will be another element
     */
    public WebElement resultTitle(String position) {

        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_ukCombinedResearch_title" + position +"']"));
    }

    public boolean isFirstResultTitleDisplayed(String position) {

        return isElementDisplayed(By.xpath("//a[@id='cobalt_result_ukCombinedResearch_title" + position +"']"));
    }


    /**
     * grabs the title by position and title text - can be any result (cases, legislation or journals etc)
     */

    public WebElement resultTitle(String position, String text) {

        return waitForExpectedElement(By.xpath("//a[@id='cobalt_result_ukCombinedResearch_title" + position + "][contains(text(),'" + text + "')]"));
    }

    /**
     * Get version text for the result number (for cases and legislation)
     *
     * @param position Document number
     * @return Element with the version
     */
    public WebElement versionText(String position) {
        return waitForExpectedElement(By.xpath("//input[contains(@id, 'checkbox_" + position + "')]/following-sibling::div/div[@class='co_search_detailLevel_2']/p"));
    }

    /**
     * Check iv version text element is present for the result number (for cases and legislation)
     *
     * @param position Document number
     * @return True - if the element exists. False - otherwise.
     */
    public boolean isVersionTextExists(String position) {
        // Wait for result title and check it version presence
        resultTitle(position);
        return isExists(By.xpath("//input[contains(@id, 'checkbox_" + position + "')]/following-sibling::div/div[@class='co_search_detailLevel_2']/p"));
    }

    /**
     * LEGISLATION OBJECTS
     */

    /**
     * grabs the legislation provision by position only - no expected text passed in as a variable
     */

    public WebElement legislationProvision(String position) {
        return waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']/li[" + position + "]//h3/a"));
    }

    /**
     * Get all provisions for the legislation documents grouped by SI
     *
     * @param groupName SI gourp name (normally, just a title of search reslt)
     * @return List with provisions
     */
    public List<WebElement> getProvisionsForSiGroup(String groupName) {
        return waitForExpectedElements(By.xpath("//div[@class='co_searchContent' and contains(., \"" + groupName + "\")]//div[@class='co_searchResults_citation']/span"));
    }

    /**
     * Get status icon element for the case / legislation search result with the number
     *
     * @param position Search result number
     * @return Element with the status icon
     */
    public WebElement getStatusIcon(String position) {
        return waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']/li[" + position + "]//span[contains(@class, 'th_flat_icon')]"));
    }

    public WebElement getPublishedDate(String position) {
        return waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']/li[" + position + "]//span[contains(@class, 'co_greenStatus')]"));
    }

    /**
     * Check if status icon for the case / legislation search result with the number is exists
     *
     * @param position Search result number
     * @return True - if element exists. False - otherwise
     */
    public boolean isStatusIconExists(String position) {
        // Wait for result title and check it version presence
        resultTitle(position);
        return isExists(By.xpath("//ol[@class='co_searchResult_list']/li[" + position + "]//span[contains(@class, 'status_icon')]"));
    }

    /**
     * Check if "Arrangement of Provisions" link is present for the result number
     *
     * @param position Search result number
     * @return True - if element exists. False - otherwise
     */
    public boolean isArrangementOfProvisionsLinkExists(String position) {
        return isExists(By.xpath("//ol[@class='co_searchResult_list']/li[" + position + "]//a[starts-with(normalize-space(.), 'Part')]"));
    }

    public List<WebElement> legislationProvisionSections(String position) {
        return waitForExpectedElements(By.xpath("//ol[@class='co_searchResult_list']/li[" + position + "]//div[@class='co_search_legSection']"));
    }

    public boolean isLegislationProvisionSectionExists(String position) {
        return isExists(By.xpath("//ol[@class='co_searchResult_list']/li[" + position + "]//div[@class='co_search_legSection']"));
    }

    /**
     * CASES OBJECTS
     */

    /**
     * case court by position only - no text passed in as a variable
     */

    public WebElement caseCourt(String position) {
        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + position +"']//span[2]"));
    }

    /**
     * case court grabbed by using expected text and position
     */

    public WebElement caseCourt(String position, String text) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + position + "']//span[@class='co_search_detailLevel_1']/preceding-sibling::span[contains(text(),'" + text + "')]"));
    }

    /**
    * case lead citation by position only (listed after court in the individual results)
    */

    public WebElement caseLeadCitation(String position) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + position + "']//span[@class='co_search_detailLevel_1']"));
    }

    /**
     * case lead citation by position and expected text
     */

    public WebElement caseLeadCitation(String position, String text) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + position + "']//span[@class='co_search_detailLevel_1'][contains(text(),'" + text + "')]"));
    }

    /**
     * case judgment date by position only
     */

    public WebElement caseJudgmentDate(String position) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + position + "']//span[@class='co_search_detailLevel_1']/following-sibling::span"));
    }


    /**
     * case judgment date by position and expected date
     */

    public WebElement caseJudgmentDate(String position, String text) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_citation_" + position + "']//span[@class='co_search_detailLevel_1']/following-sibling::span[contains(text(),'" + text + "')]"));
    }

    /**
     * case subject heading - verifying that the Subject heading is present for a result in position 1, 2 etc only - not the data within the section
     */

    public WebElement caseSubjectHeader(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(.,'Subject')]"));
    }

    /**
     * case subject text - verifying the data within the Subject section
     */

    public WebElement caseSubjectData(String position, String text) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(.,'Subject')]/parent::*[contains(.,'" + text + "')]"));

    }

    /**
     * case subject data by position only
     */

    public WebElement caseSubject(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(.,'Subject')]/parent::*"));
    }

    public boolean isCaseSubjectDisplayed(String position) {
        return isElementDisplayed(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(.,'Subject')]/parent::*"));
    }

    /**
     * case keyword heading - verifying that the Subject heading is present for a result in position 1, 2 etc only - not the data within the section
     */

    public WebElement caseKeywordHeader (String position) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(.,'Keywords')]"));
    }


    /**
     * case keyword data - verifying the keyword data by position and keyword text which are both passed in as variables
     */

    public WebElement caseKeywordData(String position, String text) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(.,'Keywords')]/parent::*[contains(.,'" + text + "')]"));
    }

    /**
     * case keyword data by position only
     */

    public WebElement caseKeywordData(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(.,'Keywords')]/parent::*"));
    }

    public boolean isCaseKeywordDataDisplayed(String position) {
        return isElementDisplayed(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(.,'Keywords')]/parent::*"));
    }

    /**
     * case where reported heading only by position in the result list (1, 2 etc)
     */

    public WebElement caseWhereReportedHeader(String position) {
        return waitForExpectedElement(By.xpath(String.format(WHERE_REPORTED, position)));
    }

    /**
     * case where reported heading data using the position only (useful when you want to grab the data but do not know what it comprises)
     */

    public WebElement caseWhereReportedData(String position) {
        return waitForExpectedElement(By.xpath(String.format(WHERE_REPORTED, position) + "/parent::*"));
    }

    /**
     * case where reported heading data using the text sought and the position
     */

    public WebElement caseWhereReportedData(String position, String text) {
        return waitForExpectedElement(By.xpath(String.format(WHERE_REPORTED, position) + "/parent::*[contains(.,'" + text + "')]"));
    }

    public List<WebElement> caseWhereReportedLinks(String position) {
        return waitForExpectedElements(By.xpath(String.format(WHERE_REPORTED, position) +"/parent::*//a"));
    }


    /**
     * Case summary object - includes the heading and the data - obtained by position only - no expected text passed in
     */

    public WebElement caseSummary(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='co_searchResults_summary_" + position +"' and not(contains(@class, 'hide')) and contains(., 'Summary')]"));
    }

    public boolean isCaseSummaryDisplayed(String position) {
        return isElementDisplayed(By.xpath("//*[@id='co_searchResults_summary_" + position +"' and not(contains(@class, 'hide')) and contains(., 'Summary')]"));
    }

    /**
     * JOURNALS OBJECTS
     */


    /**
     * Journal name and citation object
     */

    public WebElement journalCitation(String position) {

        return waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']/li[" + position + "]//div[contains(@class, 'detail') and contains(., 'Citation')]"));
    }

    /**
     * Journal Subject heading
     */

    public WebElement journalSubjectHeader(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(text(),'Subject')]"));
    }

    /**
     * Journal subject data by position only - no expected text passed in as a variable
     */

    public WebElement journalSubjectData(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(text(),'Subject')]/parent::*"));
    }

    /**
     * Journal subject data by position and expected text
     */

    public WebElement journalSubjectData(String position, String text) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(text(),'Subject')]/parent::*[contains(.,'" + text + "')]"));
    }


    /**
     * Journal keywords heading
     */

    public WebElement journalKeywordHeader(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(text(),'Keywords')]"));
    }

    /**
     * Journal keywords by position only - no expected text passed in as a variable
     */

    public WebElement journalKeywordData(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(text(),'Keywords')]/parent::*"));
    }

    /**
     * Journal keywords by position and expected text
     */

    public WebElement journalKeywordData(String position, String text) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_ukCombinedResearch_checkbox_" + position + "']/following-sibling::div[@class='co_searchContent']//strong[contains(text(),'Keywords')]/parent::*[contains(.,'" + text + "')]"));
    }


    /**
     * Journal summary heading - passing in the position of the individual result
     */

    public WebElement journalSummaryHeader(String position) {

        return waitForExpectedElement(By.xpath("//div[@id='co_searchResults_summary_" + position + "']/strong[contains(text(),'Summary')]"));
    }

    /**
     * Journal abstract object - includes the heading and the data - obtained by position only - no expected text passed in
     */

    public WebElement journalAbstract(String position) {

        return waitForExpectedElement(By.xpath("//*[@id='co_searchResults_summary_" + position +"' and not(contains(@class, 'hide')) and contains(., 'Abstract')]"));
    }

    public boolean isDocumentSummaryOrAbstractInfoExists(String position) {

        return isExists(By.xpath("//*[@id='co_searchResults_summary_" + position + "' and not(contains(@class, 'hide'))]"));
    }

    /**
     * Get Journal title for the journal result with number
     *
     * @param resultNumber Journal search result number
     * @return Element with a journal title (including the field name "Journal title")
     */
    public WebElement journalTitle(String resultNumber) {
        return waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']/li[" + resultNumber + "]//div[contains(@class, 'detail') and contains(., 'title')]"));
    }

    /**
     * Individual search result link - passing in the position of the individual result
     */

    public WebElement searchResultWestlaw(String number) {
        
        return waitForExpectedElement(By.xpath("//ol[@class='co_searchResult_list']/li[" + number + "]//h3/a"));
    }

    /**
     * Individual search result case - this is the link on the case title - the party names e.g. Kazakhstan Kagazy Plc v Zhunus
     */

    public WebElement caseName(String resultNumber) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_result_ukCombinedResearch_title" + resultNumber + "']"));
    }

    /**
     * Individual links in the cases search result where reported field e.g. to Case Analysis, Law report
     */

    public WebElement whereReportedLink(String resultNumber, String name) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_results_ukCombinedResearch" + resultNumber + "']//a[contains(text(),'" + name + "')]"));
    }


    /**
     * Individual search result journal - this is an object representing links in the article section - e.g. to full text or abstract
     */

    public WebElement journalLinks(String resultNumber, String name) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_search_results_ukCombinedResearch" + resultNumber + "']//a[contains(text(),'" + name + "')]"));
    }


    /**
     * name of scoped practice area (this facet name does not have an associated checkbox following the scoping)
     */

    public Boolean isScopedSearchPracticeAreaFacetDisplayed(String facet) {
        return isElementDisplayed(By.xpath("//*[@id='co_facetSubHeaderknowHowPracticeAreaSummary']//b[contains(text(),'" + facet + "')]"));
    }




}
