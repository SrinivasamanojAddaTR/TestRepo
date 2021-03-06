package com.thomsonreuters.step_definitions.header.responsive_design;

import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.one_pass.OnePassLogoutPage;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertTrue;

public class UsernameLinkTestSteps extends BaseStepDef {

    private WLNHeader header = new WLNHeader();
    private HomePage homePage = new HomePage();
    private OnepassLogin onePass = new OnepassLogin();
	private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();
    private FooterUtils footerUtils = new FooterUtils();

	@Then("^user should (|not )see user icon link$")
	public void userShouldSeeUserIconLink(String not) throws Throwable {
		if (not.isEmpty()) {
			assertTrue("User Icon link not displayed..!", header.usernameLink().isDisplayed());
		} else {
			try {
				Assert.assertFalse("User Icon link displayed..!", header.usernameLink().isDisplayed());
			} catch (NoSuchElementException | TimeoutException e) {
                LOG.info("Icon link was not found");
			}
		}
    }

    @Then("^user clicks on user icon$")
    public void userHoversOnUsernameLink() throws Throwable {
        WebElement element = header.usernameIcon();
        Actions action = new Actions(getDriver());
        action.moveToElement(element).build().perform();
    }

    @Then("^user should see the user popup$")
    public void userShouldSeeTheUserPopup() throws Throwable {
        assertTrue("Popup not displayed..!", header.userNamePopup().isDisplayed());
    }

    @Then("^user clicks on \"(.*)\" link$")
    public void userClicksOnLink(String linkText) throws Throwable {
        footerUtils.closeDisclaimerMessage();
        header.waitForPageToLoad();
        header.scrollIntoViewAndClick(header.getElementByLinkText(linkText));
        header.waitForPageToLoad();
    }

    @Then("^user should navigate to (.*)$")
    public void userShouldNavigatedToPage(String resultPage) throws Throwable {
        if (resultPage.equalsIgnoreCase("One Pass")) {
            assertTrue("Onepass page not displayed..!", onePass.manageOnepassTitle().isDisplayed());
            getDriver().navigate().back();
            assertTrue("Login page not displayed..!", header.companyLogo().isDisplayed());
        } else if (resultPage.equalsIgnoreCase("Session Summary")) {
            assertTrue("Sign off page not displayed..!", onePassLogoutPage.signOffPageSignOnButton().isDisplayed());
            resetCurrentUser();
        } else if (resultPage.equalsIgnoreCase("Email preferences")) {
            assertTrue("Sign off page not displayed..!", onePassLogoutPage.isTextPresent(header.emailPreferencesByHeading(), resultPage, 2000));
        }
    }

}