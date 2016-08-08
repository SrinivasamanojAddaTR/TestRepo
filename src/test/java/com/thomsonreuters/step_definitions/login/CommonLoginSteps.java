package com.thomsonreuters.step_definitions.login;

import com.thomsonreuters.pageobjects.common.ExcelFileReader;
import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.onePass.OnePassLogoutPage;
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
    private WLNHeader wlnHeader = new WLNHeader();
    private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();
    private OnepassLogin onePassLogin = new OnepassLogin();
    private OnepassLoginUtils onePassLoginUtils = new OnepassLoginUtils();

    @When("^the user navigates to the main PLCUK page$")
    public void theUserNavigatesToTheMainPLCUKPage() throws Throwable {
        navigationCobalt.navigateToPLUKPlus();
        navigationCobalt.waitForPageToLoad();
    }

	@When("^the user navigates to the main PL ANZ page$")
	public void theUserNavigatesToTheMainPLANZPage() throws Throwable {
		navigationCobalt.navigateToPLANZPlus();
		navigationCobalt.waitForPageToLoad();
	}

    @When("^he is viewing a free document \"(.*?)\"$")
    public void heIsViewingAFreeDocument(String documentURL) throws Throwable {
        navigationCobalt.navigateToPLCUKPlusSpecificURL(documentURL);
        navigationCobalt.waitForPageToLoad();
    }

    @When("^he is viewing a free ANZ document \"(.*?)\"$")
    public void heIsViewingAFreeAnzDocument(String documentURL) throws Throwable {
        navigationCobalt.navigateToPLCANZSpecificURL(documentURL);
        navigationCobalt.waitForPageToLoad();
    }

    @After(order = 40000, value = "@RemoveSRMOptionANZ")
    public void removeSRMOptionANZ() throws IOException, InterruptedException {
        navigationCobalt.navigateToPLANZPlus();
        navigationCobalt.waitForPageToLoad();
        removeSRMOption();
    }
    
    @After(order = 40000, value = "@RemoveSRMOptionUK")
    public void removeSRMOptionUK() throws IOException, InterruptedException {
        navigationCobalt.navigateToPLUKPlus();
        navigationCobalt.waitForPageToLoad();
        removeSRMOption();
    }
    
    private void removeSRMOption() throws IOException, InterruptedException {
        //TODO: this is a workaround to click sign off twice before 'Return to Sign In' Link appears it is by design at the moment
        //confirmed by Robert Foster and Jacqueline Auma
        wlnHeader.signOff();
        onePassLogin.waitForPageToLoad();
        if (!onePassLoginUtils.isResumeAsCurrentUserLinkPresent()) {
        	onePassLogoutPage.signOffPageSignOnButton().click();
            practicalLawHomepage.waitForPageToLoad();
            wlnHeader.signOff();
        }
        onePassLogoutPage.signInWithDifferentAccountLink().click();
        currentUser = new CobaltUser();
    }
    
    

    @When("^a PPI user enter its username and password$")
    public void aPPIUserEnterItsUsernameAndPassword(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        CobaltUser plPlusUser = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        onePassLoginUtils.enterUserNameAndPassword(plPlusUser.getUserName(), ExcelFileReader.getCobaltPassword(plPlusUser.getUserName()));
        currentUser = plPlusUser;
    }

    @When("^clicks on Sign in$")
    public void clicksOnSignIn() throws Throwable {
        onePassLogin.signOnButton().click();
        practicalLawHomepage.waitForPageToLoadAndJQueryProcessing();
    }

}