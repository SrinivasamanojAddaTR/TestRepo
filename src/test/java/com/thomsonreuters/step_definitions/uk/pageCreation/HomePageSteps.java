package com.thomsonreuters.step_definitions.uk.pageCreation;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.utils.document.StandardDocumentUtils;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class HomePageSteps extends BaseStepDef {

    private HomePage homePage = new HomePage();
    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private PracticalLawHomepage plcHomePage = new PracticalLawHomepage();
    private WLNHeader wlnHeader = new WLNHeader();
    private StandardDocumentUtils standardDocumentUtils = new StandardDocumentUtils();
    private FooterUtils footerUtils = new FooterUtils();

    @Then("^user can view three tabs: Practice Areas, Resources and International$")
    public void userCanViewThreeTabsPracticeAreasResourcesAndInternational() throws Throwable {
        assertTrue("The 'Practice Area' tab is NOT displayed ", homePage.practiceAreasTab().isDisplayed());
        assertTrue("The 'Resources' tab is NOT displayed ", homePage.resourcesTab().isDisplayed());
        assertTrue("The 'International' tab is NOT displayed ", homePage.internationalTab().isDisplayed());
    }

    @And("^the user verifies that default Tab is '(.*)'$")
    public void theUserVerifiesThatDefaultTabIsTab(String tabName) throws Throwable {
        assertTrue("The 'default' tab is NOT " + tabName, homePage.activeTab().getText().contains(tabName));
    }

    @And("^the user selects Tab '(.*)' if not alrdeay selected$")
    public void theUserselectsTab(String tabName) throws Throwable {
        if (!homePage.activeTab().getText().contains(tabName)) {
            homePage.waitForElementPresent(By.linkText("Topics")).click();
            homePage.waitForPageToLoad();
        }
    }

    @Given("^the user is on the home page$")
    public void aUserIsOnTheHomePage() throws Throwable {
        navigationCobalt.navigateToHomePage();
    }

    @When("^the user is in Page '(.*)'$")
    public void theUserIsInPage(String pages) throws Throwable {
        List<String> links = Arrays.asList(pages.split(">"));
        for (String link : links) {
            if (link.contains("/")) {
                navigationCobalt.navigateToRelativeURL(link);
                navigationCobalt.waitForPageToLoad();
            }
        }
    }

    @And("^the user is in page '(.*)' with page Title '(.*)'$")
    public void theUserIsInPageResourcecsBooksOnlineWithPageTitle(String pages, String expectedTitle) throws Throwable {
        List<String> links = Arrays.asList(pages.split(">"));
        for (String link : links) {
            if (link.contains("/")) {
                navigationCobalt.navigateToRelativeURL(link);
            } else {
                if (link.equalsIgnoreCase("Browse Menu")) {
                    wlnHeader.browseMenuButton().click();
                } else {
                    navigationCobalt.waitForElementPresent(By.linkText(link)).click();
                }
                navigationCobalt.waitForPageToLoad();
            }
        }
        assertTrue(wlnHeader.pageHeaderLabel().getText().equals(expectedTitle));
    }

    @Then("^the user verifies that following '(.*)' links are displayed$")
    public void theUserVerifiesThatFollowingLinksAreDisplayed(String tab, List<String> links) throws Throwable {
        if (tab.equalsIgnoreCase("Practice areas")) {
            List<String> linksRetrieved = homePage.getPracticeAreasLinks();
            for (String link : links) {
                assertTrue("The 'Practice areas' link " + link + " is NOT displayed", linksRetrieved.contains(link));
            }
        }
    }

    @Then("^the user verifies that corresponding '(.*)' links are displayed$")
    public void the_user_verifies_that_corresponding_Topics_links_are_displayed(List<String> topicLinks) throws Throwable {
        List<String> linksRetrieved = homePage.getTopicsLinks();
        for (String link : topicLinks) {
            link = link.replaceAll("@@", ",");
            assertTrue("The 'Topic' link " + link + " is NOT displayed", linksRetrieved.contains(link));
        }
    }

    @When("^the user clicks link '(.*)' on '(.*)' page$")
    public void theUserClicksLinkOnPage(String link, String page) throws Throwable {
        if (!link.equals("")) {
            if (page.contains("Browse")) {
                homePage.findChildElement(homePage.getPracticeAreasBrowseMenuContainer(), By.linkText((link))).click();
            } else {
                try {
                    homePage.waitForExpectedElement(By.linkText(link), 2).click();
                } catch (Exception e) {
                    homePage.waitForExpectedElement(By.partialLinkText(link), 5).click();
                }
            }
            Thread.sleep(2000);
            homePage.waitForPageToLoad();
        }
    }

    @Then("^the user verifies that link '(.*)' is  displayed on '(.*)'$")
    public void the_user_verifies_that_link_is_displayed_on_the_page(String link, String page) throws Throwable {
        assertTrue("The link " + link + " is NOT displayed on " + page, homePage.waitForExpectedElement(By.linkText(link)).isDisplayed());
    }
    
    @Then("^the user verifies that link '(.*)' is  not displayed on '(.*)'$")
    public void the_user_verifies_that_link_is_not_displayed_on_the_page(String link, String page) throws Throwable {
        Assert.assertFalse("The link " + link + " is displayed on " + page, homePage.isElementPresent(By.linkText(link)));
    }

    @When("^the user clicks category link '(.*)'and topic link '(.*)' on '(.*)' page$")
    public void the_user_clicks_Category_and_topic_link_on__page(String link, String topicLink, String page) throws Throwable {
        homePage.waitForExpectedElement(By.linkText(link)).click();
        homePage.waitForPageToLoad();
        assertTrue("The Expected Category Page Title " + link + " is  NOT displayed", wlnHeader.pageHeaderLabel().getText().toLowerCase().contains(link.toLowerCase()));
        homePage.waitForExpectedElement(By.linkText(topicLink)).click();
        homePage.waitForPageToLoad();
        assertTrue("The Expected Topic Page Title " + topicLink + " is  NOT displayed", wlnHeader.pageHeaderLabel().getText().toLowerCase().contains(topicLink.toLowerCase()));
    }

    @Then("^the user verifies that the current PageTitle contains '(.*)'$")
    public void theUserVerifiesThatTheCurrentPageTitleContainsPageTitle(String pageTitle) throws Throwable {
        assertTrue("The Expected Page Title " + pageTitle + " is  NOT displayed", wlnHeader.pageHeaderLabel().getText().contains(pageTitle));
    }

    @Then("^user can view three links: Practice Areas, Resources and International$")
    public void userCanViewThreeLinksPracticeAreasResourcesAndInternational() throws Throwable {
        assertTrue("The 'Practice Area' link is NOT displayed ", homePage.practiceAreasLink().isDisplayed());
        assertTrue("The 'Resources' link is NOT displayed ", homePage.resourcesLink().isDisplayed());
        assertTrue("The 'International' link is NOT displayed ", homePage.internationalLink().isDisplayed());
    }

    @And("^the user verifies that default Link is '(.*)'$")
    public void theUserVerifiesThatDefaultLinkIs(String link) throws Throwable {
        assertTrue("The 'default' link is NOT " + link, homePage.activeLink().getText().contains(link));
    }

    @When("^the user clicks button 'Browse Menu' on the Home Page$")
    public void theUserClicksButtonBrowseMenuOnTheHomePage() throws Throwable {
        wlnHeader.browseMenuButton().click();
    }

    @Then("^the user verifies that following 'Practice areas' links are displayed in in menu 'Browse Menu' on the Home Page$")
    public void theUserVerifiesThatFollowingPracticeAreasLinksAreDisplayedInInMenuBrowseMenuOnTheHomePage(List<String> links) throws Throwable {
        List<String> linksRetrieved = homePage.getPracticeAreasLinksInBrowseMenu();
        for (String link : links) {
            assertTrue("The 'Practice areas' link " + link + " is NOT displayed in Browse Menu", linksRetrieved.contains(link));
        }
    }

    @When("^the user '(is|is not)' presented with the cookie consent message$")
    public void theUserIsPresentedWithTheCookieConsentMessage(String arg1) throws Throwable {
        if (arg1.equalsIgnoreCase("is")) {
            assertTrue(plcHomePage.cookieConsentMessage().isDisplayed());
        } else if (arg1.equalsIgnoreCase("is not")) {
            List<WebElement> cookieMessage = getDriver().findElements(By.id("CookieConsentMessage"));
            assertTrue(cookieMessage.isEmpty());
        }
    }

    @When("^the user clicks on close button on the cookie consent message$")
    public void theUserClicksOnCloseButtonOnTheCookieConsentMessage() throws Throwable {
        plcHomePage.closeCookieConsentMessage();
    }

    @When("^the user clicks on the \"(.*?)\" link on the cookie consent message$")
    public void theUserClicksOnTheLinkOnTheCookieConsentMessage(String arg1) throws Throwable {
        plcHomePage.cookieConsentLink(arg1).click();
    }
    @When("^user navigates to the \"(.*?)\" tool by clicking \"(.*?)\" button using \"(.*?)\" tab on the homepage$")
    public void userNavigatesToTheToolByClickingButtonUsingTabOnTheHomepage(String arg1, String arg2, String tabName) throws Throwable {
        homePage.specificTab(tabName).click();
        homePage.homePageStartComparingButton().click();
    }

    @When("^user selects the topic \"(.*?)\"$")
    public void userSelectsTheTopicAndClicksOnLink(String topicName) throws Throwable {
        homePage.selectTopicPageTopicLink(topicName).click();
     }

    @When("^user selects first questions and clicks on \"(.*?)\" button$")
    public void userSelectsFirstTwoQuestionsAndClicksOnButton(String arg1) throws Throwable {
        footerUtils.closeDisclaimerMessage();
        homePage.selectQuestionsPageCheckboxList().get(0).click();
        homePage.selectQPageSelectJurisdictionButton().click();
    }

    @When("^user selects two following countries and clicks on \"(.*?)\" button$")
    public void userSelectsTwoFollowingCountriesAndClicksOnButton(String arg1, List<String> countryList) throws Throwable {
        for(String country : countryList){
           homePage.selectJurisdictionCheckbox(country).click();
        }
        footerUtils.closeDisclaimerMessage();
        homePage.jurisdictionPageCompareButton().click();
    }

    @When("^user sees the comparison page and clicks on the \"(.*?)\" button on L\\.H\\.S$")
    public void userSeesTheComparisonPageAndClicksOnTheButtonOnLHS(String edit) throws Throwable {
        homePage.waitForPageToLoadAndJQueryProcessing();
        homePage.waitForElementVisible(homePage.comparePageCountryEditButton(), 10).click();
     }

    @Then("^user should see \"(.*?)\" popup with the list of countries$")
    public void userShouldSeePopupWithTheListOfCountries(String arg1) throws Throwable {
       Is.is(homePage.editPopupSaveChangesButton().isDisplayed());
    }

    @Then("^user selects the \"(.*?)\" country and save it\\.$")
    public void userSelectsTheCountryAndSaveIt(String country) throws Throwable {
        homePage.scrollIntoViewAndClick(homePage.selectJurisdictionCheckbox(country));
        homePage.scrollIntoViewAndClick(homePage.editPopupSaveChangesButton());
    }

    @Then("^user should see the \"(.*?)\" appearing on L\\.H\\.S column in the comparison tool and on the page$")
    public void userShouldSeeTheAppearingOnLHSColumnInTheComparisonToolAndOnThePage(String country) throws Throwable {
        homePage.waitForPageToLoad();
        homePage.waitForPageToLoadAndJQueryProcessing();
        Is.is(homePage.comparePageLeftColumnCountryNameLink(country).isDisplayed());
    }

    @Then("^the Practice Area section links are present and the first '(.*)' related links are valid for every PA section$")
    public void thePracticeAreaSectionLinksArePresentAndAllRelatedLinksAreValidForEveryPASection(int linksCountToCheck) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<WebElement> sectionsLinks = homePage.getSelectedSectionLinks();
        for (WebElement sectionLink : sectionsLinks) {
            softAssertions.assertThat(standardDocumentUtils.isLinksAreValidInSection(sectionLink, linksCountToCheck))
                    .withFailMessage(standardDocumentUtils.getPracticeAreaLinksErrMsg())
                    .isTrue();
        }
        softAssertions.assertAll();
    }

}