package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.onePass.OnePassLogoutPage;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class abilityToUseSaveUserNameOption extends BaseStepDef {

    private OnepassLogin onePassLogin = new OnepassLogin();
    private WLNHeader wlnHeader = new WLNHeader();
    private OnepassLoginUtils onePassLoginUtils = new OnepassLoginUtils();
	private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();

    @When("^user selects Save my Username checkbox$")
    public void userSelectsSaveMyUsernameCheckbox() throws Throwable {
        onePassLoginUtils.selectSaveMyUsernameCheckBox();
        assertFalse("Save my username and password checkbox was not unchecked", onePassLogin.saveMyUsernameAndPasswordCheckBox().isSelected());
    }

    @Then("^user logs out$")
    public void userLogsOut() throws Throwable {
        wlnHeader.signOff();
        onePassLogin.waitForPageToLoad();
        LOG.info("The user has logged out");
    }

    @Then("^user tries to log in again$")
    public void userTriesToLogInAgain() throws Throwable {
    	onePassLogoutPage.signOffPageSignOnButton().click();
        onePassLogin.waitForPageToLoad();
    }

    @Then("^he will see the username box in the log in page populated with his username$")
    public void heWillSeeTheUsernameBoxInTheLogInPagePopulatedWithHisUsername() throws Throwable {
        assertTrue("Username field is not correct", onePassLogin.getTextFromInputOrTextarea(onePassLogin.usernameTextField()).equals(onePassLoginUtils.getUserName()));
    }

    @When("^user selects Save my Username and Password checkbox$")
    public void userSelectsSaveMyUsernameAndPasswordCheckbox() throws Throwable {
        onePassLoginUtils.selectSaveMyUsernameAndPasswordCheckBox();
        assertFalse("Save my username checkbox was not unchecked", onePassLogin.saveMyUsernameCheckBox().isSelected());
    }

    @Then("^his password will be populated in the password box$")
    public void hisPasswordWillBePopulatedInThePasswordBox() throws Throwable {
        assertFalse("Password field is empty", onePassLogin.getTextFromInputOrTextarea(onePassLogin.passwordTextField()).isEmpty());
    }

    @Then("^his password will not be readable in the screen and only asterisks will be visible$")
    public void hisPasswordWillNotBeReadableInTheScreenAndOnlyAsterisksWillBeVisible() throws Throwable {
        assertTrue("Password field is not secure", onePassLogin.getTextFromInputOrTextarea(onePassLogin.passwordTextField()).equals("____SAVED____"));
    }

}