package com.thomsonreuters.step_definitions.search;

import com.thomsonreuters.pageobjects.pages.landing_page.SearchScopeControl;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by UC181137 on 23/10/2015.
 */
public class AnzPreFilteredScopedSearchTest extends BaseStepDef {

    private SearchScopeControl searchScopeControl = new SearchScopeControl();

    @When("^the scoped search drop down options should provide the following areas$")
    public void theUserObtainsTheTitleOfTheFirstResultAndStoresIt(List<String> preScopedSearchFiltersList) throws Throwable {

        for(String dropdownOption : preScopedSearchFiltersList){
             assertTrue(dropdownOption+" is not displayed..!", searchScopeControl.scopedSearchDropdownOptions(dropdownOption).isDisplayed());
        }
    }


}
