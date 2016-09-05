package com.thomsonreuters.step_definitions;

import com.thomsonreuters.pageobjects.common.BaseCommonLoginNavigation;
import com.thomsonreuters.pageobjects.utils.CobaltUser;
import com.thomsonreuters.pageobjects.utils.Product;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Login and Navigation Steps.
 */
public class CommonLoginNaviagtionSteps extends BaseCommonLoginNavigation {

	@Given("^user is logged in to WLN$")
	public void WLNLogin() throws Throwable {
		super.WLNLogin();
	}

	@Given("^WLN user is logged in with following details$")
	public void WLNLoginWithDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		super.WLNLoginWithDetails(plPlusUserList);
	}

	@Given("^PLC user is logged in with following details$")
	public void PLCLegacyLoginWithDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		super.PLCLegacyLoginWithDetails(plPlusUserList);
	}

	@Given("^PL\\+ user navigates to home page$")
	public void plUserNaviagatesToHomePage() throws Throwable {
		super.plUserNaviagatesToHomePage();
	}


	@Given("^PL\\+ ANZ user navigates to home page$")
	public void plAnzUserNaviagatesToHomePage() throws Throwable {
		super.plAnzUserNaviagatesToHomePage();
	}

	@Given("^PL\\+ user navigates to login page$")
	public void plUserNavigatesToLoginPage() throws Throwable {
		super.plUserNavigatesToLoginPage("hotprod");
	}

	@Given("^PL\\+ user is not logged in$")
	public void plUserIsNotLoggedIn() throws Throwable {
		super.plUserIsNotLoggedIn();
	}

	@Given("^PL\\+ user is logged in$")
	public void plUserIsLoggedIn() throws Throwable {
		super.plUserIsLoggedIn();
	}

	@Given("^PL\\+ user is logged in with following details after IP login$")
	public void ipUserIsLoggedIn(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		super.ipUserIsLoggedIn(plPlusUserList);
	}

	@Given("^PL\\+ user is logged in with following details$")
	public void plUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		super.plUserIsLoggedInWithFollowingDetails(plPlusUserList);
	}

	@Given("^the user logs in from the login page$")
	public void userLogsInFromLoginPage(@Transpose List<CobaltUser> plPlusUserList)
			throws InterruptedException, IOException {
		super.userLogsInFromLoginPage(plPlusUserList);
	}

	@Given("^the user enters his username and password on the login page$")
	public void userEntersUsernameAndPasswordOnLoginPage(@Transpose List<CobaltUser> plPlusUserList)
			throws InterruptedException, IOException {
		super.userEntersUsernameAndPasswordOnLoginPage(plPlusUserList);
	}

	@When("^user is navigated to routing$")
	public void userNavitedToRoutingPage() {
		super.userNavitedToRoutingPage();
	}

	@Given("^ANZ user is logged in$")
	public void anzUserIsLoggedIn() throws Throwable {
        List<CobaltUser> plPlusUserList = new ArrayList<>();
        plPlusUserList.add(new CobaltUser());
        super.anzUserIsLoggedInWithFollowingDetails(plPlusUserList);
	}

	@Given("^ANZ user is not logged in$")
	public void anzUserIsNotLoggedIn() throws Throwable {
		super.anzUserIsNotLoggedIn();
		resetCurrentUser();
	}

	@Given("^ANZ user is logged in with following details$")
	public void anzUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		super.anzUserIsLoggedInWithFollowingDetails(plPlusUserList);
	}

    @When("^the user navigates to the main PLANZ page$")
    public void theUserNavigatesToTheMainPLANZPage() throws Throwable {
        navigationCobalt.navigateToPLANZPlus();
        navigationCobalt.waitForPageToLoad();
    }

	/**
	 * EDGE Case where the test has no option but to go to the routing page.
	 * This may be to turn off an IAC or something
	 *
	 * @param plPlusUserList
	 * @throws Throwable
	 */
	@Given("^PL\\+ user is logged in with routing details$")
	public void plUserIsLoggedInWithRoutingDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		super.plUserIsLoggedInWithRoutingDetails(plPlusUserList);
	}

	@Given("^ANZ user is logged in with routing details$")
	public void anzUserIsLoggedInWithRoutingDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		super.anzUserIsLoggedInWithRoutingDetails(plPlusUserList);
	}

	@Given("^ANZ user is applying routing without login$")
	public void anzUserIsApplyingRoutingWithoutLogin(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		for(CobaltUser user : plPlusUserList) {
			user.setProduct(Product.ANZ);
		}
		plUserIsApplyingRoutingWithoutLogin(plPlusUserList);
	}

	@Given("^PL\\+ user is applying routing without login$")
	public void plUserIsApplyingRoutingWithoutLogin(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
		super.plUserIsApplyingRoutingWithoutLogin(plPlusUserList);
	}


	@When("^user relogs in$")
	public void userRelogsIn() throws Throwable {
		super.userRelogsIn();
	}

	@When("^user logs in$")
	public void userLogsInWithUsername() throws Throwable {
		super.userLogsInWithUsername();
	}

	@Then("^user logs in back again from signOff page$")
	public void userLogsInBackAgainFromSignOffPage() throws Throwable {
		super.userLogsInBackAgainFromSignOffPage();
	}

	@Given("^PL\\+ user '(.*)' opens '(.*)' folder$")
	public void plUserLoginAndOpenFolder(String userName, String folderName) throws Throwable {
		super.plUserLoginAndOpenFolder(userName, folderName);
	}

	@Given("^PL\\+ user '(.*)' navigates directly to document with guid '(.*)'$")
	public void plUserLoginAndNavigateToDoc(String userName, String docGuid) throws Throwable {
		super.plUserLoginAndNavigateToDoc(userName, docGuid);
	}

	@Given("^PL\\+ user '(.*)' searches for '(.*)'$")
	public void plUserLoginAndSearch(String userName, String term) throws Throwable {
		super.plUserLoginAndSearch(userName, term);
	}

	@Given("^user deletes all cookies$")
	public void userDeletesAllCookies() throws Throwable {
		try {
            wlnHeader.signOff();
		} catch (Exception e) {
			LOG.info("Could not sign off. Supposing that user was not logged in: " + e.getMessage());
		}
		getDriver().manage().deleteAllCookies();
		resetCurrentUser();
	}
}