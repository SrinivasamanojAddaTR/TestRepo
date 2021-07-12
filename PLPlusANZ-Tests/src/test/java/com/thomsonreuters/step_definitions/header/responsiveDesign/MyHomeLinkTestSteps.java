package com.thomsonreuters.step_definitions.header.responsiveDesign;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyHomeLinkTestSteps extends BaseStepDef {

    private WLNHeader header = new WLNHeader();
    private PracticalLawHomepage homePage = new PracticalLawHomepage();

    @Then("^user should see the \"(.*?)\" link$")
    public void userShouldSeeLink(String linkText) throws Throwable {
        assertTrue(linkText + " link not present..!", homePage.isElementDisplayed(homePage.getElementByLinkText(linkText)));
    }

    @Then("^user should see United Kingdom as a home page$")
    public void userShouldSeeUnitedKingdomAsAHomePage() throws Throwable {
        String header1 = header.pageHeaderLabel().getText();
        assertTrue("United Kingdom page not displayed..!", header1.contains("United Kingdom"));
    }

    @Then("^user should not see the (.*?) link$")
    public void userShouldNotSeeTheLink(String linkText) throws Throwable {
        assertFalse(linkText + " is present..!", homePage.isExists(By.linkText(linkText)));
    }

    @Then("^user should see default PLCUK home page$")
    public void userShouldSeePLCUKBrowseAsAHomePage() throws Throwable {
        assertTrue("PLCUK home page not displayed..!", homePage.browseWidget().isDisplayed());
    }

}