package com.thomsonreuters.step_definitions.header.responsiveDesign;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.login.WLNextHomePage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyHomeLinkTest extends BaseStepDef {

    private CommonMethods comMethods = new CommonMethods();
    private WLNHeader header = new WLNHeader();
    private WLNextHomePage homePage = new WLNextHomePage();

    @Then("^user should see the \"(.*?)\" link$")
    public void userShouldSeeLink(String linkText) throws Throwable {
        assertTrue(linkText + " link not present..!", comMethods.waitElementByLinkText(linkText) != null);
    }

    @Then("^user should see United Kingdom as a home page$")
    public void userShouldSeeUnitedKingdomAsAHomePage() throws Throwable {
        String header1 = header.pageHeaderLabel().getText();
        assertTrue("United Kingdom page not displayed..!", header1.contains("United Kingdom"));
    }

    @Then("^user should not see the (.*?) link$")
    public void userShouldNotSeeTheLink(String linkText) throws Throwable {
        assertFalse(linkText + " is present..!", comMethods.isExists(By.linkText(linkText)));
    }

    @Then("^user should see default PLCUK home page$")
    public void userShouldSeePLCUKBrowseAsAHomePage() throws Throwable {
        assertTrue("PLCUK home page not displayed..!", homePage.browseWidget().isDisplayed());
    }

}