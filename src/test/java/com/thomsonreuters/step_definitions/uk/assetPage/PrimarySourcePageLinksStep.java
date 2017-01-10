package com.thomsonreuters.step_definitions.uk.assetPage;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.PrimarySourceDocumentPage;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimarySourcePageLinksStep extends BaseStepDef {

	PrimarySourceDocumentPage primarySourceDocumentPage = new PrimarySourceDocumentPage();
	AssetPageUtils assetPageUtils = new AssetPageUtils();
	AssetDocumentPage assetDocumentPage = new AssetDocumentPage();

	private String hrefAtribute;

	@Then("^the user see links to \"(.*?)\" Legislation$")
	public void theUserSeeLinksToLegislation(String legislationLinkText) throws Throwable {
		primarySourceDocumentPage.refreshPage();
		primarySourceDocumentPage.waitForPageToLoad();
		assertTrue("The primary source title doesn't displayed",
				primarySourceDocumentPage.isElementDisplayed(primarySourceDocumentPage
						.legislationLink(legislationLinkText)));
	}

	@When("^the user click on \"(.*?)\" Legislation link$")
	public void theUserClickOnLegislationLink(String legislationLinkText) throws Throwable {
		assetPageUtils.readBasePageParameters();
		primarySourceDocumentPage.legislationLink(legislationLinkText).click();
	}

	@Then("^the user does not see links to \"(.*?)\" Legislation$")
	public void theUserDoesNotSeeLinksToLegislation(String legislationLinkText) throws Throwable {
		try {
			assertFalse("The user see links to Legislation",
					primarySourceDocumentPage.isElementDisplayed(primarySourceDocumentPage
							.legislationLink(legislationLinkText)));
		} catch (PageOperationException poe) {
			LOG.info("Legislation link was not found", poe);
		}
	}

	@Then("^the link to \"(.*?)\" Westlaw UK displayed$")
	public void theLinkToWestlawUKDisplayed(String westlawUKLinkText) throws Throwable {
		assertTrue("The link to Westlaw UK doesn't displayed",
				primarySourceDocumentPage.isElementDisplayed(assetDocumentPage.westlawUkLink(westlawUKLinkText)));
	}

	@Then("^the user is taken to \"(.*?)\" resource$")
	public void theUserIsTakenToResource(String linkText) throws Throwable {
		primarySourceDocumentPage.waitForPageToLoad();
		assetPageUtils.readBasePageParameters();
		assertTrue("The user doesn't taken to the selected resource",
				assetPageUtils.isTheUserTakenToTheSelectedResource(linkText));
	}

	@Then("^the user sees the \"(.*?)\" link$")
	public void theUserSeesTheLink(String linkText) throws Throwable {
		assertTrue("The user doesn't see Link",
				primarySourceDocumentPage.isElementDisplayed(primarySourceDocumentPage.linkInPrimarySource(linkText)));
	}

	@When("^the user clicks on this \"(.*?)\" link$")
	public void theUserClicksOnThisLink(String linkText) throws Throwable {
		assetPageUtils.readBasePageParameters();
		hrefAtribute = primarySourceDocumentPage.linkInPrimarySource(linkText).getAttribute("href");
		primarySourceDocumentPage.linkInPrimarySource(linkText).click();
	}

	@Then("^the document contain \"(.*?)\" links$")
	public void theDocumentContainLinks(String linkText) throws Throwable {
		primarySourceDocumentPage.waitForPageToLoad();
		assertTrue("The document doesn't contain link", assetPageUtils.isTheDocumentContainLink(linkText));
	}

	@Then("^these \"(.*?)\" displayed accoding with className and hrefAtribute$")
	public void theseDisplayedAccodingWithClassNameAndHrefAtribute(String linkText) throws Throwable {
		primarySourceDocumentPage.waitForPageToLoad();
		assertTrue("The link doesn't displayed according with class name and hrefAtribute",
				assetPageUtils.isTheLinkDisplayedAccodingWithClassNameAndHrefAtribute(linkText));
	}

	@Then("^the user sees \"(.*?)\" Specific provision coverage$")
	public void theUserSeesSpecificProvisionCoverage(String provisionSectionText) throws Throwable {
		assertTrue("The user doesn't see specific Provision coverage",
				primarySourceDocumentPage.isElementDisplayed(primarySourceDocumentPage
						.specificProvisionCoverageText(provisionSectionText)));
	}

	@Then("^the user sees the \"(.*?)\" link in Specific provision coverage section$")
	public void theUserSeesTheLinkInSpecificProvisionCoverageSection(String linkText) throws Throwable {
		assertTrue("The user doesn't see link in provision coverage section",
				primarySourceDocumentPage.isElementDisplayed(primarySourceDocumentPage
						.linkInSpecificProvisionCoverageSection(linkText)));
	}

	@When("^the user clicks on this \"(.*?)\" link in Specific provision coverage section$")
	public void theUserClicksOnThisLinkInSpecificProvisionCoverageSection(String linkText) throws Throwable {
		assetPageUtils.readBasePageParameters();
		hrefAtribute = primarySourceDocumentPage.linkInSpecificProvisionCoverageSection(linkText).getAttribute("href");
		primarySourceDocumentPage.linkInSpecificProvisionCoverageSection(linkText).click();
	}

	@Then("^the user is taken to the primary source document$")
	public void theUserIsTakenToThePrimarySourceDocument() throws Throwable {
		assertTrue("The user doesn't taken to the primary source document",
				assetPageUtils.isTheUserTakenToTheInternalDocument(hrefAtribute));
	}

	@Then("^the user sees Show more \"(.*?)\" link$")
	public void theUserSeesShowMoreLink(String showMoreLinkText) throws Throwable {
		primarySourceDocumentPage.waitForPageToLoad();
		assertTrue("The user doesn't see Show more link",
				primarySourceDocumentPage.isElementDisplayed(primarySourceDocumentPage.showMoreLink(showMoreLinkText)));
	}

	@When("^the user clicks on Show more \"(.*?)\" link$")
	public void theUserClicksOnShowMoreLink(String showMoreLinkText) throws Throwable {
		primarySourceDocumentPage.showMoreLink(showMoreLinkText).click();
	}

	@Then("^this link \"(.*?)\" belong to document \"(.*?)\" type$")
	public void thisLinkBelongToDocumentType(String linkText, String documentTupeText) throws Throwable {
		assertTrue("Link doesn't belong to document type",
				primarySourceDocumentPage.isElementDisplayed(primarySourceDocumentPage.linkOfSpecificDocumentType(
						linkText, documentTupeText)));
	}

	@Then("^the user is taken from primary source page to internal document$")
	public void theUserIsTakenFromPrimarySourcePageToInternalDocument() throws Throwable {
		assertTrue("The user doesn't taken to the internal document",
				assetPageUtils.isTheUserTakenToTheInternalDocument(hrefAtribute));
	}

	@Then("^text added to the document$")
	public void textAddedToTheDocument() throws Throwable {
		primarySourceDocumentPage.waitForPageToLoadAndJQueryProcessing();
		assetPageUtils.addTextToTheDocumentPage();
	}

	@When("^the user sees the number of links found$")
	public void theUserSeesTheNumberOfLinksFound() throws Throwable {
		assertTrue("The user doesn't see the number of links found",
				primarySourceDocumentPage.isElementDisplayed(primarySourceDocumentPage.numberOfLinksFoundResult()));
	}

	@When("^this number more than \"(.*?)\"$")
	public void thisNumberMoreThan(String numberOfLinks) throws Throwable {
		assertTrue("The number of links less than 300", assetPageUtils.isTheNumberOfLinksMoreThan(numberOfLinks));
	}

	@Then("^the number of displayed links equals to the number of results found$")
	public void theNumberOfDisplayedLinksEqualsToTheNumberOfResultsFound() throws Throwable {
		assertTrue("The number of displayed links is not equals to the number of results found",
				assetPageUtils.isTheNumberOfLinksEqualsToTheNumberOfResultsFound());
	}

	@Then("^the number of open tabs equals \"(.*?)\"$")
	public void theNumberOfOpenTabsEquals(String numberOfOpenedTubs) throws Throwable {
		assertTrue("Incorrect number of opened tubs",
				assetPageUtils.isTheNumberOfOpenedTubsEqueals(Integer.parseInt(numberOfOpenedTubs)));
	}

}
