package com.thomsonreuters.step_definitions.uk.search;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.WindowHandler;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.generic.PPIGenericDocDisplay;
import com.thomsonreuters.pageobjects.pages.landingPage.UKPLCSitePage;
import com.thomsonreuters.pageobjects.pages.search.*;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FacetJavaTest1 extends BaseStepDef {
    Integer[] resultArray = new Integer[10];

    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private UKPLCSitePage ukplcSitePage = new UKPLCSitePage();
    private CasesSearchResultsPage casesSearchResultsPage = new CasesSearchResultsPage();
    private WhatsMarketSearchResultsPage whatsMarketSearchResultsPage = new WhatsMarketSearchResultsPage();
    private WindowHandler windowHandler = new WindowHandler();
    private KnowHowDocumentPage knowHowDocumentPage = new KnowHowDocumentPage();
    private CommonMethods commonMethods = new CommonMethods();
    private LegislationSearchResultsPage legislationSearchResultsPage = new LegislationSearchResultsPage();
    private JournalsSearchResultsPage journalsSearchResultsPage = new JournalsSearchResultsPage();
    private CasesDocumentPage casesDocumentPage = new CasesDocumentPage();
    private WhatsMarketDocumentPage whatsMarketDocumentPage = new WhatsMarketDocumentPage();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private SearchUtils searchUtils = new SearchUtils();
    private PPIGenericDocDisplay ppiGenericDocDisplay = new PPIGenericDocDisplay();
    private String mainWindowHandle;
    private int facetsDocsCount = 0;

    @When("^the user starts a new record of facet counts$")
    public void theUserStartsANewRecordOfFacetCounts() throws Throwable {
        Arrays.fill(resultArray, null);
    }

    @When("^the user gets the know how facet \"(.*?)\" count and stores it as count \"(.*?)\"$")
    public void theUserGetsTheKnowHowFacetCountAndStoresItAsCount(String arg1, Integer count) throws Throwable {
        // Captures the page object text value and stores it in the "numberReturnedFromWebsite" string
        String numberReturnedFromWebsite = knowHowSearchResultsPage.facetCount(arg1).getText();
        // To make sure the string is only a number we remove any non-numeric characters
        numberReturnedFromWebsite = numberReturnedFromWebsite.replaceAll("[^0-9]", "");
        // Stores the value from "numberReturnedFromWebsite" in the resultArray whilst at the same time converting it to a number
        resultArray[count] = Integer.parseInt(numberReturnedFromWebsite);
    }

    @When("^the user verifies that the know how search result count \"(.*?)\" exceeds facet count \"(.*?)\"$")
    public void theUserVerifiesThatTheKnowHowSearchResultCountExceedsFacetCount(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] > resultArray[count2]);
    }

    @When("^the user verifies that the know how search result count \"(.*?)\" equals facet count \"(.*?)\"$")
    public void theUserVerifiesThatTheKnowHowSearchResultCountEqualsFacetCount(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] == resultArray[count2]);
    }

    @When("^the user verifies that the know how facet count \"(.*?)\" equals count \"(.*?)\"$")
    public void theUserVerifiesThatTheKnowHowFacetCountEqualsCount(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] == resultArray[count2]);
    }

    @When("^the user verifies the presence of the know how facet \"(.*?)\"$")
    public void theUserVerifiesThePresenceOfTheKnowHowFacet(String arg1) throws Throwable {
        knowHowSearchResultsPage.facetName(arg1).isDisplayed();
    }

    @When("^the user verifies that the know how facet count \"(.*?)\" is less than \"(.*?)\"$")
    public void theUserVerifiesThatTheKnowHowFacetCountIsLessThan(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] < resultArray[count2]);
    }

    @When("^the user verifies the presence of an associated know how facet \"(.*?)\" count$")
    public void theUserVerifiesThePresenceOfAnAssociatedKnowHowFacetCount(String arg1) throws Throwable {
        knowHowSearchResultsPage.facetCount(arg1).isDisplayed();
    }

    @When("^the user deselects the know how facet \"(.*?)\"$")
    public void theUserDeselectsTheKnowHowFacet(String arg1) throws Throwable {
        knowHowSearchResultsPage.facetName(arg1).click();
    }

    @When("^the user gets the know how search result count and stores it as count \"(.*?)\"$")
    public void theUserGetsTheKnowHowSearchResultCountAndStoresItAsCount(Integer count) throws Throwable {
        String numberReturnedFromWebsite = knowHowSearchResultsPage.knowHowSearchResultCount().getText();
        numberReturnedFromWebsite = numberReturnedFromWebsite.replaceAll("[^0-9]", "");
        resultArray[count] = Integer.parseInt(numberReturnedFromWebsite);
    }

    @When("^the user verifies that the know how facet is selected \"(.*?)\"$")
    public void theUserVerifiesThatTheKnowHowFacetIsSelected(String arg1) throws Throwable {
        assertTrue(knowHowSearchResultsPage.knowHowFacetCheckbox(arg1).isSelected());
    }

    @When("^the user verifies that the know how search result count \"(.*?)\" is less than \"(.*?)\"$")
    public void theUserVerifiesThatTheKnowHowSearchResultCountIsLessThan(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] < resultArray[count2]);
    }

    @When("^the user selects the know how parent facet \"(.*?)\"$")
    public void theUserSelectsTheKnowHowParentFacet(String arg1) throws Throwable {
        knowHowSearchResultsPage.knowHowFacetCheckbox(arg1).click();
    }

    @When("^the user verifies that know how count \"(.*?)\" is the same as count \"(.*?)\"$")
    public void theUserVerifiesThatKnowHowCountIsTheSameAsCount(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] < resultArray[count2]);
    }

    @When("^the user is able to verify that the search result in position \"(.*?)\" within the result list has the resource type \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheSearchResultInPositionWithinTheResultListHasTheResourceType(String arg1, String arg2) throws Throwable {
        String text = searchResultsPage.getWholeResultItemKnowHow(arg1);
        assertTrue(text.contains(arg2));
    }

    @When("^the user is able to verify that the search result in position \"(.*?)\" within the result list has the jurisdiction \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheSearchResultInPositionWithinTheResultListHasTheJurisdictionJurisdiction(String arg1, String arg2) throws Throwable {
        String text = searchResultsPage.getWholeResultItemKnowHow(arg1);
        assertTrue(text.contains(arg2));
    }

    @When("^the user is able to open the search result in position \"(.*?)\" within the result list$")
    public void theUserIsAbleToOpenTheSearchResultInPositionWithinTheResultList(String arg1) throws Throwable {
        mainWindowHandle = searchResultsPage.getCurrentWindowHandle();
        searchResultsPage.openPLCLinkFromResultItem(arg1);
        windowHandler.switchWindow("PLC");
    }

    @When("^the user is able to verify that the practice areas for the document includes \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatThePracticeAreasForTheDocumentIncludes(String arg1) throws Throwable {
        WebElement products = ukplcSitePage.productsSection();
        assertTrue((products.getText().contains(arg1)));
    }

    @When("^the user is able to verify that the topics for the document includes \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheTopicsForTheDocumentIncludes(String topicName) throws Throwable {
        assertTrue(ukplcSitePage.isTopicAvailable(topicName));
    }

    @When("^the user verifies that the know how parent facet \"(.*?)\" is not displayed$")
    public void theUserVerifiesThatTheKnowHowParentFacetIsNotDisplayed(String arg1) throws Throwable {
        assertFalse(knowHowSearchResultsPage.facetName(arg1).isDisplayed());
    }

    @When("^the user selects the know how following parent facets$")
    public void theUserSelectsTheKnowHowFollowingParentFacets(List<String> facets) throws Throwable {
//        WebElement multipleFilterButton = commonMethods.waitForElementToBeVisible(researchOrganizerPage.selectMultipleByFilters(), 1000);
//        if (multipleFilterButton != null) {
//            knowHowSearchResultsPage.scrollIntoViewAndClick(multipleFilterButton);
//        }
//        if (commonMethods.waitForElementToBeVisible(knowHowSearchResultsPage.moreJuridictionByLink(), 2000) != null) {
//            commonMethods.scrollUpOrDown(200);
//            knowHowSearchResultsPage.waitForElementVisible(knowHowSearchResultsPage.moreJuridictionByLink()).click();
//        }

        for (String facet : facets) {
            knowHowSearchResultsPage.knowHowFacetCheckbox(facet).click();
            assertTrue("Check box not selected..!", searchUtils.isCheckboxSeleted(facet));
        }
    }

    @When("^the user selects the know how following parent facets with single selection$")
    public void theUserSelectsTheKnowHowFollowingParentFacetWithSingleSelection(List<String> facets) throws Throwable {
        if (researchOrganizerPage.isElementDisplayed(researchOrganizerPage.cancelByFilters())) {
            knowHowSearchResultsPage.getCancelButton().click();
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (String facet : facets) {
            knowHowSearchResultsPage.knowHowFacetCheckbox(facet).click();
            softAssertions.assertThat(searchUtils.isCheckboxSeleted(facet)).withFailMessage("Check box not selected..!").isTrue();
        }
        softAssertions.assertAll();
    }

    @When("^the user verifies that the know how following facet is selected$")
    public void theUserVerifiesThatTheKnowHowFollowingFacetIsSelected(List<String> facets) throws Throwable {
        for (String facet : facets) {
            assertTrue(knowHowSearchResultsPage.knowHowFacetCheckbox(facet).isSelected());
        }
    }

    @When("^the user clicks on clear all link$")
    public void theUserClicksOnClearAllLink() throws Throwable {
        // Find an element and define it
        WebElement elementToClick = knowHowSearchResultsPage.clearAllLink();
        // Scroll the browser to the element's Y position
        ((JavascriptExecutor) getDriver()).executeScript("scroll(250,0);");
        // Click the element
        elementToClick.click();
        knowHowSearchResultsPage.waitForPageToLoad();
    }

    @Then("^the user verifies that the following parent facets are not selected$")
    public void theUserVerifiesThatTheFollowingParentFacetsIsNotSelected(List<String> facets) throws Throwable {
        casesSearchResultsPage.waitForPageToLoad();
        casesSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
        for (String facet : facets) {
            assertFalse(facet + " is still Selected..!", casesSearchResultsPage.facetCheckbox(facet).isSelected());
        }
    }

    @When("^the user verifies the presence of the whats market facet \"(.*?)\"$")
    public void theUserVerifiesThePresenceOfWhatsMarketFacet(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.whatsMarketFacetName(arg1).isDisplayed();
    }

    @When("^the user is able to verify that the first result contains the text \"(.*?)\" or the text \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsTheTextOrTheText(String arg1, String arg2) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if (text.getText().contains(arg1) || text.getText().contains(arg2)) {
            resultIsTrue = true;
        }
        assertTrue(resultIsTrue);
    }

    @When("^the user is able to verify that the first result contains the text \"(.*?)\" and the text \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsTheTextAndTheText(String arg1, String arg2) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        assertTrue(text.getText().contains(arg1));
        assertTrue(text.getText().contains(arg2));
    }

    @When("^the user is able to verify that the first cases result contains the term \"(.*?)\" but not \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstCasesResultContainsTheTermButNot(String arg1, String arg2) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if (text.getText().contains(arg1)) {
            resultIsTrue = true;
        } else if (text.getText().contains(arg2)) {
            resultIsTrue = false;
        }
        assertTrue(resultIsTrue);
    }


    @When("^the user is able to verify that the first result contains the cases text \"(.*?)\" and the text \"(.*?)\" or the cases text \"(.*?)\" and the text \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsTheCasesTextAndTheTextOrTheCasesTextAndTheText(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if ((text.getText().contains(arg1) && text.getText().contains(arg2)) || (text.getText().contains(arg3) && text.getText().contains(arg4))) {
            resultIsTrue = true;
        }
        assertTrue(resultIsTrue);
    }

    @When("^the user is able to verify that the first result contains either both terms \"(.*?)\" and \"(.*?)\" or just \"(.*?)\" or just \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsEitherBothTermsAndOrJustOrJust(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if (text.getText().contains(arg1) || text.getText().contains(arg2)) {
            resultIsTrue = true;
        }
        assertTrue(resultIsTrue);
    }

    @When("^the user is able to verify that the first result contains the phrase \"(.*?)\" as a complete query displayed in the same order$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsThePhraseAsACompleteQueryDisplayedInTheSameOrder(String arg1) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        assertTrue(text.getText().contains(arg1));
    }

    @When("^the user is able to verify that the first result contains the phrase \"(.*?)\" or the phrase \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsThePhraseOrThePhrase(String arg1, String arg2) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if (text.getText().contains(arg1) || text.getText().contains(arg2)) {
            resultIsTrue = true;
        }
        assertTrue(resultIsTrue);
    }

    @When("^the user is able to verify that the first case result contains the phrase \"(.*?)\" and the phrase \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstCaseResultContainsThePhraseAndThePhrase(String arg1, String arg2) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if (text.getText().contains(arg1) && text.getText().contains(arg2)) {
            resultIsTrue = true;
        }
        assertTrue(resultIsTrue);
    }

    @When("^the user is able to verify that the first result contains the phrase \"(.*?)\" and not the phrase \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsThePhraseAndThePhrase(String arg1, String arg2) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if (text.getText().contains(arg1)) {
            resultIsTrue = true;
        } else if (text.getText().contains(arg2)) {
            resultIsTrue = false;
        }
        assertTrue(resultIsTrue);
    }

    @When("^the user is able to verify that the first result contains both \"(.*?)\" and \"(.*?)\" in the same paragraph$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsBothAndInTheSameParagraph(String arg1, String arg2) throws Throwable {
        assertTrue(searchUtils.isSearchTermsPresentInParagraph(CommonDocumentPage.TermsInSequence.NO, arg1, arg2));
    }

    @When("^the user is able to verify that the first result contains both \"(.*?)\" and \"(.*?)\" in the same paragraph with the first term preceding the second$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsBothAndInTheSameParagraphWithTheFirstTermPrecedingTheSecond(String arg1, String arg2) throws Throwable {
        assertTrue(searchUtils.isSearchTermsPresentInParagraph(CommonDocumentPage.TermsInSequence.NO, arg1, arg2));
    }

    @When("^the user is able to verify that the first result contains the term \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsTheTerm(String termExpected) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        termExpected = termExpected.toUpperCase();
        String textReturned = text.getText().toUpperCase();
        String textLength = Integer.toString(textReturned.length());
        String textReturnedSample = textReturned.substring(0, 256);
        assertTrue(textReturned.contains(termExpected));
    }

    @When("^the user is able to verify that the first result contains the term \"(.*?)\" but not term \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsTheTermButNotTerm(String arg1, String arg2) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if (text.getText().contains(arg1)) {
            resultIsTrue = true;
        } else if (text.getText().contains(arg2)) {
            resultIsTrue = false;
        }
        assertTrue(resultIsTrue);
    }

    @When("^the user is able to verify that the first result contains the cases term \"(.*?)\" and \"(.*?)\" but not \"(.*?)\"$")
    public void theUserIsAbleToVerifyThatTheFirstResultContainsTheCasesTermAndButNot(String arg1, String arg2, String arg3) throws Throwable {
        WebElement text = casesDocumentPage.caseAnalysisFullText();
        Boolean resultIsTrue = false;
        if (text.getText().contains(arg1) && text.getText().contains(arg2)) {
            resultIsTrue = true;
        } else if (text.getText().contains(arg3)) {
            resultIsTrue = false;
        }
        assertTrue(resultIsTrue);
    }

    @When("^the user is able to verify that the first case result contains the term \"(.*?)\" and the term \"(.*?)\" within \"(.*?)\" terms of each other with the first term preceding the second$")
    public void theUserIsAbleToVerifyThatTheFirstCaseResultContainsTheTermAndTheTermWithinTermsOfEachOtherWithTheFirstTermPrecedingTheSecond(String firstTerm, String withInWords, String secondTerm) throws Throwable {
        assertTrue(searchUtils.isSearchTermsPresentInParagraphWithInNumberOfWords(CommonDocumentPage.TermsInSequence.YES, Integer.valueOf(withInWords), firstTerm, secondTerm));
    }

    @Then("^the user verifies the search result contains the both search terms \"(.*?)\" \"(.*?)\" within \"(.*?)\" terms of each other within the full text$")
    public void theUserVerifiesTheSearchResultContainsTheBothSearchTermsWithinTermsOfEachOtherWithinTheFullText(String firstTerm, String secondTerm, String withInWords) {
        assertTrue(searchUtils.isSearchTermsPresentInParagraphWithInNumberOfWords(KnowHowDocumentPage.TermsInSequence.NO, Integer.valueOf(withInWords), firstTerm, secondTerm));
    }

    @When("^the user verifies the presence of the whats market facet groups$")
    public void theUserVerifiesThePresenceOfTheWhatsMarketFacetGroups(List<String> actualFacets) throws Throwable {
        for (String facet : actualFacets) {
            assertTrue(whatsMarketSearchResultsPage.whatsMarketFacetGroupName(facet).isDisplayed());
        }
    }


    @When("^the user verifies the presence of the whats market child facet \"(.*?)\"$")
    public void theUserVerifiesThePresenceOfWhatsMarketChildFacet(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.whatsMarketFacetName(arg1).isDisplayed();
    }

    @When("^the user selects the whats market facet \"(.*?)\"$")
    public void theUserSelectsTheWhatsMarketFacet(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.whatsMarketFacetCheckbox(arg1).click();
    }

    @When("^the user expands the whats market facet \"(.*?)\"$")
    public void theUserExpandsTheWhatsMarketFacet(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.expandWhatsMarketFacet(arg1).click();
    }

    @When("^the user verifies the presence of an associated whats market facet \"(.*?)\" count$")
    public void theUserVerifiesThePresenceOfAnAssociatedWhatsMarketFacetCount(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.whatsMarketFacetCount(arg1).isDisplayed();
    }

    @When("^the user verifies that the whats market facet count \"(.*?)\" is less than \"(.*?)\"$")
    public void theUserVerifiesThatTheWhatsMarketFacetCountIsLessThan(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] < resultArray[count2]);
    }

    @When("^the user verifies that the whats market facet \"(.*?)\" is selected$")
    public void theUserVerifiesThatTheWhatsMarketFacetIsSelected(String arg1) throws Throwable {
        assertTrue(whatsMarketSearchResultsPage.whatsMarketFacetCheckbox(arg1).isSelected());
    }

    @When("^the user verifies that the whats market facet \"(.*?)\" is not selected$")
    public void theUserVerifiesThatWhatsMarketFacetIsNotSelected(String arg1) throws Throwable {
        assertFalse(whatsMarketSearchResultsPage.whatsMarketFacetCheckbox(arg1).isSelected());
    }

    @When("^the user verifies the presence of the know how child facet \"(.*?)\"$")
    public void theUserVerifiesThePresenceOfTheKnowHowChildFacet(String arg1) throws Throwable {
        knowHowSearchResultsPage.facetName(arg1).isDisplayed();
    }

    @When("^the user verifies that the whats market search result count \"(.*?)\" is less than \"(.*?)\"$")
    public void theUserVerifiesThatTheWhatsMarketSearchResultCountIsLessThan(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] < resultArray[count2]);
    }

    @When("^the user deselects the whats market facet \"(.*?)\"$")
    public void theUserDeselectsTheWhatsMarketFacet(String arg1) throws Throwable {
        whatsMarketSearchResultsPage.whatsMarketFacetCheckbox(arg1).click();
    }

    @When("^the user verifies that the whats market search result count \"(.*?)\" equals the search result count \"(.*?)\"$")
    public void theUserVerifiesThatTheWhatsMarketSearchResultCountEqualsTheSearchResultCount(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] == resultArray[count2]);
    }

    @When("^the user gets the whats market search result count and stores it as count \"(.*?)\"$")
    public void theUserGetsTheWhatsMarketSearchResultCountAndStoresItAsCount(Integer count) throws Throwable {
        String numberReturnedFromWebsite = knowHowSearchResultsPage.knowHowSearchResultCount().getText();
        numberReturnedFromWebsite = numberReturnedFromWebsite.replaceAll("[^0-9]", "");
        resultArray[count] = Integer.parseInt(numberReturnedFromWebsite);
    }

    @When("^the user verifies the presence of the whats market facet group header \"(.*?)\"$")
    public void theUserVerifiesThePresenceOfTheWhatsMarketFacetGroupHeader(String arg1) throws Throwable {
        //whatsMarketSearchResultsPage.whatsMarketFacetGroupHeader(arg1).isDisplayed();
    }

    @When("^the user verifies that the facet count for all the individual facets is not \"(.*?)\"$")
    public void theUserVerifiesThatTheFacetCountForAllTheIndividualFacetsIsNot(String arg1) throws Throwable {
        assertFalse(knowHowSearchResultsPage.isFacetCountPresent(arg1));
    }

    @When("^the user verifies that the facet count for all the individual facets does not contain \"(.*?)\"$")
    public void theUserVerifiesThatTheFacetCountForAllTheIndividualFacetsDoesNotContains(String arg1) throws Throwable {
        assertFalse(knowHowSearchResultsPage.isFacetCountPresent(arg1));
    }

    @And("^user comes back to main window$")
    public void shiftDriverObject() {
        getDriver().close();
        getDriver().switchTo().window(mainWindowHandle);
    }

    @When("^the user selects the know how \"(.*?)\" facet \"(.*?)\"$")
    public void theUserSelectsTheFacet(String facetType, String facet) {
        knowHowSearchResultsPage.selectFacetCheckBox(facetType, facet.split("_"));
    }

    @Then("^the user verifies that know how \"(.*?)\" facet \"(.*?)\" is selected$")
    public void isFacetSelected(String facetType, String facet) {
        assertTrue(knowHowSearchResultsPage.isFacetSelected(facetType, facet.split("_")));
    }

    @Then("^the user verifies that know how \"(.*?)\" facet \"(.*?)\" is not selected$")
    public void isFacetNotSelected(String facetType, String facet) {
        assertFalse(knowHowSearchResultsPage.isFacetSelected(facetType, facet.split("_")));
    }

    @When("^the user opens the result in position \"(.*?)\"$")
    public void theUserOpenTheResultInPosition(String resultIndex) {
        knowHowSearchResultsPage.clickOnResultItem(Integer.parseInt(resultIndex));
    }

    @Then("^the user verifies the search result contains the search terms \"(.*?)\" and also \"(.*?)\" within the full text$")
    public void theUserCanVerifyTheSearchResultContainsTheSearchTermsAndAlsoWithinTheFullText(String firstTerm, String secondTerm) {
        String docText = searchUtils.getFullText().toLowerCase();
        assertTrue(docText.contains(firstTerm));
        assertTrue(docText.contains(secondTerm));
    }

    @Then("^the displayed document will have the terms \"([^\"]*)\" marked up as hits$")
    public void theDisplayedDocumentWillHaveTheTermsMarkedUpAsHits(String searchTerms) throws Throwable {
        if (!searchTerms.equals("") && !searchTerms.isEmpty()) {
            //Split each term using the space character
            String eachTerms[] = searchTerms.split(" ");
            Boolean termFound;
            String textFromElement;

            for (int dataRow = 0; dataRow < eachTerms.length; dataRow++) {

                String currentTerm = eachTerms[dataRow].toUpperCase();
                // remove any white spaces
                currentTerm = currentTerm.replaceAll("\\s+", "");

                // Ignore And and Or
                if ((!currentTerm.equals("AND")) && (!currentTerm.equals("&")) && (!currentTerm.equals("OR"))) {

                    if (currentTerm.length() > 0) {
                        termFound = false;
                        // If a single term is connected with a + plus sign split that into multiple terms, any of which will pass
                        String equivalentTerms[] = currentTerm.split("\\+");

                        for (int dataRowTwo = 0; dataRowTwo < equivalentTerms.length; dataRowTwo++) {
                            currentTerm = equivalentTerms[dataRowTwo];

                            LOG.info("Checking that the term '" + currentTerm + "' is marked up as a hit");

                            List<WebElement> searchTermsFound = ppiGenericDocDisplay.ppiTermNavigationHitMarkupCheckTermsAsList();

                            LOG.info("The number of marked up search terms found is: " + searchTermsFound.size());

                            for (WebElement element : searchTermsFound) {
                                textFromElement = element.getText().toUpperCase();
                                if (Pattern.matches(commonMethods.wildcardToRegex("*" + currentTerm + "*"), textFromElement)) {
                                    LOG.info("Checking for term '" + currentTerm + "' within " + textFromElement);
                                    termFound = true;
                                    break;
                                }
                            }

                        }
                        assertTrue("Term '" + currentTerm + "' was not found", termFound);

                    }

                }

            }
        }
    }

    @Then("^returns to the WM search results by Return to list$")
    public void returnstotheWMsearchresultsbyReturntolist() {
        knowHowSearchResultsPage.waitForPageToLoad();
        knowHowSearchResultsPage.getElementByLinkText("Return to list").click();
    }

    @Then("^the user verifies the search result contains the search terms \"(.*?)\" as a phrase within the full text$")
    public void theUserVerifiesTheSearchResultContainsTheSearchTermsAsAPhraseWithinTheFullText(String phrase) {
        String docText = searchUtils.getFullText().toLowerCase();
        assertTrue(docText.contains(phrase));
    }

    @Then("^the user verifies the search result contains the search terms \"(.*?)\" \"(.*?)\" within a single paragraph in the full text$")
    public void theUserVerifiesTheSearchResultContainsTheSearchTermsWithinASingleParagraphInTheFullText(String firstTerm, String secondTerm) {
        assertTrue(searchUtils.isSearchTermsPresentInParagraph(CommonDocumentPage.TermsInSequence.NO, firstTerm, secondTerm));
    }

    @Then("^the user verifies the search result contains the search terms \"(.*?)\" \"(.*?)\" within the same sentence in the full text$")
    public void theUserVerifiesTheSearchResultContainsTheSearchTermsWithinSentenceInTheFullText(String firstTerm, String secondTerm) {
        assertTrue(searchUtils.isSearchTermsPresentInSentence(CommonDocumentPage.TermsInSequence.NO, firstTerm, secondTerm));
    }

    @Then("^the user verifies the search result contains the search terms \"(.*?)\" \"(.*?)\" in the full text where the first precedes the second in the same paragraph$")
    public void theUserVerifiesTheSearchResultContainsTheSearchTermsInTheFullTextWhereTheFirstPrecedesTheSecondInTheSameParagraph(String firstTerm, String secondTerm) {
        Boolean result = false;

        //Split the first term using the + plus character to represent more than one check on a term
        String firstEachTerms[] = firstTerm.split("\\+");
        //Split the second term using the + plus character to represent more than one check on a term
        String secondEachTerms[] = secondTerm.split("\\+");

        for (int dataRow = 0; dataRow < firstEachTerms.length; dataRow++) {
            String firstTermToCheck = firstEachTerms[dataRow];
            for (int dataRowTwo = 0; dataRowTwo < secondEachTerms.length; dataRowTwo++) {
                String secondTermToCheck = secondEachTerms[dataRowTwo];
                if (!result) {
                    LOG.info("Checking term " + firstTermToCheck + " precedes " + secondTermToCheck);
                    result = searchUtils.isSearchTermsPresentInParagraph(KnowHowDocumentPage.TermsInSequence.YES, firstTermToCheck, secondTermToCheck);
                } else {
                    // Test has passed
                    break;
                }
            }
        }

        assertTrue(result);

    }

    @Then("^the user verifies the search result contains the search terms \"(.*?)\" \"(.*?)\" in the full text where the first precedes the second in the same sentence$")
    public void theUserVerifiesTheSearchResultContainsTheSearchTermsInTheFullTextWhereTheFirstPrecedesTheSecondInTheSameSentence(String firstTerm, String secondTerm) {
        assertTrue(searchUtils.isSearchTermsPresentInSentence(CommonDocumentPage.TermsInSequence.YES, firstTerm, secondTerm));
    }


    @Then("^the user verifies the search result contains the both search terms \"(.*?)\" \"(.*?)\" \"(.*?)\" terms of each other in the full text with the first preceding the second$")
    public void theUserVerifiesTheResultsWithNumberOfTermsWithEachOtherInSequence(String firstTerm, String secondTerm, String withInWords) {
        assertTrue("Unable to find the search terms with the preceding sequence", searchUtils.isSearchTermsPresentInParagraphWithInNumberOfWords(KnowHowDocumentPage.TermsInSequence.YES, Integer.valueOf(withInWords), firstTerm, secondTerm));
    }

    @Then("^the user verifies the search result contains the first search term \"(.*?)\" in the full text for the first term and not the second \"(.*?)\"$")
    public void theUserVerifiesTheSearchResultsContainsTheFirstSearchNotTheSecondOne(String firstTerm, String secondTerm) {
        String docText = searchUtils.getFullText().toLowerCase();
        assertTrue(docText.contains(firstTerm.toLowerCase().trim()));
        assertFalse(docText.contains(secondTerm.toLowerCase().trim()));
    }

    @Then("^the user verifies the search result contains the full text includes one or more of the variants$")
    public void theUserVerifiesTheResultContainsWithOneOrMoreVariants(List<String> results) {
        String docText = searchUtils.getFullText();
        int count = 0;
        for (String result : results) {
            if (docText.contains(result)) {
                count++;
            }
        }
        assertTrue(count > 0);
    }

    @Then("^the user verifies the search result contains full text includes words starting and ending with given \"(.*?)\"$")
    public void theUserVerifiesTheSearchResultContainsWordsStartingAndEndingWithGivenCriteria(String searchTerm) {
        String docText = searchUtils.getFullText();
        String regExp = searchTerm.replace("**", "[a-zA-Z\\d_]");
        assertTrue(commonMethods.isRegExpFound(regExp, docText));
    }

    @Then("^the user verifies the search result contains the full text may include the following terms$")
    public void theUserVerifiesTheSearchResultContainsWordsStartingAndEndingWithGivenCriteria(List<String> results) {
        String docText = searchUtils.getFullText().toLowerCase();
        int count = 0;
        for (String result : results) {
            if (docText.contains(result.toLowerCase())) {
                count++;
            }
        }
        assertTrue(count > 0);
    }

    @Then("^the user verifies the search result contains the full text will contain the term \"(.*?)\" but not the term \"(.*?)\"$")
    public void theUserVerifiesThatThereIsNoPlurals(String term1, String term2) {
        String docText = searchUtils.getFullText().toLowerCase();
        assertTrue(docText.contains(term1));
        assertFalse(docText.contains(term2));
    }

    @Then("^the user verifies the search result contains the full text will include the following variants terms$")
    public void theSearchResultContainsVariantTerms(List<String> results) {
        String docText = searchUtils.getFullText().toLowerCase();
        int count = 0;
        for (String result : results) {
            if (docText.contains(result.toLowerCase())) {
                count++;
            }
        }
        assertTrue(count > 0);
    }

    @Then("^the user verifies the search result contains the full text will contains the term \"(.*?)\"$")
    public void theUserVerifiesTheBracetsSearchWillNotImpactTheTerm(String termText) {
        String docText = searchUtils.getFullText().toLowerCase();
        assertTrue(docText.contains(termText));
    }

    @Then("^the selected full text contains the term \"(.*?)\" but not the term \"(.*?)\" and the word \"(.*?)\" is accompanied by the word \"(.*?)\" in the same paragraph$")
    public void theUserVerifiesTheTermSearching(String term1, String term2, String term3, String term4) {
        String docText = null;
        docText = knowHowDocumentPage.getFullText().toLowerCase();
        assertTrue(docText.contains(term1.toLowerCase().trim()));
        assertFalse(docText.contains(term2.toLowerCase().trim()));
        assertTrue(searchUtils.isSearchTermsPresentInParagraph(KnowHowDocumentPage.TermsInSequence.NO, term3, term4));
    }

    @Then("^the user is able to verify that the full text contains the term \"(.*?)\" within \"(.*?)\" terms of the term \"(.*?)\" or the term \"(.*?)\" within \"(.*?)\" terms of the term \"(.*?)\"$")
    public void theUserVerifiesTheTermSearchingWithOr(String term1, String withInWords, String term2, String term3, String noOfWords, String term4) {
        boolean firstSetValue = searchUtils.isSearchTermsPresentInParagraphWithInNumberOfWords(KnowHowDocumentPage.TermsInSequence.NO, Integer.valueOf(withInWords), term1, term2);
        boolean secondSetValue = searchUtils.isSearchTermsPresentInParagraphWithInNumberOfWords(KnowHowDocumentPage.TermsInSequence.YES, Integer.valueOf(noOfWords), term3, term4);
        assertTrue(firstSetValue || secondSetValue);
    }

    @Then("^the user is able to verify that the full text will not contain the term \"(.*?)\"$")
    public void theUserVerifiesThatThereIsNoPluralwords(String term1) {
        String docText = searchUtils.getFullText();
        assertFalse(docText.contains(" " + term1 + " "));
    }

    @Then("^the result contains either of the results$")
    public void whatsMarketEitherOfTheResults(List<String> results) {
        int count, actualCount = 0;
        String resultParts[];
        String docText = searchUtils.getFullText().toLowerCase();
        for (String result : results) {
            resultParts = result.split("&");
            count = 0;
            for (String resultPart : resultParts) {
                if (docText.contains(resultPart.toLowerCase().trim())) {
                    if (++count == resultParts.length) {
                        actualCount++;
                    }
                }
            }
        }
        assertTrue(actualCount > 0);
    }

    @When("^the user opens the whats market result in position \"(.*?)\"$")
    public void theUserOpenTheWhatsMarketResultInPosition(String resultIndex) {
        whatsMarketSearchResultsPage.selectResultItemByIndex(resultIndex);
    }

    @Then("^the user verifies the whats market result contains the search terms \"(.*?)\" and also \"(.*?)\" within the full text$")
    public void theUserCanVerifyThatWhatsMarketResultsContainsBothSearchTerms(String firstTerm, String secondTerm) {
        String docText = searchUtils.getFullText();
        assertTrue(docText.contains(firstTerm));
        assertTrue(docText.contains(secondTerm));
    }

    @When("^the user gets the cases search result count and stores it as count \"(.*?)\"$")
    public void theUserGetsTheCasesSearchResultCountAndStoresItAsCount(Integer count) throws Throwable {
        String numberReturnedFromWebsite = casesSearchResultsPage.casesSearchResultCount().getText();
        numberReturnedFromWebsite = numberReturnedFromWebsite.replaceAll("[^0-9]", "");
        resultArray[count] = Integer.parseInt(numberReturnedFromWebsite);
    }

    @When("^the user verifies that the cases search result count \"(.*?)\" is less than \"(.*?)\"$")
    public void theUserVerifiesThatTheCasesSearchResultCountIsLessThan(Integer count1, Integer count2) throws Throwable {
        assertTrue(resultArray[count1] < resultArray[count2]);
    }

    @When("^the user opens the cases result in position \"(.*?)\"$")
    public void theUserOpenTheCasesResultInPosition(String resultIndex) {
        casesSearchResultsPage.clickOnResultItem(Integer.parseInt(resultIndex));
    }

    @When("^the user opens the legislation result in position \"(.*?)\"$")
    public void theUserOpenTheLegislationResultInPosition(String resultIndex) {
        legislationSearchResultsPage.clickOnResultItem(Integer.parseInt(resultIndex));
    }

    @When("^the user selects the cancel option$")
    public void theUserSelectsTheCancelOption() throws Throwable {
        searchResultsPage.cancelFilters().click();
    }

    @When("^the user verifies the presence of the option to Apply Filters$")
    public void theUserVerifiesThePresenceOfTheOptionToApplyFilters() throws Throwable {
        assertTrue(searchResultsPage.applyFilters().isDisplayed());
    }

    @When("^the user verifies the presence of the option to Select Multiple Filters$")
    public void theUserVerifiesThePresenceOfTheOptionToSelectMultipleFilters() throws Throwable {
        assertTrue(searchResultsPage.selectMultipleFilters().isDisplayed());
    }

    @When("^the user verifies the presence of the error message You can only compare deals of the same type$")
    public void theUserVerifiesThePresenceOfTheErrorMessageYouCanOnlyCompareDealsOfTheSameType() throws Throwable {
        whatsMarketSearchResultsPage.errorMessage().isDisplayed();
    }

    @When("^the user selects OK$")
    public void theUserSelectsOK() throws Throwable {
        whatsMarketSearchResultsPage.okButton().click();
    }

    @When("^the user selects the option to apply multiple filters$")
    public void theUserSelectsTheOptionToApplyMultipleFilters() throws Throwable {
        searchResultsPage.selectMultipleFilters().click();
    }

    @When("^the user opens the journals result in position \"(.*?)\"$")
    public void theUserOpenTheJournalsResultInPosition(String resultIndex) {
        journalsSearchResultsPage.clickOnResultItem(Integer.parseInt(resultIndex));
    }

    @Then("^the displayed PL document will have the terms \"([^\"]*)\" marked up as hits$")
    public void theDisplayedPLDocumentWillHaveTheTermsMarkedUpAsHits(String searchTerms) throws Throwable {
        if (searchTerms.length() > 0) {
            String eachTerms[] = searchTerms.split(" ");
            Boolean termFound;
            for (int dataRow = 0; dataRow < eachTerms.length; dataRow++) {
                if (eachTerms[dataRow].length() > 0) {
                    termFound = false;
                    LOG.info(" ...Checking that the term " + eachTerms[dataRow] + " is marked up as a hit");
                    List<String> searchTermsFound = casesDocumentPage.termNavigationHitMarkupCheckTermsAsList();
                    for (String currentTerm : searchTermsFound) {
                        if (currentTerm.toUpperCase().contains(eachTerms[dataRow].toUpperCase())) {
                            termFound = true;
                            break;
                        }
                    }
                    assertTrue(termFound);
                }
            }
        }
    }

    @And("^the user applies multiple filters$")
    public void selectCoupleOfFilters(@Transpose List<String> filterNames) {
        for (String filterName : filterNames) {
            knowHowSearchResultsPage.getFacetCheckbox(filterName).click();
            facetsDocsCount += commonMethods.getIntFromString(knowHowSearchResultsPage.facetCount(filterName).getText());
        }
        if (!knowHowSearchResultsPage.isCancelButtonExists()) { // Filter state can be stored after previous sessions
            knowHowSearchResultsPage.selectMultipleFilters().click();
        }
        knowHowSearchResultsPage.applyFiltersButton().click();
        knowHowSearchResultsPage.waitForPageToLoad();
        knowHowSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^the user sees the search result count updated accordingly$")
    public void theUserSeeTheSearchResultCountUpdatedAccordingly() throws Throwable {
        assertTrue("Results count was not updated after filtering",
                searchUtils.getSearchResultsCountAsInt() <= facetsDocsCount);
    }

}