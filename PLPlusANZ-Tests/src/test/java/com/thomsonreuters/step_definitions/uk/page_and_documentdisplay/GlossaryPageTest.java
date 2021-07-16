package com.thomsonreuters.step_definitions.uk.page_and_documentdisplay;

import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.GlossaryPage;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class GlossaryPageTest extends BaseStepDef {

    private GlossaryPage glossaryPage = new GlossaryPage();
    private FooterUtils footerUtils = new FooterUtils();

    private String selectedAlphabet = null;
    private String selectedLinkText = null;

    @When("^the user clicks on the alphabet \"(.*?)\" in the tabbed alphabetical list$")
    public void theUserClicksOnTheAlphabetInTheTabbedAlphabeticalList(String alphabet) throws Throwable {
        glossaryPage.glossaryLetter(alphabet).click();
        selectedAlphabet = alphabet;
		glossaryPage.waitForPageToLoad();
    }

    @Then("^the glossary list rolls up and the first term in the respective list is selected \\(except x, Y and Z\\)$")
    public void theGlossaryListRollsUpAndTheFirstTermInTheRespectiveListIsSelectedExceptXYAndZ() throws Throwable {
        footerUtils.closeDisclaimerMessage();
        selectedLinkText = glossaryPage.selectedGlossaryTermLink().getText().trim();
        boolean isTermSelected = false;
        if (glossaryPage.nextElementToAlphabetTitle(selectedAlphabet).getText().trim().equalsIgnoreCase(selectedLinkText)) {
            isTermSelected = true;
        }
        assertTrue("First term link has not been selected..!", isTermSelected);
    }

    @Then("^the corresponding definition of the selected term should be displayed on the right hand side$")
    public void theCorrespondingDefinitionOfTheSelectedTermIsDisplayedOnTheRightHandSide() throws Throwable {
        assertTrue("Selected Link Text has not been found in heading..!",
                glossaryPage.glossaryHeading().getText().contains(selectedLinkText));
    }

    @When("^the user selects a term e\\.g\\. \"(.*?)\" on the tabbed alphabetical list$")
    public void theUserSelectsATermEGOnTheTabbedAlphabeticalList(String linkText) throws Throwable {
        selectedLinkText = linkText;
        glossaryPage.glossaryTerm(linkText).click();
    }

    @Then("^letter \"(.*?)\" should be selected on the alphabet tab$")
    public void letterAShouldBeSelectedOnTheAlphabetTab(String alphabet) throws Throwable {
        assertTrue("Selected alphabet is different..!",
                glossaryPage.selectedGlossaryAlphabetLink().getText().trim().equals(alphabet));
    }

    @Then("^the title \"(.*?)\" is displayed in the definition$")
    public void theTitleIsDisplayedInTheDefinition(String glossaryTitle) throws Throwable {
        assertTrue("Selected Link Text has not been found in heading..!",
                glossaryPage.glossaryHeading().getText().contains(glossaryTitle));
    }

    @Then("^the term should adjust itself to roll up the list as the first item$")
    public void theTermShouldAdjustItselfToRollUpTheListAsTheFirstItem() throws Throwable {
        assertTrue("Selected alphabet is different..!",
                glossaryPage.selectedGlossaryTermLink().getText().trim().equalsIgnoreCase(selectedLinkText));
    }

    @Then("^the user should be able to view the scroll up and scroll down button on the list$")
    public void theUserShouldBeAbleToViewTheScrollUpAndScrollDownButtonOnTheList() throws Throwable {
        assertTrue("Scroll up button not displayed..!", glossaryPage.scrollUpButton().isDisplayed());
        assertTrue("Scroll down button not displayed..!", glossaryPage.scrollDownButton().isDisplayed());
    }

    @Then("^clicking on the scroll up button the user should be able to roll up the list of terms$")
    public void clickingOnTheScrollUpButtonTheUserShouldBeAbleToRollUpTheListOfTerms() throws Throwable {
        for (int i = 0; i < 9; i++) {
            glossaryPage.scrollIntoViewAndClick(glossaryPage.scrollUpButton());
        }
    }

    @Then("^clicking on the scroll down button the user should be able to traverse down the list of terms$")
    public void clickingOntHeScrollDownButtonTheUserShouldBeAbleToTraverseDownTheListOfTerms() throws Throwable {
        for (int i = 0; i < 9; i++) {
            footerUtils.closeDisclaimerMessage();
            glossaryPage.scrollIntoViewAndClick(glossaryPage.scrollDownButton());
        }
    }

}