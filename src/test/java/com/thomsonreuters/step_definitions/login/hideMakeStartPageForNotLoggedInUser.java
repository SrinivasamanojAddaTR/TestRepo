package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;

public class hideMakeStartPageForNotLoggedInUser extends BaseStepDef {

    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private CategoryPage categoryPage = new CategoryPage();

    @When("^he navigates to a page \"(.*?)\" eligible to be selected as Start Page$")
    public void heNavigatesToAPageEligibleToBeSelectedAsStartPage(String url) throws Throwable {
        navigationCobalt.navigateToPLUKPlus(url);
        categoryPage.waitForPageToLoad();
    }

    @Then("^he does not see any Start Page icon/link$")
    public void heDoesNotSeeAnyStartPageIconLink() throws Throwable {
        assertFalse("Make this my start page link is visible for user", categoryPage.makeThisMyStartPageLinkPresent());
    }

    @Then("^he is not able to select the page as start page$")
    public void heIsNotAbleToSelectThePageAsStartPage() throws Throwable {
        boolean isPageSelectedAsStartPage = true;
        try {
            categoryPage.addToFavoritesLink().click();
        } catch (NullPointerException npe) {
            LOG.info("User is not able to select page as start page");
            isPageSelectedAsStartPage = false;
        }
        assertFalse("User is able select page as start page", isPageSelectedAsStartPage);
    }

}