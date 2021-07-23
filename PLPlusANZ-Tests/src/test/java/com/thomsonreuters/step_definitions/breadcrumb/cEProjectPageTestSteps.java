package com.thomsonreuters.step_definitions.breadcrumb;

import static org.junit.Assert.assertTrue;

import com.thomsonreuters.pageobjects.pages.contactexpress.ContractExpressHeader;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;

import cucumber.api.java.en.Then;

public class cEProjectPageTestSteps extends BaseStepDef {
	private ContractExpressHeader header = new ContractExpressHeader();
	private HomePage homePage = new HomePage();
	
	@Then("^User verifies the Filter Search$")
	public void theFilterSearch( )throws Throwable{
		
		//assertTrue("Filter Search is not displayed",header.isFilterSearchDisplayed());		
	}
	
}
