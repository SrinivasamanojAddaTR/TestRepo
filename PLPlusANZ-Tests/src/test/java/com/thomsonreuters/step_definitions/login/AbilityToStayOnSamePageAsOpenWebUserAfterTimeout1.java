package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.pages.footer.WLNFooter;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.utils.TimeoutUtils;
import com.thomsonreuters.pageobjects.utils.document.StandardDocumentUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;

import static org.junit.Assert.assertTrue;

public class AbilityToStayOnSamePageAsOpenWebUserAfterTimeout1 extends BaseStepDef {

    private WLNHeader wlnHeader = new WLNHeader();
    private WLNFooter wlnFooter = new WLNFooter();
    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private StandardDocumentUtils standartDocumentUtils = new StandardDocumentUtils();
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
        expectedPageTitle = practicalLawHomepage.getPageTitle();
    }

    @When("^the user goes to third-party site and stays there for \"(\\d+)\" seconds$")
    public void theUserGoesToThirdPartySiteAndStays(int sec) throws InterruptedException {
        wlnHeader.navigate(thirdPartySiteUrl);
        TimeoutUtils.sleepInSeconds(sec);
    }

    @When("^the user selects a bookmark$")
    public void theUserSelectsBookmark() {
        wlnHeader.navigate(bookmarkUrl);
        wlnHeader.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^he has a session open and timed out$")
    public void heHasASessionOpenAndTimedOut() throws Throwable {
        expectedPageTitle = practicalLawHomepage.getPageTitle();
        TimeoutUtils.sleepInSeconds(200);
    }

    @When("^he has a session open and timed out after \"(\\d+)\" sec$")
    public void heHasASessionOpenAndTimedOutAfter(int sec) throws Throwable {
        expectedPageTitle = practicalLawHomepage.getPageTitle();
        TimeoutUtils.sleepInSeconds(sec);
    }

    @When("^the user opens new tab and switch on it$")
    public void userOpensNewTab() throws Throwable {
        expectedPageTitleForFirstTab = practicalLawHomepage.getPageTitle();
        windowHandleFirstTab = getDriver().getWindowHandle();
        ((JavascriptExecutor) getDriver()).executeScript("window.open('about:blank','_blank');");
        wlnHeader.switchToOpenedWindow();
        windowHandleSecondTab = getDriver().getWindowHandle();
    }

    @When("^the user saves the page title for second tab$")
    public void userSavesPageUrlForSecondTab() throws Throwable {
        expectedPageTitleForSecondTab = practicalLawHomepage.getPageTitle();
    }

    @Then("^the user is presented with a warning message that session is expired$")
    public void warningMessageDisplayed() throws Throwable {
        assertTrue("Session time out pop up is not visible", practicalLawHomepage.isTimeoutPopUpPresent(100));
        TimeoutUtils.sleepInSeconds(150);
    }

    @Then("^the user is presented with a warning message that session is expired on all tabs$")
    public void warningMessageDisplayedOnAllTabs() throws Throwable {
        Set<String> windowHandles = getDriver().getWindowHandles();
        for (String windowHandle : windowHandles) {
            wlnHeader.switchToWindow(windowHandle);
            assertTrue("Session time out pop up is not visible on page " + practicalLawHomepage.getPageTitle(), practicalLawHomepage.isTimeoutPopUpPresent(200));
        }
        TimeoutUtils.sleepInSeconds(100);
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
        expectedPageTitle = practicalLawHomepage.getPageTitle();
    }

    @Then("^he should stay on same (?:document|search|category) page as OpenWeb user$")
    public void heShouldStayOnSameDocumentPageAsOpenWebUser() throws Throwable {
        practicalLawHomepage.waitForPageToLoadAndJQueryProcessing();
        String currentPageTitle = practicalLawHomepage.getPageTitle();
        assertTrue("User was redirected to another page after timed out session, the current title is " + currentPageTitle, expectedPageTitle.equals(currentPageTitle));
        assertTrue("User is logged in", wlnHeader.isSignInLinkPresent());
    }

    @Then("^he should stay on same (?:document|search|category) page as OpenWeb user on (first|second) tab$")
    public void heShouldStayOnSameDocumentPageAsOpenWebUserOnSpecifiedTab(String tab) throws Throwable {
        boolean isTabTitleExpected = false;
        if(tab.equals("first")){
            isTabTitleExpected = standartDocumentUtils.verifyTabTitle(windowHandleFirstTab,expectedPageTitleForFirstTab);

        }else {
            isTabTitleExpected = standartDocumentUtils.verifyTabTitle(windowHandleSecondTab,expectedPageTitleForSecondTab);
        }
        assertTrue("User was redirected to another page after timed out session", isTabTitleExpected);
        assertTrue("User is logged in", wlnHeader.isSignInLinkPresent());
    }

    @Then("^user gets redirected to the (?:document|search|category) page that he was visiting and is logged in$")
    public void userGetsRedirectedToTheDocumentPageThatHeWasVisiting() throws Throwable {
        practicalLawHomepage.waitForPageToLoad();
        String currentPageTitle = practicalLawHomepage.getPageTitle();
        assertTrue("User was redirected to another page after new session from page was started",
                expectedPageTitle.equals(currentPageTitle));
        assertTrue("User is not logged in", !wlnFooter.isSignInLinkPresent());
    }

    @Then("^user gets redirected to the (?:document|search|category) page on (first|second) tab that he was visiting and is logged in$")
    public void userGetsRedirectedToTheDocumentPageThatHeWasVisitingOnSpecifiedTab(String tab) throws Throwable {
        boolean isTabTitleExpected = false;
        if(tab.equals("first")){
            isTabTitleExpected = standartDocumentUtils.verifyTabTitle(windowHandleFirstTab,expectedPageTitleForFirstTab);

        }else {
            isTabTitleExpected = standartDocumentUtils.verifyTabTitle(windowHandleSecondTab,expectedPageTitleForSecondTab);
        }
        assertTrue("User was redirected to another page after timed out session", isTabTitleExpected);
        assertTrue("User is not logged in", !wlnHeader.isSignInLinkPresent());
    }

    @Then("^user closes second page$")
    public void userClosesSecondPage() throws Throwable {
        wlnHeader.switchToWindow(windowHandleSecondTab);
        getDriver().close();
        wlnHeader.switchToWindow(windowHandleFirstTab);
    }
}