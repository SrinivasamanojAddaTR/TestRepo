package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.pageobjects.pages.login.WelcomePage;
import com.thomsonreuters.pageobjects.pages.onePass.OnePassLogoutPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.KHResourcePage;
import com.thomsonreuters.pageobjects.pages.plcLegacy.PLCLegacyHeader;
import com.thomsonreuters.pageobjects.pages.plcLegacy.PLCLegacyLoginScreen;
import com.thomsonreuters.pageobjects.pages.search.SearchHomePage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.*;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.globalPage.GlobalPageUtils;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
import com.thomsonreuters.pageobjects.utils.sitestructure.SiteStructureUtils;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static com.thomsonreuters.pageobjects.utils.CobaltUser.isUserFirstUser;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Login and Navigation Steps.
 */
public class BaseCommonLoginNavigation extends BaseStepDef {

    public static final String ROUTING = "routing";

    private static final String DEFAULT_PROD_KEYWORD = "prod";

    private static final boolean DEFAULT_HISTORY_LINK_VERIFICATION_STATE = false;
    private static final boolean DEFAULT_IS_CREATE_NEW_SESSION_STATE = false;

    private static final boolean DEFAULT_IS_VERIFY_USER_LOGGED_FOR_SKIP_LOGIN_CASE_STATE = false;

    private static final String ADDITIONAL_IAC_FAC_PROP_FILE_NAME = "additionalIacAndFac.properties";
    private static final String USERNAME = "username";
    private static final String GRANT_ACCESS_OPTION_TEXT = "Grant";

    private RoutingPage routingPage;
    protected NavigationCobalt navigationCobalt;
    private OnepassLogin onepassLogin;
    private OnepassLoginUtils onepassLoginUtils;
    private WelcomePage welcome;
    private PracticalLawHomepage plcHomePage;
    protected WLNHeader wlnHeader;
    private CommonMethods comMethods;
    private PLCLegacyHeader plcLegacyHeader;
    private PLCLegacyLoginScreen plcLegacyLoginScreen;
    private OnePassLogoutPage onePassLogoutPage;
    private ResearchOrganizerPage researchOrganizerPage;
    private FoldersUtils foldersUtils;
    private SearchHomePage searchHomePage;
    private KHResourcePage resourcePage;
    private CategoryPage categoryPage;
    private GlobalPageUtils globalPageUtils;
    private SiteStructureUtils siteStructureUtils;
    private FooterUtils footerUtils;
    private SearchUtils searchUtils;

    public BaseCommonLoginNavigation() {
        routingPage = new RoutingPage();
        onepassLoginUtils = new OnepassLoginUtils();
        comMethods = new CommonMethods();
        wlnHeader = new WLNHeader();
        navigationCobalt = new NavigationCobalt();
        plcHomePage = new PracticalLawHomepage();
        onepassLogin = new OnepassLogin();
        welcome = new WelcomePage();
        plcLegacyHeader = new PLCLegacyHeader();
        plcLegacyLoginScreen = new PLCLegacyLoginScreen();
        onePassLogoutPage = new OnePassLogoutPage();
        researchOrganizerPage = new ResearchOrganizerPage();
        foldersUtils = new FoldersUtils();
        searchHomePage = new SearchHomePage();
        resourcePage = new KHResourcePage();
        categoryPage = new CategoryPage();
        globalPageUtils = new GlobalPageUtils();
        siteStructureUtils = new SiteStructureUtils();
        footerUtils = new FooterUtils();
        searchUtils = new SearchUtils();
    }

    public void WLNLogin() throws Throwable {
        CobaltUser user = new CobaltUser();
        user.setProduct(Product.WLN);
        loginUser(user);
        LOG.info("The user has logged in to WLN");
    }

