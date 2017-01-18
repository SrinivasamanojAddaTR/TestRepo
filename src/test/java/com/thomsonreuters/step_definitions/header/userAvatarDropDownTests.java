package com.thomsonreuters.step_definitions.header;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class UserAvatarDropDownTests extends BaseStepDef {
	
	private WLNHeader wlnHeader = new WLNHeader();
	private OnepassLogin onepassLogin = new OnepassLogin();
	private final String ONEPASS_LOGIN_FORM_TITLE = "manage onepass";
	
	@When("^the user selects the user profile symbol$")
	public void theUserSelectsTheUserProfileSymbol() throws Throwable {
		wlnHeader.expandUserAvatarDropDown();
	}

	@Then("^the Edit Profile option will be displayed$")
	public void theEditProfileOptionWillBeDisplayed() throws Throwable {
		assertTrue("Edit profile link is not displayed",wlnHeader.isEditProtfileLinkPresent());
	}

	@Then("^the user selects Edit profile option$")
	public void theUserSelectsEditProfileOption() throws Throwable {
		wlnHeader.editProfileLink().click();
		
	}

	@Then("^the user will be taken to the Manage One Pass page$")
	public void theUserWillBeTakenToTheManageOnePassPage() throws Throwable {
		assertTrue("Edit profile link is not displayed", ONEPASS_LOGIN_FORM_TITLE.equalsIgnoreCase(onepassLogin.manageOnePassLoginFormTitle().getText()));
	}

}
