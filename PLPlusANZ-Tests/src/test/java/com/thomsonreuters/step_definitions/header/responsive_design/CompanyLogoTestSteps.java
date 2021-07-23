package com.thomsonreuters.step_definitions.header.responsive_design;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class CompanyLogoTestSteps extends BaseStepDef {

    private WLNHeader header = new WLNHeader();

    @Then("^user should see company logo$")
    public void userShouldSeeCompanyLogo() throws Throwable {
        Assert.assertTrue("Company logo not displayed..!", header.companyLogo().isDisplayed());
    }

    @Then("^user clicks on company logo$")
    public void userClicksOnCompanyLogo() throws Throwable {
        header.companyLogo().click();
    }

    @Then("^user should see \"(.*)\" page$")
    public void userShouldSeeHomePage(String pageTitle) throws Throwable {
        Assert.assertTrue(pageTitle+" page not displayed..!", header.pageHeaderLabel().getText().trim().contains(pageTitle));
    }

}