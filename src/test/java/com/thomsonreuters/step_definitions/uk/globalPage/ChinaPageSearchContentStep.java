package com.thomsonreuters.step_definitions.uk.globalPage;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ChinaPageSearchContentStep {
	private static final String PATH_TO_CHINA_PAGE = "/Browse/Home/International/ChinaGlobal";

	private NavigationCobalt navigation = new NavigationCobalt();
	private HomePage homePage = new HomePage();
	private GlPageSearchContentStep glPageSearchContentStep = new GlPageSearchContentStep();

	@When("^the user navigates to the China Category Page$")
	public void theUserNavigatesToTheChinaCategoryPage() throws Throwable {
		navigation.navigateToPLCUKPlusSpecificURL(PATH_TO_CHINA_PAGE);
	}

	@Then("^the China Category Page opens correctly$")
	public void theChinaCategoryPageOpensCorrectly() throws Throwable {
		glPageSearchContentStep.theCategoryPageOpensCorrectly();
	}

	@When("^the user selects \"(.*?)\" tab$")
	public void theUserSelectsTab(String tab) throws Throwable {
		homePage.specificTab(tab).click();
	}
}
