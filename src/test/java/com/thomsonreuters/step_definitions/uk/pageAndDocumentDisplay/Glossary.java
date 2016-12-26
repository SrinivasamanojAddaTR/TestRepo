package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.GlossaryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class Glossary extends BaseStepDef {

    private NavigationCobalt navigation = new NavigationCobalt();
    private GlossaryPage glossaryPage = new GlossaryPage();
    private HomePage homePage = new HomePage();

    @When("^user navigates to a glossary page$")
    public void userNavigatesToAGlossaryPage() throws Throwable {
        homePage.selectResourceTab();
        homePage.selectLinkPresentOnTab("Glossary");
        assertThat("Glossary heading is not displayed", glossaryPage.glossaryHeading().isDisplayed(), Is.is(true));
    }

    @Then("^the user is able to see the tabbed alphabetical list$")
    public void theUserIsAbleToSeeTheTabbedAlphabeticalList() throws Throwable {
        String[] expectedTabbedAlphabets = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        assertThat("Glossary heading is not displayed", glossaryPage.glossaryHeading().isDisplayed(), Is.is(true));
        assertThat(glossaryPage.alphabetListToString(), CoreMatchers.hasItems(expectedTabbedAlphabets));
    }

    @Given("^user navigates to a (.+) resource$")
    public void userNavigatesToAPracticeNoteResource(String resource) throws Throwable {
        if (resource.equals("Practice Note")) {
            navigation.navigateToPLUKPlus("/Document/I0206eb261cb611e38578f7ccc38dcbee/View/FullText.html");
        }
    }

    @When("^user clicks on glossary term \"(.*?)\" in the resource page$")
    public void userClicksOnGlossaryTermInTheResourcePage(String glossaryTerm) throws Throwable {
        WebElement element = glossaryPage.glossaryTerm(glossaryTerm);
        new Actions(getDriver()).moveToElement(element).build().perform();
        element.click();
    }

    @Then("^the glossary modal pop up box opens with the title \"(.*?)\"$")
    public void theGlossaryModalPopUpBoxOpensWithTheDefinitionOfTheAboveTerm(String modalTitle) throws Throwable {
        assertTrue(glossaryPage.glossaryModalTitle().getText().toLowerCase().contains(modalTitle));
    }

    @When("^user clicks on glossary term \"(.*?)\" inside this modal box$")
    public void userClicksOnGlossaryTermInsideThisModalBox(String glossaryTermOnModal) throws Throwable {
        glossaryPage.glossaryTermOnModalBox(glossaryTermOnModal).click();
    }

}