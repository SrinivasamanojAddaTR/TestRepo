package com.thomsonreuters.step_definitions.uk.legalUpdates.adestra;

import com.thomsonreuters.pageobjects.pages.adestra.SubscriptionPreferencePage;
import com.thomsonreuters.pageobjects.pages.annotations.SharedAnnotationsPage;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.adestra.AdestraUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;

import java.util.List;

public class AdestraCommonSteps extends BaseStepDef {

    private SubscriptionPreferencePage subscriptionPreferencePage = new SubscriptionPreferencePage();
    private WLNHeader wlnHeader = new WLNHeader();
    private AdestraUtils adestraUtils = new AdestraUtils();
    private SharedAnnotationsPage sharedAnnotationsPage = new SharedAnnotationsPage();

    @Given("^a user creates subscription to the \"(.*?)\" \"(.*?)\" email service with \"(.*?)\"$")
    public void aUserCreatesSubscriptionToTheEmailServiceWith(String region, String service, List<String> frequencies) throws Throwable {
        subscriptionPreferencePage.openSpecifiedServiceTab(region);
        subscriptionPreferencePage.createSubscriptionsANZ(region, service, frequencies);
    }

    @Given("^a user is viewing the email preference page$")
    public void aUserIsViewingTheEmailPreferencePage() throws Throwable {
        wlnHeader.openEmailPreferences();
        subscriptionPreferencePage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
        sharedAnnotationsPage.closeDisclaimer();
    }

    @After(order = 100000, value = "@UsubscribeUserFromAllSubscriptionsAndRemoveUnsubscribeOption")
    public void unsubscribeUserFromAllSubscriptionsAndRemoveUnsubscribeOption() throws Exception {
    	adestraUtils.removeSubscriptionViaAPI(currentUser.getEmail());
    	 subscriptionPreferencePage.removeUnsubscribeAllOptionANZ();
    }

}