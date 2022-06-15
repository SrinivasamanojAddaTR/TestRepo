package com.thomsonreuters.step_definitions;

import com.thomsonreuters.pageobjects.common.BaseCommonLoginNavigation;
import com.thomsonreuters.pageobjects.utils.CobaltUser;
import com.thomsonreuters.pageobjects.utils.Product;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.List;

/**
 * Login and Navigation Steps.
 */
public class CommonLoginNaviagtionSteps extends BaseCommonLoginNavigation {

	@Given("^user is logged in to WLN$")
	public void wlnLogin() throws IOException{
		super.wlnLogin();
	}

	@Given("^WLN user is logged in with following details$")
	public void wlnLoginWithDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
		super.wlnLoginWithDetails(plPlusUserList);
	}

	@Given("^PLC user is logged in with following details$")
	public void plcLegacyLoginWithDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
		super.plcLegacyLoginWithDetails(plPlusUserList);
	}

	@Given("^PL\\+ user navigates to home page$")
	public void plUserNaviagatesToHomePage() {
		super.plUserNaviagatesToHomePage();
	}


	@Given("^PL\\+ ANZ user navigates to home page$")
	public void plAnzUserNaviagatesToHomePage() throws IOException {
		super.plAnzUserNaviagatesToHomePage();
	}

	@Given("^PL\\+ user navigates to login page$")
	public void plUserNavigatesToLoginPage() {
		super.plUserNavigatesToLoginPage("hotprod");
	}

	@Given("^PL\\+ user is not logged in$")
	public void plUserIsNotLoggedIn() {
		super.plUserIsNotLoggedIn();
	}

	@Given("^PL\\+ user is logged in$")
	public void plUserIsLoggedIn() throws IOException{
		super.plUserIsLoggedIn();
	}

	@Given("^PL\\+ user is logged in with following details after IP login$")
	public void ipUserIsLoggedIn(@Transpose List<CobaltUser> plPlusUserList) {
		super.ipUserIsLoggedIn(plPlusUserList);
	}

	@Given("^PL\\+ user is logged in with following details$")
	public void plUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
		super.plUserIsLoggedInWithFollowingDetails(plPlusUserList);
	}

	@Given("^the user logs in from the login page$")
	public void userLogsInFromLoginPage(@Transpose List<CobaltUser> plPlusUserList) {
		super.userLogsInFromLoginPage(plPlusUserList);
	}

	@Given("^the user enters his username and password on the login page$")
	public void userEntersUsernameAndPasswordOnLoginPage(@Transpose List<CobaltUser> plPlusUserList) {
		super.userEntersUsernameAndPasswordOnLoginPage(plPlusUserList);
	}

	@When("^user is navigated to routing$")
	public void userNavitedToRoutingPage() {
		super.userNavitedToRoutingPage();
	}

	@Given("^ANZ user is logged in$")
	public void anzUserIsLoggedIn() throws IOException {
        super.anzUserIsLoggedIn();
	}

	@Given("^ANZ user is not logged in$")
	public void anzUserIsNotLoggedIn() throws IOException {
		super.anzUserIsNotLoggedIn();
		resetCurrentUser();
	}

	@Given("^ANZ user is logged in with following details$")
	public void anzUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
		super.anzUserIsLoggedInWithFollowingDetails(plPlusUserList);
	}

    @When("^the user navigates to the main PLANZ page$")
    public void theUserNavigatesToTheMainPLANZPage() {
        navigationCobalt.navigateToPLANZPlus();
		wlnHeader.waitForPageToLoad();
		footerUtils.closeDisclaimerMessage();
		wlnHeader.closePrivacyNoticePopup();
		wlnHeader.waitForPageToLoad();
    }

	/**
	 * EDGE Case where the test has no option but to go to the routing page.
	 * This may be to turn off an IAC or something
	 *
	 * @param plPlusUserList
	 * @throws Throwable
	 */
	@Given("^PL\\+ user is logged in with routing details$")
	public void plUserIsLoggedInWithRoutingDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
		super.plUserIsLoggedInWithRoutingDetails(plPlusUserList);
	}

	@Given("^ANZ user is logged in with routing details$")
	public void anzUserIsLoggedInWithRoutingDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
		super.anzUserIsLoggedInWithRoutingDetails(plPlusUserList);
	}

	@Given("^ANZ user is applying routing without login$")
	public void anzUserIsApplyingRoutingWithoutLogin(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
		for(CobaltUser user : plPlusUserList) {
			user.setProduct(Product.ANZ);
		}
		plUserIsApplyingRoutingWithoutLogin(plPlusUserList);
	}

	@Given("^PL\\+ user is applying routing without login$")
	public void plUserIsApplyingRoutingWithoutLogin(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
		super.plUserIsApplyingRoutingWithoutLogin(plPlusUserList);
	}


	@When("^user relogs in$")
	public void userRelogsIn() throws IOException {
		super.userRelogsIn();
	}

	@When("^user logs in$")
	public void userLogsInWithUsername() {
		super.userLogsInWithUsername();
	}

	@Then("^user logs in back again from signOff page$")
	public void userLogsInBackAgainFromSignOffPage() {
		super.userLogsInBackAgainFromSignOffPage();
	}

	@Given("^PL\\+ user '(.*)' opens '(.*)' folder$")
	public void plUserLoginAndOpenFolder(String userName, String folderName) throws IOException {
		super.plUserLoginAndOpenFolder(userName, folderName);
	}

	@Given("^PL\\+ user '(.*)' navigates directly to document with guid '(.*)'$")
	public void plUserLoginAndNavigateToDoc(String userName, String docGuid) throws IOException {
		super.plUserLoginAndNavigateToDoc(userName, docGuid);
	}

	@Given("^PL\\+ user '(.*)' searches for '(.*)'$")
	public void plUserLoginAndSearch(String userName, String term) throws IOException {
		super.plUserLoginAndSearch(userName, term);
	}

	@Given("^user deletes all cookies$")
	public void userDeletesAllCookies() {
		try {
            wlnHeader.signOff();
		} catch (Exception e) {
			LOG.info("Could not sign off. Supposing that user was not logged in: " + e.getMessage());
		}
		getDriver().manage().deleteAllCookies();
		resetCurrentUser();
	}

}