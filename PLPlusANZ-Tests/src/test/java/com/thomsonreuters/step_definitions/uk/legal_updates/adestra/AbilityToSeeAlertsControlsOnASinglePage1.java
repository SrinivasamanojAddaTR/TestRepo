package com.thomsonreuters.step_definitions.uk.legal_updates.adestra;

import com.thomsonreuters.pageobjects.pages.adestra.SubscriptionPreferencePage;
import com.thomsonreuters.pageobjects.utils.adestra.AdestraUtils;
import com.thomsonreuters.pageobjects.utils.adestra.SubscriptionParameters;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AbilityToSeeAlertsControlsOnASinglePage1 extends BaseStepDef {

    private SubscriptionPreferencePage subscriptionPreferencePage = new SubscriptionPreferencePage();
    private AdestraUtils adestraUtils = new AdestraUtils();

    private List<SubscriptionParameters> servicesForSpecifiedRegion;
    private List<String> adestraSubscriptionIDS;

    private final String HTML_RADIOBUTTON_LABEL = "HTML (images and text)";
    private final String TEXT_RADIOBUTTON_LABEL = "Text only";
    private final String SAVE_BUTTON_LABEL = "Save";
    private final String CANCEL_BUTTON_LABEL = "Cancel";

    @Given("^the user has opened the \"(.*?)\" Services tab$")
    public void theUserHasOpenedTheServicesTab(String region) throws Throwable {
        servicesForSpecifiedRegion = adestraUtils.getServiceForSpecifiedRegion(region, currentUser.getProduct());
        subscriptionPreferencePage.openSpecifiedServiceTab(region);
    }

    @When("^the user selects checkboxes for each  \"(.*?)\" service$")
    public void theUserSelectsCheckboxesForEachService(String region) throws Throwable {
        for (SubscriptionParameters service : servicesForSpecifiedRegion) {
            subscriptionPreferencePage.getSpecifiedCheckBoxAndClickOnIt(service.getCommonName(), service.getProductFrequency(), region);
        }
    }

    @When("^the user saves his preferences$")
    public void theUserSavesHisPreferences() throws Throwable {
        subscriptionPreferencePage.saveButtonANZ().click();
        subscriptionPreferencePage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    @Then("^user \"(.*?)\" preferences should be saved in adestra$")
    public void userPreferencesShouldBeSavedInAdestra(String userEmail) throws Throwable {
        adestraSubscriptionIDS = adestraUtils.getAdestraSubscriptionNameListForPLCUK(userEmail);
        assertTrue("User has no subscriptions or subscriptions number is not correct" + "subscriptions: " + adestraSubscriptionIDS.toString() + "ssize: " + adestraSubscriptionIDS.size(), adestraUtils.isUserHasCorrectSubscriptions(servicesForSpecifiedRegion, adestraSubscriptionIDS));
    }

    @Then("^the user should be presented with each \"(.*?)\" service$")
    public void theUserShouldBePresentedWithEachService(String region) throws Throwable {
        int result = 0;
        for (SubscriptionParameters service : servicesForSpecifiedRegion) {
            if (!subscriptionPreferencePage.getSpecifiedServiceField(service.getCommonName(), region).isDisplayed()) {
                result++;
            }
        }
        assertTrue("One of services for " + region + " is unavailbale", result == 0);
    }

    @Then("^each \"(.*?)\" service should have the relevant check box options$")
    public void eachServiceShouldHaveTheRelevantCheckBoxOptions(String region) throws Throwable {
        int result = 0;
        for (SubscriptionParameters service : servicesForSpecifiedRegion) {
            if (!subscriptionPreferencePage.getSpecifiedCheckBox(service.getCommonName(), service.getProductFrequency(), region).isDisplayed()) {
                LOG.error("Unable to find frequency checkbox: " + service.getProductFrequency() + " for service: " + service.getCommonName() + " on region tab: " + region);
                result++;
            }
        }
        assertTrue("One of checkboxes for " + region + " is unavailbale", result == 0);
    }

    @Then("^the user should be presented with two radio buttons as email options$")
    public void theUserShouldBePresentedWithTwoRadioButtonsAsEmailOptions() throws Throwable {
        assertTrue("one of radiobuttons is absent", subscriptionPreferencePage.htmlRadioButtonANZ().isDisplayed() && subscriptionPreferencePage.textOnlyRadioButtonANZ().isDisplayed());
    }

    @Then("^the options should include HTML and Text Only$")
    public void theOptionsShouldIncludeHTMLAndTextOnly() throws Throwable {
        String htmlRB = subscriptionPreferencePage.htmlRadioButtonLabelANZ().getText();
        String textRB = subscriptionPreferencePage.textOnlyRadioButtonLabelANZ().getText();
        assertTrue("Label for radio button is incorrect", htmlRB.contains(HTML_RADIOBUTTON_LABEL) && textRB.contains(TEXT_RADIOBUTTON_LABEL));
    }

    @Then("^the user should be presented with a checkbox for 'Receive an email even if there are no new items'$")
    public void theUserShouldBePresentedWithACheckboxForReceiveAnEmailEvenIfThereAreNoNewItems() throws Throwable {
        assertTrue("Checkbox 'I would like to receive 'No new items to report' emails' is absent", subscriptionPreferencePage.recieveNoNewItemsCheckBoxANZ().isDisplayed());
    }

    @Then("^the user should be presented with two buttons to save their preferences or cancel their changes$")
    public void theUserShouldBePresentedWithTwoButtonsToSaveTheirPreferencesOrCancelTheirChanges() throws Throwable {
        assertTrue("one of save/cancel buttons is absent", subscriptionPreferencePage.saveButtonANZ().isDisplayed() && subscriptionPreferencePage.cancelButtonANZ().isDisplayed());
    }

    @Then("^one button should be labelled 'Save'$")
    public void oneButtonShouldBeLabelledSavePreferences() throws Throwable {
        assertTrue("Save button label is incorrect", subscriptionPreferencePage.saveButtonANZ().getText().equals(SAVE_BUTTON_LABEL));
    }

    @Then("^one button should be labelled 'Cancel'$")
    public void oneButtonShouldBeLabelledCancelChanges() throws Throwable {
        assertTrue("Cancel button label is incorrect", subscriptionPreferencePage.cancelButtonANZ().getText().equals(CANCEL_BUTTON_LABEL));
    }

}