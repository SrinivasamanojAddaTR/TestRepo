package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.KHResourcePage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.hamcrest.core.Is;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PADDTests extends BaseStepDef {

    private CommonMethods comMethods = new CommonMethods();
    private KHResourcePage khResourcePage = new KHResourcePage();

    int draftingNotesCount=0;

    @Then("^the user should see the related content button on the sticky header$")
    public void theUserShouldSeeTheRelatedContentButtonOnTheStickyHeader() throws Throwable {
        assertThat(khResourcePage.relatedContentLinkOnStickyBar().isDisplayed(), Is.is(true));
    }

    @Then("^the user clicks on \"Related Content\" button$")
    public void theUserClicksOnRelatedContentButton() throws Throwable {
        khResourcePage.relatedContentLinkOnStickyBar().click();
    }

    @Then("^the user should see the related content section displayed$")
    public void theUserShouldSeeTheRelatedContentSectionDisplayed() throws Throwable {
        assertTrue("Related Content section not displayed..!", khResourcePage.relatedContentHeading().isDisplayed());
    }
    @Then("^user should see the Standard Document with drafting icon on the Delivery toolbar$")
    public void UserShouldSeeTheStandardDocumentWithDraftingIconOnTheDeliveryToolbar() throws Throwable {
       assertTrue("Related Content section not displayed..!", khResourcePage.draftingNotesDeliveryIcon().isDisplayed());
       draftingNotesCount= khResourcePage.collapsedDraftingNotesList().size();
    }

    @Then("^user should see all the drafting notes in the document expanded$")
    public void userShouldSeeAllTheDraftingNotesInTheDocumentExpanded() throws Throwable {
        int expandedDraftingNotesCount=khResourcePage.expandedDraftingNotesList().size();
        assertTrue("All the drafting notes not expanded..!", draftingNotesCount==expandedDraftingNotesCount);
    }

    @Then("^user clicks to close the drafting note with title \"(.*)\"$")
    public void userClicksToCloseTheFirstExpandedDraftingNotes(String title) throws Throwable {
       khResourcePage.draftingNoteCloseIconByTitle(title).click();
    }

    @Then("^user should see the drafting note with title \"(.*)\" collapsed$")
    public void userShouldSeeTheDraftingNoteWithTitleCollapsed(String title) throws Throwable {
        assertTrue("Drafting Note " + title + " not collapsed..!", khResourcePage.collapsedDraftingNoteTitle(title).isDisplayed());
    }

    @Then("^user should see all the drafting notes in the document collapsed$")
    public void userShouldSeeAllTheDraftingNotesInTheDocumentCollapsed() throws Throwable {
        int collapsedDraftingNotesCount=khResourcePage.collapsedDraftingNotesList().size();
        assertTrue("All the documents not collapsed..!", collapsedDraftingNotesCount==draftingNotesCount);
    }

    @Then("^user should see the tick with \"(.*)\" option$")
    public void userShouldSeeAllTheDraftingNotesInTheDocumentCollapsed(String option) throws Throwable {
        assertTrue(option +" not selected..!", khResourcePage.showHidePopupSelectedOption().getText().trim().equalsIgnoreCase(option));
    }

    @Then("^the user closes the ToC by clicking on cross button$")
    public void theUserClosesTheToCByClickingOnCrossButton() throws Throwable {
        Is.is(khResourcePage.tocHiddenTitle().isDisplayed());
        khResourcePage.tocCrossIcon().click();
    }

    @Then("^the user should see ToC hidden with menu toggle button on left side$")
    public void theUserShouldSeeToCHiddenWithMenuToggleButtonOnLeftSide() throws Throwable {
        assertTrue("Cross Icon displayed..!", comMethods.isElementDisplayed(khResourcePage.tocMenuTogglCollapsedButton()));
    }
}