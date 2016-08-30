package com.thomsonreuters.step_definitions.linking;

import com.thomsonreuters.pageobjects.pages.landingPage.InternationalLandingPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class linkingFromInternationalTab {
	
    private InternationalLandingPage internationalLandingPage = new InternationalLandingPage();

	@When("^the user has selected the international subscriptions link for the country \"([^\"]*)\"$")
    public void hasSelectedTheInternationalSubscriptionLinkForTheCountry(String country) throws Throwable {
        internationalLandingPage.internationalSubscriptionForCountry(country).click();
    }
	
	@Then("^the PLAU international tab will be displayed and the session will not be paused$")
	public void tabRemainsOpen() throws Throwable {	
		assertTrue("The international tab is not opened", internationalLandingPage.internationalTabIsOpened());
		assertTrue("Session is paused", internationalLandingPage.sessionIsNotPause());
	}


}
