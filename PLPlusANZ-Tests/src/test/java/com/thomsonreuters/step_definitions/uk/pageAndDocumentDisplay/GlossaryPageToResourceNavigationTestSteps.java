package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.GlossaryPage;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class GlossaryPageToResourceNavigationTestSteps extends BaseStepDef {

    private GlossaryPage glossaryPage = new GlossaryPage();
    private FooterUtils footerUtils = new FooterUtils();

    @When("^the user clicks on glossary term \"(.*?)\"$")
    public void theUserClicksOnGlossaryTerm(String termLink) throws Throwable {
        footerUtils.closeDisclaimerMessage();
        glossaryPage.scrollIntoViewAndClick(glossaryPage.glossaryTerm(termLink));
    }

    @When("^clicks on the (know how resource|glossary term) link \"(.*?)\" in the definition page$")
    public void clicksOnTheKnowHowResourceLinkInTheDefinitionPage(String type, String resourceLink) throws Throwable {
        glossaryPage.practiceNoteLink(resourceLink).click();
    }

    @Then("^the user is navigated to the actual know how resource page$")
    public void theUserIsNavigatedToTheActualKnowHowResourcePage() throws Throwable {
        assertThat(glossaryPage.glossaryHeading().getText())
                .as("The publication widget links are incorrect")
                .isEqualTo("Legal Aid, Sentencing and Punishment of Offenders Act 2012");
    }

    @Then("^the definition of the selected term should be displayed$")
    public void theDefinitionOfTheSelectedTermShouldBeDisplayed() throws Throwable {
        assertTrue("Glossary Term definition is not displayed on the modal box",
                glossaryPage.glossaryModalTermDefinition().isDisplayed());
    }

    @Then("^the definition contains text \"(.*?)\"$")
    public void theDefinitionContainsText(String text) {
        assertTrue("Term definition does not contain expected text: " + text, glossaryPage.glossaryTermBody().getText().contains(text));
    }

    @Then("^the term \"(.*?)\" is (visible|not visible) in the list$")
    public void theTermIsNotVisibleInList(String term, String not) {
        boolean underUpButton = glossaryPage.compareElementsLocationByHeight(glossaryPage.glossaryTerm(term), glossaryPage.scrollUpButton()) > 0;
        boolean overDownButton = glossaryPage.compareElementsLocationByHeight(glossaryPage.glossaryTerm(term), glossaryPage.scrollDownButton()) < 0;
        if (!not.contains("not")) {
            Assert.assertTrue("Term '" + term + "' should be visible", underUpButton && overDownButton);
        } else {
            Assert.assertFalse("Term '" + term + "' should not be visible", underUpButton && overDownButton);
        }
    }


}