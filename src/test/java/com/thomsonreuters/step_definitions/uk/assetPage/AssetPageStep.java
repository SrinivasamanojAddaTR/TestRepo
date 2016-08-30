package com.thomsonreuters.step_definitions.uk.assetPage;

import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssetPageStep extends BaseStepDef {

	private AssetPageUtils assetPageUtils = new AssetPageUtils();
	private AssetDocumentPage assetDocumentPage = new AssetDocumentPage();

	
	@Then("^the party names are displayed$")
	public void thePartyNamesAreDisplayed() throws Throwable {
		assertTrue("The party names is not displayed", assetDocumentPage.partyNames().isDisplayed());
	}

	@Then("^the user see links to \"(.*?)\" Bailii$")
	public void theUserSeeLinksToBailii(String bailiiLink) throws Throwable {
		assertTrue("The user doesn't see links to Bailii", assetPageUtils.isTheUserSeeLinksToBailii(bailiiLink));
	}

	@When("^the user click on \"(.*?)\" Bailii link$")
	public void theUserClickOnBailiiLink(String bailiiLink) throws Throwable {
		assetPageUtils.clickOnBailiiLink(bailiiLink);
	}

	@Then("^the user see links to legal updates$")
	public void theUserSeeLinksToLegalUpdates() throws Throwable {
		assertTrue("The user doesn't see links to legal updates", assetPageUtils.isTheUserSeeLinksToLegalUpdates());
	}

	@When("^the user click on link to legal updates$")
	public void theUserClickOnLinkToLegalUpdates() throws Throwable {
		assetPageUtils.clickOnLinkToLegalUpdates();
	}

	@Then("^the user see hardcoded \"(.*?)\" links$")
	public void theUserSeeHardcodedLinks(String linkText) throws Throwable {
		assertTrue("The user doesn't see hardcoded links", assetPageUtils.isTheUserSeeHardcodedLinks(linkText));
	}

	@When("^the user clicks on hardcoded \"(.*?)\" link$")
	public void theUserClicksOnHardcodedLink(String linkText) throws Throwable {
		assetPageUtils.clickOnHardcodedLink(linkText);
	}

	@Then("^the user is taken to the legal updates$")
	public void theUserIsTakenToTheLegalUpdates() throws Throwable {
		assertTrue("The user doesn't taken to the legal updates", assetPageUtils.isTheUserTakenToTheLegalUpdates());
	}

	@Then("^the user see celex links \"(.*?)\"$")
	public void theUserSeeCelexLinks(String celexLinkText) throws Throwable {
		assertTrue("The user doesn't see celex links", assetPageUtils.isTheUserSeeCelexLinks(celexLinkText));
	}

	@When("^the user click on celex link \"(.*?)\"$")
	public void theUserClickOnCelexLink(String celexLinkText) throws Throwable {
		assetPageUtils.clickOnCelexLink(celexLinkText);
	}

	@Then("^the user see link to \"(.*?)\" Westlaw UK$")
	public void theUserSeeLinkToWestlawUK(String westlawUkLinkText) throws Throwable {
		assertTrue("The user doesn't see link to westlawUk",
				assetPageUtils.isTheUserSeeLinkToWestlawUK(westlawUkLinkText));
	}

	@When("^the user click on link to \"(.*?)\" Westlaw UK$")
	public void theUserClickOnLinkToWestlawUK(String westlawUkLinkText) throws Throwable {
		assetDocumentPage.waitForPageToLoad();
		assetPageUtils.clickOnLinkToWestlawUK(westlawUkLinkText);
	}

	@Then("^the user is taken to the login page in Westlaw UK$")
	public void theUserIsTakenToTheLoginPageInWestlawUK() throws Throwable {
		assetDocumentPage.waitForPageToLoad();
		assertTrue("The user doesn't taken to the login page in WestlawUk",
				assetPageUtils.isTheUserTakenToTheLoginPageInWestlawUkDocument());
	}

	@Then("^the user is taken to the Westlaw UK document$")
	public void theUserIsTakenToTheWestlawUKDocument() throws Throwable {
		assertTrue("The user doesn't taken to the westlawUk document",
				assetPageUtils.isTheUserTakenToTheWestlawUkDocument());
	}

	@Then("^the user login Westlaw UK$")
	public void theUserLoginWestlawUK() throws Throwable {
		assetPageUtils.loginWestlawUk();
	}

	@When("^the user see opened document in Westlaw UK$")
	public void theUserSeeOpenedDocumentInWestlawUK() throws Throwable {
		assetDocumentPage.waitForPageToLoad();
		assertTrue("The user doesn't see opened document in westlaw UK", assetPageUtils.openDocumentInWestlawUK());
	}

	@Then("^the user close the current window$")
	public void theUserCloseTheCurrentWindow() throws Throwable {
		getDriver().switchTo().window(getDriver().getWindowHandle()).close();
	}

	@Then("^the user click on Log out link in Westlaw UK$")
	public void theUserClickOnLogOutLinkInWestlawUK() throws Throwable {

	}

	@Then("^the user go back to previous window$")
	public void theUserGoBackToPreviousWindow() throws Throwable {
		assetPageUtils.goBackToThePreviousWindow();
	}

	@Then("^the Case page \"(.*?)\" is displayed in assert page$")
	public void theCasePageIsDisplayedInAssertPage(String casePageText) throws Throwable {
		assertTrue("The Case Page doesn't displayed in assert page",
				assetPageUtils.isTheCasePageIsDisplayedInAssertPage(casePageText));
	}

	@Then("^the user see Specialist court value$")
	public void theUserSeeSpecialistCourtValue() throws Throwable {
		assertTrue("The user doesn't see the value of Specialist court",
				assetPageUtils.isTheUserSeeTheValueOfSpecialistCourt());
	}

	@Then("^the user does not see links to \"(.*?)\" Bailii$")
	public void theUserDoesNotSeeLinksToBailii(String bailiiLink) throws Throwable {
		assertFalse("The user see link to Bailii", assetPageUtils.isTheUserSeeLinksToBailii(bailiiLink));
	}

	@Then("^the user taken to EU document in Westlaw UK$")
	public void theUserTakenToEUDocumentInWestlawUK() throws Throwable {
		assertTrue("The user doesn't taken to EUDocument in Westlaw UK", assetPageUtils.euDocumentTitle().isDisplayed());
	}

	@Then("^the user clicks on Log Out link in Westlaw Uk$")
	public void theUserClicksOnLogOutLinkInWestlawUk() throws Throwable {
		assetDocumentPage.waitForPageToLoad();
		assetPageUtils.outPutLink().click();
		assetDocumentPage.waitForPageToLoad();
	}

	@Then("^the user see Where Reported list$")
	public void theUserSeeWhereReportedList() throws Throwable {
		assertTrue("The user doesn't see Where reported list",
				assetDocumentPage.isElementDisplayed(assetDocumentPage.whereReportedList()));
	}
	
	@Then("^the metadata does not contain \"(.*?)\"$")
	public void theMetadataDoesNotContain(String field) throws Throwable {
		assertFalse("The user see the division", assetPageUtils.isTheFieldInMetadataDisplayed(field));
	}
	

	@Then("^the user sees link \"(.*?)\" in the \"(.*?)\" section$")
	public void theUserSeesLinkInTheSection(String linkText, String sectionName) throws Throwable {
		assertTrue("The user doesn't see link in section", assetDocumentPage.isElementDisplayed(assetDocumentPage
				.linkInLegalApdatesSection(linkText, sectionName)));
	}

	@Then("^font size of \"(.*?)\" equals font size of \"(.*?)\"$")
	public void fontSizeOfEqualsFontSizeOf(String linktTextFirst, String linkTextSecond) throws Throwable {
		assertTrue("The font sizes of these links are not equals", assetPageUtils.getFontSizeOfLink(linktTextFirst)
				.equals(assetPageUtils.getFontSizeOfLink(linkTextSecond)));
	}

	@Then("^the asset page has title$")
	public void theAssetPageHasTitle() throws Throwable {
		assertTrue("The asset page doesn't has title: ", !assetDocumentPage.getPageTitle().isEmpty());
	}

	@Then("^the title include party names$")
	public void theTitleIncludePartyNames() throws Throwable {
		assertTrue("The title doesn't include the party names: ",
				assetDocumentPage.getPageTitle().contains(assetDocumentPage.partyNames().getText()));
	}

}