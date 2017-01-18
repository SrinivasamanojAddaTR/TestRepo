package com.thomsonreuters.step_definitions.uk.legalUpdates.web;

import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesResultsPage;
import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesWidgetPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LegalUpdatesWidget extends BaseStepDef {

    private LegalUpdatesWidgetPage luWidget = new LegalUpdatesWidgetPage();
    private LegalUpdatesResultsPage legalUpdatesResultsPage = new LegalUpdatesResultsPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();

    private List<String> updatesTitles;
    private List<String> updatesDates;

    @Given("^the user should see (\\d+) updates on a \"(.*?)\" widget$")
    public void theUserShouldSeeUpdatesOnAWidget(int titlesCount, String widgetName) throws Throwable {
        updatesTitles = luWidget.getAll5UpdatesTitles(widgetName);
        assertTrue("Titles count is more or less than 5. Actual titles count: " + updatesTitles.size(), updatesTitles.size() == titlesCount);
    }
    

    @Given("^\"(.*?)\" widget should display publication dates of documents$")
    public void widgetShouldDisplayPublicationDatesOfDocuments(String widgetName) throws Throwable {
        int result = 0;
        updatesDates = luWidget.getAllDatesFromWidget(widgetName);
        for (String date : updatesDates) {
            if (date.isEmpty()) {
                result++;
            }
        }
        assertTrue("Some dates from widget are not visible", updatesTitles.size() == updatesDates.size() && result == 0);
    }

    @Then("^the user should see first five updates same as on widget$")
    public void theUserShouldSeeFirstFiveUpdatesSameAsOnWidget() throws Throwable {
        knowHowSearchResultsPage.waitForSearchResults();
        List<String> luTitlesFromResultsPage = legalUpdatesResultsPage.getFirstLU5Titles();
        assertTrue("First 5 updates on results page are inconsistent with updates from widget.Updates from widget: " + updatesTitles.toString() + " updates from LU page: " + luTitlesFromResultsPage.toString(), luTitlesFromResultsPage.containsAll(updatesTitles));
    }
    
    @Then("^the user is not presented with the Legal Updates widget$")
    public void theUserIsPresentedWithTheLegalUpdatesWidget() throws Throwable {
        Assert.assertFalse("Legal Updates Widget is displayed", luWidget.isLUWidgetPresent());
    }
    
    @Then("^Out of plan badge appears on the right top of the link$")
    public void theOutOfPlanMarkIsPresent() throws Throwable {
        assertTrue("Out of plan mark is not displayed", luWidget.isOutOfPlanPresent());
    }

}