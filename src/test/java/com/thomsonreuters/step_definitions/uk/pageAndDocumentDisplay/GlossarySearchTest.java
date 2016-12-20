package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.GlossaryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GlossarySearchTest extends BaseStepDef {

    private GlossaryPage glossaryPage = new GlossaryPage();
    
    private Map<String, List<String>> listOfterms = new HashMap<>();

    @Then("^the user should be able to see the Search text box on the right hand side of the page$")
    public void theUserShouldBeAbleToSeeTheSearchTextBoxOnTheRightHandSideOfThePage() throws Throwable {
        glossaryPage.glossarySearchField().isDisplayed();
    }

    @Then("^the search text \"(.*?)\" should be pre-populated in the textbox$")
    public void theSearchTextShouldBePrePopulatedInTheTextbox(String text) throws Throwable {
        WebElement field = glossaryPage.glossarySearchField();
        assertThat(field.getAttribute("placeholder").trim(), Is.is(text));
    }

    @When("^the user enters a \"(.*?)\" into the search box$")
    public void theUserEntersAIntoTheSearchBox(String term) throws Throwable {
        glossaryPage.glossarySearchField().sendKeys(term);
    }

    @Then("^the message \"(.*?)\" is displayed below the search box$")
    public void theMessageMatchesIsDisplayedBelowTheSearchBox(String zeroResults) throws Throwable {
        assertThat(glossaryPage.searchMatchesText().getText().trim(), Is.is(zeroResults));
    }

    @Then("^the user should be able to see a list of resulting glossary terms containing this search term \"(.*?)\"$")
    public void theUserShouldBeAbleToSeeAListOfResultingGlossaryTermsContainingThisSearch(String term) throws Throwable {
        for (String glossarySearchResult : glossaryPage.getGlossarySearchResultsList()) {
            assertTrue(glossarySearchResult.toLowerCase().contains(term.toLowerCase()));
        }
    }

    @And("^the result list displayed should be sorted alphabetically as below$")
    public void theResultsListedShouldBeSortedAlphabet(List<String> results) {
        assertTrue(results.equals(glossaryPage.getGlossarySearchResultsList()));
    }   

    @Then("^the user saves the list of terms containing \"(.*?)\"$")
    public void theSaveListOfTerms(String term) throws Throwable {
    	listOfterms.put(term, glossaryPage.alphabetListWithTermToString(term));
    }
    
    @And("^the result list for the term \"(.*?)\" displayed should be sorted alphabetically$")
    public void theResultsListedShouldBeSortedAlphabet(String term) {
    	Collections.sort(listOfterms.get(term));
        assertTrue(listOfterms.get(term).equals(glossaryPage.getGlossarySearchResultsList()));
    }

    @Then("^the user should be able to see a list of resulting glossary terms containing this search \"(.*?)\" highlighted$")
    public void theUserShouldBeAbleToSeeAListOfResultingGlossaryTermsContainingThisSearchHighlighted(String term) throws Throwable {
        for (WebElement element : glossaryPage.glossaryTermsWithSearchTermList()) {
            assertTrue(term + " not found in " + element.getText().trim(), element.getText().trim().toLowerCase().contains(term));
        }
    }

    @Then("^the first result item's definition should be displayed in the right hand pane$")
    public void theFirstResultItemSDefinitionShouldBeDisplayedInTheRightHandPane() throws Throwable {
        assertTrue(glossaryPage.glossaryHeading().getText().trim().contains(glossaryPage.glossaryTermsWithSearchTermList().get(0).getText().trim()));
    }

    @Then("^clicking on listed result (\\d+) displays the corresponding definition on the right hand page$")
    public void clickingOnListedResultDisplaysTheCorrespondingDefinitionOnTheRightHandPage(int index) throws Throwable {
        WebElement element = glossaryPage.glossaryTermsWithSearchTermList().get(index - 1);
        element.click();
        element.click();
		glossaryPage.waitForPageToLoad();
        boolean found = false;
        int counter = 10;
        do {
            found = glossaryPage.glossaryHeading().getText().trim().contains(element.getText().trim());
            counter--;
        }
        while (!found && counter > 0);
        if (!found) {
            throw new AssertionError("Glossary Heading does not match. Expected: " + element.getText().trim() + "\n But was: " + glossaryPage.glossaryHeading().getText().trim());
        }
    }

    @Then("^no alphabets are selected while the search is active$")
    public void noAlphabetsAreSelectedWhileTheSearchIsActive() throws Throwable {
        // TODO - Find better way to assert expected exception
        try {
            glossaryPage.selectedGlossaryAlphabetLink();
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.info("As expected no alphabets are selected");
        }
    }

    @When("^the user has selected the letter \"(.*?)\" from the tabbed alphabet list$")
    public void theUserHasSelectedTheLetterFromTheTabbedAlphabetList(String letter) throws Throwable {
        glossaryPage.glossaryLetter(letter).click();
    }

    @When("^searches for the term \"(.*?)\" using the glossary search$")
    public void searchesForTheTermUsingTheGlossarySearch(String term) throws Throwable {
        glossaryPage.glossarySearchField().sendKeys(term);
    }

    @Then("^the search icon should be changed to a \"(.*?)\" icon$")
    public void theSearchIconShouldBeChangedToAIcon(String arg1) throws Throwable {
        glossaryPage.searchGlossaryButtonReset().isDisplayed();
    }

    @Then("^clicking on this \"(.*?)\" icon should reset the page back to the default state$")
    public void clickingOnThisIconShouldResetThePageBackToTheDefaultState(String arg1) throws Throwable {
        glossaryPage.searchGlossaryButtonReset().click();
        glossaryPage.searchGlossaryButton().isDisplayed();
    }

    @Then("^the search query is removed from the search input field$")
    public void theSearchQueryIsRemovedFromTheSearchInputField() throws Throwable {
        WebElement element = glossaryPage.glossarySearchField();
        assertThat(element.getAttribute("value"), Is.is(""));
    }

    @Then("^the total should be displayed as \"(.*?)\"$")
    public void theTotalShouldBeDisplayedAs(String expectedMatchesText) throws Throwable {
        assertThat(glossaryPage.searchMatchesText().getText().trim(), Is.is(expectedMatchesText));
    }

    @Then("^the total matches for the term \"(.*?)\" should be displayed$")
    public void theTotalForTermShouldBeDisplayed(String term) throws Throwable {
        assertThat(glossaryPage.searchMatchesText().getText().trim(), Is.is(Integer.toString(listOfterms.get(term).size())+" matches"));
    }

}