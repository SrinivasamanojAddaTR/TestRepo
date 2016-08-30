package com.thomsonreuters.step_definitions.uk.legalUpdates.adestra;

import com.thomsonreuters.pageobjects.pages.adestra.SubscriptionPreferencePage;
import com.thomsonreuters.pageobjects.utils.adestra.AdestraUtils;
import com.thomsonreuters.pageobjects.utils.adestra.SubscriptionParameters;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class abilityToSeeSavedSubscription extends BaseStepDef {

    private SubscriptionPreferencePage subscriptionPreferencePage = new SubscriptionPreferencePage();
    private AdestraUtils adestraUtils = new AdestraUtils();

    private List<SubscriptionParameters> specifiedServices;
    private List<String> existingSubscriptionIDS;

    @Given("^a user \"(.*?)\" is has subscription to the \"(.*?)\" \"(.*?)\" email service with \"(.*?)\"$")
    public void aUserIsHasSubscriptionToTheEmailServiceWith(String userEmail, String region, String service, List<String> frequencies) throws Throwable {
        specifiedServices = adestraUtils.getSpecifiedServices(region, service, frequencies, currentUser.getProduct());
        existingSubscriptionIDS = adestraUtils.getAdestraSubscriptionNameListForPLCUK(userEmail);
        assertTrue("User has no subscriptions or subscriptions number is not correct" + "subscriptions: " + existingSubscriptionIDS.toString() + "ssize: " + existingSubscriptionIDS.size(), adestraUtils.isUserHasCorrectSubscriptions(specifiedServices, existingSubscriptionIDS));
    }

    @Then("^the user should be presented with \"(.*?)\" \"(.*?)\" services row$")
    public void theUserShouldBePresentedWithServicesRow(String region, String service) throws Throwable {
        assertTrue("Service: " + service + "on tab: " + region + " is not available", subscriptionPreferencePage.getSpecifiedServiceField(service, region).isDisplayed());
    }

    @Then("^the \"(.*?)\" \"(.*?)\" services row will display the appropriate \"(.*?)\" check boxes$")
    public void theServicesRowWillDisplayTheAppropriateCheckBoxes(String region, String service, List<String> frequencies) throws Throwable {
        int result = 0;
        for (String frequency : frequencies) {
            if (!subscriptionPreferencePage.getSpecifiedCheckBox(service, frequency, region).isSelected()) {
                result++;
            }
        }
        assertTrue("One of checkboxes is unavailbale", result == 0);
    }

    @Then("^the \"(.*?)\" \"(.*?)\" check boxes \"(.*?)\" should be selectable$")
    public void theCheckBoxesShouldBeSelectable(String region, String service, List<String> frequencies) throws Throwable {
        int result = 0;
        for (String frequency : frequencies) {
            WebElement frequencyCheckBox = subscriptionPreferencePage.getSpecifiedCheckBox(service, frequency, region);
            if (!subscriptionPreferencePage.isCheckBoxSelectable(frequencyCheckBox)) {
                result++;
            }
        }
        assertTrue("One of checkboxes for is not editable", result == 0);
    }

}