package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class AbilityToSeeLogOutConfirmationPage extends BaseStepDef {

    private WLNHeader wlnHeader = new WLNHeader();
    private OnepassLogin onePassLogin = new OnepassLogin();
    private OnepassLoginUtils onePassLoginUtils = new OnepassLoginUtils();

    @When("^user clicks on Sign out$")
    public void userClicksOnSignOut() throws Throwable {
        wlnHeader.signOff();
        onePassLogin.waitForPageToLoad();
        resetCurrentUser();
    }

    @Then("^user should see Log out confirmation page$")
    public void userShouldSeeLogOutConfirmationPage() throws Throwable {
        assertTrue("User was not redirected to log out page", onePassLogin.getPageTitle().contains("Sign Off | Practical Law"));
    }

    @Then("^user should see Sign On button on this page$")
    public void userShouldSeeSignOnButtonOnThisPage() throws Throwable {
        assertTrue("Sign on Back button is absent", onePassLoginUtils.isSignBackOnButtonPresent());
    }

    @Then("^user should see session summary on this page$")
    public void userShouldSeeSessionSummaryOnThisPage() throws Throwable {
        assertTrue("Session summary box is absent", onePassLoginUtils.isSessionSummaryBoxPresent());
    }

    @Then("^user should see a branding logo on Log out screen$")
    public void userShouldSeeABrandingLogoOnLogOutScreen() throws Throwable {
        assertTrue("Branding logo on Log Out screen is absent or incorrect", onePassLoginUtils.isLogOutBrandingLogoPresent());
    }

}