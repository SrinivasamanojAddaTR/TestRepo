package com.thomsonreuters.step_definitions.uk.legalUpdates.web;

import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.legalUpdates.*;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class CurrentAwarenessCommonSteps extends BaseStepDef {

    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private HomePage homepage = new HomePage();
    private LegalUpdatesResultsPage legalUpdatesResultsPage = new LegalUpdatesResultsPage();
    private ResearchFoldersWidget researchFoldersWidget = new ResearchFoldersWidget();
    private LegalUpdatesPracticeAreaPage legalUpdatesPracticeAreaPage = new LegalUpdatesPracticeAreaPage();
    private LegalUpdatesWidgetPage legalUpdatesWidget = new LegalUpdatesWidgetPage();
    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private CalendarWidget calendarWidget = new CalendarWidget();
    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();
    private LegalUpdatesTopicPage legalUpdatesTopicPage = new LegalUpdatesTopicPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();

    private final String SPECIFIED_PRACTICE_AREA_LEGAL_UPDATES_RESULTS_PAGE = "Construction";
    private final String MEDIA_AND_TELECOMS = "/Browse/Home/Practice/MediaTelecoms";
    private final String FULL_CALENDAR_PAGE = "/Browse/Home/UnitedKingdomEvents/PensionsEvents";
    private final String RSS_PAGE_TITLE = "Subscribe to Practical Law updates via RSS";

    @Given("^a user is on a legal updates results page$")
    public void aUserIsOnALegalUpdatesResultsPage() throws Throwable {
        homepage.selectResourceTab();
        homepage.legalUpdatesContentLink().click();
        legalUpdatesPracticeAreaPage.waitForPageToLoadAndJQueryProcessing();
        legalUpdatesPracticeAreaPage.specificPracticeAreaLink(SPECIFIED_PRACTICE_AREA_LEGAL_UPDATES_RESULTS_PAGE).click();
        knowHowSearchResultsPage.waitForSearchResults();
    }

    @Given("^a user navigate to a \"(.*?)\" Topic page from a \"(.*?)\" Practice Area page$")
    public void aUserNavigateToATopicPageFromAPracticeAreaPage(String topicName, String paName) throws Throwable {
        homepage.practiceAreasTab().click();
        homepage.selectLinkPresentOnTab(paName);
        homepage.waitForPageToLoad();
        legalUpdatesTopicPage.topicsLink(topicName).click();
        legalUpdatesTopicPage.waitForPageToLoad();
    }

    @Given("^a user is on legal updates results page number \"(.*?)\"$")
    public void aUserIsOnLegalUpdatesResultsPageNumber(String curentPageNumber) throws Throwable {
        if (!legalUpdatesResultsPage.currentPageNumberLabel().getText().equals(curentPageNumber)) {
            legalUpdatesResultsPage.specifiedPageNumberLink(curentPageNumber).click();
            legalUpdatesResultsPage.waitForPageToLoad();
            knowHowSearchResultsPage.waitForSearchResults();
        }
    }

    @Given("^a user is on a subscription panel$")
    public void a_user_is_on_a_subscription_panel() throws Throwable {
        practicalLawHomepage.legalUpdatesContentLink().click();
        legalUpdatesPracticeAreaPage.waitForPageToLoadAndJQueryProcessing();
        legalUpdatesPracticeAreaPage.legalUpdatesAllWidgetsLink().click();
        legalUpdatesPracticeAreaPage.waitForPageToLoadAndJQueryProcessing();
        legalUpdatesWidget.subscribeButton().click();
    }

    @Given("^user cleans search folder$")
    public void userCleansSearchFolder() throws Throwable {
        practicalLawHomepage.researchFoldersWidgetLink().click();
        researchFoldersWidget.waitForPageToLoadAndJQueryProcessing();
        try {
            if (researchFoldersWidget.emptyFolderTextMessage().isDisplayed()) {
                LOG.info("Research Folder is empty");
            }
        } catch (NoSuchElementException e) {
            researchFoldersWidget.selectAllCheckBox().click();
            researchFoldersWidget.deleteDocumentFromFolderButton().click();
            researchFoldersWidget.waitForPageToLoadAndJQueryProcessing();
        }
    }

    @Given("^a user is on the Media & Telecoms practice area page$")
    public void aUserIsOnTheMediaTelecomsPracticeAreaPage() throws Throwable {
        navigationCobalt.navigateToPLUKPlus(MEDIA_AND_TELECOMS);
        legalUpdatesPracticeAreaPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^the user should be presented with the RSS information page$")
    public void theUserShouldBePresentedWithTheRSSInformationPage() throws Throwable {
        assertTrue("RSS button is absent on Legal Updates Widget", standardDocumentPage.documentTitle().getText().equals(RSS_PAGE_TITLE));
    }

    @Given("^a user is viewing the month selector on the mini calendar$")
    public void aUserIsViewingTheMonthSelectorOnTheMiniCalendar() throws Throwable {
        navigationCobalt.navigateToPLUKPlus("/browse/home/legalupdateslightbox");
        legalUpdatesPracticeAreaPage.waitForPageToLoadAndJQueryProcessing();
        calendarWidget.miniCalendarSelectedOptionInDropDown().click();
    }

    @Given("^user is on full calendar view page$")
    public void userIsOnFullCalendarViewPage() throws Throwable {
        navigationCobalt.navigateToPLUKPlus(FULL_CALENDAR_PAGE);
        calendarWidget.waitForPageToLoadAndJQueryProcessing();
    }

    @Given("^a user is on a legal updates results page \"(.*?)\"$")
    public void aUserIsOnALegalUpdatesResultsPage(String legalUpdatesResultsPageUrl) throws Throwable {
        navigationCobalt.navigateToPLUKPlus(legalUpdatesResultsPageUrl);
        legalUpdatesResultsPage.waitForPageToLoad();
    }

    @Given("^a user is on a \"(.*?)\" PA page$")
    public void aUserIsOnAPAPage(String practiceArea) throws Throwable {
        homepage.practiceAreasTab().click();
        legalUpdatesPracticeAreaPage.specificPracticeAreaLink(practiceArea).click();
        legalUpdatesTopicPage.waitForPageToLoad();
    }

}