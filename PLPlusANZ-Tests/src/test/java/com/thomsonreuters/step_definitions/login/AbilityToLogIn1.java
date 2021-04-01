package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.common.ExcelFileReader;
import com.thomsonreuters.pageobjects.pages.footer.WLNFooter;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.DocumentNavigationPage;
import com.thomsonreuters.pageobjects.utils.CobaltUser;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.Transpose;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbilityToLogIn1 extends BaseStepDef {

    private WLNFooter wlnFooter = new WLNFooter();
    private WLNHeader wlnHeader = new WLNHeader();
    private OnepassLogin onepassLogin = new OnepassLogin();
    private OnepassLoginUtils onepassLoginUtils =  new OnepassLoginUtils();
    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private DocumentNavigationPage documentNavigationPage = new DocumentNavigationPage();

    private String pageTitleForNotLoggedInUser = "";

    @When("^the user clicks on Sign On link on the header$")
    public void theUserClicksOnSignOnLinkOnTheHeader() throws Throwable {
        wlnHeader.signInLink().click();
        onepassLogin.waitForPageToLoad();
    }

    @When("^the user clicks on Sign in with a Different Account link on the header$")
    public void theUserClicksOnSignInWithADifferentAccountLinkOnTheHeader() throws Throwable {
        wlnHeader.signInWithDifferentAccount();
        onepassLogin.waitForPageToLoad();
    }

    @Then("^the user is able to sign in with OnePass$")
    public void theUserIsAbleToSignInWithOnePass(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        currentUser.setCurrentUser(CobaltUser.updateMissingFields(plPlusUserList.get(0)));
        onepassLoginUtils.loginToCobalt(currentUser.getUserName(), ExcelFileReader.getCobaltPassword(currentUser.getUserName()));
        practicalLawHomepage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^profile icon is shown to user$")
    public void isProfileIconPresent() {
        assertTrue("Profile icon is not visible.", wlnHeader.isUserAvatarIconPresent());
    }

    @Then("^the user clicks on Sign In link on the footer$")
    public void theUserClicksOnSignInLinkOnTheFooter() throws Throwable {
        wlnFooter.signInLink().click();
        onepassLogin.waitForPageToLoad();
    }

    @Then("^Sign In link is not shown to user$")
    public void signInLinkIsNotShownToUser() throws Throwable {
        assertFalse("Sign In link is visible", wlnFooter.isSignInLinkPresent());
    }

    @When("^the user clicks on Sign in button on the document$")
    public void theUserClicksOnSignInButtonOnTheDocument() throws Throwable {
        pageTitleForNotLoggedInUser = documentNavigationPage.getPageTitle();
        documentNavigationPage.signInButton().click();
        onepassLogin.waitForPageToLoad();
    }

    @When("^continue to login with \"(.*?)\" RegKey$")
    public void continueToLoginWithRegKey(String regKey) throws Throwable {
        onepassLoginUtils.chooseRegKey(regKey);
        practicalLawHomepage.waitForPageToLoadAndJQueryProcessing();

    }

    @Then("^the user should be redirected to a page with the document$")
    public void theUserShouldBeRedirectedToAPageWithTheDocument() throws Throwable {
        assertTrue("User was not redirected to a document page after login", documentNavigationPage.getPageTitle().equalsIgnoreCase(pageTitleForNotLoggedInUser));
    }

    @Then("^Sign In button is not shown to user$")
    public void signInButtonIsNotShownToUser() throws Throwable {
        assertFalse("Sign In button is visible", documentNavigationPage.isSignInButtonPresent());
    }

    @Then("^Sign In button is shown to user$")
    public void signInButtonIsShownToUser() throws Throwable {
        assertTrue("Sign In button is NOY visible", documentNavigationPage.isSignInButtonPresent());
    }

    @When("^the user enters their username \"(.*?)\" and password \"(.*?)\"$")
    public void theUserEntersTheirUsernameAndPassword(String userName, String password) throws Throwable {
        onepassLoginUtils.enterUserNameAndPassword(userName, password);
    }

    @When("^the user selects sign in$")
    public void theUserSelectsSignIn() throws Throwable {
        onepassLogin.signOnButton().click();
        onepassLogin.waitForPageToLoad();
    }

    @Then("^the user is presented with the following \"(.*?)\" error message \"(.*?)\"$")
    public void theUserIsPresentedWithTheFollowingErrorMessage(String messageType, String errorMessage) throws Throwable {
        if ("OnePass".equals(messageType)) {
            assertTrue("OnePass error message is not displayed", onepassLogin.isOnePassValidationErrorDisplayed(errorMessage));
        } else
            assertTrue("Validation error message is not displayed", onepassLogin.isUsernameValidationErrorDisplayed(errorMessage));
    }

    @After(order = 40000, value = "@signOffFromUsernameAndPasswordUserToBecameIPUser")
    public void becameAnIpUserAfterUSernameAndPasswordUser() {
        wlnHeader.signOff();
        onepassLogin.waitForPageToLoad();
        resetCurrentUser();
    }

}