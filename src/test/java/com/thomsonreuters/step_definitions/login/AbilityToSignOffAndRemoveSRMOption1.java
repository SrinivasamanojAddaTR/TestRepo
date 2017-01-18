package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.onePass.OnePassLogoutPage;
import com.thomsonreuters.pageobjects.utils.CobaltUser;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class AbilityToSignOffAndRemoveSRMOption1 extends BaseStepDef {

    private WLNHeader wlnHeader = new WLNHeader();
    private OnepassLogin onePassLogin = new OnepassLogin();
    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private NavigationCobalt navigationCobalt = new NavigationCobalt();
	private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();

    @When("^this logged in user clicks on Sign off$")
    public void thisLoggedInUserClicksOnSignOff() throws Throwable {
        //TODO: this is a workaround to click sign off twice before 'Return to Sign In' Link appears it is by design at the moment
        //confirmed by Robert Foster and Jacqueline Auma
        wlnHeader.signOff();
        onePassLogin.waitForPageToLoad();
        onePassLogoutPage.signOffPageSignOnButton().click();
        practicalLawHomepage.waitForPageToLoad();
        wlnHeader.signOff();
        onePassLogin.waitForPageToLoad();
    }

    @When("^he is redirected to a log out confirmation page$")
    public void heIsRedirectedToALogOutConfirmationPage() throws Throwable {
        assertTrue("User was not redirected to log out page", onePassLogin.getPageTitle().contains("Sign Off | Practical Law"));
    }

    @Then("^user selects the option to forget the remember me cookie$")
    public void userSelectsTheOptionToForgetTheRememberMeCookie() throws Throwable {
    	onePassLogoutPage.signInWithDifferentAccountLink().click();
        practicalLawHomepage.waitForPageToLoad();
        currentUser = new CobaltUser();
    }

    @Then("^the next time that he tries to connect to PL\\+$")
    public void theNextTimeThatHeTriesToConnectToPL() throws Throwable {
        navigationCobalt.navigateToPLUKPlus();
        practicalLawHomepage.waitForPageToLoad();
    }

    @Then("^he should be logged out from system$")
    public void heWillBePromptedToEnterHisUsernameAndPasswordAgain() throws Throwable {
        assertTrue("User was not logged out", wlnHeader.isSignInLinkPresent());
    }

}