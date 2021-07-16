package com.thomsonreuters.step_definitions.uk.legal_updates.adestra;

import com.thomsonreuters.pageobjects.pages.adestra.SubscriptionPreferencePage;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.adestra.AdestraUtils;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.List;

import org.openqa.selenium.By;

public class AdestraCommonSteps extends BaseStepDef {

    private SubscriptionPreferencePage subscriptionPreferencePage = new SubscriptionPreferencePage();
    private WLNHeader wlnHeader = new WLNHeader();
    private AdestraUtils adestraUtils = new AdestraUtils();
    private FooterUtils footerUtils= new FooterUtils();

    @Given("^a user creates subscription to the \"(.*?)\" \"(.*?)\" email service with \"(.*?)\"$")
    public void aUserCreatesSubscriptionToTheEmailServiceWith(String region, String service, List<String> frequencies) throws Throwable {
        subscriptionPreferencePage.openSpecifiedServiceTab(region);
        subscriptionPreferencePage.createSubscriptionsANZ(region, service, frequencies);
    }

    @Given("^a user is viewing the email preference page$")
    public void aUserIsViewingTheEmailPreferencePage() throws Throwable {
        wlnHeader.openEmailPreferences();
        subscriptionPreferencePage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
        footerUtils.closeDisclaimerMessage();
    }

    @After(order = 100000, value = "@UsubscribeUserFromAllSubscriptionsAndRemoveUnsubscribeOption")
    public void unsubscribeUserFromAllSubscriptionsAndRemoveUnsubscribeOption() throws Exception {
    	adestraUtils.removeSubscriptionViaAPI(currentUser.getEmail());
    	 subscriptionPreferencePage.removeUnsubscribeAllOptionANZ();
    }


    @When("^the user opens email preference popup using user dropdown$")
    public void theUserOpensTimeZonePopupUsingUserDropdown() throws Throwable {
     wlnHeader.expandUserAvatarDropDown();
     wlnHeader.waitForExpectedElement(By.linkText("Email preferences")).click();
    }

}