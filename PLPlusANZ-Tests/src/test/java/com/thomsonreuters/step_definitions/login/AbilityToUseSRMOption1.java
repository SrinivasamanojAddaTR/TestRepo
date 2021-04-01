package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.ExcelFileReader;
import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.footer.WLNFooter;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.onePass.OnePassLogoutPage;
import com.thomsonreuters.pageobjects.utils.CobaltUser;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbilityToUseSRMOption1 extends BaseStepDef {

    private OnepassLogin onepassLogin = new OnepassLogin();
    private WLNHeader wlnHeader = new WLNHeader();
    private WLNFooter wlnFooter = new WLNFooter();
	private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();
    private OnepassLoginUtils onepassLoginUtils = new OnepassLoginUtils();
    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private CommonMethods commonMethods =new CommonMethods();

    private final String ONEPASS_LOGIN_PAGE_TITLE = "PLC UK Signon";

    @Given("^a username/password user in the login screen$")
    public void aUsernamePasswordUserInTheLoginScreen(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        wlnHeader.signInLink().click();
        CobaltUser plPlusUser = CobaltUser.updateMissingFields(plPlusUserList.get(0));
		String password;
		if (StringUtils.isEmpty(plPlusUser.getUserName())) {
			if ("None".equalsIgnoreCase(System.getProperty("username"))) {
				// using default user from excel - P3
				plPlusUser.setUserName(ExcelFileReader.getDefaultUser());
				password = ExcelFileReader.getCobaltPassword(plPlusUser.getUserName());
			} else {
				// using system settings - P2
				plPlusUser.setUserName(System.getProperty("username"));
				password = System.getProperty("password");
			}
		} else {
			// user from feature and password from excel - P1
			password = ExcelFileReader.getCobaltPassword(plPlusUser.getUserName());
		}
		onepassLoginUtils.enterUserNameAndPassword(plPlusUser.getUserName(), password);
    }

    @When("^he selects the option to be remembered on this computer$")
    public void heSelectsTheOptionToBeRememberedOnThisComputer() throws Throwable {
        commonMethods.clickElementUsingJS(onepassLogin.rememeberMeCheckBox());
    }

    @Then("^he activates the super remember me cookie$")
    public void heActivatesTheSuperRememberMeCookie() throws Throwable {
        onepassLogin.signOnButton().click();
        practicalLawHomepage.waitForPageToLoad();
    }

    @Then("^when the user logs out$")
    public void whenTheUserLogsOut() throws Throwable {
        wlnHeader.signOff();
        currentUser.setCurrentUser(new CobaltUser());
        onepassLogin.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^he clicks the sign in button on the log out page$")
    public void heClicksTheSignInButtonOnTheLogOutPage() throws Throwable {
    	onePassLogoutPage.signOffPageSignOnButton().click();
    }

    @Then("^he tries to access PL\\+$")
    public void heTriesToAccessPL() throws Throwable {
        navigationCobalt.navigateToPLUKPlus();
        practicalLawHomepage.waitForPageToLoad();
    }
    
    @Then("^he tries to access ANZ PL\\+$")
    public void heTriesToAccessANZPL() throws Throwable {
        navigationCobalt.navigateToPLANZPlus();
        practicalLawHomepage.waitForPageToLoad();
    }

    @Then("^he will be automatically authenticated$")
    public void heWillBeAutomaticallyAuthenticated() throws Throwable {
        assertFalse("User is not logged in", wlnFooter.isSignInLinkPresent());
    }

    @Then("^he will not see the Log in page$")
    public void heWillNotSeeTheLogInPage() throws Throwable {
        assertFalse("User is able to see login page", onepassLogin.getPageTitle().contains(ONEPASS_LOGIN_PAGE_TITLE));
    }

    @Then("^there will be two options Resume as user \\[X\\] Or \\[Sign in with a different account\\]$")
    public void thereWillBeTwoOptionsResumeAsUserXOrSignInWithADifferentAccount() throws Throwable {
        assertTrue("Resume as or Sign in with different account links are not available", onepassLoginUtils.isResumeAsCurrentUserLinkPresent() && onepassLoginUtils.isSignInWithDifferentAccountLinkPresent());
    }

    @Then("^the user selects Resume as user \\[X\\]$")
    public void theUserSelectsResumeAsUserX() throws Throwable {
    	onePassLogoutPage.resumeAsCurrentUserLink().click();
    }

}