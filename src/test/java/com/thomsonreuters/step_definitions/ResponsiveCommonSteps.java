package com.thomsonreuters.step_definitions;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.pages.ask.AskResourcePage;
import com.thomsonreuters.pageobjects.pages.folders.CreateGroupPopup;
import com.thomsonreuters.pageobjects.pages.folders.FavouritesPage;
import com.thomsonreuters.pageobjects.pages.footer.WLNFooter;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawUKCategoryPage;
import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesResultsPage;
import com.thomsonreuters.pageobjects.pages.onePass.OnePassLogoutPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.KHResourcePage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.TopicPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.pages.search.WhatsMarketSearchResultsPage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResponsiveCommonSteps extends BaseStepDef {

    private WLNFooter footer = new WLNFooter();
    private WLNHeader header = new WLNHeader();
    private CommonMethods commonMethods = new CommonMethods();
    private KHResourcePage khResourcePage = new KHResourcePage();
    private KnowHowSearchResultsPage knowHowSearchResultsPage = new KnowHowSearchResultsPage();
    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private LegalUpdatesResultsPage legalUpdatesResultsPage = new LegalUpdatesResultsPage();
    private CategoryPage categoryPage = new CategoryPage();
    private FavouritesPage favouritesPage = new FavouritesPage();
    private CreateGroupPopup createGroupPopup = new CreateGroupPopup();
    private TopicPage topicPage = new TopicPage();
    private WhatsMarketSearchResultsPage whatsMarketSearchResultsPage = new WhatsMarketSearchResultsPage();
    private PageActions pageActions = new PageActions();
    private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();
    private AskResourcePage askResourcePage = new AskResourcePage();
    private PracticalLawUKCategoryPage practicalLawUKCategoryPage = new PracticalLawUKCategoryPage();
    private FooterUtils footerUtils = new FooterUtils();

    private String favGroupName = null;

    @When("^the user clicks on \"(.*?)\" link$")
    public void theUserClicksOnLink(String linkText) {
        knowHowSearchResultsPage.getElementByLinkText(linkText).click();
    }

    @Then("^the user is able to see the search results with following features according to the design document$")
    public void theUserIsAbleToSeeTheSearchResultsWithFollowingFeaturesAccordingToTheDesignDocument(List<String> searchStructList) throws Throwable {
        String feature = null;
        for (int count = 0; count < searchStructList.size(); count++) {
            feature = searchStructList.get(count);
            if (feature.equalsIgnoreCase("summary")) {
                searchResultsPage.moreOrLessDetailAnchor().click();
                searchResultsPage.moreDetailOption().click();
                assertTrue("Summary not displayed..!", knowHowSearchResultsPage.searchFirstResultElementsSummary().getAttribute("class").contains(feature));
            } else if (feature.equalsIgnoreCase("Status") || feature.equalsIgnoreCase("Document Type") || feature.equalsIgnoreCase("Jurisdiction")) {
                assertTrue(feature + " Type not displayed..!", knowHowSearchResultsPage.searchFirstResultElementsList().get(count).getText().contains(feature));
            } else {
                assertTrue("Value doesn't exist....!", false);
            }
        }
    }

    @Then("^user sign out$")
    public void userSignOut() throws Throwable {
        pageActions.mouseOver(header.usernameIcon());
        header.signOutSubLink().click();
        header.waitForPageToLoadAndJQueryProcessing();
        onePassLogoutPage.signOffPageSignOnButton().click();
    }

    @Then("^user should see footer$")
    public void userShouldSeeFooter() throws Throwable {
        header.waitForPageToLoad();
        //commonMethods.scrollUpOrDown(1300);
        assertTrue("Footer is not displayed..!", footer.footerWidget().isDisplayed());
    }

    @When("^user clicks the \"(.*?)\" link$")
    public void userClicksTheUnitedKingdomLink(String linkText) throws Throwable {
        header.waitForPageToLoad();
        header.waitForPageToLoadAndJQueryProcessing();
        header.getElementByLinkText(linkText).click();
        header.waitForPageToLoad();
    }

    @Then("^user clicks the Twitter link$")
    public void userclickstheTwitterLink() throws Throwable {
        ((JavascriptExecutor) getDriver()).executeScript("scroll(0,1550);");
        WebElement element = footer.twitterArrowLink();
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    @Then("^the user selects the \"(.*?)\" from per page dropdown$")
    public void theUserSelectsThePerPagefromPerPageDropdown(String perPageNo) throws Throwable {
        footerUtils.closeDisclaimerMessage();
        if (!legalUpdatesResultsPage.resultsPerPageLink().getText().contains(perPageNo)) {
            knowHowSearchResultsPage.searchPerPageDrodownLink().click();
            for (WebElement link : knowHowSearchResultsPage.searchPerPageDrodownListItemLinks()) {
                if (link.getText().trim().contains(perPageNo)) {
                    link.click();
                    break;
                }
            }
            assertTrue("Count is not right..!", knowHowSearchResultsPage.isTextPresent(knowHowSearchResultsPage.searchResultByCountLabel(), perPageNo, 5000));
        }
    }

    @Then("^user verifies the navigation to \"(.*?)\" not present$")
    public void userVerifiesTheNotPresent(String linkText) throws Throwable {
        boolean notPresent = true;
        for (WebElement link : knowHowSearchResultsPage.searchPaginationItemLinks()) {
            if (link.getText().trim().equalsIgnoreCase(linkText)) {
                notPresent = false;
                break;
            }
        }
        assertTrue(linkText + " not present..!", notPresent);
    }

    @Then("^user verifies the \"(.*?)\" present$")
    public void userVerifiesThePresent(String linkText) throws Throwable {
        boolean present = false;
        for (WebElement link : knowHowSearchResultsPage.searchPaginationItemLinks()) {
            if (link.getText().trim().equalsIgnoreCase(linkText)) {
                present = true;
                break;
            }
        }
        assertTrue(linkText + " not present..!", present);
    }

    @Then("^clicks on the \"(.*?)\" pagination link$")
    public void clicksOnThePaginationLink(String pageNoOrText) throws Throwable {
        for (WebElement link : knowHowSearchResultsPage.searchPaginationItemLinks()) {
            if (link.getText().trim().equalsIgnoreCase(pageNoOrText)) {
                link.click();
                break;
            }
        }
    }

    @Then("^user should see the label \"(.*?)\" in the search result heading$")
    public void userShouldSeetheLabelInTheSearchResultHeading(String label) throws Throwable {
        assertTrue(label + " not displayed..!", knowHowSearchResultsPage.searchResultHeading().getText().toLowerCase().contains(label.toLowerCase()));
    }

    @Then("^the user should be seeing \"(.*?)\" per page$")
    public void userShouldbeSeeingPerPage(String perPageNo) throws Throwable {
        int expectedNoOfResults = Integer.parseInt(perPageNo);
        knowHowSearchResultsPage.waitForPageToLoad();
        knowHowSearchResultsPage.waitForPageToLoadAndJQueryProcessing();
        commonMethods.scrollUpOrDown(70000);
        int actualNoOfResults = knowHowSearchResultsPage.searchResultsItemsList().size();
        if (actualNoOfResults > expectedNoOfResults) {
            assertTrue("Number of results not matching: expected " + expectedNoOfResults + " , actual " + actualNoOfResults,
                    expectedNoOfResults == actualNoOfResults);
        }
    }

    @Then("^user should see the \"(.*?)\" page$")
    public void userShouldseethePage(String pageDesc) throws Throwable {
        assertTrue("No Of Results Not matching..!", standardDocumentPage.documentTitle().getText().trim().contains(pageDesc));
    }

    @Then("^user should have suggestion i.e. \"(.*?)\"$")
    public void userShouldHaveSuggestion(String pageDesc) throws Throwable {
        String didYouMeanText = knowHowSearchResultsPage.searchNoResultParagraph().getText();
        didYouMeanText = didYouMeanText.replaceAll("\\r\\n|\\r|\\n", " ");
        assertTrue("Did you mean Not Matching..!", didYouMeanText.contains(pageDesc));
    }

    @Then("^user should not see any filters on the left hand side$")
    public void userShouldNotSeeAnyFiltersOnLeft() throws Throwable {
        assertFalse("User able to see search filter facets", knowHowSearchResultsPage.isSearchFilterFacetDisplayed());
    }

    @Then("^the user clicks on search result \"(.*)\" title link$")
    public void theUserClicksOnSearchResultTitleLink(String titleLinkText) throws Throwable {
        for (WebElement titleLink : searchResultsPage.getAllSearchTitleLinks()) {
            if (titleLink.getText().trim().equalsIgnoreCase(titleLinkText)) {
                titleLink.click();
                break;
            }
        }
        assertTrue(titleLinkText + " not found..!", khResourcePage.title().getText().trim().contains(titleLinkText));
    }

    @Then("^user clicks the Home Icon to make it as Start Page$")
    public void userClicksTheHomeIcon() throws Throwable {
        categoryPage.makeThisMyStartPage();
        categoryPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^user clicks the Home Icon to remove it as Start Page$")
    public void userClicksTheHomeIcontoRemoveItasStartPage() throws Throwable {
        categoryPage.removeThisAsMyStartPage();
        categoryPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^user should not see any \"(.*)\" link$")
    public void userShouldNotSeeAnyLink(String linkText) throws Throwable {
        assertFalse(linkText + " is still visible..!", commonMethods.isLinkTextPresent(linkText, 2000));
    }

    @When("^the user scrolls down the page \"(.*?)\"$")
    public void theUserScrollsDownThePage(String amountText) throws Throwable {
        if (amountText.equalsIgnoreCase("a bit")) {
            commonMethods.scrollUpOrDown(1000);
        } else if (amountText.equalsIgnoreCase("more")) {
            commonMethods.scrollUpOrDown(3000);
        } else if (amountText.equalsIgnoreCase("way down")) {
            commonMethods.scrollUpOrDown(5000);
        }
    }

    @When("^user clicks on \"(.*?)\" button$")
    public void userClicksOnButton(String buttonText) throws Throwable {
        if (buttonText.equalsIgnoreCase("organize")) {
            favouritesPage.organize().click();
        } else if (buttonText.equalsIgnoreCase("done")) {
            favouritesPage.doneOrganizing().click();
        } else if (buttonText.equalsIgnoreCase("Cancel")) {
            favouritesPage.favGroupCancelButton(favGroupName).click();
        }
    }

    @Then("^user should see \"Done\" button$")
    public void userShouldSeeButton() throws Throwable {
        assertTrue("Done Button not displayed..!", favouritesPage.isElementDisplayed(favouritesPage.doneOrganizing()));
    }

    @When("^user creates new favourites group '(.+)'$")
    public void UsercreateNewFavoriteGroup(String groupName) throws Throwable {
        favouritesPage.waitForPageToLoadAndJQueryProcessing();
        if (favouritesPage.checkFavouriteGroupIsPresent(groupName)) {
            favouritesPage.organize().click();
            commonMethods.mouseOver(favouritesPage.favouriteGroup(groupName));
            favouritesPage.deleteFavouriteGroupButton(groupName).click();
            favouritesPage.doneOrganizing().click();
        }
        favouritesPage.addGroupLink().click();
        createGroupPopup.groupName().sendKeys(groupName);
        createGroupPopup.save().click();
        createGroupPopup.waitForPageToLoad();
        favGroupName = groupName;
        favouritesPage.waitForPageToLoad();
        favouritesPage.waitForPageToLoadAndJQueryProcessing();
    }

    @When("^user hovers over the group '(.+)'$")
    public void UsercHoversOveTheGroup(String groupName) throws Throwable {
    	pageActions.mouseOver(favouritesPage.favouriteGroup(groupName));
        //commonMethods.mouseOver(favouritesPage.favouriteGroup(groupName));
    }

    @When("^user should see the aligned \"Save\" and \"Cancel\" button for group \'(.*)\'$")
    public void UsercShouldSeeTheSavveAndCancelButtons(String groupName) throws Throwable {
        assertTrue("Save Button not displayed..!", header.isElementDisplayed(favouritesPage.renameFavouriteOKGroupButton(groupName)));
        assertTrue("Cancel Button not displayed..!", header.isElementDisplayed(favouritesPage.renameGroupCancelButton(groupName)));
    }

    @When("^user should see the group '(.+)'$")
    public void UserShouldSeeTheGroup(String groupName) throws Throwable {
        favouritesPage.waitForExpectedElement(favouritesPage.favouriteByGroup(groupName), 3000);
        assertTrue(groupName + " not displayed..!", favouritesPage.checkFavouriteGroupIsPresent(groupName));
    }

    @When("^the user navigates to \"(.*)\" resource Page$")
    public void the_user_navigates_to_whats_Market_Page(String resourceType) throws Throwable {
        header.browseMenuButton().click();
        assertTrue("Browse Menu not displayed..!", header.browseMenuPopup().isDisplayed());
        header.getElementByLinkText("Resources").click();
        header.getElementByLinkText(resourceType).click();
    }

    @When("^the user navigating to topic page \"(.*)\" of practice area \"(.*)\"$")
    public void the_user_navigates_to_topicpage_of_Practice_area(String topicName, String PAName) throws Throwable {
        header.browseMenuButton().click();
        assertTrue("Browse Menu not displayed..!", header.browseMenuPopup().isDisplayed());
        header.getElementByLinkText(PAName).click();
        assertTrue(PAName + " not displayed..!", header.pageHeaderLabel().getText().contains(PAName));
        for (WebElement tab : footer.pageTabLinks()) {
            if (tab.getText().trim().contains("Topics")) {
                tab.click();
                header.waitForElementVisible(tab, 2000);
                break;
            }
        }
        header.getElementByLinkText(topicName).click();
        assertTrue(topicName + " not displayed..!", header.pageHeaderLabel().getText().contains(topicName));
    }

    @Then("^the user should see the page no \"(.*?)\"$")
    public void userShouldseethePageNo(String pageNo) throws Throwable {
        assertTrue("Page No " + pageNo + " not displayed..!", topicPage.currentPageSelected().trim().contains(pageNo));
    }

    //the user varifies each page by navigates through each of the following pages
    @Then("^the user varifies each page by navigates through each of the following pages$")
    public void theUserVarifiesEachPageByNavigatesThroughEachOfFollowingPage(List<String> pageNos) throws Throwable {
        for (String pageNo : pageNos) {
            if (!pageNo.equalsIgnoreCase("Pages")) {
                topicPage.getElementByLinkText(pageNo).click();
                assertTrue("Page No " + pageNo + " not displayed..!", topicPage.currentPageSelected().trim().contains(pageNo));
            }
        }

    }

    @Then("^user should see the following metadata in the deal \"(.*?)\"$")
    public void userShouldsethePageNo(String dealTitle, DataTable dataTable) throws Throwable {
        Map<String, String> table = dataTable.asMap(String.class, String.class);
        int record = 0;
        boolean flag = false;
        searchResultsPage.moreOrLessDetailAnchor().click();
        searchResultsPage.mostDetailOption().click();
        searchResultsPage.waitForElementVisible(whatsMarketSearchResultsPage.searchResultsByTitleLink(1));
        while (!flag) {
            for (WebElement actualDealTitleLink : whatsMarketSearchResultsPage.searchResultsTitleLinks()) {
                if (actualDealTitleLink.getText().contains(dealTitle)) {
                    ++record;
                    flag = true;
                    break;
                }
                record++;
            }
            if (!flag) {
                searchResultsPage.waitForExpectedElement(searchResultsPage.selectNextPageByLink(), 4000).click();
            }
        }
        for (Map.Entry<String, String> rowEntry : table.entrySet()) {
            if (rowEntry.getKey().equalsIgnoreCase("Announcement Date")) {
                assertTrue(rowEntry.getValue() + " not displayed..!", whatsMarketSearchResultsPage.resultDate(String.valueOf(record)).getText().contains(rowEntry.getValue()));
            } else if (rowEntry.getKey().equalsIgnoreCase("Deal Type")) {
                assertTrue(rowEntry.getValue() + " not displayed..!", whatsMarketSearchResultsPage.resultDealType(String.valueOf(record), rowEntry.getValue()).getText().contains(rowEntry.getValue()));
            } else if (rowEntry.getKey().equalsIgnoreCase("Deal Value")) {
                assertTrue(rowEntry.getValue() + " not displayed..!", whatsMarketSearchResultsPage.resultValue(String.valueOf(record)).getText().contains(rowEntry.getValue()));
            } else if (rowEntry.getKey().equalsIgnoreCase("Deal Summary")) {
                assertTrue(rowEntry.getValue() + " not displayed..!", whatsMarketSearchResultsPage.resultSummary(String.valueOf(record)).getText().contains(rowEntry.getValue()));
            }
        }

    }

    @Then("^the user should see the \"Add reply\" link next to question$")
    public void theUserShouldSeeTheAddReplyLinkNextToQuestion() throws Throwable {
        Is.is(askResourcePage.addReplyNextToHeaderLink().isDisplayed());
    }

    @When("^user should see the stricken through group '(.+)'$")
    public void userShouldSeeTheStrickenThroughGroup(String groupName) throws Throwable {
        assertTrue(groupName + " not stricken through..!", favouritesPage.favouriteStrickenThroughGroup(groupName).isDisplayed());

    }

    @When("^user should see the stricken through group link '(.+)'$")
    public void userShouldSeeTheStrickenThroughGroupLink(String groupName) throws Throwable {
        assertTrue(groupName + " not stricken through..!", favouritesPage.favouriteStrickenThroughGroup(groupName).isDisplayed());

    }

    @When("^user hovers over the fav group '(.*)' link '(.*)'$")
    public void UsercHoversOveTheGroupLink(String groupName, String linkText) throws Throwable {
        commonMethods.mouseOver(favouritesPage.favouriteGroupLink(groupName, linkText));
    }

    @When("^user should see the stricken through link '(.*)' of group '(.*)'$")
    public void UserShouldSeeTheStrickenThroughLink(String linkText, String groupName) throws Throwable {
        assertTrue(linkText + " not stricken through..!", favouritesPage.favouriteStrickenThroughGroupLink(groupName, linkText).isDisplayed());
    }

    @When("^user adds practice area '(.*)' to favourite group '(.*)'$")
    public void userAddsPAToFavouriteGroup(String paLinkText, String groupName) throws Throwable {
        header.companyLogo().click();
        header.waitForPageToLoad();
        header.waitForPageToLoadAndJQueryProcessing();
        header.getElementByLinkText(paLinkText).click();
        categoryPage.addToFavourites(groupName);
    }

    @When("^user drags page '(.*)' down to page '(.*)'$")
    public void UserDragsPage01DownToPage02(String firstPage, String secondPage) throws Throwable {
        pageActions.dragAndDrop(favouritesPage.pageInFavourite(firstPage), favouritesPage.pageInFavourite(secondPage));
    }

    @When("^user drags group '(.*)' down to group '(.*)'$")
    public void UserDragsGroup01DownToGroup02(String firstGroup, String secondGroup) throws Throwable {
        pageActions.dragAndDrop(favouritesPage.favouriteGroup(firstGroup), favouritesPage.favouriteGroup(secondGroup));
        //   assertTrue(linkText + " not stricken through..!", favouritesPage.favouriteStrickenThroughGroupLink(groupName, linkText).isDisplayed());
    }


    @When("^the user should see the group '(.*)' comes first than group '(.*)'$")
    public void UserShouldSeeTheGroup01ComesFirstThanGroup02(String firstGroup, String secondGroup) throws Throwable {
        favouritesPage.waitForPageToLoad();
        favouritesPage.waitForElementVisible(favouritesPage.favouriteByGroup(firstGroup));
        assertTrue(firstGroup + " not visible as a first group..!",
                favouritesPage.favouriteGroupNames().get(0).getText().trim().contains(firstGroup));
        assertTrue(secondGroup + " not visible as a first group..!",
                favouritesPage.favouriteGroupNames().get(1).getText().trim().contains(secondGroup));

    }

    @Then("^the footer is displayed below the end of the document$")
    public void theFooterIsDisplayedBelowTheEndOfTheDocument() {
        boolean footerBelowDoc = khResourcePage.compareElementsLocationByHeight(standardDocumentPage.endOfDocument(), footer.footerWidget()) < 0;
        assertTrue("Footer is overlapping the document body", footerBelowDoc);
    }

    @When("^the user searches for term \"(.*)\"$")
    public void theUserRunsAFreeTextSearchForTheQuery(String query) throws Throwable {
        practicalLawUKCategoryPage.searchButton().isDisplayed();
        practicalLawUKCategoryPage.freeTextField().clear();
        practicalLawUKCategoryPage.freeTextField().sendKeys(query);
        if (commonMethods.waitForElementToBeVisible(practicalLawUKCategoryPage.suggestedByTerm(query.toUpperCase()), 1000) != null) {
            practicalLawUKCategoryPage.suggestedTerm(query.toUpperCase()).click();
        } else {
            practicalLawUKCategoryPage.freeTextField().sendKeys(Keys.ENTER);
        }
    }
}