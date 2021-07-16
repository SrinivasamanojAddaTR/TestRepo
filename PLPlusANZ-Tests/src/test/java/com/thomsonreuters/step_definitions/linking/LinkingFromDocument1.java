package com.thomsonreuters.step_definitions.linking;

import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.utils.Linking.WLAUPageUtils;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class LinkingFromDocument1 {

	private static final String WLAU_SIGN_ON= "Westlaw Australia Signon";
	private static final String PLAU_SIGN_ON = "Home | Practical Law";
	private static final int SINGLE = 1;

	private AssetPageUtils assetPageUtils = new AssetPageUtils();
    private WLAUPageUtils wlauPageUtils = new WLAUPageUtils();
    private HomePage homePage = new HomePage();
    private String firstDocUrl;

	
	@Then("^the target PLAU document is displayed in the same tab$")
	public void theTargetPLAUDocumentIsDisplayed() throws Throwable {
		assertFalse("The target PLAU document is not displayed in the same tab", firstDocUrl.contains(homePage.getCurrentUrl()));
	}

    @When("^the user clicks on hardcoded \"(.*?)\" link$")
    public void theUserClicksOnHardcodedLink(String linkText) throws Throwable {
		firstDocUrl = homePage.getCurrentUrl();
        assetPageUtils.clickOnHardcodedLink(linkText);
    }
	
	@Then("^the source document with guid \"(.*?)\" remains open and new tab opens$")
	public void theSourceDocumentRemainsOpen(String guid) throws Throwable {	
		assertTrue("The source document doesn't remain open", assetPageUtils.isTheSourceDocumentRemainsOpen(guid));
	}
	
	@Given("^the user is logged into Westlaw UK$")
	public void theUserIsLoggedIntoWLUK() throws Throwable {
		wlauPageUtils.navigateToWLUK();
	//	navigationCobalt.navigateToWestlawNext();
		assetPageUtils.loginWestlawUk();
	}
	
	//not specified user name and password TODO
	@Given("^the user is logged into WLAU$")
	public void theUserIsLoggedIntoWLAU() throws Throwable {
		wlauPageUtils.loginWLAU();		
	}

	//not verified TODO
	@When("^the user see opened document in WLAU$")
	public void theUserSeeOpenedDocumentInWLAU() throws Throwable {
		assertTrue("The user doesn't see opened document in WLAU", wlauPageUtils.theUserSeeOpenedDocumentInWLAU());
	}
	
	@Then("^the user is taken to the login page in (WLAU|PLAU)$")
	public void theUserIsTakenToTheLoginPageOfProduct(String product) throws Throwable {
        switch (product) {
            case "WLAU":
                product = WLAU_SIGN_ON;
                break;
            case "PLAU":
                product = PLAU_SIGN_ON;
                break;
        }
        assertTrue("Product: " + product + "; Title: " + homePage.getPageTitle(), homePage.getPageTitle().contains(product));
	}

}
