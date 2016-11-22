package com.thomsonreuters.step_definitions.uk.legalUpdates.web;

import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesResultsPage;
import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesWidget;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class prescopingFacetsAndSortingByDateOnLUResultsPages extends BaseStepDef {

    private LegalUpdatesWidget luWidget = new LegalUpdatesWidget();
    private LegalUpdatesResultsPage legalUpdatesResultsPage = new LegalUpdatesResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();

    private List<Date> actualDates;
    private List<Date> expectedDates;

    @When("^the user clicks on the 'View all' link of the LU widget$")
    public void theUserClicksOnTheViewAllLinkOfTheLUWidget() throws Throwable {
        luWidget.veiwAllUpdatesLink().click();
        legalUpdatesResultsPage.waitForPageToLoad();
    }

    @When("^the user clicks on the 'View all' link of the \"(.*?)\" widget$")
    public void theUserClicksOnTheViewAllLinkOfTheWidget(String widgetName) throws Throwable {
        luWidget.veiwAllUpdatesLink(widgetName).click();
        legalUpdatesResultsPage.waitForPageToLoad();
    }

    @Then("^the user should be presented with a list of LU documents$")
    public void theUserShouldBePresentedWithAListOfLUDocuments() throws Throwable {
        legalUpdatesResultsPage.waitForPageToLoadAndJQueryProcessing();
        assertTrue("Results list is not displayed", legalUpdatesResultsPage.isResultsListDisplayed());
    }

    @Then("^the results should be from the relevant PA \"(.*?)\"$")
    public void theResultsShouldBeFromTheRelevantPA(String practiceAreaTag) throws Throwable {
        legalUpdatesResultsPage.waitForPageToLoadAndJQueryProcessing();
        assertTrue("Results are not from relevant PA. Expected PA: " + practiceAreaTag + " actual PA: " + legalUpdatesResultsPage.headerMetaDataTagText(), legalUpdatesResultsPage.headerMetaDataTagText().contains(practiceAreaTag));
    }

    @Then("^the 'Practice Area' facets should be pre-scoped to the \"(.*?)\" PA that the user had come from$")
    public void thePracticeAreaFacetsShouldBePreScopedToThePAThatTheUserHadComeFrom(String practiceArea) throws Throwable {
        assertTrue("Prescoped value for practice are is incorrect. Actual value: " + legalUpdatesResultsPage.getfacetSubTitleText(), legalUpdatesResultsPage.getfacetSubTitleText().contains(practiceArea));
    }

    @Then("^the user should be displayed the child topics of that practice area$")
    public void theUserShouldBeDisplayedTheChildTopicsOfThatPracticeArea() throws Throwable {
        assertTrue("Child topics menu is not displayed", legalUpdatesResultsPage.isChildTopicsFacetsDisplayed());
    }

    @Then("^the user should be able to select the child topics of that practice area to filter the results$")
    public void theUserShouldBeAbleToSelectTheChildTopicsOfThatPracticeAreaToFilterTheResults() throws Throwable {
        int result = 0;
        knowHowSearchResultsPage.clickOnSelectMultipleFilters();
        List<WebElement> childFacetsCheckboxes = legalUpdatesResultsPage.allPAFacetsChildCheckBoxes();
        for (WebElement chekcbox : childFacetsCheckboxes)
            if (!legalUpdatesResultsPage.isCheckBoxSelectable(chekcbox)) {
                result++;
            }
        assertTrue("One of checkboxes for is not editable", result == 0);
    }

    @Then("^the results should be sorted by date \\(most recent first\\)$")
    public void theResultsShouldBeSortedByDateMostRecentFirst() throws Throwable {
        actualDates = legalUpdatesResultsPage.getPublishingDatesFromStatuses(legalUpdatesResultsPage.legalUpdatesStatuses());
        expectedDates = actualDates;
        Collections.sort(expectedDates, Collections.reverseOrder());
        assertTrue("Dates on UI are not equal with sorted dates", actualDates.equals(expectedDates));
    }

    @Then("^the user should be taken to the \"(.*?)\" Topic LU results list$")
    public void theUserShouldBeTakenToTheTopicLUResultsList(String topicName) throws Throwable {
        assertTrue("Topic name is incorrect. Actual: " + legalUpdatesResultsPage.headerMetaDataTagText(), legalUpdatesResultsPage.headerMetaDataTagText().contains(topicName));
    }

    @Then("^the 'Practice Area' facets should be pre-scoped to the \"(.*?)\" Topic that the user had come from$")
    public void thePracticeAreaFacetsShouldBePreScopedToTheTopicThatTheUserHadComeFrom(String topicName) throws Throwable {
        assertTrue("Prescoped value for topic is incorrect. Actual is: " + legalUpdatesResultsPage.getfacetSubTitleText(), legalUpdatesResultsPage.getfacetSubTitleText().contains(topicName));
    }

    @Then("^the user should not be presented with the sort order drop down$")
    public void theUserShouldNotBePresentedWithTheSortOrderDropDown() throws Throwable {
        assertFalse("Sort dropdown is visible", legalUpdatesResultsPage.isSortDropDownDisplayed());
    }

    @Then("^the user should not be presented with any 'Resource type' faceting$")
    public void theUserShouldNotBePresentedWithAnyResourceTypeFaceting() throws Throwable {
        assertFalse("Resource Type faceting is visible", legalUpdatesResultsPage.isResourceTypeFilterDisplayed());
    }

}