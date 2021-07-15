package com.thomsonreuters.step_definitions.document;

import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.DocumentRightPanelPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.KHResourcePage;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class ViewDocument1 extends BaseStepDef {

	private KHResourcePage resourcePage = new KHResourcePage();
	private DocumentRightPanelPage rightPanelPage = new DocumentRightPanelPage();
	private NavigationCobalt navigationCobalt = new NavigationCobalt();
	private FooterUtils footerUtils = new FooterUtils();

	@Given("^ANZ user navigates directly to document with guid \"(.*?)\"$")
	public void anzUserNavigatesDirectlyToDocumentWithGuid(String guid) throws Throwable {
		navigationCobalt.navigateToANZSpecificResourcePage("/Document/" + guid + "/View/FullText.html");
		resourcePage.waitForPageToLoad();
		footerUtils.closeDisclaimerMessage();
        footerUtils.ourCookiesPolicy();
		resourcePage.waitForPageToLoad();
	}

	@Then("^user should not see drafting notes$")
	public void userShouldNotSeeDraftingNotes() throws Throwable {
		assertTrue("Drafting notes are displayed", !resourcePage.isDraftingNotesOptionsDisplayed()
				&& resourcePage.getNotesIconsCount() == 0);
	}

	@Then("^maintained icon is (displayed|Not displayed) on the document right hand panel$")
	public void relatedContentLinkIsDisplayedOnTheRightHandPanel(String display) throws Throwable {
		WebElement element = rightPanelPage.documentStatus();
		boolean classContainsGreenStatus = element.getAttribute("class").contains("co_greenStatus");
		if (display.equals("displayed")) {
			assertTrue("Maintained icon is not displayed", classContainsGreenStatus);
		} else {
			Assert.assertFalse("Maintained icon is displayed", classContainsGreenStatus);
		}

	}

}
