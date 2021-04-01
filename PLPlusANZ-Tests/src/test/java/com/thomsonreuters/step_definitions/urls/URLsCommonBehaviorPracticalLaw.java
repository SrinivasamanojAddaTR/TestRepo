package com.thomsonreuters.step_definitions.urls;

import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.When;

public class URLsCommonBehaviorPracticalLaw extends BaseStepDef {

    private HomePage homePage = new HomePage();

    public static final String oldDomain = "http://uk.practicallaw.com";

    @When("^the user opens (.+) on the old practical law website$")
    public void theUserOpensURLOnTheOldPracticalLawWebsite(String url) throws Throwable {
        if (url.startsWith("/")) {
            url = oldDomain + url;
        }
        homePage.navigate(url);
    }

}