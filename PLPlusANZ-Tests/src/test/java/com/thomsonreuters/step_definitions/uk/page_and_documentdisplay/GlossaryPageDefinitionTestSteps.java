package com.thomsonreuters.step_definitions.uk.page_and_documentdisplay;

import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.GlossaryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GlossaryPageDefinitionTestSteps extends BaseStepDef {

    private GlossaryPage glossaryPage = new GlossaryPage();

    @When("^the user clicks on a glossary term in the glossary definition page \"(.*?)\"$")
    public void theUserClicksOnAGlossaryTermInTheGlossaryDefinitionPage(String arg1) throws Throwable {
        glossaryPage.glossaryTerm(arg1).click();
    }

    @Then("^the user should be able to view the definition of the term \"(.*?)\" on the page$")
    public void theUserShouldBeAbleToViewTheDefinitionOfTheTermOnThePage(String arg1) throws Throwable {
        WebElement term = glossaryPage.glossaryHeading();
        assertTrue(term.getText().contains(arg1));
    }

    @Then("^the tabbed alphabet selection should change to show the first \"(.*?)\" of the selected glossary term as selected$")
    public void theTabbedAlphabetSelectionShouldChangeToShowTheFirstOfTheSelectedGlossaryTermAsSelected(String letter) throws Throwable {
        assertThat(glossaryPage.selectedGlossaryAlphabetLink().getText().trim(), Is.is(letter));
    }

    @Given("^the user clicks on a letter term in the glossary definition page eg \"(.*?)\"$")
    public void theUserClicksOnALetterTermInTheGlossaryDefinitionPageEg(String letter) throws Throwable {
        glossaryPage.glossaryLetter(letter).click();
    }

    @When("^the user selects another letter \"(.*?)\"$")
    public void theUserSelectsAnotherLetter(String letter2) throws Throwable {
        glossaryPage.glossaryLetter(letter2).click();
    }

    @Then("^\"(.*?)\" is shown as selected in the left hand pane$")
    public void isShownAsSelectedInTheLeftHandPane(String letter2) throws Throwable {
        assertThat(glossaryPage.selectedGlossaryAlphabetLink().getText().trim(), Is.is(letter2));
    }

    @Then("^the corresponding terms for \"(.*?)\" are displayed$")
    public void theCorrespondingTermsForAreDisplayed(String letter2) throws Throwable {
        assertTrue(glossaryPage.glossaryTermListLetter(letter2).isDisplayed());
    }

}