package com.thomsonreuters.pageobjects.utils.linking_utils;

import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.pages.wlau.WLAUDocumentPage;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;

public class WLAUPageUtils {

    private static final String WLUK_URL = "http://login.westlaw.co.uk";
    private static final String WLAU_URL = "http://westlaw.com.au";
    private static final String USER_NAME_WLAU = "WLAU_User";
    private static final String PASS_WLAU = "Epam2015";

    private WLAUDocumentPage wlauDocumentPage = new WLAUDocumentPage();
    private OnepassLoginUtils onepassLoginUtils = new OnepassLoginUtils();
    private OnepassLogin onepassLogin = new OnepassLogin();
    private AssetDocumentPage assetDocumentPage = new AssetDocumentPage();


    public void navigateToWLUK() {
        wlauDocumentPage.navigate(WLUK_URL);
    }

    public void loginWLAU() {
        wlauDocumentPage.navigate(WLAU_URL);
        onepassLogin.waitForPageToLoadAndJQueryProcessing();
        onepassLoginUtils.loginToCobalt(USER_NAME_WLAU, PASS_WLAU);
    }

    public boolean theUserSeeOpenedDocumentInWLAU() {
        Boolean isOpen = false;
        String winHandleFirst = assetDocumentPage.getCurrentWindowHandle();
        String secondHandle = "";
        for (String winHandle : assetDocumentPage.getDriver.getWindowHandles()) {
            assetDocumentPage.switchToWindow(winHandle);
            secondHandle = winHandle;
        }
        if (wlauDocumentPage.isPopUpInWLAUDisplayed()) {
            wlauDocumentPage.closePopUpButton().click();
        }
        if (wlauDocumentPage.documentBody().isDisplayed()) {
            wlauDocumentPage.signoutLink().click();
            assetDocumentPage.switchToWindow(secondHandle);
            assetDocumentPage.getDriver.close();
            assetDocumentPage.switchToWindow(winHandleFirst);
            isOpen = true;
        }
        return isOpen;
    }


}
