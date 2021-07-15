package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
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
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
import com.thomsonreuters.pageobjects.utils.sitestructure.SiteStructureUtils;
import com.thomsonreuters.utils.TimeoutUtils;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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

/**
 * Login and Navigation Steps.
 */
public class BaseCommonLoginNavigation extends BaseStepDef {

    private static final String ROUTING = "routing";
    private static final String ROUTING_URL = CommonStringMethods.SLASH_DELIMITER + ROUTING;

    private static final String DEFAULT_PROD_KEYWORD = "prod";

    private static final boolean DEFAULT_HISTORY_LINK_VERIFICATION_STATE = false;
    private static final boolean DEFAULT_IS_CREATE_NEW_SESSION_STATE = false;

    private static final boolean DEFAULT_IS_VERIFY_USER_LOGGED_FOR_SKIP_LOGIN_CASE_STATE = false;

    private static final String ADDITIONAL_IAC_FAC_PROP_FILE_NAME = "additionalIacAndFac.properties";
    private static final String USERNAME = "username";
    private static final String GRANT_ACCESS_OPTION_TEXT = "Grant";
    private static final String DENY_ACCESS_OPTION_TEXT = "Deny";
    private static final String FALSE_TEXT = "False";
    private static final String NOVUS_STAGE_REVIEW_TEXT = "Review";
    private static final String PL_PLUS_COLLECTION_SET = "w_plplus_catpagestst_cs";
    private static final String CROSS_BORDER_POLLS_IAC = "IAC-CROSSBORDER-POLLS";
    private static final String WEB_CONTENT_COLLECTION_VALUE = "w_cb_wcmstst_cs";
    private static final int TIMEOUT_BEFORE_LOGIN = 3;

