package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ClientIdTest extends BaseStepDef {
	
    private WLNHeader wlnHeader = new WLNHeader();
	private abilityToChangeClientIDAfterLogin abilityToChangeClientIDAfterLogin = new abilityToChangeClientIDAfterLogin();

	
	@When("^user see a list of recent client ids$")
    public void userSeeAListOfRecentClientIds(List<String> recentIDList) throws Throwable {
    	List<String> recentClientIdLinks = new ArrayList<String>();
    	for(WebElement recentClientID : wlnHeader.recentClientIdLinks()){
    		recentClientIdLinks.add(recentClientID.getText());
    	}
    	assertTrue("Client ID list is not displayed or incorrect..!", wlnHeader.recentClientIdList().isDisplayed() && recentClientIdLinks.containsAll(recentIDList));
    }

    @When("^the user selects a recent client ID \"(.*?)\"$")
    public void theUserSelectsARecentClientID(String clientID) throws Throwable {
    	wlnHeader.clientIdLinkInPopUp(clientID).click();
    	wlnHeader.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^selected Client ID \"(.*?)\" will be the new Client ID for the session$")
    public void selectedClientIDWillBeTheNewClientIDForTheSession(String clientID) throws Throwable {
    	assertTrue("Client ID was not changed..!", wlnHeader.clientIdLink(clientID).isDisplayed());
    }
    
	@Then("^user is able to change client id$")
	public void userIsAbleToChangeClientId(List<String> newClientID) throws Throwable {
		for (String clientID : newClientID) {
			String clientIdText = wlnHeader.clientIdLink().getText();
            wlnHeader.expandUserAvatarDropDown();
			abilityToChangeClientIDAfterLogin.userClicksOnClientId(clientIdText);
			wlnHeader.changeClientID(clientID);
		}
	}


}
