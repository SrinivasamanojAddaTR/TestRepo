package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;

public class HideAddToFavouritesForNotLoggedInUser1 extends BaseStepDef {

    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private CategoryPage categoryPage = new CategoryPage();

    @When("^he navigate to a page \"(.*?)\" eligible to be selected as Favourite$")
    public void heNavigateToAPageEligibleToBeSelectedAsFavourite(String url) throws Throwable {
        navigationCobalt.navigateToPLUKPlus(url);
        categoryPage.waitForPageToLoad();
    }

    @Then("^he does not see any favourites icon/link$")
    public void heDoesNotSeeAnyFavouritesIconLink() throws Throwable {
        assertFalse("Add to Favorites is visible for user", categoryPage.addToFavouritesLinkPresent());
    }

    @Then("^he is not able to add it to Favourites$")
    public void heIsNotAbleToAddItToFavourites() throws Throwable {
        boolean isPageAddedToFavorites = true;
        try {
            categoryPage.addToFavoritesLink().click();
        } catch (NullPointerException npe) {
            LOG.info("User is not able to add page to favorites");
            isPageAddedToFavorites = false;
        }
        assertFalse("User is able to add page to favorites", isPageAddedToFavorites);
    }

}