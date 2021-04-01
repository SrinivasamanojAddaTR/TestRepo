package com.thomsonreuters.step_definitions.footer;

import com.thomsonreuters.pageobjects.pages.footer.ContributingFirmsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class ContributingFirms extends BaseStepDef {
	
	private ContributingFirmsPage contributingFirmsPage = new ContributingFirmsPage();
	private String randomFirmTitle;
	
	@When("^the user cliks on letter \"(.*?)\"$")
	public void theUserCliksOnLetter(String letter) throws Throwable {
	   contributingFirmsPage.waitForPageToLoadAndJQueryProcessing();
	   contributingFirmsPage.jumpLink(letter).click();
	}

	@Then("^the user is taken to the list of Contributing Firms with names starting with the letter \"(.*?)\"$")
	public void theUserIsTakenToTheListOfContributingFirmsWithNamesStartingWithTheLetter(String letter) throws Throwable {
		assertTrue("The user doesn't taken to the selected part of the list",
				contributingFirmsPage.isTheUserTakenToSelectedPartOfList(letter));
	}
	
	@When("^the user cliks on random contributing firm$")
	public void theUserCliksOnRandomFirm() throws Throwable {
	   contributingFirmsPage.waitForPageToLoadAndJQueryProcessing();
	   WebElement randomFirm = contributingFirmsPage.randomFirm();
	   randomFirmTitle = randomFirm.getText();
	   LOG.info("random firm:"+randomFirmTitle);
	   randomFirm.click();
	}
	
	@Then("^the user verifies that the PageTitle is present on current firm page$")
	public void theUserVerifiesThePageTitleOfCurrentFirmPage() throws Throwable {
        assertTrue("The Expected Page Title " + randomFirmTitle + " is  NOT displayed", contributingFirmsPage.getPageHeaderLabel().isDisplayed());
	}

	@Then("^main information about firm is on current firm page$")
	public void mainInformationAboutFirmIsOnCurrentFirmPage() throws Throwable {
		assertTrue("The main information about " + randomFirmTitle + " firm is  NOT displayed", contributingFirmsPage.isMainInformationDisplayed());
	}

	@Then("^contributed resources is on current firm page$")
	public void contributedResourcesIsOnCurrentFirmPage() throws Throwable {
		assertTrue("The contributed resources for " + randomFirmTitle + " firm is  NOT displayed", contributingFirmsPage.isContributedResourcesDisplayed());
	}

	@Then("^logo is on current firm page$")
	public void logoIsOnCurrentFirmPage() throws Throwable {
		assertTrue("The logo for " + randomFirmTitle + " firm is  NOT displayed", contributingFirmsPage.isLogoDisplayed());
	}

	@Then("^contact information is on current firm page$")
	public void contactInformationIsOnCurrentFirmPage() throws Throwable {
		assertTrue("The contact information for " + randomFirmTitle + " firm is  NOT displayed", contributingFirmsPage.isContactInformationDisplayed());
	}
}
