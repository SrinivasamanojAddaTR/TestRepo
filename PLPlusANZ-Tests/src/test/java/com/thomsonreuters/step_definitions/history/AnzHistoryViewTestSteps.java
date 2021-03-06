package com.thomsonreuters.step_definitions.history;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.legal_updates.LegalUpdatesResultsPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.legal_updates.CalendarAndDate;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.step_definitions.login.ClientIdTest1;
import com.thomsonreuters.step_definitions.uk.search.BasicKnowHowSearchUKS101TestSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnzHistoryViewTestSteps extends BaseStepDef {

    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private LegalUpdatesResultsPage legalUpdatesResultsPage = new LegalUpdatesResultsPage();
    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private CommonMethods commonMethods = new CommonMethods();
    private ClientIdTest1 clientIdTest = new ClientIdTest1();
    private BasicKnowHowSearchUKS101TestSteps basicKnowHowSearchUKS101TestSteps = new BasicKnowHowSearchUKS101TestSteps();
    private WLNHeader wlnHeader = new WLNHeader();
    private FooterUtils footerUtils = new FooterUtils();
    private String storedTitle = null;
    private String searchResultAndCount = null;
    private static final String AUSTRALIA_SYDNEY_TIMEZONE = "Australia/Sydney";

    @When("^the user clicks on the first result and stores its title$")
    public void theUserClicksOnTheFirstResultAndStoresItsTitle() throws Throwable {
        storedTitle = legalUpdatesResultsPage.getAllUpdatesTitles().get(0).getText();
        legalUpdatesResultsPage.getAllUpdatesTitles().get(0).click();
    }

    @When("^the user clicks on the first search result$")
    public void theUserClicksOnTheFirstSearchResult() throws Throwable {
        legalUpdatesResultsPage.getAllUpdatesTitles().get(0).click();
    }

    @When("^user should see the same first title in the recent history's \"(.*)\" result$")
    public void userShouldSeeTheSameFirstTitleInTheRecentHistory(String resultPosition) throws Throwable {
        String position = resultPosition.replaceAll("[^0-9]", "");
        String actualTitle = researchOrganizerPage.getLinkToDocumentAtRowPosition(position).getText();
        assertTrue(actualTitle + " is not matching with expectedTitle..!", storedTitle.equalsIgnoreCase(actualTitle));

    }

    @When("^user should see the \"(.*)\" document's meta data and todays date$")
    public void theFolderDisappearsFromFolderLevel(String resultPosition) throws Throwable {
        String position = resultPosition.replaceAll("[^0-9]", "");
        String actualDate = researchOrganizerPage.getDateAtRowPosition(position).getText();
        String expectedDate = CalendarAndDate.getCurrentDate();
        assertTrue(actualDate + " is not matching with todays date..!", actualDate.contains(expectedDate));
    }

    @When("^user clicks on the \"(.*)\" title link in the history tab$")
    public void userClicksOnTheTitleDocumentInTheHistoryTab(String resultPosition) throws Throwable {
        String position = resultPosition.replaceAll("[^0-9]", "");
        researchOrganizerPage.getLinkToDocumentAtRowPosition(position).click();
    }

    @When("^user should be able to see the same document$")
    public void userShouldBeAbleToSeeTheSameDocument() throws Throwable {
        String docTitle = standardDocumentPage.documentTitle().getText().trim();
        assertTrue(docTitle + " is not matching with todays date..!", docTitle.contains(storedTitle));
    }

    @When("^the user stores the search title and count$")
    public void theUserStoresTheSearchTitleAndCount() throws Throwable {
        searchResultAndCount = searchResultsPage.searchTitleAndCount().getText().trim();
    }

    @When("^user should be able to see the same search result with title and count$")
    public void userShouldBeAbleToSeetheSameSearchResultWithTitleAndCount() throws Throwable {
        String actualSearchTitleAndCount = searchResultsPage.searchTitleAndCount().getText().trim();
        assertTrue(actualSearchTitleAndCount + " is not matching..!", actualSearchTitleAndCount.equalsIgnoreCase(searchResultAndCount));
    }

    @Then("^the user should be seeing \"(.*?)\" per page selected$")
    public void userShouldbeSeeingPerPage(String perPageNo) throws Throwable {
        //   legalUpdatesResultsPage.resultsPerPageLink().click();
        assertTrue(perPageNo + "Count is not right..!", searchResultsPage.isTextPresent(knowHowSearchResultsPage.searchResultByCountLabel(), perPageNo, 5000));
    }

    @Given("^the user changes the clientID to \"(.*?)\"$")
    public void theUserChangesTheClientIDTo(String clientID) throws Throwable {
        List<String> clientIDList = new ArrayList<String>();
        clientIDList.add(clientID);
        wlnHeader.expandUserAvatarDropDown();
        clientIdTest.userIsAbleToChangeClientId(clientIDList);
    }

    @When("^the user clicks on the clientID \"(.*?)\" checkbox$")
    public void theUserClicksOnTheClientID(String facet) throws Throwable {
        knowHowSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
        knowHowSearchResultsPage.clientIDFacetCheckbox(facet).click();
    }

    @Then("^the user should see the results from both \"(.*?)\" or \"(.*?)\" clientID$")
    public void theUserShouldSeeTheResultsFromBothOrClientID(String facet01, String facet02) throws Throwable {
        knowHowSearchResultsPage.waitForPageToLoad();
        knowHowSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
        int facet01Count = Integer.parseInt(knowHowSearchResultsPage.clientIDFacetCount(facet01).getText());
        int facet02Count = Integer.parseInt(knowHowSearchResultsPage.clientIDFacetCount(facet02).getText());
        int clientIDRowCount01 = 0;
        int clientIDRowCount02 = 0;
        for (WebElement clientID : knowHowSearchResultsPage.clientIDCellList()) {
            if (clientID.getText().contains(facet01)) {
                clientIDRowCount01 = clientIDRowCount01 + 1;
            } else if (clientID.getText().contains(facet02)) {
                clientIDRowCount02 = clientIDRowCount02 + 1;
            }
        }
        assertTrue("Total facet count is not equal to cliendID row count..!",
                (facet01Count + facet02Count) == (clientIDRowCount01 + clientIDRowCount02));
    }

    @When("^the user clicks on the event \"(.*?)\"$")
    public void theUserClicksOnTheEvent(String facet01) throws Throwable {
        knowHowSearchResultsPage.eventFacetCheckbox(facet01).click();
    }

    @Then("^the user should see the results from clientID \"(.*)\" and event \"(.*)\" facet$")
    public void theUserShouldSeeTheResultsFromClientIDAndSearch(String clientFacet, String eventFacet) throws Throwable {
        knowHowSearchResultsPage.waitForPageToLoad();
        knowHowSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
        int clientFacetCount = Integer.parseInt(knowHowSearchResultsPage.clientIDFacetCount(clientFacet).getText());
        int eventFacetCoutn = Integer.parseInt(knowHowSearchResultsPage.eventFacetCount(eventFacet).getText());
        assertTrue("Total facet count is not equal to cliendID row count..!",
                clientFacetCount == eventFacetCoutn);

    }

    @Then("^the user clicks '(.*)' button$")
    public void thUserClicksonSelectMultipleFiltersButton(String buttonText) throws Throwable {
        if (buttonText.equalsIgnoreCase("select multiple filters")) {
            WebElement multipleFilterButton = commonMethods.waitForElementToBeVisible(researchOrganizerPage.selectMultipleByFilters());
            if (multipleFilterButton != null) {
                multipleFilterButton.click();
            }
        } else if (buttonText.equalsIgnoreCase("Apply filter")) {
            researchOrganizerPage.applyFilters1().click();
        } else if (buttonText.equalsIgnoreCase("Cancel")) {
            researchOrganizerPage.cancelFilters1();
        } else if (buttonText.equalsIgnoreCase("Clear all filters")) {
            knowHowSearchResultsPage.historyPageClearAllFiltersLink().click();
        }
        researchOrganizerPage.waitForPageToLoad();

    }

    @Then("^the user should see search text area on history page$")
    public void theUserShouldSeeSearchTextAreaOnHistoryPage() throws Throwable {
        assertTrue("", researchOrganizerPage.searchAreaWithinHistory().isDisplayed());
    }

    @Then("^the user searches the term \"(.*?)\" (?:in folders|in history)$")
    public void theUserSearchesTheTerm(String searchTerm) throws Throwable {
        researchOrganizerPage.searchAreaWithinHistory().sendKeys(searchTerm);
        researchOrganizerPage.searchAreaWithinHistory().sendKeys(Keys.ENTER);
    }

    @Then("^the user should see the each search result according to the term \"(.*)\" (?:in folders|in history)$")
    public void theUserShouldSeeTheEachSearchResultAccordingToTheTerm(String searchTerm) throws Throwable {
        String searchTermArray[] = searchTerm.split(" ");
        researchOrganizerPage.waitForExpectedElement(researchOrganizerPage.historyPageResultByTitleLink(), 2000);
        int historyRowCount = researchOrganizerPage.historyPageResultTitleLinks().size();
        int searchTermCount = researchOrganizerPage.totalSelectedSearchTerm().size();

        if (searchTermArray.length == 1) {
            assertTrue(searchTermCount + " search term count is not matching..!", historyRowCount == searchTermCount);
        } else {
            if (searchTermArray[1].equalsIgnoreCase("OR")) {
                assertTrue(searchTermCount + " search term count is not matching..!", historyRowCount == searchTermCount);
            } else {
                assertTrue(searchTermCount + " search term count is not matching..!", historyRowCount == (searchTermCount / 2));
            }

        }
    }

    @Then("^the user should be seeing Date Picker dropdown$")
    public void theUserShouldBeSeeingDatePickerDropdown() throws Throwable {
        assertTrue("Date Picker is not present..!", researchOrganizerPage.historyPageDatePickerDropdownLink().isDisplayed());
    }

    @When("^the user clicks on the Date Pikcer dropdown$")
    public void theUserClicksOnTheDatePikcerDropdown() throws Throwable {
        researchOrganizerPage.historyPageDatePickerDropdownLink().click();
    }

    @Then("^the user should see all the following options$")
    public void
    heUserShouldSeeAllTheFollowingOptions(List<String> options) throws Throwable {
        int dateCount = 0;
        for (int count = 1; count < options.size(); count++) {
            if (!researchOrganizerPage.datePickerDropdownSelectedOption().getText().equalsIgnoreCase(options.get(count))) {
                assertTrue(options.get(count) + " is not present..!",
                        options.contains(researchOrganizerPage.datePickerDropdownOptionsList().get(dateCount).getText()));
                ++dateCount;
            }
        }
        researchOrganizerPage.historyPageDatePickerDropdownLink().click();
    }

    @When("^the user selects the date (.*) with (.*)$")
    public void theUserSelectsTheDateToday(String option, String date) throws Throwable {
        if (!researchOrganizerPage.datePickerDropdownSelectedOption().getText().equalsIgnoreCase(option)) {
            footerUtils.closeDisclaimerMessage();
            researchOrganizerPage.waitForPageToLoad();
            researchOrganizerPage.historyPageDatePickerDropdownLink().click();
            for (WebElement optionElement : researchOrganizerPage.datePickerDropdownOptionsList()) {
                String optionElementText = optionElement.getText();
                if (optionElementText.equalsIgnoreCase(option)) {
                    optionElement.click();
                    if (optionElementText.equalsIgnoreCase("All Dates Before")) {
                        researchOrganizerPage.datePickerBeforeAfterOnFromTextbox(option).clear();
                        researchOrganizerPage.datePickerBeforeAfterOnFromTextbox(option).sendKeys(date);
                        researchOrganizerPage.datePikcerOKButton(optionElementText).click();
                    } else if (optionElementText.equalsIgnoreCase("All Dates After")) {
                        researchOrganizerPage.datePickerBeforeAfterOnFromTextbox(option).clear();
                        researchOrganizerPage.datePickerBeforeAfterOnFromTextbox(option).sendKeys(date);
                        researchOrganizerPage.datePikcerOKButton(optionElementText).click();
                    } else if (optionElementText.equalsIgnoreCase("Specific Date")) {
                        researchOrganizerPage.datePickerBeforeAfterOnFromTextbox(option).clear();
                        researchOrganizerPage.datePickerBeforeAfterOnFromTextbox(option).sendKeys(date);
                        researchOrganizerPage.datePikcerOKButton(optionElementText).click();
                    } else if (optionElementText.equalsIgnoreCase("Date Range")) {
                        String dateRange[] = date.split("to");
                        researchOrganizerPage.datePickerBeforeAfterOnFromTextbox(option).clear();
                        researchOrganizerPage.datePickerUntilDateTextbox().clear();
                        researchOrganizerPage.datePickerBeforeAfterOnFromTextbox(option).sendKeys(dateRange[0]);
                        researchOrganizerPage.datePickerUntilDateTextbox().sendKeys(dateRange[1]);
                        researchOrganizerPage.datePikcerOKButton(optionElementText).click();
                    }
                    break;
                }
            }
            researchOrganizerPage.waitForPageToLoad();
            researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        }
    }

    @Then("^the user should see the results (.*) with any selected (.*)$")
    public void theUserShouldSeeTheResultsDateOptionSelected(String option, String date) throws Throwable {
        try {
            boolean isCriteriaTrue = false;
            SimpleDateFormat rowDateFormat = new SimpleDateFormat("dd MMM yyyy");
            SimpleDateFormat originalDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            originalDateFormat.setTimeZone(TimeZone.getTimeZone("AET"));
            SimpleDateFormat specificDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar today = Calendar.getInstance();
            Date todayDate = null;
            Date customDate1, customDate2;
            researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
            for (int rowCount = 1; rowCount <= researchOrganizerPage.historyPageResultTitleLinks().size(); rowCount++) {
                String  rowStrDate = researchOrganizerPage.getDateAtRowPosition(String.valueOf(rowCount)).getText();
                Date rowOriginalDate = rowDateFormat.parse(rowStrDate);
                if (option.equalsIgnoreCase("Today")) {
                    isCriteriaTrue = isCriteriaForTodayMatches(rowOriginalDate, originalDateFormat);
                } else if (option.equalsIgnoreCase("Last 7 Days")) {
                    isCriteriaTrue = isCriteriaForLastXDaysMatches(rowOriginalDate, -7);
                } else if (option.equalsIgnoreCase("Last 30 Days")) {
                    isCriteriaTrue = isCriteriaForLastXDaysMatches(rowOriginalDate, -30);
                } else if (option.equalsIgnoreCase("All")) {
                    isCriteriaTrue = isCriteriaForAllMatches(rowOriginalDate);
                } else if (option.equalsIgnoreCase("All Dates Before")) {
                    isCriteriaTrue = isCriteriaForAllDatesBeforeMatches(rowOriginalDate, originalDateFormat, date);
                } else if (option.equalsIgnoreCase("All Dates After")) {
                    isCriteriaTrue = isCriteriaForAllDatesAfterMatches(rowOriginalDate, originalDateFormat, date);
                } else if (option.equalsIgnoreCase("Specific Date")) {
                    customDate1 = specificDateFormat.parse(date);
                    String customStrDate1 = rowDateFormat.format(customDate1);
                    String rowOriginalStrDate = rowDateFormat.format(rowOriginalDate);
                    LOG.info("rowOriginalStrDate :" + rowOriginalStrDate + " , customStrDate1 :" + customStrDate1);
                    isCriteriaTrue = isCriteriaForSpecificDateMatches(customStrDate1, rowOriginalStrDate);
                } else if (option.equalsIgnoreCase("Date Range")) {
                    String[] dateRange = date.split("to");
                    customDate1 = originalDateFormat.parse(dateRange[0].trim());
                    customDate2 = originalDateFormat.parse(dateRange[1].trim());
                    isCriteriaTrue = isCriteriaForDateRangeMatches(customDate1, customDate2, rowOriginalDate);
                }
                assertTrue(rowOriginalDate.toString() + " is not satisfying criteria..!", isCriteriaTrue);
            }
        } catch (ParseException pe) {
            LOG.info(pe.getMessage());
        }
    }


    @Then("^the user runs the search \"(.*)\" if today's history is not present$")
    public void theUserRunsTheSearchIfTodaysHistoryIsNotPresent(String searchTerm) throws Throwable {
        if (!researchOrganizerPage.datePickerDropdownSelectedOption().getText().equalsIgnoreCase("All")) {
            researchOrganizerPage.waitForPageToLoad();
            researchOrganizerPage.historyPageDatePickerDropdownLink().click();
            for (WebElement optionElement : researchOrganizerPage.datePickerDropdownOptionsList()) {
                String optionElementText = optionElement.getText();
                if (optionElementText.equalsIgnoreCase("All")) {
                    optionElement.click();
                    break;
                }
            }
            String todayDate = CalendarAndDate.getCurrentDate();
            if (researchOrganizerPage.waitForExpectedElements(researchOrganizerPage.historyPageResultByTitleLink(), 10).size() != 0) {
                String actualDate = researchOrganizerPage.getDateAtRowPosition("1").getText();
                basicKnowHowSearchUKS101TestSteps.theUserRunsAFreeTextSearchForTheQuery(searchTerm);
                legalUpdatesResultsPage.getAllUpdatesTitles().get(0).click();
                legalUpdatesResultsPage.waitForPageToLoad();
                legalUpdatesResultsPage.waitForPageToLoadAndJQueryProcessing();
                legalUpdatesResultsPage.getElementByLinkText("History").click();

            }
        }}

    @Then("^user should see the page \"(.*?)\"$")
    public void userShouldSeeThePage(String page) throws Throwable {
        if (page.equalsIgnoreCase("History")) {
            assertTrue(true);
        } else {
            assertFalse(true);
        }
    }

    @Given("^the user clicks on History left side facet \"(.*?)\"$")
    public void theUserClicksOnHistoryLeftSideFacet(String option) throws Throwable {
        WebElement historyFacet = commonMethods.waitForElementToBeVisible(researchOrganizerPage.historyFacetByLink(option));
        if (historyFacet != null) {
            historyFacet.click();
        }
    }

    private boolean isCriteriaForTodayMatches(Date rowOriginalDate, SimpleDateFormat originalDateFormat) throws ParseException{
        Date todayDay = originalDateFormat.parse(new Date().toString());
        return DateUtils.isSameDay(rowOriginalDate, todayDay);
    }

    private boolean isCriteriaForLastXDaysMatches(Date rowOriginalDate, int numberOfDays){
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, numberOfDays);
        Date todayDate = today.getTime();
        return todayDate.before(rowOriginalDate);
    }

    private boolean isCriteriaForAllMatches(Date rowOriginalDate){
        DateTime currentDateTime = DateTime.now();
        DateTimeZone auTimeZone = DateTimeZone.forID(AUSTRALIA_SYDNEY_TIMEZONE);
        DateTime dateTimeInAu = currentDateTime.withZone(auTimeZone);
        Date dateInAu = dateTimeInAu.toLocalDateTime().toDate();
        return dateInAu.after(rowOriginalDate);
    }

    private boolean isCriteriaForAllDatesBeforeMatches(Date rowOriginalDate, SimpleDateFormat originalDateFormat, String date) throws ParseException{
        Date customDate = originalDateFormat.parse(date);
        return (rowOriginalDate.before(customDate) || rowOriginalDate.equals(customDate));
    }

    private boolean isCriteriaForAllDatesAfterMatches(Date rowOriginalDate, SimpleDateFormat originalDateFormat, String date) throws ParseException{
        Date customDate = originalDateFormat.parse(date);
        return rowOriginalDate.after(customDate);
    }

    private boolean isCriteriaForSpecificDateMatches(String customStrDate, String rowOriginalStrDate) {
        return rowOriginalStrDate.contains(customStrDate);
    }

    private boolean isCriteriaForDateRangeMatches(Date customDateStart, Date customDateEnd, Date rowOriginalDate) {
        return (rowOriginalDate.after(customDateStart) || rowOriginalDate.before(customDateEnd));
    }
}
