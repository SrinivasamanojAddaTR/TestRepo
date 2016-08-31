package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;

import java.util.Set;

import static org.junit.Assert.assertTrue;

public class abilityToStayOnSamePageAsOpenWebUserAfterTimeout extends BaseStepDef {
	
	private WLNHeader wlnHeader = new WLNHeader();
	private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
	private String expectedPageTitle;
	private String expectedPageTitleForFirstTab;
	private String expectedPageTitleForSecondTab;
	private String windowHandleFirstTab;
	private String windowHandleSecondTab;
	
	private String bookmarkUrl;

	private String thirdPartySiteUrl = "https://www.yandex.com/";

	@Given("^the user saves the page url into bookmark$")
	public void theUserSavesUrlIntoBookmark() {
		bookmarkUrl = practicalLawHomepage.getCurrentUrl();
		expectedPageTitle = navigationCobalt.getPageTitle();
	}
	
	@When("^the user goes to third-party site and stays there for \"(\\d+)\" seconds$")
	public void theUserGoesToThirdPartySiteAndStays(int sec) throws InterruptedException {
		wlnHeader.goTo(thirdPartySiteUrl);
		// This sleep is needed to emulate user idle within system please do not remove!
		Thread.sleep(sec * 1000);
	}

	@When("^the user selects a bookmark$")
	public void theUserSelectsBookmark() {
        wlnHeader.goTo(bookmarkUrl);
		navigationCobalt.waitForPageToLoadAndJQueryProcessing();
	}

	@When("^he has a session open and timed out$")
	public void heHasASessionOpenAndTimedOut() throws Throwable {
		expectedPageTitle = navigationCobalt.getPageTitle();
		//This sleep is needed to emulate user idle within system please do not remove!
		Thread.sleep(200000);
	}
	
	@When("^he has a session open and timed out after \"(\\d+)\" sec$")
	public void heHasASessionOpenAndTimedOutAfter(int sec) throws Throwable {
		expectedPageTitle = navigationCobalt.getPageTitle();
		//This sleep is needed to emulate user idle within system please do not remove!
		Thread.sleep(sec*1000);
	}
	
	@When("^the user opens new tab and switch on it$")
	public void userOpensNewTab() throws Throwable {
		expectedPageTitleForFirstTab=navigationCobalt.getPageTitle();
		windowHandleFirstTab=getDriver().getWindowHandle();
		wlnHeader.header().sendKeys(Keys.valueOf("CONTROL")+"n");
        wlnHeader.switchToOpenedWindow();
		windowHandleSecondTab=getDriver().getWindowHandle();
	}
	
	@When("^the user saves the page title for second tab$")
	public void userSavesPageUrlForSecondTab() throws Throwable {
		expectedPageTitleForSecondTab = navigationCobalt.getPageTitle();
	}

	@Then("^the user is presented with a warning message that session is expired$")
	public void warningMessageDisplayed() throws Throwable {
		assertTrue("Session time out pop up is not visible", practicalLawHomepage.isTimeoutPopUpPresent(100));
		//This sleep is needed to emulate user idle within system please do not remove!
		Thread.sleep(100000);
	}
	
	@Then("^the user is presented with a warning message that session is expired on all tabs$")
	public void warningMessageDisplayedOnAllTabs() throws Throwable {
        Set<String> windowHandles = getDriver().getWindowHandles();
        for (String windowHandle : windowHandles) {
            wlnHeader.switchToWindow(windowHandle);
        	assertTrue("Session time out pop up is not visible on page "+navigationCobalt.getPageTitle(), practicalLawHomepage.isTimeoutPopUpPresent(200));  
        }		
		//This sleep is needed to emulate user idle within system please do not remove!
		Thread.sleep(100000);
	}
	
	@When("^the user refreshes the page$")
	public void refreshPage() throws Throwable {
		practicalLawHomepage.refreshPage();
	}
	
	@When("^the user refreshes the first and the second pages$")
	public void refreshTwoPages() throws Throwable {
        wlnHeader.switchToWindow(windowHandleFirstTab);
		practicalLawHomepage.refreshPage();
        wlnHeader.switchToWindow(windowHandleSecondTab);
		practicalLawHomepage.refreshPage();
	}
	
	@When("^the user saves the page title$")
	public void savePageTitle() throws Throwable {
		expectedPageTitle = navigationCobalt.getPageTitle();
	}
	
	@Then("^he should stay on same (?:document|search|category) page as OpenWeb user$")
	public void heShouldStayOnSameDocumentPageAsOpenWebUser() throws Throwable {
		navigationCobalt.waitForPageToLoadAndJQueryProcessing();
		String currentPageTitle = navigationCobalt.getPageTitle();
		assertTrue("User was redirected to another page after timed out session, the current title is "+currentPageTitle, expectedPageTitle.equals(currentPageTitle));	
		assertTrue("User is logged in", wlnHeader.isSignInLinkPresent());
	}

	@Then("^he should stay on same (?:document|search|category) page as OpenWeb user on (first|second) tab$")
	public void heShouldStayOnSameDocumentPageAsOpenWebUserOnSpecifiedTab(String tab) throws Throwable {
		verifyTabTitle(tab);
		assertTrue("User is logged in", wlnHeader.isSignInLinkPresent());
	}
	
	@Then("^user gets redirected to the (?:document|search|category) page that he was visiting and is logged in$")
	public void userGetsRedirectedToTheDocumentPageThatHeWasVisiting() throws Throwable {
		navigationCobalt.waitForPageToLoad();
		String currentPageTitle = navigationCobalt.getPageTitle();
		assertTrue("User was redirected to another page after new session from page was started",
				expectedPageTitle.equals(currentPageTitle));
		assertTrue("User is not logged in", !wlnHeader.isSignInLinkPresent());
	}
	
	@Then("^user gets redirected to the (?:document|search|category) page on (first|second) tab that he was visiting and is logged in$")
	public void userGetsRedirectedToTheDocumentPageThatHeWasVisitingOnSpecifiedTab(String tab) throws Throwable {
		verifyTabTitle(tab);
		assertTrue("User is not logged in", !wlnHeader.isSignInLinkPresent());
	}
	
	private void verifyTabTitle(String tab){
		String currentPageTitle;
		navigationCobalt.waitForPageToLoadAndJQueryProcessing();
		switch (tab) {
		case "first":
            wlnHeader.switchToWindow(windowHandleFirstTab);
			currentPageTitle = navigationCobalt.getPageTitle();
			assertTrue("User was redirected to another page after timed out session, the current title is "+currentPageTitle, expectedPageTitleForFirstTab.equals(currentPageTitle));
			break;
		case "second":
            wlnHeader.switchToWindow(windowHandleSecondTab);
			currentPageTitle = navigationCobalt.getPageTitle();
			assertTrue("User was redirected to another page after timed out session, the current title is "+currentPageTitle, expectedPageTitleForSecondTab.equals(currentPageTitle));
			break;
		}
	}
	
	@Then("^user closes second page$")
	public void userClosesSecondPage() throws Throwable {
        wlnHeader.switchToWindow(windowHandleSecondTab);
        getDriver().close();
        wlnHeader.switchToWindow(windowHandleFirstTab);
	}

}