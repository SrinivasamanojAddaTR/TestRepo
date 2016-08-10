package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class abilityToChangeClientIDAfterLogin extends BaseStepDef {

    private WLNHeader wlnHeader = new WLNHeader();

    @Given("^user is able to see default client id \"(.*?)\" in the header$")
    public void userIsAbleToSeeDefaultClientIdInTheHeader(String defaultClientID) throws Throwable {
		wlnHeader.waitForPageToLoadAndJQueryProcessing();
		assertTrue("Client ID is not displayed..!", wlnHeader.clientIdLink(defaultClientID).isDisplayed());
        //we will close UserAvatarDropDown because double expand is equals to close UserAvatarDropDown
        wlnHeader.expandUserAvatarDropDown();
    }

    @When("^user clicks on client id \"(.*?)\"$")
    public void userClicksOnClientId(String defaultClientID) throws Throwable {
        wlnHeader.clientIdLink(defaultClientID).click();
        wlnHeader.waitForPageToLoadAndJQueryProcessing();
    }
	
	@Then("^user is able to change client id \"(.*?)\"$")
	public void userIsAbleToChangeClientId(String newClientID) throws Throwable {
		
			wlnHeader.changeClientID(newClientID);
	}

    @Then("^user can see new client id \"(.*?)\" in the header$")
    public void userCanSeeNewClientIdInTheHeader(String newClientID) throws Throwable {
        assertTrue("Client ID was not changed..!", wlnHeader.clientIdLink(newClientID).isDisplayed());
    }
}