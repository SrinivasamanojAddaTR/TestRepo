package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.common.ExcelFileReader;
import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.landing_page.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.utils.CobaltUser;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.Transpose;
import cucumber.api.java.After;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.List;

public class CommonLoginSteps extends BaseStepDef {

    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private OnepassLogin onePassLogin = new OnepassLogin();
    private OnepassLoginUtils onePassLoginUtils = new OnepassLoginUtils();

    @When("^the user navigates to the main PLCUK page$")
    public void theUserNavigatesToTheMainPLCUKPage() throws Throwable {
        navigationCobalt.navigateToPLUKPlus();
        practicalLawHomepage.waitForPageToLoad();
    }

	@When("^the user navigates to the main PL ANZ page$")
	public void theUserNavigatesToTheMainPLANZPage() throws Throwable {
		navigationCobalt.navigateToPLANZPlus();
        practicalLawHomepage.waitForPageToLoad();
	}

    @When("^he is viewing a free document \"(.*?)\"$")
    public void heIsViewingAFreeDocument(String documentURL) throws Throwable {
        navigationCobalt.navigateToPLUKPlus(documentURL);
        practicalLawHomepage.waitForPageToLoad();
    }

    @When("^he is viewing a free ANZ document \"(.*?)\"$")
    public void heIsViewingAFreeAnzDocument(String documentURL) throws Throwable {
        navigationCobalt.navigateToPLCANZSpecificURL(documentURL);
        practicalLawHomepage.waitForPageToLoad();
    }

    @After(order = 40000, value = "@RemoveSRMOptionANZ")
    public void removeSRMOptionANZ() throws IOException, InterruptedException {
        navigationCobalt.navigateToPLANZPlus();
        practicalLawHomepage.waitForPageToLoad();
        currentUser.setCurrentUser(onePassLoginUtils.removeSRMOption());
    }
    
    @After(order = 40000, value = "@RemoveSRMOptionUK")
    public void removeSRMOptionUK() throws IOException, InterruptedException {
        navigationCobalt.navigateToPLUKPlus();
        practicalLawHomepage.waitForPageToLoad();
        currentUser.setCurrentUser(onePassLoginUtils.removeSRMOption());
    }

    @When("^a PPI user enter its username and password$")
    public void aPPIUserEnterItsUsernameAndPassword(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        CobaltUser plPlusUser = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        onePassLoginUtils.enterUserNameAndPassword(plPlusUser.getUserName(), ExcelFileReader.getCobaltPassword(plPlusUser.getUserName()));
        currentUser.setCurrentUser(plPlusUser);
    }

    @When("^clicks on Sign in$")
    public void clicksOnSignIn() throws Throwable {
        onePassLogin.signOnButton().click();
        practicalLawHomepage.waitForPageToLoadAndJQueryProcessing();
    }

}