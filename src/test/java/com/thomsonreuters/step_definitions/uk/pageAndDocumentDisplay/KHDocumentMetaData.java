package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.annotations.SharedAnnotationsPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.DocumentRightPanelPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.DraftingNotes;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.KHResourcePage;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertTrue;

public class KHDocumentMetaData extends BaseStepDef {

    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private DocumentRightPanelPage rightPanelPage = new DocumentRightPanelPage();
    private KHResourcePage resourcePage = new KHResourcePage();
    private FooterUtils footerUtils = new FooterUtils();
    private SharedAnnotationsPage sharedAnnotationsPage = new SharedAnnotationsPage();

    public int notesIcons;

    @When("^user navigates directly to document with plcref \"(.*?)\"$")
    public void userNavigatesDirectlyToDocumentWithPlcref(String plcRef) throws Throwable {
        navigationCobalt.navigateToWLNSpecificResourcePage("/" + plcRef);
        resourcePage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^following jurisdictions are displayed on the document right hand panel$")
    public void followingJurisdictionsAreDisplayedOnTheDocumentRightHandPanel(List<String> expectedJurisdictions) throws Throwable {
        if (expectedJurisdictions.size() == 1 && expectedJurisdictions.get(0).contains(",")) {
            assertThat(rightPanelPage.getVisibleJurisdictions(), Is.is(Arrays.asList(expectedJurisdictions.get(0).split(","))));
        } else {
            if (expectedJurisdictions.get(0).equalsIgnoreCase("No Jurisdictions")) {
                LOG.debug("No Jurisdictions are displayed for this resource type");
            } else {
                assertThat(rightPanelPage.getVisibleJurisdictions(), Is.is(expectedJurisdictions));
            }
        }
    }

    @Then("^link 'View all' to display more jurisdictions is not displayed$")
    public void linkToDisplayMoreJurisdictionsIsNotDisplayed() throws Throwable {
        assertThat(rightPanelPage.isViewAllLinkDisplayed(), Is.is(false));
    }

    @When("^the user clicks on the (View Resource History|View Related Content) link on right hand panel$")
    public void theUserClicksOnTheViewLinkOnRightHandPanel(String linkText) throws Throwable {
        if (linkText.equalsIgnoreCase("View Resource History")) {
            rightPanelPage.relatedOrHistoryLink(linkText).click();
        } else if (linkText.equalsIgnoreCase("View Related Content")) {
            rightPanelPage.relatedContentLink().click();
        }
    }

    @Then("^the user is taken to the \"(.*?)\" box at the bottom of the resource page$")
    public void theUserIsTakenToTheResourceHistoryBoxAtTheBottomOfTheResourcePage(String navigationLink) throws Throwable {
        assertTrue(rightPanelPage.isSectionDisplayed(navigationLink));
    }

    @Then("^'(Related Content|View Resource History)' link is (displayed|Not displayed) on the right hand panel$")
    public void relatedContentLinkIsDisplayedOnTheRightHandPanel(String linkText, String display) throws Throwable {
        try {
            navigationCobalt.waitForPageToLoadAndJQueryProcessing();
            WebElement element = rightPanelPage.relatedOrHistoryLink(linkText);
            assertThat(element.isDisplayed(), Is.is(true));
        } catch (NoSuchElementException npe) {
            if (display.equalsIgnoreCase("displayed")) {
                throw npe;
            } else {
                LOG.debug(linkText + " is not displayed");
            }
        }
    }

    @Then("^'Ask a question' link is displayed$")
    public void askAQuestionLink() throws Throwable {
        assertThat(resourcePage.askAQuestion().isDisplayed(), Is.is(true));
    }

    @Then("^jurisdiction information is displayed on the right hand panel$")
    public void jurisdictionInfoIsDisplayed() throws Throwable {
        assertThat(rightPanelPage.getVisibleJurisdictions().size(), greaterThan(0));
    }


    @Then("^\"(.*?)\" is displayed underneath the title$")
    public void isDisplayedUnderneathTheTitle(String author) throws Throwable {
        try {
            String authorText = resourcePage.author().getText().trim();
            String authorTrimmed = authorText.substring(3);
            assertThat(authorTrimmed, Is.is(author));
        } catch (NoSuchElementException npe) {
            if (author.equalsIgnoreCase("No Author")) {
                LOG.debug("No Author is displayed for this resource type");
            } else {
                throw npe;
            }
        }
    }

    @Then("^document title is displayed as \"(.*?)\"$")
    public void titleIsDisplayedAs(String title) throws Throwable {
        assertThat(resourcePage.title().getText().trim().replaceAll("\\n", " "), Is.is(title.replaceAll("\\\\n", " ")));
    }

    @Then("^plc reference is displayed as \"(.*?)\"$")
    public void plcRefIsDisplayedAs(String plcRef) throws Throwable {
        assertThat(resourcePage.plcRef().getText().trim(), Is.is(plcRef));
    }

    @Then("^resource type is displayed as \"(.*?)\" on right hand panel$")
    public void documentTypeIsDisplayedAsArticles(String documentType) throws Throwable {
    	rightPanelPage.waitForPageToLoadAndJQueryProcessing();
        assertThat(rightPanelPage.resourceTypeText().getText().trim(), Is.is(documentType));
    }

    @And("^the user can go back and verify the link '(.*)' is on the '(.*)'$")
    public void the_user_can_go_back_and_verify_the_link_is_on_the_Page(String link, String page) throws Throwable {
        getDriver().navigate().back();
        assertThat("The link is NOT present on the page " + page, resourcePage.waitForElementPresent(By.linkText(link)).isDisplayed(), Is.is(true));
    }

    @And("^the user can go back by clicking back link '(.*)' and verify the link '(.*)' is on the '(.*)'$")
    public void the_user_can_go_back_by_clicking_back_link_and_verify_the_link_is_on_the_Topic_Page(String backLink, String link, String page) throws Throwable {
        resourcePage.waitForExpectedElement(By.linkText(backLink)).click();
        assertThat("The link is NOT present on the page " + page, resourcePage.waitForElementPresent(By.linkText(link)).isDisplayed(), Is.is(true));
    }

    @When("^user clicks on 'View all' link to view all jurisdictions$")
    public void userClicksOnLinkToViewAllJurisdictions() throws Throwable {
        rightPanelPage.jurisdictionViewAllLink().click();
    }

    @Then("^the user can read the label listing the countries as \"(.*?)\"$")
    public void theUserCanReadTheLabelListingTheCountriesAs(String label) throws Throwable {
        assertThat(rightPanelPage.jurisdictionLabel().getText(), Is.is(label));
    }

    @When("^user agrees to view the document if out of plan$")
    public void viewOutOfPlanDocument() throws Throwable {
        if (getDriver().findElements(By.id("co_WarnViewOkButton")).size() > 0) {
            getDriver().findElement(By.id("co_WarnViewOkButton")).click();
        }
    }

    @Given("^resource status \"(.*?)\" is displayed on the document right hand panel$")
    public void resourceStatusIsDisplayed(String expectedStatus) throws Throwable {
        try {
            assertThat(rightPanelPage.documentStatus().getText().trim().replaceAll("\\n", ""), Is.is(expectedStatus.replaceAll("\\\\n", "")));
        } catch (NoSuchElementException npe) {
            if (expectedStatus.equalsIgnoreCase("No Status")) {
                LOG.debug("No Status is displayed for this resource type");
            } else {
                throw npe;
            }
        }
    }

    @Then("^author name \"(.*?)\" is displayed underneath the document title$")
    public void authorNameIsDisplayedUnderneathTheDocumentTitle(String authorName) throws Throwable {
        assertThat(resourcePage.author().getText().trim(), Is.is(authorName));
    }

    @Then("^clicking on (author|external) link \"(.*?)\" opens in (new|same) window$")
    public void authorLinkOpensInNewWindow(String authorOrExternal, String link, String windowType) throws Throwable {
        switch (windowType) {
            case "new":
                String currentWindowHandle = getDriver().getWindowHandle();
                if (authorOrExternal.equalsIgnoreCase("external")) {
                    //Thread.sleep(2000);
                    try {
                        resourcePage.contentBody().findElement(By.linkText(link)).click();
                    } catch (Exception e) {
                        resourcePage.contentBody().findElement(By.linkText(link)).click();
                    }
                } else {
                    resourcePage.author().findElement(By.linkText(link)).click();
                }

                getDriver().switchTo().window(currentWindowHandle);
                resourcePage.clickOnSuspendBillingContinueButton();
                break;

            // TODO - remove sleeps
            case "same":
                String documentTitle = resourcePage.title().getText().trim();
                resourcePage.author().findElement(By.linkText(link)).click();
                Thread.sleep(2000);
                getDriver().navigate().back();
                Thread.sleep(2000);
                assertThat(resourcePage.title().getText().trim(), Is.is(documentTitle));
                break;

            default:
                break;
        }
    }

    @Then("^user is able to expand the first drafting notes$")
    public void expandDraftingNotes() throws Throwable {
        resourcePage.firstCollapsedDraftingNotes().click();
    }

    @Then("^see the expanded drafting notes heading is \"(.*)\"$")
    public void expandAndCloseDraftingNotes(String expectedHeading) throws Throwable {
        assertThat(resourcePage.activeDraftingNoteHeading().getText().trim().toLowerCase(), Is.is(expectedHeading.toLowerCase()));
    }

    @Then("^user is able to close the first drafting notes$")
    public void closeDraftingNotes() throws Throwable {
        resourcePage.closeDraftingNote().click();
        assertThat(resourcePage.isThereAnyExpandedDraftingNote(), Is.is(false));
    }

    @Then("^user scroll down the resource by offset (\\d+)$")
    public void scrollDownTheResource(int offset) throws Throwable {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollTo(0," + offset + ");");
    }

    @Then("^back to top sticky link is displayed$")
    public void backToTopIsDisplayed() throws Throwable {
        footerUtils.closeDisclaimerMessage();
        sharedAnnotationsPage.waitForDisclaimerAbsent();
        assertThat(resourcePage.backToTop().isDisplayed(), Is.is(true));
    }

    @Then("^user can navigate to top from anywhere in the document by clicking on the back to top link$")
    public void navigateToTop() throws Throwable {
        resourcePage.backToTop().click();
        Thread.sleep(2000);
        // TODO -Figure out how to implement this
        // One way to check that sticky bar is no more visible
    }

    @Then("^the document title \"(.*?)\" is displayed on the sticky bar$")
    public void theDocumentTitleIsDisplayedOnTheStickyBar(String expectedTitle) throws Throwable {
        String actualTitle = resourcePage.titleOnStickyBar().getText().trim();
        int endIndex = actualTitle.contains("\n") ? actualTitle.indexOf("\n") : actualTitle.length();
        LOG.info("Heading = " + actualTitle.substring(0, endIndex).trim());
        assertThat(actualTitle.substring(0, endIndex).trim(), Is.is(expectedTitle));
    }

    @Given("^user scroll down the resource to heading \"(.*?)\"$")
    public void userScrollDownTheResourceToHeading(String heading) throws Throwable {
        for (WebElement headingElement : resourcePage.allHeadings()) {
            if (headingElement.getText().trim().equalsIgnoreCase(heading)) {
                scrollToElement(headingElement);
            }
        }
    }

    @Then("^scrolled heading \"(.*?)\" is displayed on the sticky bar$")
    public void scrolledHeadingIsDisplayedOnTheStickyBar(String expectedHeading) throws Throwable {
        assertThat(resourcePage.subSectionHeadingOnStickyBar().getText().trim(), Is.is(expectedHeading));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Then("^'Related Content' link is displayed on the sticky bar$")
    public void relatedContentLinkIsDisplayedOnTheStickyBar() throws Throwable {
        assertThat(resourcePage.relatedContentLinkOnStickyBar().isDisplayed(), Is.is(true));
    }

    @Then("^clicking on 'Related Content' link on sticky bar jumps to Related Content section$")
    public void clickingOnRelatedContentLinkOnStickyBarJumpsToRelatedContentSection() throws Throwable {
        resourcePage.relatedContentLinkOnStickyBar().click();
        // TODO - Figure out how to implement this
        LOG.debug("Nothing");
    }

    @Then("^the user can see 3 latest resource histories displayed$")
    public void theFollowingResourceHistoryEntriesAreVisible() throws Throwable {
        int listSize = resourcePage.visibleResourceHistoryList().size();
        assertThat(listSize, Is.is(3));
    }

    @When("^the user clicks on the 'View Resource History' link on the resource page$")
    public void theUserClicksOnTheViewResourceHistoryLinkOnTheResourcePage() throws Throwable {
        rightPanelPage.viewResourceHistoryLink().click();
    }

    @Then("^the link \"(.*?)\" is displayed$")
    public void theLinkIsDisplayed(String linkText) throws Throwable {
        assertTrue(resourcePage.viewAllAndLatestResourceHistoryLink().isDisplayed());
    }

    @Then("^user clicks on '(View All|View Latest)' to view (all|latest) resource histories$")
    public void theUserClicksOnLink(String linkText, String allOrLatest) throws Throwable {
        WebElement element = resourcePage.viewAllAndLatestResourceHistoryLink();
        assertThat(element.getText().trim(), Is.is(linkText));
        element.click();
    }

    @Then("^the user can now see more than (\\d+) resource history entries$")
    public void theUserCanNowSeeMoreThanResourceHistoryEntries(int size) throws Throwable {
        int listSize = resourcePage.visibleResourceHistoryList().size();
        assertTrue("less than 3 entries are displayed", listSize >= size);
    }

    @Then("^the following message is displayed in the resource history section$")
    public void theFollowingMessageIsDisplayedInTheResourceHistorySection(List<String> expectedMessage) throws Throwable {
        String actualText = resourcePage.visibleResourceHistoryList().get(0).getText();
        String expectedText = expectedMessage.get(0);
        assertThat(actualText.replace("\n", " ").trim(), Is.is(expectedText));
    }

    @Then("^the link 'View All' to display more resource entries is not visible$")
    public void theLinkToDisplayMoreResourceEntriesIsNotVisible() throws Throwable {
        assertThat(resourcePage.viewAllResourceHistoryLink(), Is.is(false));
    }

    @Then("^user \"(.*?)\" see the embedded resources within this document$")
    public void userSeeTheEmbeddedResourcesWithinThisDocument(String canOrCannot) throws Throwable {
        if (canOrCannot.equalsIgnoreCase("cannot")) {
            assertThat(resourcePage.visibleEmbeddedResources().size(), Is.is(0));
        } else {
            assertTrue(resourcePage.visibleEmbeddedResources().size() > 0);
        }
    }

    @Then("^user is able to see the \"(.*?)\" link on the document$")
    public void userIsAbleToSeeTheLinkOnTheDocument(String showHideLink) throws Throwable {
        assertThat(resourcePage.showHideIndividualCasesLink(showHideLink).getText().trim(), Is.is(showHideLink));
    }

    @When("^user clicks on \"(.*?)\" link on this case tracker page$")
    public void userClicksOnLinkOnThisPage(String link) throws Throwable {
        resourcePage.showHideIndividualCasesLink(link).click();
    }

    @When("^the user clicks on the embedded resources link \"(.*?)\"$")
    public void theUserClicksOnTheEmbeddedResourcesLink(String embeddedLink) throws Throwable {
        try {
            resourcePage.embeddedResourceLink(embeddedLink).click();
        } catch (Exception e) {
            resourcePage.embeddedResourceLink(embeddedLink).click();
        }
    }

    @Then("^the user is navigated to the child case tracker page with title \"(.*?)\"$")
    public void theUserIsNavigatedToTheChildCaseTrackerPageWithTitle(String title) throws Throwable {
        resourcePage.waitForPageToLoadAndJQueryProcessing();
        assertThat(resourcePage.title().getText().trim(), Is.is(title));
    }

    @Then("^the user should not see the \"(.*?)\" tag on the What's Market document$")
    public void theUserShouldNotSeeTheTagOnTheWhatSMarketDocument(String lastViewed) throws Throwable {
        try {
            assertThat(resourcePage.whatsMarketLastViewedTag().getText().trim(), isEmptyString());
        } catch (NoSuchElementException ne) {
            LOG.info("Last Viewed Tag is no more visible on UK What's Market resources");
        }
    }

    @And("^clicks on the Show/Hide Drafting Notes option on the delivery toolbar$")
    public void clicksOnTheShowHideDraftingNotesOptionOnTheDeliveryToolbar() throws Throwable {
        resourcePage.selectShowAndHideDraftingNotesLink();
    }

    @Then("^the following drafting notes options are displayed$")
    public void theFollowingDraftingNotesOptionsAreDisplayed(List<String> options) throws Throwable {
        List<String> actualOptions = resourcePage.getNotesOptions();
        assertTrue(actualOptions.size() == 3);
        for (String expectedOption : options) {
            assertTrue(actualOptions.contains(expectedOption));
        }
    }

    @When("^user clicks on the 'Show All' option$")
    public void userClicksOnTheShowAllOption() throws Throwable {
        notesIcons = resourcePage.getNotesIconsCount();
        resourcePage.selectOptionFromDraftingNotes(DraftingNotes.SHOW_ALL);
    }

    @Then("^the document text along with the drafting notes expanded version is displayed$")
    public void theDocumentTextAlongWithTheDraftingNotesExpandedVersionIsDisplayed() throws Throwable {
        int notesCount = resourcePage.getDisplayedNotesCount();
        assertTrue(notesIcons == notesCount);
    }

    @When("^user clicks on the 'Hide All' option$")
    public void userClicksOnTheHideAllOption() throws Throwable {
        resourcePage.selectOptionFromDraftingNotes(DraftingNotes.HIDE_ALL);
    }

    @Then("^the document text along with drafting notes collapsed version is displayed$")
    public void theDocumentTextAlongWithDraftingNotesCollapsedVersionIsDisplayed() throws Throwable {
        assertTrue(resourcePage.getDisplayedNotesCount() == 0);
    }

    @When("^user clicks on the 'Show Notes Only' option$")
    public void userClicksOnTheShowNotesOnlyOption() throws Throwable {
        notesIcons = resourcePage.getNotesIconsCount();
        resourcePage.selectOptionFromDraftingNotes(DraftingNotes.SHOW_NOTES_ONLY);
    }

    @Then("^only the drafting notes expanded version is displayed$")
    public void onlyTheDraftingNotesExpandedVersionIsDisplayed() throws Throwable {
        int notesCount = resourcePage.getDisplayedNotesCount();
        assertTrue(notesCount > 0);
        assertTrue(notesIcons == notesCount);
        assertFalse(resourcePage.isContentParagraphsDisplayed());
    }

}