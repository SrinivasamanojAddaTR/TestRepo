package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.SortOptions;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.folders.SaveToPopup;
import com.thomsonreuters.pageobjects.pages.landingPage.*;
import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesWidgetPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.DocumentDeliveryPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchSearch.BaseResultsPage;
import com.thomsonreuters.pageobjects.pages.search.*;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class SearchJavaTest1 extends BaseStepDef {

    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private WhatsMarketSearchResultsPage whatsMarketSearchResultsPage = new WhatsMarketSearchResultsPage();
    private PracticalLawUKCategoryPage practicalLawUKCategoryPage = new PracticalLawUKCategoryPage();
    private KnowHowDocumentPage knowHowDocumentPage = new KnowHowDocumentPage();
    private InternationalLandingPage internationalLandingPage = new InternationalLandingPage();
    private WhatsMarketDocumentPage whatsMarketDocumentPage = new WhatsMarketDocumentPage();
    private WhatsMarketHomePage whatsMarketHomePage = new WhatsMarketHomePage();
    private CasesSearchResultsPage casesSearchResultsPage = new CasesSearchResultsPage();
    private CasesDocumentPage casesDocumentPage = new CasesDocumentPage();
    private UKPLCSitePage ukplcSitePage = new UKPLCSitePage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private JournalsSearchResultsPage journalsSearchResultsPage = new JournalsSearchResultsPage();
    private LegalUpdatesWidgetPage legalUpdatesWidget = new LegalUpdatesWidgetPage();
    private ResourcesPage resourcesPage = new ResourcesPage();
    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private SearchScopeControl searchScopeControl = new SearchScopeControl();
    private WhatsMarketComparisonReportPage whatsMarketComparisonReportPage = new WhatsMarketComparisonReportPage();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private DocumentDeliveryPage documentDeliveryPage = new DocumentDeliveryPage();
    private CommonMethods commonMethods = new CommonMethods();
    private FoldersUtils foldersUtils = new FoldersUtils();
    private BaseResultsPage baseResultPage = new BaseResultsPage();
    private SearchUtils searchUtils = new SearchUtils();
    private SaveToPopup saveToPopup = new SaveToPopup();

    private List<String> actualSuggestions;
    private String searchTerm;

    @When("^the user enters (\\d+) characters into the search box \"(.*?)\"$")
    public void theUserEntersCharactersIntoTheSearchBox(int arg1, String arg2) throws Throwable {
        practicalLawUKCategoryPage.freeTextField().sendKeys(arg2);
    }

    @When("^the user selects the suggested term \"(.*?)\"$")
    public void theUserSelectsTheSuggestedTerm(String arg1) throws Throwable {
        practicalLawUKCategoryPage.suggestedTerm(arg1).click();
        knowHowSearchResultsPage.waitForSearchResults();
        searchTerm = arg1;
    }

    @When("^the user mouse over the suggested term \"(.*?)\"$")
    public void theUserMouseOverTheSuggestedTerm(String arg1) throws Throwable {
        practicalLawUKCategoryPage.mouseOverOnSuggestedSearchResult(arg1);
    }

    @When("^the user verifies that the term \"(.*?)\" appears in the search box$")
    public void theUserVerifiesThatTheTermAppearsInTheSearchBox(String arg1) throws Throwable {
        assertTrue(practicalLawUKCategoryPage.freeTextField().getAttribute("value").equals(arg1));
    }

    @Then("^the user edits the text of the suggested search term and add additional text \"(.*?)\"$")
    public void theUserEditsTheTextOfTheSuggestedSearchTermAndAddAdditionalText(String arg1) throws Throwable {
        practicalLawUKCategoryPage.freeTextField().sendKeys(" " + arg1);
    }

    @And("^the user edits the text of the suggested search term and add additional text \"(.*?)\" before the search is initiated$")
    public void theUserEditsTheSuggestedSearch(String arg1) throws Throwable {
        practicalLawUKCategoryPage.freeTextField().sendKeys(" " + arg1);
    }

    @Then("^the user can submit the search request$")
    public void theUserCanSubmitTheSearchRequest() throws Throwable {
        practicalLawUKCategoryPage.searchButton().click();
        knowHowSearchResultsPage.waitForSearchResults();
    }

    @Then("^the user can open the first know how search result \"(.*)\"$")
    public void theUserCanOpenTheFirstKnowHowSearchResult(String arg1) throws Throwable {
        knowHowSearchResultsPage.knowHowSearchResultTitle(arg1).click();
    }

    @Then("^the user can verify that the full text of the know how document contains the search terms \"(.*?)\" and \"(.*?)\"$")
    public void theUserCanVerifyThatTheFullTextOfTheKHDocumentContainsTheSearchTermsAnd(String firstTerm, String secondTerm) throws Throwable {
        String docText = null;
        docText = knowHowDocumentPage.getFullText().toLowerCase();
        for (String term : firstTerm.toLowerCase().split(" ")) {
            assertTrue(docText.contains(term));
        }
        for (String term : secondTerm.toLowerCase().split(" ")) {
            assertTrue(docText.contains(term));
        }
    }

    @Then("^the user can verify that the full text of the document contains the search term \"(.*?)\"$")
    public void theUserCanVerifyThatTheFullTextOfTheKHDocumentContainsTheSearchTermsAnd(String firstTerm) throws Throwable {
        String docText = knowHowDocumentPage.getFullText().toLowerCase();
        for (String term : firstTerm.toLowerCase().split(" ")) {
            assertTrue("Document body does not contain the search term", docText.contains(term));
        }
    }

    @Then("^the user can verify that the full text of the whats market document contains the search terms \"(.*?)\" and \"(.*?)\"$")
    public void theUserCanVerifyThatTheFullTextOfTheWMDocumentContainsTheSearchTermsAnd(String firstTerm, String secondTerm) throws Throwable {
        String docText = null;
        docText = whatsMarketDocumentPage.getFullText().toLowerCase();
        for (String term : firstTerm.toLowerCase().split(" ")) {
            assertTrue(docText.contains(term));
        }
        for (String term : secondTerm.toLowerCase().split(" ")) {
            assertTrue(docText.contains(term));
        }
    }

    @Then("^the user can open the first whats market search result \"(.*?)\"$")
    public void theUserCanOpenTheFirstWhatsMarketSearchResult(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.whatsMarketSearchResultTitle(arg1).click();
    }

    @Then("^the user uses their keyboard to navigate to the suggested term \"(.*?)\"$")
    public void theUserUsesTheirKeyBoardToNavigateToTheSuggestedTerm(String searchTerm) {
        List<Keys> keys = new ArrayList<Keys>();
        List<String> suggestions = knowHowSearchResultsPage.getSearchSuggestions();
        for (int i = 0; i < suggestions.indexOf(searchTerm); i++) {
            keys.add(Keys.ARROW_DOWN);
        }
        knowHowSearchResultsPage.useKeyBoard(keys, practicalLawUKCategoryPage.freeTextField());
    }

    @When("^has selected the homepage practice area link to \"(.*?)\"$")
    public void hasSelectedTheHomepagePracticeAreaLinkTo(String arg1) throws Throwable {
        practicalLawUKCategoryPage.practiceAreaLink(arg1).click();
    }

    @When("^has selected the topic link to \"([^\"]*)\"")
    public void hasSelectedTheTopicLinkTo(String arg1) throws Throwable {
        practicalLawUKCategoryPage.topicAreaLink(arg1).click();
    }

    @When("^only the selected Practice Area facet heading \"(.*?)\" and its topic children \"(.*?)\" etc are displayed$")
    public void onlyTheSelectedPracticeAreaFacetHeadingAndItsTopicChildrenEtcAreDisplayed(String arg1, String arg2) throws Throwable {
        assertTrue(searchResultsPage.practiceAreaFacetHeader(arg1).isDisplayed());
        assertTrue(searchResultsPage.practiceAreaTopicFacetCheckbox(arg2).isDisplayed());
    }

    @When("^the specific Subject Area \"([^\"]*)\" and its parent Practice Area \"([^\"]*)\" are displayed$")
    public void theSpecificSubjectAreaAndItsParentPracticeAreaAreDisplayed(String arg1, String arg2) throws Throwable {
        searchResultsPage.waitForPageToLoadAndJQueryProcessing();
        assertTrue(searchResultsPage.practiceAreaFacetHeader(arg1).isDisplayed());
        assertTrue(searchResultsPage.practiceAreaFacetHeader(arg2).isDisplayed());
    }

    @Then("^it is clear to the user that results restricted to the practice area because the product details contain the code \"([^\"]*)\"$")
    public void it_is_clear_to_the_user_that_results_restricted_to_the_practice_area_because_the_product_details_contain_the_code(String arg1) throws Throwable {
        WebElement products = ukplcSitePage.productsSection();
        assertTrue((products.getText().contains(arg1)));
    }

    @Then("^it is clear to the user that results are restricted to the subject area and practice area because the product details contain the code \"([^\"]*)\" and \"([^\"]*)\"$")
    public void it_is_clear_to_the_user_that_results_are_restricted_to_the_subject_area_and_practice_area_because_the_product_details_contain_the_code(String arg1, String arg2) throws Throwable {
        assertTrue(ukplcSitePage.isTopicAvailable(arg1));
        WebElement products = ukplcSitePage.productsSection();
        assertTrue((products.getText().contains(arg2)));
    }

    @When("^the user opens the link associated with result number \"([^\"]*)\"$")
    public void theUserOpensTheLinkAssociatedWithResultNumber(int arg1) throws Throwable {
        searchResultsPage.testLink(arg1).click();
    }

    @When("^the user can open the first journals search result \"([^\"]*)\"$")
    public void theUserCanOpenTheFirstJournalsSearchResult(String arg1) throws Throwable {
        journalsSearchResultsPage.journalsResult(arg1).click();
    }

    @Then("^the suggested journal search terms are displayed in alphabetical order$")
    public void theSuggestedJournalSearchTermsAreDisplayedInAlphabeticalOrder() {
        actualSuggestions = journalsSearchResultsPage.getSearchSuggestions();
        assertTrue(commonMethods.isSorted(actualSuggestions, SortOptions.ASC));
    }

    @Then("^no matching terms are found then no search terms are displayed to the user$")
    public void NoMatchingTermsAreFoundThenNoSearchTermsAreDisplayedToTheUser(List<String> expectedSuggestions) {
        actualSuggestions = knowHowSearchResultsPage.getSearchSuggestions();
        for (String suggestion : expectedSuggestions) {
            assertFalse(actualSuggestions.contains(suggestion.toUpperCase()));
        }
    }

    @Then("^the matching journal search terms are displayed to the user in the form of a list$")
    public void TheMatchingJournalSearchTermsAreDisplayedToTheUserInTheFormOfAList(List<String> expectedSuggestions) {
        actualSuggestions = knowHowSearchResultsPage.getSearchSuggestions();
        for (String suggestion : expectedSuggestions) {
            assertTrue(actualSuggestions.contains(suggestion.toUpperCase()));
        }
    }

    @When("^the user verifies that the option for sorting by relevance is displayed$")
    public void theUserVerifiesThatTheOptionForSortingByRelevanceIsDisplayed() throws Throwable {
        assertTrue(searchResultsPage.sortByDropdownSelectedOption().getText().trim().equals("Relevancy"));
    }

    @When("^the user verifies that the search hit is on the case analysis document \"([^\"]*)\"$")
    public void theUserVerifiesThatTheSearchHitIsOnTheCaseAnalysisDocument(String arg1) throws Throwable {
        assertTrue(casesSearchResultsPage.documentHit(arg1).isDisplayed());
    }

    @When("^the user verifies that the search hit is on the law report \"([^\"]*)\"$")
    public void theUserVerifiesThatTheSearchHitIsOnTheLawReport(String arg1) throws Throwable {
        assertTrue(casesSearchResultsPage.documentHit(arg1).isDisplayed());
    }

    @When("^the user verifies that the search hit is on the official transcript \"([^\"]*)\"$")
    public void theUserVerifiesThatTheSearchHitIsOnTheOfficialTranscript(String arg1) throws Throwable {
        assertTrue(casesSearchResultsPage.documentHit(arg1).isDisplayed());
    }

    @When("^the user selects the party name link \"([^\"]*)\"$")
    public void theUserSelectsThePartyNameLink(String arg1) throws Throwable {
        casesSearchResultsPage.caseTitle(arg1).click();
    }

    @When("^the case analysis document is displayed$")
    public void theCaseAnalysisDocumentIsDisplayed() throws Throwable {
        String text = casesDocumentPage.caseAnalysisFullText().getText();
        assertTrue(text.contains("Case Analysis"));
    }

    @When("^the full text judgment is displayed containing the text \"([^\"]*)\"$")
    public void theFullTextJudgmentIsDisplayedContainingTheText(String arg1) throws Throwable {
        assertTrue(casesDocumentPage.fullTextJudgment().getText().contains(arg1));
    }

    @When("^the user will be able to verify the presence of Party Names for result \"([^\"]*)\"$")
    public void theUserWillBeAbleToVerifyThePresenceOfPartyNamesForResult(String arg1) throws Throwable {
        assertTrue(casesSearchResultsPage.caseTitle(arg1).isDisplayed());
    }

    @When("^the user will be able to verify the presence of Court information for result \"([^\"]*)\"$")
    public void theUserWillBeAbleToVerifyThePresenceOfCourtInformationForResult(String arg1) throws Throwable {
        assertTrue(casesSearchResultsPage.courtDetailFirstResult(arg1).isDisplayed());
    }

    @When("^the user will be able to verify the presence of the judgment date for result \"([^\"]*)\"$")
    public void theUserWillBeAbleToVerifyThePresenceOfTheJudgmentDateForResult(String arg1) throws Throwable {
        assertTrue(casesSearchResultsPage.dateDetailFirstResult(arg1).isDisplayed());
    }

    @When("^the user is able to open and validate that the document displayed is the law report \"([^\"]*)\"$")
    public void theUserIsAbleToOpenAndValidateThatTheDocumentDisplayedIsTheLawReport(String arg1) throws Throwable {
        WebElement guid = casesSearchResultsPage.documentHitGuid(arg1);
        String text = guid.getAttribute("docguid");
        casesSearchResultsPage.caseTitle("1").click();
        assertTrue(getDriver().getCurrentUrl().contains(text));
    }

    @When("^the user selects the checkbox associated with result \"([^\"]*)\"$")
    public void theUserSelectsTheCheckboxAssociatedWithResult(String index) throws Throwable {
        searchResultsPage.waitForPageToLoad();
        WebElement resultItemCheckBox = searchResultsPage.resultCheckbox(index);
        searchResultsPage.scrollIntoViewAndClick(resultItemCheckBox);
    }

    @When("^the user selects the checkbox associated with whats market result \"([^\"]*)\"$")
    public void theUserSelectsTheCheckboxAssociatedWithWhatsMarketResult(String arg1) throws Throwable {
        searchResultsPage.resultCheckboxWhatsMarket(arg1).click();
    }

    @When("^the user verifies the checkbox associated with whats market result \"([^\"]*)\" is checked$")
    public void theUserVerifiesTheCheckboxAssociatedWithWhatsMarketResultIsChecked(String arg1) throws Throwable {
        searchResultsPage.resultCheckboxWhatsMarket(arg1).isSelected();
    }

    @When("^the user verifies the presence of a field entitled To$")
    public void theUserVerifiesThePresenceOfAFieldEntitledTo() throws Throwable {
        searchResultsPage.emailToField().isDisplayed();
    }

    @When("^the user verifies the presence of a field entitled Subject$")
    public void theUserVerifiesThePresenceOfAFieldEntitledSubject() throws Throwable {
        searchResultsPage.emailSubjectField().isDisplayed();
    }

    @When("^the user verifies the presence of a field entitled Email Note$")
    public void theUserVerifiesThePresenceOfAFieldEntitledEmailNote() throws Throwable {
        searchResultsPage.emailNoteField().isDisplayed();
    }

    @When("^the user verifies the presence of a checkbox for inclusion of table of contents$")
    public void theUserVerifiesThePresenceOfACheckboxForInclusionOfTableOfContents() throws Throwable {
        searchResultsPage.tableOfContentsOption().isDisplayed();
    }

    @When("^the user verifies the presence of an Email option$")
    public void theUserVerifiesThePresenceOfAnEmailOption() throws Throwable {
        searchResultsPage.emailButton().isDisplayed();
    }

    @When("^the user verifies the presence of an As option entitled \"([^\"]*)\"$")
    public void theUserVerifiesThePresenceOfAnAsOptionEntitled(String arg1) throws Throwable {
        searchResultsPage.asDropdownOption(arg1).isDisplayed();
    }

    @When("^the user selects the Email option$")
    public void theUserSelectsTheEmailOption() throws Throwable {
        searchResultsPage.emailButton().click();
    }

    @When("^the user verifies the presence of a pop up entitled Print Documents$")
    public void theUserVerifiesThePresenceOfAPopUpEntitledPrintDocuments() throws Throwable {
        searchResultsPage.acceptDialogIfAppears();
        searchResultsPage.printDocumentsPopUp().isDisplayed();
    }

    @When("^the user selects the print delivery option$")
    public void theUserSelectsThePrintDeliveryOption() throws Throwable {
        searchResultsPage.printDeliveryIcon().click();
    }

    @When("^the user verifies the presence of a Print option$")
    public void theUserVerifiesThePresenceOfAPrintOption() throws Throwable {
        searchResultsPage.printButton().isDisplayed();
    }

    @When("^the user selects the print option$")
    public void theUserSelectsThePrintOption() throws Throwable {
        searchResultsPage.printButton().click();
    }

    @When("^the user verifies that a pop up entitled Download This Document is displayed to the user$")
    public void theUserVerifiesThatAPopUpEntitledDownLoadThisDocumentIsDisplayedToTheUser() throws Throwable {
        searchResultsPage.downloadSingleDocumentPopUp().isDisplayed();
    }

    @When("^the user verifies that the product detail contains the practice area \"([^\"]*)\"$")
    public void theUserVerifiesThatTheProductDetailContainsThePracticeArea(String arg1) throws Throwable {
        assertTrue(knowHowDocumentPage.knowHowProductCodes(arg1).isDisplayed());
    }

    @When("^the user verifies that the product detail contains PLC Magazine \"([^\"]*)\"$")
    public void theUserVerifiesThatTheProductDetailContainsPLCMagazine(String arg1) throws Throwable {
        assertTrue(knowHowDocumentPage.knowHowProductCodes(arg1).isDisplayed());
    }

    @When("^the user verifies that the product detail contains the topic area \"([^\"]*)\"$")
    public void theUserVerifiesThatTheProductDetailContainsTheTopicArea(String arg1) throws Throwable {
        assertTrue(knowHowDocumentPage.topicInformation(arg1).isDisplayed());
    }

    @When("^the user verifies that the product detail contains at least one of the practice areas$")
    public void theUserVerifiesThatTheProductDetailContainsAtLeastOneOfThePracticeArea() throws Throwable {
        WebElement products = knowHowDocumentPage.productCodeSection();
        assertTrue((products.getText().contains("PLC UK Magazine (UK)")) || (products.getText().contains("PLC Arbitration")) || (products.getText().contains("PLC Arbitration - International"))
                || (products.getText().contains("PLC Business Development")) || (products.getText().contains("PLC UK Commercial)")) || (products.getText().contains("PLC EU Competition Law)"))
                || (products.getText().contains("PLC UK Competition Law")) || (products.getText().contains("PLC UK Construction")) || (products.getText().contains("PLC UK Corporate"))
                || (products.getText().contains("PLC Cross-border")) || (products.getText().contains("PLC UK Dispute Resolution")) || (products.getText().contains("PLC UK Employment"))
                || (products.getText().contains("PLC UK Environment")) || (products.getText().contains("PLC EU")) || (products.getText().contains("PLC UK Finance"))
                || (products.getText().contains("PLC UK Financial Services"))
                || (products.getText().contains("PLC Glossary")) || (products.getText().contains("IP&IT")) || (products.getText().contains("PLC UK Law Department"))
                || (products.getText().contains("PLC Law Firm Publications"))
                || (products.getText().contains("PLC Lawdepartment Global")) || (products.getText().contains("PLC multi-jurisdictional guides")) || (products.getText().contains("PLC UK Pensions"))
                || (products.getText().contains("PLC UK Private Client")) || (products.getText().contains("PLC UK Property")) || (products.getText().contains("PLC UK Public Sector"))
                || (products.getText().contains("PLC UK Restructuring and Insolvency")) || (products.getText().contains("PLC UK Share Schemes & Incentives"))
                || (products.getText().contains("PLC UK Tax")) || (products.getText().contains("PLC US Antitrust")) || (products.getText().contains("PLC US Bankruptcy")) || (products.getText().contains("PLC US Capital Markets & Securities"))
                || (products.getText().contains("PLC US Commercial")) || (products.getText().contains("PLC US Corporate and M&A")) || (products.getText().contains("PLC US Corporate & Securities"))
                || (products.getText().contains("PLC US Corporate & Securities")) || (products.getText().contains("PLC US Employee Benefits & Executive Compensation"))
                || (products.getText().contains("PLC US Environmental")) || (products.getText().contains("PLC US Federal Litigation")) || (products.getText().contains("PLC US Finance"))
                || (products.getText().contains("PLC US Glossary")) || (products.getText().contains("PLC US Intellectual Property & Technology"))
                || (products.getText().contains("PLC US Labor & Employment")) || (products.getText().contains("PLC US Law Department"))
                || (products.getText().contains("PLC US Real Estate")) || (products.getText().contains("PLC US Tax"))
                || (products.getText().contains("Practical Law The Journal (US)")) || (products.getText().contains("PLC Arbitration - International"))
                || (products.getText().contains("PLC Arbitration - England and Wales")) || (products.getText().contains("PLC US Litigation - New York"))
                || (products.getText().contains("China")) || (products.getText().contains("PLC Cross-border")) || (products.getText().contains("Commercial: International")));
    }

    @When("^the user is able to verify that sample results contain a date in the \"([^\"]*)\" format$")
    public void theUserIsAbleToVerifyThatSampleResultsContainADateInTheDDMMMYYYYFormat(String dateformat) throws Throwable {
        String dateString[] = knowHowSearchResultsPage.date().getText().split("Published on ");
        String dateToValidate = dateString[1];

        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        sdf.setLenient(false);
        //if not valid, it will throw ParseException
        Date date = sdf.parse(dateToValidate);
        LOG.info("Date is " + date);
    }

    @When("^the user is presented with a message offering a free trial in order to access the full resource$")
    public void theUserIsPresentedWithAMessageOfferingAFreeTrialInOrderToAccessTheFullResource() throws Throwable {
        knowHowDocumentPage.requestFreeTrialMessage().isDisplayed();
    }

    @When("^has selected the link to View All on the Legal Updates Widget$")
    public void hasSelectedTheLinkToViewAllOnTheLegalUpdatesWidget() throws Throwable {
        legalUpdatesWidget.veiwAllUpdatesLink().click();
    }

    @When("^the user is able to verify the presence of the resource title for the first result \"([^\"]*)\"$")
    public void theUserIsAbleToVerifyThePresenceOfTheResourceTitleForTheFirstResult(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.whatsMarketSearchResultTitle(arg1).isDisplayed();
    }

    @When("^the user is able to verify the presence of a date of announcement for the first result \"([^\"]*)\"$")
    public void theUserIsAbleToVerifyThePresenceOfADateOfAnnouncementForTheFirstResult(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.resultDate(arg1).isDisplayed();
    }

    @When("^the user is able to verify the presence of the deal value for the first result \"([^\"]*)\"$")
    public void theUserIsAbleToVerifyThePresenceOfTheDealValueForTheFirstResult(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.resultValue(arg1).isDisplayed();
    }

    @When("^the user is able to verify the presence of a deal summary for the first result \"([^\"]*)\"$")
    public void theUserIsAbleToVerifyThePresenceOfADealSummaryForTheFirstResult(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.resultSummary(arg1).isDisplayed();
    }

    @When("^the user is able to verify that a deal summary for the first result is not displayed \"([^\"]*)\"$")
    public void theUserIsAbleToVerifyThatADealSummaryForTheFirstResultIsNotDisplayed(String arg1) throws Throwable {
        assertFalse(whatsMarketSearchResultsPage.resultSummary(arg1).isDisplayed());
    }

    @When("^the user is able to verify that a date of announcement for the first result is not displayed \"([^\"]*)\"$")
    public void theUserIsAbleToVerifyThatADateOfAnnouncementForTheFirstResultIsNotDisplayed(String arg1) throws Throwable {
        assertFalse(whatsMarketSearchResultsPage.resultDate(arg1).isDisplayed());
    }

    @When("^the user is able to verify that a deal value for the first result is not displayed \"([^\"]*)\"$")
    public void theUserIsAbleToVerifyThatADealValueForTheFirstResultIsNotDisplayed(String arg1) throws Throwable {
        assertFalse(whatsMarketSearchResultsPage.resultValue(arg1).isDisplayed());
    }

    @When("^has selected the link entitled International$")
    public void hasSelectedTheLinkEntitled() throws Throwable {
        practicalLawHomepage.internationalLink().click();
    }

    @When("^has selected the link to the country page \"([^\"]*)\"$")
    public void hasSelectedTheLinkToTheCountryPage(String arg1) throws Throwable {
        internationalLandingPage.countryNameLink(arg1).click();
    }

    @When("^the user can verify that the scoped search dropdown states \"([^\"]*)\"$")
    public void theUserCanVerifyThatTheScopedSearchDropdownStates(String expectedText) throws Throwable {
        String returnedText;

        // Strip spaces from the string
        expectedText = expectedText.replaceAll("\\s+", "");

        // Strip spaces from the string
        returnedText = searchScopeControl.scopedSearchDropdownTitle().getText().replaceAll("\\s+", "");

        assertTrue(returnedText.equals(expectedText));
    }

    @When("^the user selects that the scoped search dropdown to '(.*)'$")
    public void theUserSelectsTheScopedSearchDropdownStates(String value) throws Throwable {
        searchScopeControl.scopedSearchDropdownMenuButton().click();
        searchScopeControl.waitForExpectedElement(By.linkText(value)).click();
    }

    @When("^the user can (display|close) the scoped search dropdown menu options$")
    public void theUserCanDisplayTheScopedSearchDropdownMenuOptions(String arg1) throws Throwable {
        searchScopeControl.scopedSearchDropdownMenuButton().click();
    }

    @When("^the user can verify that the dropdown options include \"([^\"]*)\"$")
    public void theUserCanVerifyThatTheDropdownOptionsInclude(String arg1) throws Throwable {
        searchScopeControl.scopedSearchDropdownOptions(arg1).isDisplayed();
    }

    @Then("^the search drop down options provided on \"(.*?)\" are as below$")
    public void theSearchDropDownOptionsProvidedOnAreAsBelow(String currentPage, List<String> expectedOptions) throws Throwable {
        for (String option : searchScopeControl.scopedSearchDropdownOptionsList()) {
            LOG.info("Option from dropdown list is " + option);
            int count = 0;
            for (String expectedOption : expectedOptions) {
                //assertTrue("Optional blocks not displayed as expected", blockTitle.equals(expectedTitle));
                if (option.equals(expectedOption)) {
                    count++;
                    break;
                }
            }
            assertTrue(option + "is not present", count > 0);
        }
    }

    @When("^user selects the dropdown option \"(.*?)\"$")
    public void userSelectsTheDropdownOption(String option) throws Throwable {
        searchScopeControl.scopedSearchDropdownOptions(option).click();
    }

    @When("^the user selects the practice area \"([^\"]*)\"$")
    public void theSelectsThePracticeArea(String arg1) throws Throwable {
        practicalLawHomepage.practiceAreaLink(arg1).click();
    }


    @When("^the user can verify that the title listed above the search results is the country name \"([^\"]*)\"$")
    public void theUserCanVerifyThatTheTitleListedAboveTheSearchResultsIsTheCountryName(String arg1) throws Throwable {
        searchResultsPage.resultPageTitle(arg1).isDisplayed();
    }

    @When("^the user can verify that the search result in position \"([^\"]*)\" contains the jurisdiction \"([^\"]*)\"$")
    public void theUserCanVerifyThatTheSearchResultInPositionContainsTheJurisdiction(String arg1, String arg2) throws Throwable {
        searchResultsPage.jurisdiction(arg1, arg2).isDisplayed();
    }

    @When("^the user can verify that the title listed above the search results is \"([^\"]*)\"$")
    public void theUserCanVerifyThatTheTitleListedAboveTheSearchResultsIs(String arg1) throws Throwable {
        arg1 = "\"" + arg1 + "\"";
        searchResultsPage.resultPageTitle(arg1).isDisplayed();
    }

    @When("^has selected the link to PLC Magazine$")
    public void hasSelectedTheLinkToPLCMagazine() throws Throwable {
        resourcesPage.plcMagazineLink().click();
    }

    @When("^has selected the link to the deal type \"([^\"]*)\"$")
    public void hasSelectedTheLinkToTheDealType(String arg1) throws Throwable {
        if (arg1.equals("Administrations")) {
            whatsMarketHomePage.Administrations().click();
        } else if (arg1.equals("Public M & A"))
            whatsMarketHomePage.PublicMandALink().click();
    }

    @When("^has selected the link to the practice area \"([^\"]*)\"$")
    public void hasSelectedTheLinkToThePracticeArea(String arg1) throws Throwable {
        practicalLawHomepage.practiceAreaLink(arg1).click();
    }

    @When("^the user is able to verify that the result in position \"([^\"]*)\" has the deal type \"([^\"]*)\"$")
    public void theUserIsAbleToVerifyThatTheResultInPositionHasTheDealType(String arg1, String arg2) throws Throwable {
        whatsMarketSearchResultsPage.resultDealType(arg1, arg2).isDisplayed();
    }

    @When("^the user is able to verify all fields and list of items are present$")
    public void theUserIsAbleToVerifyAllFieldsAndListOfItemsArePresent() throws Throwable {
        theUserVerifiesThePresenceOfAFieldEntitledTo();
        theUserVerifiesThePresenceOfAFieldEntitledSubject();
        theUserVerifiesThePresenceOfAFieldEntitledEmailNote();
    }

    @When("^the user selects the compare button$")
    public void theUserSelectsTheCompareButton() throws Throwable {
        whatsMarketSearchResultsPage.compareButton().click();
    }

    @When("^the user verifies the presence of the heading Deal Comparison Report$")
    public void theUserVerifiesThePresenceOfTheHeadingDealComparisonReport() throws Throwable {
        whatsMarketComparisonReportPage.dealComparisonReportHeader().isDisplayed();
    }

    @When("^the user ensures that the left hand column select is displayed$")
    public void theUserEnsuresThatTheTheLeftHandColumnIsDisplayed() throws Throwable {
        // If the toggle isn't already open, then open it
        whatsMarketComparisonReportPage.menuToggleButton().isDisplayed();
        String returnLink = whatsMarketComparisonReportPage.returnToSearchLink().getText();
        assertTrue(returnLink.contains("Return To"));
        if (!whatsMarketComparisonReportPage.leftHandPaneToggleButtonActive()) {
            whatsMarketComparisonReportPage.menuToggleButton().click();
        }
        whatsMarketComparisonReportPage.leftHandColumnSelect().isDisplayed();
    }

    @When("^the user selects the profile option to display \"([^\"]*)\"$")
    public void theUserSelectsTheProfileOptionToDisplay(String profileName) throws Throwable {
        whatsMarketComparisonReportPage.whatsMarketCheckboxByName(profileName).click();
    }


    @When("^the user verifies the absence of a column entitled \"([^\"]*)\"$")
    public void theUserVerifiesTheAbsenceOfAColumnEntitled(String arg1) throws Throwable {
        assertFalse(whatsMarketComparisonReportPage.columnHeader(arg1).isDisplayed());
    }

    @When("^the user verifies the presence of a column entitled \"([^\"]*)\"$")
    public void theUserVerifiesThePresenceOfAColumnEntitled(String arg1) throws Throwable {
        whatsMarketComparisonReportPage.columnHeader(arg1).isDisplayed();
    }

    @When("^the user verifies the absence of a column entitled 'Company name'$")
    public void theUserVerifiesTheAbsenceOfAColumnEntitledCompanyName() throws Throwable {
        assertThat(whatsMarketComparisonReportPage.isCompNameColumnDisplayed(), Is.is(false));
    }

    @When("^the user clicks on the column entitled \"(.*?)\"$")
    public void theUserClicksOnTheColumnEntitled(String arg1) throws Throwable {
        whatsMarketComparisonReportPage.columnHeader(arg1).click();
    }

    @Then("^the user verifies the deals are sorted alphabetically by \"(.*?)\"$")
    public void theUserVerifiesTheDealsAreSortedAlphabeticallyBy(String arg1) throws Throwable {
        List<String> deal = whatsMarketComparisonReportPage.getDealComparisonReportDeal();
        assertTrue(commonMethods.isSorted(deal, SortOptions.ASC));
    }

    @Then("^the user verifies the deals are sorted by 'Date administrators appointed'$")
    public void theUserVerifiesTheDealsAreSortedByDateAdministratorsAppointed() throws Throwable {
        List<WebElement> date = whatsMarketComparisonReportPage.getDealComparisonReportDateAdminAppointed();
        assertTrue(commonMethods.isResultsSortedByDate(date, SortOptions.ASC));
    }

    @When("^the user selects the option to Select All$")
    public void theUserSelectsTheOptionToSelectAll() throws Throwable {
        whatsMarketComparisonReportPage.selectAllCheckbox().click();
    }

    @When("^the user selects the save option$")
    public void theUserSelectsTheSaveOption() throws Throwable {
        whatsMarketComparisonReportPage.saveReportProfile().click();
    }

    @When("^the user verifies the Comparison Terms selector (is|is not) displayed$")
    public void theUserVerifiesTheComparisonTermsSelector(String isIsNot) throws Throwable {
        if (isIsNot.equalsIgnoreCase("is")) {
            assertTrue(getDriver().findElement(By.xpath("//div[@id='co_contentWrapper']//self::div[@class='sidebar-is-active']")).isDisplayed());
        } else if (isIsNot.equalsIgnoreCase("is not")) {
            assertTrue(getDriver().findElement(By.xpath("//div[@id='co_contentWrapper']//self::div[@class='']")).isDisplayed());
        }
    }

    @When("^the user selects the menu icon$")
    public void theUserSelectsTheMenuIcon() throws Throwable {
        whatsMarketComparisonReportPage.menuToggleButton().click();
    }

    @When("^the user verifies the presence of a pop up entitled 'Save Report Profile'$")
    public void theUserVerifiesThePresenceOfAPopUpEntitledSaveReportProfile() throws Throwable {
        whatsMarketComparisonReportPage.newProfilePopUpHeader().isDisplayed();
    }

    @When("^the user enters text into the profile name field \"([^\"]*)\"$")
    public void theUserEntersTextIntoTheProfileNameField(String arg1) throws Throwable {
        whatsMarketComparisonReportPage.profileTextEntryField().sendKeys(arg1);
    }

    @When("^the user selects the save option on the pop up$")
    public void theUserSelectsTheSaveOptionOnThePopUp() throws Throwable {
        whatsMarketComparisonReportPage.saveOptionOnPopUp().click();
        whatsMarketComparisonReportPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user selects the report profile dropdown$")
    public void theUserSelectsTheReportProfileDropdown() throws Throwable {
        whatsMarketComparisonReportPage.reportProfileDropdown().click();
    }

    @When("^the user selects the report profile entitled \"([^\"]*)\"$")
    public void theUserSelectsTheReportProfileEntitled(String arg1) throws Throwable {
        Select selectedOption = new Select
                (whatsMarketComparisonReportPage.reportProfileDropdown());
        selectedOption.selectByVisibleText(arg1);
    }

    @When("^the user verifies that the report profile dropdown (does|does not) include the profile entitled \"([^\"]*)\"$")
    public void theUserVerifiesThatTheReportProfileDropdownIncludesTheProfileEntitled(String doesDoesNot, String arg1) throws Throwable {
        if (doesDoesNot.equalsIgnoreCase("does")) {
            assertThat(whatsMarketComparisonReportPage.isReportProfileDropdownOptionDisplayed(arg1), Is.is(true));
        } else if (doesDoesNot.equalsIgnoreCase("does not")) {
            assertThat(whatsMarketComparisonReportPage.isReportProfileDropdownOptionDisplayed(arg1), Is.is(false));
        }
    }

    @When("^the user selects the delete option$")
    public void theUserSelectsTheDeleteOption() throws Throwable {
        whatsMarketComparisonReportPage.deleteReportProfile().click();
    }

    @When("^the user verifies the presence of a pop up entitled Delete Report Profile$")
    public void theUserVerifiesThePresenceOfAPopUpEntitledDeleteReportProfile() throws Throwable {
        whatsMarketComparisonReportPage.deleteProfilePopUpHeader().isDisplayed();
    }

    @When("^the user selects the report profile entitled \"([^\"]*)\" on Delete Report Profile popup$")
    public void theUserSelectsTheReportProfileEntitledOnDeleteReportProfilePopup(String arg1) throws Throwable {
        whatsMarketComparisonReportPage.reportProfileOnDeleteProfilePopup(arg1).click();
    }

    @When("^the user selects the delete button on Delete Report Profile popup$")
    public void theUserSelectsTheDeleteButtonOnDeleteReportProfilePopup() throws Throwable {
        whatsMarketComparisonReportPage.deleteButtonOnDeleteProfilePopup().click();
        whatsMarketComparisonReportPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks the 'Organize Columns' button on deal comparison report$")
    public void theUserClicksTheOrganizeColumnsButtonOnDealComparisonReport() throws Throwable {
        whatsMarketComparisonReportPage.organizeColumns().click();
    }

    @When("^the user clicks on column \"(.*?)\" on Organize Columns popup$")
    public void theUserClicksOnColumnOnOrganizeColumnsPopup(String arg1) throws Throwable {
        whatsMarketComparisonReportPage.columnOption(arg1).click();
    }

    @When("^the user clicks the \"(.*?)\" button on Organize Columns popup$")
    public void theUserClicksTheButtonOnOrganizeColumnsPopup(String arg1) throws Throwable {
        getDriver().findElement(By.linkText(arg1)).click();
    }

    @When("^the user verifies column \"(.*?)\" is in position \"(.*?)\" on Organize Columns popup$")
    public void theUserVerifiesColumnIsInPositionOnOrganizeColumnsPopup(String column, int position) throws Throwable {
        List<String> columns = whatsMarketComparisonReportPage.getColumnsOrganizeColumns();
        assertTrue(columns.get(position - 1).equals(column));
    }

    @When("^the user clicks the 'Save' button on Organize Columns popup$")
    public void theUserClicksTheSaveButtonOnOrganizeColumnsPopup() throws Throwable {
        whatsMarketComparisonReportPage.saveButtonOrganizeColumns().click();
    }

    @When("^the user clicks the 'Cancel' button on Organize Columns popup$")
    public void theUserClicksTheCancelButtonOnOrganizeColumnsPopup() throws Throwable {
        whatsMarketComparisonReportPage.cancelButtonOrganizeColumns().click();
    }

    @When("^the user clicks the 'Close' button on Organize Columns popup$")
    public void theUserClicksTheCloseButtonOnOrganizeColumnsPopup() throws Throwable {
        whatsMarketComparisonReportPage.closeButtonOrganizeColumns().click();
    }

    @When("^the user verifies the Organize Columns popup is closed$")
    public void theUserVerifiesTheOrganizeColumnsPopupIsClosed() throws Throwable {
        Boolean popupPresent = searchResultsPage.waitForExpectedElement(whatsMarketComparisonReportPage.organizeColumnsLightBoxLocator()).isDisplayed();
        assertFalse(popupPresent);
    }

    @Then("^the user verifies heading \"(.*?)\" is in column \"(.*?)\" on the Deal Comparison Report$")
    public void theUserVerifiesHeadingIsInColumnOnTheDealComparisonReport(String columnName, Integer position) throws Throwable {
        assertTrue(whatsMarketComparisonReportPage.headerColumns(position).equalsIgnoreCase(columnName));
    }

    @When("^the user clicks the 'clear selected' button$")
    public void theUserClicksTheClearSelectedButton() throws Throwable {
        whatsMarketComparisonReportPage.clearSelected().click();
        whatsMarketComparisonReportPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user clicks the next arrow on deal comparison report$")
    public void theUserClicksTheNextArrowOnDealComparisonReport() throws Throwable {
        whatsMarketComparisonReportPage.nextArrow().click();
    }

    @When("^the user clicks the previous arrow on deal comparison report$")
    public void theUserClicksThePreviousArrowOnDealComparisonReport() throws Throwable {
        whatsMarketComparisonReportPage.previousArrow().click();
    }

    @When("^the user verifies all Comparison Terms checkboxes are checked for Administration deal type$")
    public void theUserVerifiesAllComparisonTermsCheckboxesAreCheckedForAdministrationDealType() throws Throwable {
        assertTrue(whatsMarketComparisonReportPage.selectAllCheckbox().isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Deal").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Company name").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Company number").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Industry sector").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Nature of business").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Administrators: firm name").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Administrators: individuals").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Administrators: location").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Ultimate holding company").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Related appointments").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Related appointments: details").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Other points to note").isSelected());
        assertTrue(whatsMarketComparisonReportPage.whatsMarketCheckboxByName("Public documents").isSelected());
    }

    @When("^the user verifies all Comparison Terms checkboxes are unchecked$")
    public void theUserVerifiesAllComparisonTermsCheckboxesAreUnchecked() throws Throwable {
        assertFalse(whatsMarketComparisonReportPage.selectAllCheckbox().isSelected());
        List<WebElement> checked = getDriver().findElements(By.xpath("//div[@id='fieldGroupsContainer']//li/input[@checked='checked']"));
        assertTrue(checked.isEmpty());
    }

    @And("^the user adds whats market deal comparison report to new \"([^\"]*)\" folder with parent folder \"([^\"]*)\"$")
    public void addDocumentToFolderFromDocumentView(String folder, String parentFolder) throws Throwable {
        documentDeliveryPage.waitForPageToLoadAndJQueryProcessing();
        documentDeliveryPage.clickOnAddToFolderLink();
        // Use the save button to help determine the pop-up has rendered as the test wasn't waiting before
        saveToPopup.saveButton().isDisplayed();
        foldersUtils.saveToNewFolder(folder, parentFolder);
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        String message = searchResultsPage.folderingPopupMessage().getText();
        LOG.info(message);
        assertEquals("Message is incorrect", message, "Deal Comparison Report | Administrations | 3 Records saved to '" + folder + "'.");
    }

    @Then("^the user verifies the whats market deal comparison report is present in the \"(.*?)\" folder$")
    public void theUserVerifiesTheWhatsMarketDealComparisonReportIsPresentInTheFolder(String folder) throws Throwable {
        foldersUtils.openFolder(folder);
        researchOrganizerPage.waitForPageToLoad();
        assertTrue(getDriver().findElement(By.linkText("Deal Comparison Report | Administrations | 3 Records")).isDisplayed());
    }

    @When("^the user verifies the presence of a pop up entitled '(Download Report|Email Report)'$")
    public void theUserVerifiesThePresenceOfAPopUpEntitledDownloadReport(String header) throws Throwable {
        whatsMarketComparisonReportPage.downloadEmailReportPopUpHeader(header).isDisplayed();
    }

    @When("^the user verifies the presence of a 'Back To Search Results' button$")
    public void theUserVerifiesThePresenceOfABackToSearchResultsButton() throws Throwable {
        whatsMarketComparisonReportPage.returnToSearchResultsLink().isDisplayed();
    }

    @When("^the user clicks the 'Back To Search Results' button$")
    public void theUserClicksTheBackToSearchResultsButton() throws Throwable {
        whatsMarketComparisonReportPage.returnToSearchResultsLink().click();
        whatsMarketComparisonReportPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^the user verifies the presence of the following comparison options")
    public void theUserVerifiesThePresenceOfTheFollowingComparisonOptions(List<String> optionList) throws Throwable {
        for (String option : optionList) {
            whatsMarketComparisonReportPage.comparisonTermsOptions(option).isDisplayed();
        }
    }

    @When("^the user ensures that the comparison terms header is visible")
    public void theUserEnsuresThatTheComparisonTermsHeaderIsVisible() throws Throwable {
        whatsMarketComparisonReportPage.comparisonTermsHeader().isDisplayed();
    }


    @Then("^the search results should be shown according to the search term selected$")
    public void theSearchResultShouldBeShownAccordingToTheSearchTermSelected() throws Throwable {
        searchResultsPage.moreOrLessDetailAnchor().click();
        searchResultsPage.moreDetailOption().click();
        assertTrue("One of results does not contains search term '" + searchTerm + "'",
                searchUtils
                        .isEveryResultContains(baseResultPage.getResultListWithFullText(), Arrays.asList(searchTerm.split("\\s+"))));
    }


    @When("^the user selects the download icon on the comparison report page$")
    public void theUserSelectsTheDownloadIconOnTheComparisonReportPage() throws Throwable {
        whatsMarketComparisonReportPage.downloadIcon().click();
    }


    @When("^the user selects the Email icon on the comparison report page$")
    public void theUserSelectsTheEmailIconOnTheComparisonReportPage() throws Throwable {
        whatsMarketComparisonReportPage.emailIcon().click();
    }

    @Then("^there is no error message on search results page$")
    public void thereIsNoErrorMessageOnSearchResultsPage() {
        if (searchResultsPage.isElementDisplayed(searchResultsPage.errorMessageBy())) {
            String error = searchResultsPage.errorMessage().getText().trim();
            assertTrue("There is an error on the page: \n" + error, error.isEmpty());
        }
        LOG.info("There are not errors on the page");
    }


    @When("^the user selects the select all option on the results page$")
    public void theUserSelectsTheSelectAllOptionOnTheResultsPage() throws Throwable {
        searchResultsPage.selectAllCheckbox().click();
    }


    @When("^the user selects the home node icon$")
    public void theUserSelectsTheHomeNodeIcon() throws Throwable {
        whatsMarketComparisonReportPage.homeNode().click();
    }


    @When("^the user verifies that the comparison option \"(.*?)\" is still selected$")
    public void theUserVerifiesThatTheComparisonOptionIsStillSelected(String arg1) throws Throwable {
        whatsMarketComparisonReportPage.whatsMarketCheckboxByName(arg1).isSelected();
    }

    @When("^the user can verify that the document contains the header Also Found In$")
    public void theUserCanVerifyThatTheDocumentContainsTheHeaderAlsoFoundIn() throws Throwable {
        knowHowDocumentPage.alsoFoundInHeader().isDisplayed();
    }
}




