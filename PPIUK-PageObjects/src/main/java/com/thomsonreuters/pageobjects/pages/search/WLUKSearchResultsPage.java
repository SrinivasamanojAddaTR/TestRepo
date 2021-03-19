package com.thomsonreuters.pageobjects.pages.search;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by u4400015 on 17/08/2016.
 */
public class WLUKSearchResultsPage extends AbstractPage {


    private static final String DATE_PICKER_OPTION_XPATH = "//*[@class='co_dropDownMenuList']//span[@class='co_facet_predefinedListDateRanges_name'][contains(.,'%s')]";
    private static final By DATE_PICKER_DROPDOWN_CSS = By.cssSelector(".co_facet_predefinedDateRanges .co_dropDownMenuContent");
    private static final By CONTENT_TYPE_HEADER = By.xpath("//*[@id='co_facetHeaderresearchContentTypeSummary']");
    private static final String FACET_COUNT = "//label[contains(@for,'facet')][contains(text(), '%s')]/preceding-sibling::span[@class='co_facetCount']";
    private static final By PRACTICE_AREA_HEADER = By.xpath("//*[@id='co_facetHeaderknowHowPracticeAreaSummary']");
    private static final By DATE_HEADER = By.xpath("co_facetHeaderresearchDateSummary");
    private static final By MULTIPLE_FILTER_STATE_CHECKBOX = By.xpath("//input[contains(@id,'SlideToggle_')]");
    private static final By MULTIPLE_FILTER_TOGGLE = By.xpath("//*[contains(@class,'SlideToggle-thumb-container')]");
    private static final String APPLY_FILTERS_BUTTON = "//*[contains(@class,'co_multifacet_apply')]";
    /**
     * Object representing Resource Type heading for facet group
     */
    public WebElement facetGroupHeaderResourceType() {

        return waitForExpectedElement(By.id("co_facetHeaderresearchContentTypeSummary"));
    }

    public WebElement applyFLUKFiltersButton() {
        return waitForExpectedElement(By.xpath(APPLY_FILTERS_BUTTON));
    }

    /**
     * Object representing Jurisdiction heading for facet group
     */
    public WebElement facetGroupHeaderJurisdiction() {

        return waitForExpectedElement(By.id("co_facetHeaderjurisdictionSummary"));
    }
    public void clickMultipleFiltersToggle() {
        click(MULTIPLE_FILTER_TOGGLE);
    }
    public boolean isMultipleFiltersToggleSelected() {
        return waitForElementPresent(MULTIPLE_FILTER_STATE_CHECKBOX).isSelected();
    }

    /**
     * Object representing Content Type Header in facet pane
     */
    public Boolean isContentGroupHeaderDisplayed() {

        return isElementDisplayed(CONTENT_TYPE_HEADER);
    }

    /**
     * Object representing Practice Area Header in facet pane
     */
    public Boolean isPracticeAreaHeaderDisplayed() {

        return isElementDisplayed(PRACTICE_AREA_HEADER);
    }

    /**
     * Object representing Date Header in facet pane
     */
    public Boolean isDateHeaderDisplayed() {

        return isElementDisplayed(DATE_HEADER);
    }

    /**
     * Object representing Topic heading for facet group
     */
    public WebElement facetGroupHeaderTopic() {

        return waitForExpectedElement(By.id("co_facetHeadertopicSummary"));
    }


    /**
     * This is for the "Clear all" link element
     */
    public WebElement clearAllLink() {

        return waitForExpectedElement(By.xpath("//div[@id='co_undoAllSelections']/a"), 10);
    }


    /**
     * Object representing any wluk facet checkbox as identified by facet name
     */
    public WebElement knowHowFacetCheckbox(String facetName) {
        try {
            WebElement findlabel = waitForExpectedElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[text()='" + facetName + "']"), 10);
            String labelFor = findlabel.getAttribute("for");
            return waitForExpectedElement(By.id(labelFor));
        } catch (StaleElementReferenceException se) {
            return knowHowFacetCheckbox(facetName);
        }
    }


    public WebElement datePickerHeading() {

        return waitForExpectedElement(By.xpath("//*[@id='co_facetHeaderresearchDateSummary']"));
    }


    /**
     * Object representing
     * date picker object on search results page
     */

    public WebElement datePickerWidget() {
        return waitForExpectedElement(By.cssSelector(".co_facet_predefinedDateRanges button"));
    }



    /**
     * Is date picker drop-down with date options visible?
     *
     * @return True - if date picker drop-down visible (expanded), false - otherwise (collapsed)
     */
    public boolean isDatePickerDropdownVisible() {

        return isElementDisplayed(DATE_PICKER_DROPDOWN_CSS);
    }

    /**
     * Object representing individual options within date picker
     */
    public WebElement datePickerOptions(String option) {

        return waitForExpectedElement(By.xpath(String.format(DATE_PICKER_OPTION_XPATH, option)));
    }

    /**
     * Object representing date picker facets count
     */

    public WebElement datePickerNameAndCountEntries(String option) {

        return waitForExpectedElement(By.xpath("//*[@class='co_facet_predefinedListDateRanges_dateRange']//span[contains(text(),'" + option + "')]/following-sibling::*"));
    }


    /**
     * Object checking for presence of option within date picker
     */

    public boolean isDatePickerOptionDisplayed(String option) {
        return isElementDisplayed(By.xpath(String.format(DATE_PICKER_OPTION_XPATH, option)));
    }


    /**
     * object representing the All option on the collapsed date picker widget
     */


    public boolean isAllOptionDisplayed() {
        return isElementDisplayed(By.xpath("//*[@class='co_dropDownButton'][contains(text(),'All')]"));
    }

    /**
     * object representing the Last 6 months option on the collapsed date picker widget
     */


    public boolean isLast6MonthsOptionDisplayed() {
        return isElementDisplayed(By.xpath("//button[@class='co_dropDownButton'][contains(.,'Last 6 months')]"));
    }



    /**
     * This is an object for all the listed date picker entries
     */


    public List<WebElement> getDatePickerEntries() {
        return waitForExpectedElements(By.xpath("//*[@class='co_dropDownMenuList']//span[@class='co_facet_predefinedListDateRanges_name']"));
    }


    /**
     * this is the object representing any search result of any type on the wluk combined search results page
     */

    public WebElement wlukSearchResult(String number) {

        return waitForExpectedElement(By.xpath("//*[@id='cobalt_result_ukCombinedResearch_title" + number + "']"));
    }

    public WebElement datePickerDropdown() {

        return waitForExpectedElement(By.xpath("//*[@class='co_dropDownMenuList']"));
    }

    public WebElement expandDatePickerButton() {

        return waitForExpectedElement(By.xpath("//button[@class='co_dropDownButton']"));
    }

    public WebElement facetCount(String facet) {

        return waitForExpectedElement(By.xpath(String.format(FACET_COUNT, facet)));

    }

    public Boolean isFacetCountDisplayed(String facet) {

        return isElementDisplayed(By.xpath(String.format(FACET_COUNT, facet)));
    }

}
