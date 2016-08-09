package com.thomsonreuters.step_definitions.uk.assetPage;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssetPageJumpJinksStep  extends BaseStepDef{

	AssetPageUtils assetPageUtils = new AssetPageUtils();
	AssetDocumentPage assetDocumentPage = new AssetDocumentPage();

	private String linkText;

	@Then("^the user see \"(.*?)\" jump link in the left hand side navigation panel$")
	public void theUserSeeJumpLinkInTheLeftHandSideNavigationPanel(String jumpLinkText) throws Throwable {
		assetDocumentPage.waitForPageToLoad();
		assetDocumentPage.waitForPageToLoadAndJQueryProcessing();
		assertTrue("The user doesn't see jump links in the left hand navigation panel",
				assetPageUtils.isTheUserSeeJumpLinksInTheLeftHandSideNavigationPanel(jumpLinkText));
	}

	@When("^the user clicks on \"(.*?)\" jump link$")
	public void theUserClicksOnJumpLink(String jumpLinkText) throws Throwable {
		linkText = assetPageUtils.clickOnJumpLink(jumpLinkText);
	}

	@Then("^the user is taken to selected part of the document$")
	public void theUserIsTakenToSelectedPartOfTheDocument() throws Throwable {
		assertTrue("The user doesn't taken to the selected part of the document",
				assetPageUtils.isTheUserTakenToSelectedPartOfTheDocument(linkText));
	}

	@Then("^the user does not see \"(.*?)\" jump link$")
	public void theUserDoesNotSeeJumpLink(String jumpLinkText) throws Throwable {
		try {
			assertFalse("The user see jump links in the left hand navigation panel",
					assetDocumentPage.isElementDisplayed(assetDocumentPage.jumpLink(jumpLinkText)));
		} catch (PageOperationException poe) {
			LOG.info("context", poe);
		}
	}

	@When("^the user does not see \"(.*?)\" section$")
	public void theUserDoesNotSeeSection(String jumpLinkText) throws Throwable {
		try {
			assertFalse("The user see jump link section",
					assetDocumentPage.isElementDisplayed(assetDocumentPage.junpLinkSection(jumpLinkText)));
		} catch (PageOperationException poe) {
			LOG.info("context", poe);
		}
	}

}