    private static final By SESSION_TIMEOUT_FIELD = By.xpath("//input[@id='Text2' and @name='SessionTimeoutOverride']");
    private static final By CAT_PAGE_COLLECTION_SET_ELEMENT = By.id("CategoryPageCollectionSet");
    private static final By WEB_CONTENT_COLLECTION_ELEMENT = By.id("WebContentCollectionSet");
    private static final String BASE_URL = System.getProperty("base.url");

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
    private FoldersUtils foldersUtils;
    private SearchHomePage searchHomePage;
    private KHResourcePage resourcePage;
    private CategoryPage categoryPage;
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
        foldersUtils = new FoldersUtils();
        searchHomePage = new SearchHomePage();
        resourcePage = new KHResourcePage();
        categoryPage = new CategoryPage();
        siteStructureUtils = new SiteStructureUtils();
        footerUtils = new FooterUtils();
        searchUtils = new SearchUtils();
    }

    public void wlnLogin() throws IOException {
        CobaltUser user = new CobaltUser();
        user.setProduct(Product.WLN);
        loginUser(user);
        LOG.info("The user has logged in to WLN");
    }

    public void wlnLoginWithDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
        CobaltUser user = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        user.setProduct(Product.WLN);
        loginUser(user);
        LOG.info("The WLN user is logged in with the following details");
    }

    public void plcLegacyLoginWithDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
        CobaltUser user = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        user.setProduct(Product.PLC_lEGACY);
        loginUser(user);
        LOG.info("The PLC user is logged in with the following details");
    }

    public void plUserNaviagatesToHomePage() {
        onepassLogin.deleteAllCookies();
        navigationCobalt.navigateToPLUKPlus();
        plcHomePage.waitForPageToLoadAndJQueryProcessing();
        plcHomePage.closeCookieConsentMessage();
        resetCurrentUser();
        LOG.info("The PL+ user has navigated to the home page");
    }

    public void theUserClicksOnSignOnLinkOnTheHeader() {
        wlnHeader.signInLink().click();
        onepassLogin.waitForPageToLoad();
        LOG.info("The user has clicked on the SignOn link on the header");
    }

    public void plAnzUserNaviagatesToHomePage() {
        getDriver().manage().deleteAllCookies();
        navigationCobalt.navigateToPLANZPlus();
        resetCurrentUser();
        wlnHeader.waitForPageToLoadAndJQueryProcessing();
        footerUtils.closeDisclaimerMessage();
        wlnHeader.closePrivacyNoticePopup();
        LOG.info("The PL+ ANZ user has navigated to the home page");
    }

    public void plUserNavigatesToLoginPage() {
        plUserNavigatesToLoginPage(DEFAULT_PROD_KEYWORD);
    }

    public void plUserNavigatesToLoginPage(String prodKeyword) {
        onepassLogin.deleteAllCookies();
        navigationCobalt.navigateToPLUKPlus();
        plcHomePage.closeCookieConsentMessage();
        resetCurrentUser();
        if (!BASE_URL.contains(prodKeyword)) {
            theUserClicksOnSignOnLinkOnTheHeader();
            LOG.info("The PL+ user has navigated to the login page");
        } else {
            LOG.info("OpenWeb is OFF on production. User already on login page");
        }
    }

    public void plUserIsNotLoggedIn() {
        plUserIsNotLoggedIn(DEFAULT_IS_CREATE_NEW_SESSION_STATE);
    }

    public void plUserIsNotLoggedIn(boolean isCreateNewSessionImmediately) {
        if (isCreateNewSessionImmediately || !isUserFirstUser(currentUser)) {
            newSession(currentUser);
            navigationCobalt.navigateToPLUKPlus();
            plcHomePage.waitForPageToLoadAndJQueryProcessing();
            plcHomePage.closeCookieConsentMessage();
        } else {
            LOG.info("No need to create new session. Current user: {} is the first user", currentUser);
        }
    }

    public void loginWithAthens(@Transpose List<CobaltUser> plPlusUserList) {
    	if(onepassLogin.isUsernameAthensPresent()){
        CobaltUser user = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        onepassLoginUtils.loginWithAthens(user.getUserName(), getPasswordForPlPlusUser(user.getUserName()));
    	}
        LOG.info("The PL+ user has logged in with the Athens");
    }

    public void loginWithInstitution(@Transpose List<CobaltUser> plPlusUserList) {
    	if(onepassLogin.isUsernameShibolethPresent()){
        CobaltUser user = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        onepassLoginUtils.loginWithInstitution(user.getUserName(), getPasswordForPlPlusUser(user.getUserName()));
    	}
        LOG.info("The PL+ user has logged in with the Shiboleth");
    }

    public void plUserIsLoggedIn() throws IOException {
        plUserIsLoggedIn(DEFAULT_HISTORY_LINK_VERIFICATION_STATE);
    }

    public void plUserIsLoggedIn(boolean isRequiredToVerifyHistoryLink) throws IOException {
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

    public void anzUserIsLoggedIn() throws IOException {
        anzUserIsLoggedIn(DEFAULT_HISTORY_LINK_VERIFICATION_STATE);
    }

    public void anzUserIsLoggedIn(boolean isRequiredToVerifyHistoryLink) throws IOException {
        CobaltUser plPlusUser = new CobaltUser();
        plPlusUser.setUserName(this.getCurrentUserName());

        plPlusUser.setUserName(!"None".equalsIgnoreCase(System.getProperty(USERNAME)) ? System.getProperty(USERNAME)
                : StringUtils.defaultIfEmpty(User.getInstance().getUserName(), currentUser.getUserName()));

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

    public void ipUserIsLoggedIn(@Transpose List<CobaltUser> plPlusUserList) {
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

    public void plUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException{
        CobaltUser plPlusUser = updateFieldsForPlPlusUser(plPlusUserList.get(0));
        applyRootingCliValueIfPresent(plPlusUser);
        loginUser(CobaltUser.updateMissingFields(plPlusUserList.get(0)));
        footerUtils.closeDisclaimerMessage();
        LOG.info("The PL+ user has logged in with the following details");
        plcHomePage.flashScreenPL();
        footerUtils.ourCookiesPolicy();

    }

    public void plUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList, String baseUrl) throws IOException {
        System.setProperty("plcukProductBase", baseUrl);
        plUserIsLoggedInWithFollowingDetails(plPlusUserList);
    }

    public void userNavitedToRoutingPage() {
        navigateToRoutingPage(Product.PLC);
        LOG.info("The user has navigated to the routing page");
    }

    public void anzUserIsNotLoggedIn() {
        if (!isUserFirstUser(currentUser)) {
            newSession(currentUser);
            navigationCobalt.navigateToPLANZPlus();
            wlnHeader.waitForPageToLoadAndJQueryProcessing();
            wlnHeader.closePrivacyNoticePopup();
            footerUtils.closeDisclaimerMessage();
            TimeoutUtils.sleepInSeconds(TIMEOUT_BEFORE_LOGIN);
            footerUtils.ourCookiesPolicy();
        } else {
            LOG.info("No need to create new session. Current user: {} is the first user", currentUser);
        }
        LOG.info("ANZ user has logged in");
    }

    public void anzUserIsLoggedInWithFollowingDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
        for (CobaltUser user : plPlusUserList) {
            user.setProduct(Product.ANZ);
        }
        plUserIsLoggedInWithFollowingDetails(plPlusUserList);
        LOG.info("ANZ user has logged in with the following details");
    }

    public void plUserIsLoggedInWithRoutingDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
        CobaltUser plPlusUser = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        if (StringUtils.isEmpty(plPlusUser.getUserName())) {
            plPlusUser.setUserName(this.getCurrentUserName());
        }
        applyRootingCliValueIfPresent(plPlusUser);
        loginUser(CobaltUser.updateMissingFields(plPlusUserList.get(0)));
        footerUtils.closeDisclaimerMessage();
        LOG.info("The PL+ user has logged in with routing details");
    }

    public void anzUserIsLoggedInWithRoutingDetails(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
        for (CobaltUser user : plPlusUserList) {
            user.setProduct(Product.ANZ);
        }
        plUserIsLoggedInWithRoutingDetails(plPlusUserList);
        LOG.info("ANZ user has logged in with routing details");
    }

    public void plUserIsApplyingRoutingWithoutLogin(@Transpose List<CobaltUser> plPlusUserList) throws IOException {
        CobaltUser plPlusUser = CobaltUser.updateMissingFields(plPlusUserList.get(0));
        if (StringUtils.isEmpty(plPlusUser.getUserName())) {
            plPlusUser.setUserName(this.getCurrentUserName());
        }
        doRouting(CobaltUser.updateMissingFields(plPlusUserList.get(0)));
        plcHomePage.waitForPageToLoadAndJQueryProcessing();
        plcHomePage.closeCookieConsentMessage();
        LOG.info("The PL+ user has applied routing without login");
    }

    public void plUserIsApplyingRoutingWithoutLogin(@Transpose List<CobaltUser> plPlusUserList, String baseUrl) throws IOException {
        System.setProperty("plcukProductBase", baseUrl);
        plUserIsApplyingRoutingWithoutLogin(plPlusUserList);
    }

    private String getCurrentUserName() {
        return !"None".equalsIgnoreCase(System.getProperty(USERNAME)) ? System.getProperty(USERNAME) : StringUtils.defaultIfEmpty(User.getInstance().getUserName(), ExcelFileReader.getDefaultUser());
    }

    protected void loginUser(CobaltUser plPlusUser) throws IOException {
        loginUser(plPlusUser, DEFAULT_IS_VERIFY_USER_LOGGED_FOR_SKIP_LOGIN_CASE_STATE);
    }

    protected void loginUser(CobaltUser plPlusUser, boolean isVerifyUserLoggedForSkipLoginCase) throws IOException {
        LOG.info("---------------------------------BEGIN USER LOGIN-------------------------------");
        if (plPlusUser.equalTo(currentUser)) {
            loginAsCurrentUser(plPlusUser, isVerifyUserLoggedForSkipLoginCase);
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

            loginAsWLNUser(plPlusUser);
        }
        currentUser.setCurrentUser(plPlusUser);
        LOG.info("The user has logged in");
        logSessionID();
    }

    private void loginAsCurrentUser(CobaltUser plPlusUser, boolean isVerifyUserLoggedForSkipLoginCase) throws IOException {
        navigateToHomePage(plPlusUser.getProduct());
        if (isVerifyUserLoggedForSkipLoginCase) {
            wlnHeader.waitForPageToLoad();
            if (wlnHeader.isSignInLinkPresentWithoutWait() && !wlnHeader.isHistoryLinkPresent()) {
                LOG.info("User not logged in");
                resetCurrentUser();
                LOG.info("Reset user and log in again");
                loginUser(plPlusUser);
            }
        }
    }

    private void loginAsWLNUser(CobaltUser plPlusUser) {
        if (plPlusUser.getProduct().equals(Product.WLN)) {
            try {
                clientId(plPlusUser.getClientId());
            } catch (TimeoutException e) {
                LOG.info("Failed to find client id");
            }
            closeWelcomeDialog();
        }
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
        if (comMethods.waitForElementToBeVisible(By.xpath("//*[@id='coid_lightboxOverlay']//input[@value='Close']")
        ) != null) {
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

    private void skipAnonymousAuthenticationRouting(CobaltUser user){
        if (isUseAdditionalIacAndFacEnabled() && user.getRouting().equals(Routing.NONE)) {
            user.setRouting(Routing.DEFAULT);
        }
        if (!user.getRouting().equals(Routing.NONE)) {
            navigateToRoutingPage(user.getProduct());
            routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), "True");
        }
    }

    private void setRoutingIfLoginNotRequired(CobaltUser user) {
        if (user.getLoginRequired().equalsIgnoreCase("NO")) {
            routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
            setAnonymousRegistrationKey();
        }
    }

    private void doRouting(CobaltUser user) throws IOException {
        if (user.getProduct().equals(Product.PLC) || Product.ANZ.equals(user.getProduct())) {
            skipAnonymousAuthenticationRouting(user);
            switch (user.getRouting()) {
                case DEFAULT:
                    LOG.info("DEFAULT routing");
                    break;

                case KHSEARCH:

                case RESEARCH_DOC_DISPLAY:
                    LOG.info("RESEARCH_DOC_DISPLAY routing");
                    comMethods.waitForElementToBeVisible(CAT_PAGE_COLLECTION_SET_ELEMENT)
                            .sendKeys(PL_PLUS_COLLECTION_SET);
                    /** VERY IMPORTANT: TODO to remove when PMD issue is fixed */
                    onepassLogin.findElement(By.id("ProductMetadataDataVersion")).sendKeys("2482");
                    new Select(onepassLogin.findElement(WEB_CONTENT_COLLECTION_ELEMENT)).selectByValue(WEB_CONTENT_COLLECTION_VALUE);
                    routingPage.showFeatureSelectionsLink().click();
                    WebElement ignore = routingPage.ignoreAuthorizationBlocksDropdown();
                    routingPage.selectDropDownByVisibleText(ignore, GRANT_ACCESS_OPTION_TEXT);
                    WebElement pre = routingPage.preReleaseContentDropdown();
                    routingPage.selectDropDownByVisibleText(pre, GRANT_ACCESS_OPTION_TEXT);
                    WebElement bypass = routingPage.wlnByPass100KAncillaryDropdown();
                    routingPage.selectDropDownByVisibleText(bypass, GRANT_ACCESS_OPTION_TEXT);
                    break;

                case BETA:
                    LOG.info("BETA routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ADESTRA_WMHK:
                    LOG.info("Adestra WM Hong Kong routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.adestraHKWhatsMarketDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case WMHK:
                    LOG.info("WM Hong Kong routing");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), NOVUS_STAGE_REVIEW_TEXT);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.blockWhatsMarketHongKongSearchDropdown(), DENY_ACCESS_OPTION_TEXT);
                    break;

                case OPENWEB_WMHK:
                    LOG.info("WM Hong Kong Open Web routing");
                    user.setLoginRequired("NO");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), NOVUS_STAGE_REVIEW_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
                    setAnonymousRegistrationKey();
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case WMHK_DENY:
                    LOG.info("WM Hong Kong Deny routing");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), NOVUS_STAGE_REVIEW_TEXT);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.blockWhatsMarketHongKongSearchDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case DASHBOARD:
                    LOG.info("Dashboard routing");
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-CROSSBORDER-DASHBOARD");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case POLLS:
                    LOG.info("POLLS routing");
                    routingPage.infrastructureAccessTextArea().sendKeys(CROSS_BORDER_POLLS_IAC);
                    break;

                case POLLS_OPEN_WEB:
                    LOG.info("POLLS Open Web routing");
                    user.setLoginRequired("NO");
                    routingPage.infrastructureAccessControls().sendKeys(CROSS_BORDER_POLLS_IAC);
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), NOVUS_STAGE_REVIEW_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
                    setAnonymousRegistrationKey();
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case DISABLE_CB_POLLS:
                    LOG.info("Disabling polls from CrossBorder routing");
                    routingPage.removedInfrastructureAccessControls().sendKeys(CROSS_BORDER_POLLS_IAC);
                    break;

                case ITG:
                    LOG.info("ITG routing");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-CROSSBORDER-ITG");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), NOVUS_STAGE_REVIEW_TEXT);
                    break;

                case ITG_OPEN_WEB:
                    LOG.info("ITG Open Web routing");
                    user.setLoginRequired("NO");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-CROSSBORDER-ITG");
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), NOVUS_STAGE_REVIEW_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
                    setAnonymousRegistrationKey();
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case APPELLATE_HISTORY:
                    routingPage.showFeatureSelectionLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.userInternalDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case RDD:
                    LOG.info("RDD routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case OPEN_WEB:
                    LOG.info("OPEN_WEB routing");
                    user.setLoginRequired("NO");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
                    setAnonymousRegistrationKey();
                    break;

                case ANZ_IAC:
                    LOG.info("ANZ_IAC_Breadcrumbs routing");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-LIGER-NORT-TEST-FILTER");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ANZ_ADESTRA_AU_EMPLOYMENT:
                    LOG.info("ANZ_ADESTRA_AU_EMPLOYMENT routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.adestraAuEmploymentDropdown(), DENY_ACCESS_OPTION_TEXT);
                    break;

                case FAST_DRAFT_OPEN_WEB:
                    LOG.info("FAST_DRAFT_OPEN_WEB routing");
                    user.setLoginRequired("NO");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
                    setAnonymousRegistrationKey();
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
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case FIRM_STYLE:
                    LOG.info("FIRM_STYLE routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case FIRM_STYLE_NO_FAC:
                    LOG.info("FIRM_STYLE_NO_FAC routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), DENY_ACCESS_OPTION_TEXT);
                    break;

                case FIRM_STYLE_IP_USERS:
                    LOG.info("FIRM_STYLE_IP_USERS routing");
                    user.setLoginRequired("NO");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case FIRM_STYLE_IP_USERS_NO_FAC:
                    LOG.info("FIRM_STYLE_IP_USERS_NO_FAC routing");
                    user.setLoginRequired("NO");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.firmStyleDropdown(), DENY_ACCESS_OPTION_TEXT);
                    break;

                case KH_DOC_DISPLAY:
                    LOG.info("KH_DOC_DISPLAY routing");
                    routingPage.showFeatureSelectionsLink().click();
                    WebElement ignore1 = routingPage.ignoreAuthorizationBlocksDropdown();
                    routingPage.selectDropDownByVisibleText(ignore1, GRANT_ACCESS_OPTION_TEXT);
                    break;

                case CAT_PAGES:
                    LOG.info("CAT_PAGES routing");
                    comMethods.waitForElementToBeVisible(CAT_PAGE_COLLECTION_SET_ELEMENT)
                            .sendKeys("w_plcuk_catpagestst_cs");
                    break;

                case CONCEPTS_LIBRARY_LINKS:
                    LOG.info("CONCEPTS_LIBRARY_LINKS routing");
                    comMethods.waitForElementToBeVisible(CAT_PAGE_COLLECTION_SET_ELEMENT)
                            .sendKeys("w_plcuk_catpagesqa_cs");
                    break;

                    
                case BCI_TOOLS_OPENWEB:
                    LOG.info("BCI_PAGES open web routing");
                    user.setLoginRequired("NO");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ASK:
                    LOG.info("ASK routing");
                    enableAskContent();
                    setRoutingIfLoginNotRequired(user);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ASK_EDITOR:
                    LOG.info("ASK_EDITOR routing");
                    enableAskContent();
                    setRoutingIfLoginNotRequired(user);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.askEditorDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ASK_DEV_WEB_COLLECTION:
                    LOG.info("ASK_DEV_WEB_COLLECTION routing");
                    enableAskContent();
                    routingPage.setCategoryPageCollectionSet(PL_PLUS_COLLECTION_SET);
                    routingPage.selectDropDownByVisibleText(routingPage.webContentCollectionSetDropdown(), "DEV");
                    setRoutingIfLoginNotRequired(user);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ASK_PROD_WEB_COLLECTION_CATEGORTY_PAGE_CSET:
                    LOG.info("ASK_PROD_WEB_COLLECTION_CATEGORTY_PAGE_CSET routing");
                    enableAskContent();
                    routingPage.setCategoryPageCollectionSet(PL_PLUS_COLLECTION_SET);
                    routingPage.selectDropDownByVisibleText(routingPage.webContentCollectionSetDropdown(), "PROD");
                    setRoutingIfLoginNotRequired(user);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ASK_UNRELEASEDCATEGORY:
                    LOG.info("ASK_UNRELEASEDCATEGORY routing");
                    enableAskContent();
                    setRoutingIfLoginNotRequired(user);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
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
                    comMethods.waitForElementToBeVisible(CAT_PAGE_COLLECTION_SET_ELEMENT)
                            .sendKeys(PL_PLUS_COLLECTION_SET);
                    new Select(onepassLogin.findElement(WEB_CONTENT_COLLECTION_ELEMENT)).selectByVisibleText("TEST");
                    new Select(onepassLogin.findElement(By.id("SkipAnonymousAuthenticationKey"))).selectByValue(FALSE_TEXT);
                    break;

                case DOCDISPLAY_UseCollectionSet:
                    LOG.info("DOCDISPLAY_UseCollectionSet routing");
                    comMethods.waitForElementToBeVisible(CAT_PAGE_COLLECTION_SET_ELEMENT)
                            .sendKeys(PL_PLUS_COLLECTION_SET);
                    new Select(onepassLogin.findElement(WEB_CONTENT_COLLECTION_ELEMENT)).selectByValue(WEB_CONTENT_COLLECTION_VALUE);
                    routingPage.showFeatureSelectionsLink().click();
                    WebElement ignore3 = routingPage.ignoreAuthorizationBlocksDropdown();
                    routingPage.selectDropDownByVisibleText(ignore3, GRANT_ACCESS_OPTION_TEXT);
                    WebElement pre1 = routingPage.preReleaseContentDropdown();
                    routingPage.selectDropDownByVisibleText(pre1, GRANT_ACCESS_OPTION_TEXT);
                    break;

                case SPECIFIED_USER_TIMEOUT_3_MINUTES:
                    LOG.info("SPECIFIED_USER_TIMEOUT_3_MINUTES routing");
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).clear();
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).sendKeys("3");
                    break;

                case SPECIFIED_USER_TIMEOUT_4_MINUTES:
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).clear();
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).sendKeys("4");
                    break;

                case SPECIFIED_USER_TIMEOUT_5_MINUTES:
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).clear();
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).sendKeys("5");
                    break;

                case SPECIFIED_USER_TIMEOUT_7_MINUTES:
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).clear();
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).sendKeys("7");
                    break;

                case SPECIFIED_USER_TIMEOUT_13_MINUTES:
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).clear();
                    comMethods.waitForElementToBeVisible(SESSION_TIMEOUT_FIELD).sendKeys("13");
                    break;
                case NON_SUBSCRIBER:
                    LOG.info("NON_SUBSCRIBER routing");
                    routingPage.showFeatureSelectionsLink().click();
                    WebElement plc = routingPage.practicalLawDropdown();
                    routingPage.selectDropDownByVisibleText(plc, GRANT_ACCESS_OPTION_TEXT);
                    break;

                case RESEARCH_SEARCH:
                    LOG.info("RESEARCH_SEARCH routing");
                    comMethods.waitForElementToBeVisible(CAT_PAGE_COLLECTION_SET_ELEMENT)
                            .sendKeys(PL_PLUS_COLLECTION_SET);
                    onepassLogin.findElement(By.id("ProductMetadataDataVersion")).sendKeys("2513");
                    new Select(onepassLogin.findElement(WEB_CONTENT_COLLECTION_ELEMENT)).selectByValue(WEB_CONTENT_COLLECTION_VALUE);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ASSERT_PAGE:
                    LOG.info("ASSERT_PAGE routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.userInternal(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case GLOBAL_PAGE:
                    LOG.info("GLOBAL_PAGE routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case UNRELEASED_CAT_PAGES_GRANT:    
                    LOG.info("UNRELEASED_CAT_PAGES_GRANT routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;
                    
                case UNRELEASED_CAT_PAGES:
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), DENY_ACCESS_OPTION_TEXT);

                    break;

                case NOWHATSMARKETACCESS:
                    LOG.info("NOWHATSMARKETACCESS routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.whatsMarketSearchResultsDropdown(), DENY_ACCESS_OPTION_TEXT);
                    break;
                case SITE_STRUCTURE_NO_COPY_FOR_LINKBUILDER:
                    routingPage.removedInfrastructureAccessControls().sendKeys("IAC-UK-LINKBUILDER-COPY");
                    setAllContactsGroupSettings();
                    break;
                    // DO NOT BREAK HERE!
                case SITE_STRUCTURE:
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS, IAC-SMARTBREADCRUMB-ADD-NAVID, IAC-UK-LINKBUILDER");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.smartBreadcrumbDisplay(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.linkbuilderDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case ALL_CONTACTS_GROUP:
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.enableAllContactsGroupDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case SITE_STRUCTURE_LINKBUILDER:
                    routingPage.infrastructureAccessControls().sendKeys("IAC-SMARTBREADCRUMB-ADD-NAVID, IAC-UK-LINKBUILDER");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.smartBreadcrumbDisplay(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.linkbuilderDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case SITE_STRUCTURE_OW:
                    LOG.info("SITE_STRUCTURE_OW routing");
                    user.setLoginRequired("NO");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS, IAC-SMARTBREADCRUMB-ADD-NAVID, IAC-UK-LINKBUILDER, IAC-SEARCH-TABS");
                    setAnonymousRegistrationKey();
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.smartBreadcrumbDisplay(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.linkbuilderDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;
                    
                case SITE_STRUCTURE_IP_USERS:
                    LOG.info("SITE_STRUCTURE_IP_USERS routing");
                    routingPage.infrastructureAccessControls().sendKeys("IAC-UK-COMPARTMENTS, IAC-SMARTBREADCRUMB-ADD-NAVID, IAC-UK-LINKBUILDER");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.smartBreadcrumbDisplay(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.linkbuilderDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
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
                    routingPage.selectDropDownByVisibleText(routingPage.AdestraUkWhatsMarketDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case CALENDAR_KEY_DATE_DOC:
                    LOG.info("CALENDAR_KEY_DATE_DOC routing");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case CALENDAR_KEY_DATE_DOC_OW:
                    LOG.info("CALENDAR_KEY_DATE_DOC_OW routing");
                    user.setLoginRequired("NO");
                    routingPage.infrastructureAccessTextArea().clear();
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-PLPLUS-CALENDAR");
                    routingPage.selectDropDownByVisibleText(routingPage.skipAnonymousAuthenticationDropdown(), FALSE_TEXT);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.unreleasedCatPagesDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    break;

                case CAA:
                    LOG.info("CAA routing");
                    routingPage.infrastructureAccessTextArea().clear();
                    routingPage.infrastructureAccessTextArea().sendKeys("IAC-UK-COMPARTMENTS");
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
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
                    routingPage.selectDropDownByVisibleText(routingPage.novusStageDropdown(), NOVUS_STAGE_REVIEW_TEXT);
                    routingPage.showFeatureSelectionsLink().click();
                    routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                    routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
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
                routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                routingPage.saveChangesAndSignOnButton().click();
            } else if (user.getRouting().equals(Routing.FOLDERS)) {

                navigateToRoutingPage(user.getProduct());
                routingPage.showFeatureSelectionsLink().click();
                routingPage.waitForPageToLoad();
                routingPage.selectDropDownByVisibleText(routingPage.ignoreAuthorizationBlocksDropdown(), GRANT_ACCESS_OPTION_TEXT);
                routingPage.selectDropDownByVisibleText(routingPage.preReleaseContentDropdown(), GRANT_ACCESS_OPTION_TEXT);
                routingPage.selectDropDownByVisibleText(routingPage.wlnByPass100KAncillaryDropdown(), GRANT_ACCESS_OPTION_TEXT);
                routingPage.saveChangesAndSignOnButton().click();
            } else {
                navigateToHomePage(user.getProduct());
            }
        }
    }

    private void setAllContactsGroupSettings() {
        routingPage.showFeatureSelectionsLink().click();
        routingPage.selectDropDownByVisibleText(routingPage.enableAllContactsGroupDropdown(), GRANT_ACCESS_OPTION_TEXT);
    }

    private void enableAskContent() {
        LOG.info("Ask Content IAC is Enabled");
        routingPage.infrastructureAccessTextArea().sendKeys("IAC-ASK-CONTENT");
    }

    private void setAnonymousRegistrationKey(){
        routingPage.anonymousRegistrationKeyTextBox().sendKeys("1890639-SKKON3");
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
    protected void login(CobaltUser user) {
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

    /**
     * Do not delete this. Handles login flow for Open WEB user
     */
    protected void handleOpenWebFlow() {
        plcHomePage.flashScreenPL();
        wlnHeader.signInLink().click();
        wlnHeader.waitForPageToLoad();
        wlnHeader.waitForPageToLoadAndJQueryProcessing();
        if (wlnHeader.isSignInLinkPresent()) {
            handleOpenWebFlow();
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
                navigationCobalt.navigateToWLNSpecificURL(ROUTING_URL);
                break;
            case PLC:
                navigationCobalt.navigateToPLUKPlus(ROUTING_URL);
                break;
            case ANZ:
                navigationCobalt.navigateToPLCANZSpecificURL(ROUTING_URL);
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
                    clickSignOffSafely(product);
                    LOG.info("The user is signed off from PLC");
                    break;
                case ANZ:
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

    /**
     * New session is created. 1. Reset Routing 2. Sign-Off 3. Delete Cookies
     */
    protected void newSession(CobaltUser user) {
        signOff(user);
        onepassLogin.deleteAllCookies();
        LOG.info("New Session Created");
    }

    public void userRelogsIn() throws IOException {
        LOG.info("Current user relogs in");
        signOff(currentUser);
        onepassLogin.deleteAllCookies();
        CobaltUser plPlusUser = cloneCurrentUserObject();
        resetCurrentUser();
        loginUser(plPlusUser);
        onepassLogin.waitForPageToLoadAndJQueryProcessing();
        footerUtils.closeDisclaimerMessage();
        LOG.info("Current user has reloged in");
    }

    public void userLogsInWithUsername() {
        String userName = this.getCurrentUserName();
        onepassLogin.usernameTextField().sendKeys(userName);
        String password = getPasswordForPlPlusUser(userName);
        onepassLogin.passwordTextField().clear();
        onepassLogin.passwordTextField().sendKeys(password);
        onepassLogin.signOnButton().click();
        LOG.info("The user has logged in");
        logSessionID();
    }

    public void userLogsInBackAgainFromSignOffPage() {
        onePassLogoutPage.signOffPageSignOnButton().click();
        onepassLogin.waitForPageToLoad();
        userLogsInWithUsername();
        LOG.info("The user has logged back again from the signOff page");
    }

    public void plUserLoginAndOpenFolder(String userName, String folderName) throws IOException{
        LOG.info("The {} opens {} folder", userName, folderName);
        List<CobaltUser> cobaltUsers = new ArrayList<>();
        cobaltUsers.add(getCobaltUserForUserName(userName));
        plUserIsLoggedInWithFollowingDetails(cobaltUsers);
        userGoesToFolderSubFolder(folderName);
        LOG.info("The {} has opened the {} folder", userName, folderName);
    }

    public void userLoginAndOpenFolder(String userName, String folderName) throws IOException {
        LOG.info("The user {} logged in and opens a folder {}", userName, folderName);
        List<CobaltUser> cobaltUsers = new ArrayList<>();
        cobaltUsers.add(getCobaltUserForUserName(userName));
        plUserIsLoggedInWithFollowingDetails(cobaltUsers);
        userGoesToFolderSubFolder(folderName);
        LOG.info("The user {} has logined and opened a folder {}", userName, folderName);
    }

    private void userGoesToFolderSubFolder(String folderName) {
        LOG.info("The user is going to a folder subfolder");
        wlnHeader.clickHeaderLinkByName("Folders");
        openFolder(folderName);
        LOG.info("The user has gone to a folder subfolder");
    }

    private void openFolder(String folderName) {
        LOG.info("Open folder {}", folderName);
        foldersUtils.openFolder(folderName);
        LOG.info("{} has been opened", folderName);
    }

    @Given("^PL\\+ user '(.*)' navigates directly to document with guid '(.*)'$")
    public void plUserLoginAndNavigateToDoc(String userName, String docGuid) throws IOException {
        LOG.info("The PL+ user is navigating directly to the document with guid: {}", docGuid);
        plUserIsLoggedInWithFollowingDetails(getCobaltUserForUserNameAsList(userName));
        navigatesDirectlyToDocumentWithGuid(docGuid);
        LOG.info("The PL+ user has directly navigated to the document with guid: {}", docGuid);
    }

    private void navigatesDirectlyToDocumentWithGuid(String guid) {
        LOG.info("The user is navigating directly to the document with guid");
        navigationCobalt.navigateToPLUKPlus("/Document/" + guid + "/View/FullText.html");
        resourcePage.waitForPageToLoadAndJQueryProcessing();
        LOG.info("The user has navigated directly to the document with guid");
    }

    @Given("^PL\\+ user '(.*)' searches for '(.*)'$")
    public void plUserLoginAndSearch(String userName, String term) throws IOException {
        LOG.info("The PL+ user {} searches for {}", userName, term);
        plUserIsLoggedInWithFollowingDetails(getCobaltUserForUserNameAsList(userName));
        searchFor(term);
        LOG.info("The PL+ user {} found the term: {}", userName, term);
    }

    private void searchFor(String searchQuery) {
        LOG.info("Searching for {}", searchQuery);
        searchUtils.enterSearchText(searchQuery);
        searchHomePage.searchButton().sendKeys(Keys.ENTER);
        searchHomePage.waitForPageToLoad();
        searchHomePage.waitForPageToLoadAndJQueryProcessing();
        LOG.info("Found {}", searchQuery);
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

    public boolean isHomePage() {
        return categoryPage.getCurrentUrl().contains("/Search/Home.html")
                || categoryPage.getCurrentUrl().contains("/Search/BrowseRoot.html")
                || categoryPage.getCurrentUrl().contains("Home/Home");
    }

    public void userLogsInFromLoginPage(@Transpose List<CobaltUser> plPlusUserList) {
        CobaltUser plPlusUser = updateFieldsForPlPlusUser(plPlusUserList.get(0));
        login(plPlusUser);
        currentUser.setCurrentUser(plPlusUser);
    }

    public void userEntersUsernameAndPasswordOnLoginPage(@Transpose List<CobaltUser> plPlusUserList) {
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
        if (!StringUtils.isEmpty(mandatoryRouting) &&
                mandatoryRouting.toUpperCase().contentEquals("VALUE_FROM_COMMAND_LINE")) {
                if (!StringUtils.isEmpty(mandatoryRoutingCli)) {
                    mandatoryRouting = mandatoryRoutingCli.toUpperCase();
                } else {
                    mandatoryRouting = "NO";
                }
        }
        if (!BooleanUtils.toBoolean(System.getProperty(ROUTING))
                && (StringUtils.isEmpty(mandatoryRouting) || mandatoryRouting.equals("NO"))) {
            plPlusUser.setRouting(Routing.NONE);
        }
    }

    //Cannot verify without exception if user menu is clickable or overlapped by some popup
    private void clickSignOffSafely(Product product) {
        try {
            wlnHeader.signOff();
        } catch (WebDriverException nse) {
            LOG.info("Sign-off link not found - navigating to the home page and trying again");
            navigateToHomePage(product);
            wlnHeader.signOff();
        }
        LOG.info("The user is signed off from {}", product);
    }

    private void logSessionID() {
        String sessionId = StringUtils.defaultIfBlank(getSessionIDFromUI(), "user is not on PLC page");
        LOG.info("Session ID : {}", sessionId);
    }
}