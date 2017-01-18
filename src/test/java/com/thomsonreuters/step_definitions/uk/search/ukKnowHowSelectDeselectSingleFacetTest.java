package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Created by Steph Armytage on 16/02/2015.
 */
public class UKKnowHowSelectDeselectSingleFacetTest extends BaseStepDef {

    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();

    @When("^the user selects the know how option to apply filters$")
    public void theUserSelectsTheKnowHowOptionToApplyFilters() throws Throwable {
        ((JavascriptExecutor) getDriver()).executeScript("scroll(250,0);");
        knowHowSearchResultsPage.applyFiltersButton().click();
        knowHowSearchResultsPage.waitForSearchResults();
    }

}
