package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class abilityToHaveLoginPage extends BaseStepDef {

    private OnepassLoginUtils onePassLoginUtils = new OnepassLoginUtils();
    private OnepassLogin onePassLogin = new OnepassLogin();

    @Then("^the user will be redirected to a login page$")
    public void theUserWillBeRedirectedToALoginPage() throws Throwable {
        assertTrue("User is not on login page", onePassLoginUtils.isLoginBoxPresent());
    }

    @Then("^in this login page he will be able to see username and password boxes$")
    public void inThisLoginPageHeWillBeAbleToSeeUsernameAndPasswordBoxes() throws Throwable {
        assertTrue("User is not able to see Username and PAssword fields", onePassLoginUtils.isPasswordTextFieldPresent() && onePassLoginUtils.isUserNameTextFieldPresent());
    }

    @Then("^he will be able to see a link to Forget my username and password$")
    public void heWillBeAbleToSeeALinkToForgetMyUsernameAndPassword() throws Throwable {
        assertTrue("User is not able to see a link to Forget my username and password", onePassLoginUtils.isForgotMyUsernameOrPasswordLinkPresent());
    }

    @Then("^he will be able to select a save username option$")
    public void heWillBeAbleToSelectASaveUsernameOption() throws Throwable {
        assertTrue("User is not able to select Save Username checkbox", onePassLogin.isCheckBoxSelectable(onePassLogin.saveMyUsernameCheckBox()));
    }

    @Then("^he will be able to select a save username and password option$")
    public void heWillBeAbleToSelectASaveUsernameAndPasswordOption() throws Throwable {
        assertTrue("User is not able to select Save Username And Password checkbox", onePassLogin.isCheckBoxSelectable(onePassLogin.saveMyUsernameAndPasswordCheckBox()));
    }

    @Then("^he will be able to select a Remember me on this computer option to enable the Super Remember me cookie$")
    public void heWillBeAbleToSelectARememberMeOnThisComputerOptionToEnableTheSuperRememberMeCookie() throws Throwable {
        assertTrue("User is not able to select Remember me on this computer checkbox", onePassLogin.isCheckBoxSelectable(onePassLogin.rememeberMeCheckBox()));
    }

    @Then("^he will be able to click a link to see some help/information about the use of the Super Remember me cookie$")
    public void heWillBeAbleToClickALinkToSeeSomeHelpInformationAboutTheUseOfTheSuperRememberMeCookie() throws Throwable {
        onePassLoginUtils.openSuperRememberMeHintPopUp();
        assertTrue("User is not able to see some help/information about the use of the Super Remember me cookie", onePassLoginUtils.isSuperRememberMeHitnPopUpPresent());
    }

    @Then("^he will be able to see a link to Create a new OnePass Profile$")
    public void heWillBeAbleToSeeALinkToCreateANewOnePassProfile() throws Throwable {
        assertTrue("User is not able to see a link to Create a new OnePass Profile", onePassLoginUtils.isCreateNewOnePassProfileLinkPresent());
    }

    @Then("^he will be able to see a link to Update an existing OnePass Profile$")
    public void heWillBeAbleToSeeALinkToUpdateAnExistingOnePassProfile() throws Throwable {
        assertTrue("User is not able to see a link to Update an existing OnePass Profile", onePassLoginUtils.isUpdateExistingOnePassProfileLinkPresent());
    }

    @Then("^he will be able to see a link to Learn more about OnePass$")
    public void heWillBeAbleToSeeALinkToLearnMoreAboutOnePass() throws Throwable {
        assertTrue("User is not able to see a link to Learn more about OnePass", onePassLoginUtils.isLearnMoreAboutOnePassLinkPresent());
    }

}