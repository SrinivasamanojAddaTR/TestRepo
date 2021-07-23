package com.thomsonreuters.step_definitions.search;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.When;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by UC181137 on 23/10/2015.
 */
public class AnzSearchConnectorsGuideTestSteps extends BaseStepDef {

    private WLNHeader header = new WLNHeader();

    @When("^user should see the following character with its related explanation on the popup$")
    public void theUserObtainsTheTitleOfTheFirstResultAndStoresIt(Map<String,String> connectorsMap) throws Throwable {
        int charPostion = 0;
        for (Map.Entry<String,String> keyEntry : connectorsMap.entrySet()) {
           if(keyEntry.getKey().equalsIgnoreCase("Character")) {
               continue;
           }
            assertTrue(keyEntry.getKey() + " not Found..!", header.connectorsCharactersList().get(charPostion).getText().trim().equalsIgnoreCase(keyEntry.getKey()));
            assertTrue(keyEntry.getValue() + " not Found..!", header.connectorsExplanationList().get(charPostion).getText().trim().equalsIgnoreCase(keyEntry.getValue()));
         charPostion++;
        }
        header.searchGuideCloseButton().click();
    }


}
