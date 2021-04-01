package com.thomsonreuters.step_definitions.uk.folders;

import com.thomsonreuters.pageobjects.common.BaseCommonLoginNavigation;
import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.folders.CreateGroupPopup;
import com.thomsonreuters.pageobjects.pages.folders.FavouritesPage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.pages.widgets.WLNFavoritesWidget;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbilityNotToSeeFauvoritesFromOthersSystems1 extends BaseStepDef {

    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private CategoryPage categoryPage = new CategoryPage();
    private WLNFavoritesWidget wlnFavoritesWidget = new WLNFavoritesWidget();
    private FavouritesPage favouritesPage = new FavouritesPage();
    private BaseCommonLoginNavigation baseCommonLoginNavigation = new BaseCommonLoginNavigation();
    private CreateGroupPopup createGroupPopup = new CreateGroupPopup();
    private PageActions pageActions = new PageActions();

    @When("^the user come back on to WLN Home page$")
    public void userComeBackOnToWLNHomePage() throws Throwable {
        categoryPage.waitForPageToLoad();
        if (!baseCommonLoginNavigation.isHomePage()) {
            navigationCobalt.navigateToWLNHomePage();
        }
    }

    @When("^the user come back on to Home page$")
    public void userComeBackOnToHomePage() throws Throwable {
        categoryPage.waitForPageToLoad();
        if (!baseCommonLoginNavigation.isHomePage()) {
            navigationCobalt.navigateToHomePage();
            categoryPage.waitForPageToLoad();
        }
    }

    @When("^the user opens WLN '(.+)' link$")
    public void goLink(String linkName) throws Throwable {
        categoryPage.openPageByText(linkName);
    }

    @When("^the user adds page to favourites group '(.+)'$")
    public void addPageToGroup(String groupName) throws Throwable {
        categoryPage.addToFavourites(groupName);
    }

    @When("^the user makes page as My Start Page$")
    public void addPageToGroup() throws Throwable {
        categoryPage.makeThisMyStartPage();
    }
    
    @Then("^the user clicks the Start Page to remove it from Start Page$")
    public void userClicksTheHomeIcontoRemoveItasStartPage() throws Throwable {
        categoryPage.removeThisAsMyStartPage();
    }

    @When("^the user creates new favourites group '(.+)'$")
    public void createNewFavoriteGroup(String groupName) throws Throwable {
    	favouritesPage.waitForPageToLoad();
        favouritesPage.addGroupLink().click();
        favouritesPage.waitForPageToLoadAndJQueryProcessing();
        favouritesPage.waitForPageToLoad();
        createGroupPopup.groupName().sendKeys(groupName);
        createGroupPopup.save().click();
        createGroupPopup.waitForPageToLoad();
    }

    @Then("^the favourites group '(.+)' presents on Favourites page$")
    public void checkFavoriteGroupPresents(String groupName) throws Throwable {
        favouritesPage.favouriteGroup(groupName);
    }

    @Then("^the favourites group '(.+)' is absent on Favourites page$")
    public void checkFavoriteGroupAbsent(String groupName) throws Throwable {
        favouritesPage.waitForPageToLoadAndJQueryProcessing();
        favouritesPage.waitFavouriteGroupIsAbsent(groupName);
    }

    @When("^the user renames the favourites group '(.+)' to '(.+)'$")
    public void renameFavoriteGroup(String oldName, String newName) throws Throwable {
        favouritesPage.organize().click();
        pageActions.mouseOver(favouritesPage.favouriteGroup(oldName));
        favouritesPage.renameFavouriteGroupButton(oldName).click();
        favouritesPage.renameFavouriteGroupInput(oldName).clear();
        favouritesPage.renameFavouriteGroupInput(oldName).sendKeys(newName);
        favouritesPage.renameFavouriteOKGroupButton(oldName).click();
        favouritesPage.doneOrganizing().click();
    }

    @When("^the user deletes the favourites group '(.+)'$")
    public void deleteFavoriteGroup(String groupName) throws Throwable {
        favouritesPage.organize().click();
        pageActions.mouseOver(favouritesPage.favouriteGroup(groupName));
        favouritesPage.waitForPageToLoad();
        favouritesPage.deleteFavouriteGroupButton(groupName).click();
        favouritesPage.doneOrganizing().click();
    }

    @When("^the user deletes the favourites page '(.+)'$")
    public void deleteFavoritePage(String pageName) throws Throwable {
		favouritesPage.organize().click();
        pageActions.mouseOver(favouritesPage.pageInFavourite(pageName));
        favouritesPage.deletePageFromFavourite(pageName).click();
        favouritesPage.doneOrganizing().click();
    }

    @Then("^the user checks that '(.+)' link is not saved to Favourites$")
    public void checkPageIsNotInFavourites(String linkName) throws Throwable {
        categoryPage.openPageByText(linkName);
        categoryPage.checkPageIsNotInFavourites();
    }

    @Then("^the user checks that WLN '(.+)' link is not in favorites group '(.+)' on Favorites widget$")
    public void checkPageIsNotInFavoritesOnFavoritesWidget(String linkName, String groupName) throws Throwable {
        wlnFavoritesWidget.checkCategoryPageAbsent(linkName, groupName);
    }

    @Then("^the user checks that '(.+)' link presents in favourites group '(.+)' on Favourites page$")
    public void checkPagePresentsInFavouritesOnFavouritesPage(String linkName, String groupName) throws Throwable {
        favouritesPage.waitForPageToLoad();
        assertFalse(favouritesPage.checkCategoryPageIsAbsent(linkName, groupName));
    }

    @When("^the user clicks '(.+)' link on Favourites page$")
    public void openLinkOnFavouritesPage(String pageName) throws Throwable {
        favouritesPage.openPage(pageName).click();
        favouritesPage.waitForPageToLoad();
    }

    @Then("page '(.+)' opens$")
    public void checkPageOpens(String pageName) throws Throwable {
        categoryPage.checkPageOpens(pageName);
    }

    @Then("^the user checks the start page is Home page$")
    public void checkHomePage() throws Throwable {
        if (!baseCommonLoginNavigation.isHomePage()) {
            throw new RuntimeException("Start page is not Home Page");
        }
    }

    @Then("^the user checks that '(.+)' link is not in favourites group '(.+)' on Favourites page$")
    public void checkPageIsNotInFavouritesOnFavouritesPage(String linkName, String groupName) throws Throwable {
        favouritesPage.waitForPageToLoad();
        assertTrue(favouritesPage.checkCategoryPageIsAbsent(linkName, groupName));
    }

    @When("^the user opens '(.+)' link$")
    public void openLink(String linkName) throws Throwable {
        categoryPage.openPageByText(linkName);
    }

    @When("^the user opens \"([^\"]*)\" link$")
    public void openLinkFromList(String linkName) throws Throwable {
        categoryPage.openPageByText(linkName);
    }

    @When("^the user opens '(.+)' Tab$")
    public void openTab(String linkName) throws Throwable {
        categoryPage.openTab(linkName);
    }
}