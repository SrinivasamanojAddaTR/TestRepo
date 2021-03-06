package com.thomsonreuters.step_definitions.breadcrumb;

import com.thomsonreuters.pageobjects.pages.contactexpress.ContractExpressHeader;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.utils.TimeoutUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class cEHomePageTestSteps extends BaseStepDef {
    private ContractExpressHeader header = new ContractExpressHeader();
    private HomePage homePage = new HomePage();


    @Then("^the User verifies the \"([^\"]*)\" title$")
    public void theUserVerifiesTheTitle(String title) throws Throwable {
        String headerTitle = header.contractExpressTitle().getText();
        System.out.println("Header is" + headerTitle);
        assertTrue("Title are not equal", headerTitle.contains(title));

    }

    @Then("^user verifies the following CE links are displayed$")
    public void userVerifiesTheFollowingCELinksAreDisplayed(List<String> linksText) throws Throwable {
        for (String linkText : linksText) {
            assertTrue(linkText + " is not visible..!", homePage.waitForExpectedElement(By.linkText(linkText)).isDisplayed());
        }
    }

    @When("^the help icon should be displayed$")
    public void theUserVerifiesTheHelpIcon() throws Throwable {
        assertTrue("Help icon is not displayed", header.ishelpiconPresent());
    }

    @When("^the user dropdown should be displayed$")
    public void theUserDropDownisDisplayed() throws Throwable {
        assertTrue("User DropDown is not displayed", header.isuserDropdownPresent());
    }

    @Then("^Show welcome screen when I log in checkbox should be displayed$")
    public void showWelcomeCheckboxisDisplayed() throws Throwable {
        assertTrue("User DropDown is not displayed", header.isWelcomeCheckboxPresent());
    }

    @When("^the user clicks the link \"([^\"]*)\" in footer$")
    public void theUserClicksOnFooter(String link) throws Throwable {
        header.userNoticeAndPrivacyStatement().isDisplayed();
        System.out.println("footer link is displayed");
        TimeoutUtils.sleepInSeconds(1);
        header.userNoticeAndPrivacyStatement().sendKeys(Keys.ENTER);
        System.out.println("footer link is clicked");
    }

    @Then("^user is taken to \"(.*?)\" Page$")
    public void verifyNavigatedPage(String title) throws Throwable {
        assertTrue("Not traversed to requested page", header.cEHeaderTitle().getText().trim().contains(title));
    }

    @When("^user waits until Address Page is displayed")
    public void waitUntilAddressDisplayed(){
        header.waitUntilAddressIconDisplayed();
    }
}