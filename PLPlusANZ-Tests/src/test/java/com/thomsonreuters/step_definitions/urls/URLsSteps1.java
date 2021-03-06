package com.thomsonreuters.step_definitions.urls;

import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.step_definitions.header.responsive_design.UsernameLinkTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class URLsSteps1 extends BaseStepDef {

	private NavigationCobalt navigationCobalt = new NavigationCobalt();
	private HomePage page = new HomePage();
	private CategoryPage categoryPage = new CategoryPage();
	private UsernameLinkTestSteps usernameLinkTest = new UsernameLinkTestSteps();

	@When("^the user opens \"(.*?)\" url on PL AU website$")
	public void theUserOpensUrlOnPLAUSite(String url) throws Throwable {
		navigationCobalt.navigateToPLCANZSpecificURL(url);
		categoryPage.waitForPageToLoad();
	}

	@Then("^Add Page to Favorites icon is displayed$")
	public void addToFavoritesIconisDisplayed() throws Throwable {
		Assert.assertTrue("Add to Favorites Icon is not displayed", categoryPage.isElementDisplayed(categoryPage.addToFavoritesLink()));

	}
	
	@Then("^the page URL contains \"(.*?)\"$")
	public void thePageUrlContains(String url) throws Throwable {
		Assert.assertTrue(String.format("Page URL '%s' does not contain expected text '%s'", page.getCurrentUrl(), url), page
				.getCurrentUrl().toLowerCase().contains(url.toLowerCase()));

	}

	@Then("^the user is presented with several practice areas options$")
	public void theUserIsPresentedWithSeveralPracticeAreasOptions() {
		String title = page.getPageHeaderLabel().getText();
		String regex = "Share acquisitions: private";
		Assert.assertTrue(String.format("Page title '%s' does not match expected regex '%s'", title, regex), title.matches(regex));
	}

	@Then("^the user is presented with a page with header \"(.*?)\"$")
	public void theUserIsPresentedWithPageWithhHeader(String header) {
		Assert.assertTrue(
				String.format("Page header '%s' does not contain expected text '%s'", page.getPageHeaderLabel().getText(), header), page
						.getPageHeaderLabel().getText().toLowerCase().contains(header.toLowerCase()));
	}

	@Then("^the user is presented with comparison tool page with header \"(.*?)\"$")
	public void theUserIsPresentedWithComparisonToolPageWithhHeader(String header) {
		Assert.assertTrue(
				String.format("Page header '%s' does not contain expected text '%s'", page.getTitle().getText(), header),
				page.getTitle().getText().toLowerCase().contains(header.toLowerCase()));
	}

	@Then("^user should see Tabs and corresponding urls$")
	public void userShouldSeeTabsAndCorrespondingUrls(DataTable dataTable) throws Throwable {
		Map<String, String> footerColumnLinks = dataTable.asMap(String.class, String.class);
		for(Map.Entry<String, String> entry : footerColumnLinks.entrySet()){
			usernameLinkTest.userClicksOnLink(entry.getKey());
			thePageUrlContains(entry.getValue());
		}
	}

	@When("^the user clicks on the browser back button$")
	public void clicksOnBrowserBackButton() {
			    page.browserGoBack();
	}
}
