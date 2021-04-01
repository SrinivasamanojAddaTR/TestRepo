package com.thomsonreuters.step_definitions.uk.legalUpdates.adestra;

import com.thomsonreuters.pageobjects.pages.adestra.SubscriptionPreferencePage;
import com.thomsonreuters.pageobjects.utils.adestra.AdestraUtils;
import com.thomsonreuters.pageobjects.utils.adestra.SubscriptionParameters;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AbilityToCancelSubscription1 extends BaseStepDef {

    private SubscriptionPreferencePage subscriptionPreferencePage = new SubscriptionPreferencePage();
    private AdestraUtils adestraUtils = new AdestraUtils();

    private List<SubscriptionParameters> specifiedServices;
    private List<SubscriptionParameters> editedSpecifiedServices;

    private List<String> existingSubscriptionIDS;
    private List<String> editedSubscriptionIDS;

    @Given("^a user \"(.*?)\" has already subscribed to the \"(.*?)\" \"(.*?)\" email service \"(.*?)\"$")
    public void aUserHasAlreadySubscribedToTheEmailService(String userEmail, String service, List<String> frequencies, String region) throws Throwable {
        specifiedServices = adestraUtils.getSpecifiedServices(region, service, frequencies, currentUser.getProduct());
        existingSubscriptionIDS = adestraUtils.getAdestraSubscriptionNameListForPLCUK(userEmail);
        assertTrue("User has no subscriptions or subscriptions number is not correct: " + "subscriptions: " + existingSubscriptionIDS.toString() + "ssize: " + existingSubscriptionIDS.size(), adestraUtils.isUserHasCorrectSubscriptions(specifiedServices, existingSubscriptionIDS));
    }

    @When("^the user unchecks \"(.*?)\" the \"(.*?)\" \"(.*?)\" email check box$")
    public void theUserUnchecksTheEmailCheckBox(String region, List<String> frequencies, String service) throws Throwable {
        editedSpecifiedServices = adestraUtils.removeSubscriptionFromSpecifiedServices(specifiedServices, region, service, frequencies);
        for (String frequency : frequencies) {
            subscriptionPreferencePage.getSpecifiedCheckBoxAndClickOnIt(service, frequency, region);
        }
    }

    @When("^the user clicks the 'Save preference' button$")
    public void theUserClicksTheSavePreferenceButton() throws Throwable {
        subscriptionPreferencePage.saveButtonANZ().click();
        subscriptionPreferencePage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    @Then("^the users \"(.*?)\" saved subscription preferences should be saved in Adestra$")
    public void theUsersSavedSubscriptionPreferencesShouldBeSavedInAdestra(String userEmail) throws Throwable {
        editedSubscriptionIDS = adestraUtils.getAdestraSubscriptionNameListForPLCUK(userEmail);
        assertTrue("User has no subscriptions or subscriptions number is not correct" + "subscriptions: " + editedSubscriptionIDS.toString() + "ssize: " + editedSubscriptionIDS.size(), adestraUtils.isUserHasCorrectSubscriptions(editedSpecifiedServices, editedSubscriptionIDS));
    }

    @Then("^the user should be unsubscribed from the \"(.*?)\" email service \"(.*?)\" \"(.*?)\"$")
    public void theUserShouldBeUnsubscribedFromTheEmailService(String region, String service, List<String> frequencies) throws Throwable {
        int result = 0;
        for (String frequency : frequencies) {
            if (subscriptionPreferencePage.getSpecifiedCheckBox(service, frequency, region).isSelected()) {
                result++;
            }
        }
        assertTrue("One of checkboxes is unavailbale", result == 0);
    }

    @When("^the user checks the 'Yes' box in the Unsubscribe All section$")
    public void theUserChecksTheYesBoxInTheUnsubscribeAllSection() throws Throwable {
        subscriptionPreferencePage.unsubscribeAllANZ();
    }

    @Then("^the user \"(.*?)\" should be unsubscribed from all email services$")
    public void theUserShouldBeUnsubscribedFromAllEmailServices(String userEmail) throws Throwable {
        assertTrue("Subscriptions were not deleted from Adestra", adestraUtils.getAdestraSubscriptionNameListForPLCUK(userEmail).isEmpty());
    }

    @Then("^all email service check boxes on the preference page should be unchecked$")
    public void allEmailServiceCheckBoxesOnThePreferencePageShouldBeUnchecked() throws Throwable {
        int result = 0;
        for (SubscriptionParameters parameter : specifiedServices) {
            if (subscriptionPreferencePage.getSpecifiedCheckBox(parameter.getCommonName(), parameter.getProductFrequency(), parameter.getCategoryName()).isSelected()) {
                LOG.error("Frequency checkbox is selected after unsubscribe: " + parameter.getProductFrequency() + " for service: " + parameter.getCommonName() + " on region tab: " + parameter.getCategoryName());
                result++;
            }
        }
        assertTrue("One of checkboxes is unavailbale", result == 0);
    }
}