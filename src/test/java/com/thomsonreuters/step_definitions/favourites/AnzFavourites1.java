package com.thomsonreuters.step_definitions.favourites;

import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.pages.folders.FavouritesPage;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.step_definitions.ResponsiveCommonSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import static org.junit.Assert.assertTrue;

public class AnzFavourites1 extends BaseStepDef {

    private FavouritesPage favouritesPage = new FavouritesPage();
    private PageActions pageActions = new PageActions();
	private ResponsiveCommonSteps responsiveCommonSteps = new ResponsiveCommonSteps();
	private WLNHeader wlnHeader = new WLNHeader();
    private CategoryPage categoryPage = new CategoryPage();
    private HomePage homepage = new HomePage();
	
    @When("^user makes practice area page '(.*)' as start page$")
    public void userAddsPAToFavouriteGroup(String paLinkText) throws Throwable {
    	wlnHeader.companyLogo().click();
		categoryPage.waitForPageToLoad();
		categoryPage.waitForPageToLoadAndJQueryProcessing();
        categoryPage.getElementByLinkText(paLinkText).click();
        categoryPage.makeThisMyStartPage();
    }
	
	@When("^the user should see the page '(.*)' comes first than page '(.*)'$")
    public void UserShouldSeeThePage01ComesFirstThanPage02(String firsPage, String secondPage) throws Throwable {
        favouritesPage.waitForPageToLoadAndJQueryProcessing();
		favouritesPage.refreshPage();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(favouritesPage.pagesInFavouriteFroup().get(0).getText().trim().contains(firsPage)).overridingErrorMessage("Expected first page is: <%s> but actual: <%s>", firsPage, favouritesPage.pagesInFavouriteFroup().get(0).getText()).isTrue();
        softly.assertThat(favouritesPage.pagesInFavouriteFroup().get(1).getText().trim().contains(secondPage)).overridingErrorMessage("Expected second page is: <%s> but actual: <%s>", secondPage, favouritesPage.pagesInFavouriteFroup().get(1).getText()).isTrue();
		softly.assertAll();

    }
	
	@When("^the user deletes the start page from favourites$")
    public void deleteFavoritePage() throws Throwable {
		wlnHeader.favouritesLink().click();
		favouritesPage.organize().click();
        pageActions.mouseOver(favouritesPage.myStartPageLink());
        favouritesPage.myStartPageDeleteLink().click();
        favouritesPage.doneOrganizing().click();
    }
	
	@Then("the user should see \"(.*?)\" link appears first in Frequently Used Items in Favourites$")
	public void UserShouldSeeTheFrequentlyUsedItemsLinkAppearsFirst(String pageLink) throws Throwable {
		responsiveCommonSteps.theUserClicksOnLink("Favourites");
		assertTrue(pageLink + " not displayed firts..!", favouritesPage.frequentlyUsedItemsLinks().get(0).getText().trim().contains(pageLink));
	}
	
	@Then("^user navigates to Resource \"(.*)\" on resource page$")
	public void userNavigatesToResourceOnResourcePage(String resourcePage) throws Throwable {
		 homepage.selectResourceTab();
		 homepage.selectLinkPresentOnTab(resourcePage);
		 homepage.waitForPageToLoadAndJQueryProcessing();

	}
	
	@When("^the user selects country \"(.*)\" on international tab$")
    public void theUserSelectsCountryLinkOnInternationalTab(String countryName) throws Throwable {
		homepage.selectInternationalTab();
		homepage.selectLinkPresentOnTab(countryName);
		homepage.waitForPageToLoadAndJQueryProcessing();
    }

}
