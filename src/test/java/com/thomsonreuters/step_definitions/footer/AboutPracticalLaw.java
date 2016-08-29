package com.thomsonreuters.step_definitions.footer;

import com.thomsonreuters.pageobjects.pages.company.AboutCompanyPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AboutPracticalLaw extends BaseStepDef {
	
	private AboutCompanyPage aboutCompanyPage = new AboutCompanyPage();
	
	@Then("^the user selects a tab \"(.*?)\" within the table of contents$")
	public void theUserSelectsTabWithinTheTableOfContents(String tabName) throws Throwable {
		aboutCompanyPage.specifiedTabInTableOfContents(tabName).click();
		aboutCompanyPage.waitForPageToLoad();
	}
	
	@Then("^tab \"(.*?)\" in Table of Contents is active$")
	public void tabInTOCIsActive(String tabName) throws Throwable {
		String activeTab = aboutCompanyPage.activeTabInTableOfContents().getText().trim();
		assertTrue("Tab is not active! Active tab text is: " + activeTab, activeTab.contains(tabName));
	}
	
	@Then("^about company page is not empty$")
	public void aboutCompanyPageisNotEmpty() throws Throwable {
		assertFalse("Text on company page is empty", aboutCompanyPage.textSection().getText().isEmpty());
	}

}