    public void WLNLoginWithDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        CobaltUser user = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        user.setProduct(Product.WLN);
        loginUser(user);
        LOG.info("The WLN user is logged in with the following details");
    }

    public void PLCLegacyLoginWithDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        CobaltUser user = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        user.setProduct(Product.PLC_lEGACY);
        loginUser(user);
        LOG.info("The PLC user is logged in with the following details");
    }

    public void plUserNaviagatesToHomePage() throws Throwable {
        onepassLogin.deleteAllCookies();
        navigationCobalt.navigateToPLUKPlus();
        plcHomePage.waitForPageToLoadAndJQueryProcessing();
        plcHomePage.closeCookieConsentMessage();
        resetCurrentUser();
        LOG.info("The PL+ user has navigated to the home page");
    }

    public void theUserClicksOnSignOnLinkOnTheHeader() throws Throwable {
        wlnHeader.signInLink().click();
        onepassLogin.waitForPageToLoad();
        LOG.info("The user has clicked on the SignOn link on the header");
    }

    public void plAnzUserNaviagatesToHomePage() throws Throwable {
        getDriver().manage().deleteAllCookies();
        navigationCobalt.navigateToPLANZPlus();
        resetCurrentUser();
        footerUtils.closeDisclaimerMessage();
        footerUtils.ourCookiesPolicy();
        LOG.info("The PL+ ANZ user has navigated to the home page");
    }

    public void plUserNavigatesToLoginPage() throws Throwable {
        plUserNavigatesToLoginPage(DEFAULT_PROD_KEYWORD);
    }

    public void plUserNavigatesToLoginPage(String prodKeyword) throws Throwable {
        onepassLogin.deleteAllCookies();
        navigationCobalt.navigateToPLUKPlus();
        plcHomePage.closeCookieConsentMessage();
        resetCurrentUser();
        if (!baseUrl.contains(prodKeyword)) {
            theUserClicksOnSignOnLinkOnTheHeader();
            LOG.info("The PL+ user has navigated to the login page");
        } else {
            LOG.info("OpenWeb is OFF on production. User already on login page");
        }
    }

    public void plUserIsNotLoggedIn() throws Throwable {
        plUserIsNotLoggedIn(DEFAULT_IS_CREATE_NEW_SESSION_STATE);
    }

    public void plUserIsNotLoggedIn(boolean isCreateNewSessionImmediately) throws Throwable {
        if (isCreateNewSessionImmediately || !isUserFirstUser(currentUser)) {
            newSession(currentUser);
            navigationCobalt.navigateToPLUKPlus();
            plcHomePage.waitForPageToLoadAndJQueryProcessing();
            plcHomePage.closeCookieConsentMessage();
        } else {
            LOG.info("No need to create new session. Current user: " + currentUser + " is the first user");
        }
    }

    public void loginWithAthens(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
    	if(onepassLogin.isUsernameAthensPresent()){
        CobaltUser user = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        onepassLoginUtils.loginWithAthens(user.getUserName(), getPasswordForPlPlusUser(user.getUserName()));
    	}
        LOG.info("The PL+ user has logged in with the Athens");
    }

    public void loginWithInstitution(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
    	if(onepassLogin.isUsernameShibolethPresent()){
        CobaltUser user = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        onepassLoginUtils.loginWithInstitution(user.getUserName(), getPasswordForPlPlusUser(user.getUserName()));
    	}
        LOG.info("The PL+ user has logged in with the Shiboleth");
    }

    public void plUserIsLoggedIn() throws Throwable {
        plUserIsLoggedIn(DEFAULT_HISTORY_LINK_VERIFICATION_STATE);
    }

    public void plUserIsLoggedIn(boolean isRequiredToVerifyHistoryLink) throws Throwable {
        CobaltUser plPlusUser = new CobaltUser();
        plPlusUser.setUserName(this.getCurrentUserName());

        if (!BooleanUtils.toBoolean(System.getProperty(ROUTING))) {
            plPlusUser.setRouting(Routing.NONE);
        } else {
            plPlusUser.setRouting(Routing.DEFAULT);
        }
        plUserIsLoggedInWithFollowingDetails(Collections.singletonList(plPlusUser));
        LOG.info("The PL+ user is logged in");
        if (isRequiredToVerifyHistoryLink) {
            Assert.assertTrue(wlnHeader.isHistoryLinkPresent());
        }
    }

    public void anzUserIsLoggedIn() throws Throwable {
        anzUserIsLoggedIn(DEFAULT_HISTORY_LINK_VERIFICATION_STATE);
    }

    public void anzUserIsLoggedIn(boolean isRequiredToVerifyHistoryLink) throws Throwable {
        CobaltUser plPlusUser = new CobaltUser();
        plPlusUser.setUserName(this.getCurrentUserName());

        if (!BooleanUtils.toBoolean(System.getProperty(ROUTING))) {
            plPlusUser.setRouting(Routing.NONE);
        } else {
            plPlusUser.setRouting(Routing.DEFAULT);
        }
        anzUserIsLoggedInWithFollowingDetails(Collections.singletonList(plPlusUser));
        LOG.info("The PL ANZ user is logged in");
        if (isRequiredToVerifyHistoryLink) {
            Assert.assertTrue(wlnHeader.isHistoryLinkPresent());
        }
    }

    public void ipUserIsLoggedIn(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        CobaltUser plPlusUser = updateFieldsForPlPlusUser(plPlusUserList.get(0));
        String mandatoryRouting = plPlusUser.getMandatoryRouting();

        if ("false".equalsIgnoreCase(System.getProperty(ROUTING))
                && (StringUtils.isEmpty(mandatoryRouting) || mandatoryRouting.equals("NO"))) {
            plPlusUser.setRouting(Routing.NONE);
        }
        if (plPlusUser.getLoginRequired().equals("YES")) {
            login(plPlusUser);
        }
        currentUser.setCurrentUser(plPlusUser);
        LOG.info("The PL+ user has logged in with the following details after IP login");
    }

    public void plUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        CobaltUser plPlusUser = updateFieldsForPlPlusUser(plPlusUserList.get(0));
        applyRootingCliValueIfPresent(plPlusUser);
        loginUser(CobaltUser.updateMissingFields(plPlusUserList.get(0)));
        footerUtils.closeDisclaimerMessage();
        LOG.info("The PL+ user has logged in with the following details");
        plcHomePage.flashScreenPL();
        footerUtils.ourCookiesPolicy();

    }

    public void plUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList, String baseUrl) throws Throwable {
        System.setProperty("plcukProductBase", baseUrl);
        plUserIsLoggedInWithFollowingDetails(plPlusUserList);
    }

    public void userNavitedToRoutingPage() {
        navigateToRoutingPage(Product.PLC);
        LOG.info("The user has navigated to the routing page");
    }

    public void anzUserIsNotLoggedIn() throws Throwable {
        if (!isUserFirstUser(currentUser)) {
            newSession(currentUser);
            navigationCobalt.navigateToPLANZPlus();
            footerUtils.closeDisclaimerMessage();
            Thread.sleep(300);
            footerUtils.ourCookiesPolicy();
        } else {
            LOG.info("No need to create new session. Current user: " + currentUser + " is the first user");
        }
        LOG.info("ANZ user has logged in");
    }

    public void anzUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        for (CobaltUser user : plPlusUserList) {
            user.setProduct(Product.ANZ);
        }
        plUserIsLoggedInWithFollowingDetails(plPlusUserList);
        LOG.info("ANZ user has logged in with the following details");
    }

    public void plUserIsLoggedInWithRoutingDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        CobaltUser plPlusUser = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        if (StringUtils.isEmpty(plPlusUser.getUserName())) {
            plPlusUser.setUserName(this.getCurrentUserName());
        }
        applyRootingCliValueIfPresent(plPlusUser);
        loginUser(CobaltUser.updateMissingFields(plPlusUserList.get(0)));
        footerUtils.closeDisclaimerMessage();
        LOG.info("The PL+ user has logged in with routing details");
    }

    public void anzUserIsLoggedInWithRoutingDetails(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        for (CobaltUser user : plPlusUserList) {
            user.setProduct(Product.ANZ);
        }
        plUserIsLoggedInWithRoutingDetails(plPlusUserList);
        LOG.info("ANZ user has logged in with routing details");
    }

    public void plUserIsApplyingRoutingWithoutLogin(@Transpose List<CobaltUser> plPlusUserList) throws Throwable {
        CobaltUser plPlusUser = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        if (StringUtils.isEmpty(plPlusUser.getUserName())) {
            plPlusUser.setUserName(this.getCurrentUserName());
        }
        doRouting(CobaltUser.updateMissingFields(plPlusUserList.get(0)));
        plcHomePage.waitForPageToLoadAndJQueryProcessing();
        plcHomePage.closeCookieConsentMessage();
        LOG.info("The PL+ user has applied routing without login");
    }

    public void plUserIsApplyingRoutingWithoutLogin(@Transpose List<CobaltUser> plPlusUserList, String baseUrl) throws Throwable {
        System.setProperty("plcukProductBase", baseUrl);
        plUserIsApplyingRoutingWithoutLogin(plPlusUserList);
    }

    private String getCurrentUserName() {
        return !"None".equalsIgnoreCase(System.getProperty(USERNAME)) ? System.getProperty(USERNAME) : StringUtils.defaultIfEmpty(User.getInstance().getUserName(), ExcelFileReader.getDefaultUser());
    }

    protected void loginUser(CobaltUser plPlusUser) throws InterruptedException, IOException {
        loginUser(plPlusUser, DEFAULT_IS_VERIFY_USER_LOGGED_FOR_SKIP_LOGIN_CASE_STATE);
    }

    protected void loginUser(CobaltUser plPlusUser, boolean isVerifyUserLoggedForSkipLoginCase)
            throws InterruptedException, IOException {
        if (currentUser != null && plPlusUser.equalTo(currentUser)) {
            navigateToHomePage(plPlusUser.getProduct());
            if (isVerifyUserLoggedForSkipLoginCase) {
                wlnHeader.waitForPageToLoad();
                LOG.info("---------------------------------BEGIN-------------------------------");
                if (wlnHeader.isSignInLinkPresentWithoutWait() && !wlnHeader.isHistoryLinkPresent()) {
                    LOG.info("User not logged in");
                    resetCurrentUser();
                    LOG.info("Reset user and log in again");
                    loginUser(plPlusUser);
                }
            }
        } else {
            if (!isUserFirstUser(currentUser)) {
                newSession(currentUser);
                if (plPlusUser.getProduct().equals(Product.PLC_lEGACY)) {
                    loginLegacySite(plPlusUser);
                }
            }
            doRouting(plPlusUser);

            if (plPlusUser.getProduct().equals(Product.PLC_lEGACY) && isUserFirstUser(currentUser)) {
                loginLegacySite(plPlusUser);
            }

            if (null == plPlusUser.getUserName()) {
                plPlusUser.setUserName(ExcelFileReader.getDefaultUser());
            }

            if (plPlusUser.getLoginRequired().equals("YES")) {
                login(plPlusUser);
            }

            if (plPlusUser.getProduct().equals(Product.WLN) || plPlusUser.getRouting().equals(Routing.SITE_STRUCTURE_IP_USERS)) {
                try {
                    clientId(plPlusUser.getClientId());
                } catch (TimeoutException e) {
                    LOG.info("Failed to find client id");
                }
                closeWelcomeDialog();
            }
        }
        currentUser.setCurrentUser(plPlusUser);
        LOG.info("The user has logged in");
        logSessionID();
    }

    private void loginLegacySite(CobaltUser plPlusUser) {
        onepassLogin.deleteAllCookies();
        navigateToHomePage(plPlusUser.getProduct());
        plcLegacyHeader.login();
        plcLegacyLoginScreen.onePass();
    }

    protected void navigateToHomePage(Product product) {
        switch (product) {
            case WLN:
                navigationCobalt.navigateToWestlawNext();
                break;
            case PLC:
                navigationCobalt.navigateToPLUKPlus();
                break;
            case ANZ:
                navigationCobalt.navigateToPLANZPlus();
                break;
            case PLC_lEGACY:
                navigationCobalt.navigateToPLCLegacy();
                break;
            case BOOKS:
                navigationCobalt.navigateToBooks();
                break;
            case WLU:
                navigationCobalt.navigateToWestlawUk();
                break;
            case PRACTICALLAW:
                navigationCobalt.navigateToPracticalLaw();
                break;
            default:
                break;
        }
        LOG.info("The user has navigated to the home page");
    }

    private void closeWelcomeDialog() {
        if (comMethods.waitForElementToBeVisible(By.xpath("//*[@id='coid_lightboxOverlay']//input[@value='Close']"),
                1000) != null) {
            onepassLogin.findElement(By.id("welcomePrefCheckbox")).click();
            onepassLogin.findElement(By.xpath("//*[@id='coid_lightboxOverlay']//input[@value='Close']")).click();
        }
        LOG.info("The welcome dialog has closed");
    }

    protected String getPasswordForPlPlusUser(String userName) {
        String overriddenUserName = System.getProperty(USERNAME);
        if (userName.equals(overriddenUserName) || StringUtils.equals(userName, User.getInstance().getUserName())) {
            return StringUtils.defaultIfEmpty(StringUtils.defaultIfEmpty(System.getProperty("password"),
                    User.getInstance().getPassword()), ExcelFileReader.getCobaltPassword(userName));
        }
        return ExcelFileReader.getCobaltPassword(userName);
    }

    private void doRouting(CobaltUser user) throws InterruptedException, IOException {
        if (user.getProduct().equals(Product.PLC) || Product.ANZ.equals(user.getProduct())) {
            if (isUseAdditionalIacAndFacEnabled() && user.getRouting().equals(Routing.NONE)) {
                user.setRouting(Routing.DEFAULT);
            }
            if (!user.getRouting().equals(Routing.NONE)) {
                navigateToRoutingPage(user.getProduct());
                routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "True");
            }
            switch (user.getRouting()) {
                case DEFAULT:
                    LOG.info("DEFAULT routing");
                /*comMethods.waitForElementToBeVisible(By.id("CategoryPageCollectionSet"), 1000)
						.sendKeys("w_plplus_catpagestst_cs");
				new Select(onepassLogin.findElement(By.id("WebContentCollectionSet"))).selectByValue("w_cb_wcmstst_cs");*/
                    break;

                case KHSEARCH:

                case RESEARCH_DOC_DISPLAY:
                    LOG.info("RESEARCH_DOC_DISPLAY routing");
                    comMethods.waitForElementToBeVisible(By.id("CategoryPageCollectionSet"), 1000)
                            .sendKeys("w_plplus_catpagestst_cs");
                    /** VERY IMPORTANT: TODO to remove when PMD issue is fixed */
                    onepassLogin.findElement(By.id("ProductMetadataDataVersion")).sendKeys("2482");
                    new Select(onepassLogin.findElement(By.id("WebContentCollectionSet"))).selectByValue("w_cb_wcmstst_cs");
                    routingPage.showFeatureSelectionsLink().click();
                    WebElement ignore = routingPage.ignoreAuthorizationBlocksDropdown();
                    routingPage.selectDropDownByVisibleText(ignore, "Grant");
                    WebElement pre = routingPage.preReleaseContentDropdown();
                    routingPage.selectDropDownByVisibleText(pre, "Grant");
                    WebElement bypass = routingPage.wlnByPass100KAncillaryDropdown();
                    routingPage.selectDropDownByVisibleText(bypass, "Grant");
                    break;

                case BETA:
                    LOG.info("BETA routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    break;

                case ADESTRA_WMHK:
                    LOG.info("Adestra WM Hong Kong routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.adestraHKWhatsMarketDropdown(), "Grant");
                    break;

                case WMHK:
                    LOG.info("WM Hong Kong routing");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), "Review");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.blockWhatsMarketHongKongSearchDropdown(), "Deny");
                    break;

                case OPENWEB_WMHK:
                    LOG.info("WM Hong Kong Open Web routing");
                    user.setLoginRequired("NO");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), "Review");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                    routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case WMHK_DENY:
                    LOG.info("WM Hong Kong Deny routing");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), "Review");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.blockWhatsMarketHongKongSearchDropdown(), "Grant");
                    break;

                case DASHBOARD:
                    LOG.info("Dashboard routing");
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-CROSSBORDER-DASHBOARD");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    break;

                case POLLS:
                    LOG.info("POLLS routing");
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-CROSSBORDER-POLLS");
                    break;

                case POLLS_OPEN_WEB:
                    LOG.info("POLLS Open Web routing");
                    user.setLoginRequired("NO");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-CROSSBORDER-POLLS");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), "Review");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                    routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    break;

                case DISABLE_CB_POLLS:
                    LOG.info("Disabling polls from CrossBorder routing");
                    routingPage.removedInfrastructureAccessControls().sendKeys("IAC-CROSSBORDER-POLLS");
                    break;

                case ITG:
                    LOG.info("ITG routing");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-CROSSBORDER-ITG");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), "Review");
                    break;

                case ITG_OPEN_WEB:
                    LOG.info("ITG Open Web routing");
                    user.setLoginRequired("NO");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-CROSSBORDER-ITG");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), "Review");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                    routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    break;

                case APPELLATE_HISTORY:
                    routingPage.showFeatureSelectionLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.userInternalDropdown(), "Grant");
                    break;

                case RDD:
                    LOG.info("RDD routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    break;

                case OPEN_WEB:
                    LOG.info("OPEN_WEB routing");
                    user.setLoginRequired("NO");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                    routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    break;

                case ANZ_IAC:
                    LOG.info("ANZ_IAC_Breadcrumbs routing");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-LIGER-NORT-TEST-FILTER");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    break;

                case ANZ_ADESTRA_AU_EMPLOYMENT:
                    LOG.info("ANZ_ADESTRA_AU_EMPLOYMENT routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.adestraAuEmploymentDropdown(), "Deny");
                    break;

                case FAST_DRAFT_OPEN_WEB:
                    LOG.info("FAST_DRAFT_OPEN_WEB routing");
                    user.setLoginRequired("NO");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                    routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    break;

                case FAST_DRAFT_IP_USERS:
                    LOG.info("FAST_DRAFT_IP_USERS routing");
                    user.setLoginRequired("NO");
                    break;

                case FAST_DRAFT:
                    LOG.info("FAST_DRAFT routing");
                    break;

                case FAST_DRAFT_INCORRECT:
                    LOG.info("FAST_DRAFT_INCORRECT routing");
                    routingPage.fastDraftHost().clear();
                    routingPage.fastDraftHost().sendKeys("http://uc199881-tpv-z:9933/");
                    break;

                case FAST_DRAFT_FIRM_STYLE:
                    LOG.info("FAST_DRAFT_FIRM_STYLE routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), "Grant");
                    break;

                case FIRM_STYLE:
                    LOG.info("FIRM_STYLE routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), "Grant");
                    break;

                case FIRM_STYLE_NO_FAC:
                    LOG.info("FIRM_STYLE_NO_FAC routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), "Deny");
                    break;

                case FIRM_STYLE_IP_USERS:
                    LOG.info("FIRM_STYLE_IP_USERS routing");
                    user.setLoginRequired("NO");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), "Grant");
                    break;

                case FIRM_STYLE_IP_USERS_NO_FAC:
                    LOG.info("FIRM_STYLE_IP_USERS_NO_FAC routing");
                    user.setLoginRequired("NO");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), "Deny");
                    break;

                case KH_DOC_DISPLAY:
                    LOG.info("KH_DOC_DISPLAY routing");
                    routingPage.showFeatureSelectionsLink().click();
                    WebElement ignore1 = routingPage.ignoreAuthorizationBlocksDropdown();
                    routingPage.selectDropDownByVisibleText(ignore1, "Grant");
                    break;

                case CAT_PAGES:
                    LOG.info("CAT_PAGES routing");
                    comMethods.waitForElementToBeVisible(By.id("CategoryPageCollectionSet"), 1000)
                            .sendKeys("w_plcuk_catpagestst_cs");
                    break;

                case CONCEPTS_LIBRARY_LINKS:
                    LOG.info("CONCEPTS_LIBRARY_LINKS routing");
                    comMethods.waitForElementToBeVisible(By.id("CategoryPageCollectionSet"), 1000)
                            .sendKeys("w_plcuk_catpagesqa_cs");
                    break;

                    
                case BCI_TOOLS_OPENWEB:
                    LOG.info("BCI_PAGES open web routing");
                    user.setLoginRequired("NO");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case ASK:
                    LOG.info("ASK routing");
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-ASK-CONTENT");
                    if (user.getLoginRequired().equalsIgnoreCase("NO")) {
                        routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                        routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    }
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case ASK_EDITOR:
                    LOG.info("ASK_EDITOR routing");
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-ASK-CONTENT");
                    if (user.getLoginRequired().equalsIgnoreCase("NO")) {
                        routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                        routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    }
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.askEditorDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case ASK_DEV_WEB_COLLECTION:
                    LOG.info("ASK_DEV_WEB_COLLECTION routing");
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-ASK-CONTENT");
                    routingPage.setCategoryPageCollectionSet("w_plplus_catpagestst_cs");
                    routingPage.selectDropDownByVisibleText(routingPage.webContentCollectionSetDropdown(), "DEV");
                    if (user.getLoginRequired().equalsIgnoreCase("NO")) {
                        routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                        routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    }
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case ASK_PROD_WEB_COLLECTION_CATEGORTY_PAGE_CSET:
                    LOG.info("ASK_PROD_WEB_COLLECTION_CATEGORTY_PAGE_CSET routing");
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-ASK-CONTENT");
                    routingPage.setCategoryPageCollectionSet("w_plplus_catpagestst_cs");
                    routingPage.selectDropDownByVisibleText(routingPage.webContentCollectionSetDropdown(), "PROD");
                    if (user.getLoginRequired().equalsIgnoreCase("NO")) {
                        routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                        routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    }
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case ASK_UNRELEASEDCATEGORY:
                    LOG.info("ASK_UNRELEASEDCATEGORY routing");
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-ASK-CONTENT");
                    if (user.getLoginRequired().equalsIgnoreCase("NO")) {
                        routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                        routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    }
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case NONE:
                    LOG.info("No routing required.");
                    navigateToHomePage(user.getProduct());
                    if (user.getLoginRequired().equalsIgnoreCase("YES")) {
                        handleOpenWebFlow();
                    } else {
                        plcHomePage.closeCookieConsentMessage();
                    }
                    return;

                case OPEN_WEB_SEARCH:
                    LOG.info("OPEN_WEB_SEARCH routing");
                    comMethods.waitForElementToBeVisible(By.id("CategoryPageCollectionSet"), 1000)
                            .sendKeys("w_plplus_catpagestst_cs");
                    new Select(onepassLogin.findElement(By.id("WebContentCollectionSet"))).selectByVisibleText("TEST");
                    new Select(onepassLogin.findElement(By.id("SkipAnonymousAuthenticationKey"))).selectByValue("False");
                    break;

                case DOCDISPLAY_UseCollectionSet:
                    LOG.info("DOCDISPLAY_UseCollectionSet routing");
                    comMethods.waitForElementToBeVisible(By.id("CategoryPageCollectionSet"), 1000)
                            .sendKeys("w_plplus_catpagestst_cs");
                    new Select(onepassLogin.findElement(By.id("WebContentCollectionSet"))).selectByValue("w_cb_wcmstst_cs");
                    routingPage.showFeatureSelectionsLink().click();
                    WebElement ignore3 = routingPage.ignoreAuthorizationBlocksDropdown();
                    routingPage.selectDropDownByVisibleText(ignore3, "Grant");
                    WebElement pre1 = routingPage.preReleaseContentDropdown();
                    routingPage.selectDropDownByVisibleText(pre1, "Grant");
                    break;

                case SPECIFIED_USER_TIMEOUT_3_MINUTES:
                    LOG.info("SPECIFIED_USER_TIMEOUT_3_MINUTES routing");
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).clear();
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).sendKeys("3");
                    break;

                case SPECIFIED_USER_TIMEOUT_4_MINUTES:
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).clear();
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).sendKeys("4");
                    break;

                case SPECIFIED_USER_TIMEOUT_5_MINUTES:
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).clear();
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).sendKeys("5");
                    break;

                case SPECIFIED_USER_TIMEOUT_7_MINUTES:
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).clear();
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).sendKeys("7");
                    break;

                case SPECIFIED_USER_TIMEOUT_13_MINUTES:
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).clear();
                    comMethods.waitForElementToBeVisible(
                            By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']"), 1000).sendKeys("13");
                    break;
                case NON_SUBSCRIBER:
                    LOG.info("NON_SUBSCRIBER routing");
                    routingPage.showFeatureSelectionsLink().click();
                    WebElement plc = routingPage.practicalLawDropdown();
                    routingPage.selectDropDownByVisibleText(plc, "Grant");
                    break;

                case RESEARCH_SEARCH:
                    LOG.info("RESEARCH_SEARCH routing");
                    comMethods.waitForElementToBeVisible(By.id("CategoryPageCollectionSet"), 1000)
                            .sendKeys("w_plplus_catpagestst_cs");
                    onepassLogin.findElement(By.id("ProductMetadataDataVersion")).sendKeys("2513");
                    new Select(onepassLogin.findElement(By.id("WebContentCollectionSet"))).selectByValue("w_cb_wcmstst_cs");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    break;

                case ASSERT_PAGE:
                    LOG.info("ASSERT_PAGE routing");
                    // routingPage.setPMdDataVersion("2808");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.userInternal(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    break;

                case ANNOTATIONS:
                    LOG.info("ANNOTATIONS routing");
                    // routingPage.infrastructureAccessTextArea().sendKeys("IAC-WLNDOC-SHAREDNOTES");
                    // routingPage.showFeatureSelectionsLink().click();
                    // routingPage.selectDropDownByVisibleText(routingPage.waitForExpectedElement(By.id("co_website_resourceInfoTypes_BlockShareNoteLink")),"Deny");
                    // routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(),
                    // "Grant");
                    // routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(),
                    // "Grant");
                    // routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(),
                    // "Grant");
                    break;

                case GLOBAL_PAGE:
                    LOG.info("GLOBAL_PAGE routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case UNRELEASED_CAT_PAGES_GRANT:    
                    LOG.info("UNRELEASED_CAT_PAGES_GRANT routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;
                    
                case UNRELEASED_CAT_PAGES:
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Deny");

                    break;

                case NOWHATSMARKETACCESS:
                    LOG.info("NOWHATSMARKETACCESS routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.whatsMarketSearchResultsDropdown(), "Deny");
                    break;
                case SITE_STRUCTURE_NO_COPY_FOR_LINKBUILDER:
                    routingPage.removedInfrastructureAccessControls().sendKeys("IAC-UK-LINKBUILDER-COPY");
                    // DO NOT BREAK HERE!
                case SITE_STRUCTURE:
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS, IAC-SMARTBREADCRUMB-ADD-NAVID, IAC-UK-LINKBUILDER");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.smartBreadcrumbDisplay(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.linkbuilderDropdown(), "Grant");
                    break;

                case ALL_CONTACTS_GROUP:
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.enableAllContactsGroupDropdown(), "Grant");
                    break;

                case SITE_STRUCTURE_LINKBUILDER:
                    routingPage.infrastructureAccessControls().sendKeys("IAC-SMARTBREADCRUMB-ADD-NAVID, IAC-UK-LINKBUILDER");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.smartBreadcrumbDisplay(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.linkbuilderDropdown(), "Grant");
                    break;

                case SITE_STRUCTURE_OW:
                    LOG.info("SITE_STRUCTURE_OW routing");
                    user.setLoginRequired("NO");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS, IAC-SMARTBREADCRUMB-ADD-NAVID, IAC-UK-LINKBUILDER, IAC-SEARCH-TABS");
                    routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.smartBreadcrumbDisplay(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.linkbuilderDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;
                    
                case SITE_STRUCTURE_IP_USERS:
                    LOG.info("SITE_STRUCTURE_IP_USERS routing");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS, IAC-SMARTBREADCRUMB-ADD-NAVID, IAC-UK-LINKBUILDER");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.smartBreadcrumbDisplay(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.linkbuilderDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case SITE_STRUCTURE_ERROR_PAGE:
                    LOG.info("SITE_STRUCTURE_ERROR_PAGE");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS,IAC-DO-NOT-TURN-ON");
                    break;

                case SEARCH_WLUK:
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS,IAC-SEARCH-WITHIN-FOLDERS,IAC-SEARCH-WITHIN-HISTORY,IAC-SEARCH-TABS");
                    break;

                case SEARCH_WITHIN_FFH:
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS,IAC-SEARCH-WITHIN-HISTORY,IAC-SEARCH-WITHIN-FOLDERS");
                    break;

                case ADSTUKWHATMRKT:
                    LOG.info("ADSTUKWHATMRKT routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.AdestraUkWhatsMarketDropdown(), "Grant");
                    break;

                case CALENDAR_KEY_DATE_DOC:
                    LOG.info("CALENDAR_KEY_DATE_DOC routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    break;

                case CALENDAR_KEY_DATE_DOC_OW:
                    LOG.info("CALENDAR_KEY_DATE_DOC_OW routing");
                    user.setLoginRequired("NO");
                    routingPage.infrastructureAccessTextArea().clear();
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-PLPLUS-CALENDAR");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "False");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), "Grant");
                    break;

                case CAA:
                    LOG.info("CAA routing");
                    routingPage.infrastructureAccessTextArea().clear();
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-UK-COMPARTMENTS");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    break;

                case SEARCH_COMO_GROUP6:
                    LOG.info("SEARCH-COMO-GROUP6 routing");
                    routingPage.removedInfrastructureAccessControls().clear();
                    routingPage.removedInfrastructureAccessControls().sendKeys("IAC-SEARCH-COMO-GROUP6");
                    break;

                case MULTIMEDIA_CONTENT:
                    LOG.info("MULTIMEDIA-CONTENT routing");
                    routingPage.documentTextArea().clear();
                    routingPage.documentTextArea().sendKeys(System.getProperty("documentModuleAuthority"));
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), "Review");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                    break;

                default:
                    throw new UnknownError("Routing Code not implemented " + user.getRouting());
            }

            fillAdditionalIacAndFacInfoIfEnabled();
            plcHomePage.waitForPageToLoadAndJQueryProcessing();
            routingPage.saveChangesAndSignOnButton().click();
        } else if (user.getProduct().equals(Product.WLN)) {
            if (user.getRouting().equals(Routing.WLN_ANNOTATIONS)) {
                navigateToRoutingPage(user.getProduct());
                routingPage.infrastructureAccessTextArea().clear();
                routingPage.infrastructureAccessTextArea().sendKeys("IAC-WLNDOC-SHAREDNOTES");
                routingPage.selectToggleSupportedFeatures();
                routingPage.selectSharedNotesCheckBox();
                routingPage.showFeatureSelectionsLink().click();
                routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                routingPage.saveChangesAndSignOnButton().click();
            } else if (user.getRouting().equals(Routing.FOLDERS)) {

                navigateToRoutingPage(user.getProduct());
                routingPage.showFeatureSelectionsLink().click();
                routingPage.waitForPageToLoad();
                routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), "Grant");
                routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), "Grant");
                routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), "Grant");
                routingPage.saveChangesAndSignOnButton().click();
            } else {
                navigateToHomePage(user.getProduct());
            }
        }
    }

    private void fillAdditionalIacAndFacInfoIfEnabled() throws IOException {
        if (!isUseAdditionalIacAndFacEnabled()) {
            LOG.info("Using additional IACs and FACs is disabled");
            return;
        }
        Properties props = new Properties();
        loadProps(props, ADDITIONAL_IAC_FAC_PROP_FILE_NAME);
        boolean isDisableUsingAdditionalIacAndFac = BooleanUtils.toBoolean(System.getProperty("disableUsingAdditionalFacAndIacFromCli"));
        String[] iacs = (isDisableUsingAdditionalIacAndFac ? props.getProperty("iacs")
                : getPropertyValue("iacs", props.getProperty("iacs"))).split(",");
        WebElement iacsArea = routingPage.infrastructureAccessTextArea();
        String[] existingIacs = org.apache.commons.lang3.StringUtils
                .split(org.apache.commons.lang3.StringUtils.remove(iacsArea.getAttribute("value"), " "), ",");

        // at first remove from additional iacs already entered values, then union arrays and perform join
        String finalIacs = org.apache.commons.lang3.StringUtils
                .join(ArrayUtils.addAll(ArrayUtils.removeElements(iacs, existingIacs), existingIacs), ",");
        iacsArea.clear();
        iacsArea.sendKeys(finalIacs);
        String facs = isDisableUsingAdditionalIacAndFac ? props.getProperty("facs")
                : getPropertyValue("facs", props.getProperty("facs"));
        if (routingPage.showFeatureSelectionsLink().getText().contains("Show")) {
            routingPage.showFeatureSelectionsLink().click();
        }
        if (!StringUtils.isEmpty(facs)) {
            for (String fac : facs.split(",")) {
                routingPage.selectDropDownByVisibleText(routingPage.getDropDownByLabel(fac), GRANT_ACCESS_OPTION_TEXT);
            }
        }
    }

    private static String getPropertyValue(String systemPropertyName, String defaultValue) {
        String properyValue = System.getProperty(systemPropertyName);
        //will not cover empty "" value, because it will work as disable case if, for example, we will not need to use particular values
        return (properyValue != null) ? properyValue : defaultValue;
    }

    private void loadProps(Properties props, String fileName) throws IOException {
        InputStream is = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream(fileName);
            props.load(is);
        } finally {
            if (null != is) {
                is.close();
            }
        }
    }

    private boolean isUseAdditionalIacAndFacEnabled() {
        String value = System.getProperty("useAdditionalIacAndFac");
        return !StringUtils.isEmpty(value) && value.contentEquals(Boolean.TRUE.toString());
    }


    /**
     * log user in from login page
     */
    protected void login(CobaltUser user) throws InterruptedException, IOException {
        if ("SUPER_REMEMBER_ME_USER".equals(user.getRole())) {
            onepassLoginUtils.loginToCobaltWithSRM(user.getUserName(), getPasswordForPlPlusUser(user.getUserName()));
        } else {
            onepassLoginUtils.loginToCobalt(user.getUserName(), getPasswordForPlPlusUser(user.getUserName()));
        }
        plcHomePage.waitForPageToLoad();
        plcHomePage.waitForPageToLoadAndJQueryProcessing();
        if (user.getRouting() != Routing.SITE_STRUCTURE_OW) {
            siteStructureUtils.confirmClientID(user.getClientId());
        }
        plcHomePage.waitForPageToLoadAndJQueryProcessing();
        plcHomePage.closeCookieConsentMessage();
        LOG.info("The user has logged in from the login page");
    }

    String baseUrl = System.getProperty("base.url");

    /**
     * Do not delete this. Handles login flow for Open WEB user
     *
     * @throws InterruptedException
     */
    protected void handleOpenWebFlow() throws InterruptedException {
        /**
         * The below commented lines ensure that HOTPROD is being hit thinking
         * OW is turned OFF.
         *
         * Please uncomment and update the lines if OW will be turned OFF on any
         * of the sites.
         */
		/*
		 * switch (baseUrl) { case "hotprod": LOG.info(
		 * "HOT PROD Site is being tested."); break; case "prod": LOG.info(
		 * "Production Site is being tested."); wlnHeader.signInLink().click();
		 * break; case "prodA": LOG.info("PROD A is being tested.");
		 * wlnHeader.signInLink().click(); break; case "prodB": LOG.info(
		 * "PROD B is being tested."); wlnHeader.signInLink().click(); break;
		 * default: wlnHeader.signInLink().click(); break; }
		 */
    	plcHomePage.flashScreenPL();
      //  plcHomePage.closeCookieConsentMessage();
        wlnHeader.signInLink().click();
        wlnHeader.waitForPageToLoad();
        wlnHeader.waitForPageToLoadAndJQueryProcessing();
        if (wlnHeader.isSignInLinkPresent()) {
            handleOpenWebFlow();
        }
    }

    private void hackToTRemovePortAndNavigateToOnePassPage() throws InterruptedException {
        String currentUrl;
        int count = 10;
        do {
            Thread.sleep(1000);
            currentUrl = plcHomePage.getCurrentUrl();
            LOG.info("Current Url = " + currentUrl);
            count--;
        } while ((!currentUrl.contains(":9001/") && !currentUrl.contains(":9517/")) && count > 0);
        LOG.info("Current Url = " + currentUrl);
        if (System.getProperty("base.url").equalsIgnoreCase("ci")) {
            onepassLogin.navigate(currentUrl.replace(":9001/", "/"));
        }
        if (System.getProperty("base.url").equalsIgnoreCase("demo")) {
            onepassLogin.navigate(currentUrl.replace(":9517/", "/"));
        }
    }

    /**
     * Sets Client id
     *
     * @param clientId clientId
     */
    private void clientId(String clientId) {
        welcome.clientID().clear();
        welcome.clientID().sendKeys(clientId);
        welcome.clientID().sendKeys(Keys.ENTER);
        plcHomePage.waitForPageToLoad();
    }

    /**
     * Navigates to Routing Page with check, If the User session is active
     */
    private void navigateToRoutingPage(Product product) {
        switch (product) {
            case WLN:
                navigationCobalt.navigateToWLNSpecificURL("/routing");
                break;
            case PLC:
                navigationCobalt.navigateToPLUKPlus("/routing");
                break;
            case ANZ:
                navigationCobalt.navigateToPLCANZSpecificURL("/routing");
                break;
            default:
                break;
        }
        try {
            WebElement element = onepassLogin
                    .findElement(By.cssSelector(".co_website_routingActiveSessionExplanation >input"));
            element.click();
        } catch (NoSuchElementException | ElementNotVisibleException nse) {
            LOG.error("User not signed in. Routing page is displayed");
        }
        LOG.info("The user session is active, so the user is navigated to the Routing Page");
    }


    /**
     * Signs Off User if user is signed in
     */
    protected void signOff(CobaltUser user) {
        signOff(user.getProduct());
    }

    /**
     * Signs Off User if user is signed in
     */
    protected void signOff(Product product) {
        WebElement element = null;
        try {
            switch (product) {
                case WLN:
                    element = onepassLogin.findElement(By.linkText("Sign Off"));
                    break;
                case PLC:
                    boolean alreadyLoggedIn = false;
                    try {
                        wlnHeader.userAvatarIcon().isDisplayed();
                        alreadyLoggedIn = true;
                    } catch (Exception e) {
                        LOG.error("The user is not logged in");
                    }
                    if (alreadyLoggedIn) {
                        wlnHeader.expandUserAvatarDropDown();
                        element = wlnHeader.userPreferencesDropdown("Sign out");
                    }
                    LOG.info("The user is signed off from PLC");
                    break;
                case ANZ:
                    wlnHeader.expandUserAvatarDropDown();
                    element = wlnHeader.signOutLink();
                    LOG.info("The user is signed off from ANZ");
                    break;
                case PLC_lEGACY:
                    navigationCobalt.navigateToPLCLegacy();
                    element = onepassLogin.findElement(By.linkText("Log out"));
                    LOG.info("The user is signed off from PLC_LEGACY");
                    break;
                default:
                    break;
            }
            if (element != null) {
                element.click();
            }
        } catch (NoSuchElementException | ElementNotVisibleException | TimeoutException nse) {
            LOG.error("Sign-Off link not found");
        }
    }

    private void unlockUser(CobaltUser user) {
    }

    /**
     * New session is created. 1. Reset Routing 2. Sign-Off 3. Delete Cookies
     */
    protected void newSession(CobaltUser user) throws IOException, InterruptedException {
        signOff(user);
        onepassLogin.deleteAllCookies();
        LOG.info("New Session Created");
    }

    public void userRelogsIn() throws Throwable {
        LOG.info("Current user relogs in");
        signOff(currentUser);
        onepassLogin.deleteAllCookies();
        CobaltUser plPlusUser = cloneCurrentUserObject();
        resetCurrentUser();
        loginUser(plPlusUser);
        LOG.info("Current user has reloged in");
    }

    public void userLogsInWithUsername() throws Throwable {
        String userName = this.getCurrentUserName();
        onepassLogin.usernameTextField().sendKeys(userName);
        String password = getPasswordForPlPlusUser(userName);
        onepassLogin.passwordTextField().clear();
        onepassLogin.passwordTextField().sendKeys(password);
        onepassLogin.signOnButton().click();
        LOG.info("The user has logged in");
    }

    public void userLogsInBackAgainFromSignOffPage() throws Throwable {
        onePassLogoutPage.signOffPageSignOnButton().click();
        onepassLogin.waitForPageToLoad();
        userLogsInWithUsername();
        LOG.info("The user has logged back again from the signOff page");
    }

    public void plUserLoginAndOpenFolder(String userName, String folderName) throws Throwable {
        LOG.info("The " + userName + " opens " + folderName + " folder");
        List<CobaltUser> cobaltUsers = new ArrayList<>();
        cobaltUsers.add(getCobaltUserForUserName(userName));
        plUserIsLoggedInWithFollowingDetails(cobaltUsers);
        userGoesToFolderSubFolder(folderName);
        LOG.info("The " + userName + " has opened " + folderName + " folder");
    }

    public void userLoginAndOpenFolder(String userName, String folderName) throws Throwable {
        LOG.info("The user" + userName + " logins and opens a folder " + folderName);
        List<CobaltUser> cobaltUsers = new ArrayList<>();
        cobaltUsers.add(getCobaltUserForUserName(userName));
        plUserIsLoggedInWithFollowingDetails(cobaltUsers);
        userGoesToFolderSubFolder(folderName);
        LOG.info("The user" + userName + " has logined and opened a folder " + folderName);
    }

    private void userGoesToFolderSubFolder(String folderName) throws Throwable {
        LOG.info("The user is going to a folder subfolder");
        wlnHeader.clickHeaderLinkByName("Folders");
        openFolder(folderName);
        LOG.info("The user has gone to a folder subfolder");
    }

    private String folderName;

    private void openFolder(String folderName) {
        LOG.info("Open folder " + folderName);
        foldersUtils.openFolder(folderName);
        this.folderName = folderName;
        LOG.info(folderName + " has been opened");
    }

    @Given("^PL\\+ user '(.*)' navigates directly to document with guid '(.*)'$")
    public void plUserLoginAndNavigateToDoc(String userName, String docGuid) throws Throwable {
        LOG.info("The PL+ user is navigating directly to the document with guid " + docGuid);
        plUserIsLoggedInWithFollowingDetails(getCobaltUserForUserNameAsList(userName));
        navigatesDirectlyToDocumentWithGuid(docGuid);
        LOG.info("The PL+ user has directly navigated to the document with guid " + docGuid);
    }

    private void navigatesDirectlyToDocumentWithGuid(String guid) throws Throwable {
        LOG.info("The user is navigating directly to the document with guid");
        navigationCobalt.navigateToPLUKPlus("/Document/" + guid + "/View/FullText.html");
        resourcePage.waitForPageToLoadAndJQueryProcessing();
        LOG.info("The user has navigated directly to the document with guid");
    }

    @Given("^PL\\+ user '(.*)' searches for '(.*)'$")
    public void plUserLoginAndSearch(String userName, String term) throws Throwable {
        LOG.info("The PL+ user " + userName + "searches for " + term);
        plUserIsLoggedInWithFollowingDetails(getCobaltUserForUserNameAsList(userName));
        searchFor(term);
        LOG.info("The PL+ user " + userName + " found" + term);
    }

    private void searchFor(String searchQuery) {
        LOG.info("Searching for " + searchQuery);
        searchUtils.enterSearchText(searchQuery);
        searchHomePage.searchButton().sendKeys(Keys.ENTER);
        searchHomePage.waitForPageToLoad();
        searchHomePage.waitForPageToLoadAndJQueryProcessing();
        LOG.info("Found " + searchQuery);
    }

    protected CobaltUser getCobaltUserForUserName(String userName) {
        CobaltUser resultUser = new CobaltUser();
        resultUser.setUserName(userName);
        resultUser = CobaltUser.updateMissingFields(resultUser);
        return resultUser;
    }

    private List<CobaltUser> getCobaltUserForUserNameAsList(String userName) {
        List<CobaltUser> cobaltUsers = new ArrayList<>();
        cobaltUsers.add(getCobaltUserForUserName(userName));
        return cobaltUsers;
    }

    protected void deleteAllCookies() {
        onepassLogin.deleteAllCookies();
    }

    public void userComeBackOnToHomePageAsLoggedInUser() throws Throwable {
        wlnHeader.waitForPageToLoadAndJQueryProcessing();
        if (!isHomePage()) {
            navigationCobalt.navigateToHomePage();
            wlnHeader.waitForPageToLoad();

            wlnHeader.waitForPageToLoadAndJQueryProcessing();
        }
        assertThat(wlnHeader.favouritesLink().isDisplayed(), Is.is(true));
        assertThat(wlnHeader.foldersLink().isDisplayed(), Is.is(true));
        assertThat(wlnHeader.historyLink().isDisplayed(), Is.is(true));
        assertThat(resourcePage.waitForExpectedElement(By.linkText("Employment")).isDisplayed(), Is.is(true));
    }

    public boolean isHomePage() {
        return categoryPage.getCurrentUrl().contains("/Search/Home.html")
                || categoryPage.getCurrentUrl().contains("/Search/BrowseRoot.html")
                || categoryPage.getCurrentUrl().contains("Home/Home");
    }

    public void userLogsInFromLoginPage(@Transpose List<CobaltUser> plPlusUserList)
            throws InterruptedException, IOException {
        CobaltUser plPlusUser = updateFieldsForPlPlusUser(plPlusUserList.get(0));
        login(plPlusUser);
        currentUser.setCurrentUser(plPlusUser);
    }

    public void userEntersUsernameAndPasswordOnLoginPage(@Transpose List<CobaltUser> plPlusUserList)
            throws InterruptedException, IOException {
        CobaltUser plPlusUser = updateFieldsForPlPlusUser(plPlusUserList.get(0));
        onepassLoginUtils.enterUserNameAndPassword(plPlusUser.getUserName(),
                getPasswordForPlPlusUser(plPlusUser.getUserName()));
        currentUser.setCurrentUser(plPlusUser);
    }

    protected CobaltUser updateFieldsForPlPlusUser(CobaltUser plPlusUser) {
        CobaltUser.updateMissingFields(plPlusUser);
        if (StringUtils.isEmpty(plPlusUser.getUserName())) {
            plPlusUser.setUserName(this.getCurrentUserName());
            CobaltUser.updateMissingFields(plPlusUser);
        }
        return plPlusUser;
    }

    protected void applyRootingCliValueIfPresent(CobaltUser plPlusUser) {
        String mandatoryRouting = plPlusUser.getMandatoryRouting();
        String mandatoryRoutingCli = System.getProperty("MANDATORY_ROUTING");
        if (!StringUtils.isEmpty(mandatoryRouting)) {
            if (mandatoryRouting.toUpperCase().contentEquals("VALUE_FROM_COMMAND_LINE")) {
                if (!StringUtils.isEmpty(mandatoryRoutingCli)) {
                    mandatoryRouting = mandatoryRoutingCli.toUpperCase();
                } else {
                    mandatoryRouting = "NO";
                }
            }
        }
        if ("false".equalsIgnoreCase(System.getProperty(ROUTING))
                && (StringUtils.isEmpty(mandatoryRouting) || mandatoryRouting.equals("NO"))) {
            plPlusUser.setRouting(Routing.NONE);
        }
    }

    private void logSessionID() {
        String sessionId = StringUtils.defaultIfBlank(getSessionIDFromUI(), "user is not on PLC page");
        LOG.info("Session ID : {}", sessionId);
    }
}