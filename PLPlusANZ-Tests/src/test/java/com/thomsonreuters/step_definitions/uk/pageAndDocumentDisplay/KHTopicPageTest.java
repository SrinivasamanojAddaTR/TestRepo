package com.thomsonreuters.step_definitions.uk.pageAndDocumentDisplay;

import com.thomsonreuters.pageobjects.pages.delivery.EmailOptionsPage;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.KHResourcePage;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.ListOfItemsDeliveryOptionsPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.TopicPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class KHTopicPageTest extends BaseStepDef {

    private TopicPage topicPage = new TopicPage();
    private KHResourcePage resourcePage = new KHResourcePage();
    private EmailOptionsPage email = new EmailOptionsPage();
    private HomePage homePage = new HomePage();
    private ListOfItemsDeliveryOptionsPage listOfItemsDeliveryOptionsPage = new ListOfItemsDeliveryOptionsPage();
    private CategoryPage categoryPage = new CategoryPage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    public static final String TOPIC_TAB = "Topics";


    @When("^the user navigates to practice area \"(.*?)\" filtered by \"(.*?)\" topic page$")
    public void theUserNavigatesToPracticeAreaFilteredByTopicPage(String practiceArea, String topicName) throws Throwable {
        homePage.selectLinkPresentOnTab(practiceArea);
        categoryPage.openPageByText(TOPIC_TAB);
        topicPage.clickTopicLinkOnPAPage(topicName).click();
        resourcePage.waitForPageToLoad();
    }

    @Then("^the user is presented with a topic page with title \"(.*?)\"$")
    public void theUserIsPresentedWithATopicPageWithTitle(String titleName) throws Throwable {
        assertTrue(topicPage.topicPageTitle().getText().equals(titleName));
    }

    @Then("^the following resources are present under the Editors Picks$")
    public void theFollowingResourcesArePresentUnderTheEditorsPicks(List<KHEditorsPicks> expectedResources) throws Throwable {
        Map<String, String> actualEditorPicks = topicPage.getEditorPicksAsMap();
        for (KHEditorsPicks resource : expectedResources) {
            String actualMetaData = actualEditorPicks.get(resource.getResourceLink());
            assertFalse(resource.getResourceLink() + " is not present in Editors Picks", null == actualMetaData);
            assertThat(actualMetaData, Is.is(resource.getMetadata().replace(";", "|")));
        }
    }

    @When("^the user clicks on the resource link \"(.*?)\" in Editor's pick widget$")
    public void theUserClicksOnTheResourceLinkInEditorSPickWidget(String resourceLink) throws Throwable {
        topicPage.clickResourceLinkOnEditorsPick(resourceLink).click();
    }

    @When("^the user (selects|unselects) the following Editor's Picks resources$")
    public void theUserSelectsTheFollowingEditorSPicksResources(String selection, List<String> resources) throws Throwable {
        if ((selection.equals("selects")) || (selection.equals("unselects"))) {
            for (String resource : resources) {
                topicPage.selectEditorsPickResourceByTitle(resource);
            }
        }
    }

    @Then("^the following icons are enabled$")
    public void theFollowingIconsAreEnabled(List<String> deliveryOptions) throws Throwable {
        for (String option : deliveryOptions) {
            assertThat(option + " is disabled", listOfItemsDeliveryOptionsPage.enabledDeliveryOption(option), Is.is(true));
        }
    }

    @Then("^the following icons are disabled")
    public void theFollowingIconsAreDisabled(List<String> deliveryOptions) throws Throwable {
        for (String option : deliveryOptions) {
            assertThat(option + " is enabled", listOfItemsDeliveryOptionsPage.disabledDeliveryOption(option), Is.is(true));
        }
    }

    @When("^the user (selects|unselects) the following Topic page resources$")
    public void theUserSelectsTheFollowingTopicPageResources(String selection, List<String> resources) throws Throwable {
        if ((selection.equals("selects")) || (selection.equals("unselects"))) {
            for (String resource : resources) {
                topicPage.selectTopicPageResourceByTitle(resource);
            }
        }
    }

    @When("^the user selects \"(Email|Print|Download|Save to Folder)\" delivery option on Topics Page$")
    public void theUserSelectsDeliveryOptionOnTopicsPage(String deliveryOption) throws Throwable {
        switch (deliveryOption) {
            case "Email":
                listOfItemsDeliveryOptionsPage.emailLink().click();
                email.emailButton().isDisplayed();
                break;
            case "Print":
                listOfItemsDeliveryOptionsPage.printLink().click();
                break;
            case "Download":
                listOfItemsDeliveryOptionsPage.downloadLink().click();
                break;
            case "Save to Folder":
                listOfItemsDeliveryOptionsPage.saveToFolderLink().click();
                break;
            default:
                break;
        }
    }

    public class KHEditorsPicks {
        private String resourceLink;
        private String metadata;

        public String getResourceLink() {
            return resourceLink;
        }

        public String getMetadata() {
            return metadata;
        }
    }

    @Then("^following optional blocks are displayed on the right hand side$")
    public void optionalBlockIsDisplayedOnTheRightHandSide(List<String> expectedHeaders) throws Throwable {
        for (String blockTitle : topicPage.optionalBlockTitle()) {
            int count = 0;
            for (String expectedTitle : expectedHeaders) {
                if (blockTitle.equals(expectedTitle)) {
                    count++;
                    break;
                }
            }
            assertTrue(blockTitle + "is not present", count > 0);
        }
    }

    @When("^following optional blocks are not displayed on the right hand side$")
    public void followingOptionalBlocksAreNotDisplayedOnTheRightHandSide(List<String> expectedHeaders) throws Throwable {
        for (String blockTitle : topicPage.optionalBlockTitle()) {
            int count = 0;
            for (String expectedTitle : expectedHeaders) {
                if (!blockTitle.equals(expectedTitle)) {
                    count++;
                }
            }
            assertTrue(blockTitle + "is present", count > 0);
        }
    }

    @Then("^the number of resources displayed on the first page is (\\d+)$")
    public void theNumberOfResourcesDisplayedOnTheFirstPageIs(int maxNoOfResources) throws Throwable {
        resourcePage.waitForPageToLoad();
        resourcePage.waitForExpectedElement(topicPage.resourceDocByTitle(), 5000);
        assertThat(topicPage.totalResourcesOnFirstPage(), Is.is(maxNoOfResources));
    }

    @When("^the user clicks on the resource link \"(.*?)\" under Practice Notes resource type$")
    public void theUserClicksOnTheResourceLinkUnderPracticeNotesResourceType(String resourceName) throws Throwable {
        topicPage.clickTopicLinkOnPAPage(resourceName).click();
    }

    @When("^the facet ordering along is as follows$")
    public void theFacetOrderingAlongWithCorrespondingResourceCountIsAsFollows(List<String> expectedFacetNames) throws Throwable {
        List<String> actualFacetNameList = topicPage.getTopicPageFacetsAsList();
        assertEquals(actualFacetNameList, expectedFacetNames);
    }

    @When("^user clicks on Page \"(.*?)\" of the Topic page results list$")
    public void userClicksOnPageOfTheTopicPageResultsList(int pageNum) throws Throwable {
        topicPage.waitForPageToLoadAndJQueryProcessing();
        topicPage.pageNumber(pageNum).click();
        resourcePage.waitForPageToLoad();
        assertThat(Integer.parseInt(topicPage.currentPageSelected()), Is.is(pageNum));
    }

    @Then("^user cannot see the Editor's pick widget on this page$")
    public void userCannotSeeTheEditorSPickWidgetOnThisPage() throws Throwable {
        assertThat("Editor's pick widget is displayed", topicPage.noEditorsPickWidget(), Is.is(true));
    }

    @Then("^user can see the Editor's pick widget on this page$")
    public void userCanSeeTheEditorSPickWidgetOnThisPage() throws Throwable {
        assertThat("Editor's pick widget is not displayed", topicPage.noEditorsPickWidget(), Is.is(false));
    }

    @When("^clicks on the facet group \"(.*?)\"$")
    public void clicksOnTheFacetGroup(String facetName) throws Throwable {
        knowHowSearchResultsPage.knowHowFacetCheckbox(facetName).click();
        //topicPage.facetNameLink(facetName).click();
        topicPage.waitForPageToLoad();
    }

    @Then("^the documents listed under resource group \"(.*?)\" should be alphabetically ordered as below$")
    public void theDocumentsListedUnderResourceGroupShouldBeAlphabeticallyOrderedAsBelow(String resourceType, List<String> expectedResources) throws Throwable {
        if (resourceType.equals("Practice note: overview")) {
            resourceType = "Practice_note:_overview";
        } else if (resourceType.equals("Practice notes")) {
            resourceType = "Practice_notes";
        }
        assertTrue(expectedResources.equals(topicPage.getResourcesList(resourceType)));
    }

    @Then("^only '(.+)' are displayed on topic page$")
    public void onlyChecklistsAreDisplayedOnTopicPage(String resourceType) throws Throwable {
        topicPage.waitForPageToLoadAndJQueryProcessing();
        assertTrue(topicPage.resultsByResourceType(resourceType).isDisplayed());
        List<WebElement> otherResourceTypes = getDriver().findElements(By.xpath("//div[@id='cobalt_search_knowhowtopicuk_results']/div[2]"));
        assertTrue(otherResourceTypes.isEmpty());
    }
}