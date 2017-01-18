package com.thomsonreuters.step_definitions.legalUpdates;

import com.thomsonreuters.pageobjects.pages.adestra.SubscriptionPreferencePage;
import com.thomsonreuters.pageobjects.utils.adestra.AdestraUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdestraOutOfPlan extends BaseStepDef {

	private SubscriptionPreferencePage subscriptionPreferencePage = new SubscriptionPreferencePage();
	private AdestraUtils adestraUtils = new AdestraUtils();

	@Given("^a user \"(.*?)\" has subscription for \"(.*)\" out of plan email service$")
	public void aUserHasSubscriptionForOutOfPlanEmailService(String userEmail,
			List<String> subscriptionsID) throws Throwable {
		adestraUtils.createSubscriptionViaAPI(userEmail, subscriptionsID);
	}

	@Then("^the user should be presented with their Employment email service row$")
	public void theUserShouldBePresentedWithTheirCorporateEmailServiceRow()
			throws Throwable {
		assertTrue("Corporate row is absent", subscriptionPreferencePage
				.getSpecifiedServiceField("Employment", "AU").isDisplayed());
	}

	@Then("^the user should be presented with a 'Request a trial' link on the Employment row$")
	public void theUserShouldBePresentedWithARequestATrialLinkOnTheRow()
			throws Throwable {
		assertTrue(
				"'Request a trial' link is absent",
				subscriptionPreferencePage.getSpecifiedServiceRequestTrialLink(
						"Employment", "AU").isDisplayed());
	}

	@Then("^the weekly check box should be ticked$")
	public void theWeeklyCheckBoxShouldBeTicked() throws Throwable {
		assertTrue("Checkbox is not selected", subscriptionPreferencePage
				.getSpecifiedCheckBox("Employment", "W", "AU").isSelected());
	}

	@When("^the user unchecks the weekly check box$")
	public void theUserUnchecksTheWeeklyCheckBox() throws Throwable {
		subscriptionPreferencePage.getSpecifiedCheckBoxAndClickOnIt(
				"Employment", "W", "AU");
	}

	@Then("^the weekly check box becomes uncheckable$")
	public void theWeeklyCheckBoxBecomesUncheckable() throws Throwable {
		assertFalse("Checkbox is selectable",
				subscriptionPreferencePage
						.isCheckBoxSelectable(subscriptionPreferencePage
								.getSpecifiedCheckBox("Employment", "W", "AU")));
	}
}
