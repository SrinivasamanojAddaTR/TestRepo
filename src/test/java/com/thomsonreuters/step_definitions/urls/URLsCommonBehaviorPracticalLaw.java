package com.thomsonreuters.step_definitions.urls;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.When;

public class URLsCommonBehaviorPracticalLaw extends BaseStepDef {

    private NavigationCobalt navigationCobalt = new NavigationCobalt();

    public static final String oldDomain = "http://uk.practicallaw.com";

    @When("^the user opens (.+) on the old practical law website$")
    public void theUserOpensURLOnTheOldPracticalLawWebsite(String url) throws Throwable {
        if (url.startsWith("/")) {
            url = oldDomain + url;
        }
        navigationCobalt.navigate(url);
    }

}