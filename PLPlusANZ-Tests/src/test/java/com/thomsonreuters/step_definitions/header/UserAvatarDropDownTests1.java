package com.thomsonreuters.step_definitions.header;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class UserAvatarDropDownTests1 extends BaseStepDef {
	
	private WLNHeader wlnHeader = new WLNHeader();
	private OnepassLogin onepassLogin = new OnepassLogin();

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
		assertTrue("Edit profile link is not displayed", onepassLogin.isManageOnePassTitleDisplayed());
	}

	@When("^the user selects My Automated Document option$")
	public void theUserSelectsMyAutomatedDocumentOption() throws Throwable {
		wlnHeader.myAutomatedDocuments().click();
		wlnHeader.waitForPageToLoadAndJQueryProcessing();
	}

	@Then("^my Automated Documents option should be displayed$")
	public void isMyAutomatedDocumentsOptionDisplayed() {
		wlnHeader.waitForPageToLoadAndJQueryProcessing();
		assertThat(wlnHeader.isMyAutomatedDocumentsPresent()).
				overridingErrorMessage("My Automated Documents option should be displayed").
				isTrue();
	}
}
