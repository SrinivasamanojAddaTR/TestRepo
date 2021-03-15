package com.thomsonreuters.pageobjects.pages.header;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.PageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WLNHeader extends AbstractPage {

    private PageActions pageActions;

    private final String USER_ICON = "//*[contains(@id,'website_signOffRegion')]";
    private final String FFH_DROPDOWN= "//*[contains(@id,'%sContainer')]//*[@class='co_hasTooltip co_dropdownArrowCollapsed']";
    private static final String LOGO_BY_PRODUCT_XPATH = "//a[@id='logo']//img[@alt='%s']";
    private final String RECENT_CLIENTID_LINK_PATTTERN= "//ul[@id='co_clientID_recents']//*[contains(text(),'%s')]";

    public WLNHeader() {
        pageActions = new PageActions();
    }

    public WebElement signInLink() {
        return waitForExpectedElement(By.id("coid_website_signOffRegion"), 30);
    }

    public WebElement signOutLink() {
        return waitForExpectedElement(By.linkText("Sign out"));
    }

    public WebElement signInWithDifferentAccountLink() {
        return waitForExpectedElement(By.linkText("Sign in with a Different Account"), 30);
    }

    public WebElement pageHeaderLabel() {
        return waitForExpectedElement(By.id("co_browsePageLabel"),20);
    }

    public boolean isBrowseHeaderWithinPageHeader() {
        return isExists(By.xpath("//div[@id='co_pageHeader']/div[contains(@class,'co_browseHeader')]"));
    }

    public WebElement header() {
        return waitForExpectedElement(By.id("header"));
    }

    public WebElement headerWidget() {
        return waitForExpectedElement(By.id("co_headerWrapper"));
    }

    public WebElement companyLogo() {
        return waitForExpectedElement(By.id("coid_website_logo"));
    }

    /*
    * for phase2
     */
    public WebElement logo() {
        return waitForExpectedElement(By.id("logo"));
    }

    public WebElement folderViewAllLink() {
        return waitForExpectedElement(By.id("co_recentFoldersLink"));
    }

    public By menuByLink() {
        return By.id("menuLink");
    }

    public WebElement historyLink() {
        return waitForExpectedElement(By.linkText("History"), 10);
    }

    public WebElement favouritesLink() {
        return waitForExpectedElement(By.linkText("Favourites"), 10);
    }

    public WebElement historyArrowLink() {
        return waitForExpectedElement(By.linkText("Recent History"));
    }

    public WebElement historyRecentDocSubTitle() {
        return waitForElementVisible(By.xpath("//h3[@class='co_recentDocumentsHeader' and contains(text(),'Recent Documents')]"));
    }

    public WebElement historyRecentSearchesSubTitle() {
        return waitForElementVisible(By.xpath("//h3[@class='co_recentSearchesHeader' and contains(text(),'Recent Searches')]"));
    }

    public WebElement historySearchViewAllLink() {
        return waitForElementVisible(By.id("co_recentSearchesLink"));
    }

    public By historySearchViewAllByLink() {
        return By.id("co_recentSearchesLink");
    }

    public WebElement historyDocViewAllLink() {
        return waitForElementVisible(By.id("co_recentDocumentsLink"));
    }

    public WebElement foldersLink() {
        return waitForExpectedElement(By.linkText("Folders"),10);
    }

    public WebElement foldersArrowLink() {
        return waitForElementVisible(By.xpath("//li[@id='co_recentFoldersContainer']//a[contains(@title,'Recent Folders')]"));
    }

    public WebElement foldersRecentFoldersSubTitle() {
        return waitForElementVisible(By.xpath("//div[@id='co_recentFolders']//h3[text()='Recent Folders']"));
    }

    public List<WebElement> foldersRecentFoldersLinks() {
        return waitForElementsVisible(By.xpath("//ul[@id='co_recentFoldersList']//li//a"));
    }

    public By foldersRecentFoldersByLink() {
        return By.xpath("//ul[@id='co_recentFoldersList']//li//a");
    }

    public WebElement alertsLink() {
        return waitForExpectedElement(By.xpath("//div[@id='alerts-dropdown']//a[text()=' Alerts']"));
    }

    public WebElement alertsCenterSubLink() {
        return waitForExpectedElement(By.xpath("//li[@id='user_alerts']//a[text()='Alerts Center']"));
    }

    public WebElement usernameLink() {
        return waitForExpectedElement(By.xpath("//li[@id='user_preferences']"));
    }

    public By usernameByLink() {
        return By.xpath("//div[@id='preferences-dropdown']//a[contains(@title,'preferences')]");
    }

    public WebElement editProfileSubLink() {
        return waitForElementVisible(By.xpath("//ul[@class='pluk-dropdownOpen']//a[text()='Edit Profile']"));
    }

    public WebElement signOutSubLink() {
        return waitForExpectedElement(By.xpath("//div[@class='co_dropDownMenuContent']//a[text()='Sign out']"));
    }

    public WebElement userNamePopup() {
        return waitForExpectedElement(By.xpath("//li[@id='user_preferences']//ul[@class='co_dropDownMenuList']"));
    }

    public List<WebElement> topRightCornerLinks() {
        return waitForExpectedElements(By.xpath("//ul[@id='co_globalNav']//li//a"));
    }

    public WebElement historyHeadingTitle() {
        return waitForElementVisible(By.xpath("//h1[@class='co_historyTitle']"));
    }

    public By historyHeadingByTitle() {
        return By.xpath("//h1[@class='co_historyTitle']");
    }

    public WebElement folderHeadingTitle() {
        return waitForElementVisible(By.xpath("//h1[@class='co_folderTitle']"));
    }

    public WebElement alertsHeadingTitle() {
        return waitForExpectedElement(By.xpath("//div[@id='co_alertCenter']//h2[text()='Alerts']"));
    }

    public WebElement alertsPopup() {
        return waitForExpectedElement(By.xpath("//li[@id='user_alerts']//ul[@class='co_dropDownMenuList']"));
    }

    public WebElement usernameIcon() {
        return waitForExpectedElement(By.xpath("//li[@id='user_preferences']//span[contains(@class,'th_flat_icon icon_user')]"));
    }

    public WebElement browseMenuButton() {
        return waitForExpectedElement(By.id("browseMenu"),10);
    }
    /**
     * for phase2
     */
    public WebElement buttonBrowseMenu() {
        return waitForExpectedElement(By.xpath("//button[contains(.,'Browse menu')]"));
    }

    public WebElement browseMenuPopup() {
        return waitForExpectedElement(By.id("content-areas"));
    }

    public List<WebElement> browseMenuSubMenuList() {
        return waitForExpectedElements(By.xpath("//div[@id='main-menu']/ul/li"));
    }

    public List<WebElement> practiceAreaFirstColumnLinks() {
        return waitForExpectedElements(By.xpath("//div[@id='Practice areas0']//ul//li"));
    }

    public List<WebElement> resourcesFirstColumnLinks() {
        return waitForExpectedElements(By.xpath("//div[@id='Resources0']//ul//li"));
    }

    public List<WebElement> internationalFirstColumnLinks() {
        return waitForExpectedElements(By.xpath("//div[@id='International0']//ul//li"));
    }

    public WebElement searchGuideIcon() {
        return waitForExpectedElement(By.xpath("//div[@class='co_textarea']//a[contains(@class,'co_scopeIcon')]"));
    }
    
    public boolean isCategoryPageLinkOnWLUKPresent(String categoryPageName) {
        return isExists(By.xpath("//*[@class='co_dropDownMenuContent']//*[contains(.,'" + categoryPageName + "')]"));
    }
    
    public WebElement morePopUpDropdown() {
        return waitForExpectedElement(By.xpath("//button[@class='co_dropDownButton' and contains(., 'More')]"));
    }

    public boolean isSearchGuideIconDisplayed() {
        return isElementDisplayed(By.xpath("//div[@class='co_textarea']//a[contains(@class,'co_scopeIcon')]"));
    }

    public WebElement searchGuideCloseButton() {
        return waitForExpectedElement(By.xpath("//div[contains(@class,'co_infoBox')]//a[text()='Close']"));
    }
    
    public WebElement clientIDLink(){
    	return waitForExpectedElement(By.xpath("//*[@id='co_clientIdContainer']//*[contains(@class, 'co_dropdownArrow')]"));
    }

    public WebElement clientIdValue(){
        return waitForExpectedElement(By.xpath("//*[@id='co_clientIdContainer']//*[@title='Client ID']"));
    }

    public WebElement clientIdLink(String defaultClientID) {
        expandUserAvatarDropDown();
        return waitForExpectedElement(By.linkText(defaultClientID));
    }

    public void closePrivacyNoticePopup() {
        By privacyPolicySelector = By.cssSelector(".icon25.icon_close_x");
        if (isElementDisplayed(privacyPolicySelector)) {
            waitForExpectedElement(privacyPolicySelector).click();
            waitForElementToDissappear(privacyPolicySelector);
        }
    }

    public WebElement clientIdLink() {
        expandUserAvatarDropDown();
        return waitForExpectedElement(By.id("co_clientID_recent_0"));
    }

    public WebElement clientIdLinkInPopUp(String clientID) {
        return waitForExpectedElement(By.linkText(clientID));
    }

    public List<WebElement> recentClientIdLinks() {
        return waitForExpectedElements(By.xpath("//ul[@id='co_clientID_recents']/li/a"));
    }

    public WebElement recentClientIdList() {
        return waitForExpectedElement(By.id("co_clientID_recents"));
    }

    public boolean isRecentClientIdListPresent(){
        return isExists(By.id("co_clientID_recents"));
    }
    
    public WebElement recentClientIDValueLink(String clientID){
    	return waitForExpectedElement(By.xpath(String.format(RECENT_CLIENTID_LINK_PATTTERN, clientID)));
    }
    
    public boolean isRecentClientIDValueLinkDisplayed(String clientID){
    	return isElementDisplayed(By.xpath(String.format(RECENT_CLIENTID_LINK_PATTTERN, clientID)));
    }

    public WebElement clientIdInput() {
        return waitForExpectedElement(By.id("co_clientIDTextbox"));
    }

    public boolean isClientIdPresent(){
    	return isExists(By.id("co_clientIDTextbox"));
    }
    
    public boolean isClientIdPopUpPresent(){
    	return isElementDisplayed(By.xpath("//*[@id='co_clientID_content_1']//form"));
    }
    
    public String isClientIdValuePresent(){
    	String value = "";
    	try {
    		value = ((JavascriptExecutor) getDriver).executeScript("return Cobalt.User.ClientId;").toString();
		} catch (Exception e) {
			LOG.error(String.format("Error getting the client id. Error message: %s", e.getMessage()));
		}
    	return value;
    }
    
    public WebElement clientIdDropdown(){
    	return waitForExpectedElement(By.id("co_clientIDRecentsDropdown"));
    }

    public WebElement clientIdPopUpCloseButton(){
    	return waitForExpectedElement(By.id("co_clientId_lightbox_closeButton"));
    }
    
    public WebElement clientIdDropdownOption(String clientId){
    	return waitForExpectedElement(By.xpath("//*[@id='co_clientIDAutocomplete']//a[contains(text(),'"+ clientId +"')]"));
    }
    
    public boolean isClientIDRecentDropdownOptionPresent(){
    	return isExists(By.id("co_clientIDAutocomplete"));
    }
    
    public WebElement clientIdChangeButton() {
        return waitForExpectedElement(By.id("co_clientIDContinueButton"));
    }

    public WebElement clientIdAutocomplete(String clientID) {
        return waitForExpectedElement(By.xpath("//ul[@id='co_clientIDAutocomplete']//a[text()='" + clientID + "']"));
    }

    public boolean isClientIdAutocompleteDisplayed(String clientID) {
        return !findElements(By.xpath("//ul[@id='co_clientIDAutocomplete']//a[text()='" + clientID + "']")).isEmpty();
    }

    public WebElement clientIdCloseButton() {
        return waitForExpectedElement(By.id("co_clientID_close_0"));
    }

    public void changeClientID(String clientID) {
        clientIdInput().clear();
        clientIdInput().sendKeys(clientID);
        if(isClientIdAutocompleteDisplayed(clientID)) {
            clientIdAutocomplete(clientID).click();
        }
        clientIdInput().click();
        clientIdChangeButton().click();
        waitForPageToLoadAndJQueryProcessing();
    }
    
    public void changeClientIDandClickEnter(String clientID) {
        clientIdInput().clear();
        clientIdInput().sendKeys(clientID);
        if(isClientIdAutocompleteDisplayed(clientID)) {
            clientIdAutocomplete(clientID).click();
        }
        clientIdInput().click();
        pageActions.keyPress(Keys.ENTER);
        waitForPageToLoadAndJQueryProcessing();
    }
    
    public boolean isFavoritesLinkPresent() {
    	return isElementDisplayed(By.linkText("Favourites"));
    }
       
    public boolean isHistoryLinkPresent() {
    	return isElementDisplayed(By.linkText("History"));
    }
    
    public boolean isFoldersLinkPresent() {
    	return isElementDisplayed(By.linkText("Folders"));
    }

    public boolean isDefaultClientIdLinkPresent(String defaultClientID) {
    	if(isElementDisplayed(By.xpath(USER_ICON))){
    		expandUserAvatarDropDown();
    		return isElementDisplayed(By.linkText(defaultClientID));
    	}
    	else{
    		return false;
    	}
    	
    }
    public boolean isSignInLinkPresent() {
        return isElementDisplayed(By.id("coid_website_signOffRegion"));
    }

    public boolean isSignInLinkPresentWithoutWait() {
        return isExists(By.id("coid_website_signOffRegion"));
    }

    public WebElement expandedUserPreferencesDropDownMenu() {
        return waitForExpectedElement(By.xpath("//li[@id='user_preferences']//div[@class='co_dropDownMenu th_flat js_dropDownMenu expanded']"));
    }

    public boolean isExpandedUserPreferencesDropDownMenu() {
    	return isElementDisplayed(By.xpath("//li[@id='co_signOffContainer']/div[contains(@class,'globalNavDropdownBox')]"));
    }

    public void signInWithDifferentAccount() {
        if (!isExpandedUserPreferencesDropDownMenu()) {
            expandUserAvatarDropDown();
        }
        signInWithDifferentAccountLink().click();
    }

    public void openEmailPreferences() {
        if (!isExpandedUserPreferencesDropDownMenu()) {
            expandUserAvatarDropDown();
        }
        waitForExpectedElement(By.linkText("Email preferences")).click();
    }

    public void signOff() {
        if (!isExpandedUserPreferencesDropDownMenu()) {
            expandUserAvatarDropDown();
        }
        waitForPageToLoadAndJQueryProcessing();
        signOutLink().click();
        waitForPageToLoadAndJQueryProcessing();
    }

    public void goToUpdateExistingProfilePage() {
        if (!isExpandedUserPreferencesDropDownMenu()) {
            expandUserAvatarDropDown();
        }
        editProfileLink().click();
    }

    public WebElement editProfileLink() {
        return findElement(By.linkText("Edit profile"));
    }
    public WebElement practicalLawLogo() {
        return waitForExpectedElement(By.xpath("//a[@id='logo']//img[@alt='Practical Law']"));
    }

    public WebElement westLawLogo() {
        return waitForExpectedElement(By.xpath("//a[@id='logo']//img[@alt='Westlaw UK']"));
    }

    public WebElement commentaryLogo() {
        return waitForExpectedElement(By.xpath("//a[@id='logo']//img[@alt='Books']"));
    }
    public boolean isEditProtfileLinkPresent() {
    	return isElementDisplayed(By.linkText("Edit profile"));
    }

    public void myFastDraft() {
        waitForPageToLoad();
        waitForExpectedElement(By.xpath(USER_ICON));
        expandUserAvatarDropDown();
        waitForExpectedElement(By.xpath("//a[text()='My Fast Draft']")).click();
        waitForPageToLoad();
    }

    public void checkMyFastDraftAbsents() {
    	if(isElementDisplayed(By.xpath(USER_ICON))){
    		pageActions.mouseOver(getDriver.findElement(By.xpath(USER_ICON)));
    	}
    	waitForElementToDissappear(By.xpath("//a[text()='My Fast Draft']"));
    }

    public void expandUserAvatarDropDown() {
        if(isExpandedUserPreferencesDropDownMenu()) {
            return;
        }
        userAvatarIcon().click();
    }
    
    public boolean isErrorMessagePresent(String errorMessage){
    	return isElementDisplayed(By.xpath("//*[contains(text(),'"+ errorMessage +"')]"));
    }

    public WebElement userAvatarIcon() {
        return waitForExpectedElement(By.xpath(USER_ICON), 3);
    }
    
    public boolean isUserAvatarIconPresent() {
        return isExists(By.xpath(USER_ICON));
    }

    public WebElement userPreferencesDropdown(String selectLink) {
        return waitForExpectedElement(By.xpath("//div[@class='co_dropDownMenuContent']//a[text()='" + selectLink + "']"));
    }

    /**
     * For the L.H.S characters list in the Connectors
     */
    public List<WebElement> connectorsCharactersList() {
        return waitForExpectedElements(By.xpath("//dl[@id='co_search_advancedSearch_tncLegendList']//dt"));
    }

    /**
     * For the R.H.S explanation list in the Connectors
     */
    public List<WebElement> connectorsExplanationList() {
        return waitForExpectedElements(By.xpath("//dl[@id='co_search_advancedSearch_tncLegendList']//dd"));
    }

    /**
     * Heading for the email preferences page
     */
    public WebElement emailPreferencesHeading() {
        return waitForExpectedElement(By.xpath("//div[@id='co_pageHeader']//h1"));
    }

    /**
     * Heading for the by email preferences page
     */
    public By emailPreferencesByHeading() {
        return By.xpath("//div[@id='co_pageHeader']//h1");
    }

    /**
     * Country Toggle Dropdown Link
     */
    public WebElement countryToggleDropdownLink() {
        return waitForExpectedElement(By.xpath("//div[@id='regions-dropdown']//a"));
    }

    public boolean isCountryToggleDropdownLinkDisplayed() {
        return isElementDisplayed(By.xpath("//div[@id='regions-dropdown']//a"));
    }

    /**
     * Country Toggle Dropdown Link
     */
    public List<WebElement> countryDropdownMenuLinks() {
        return waitForExpectedElements(By.xpath("//div[contains(@class,'dropDownMenuContent') and contains(.,'Global')]//ul/li/a"));
    }

    public WebElement countryLink(String countryName) {
        return waitForExpectedElement(By.linkText(countryName));
    }

    /**
     * Get element list with all links of selected section in browse menu
     *
     * @return List with elements
     */
    public List<WebElement> getActiveSectionLinks() {
        return waitForExpectedElements(By.xpath("//div[contains(@class, 'children') and not(contains(@class, 'hide'))]//a"));
    }

    /**
     * Browse drop-down menu by element
     */
    public By browseMenuByPopup() {
        return By.id("content-areas");
    }

    /**
     * Get the drop-down element on Time-Zone popup
     *
     * @return WebElement
     */
    public WebElement timeZoneSelectDropdown() {
        return waitForExpectedElement(By.id("coid_userSettingsTimeZoneOption"));
    }

    /**
     * Get the Save button element on Time-Zone popup
     *
     * @return WebElement
     */
    public WebElement timeZoneSaveButton() {
        return waitForExpectedElement(By.id("coid_userSettingsSave"));
    }

    /**
     * Get the Close button element on Time-Zone popup
     *
     * @return WebElement
     */
    public WebElement timeZoneCloseButton() {
        return waitForExpectedElement(By.id("coid_userSettingsSave"));
    }

    /**
     * Get the time-zone by popup
     *
     * @return WebElement
     */
    public By timeZoneByPopup() {
        return By.id("co_userSettingsTabPanels");
    }

    public WebElement compartmentToggleDropDownArrow() {
        return waitForExpectedElement(By.xpath("//button[contains(@class,'co_dropDownAnchor') and @title='Switch product']"));
    }
    
    public WebElement toggleDropDownArrow() {
        return waitForExpectedElement(By.xpath(USER_ICON + "//a[contains(@class,'co_dropDownAnchor')]"));
    }

    public WebElement compartmentToggleDropDownLink(String prod) {
        return waitForExpectedElement(By.xpath("//ul[@class='co_dropDownMenuList']//li//a[text()='"+prod+"']"));
    }

    public boolean isLogoPresent(String prodName) {
        return isElementDisplayed(By.xpath(String.format(LOGO_BY_PRODUCT_XPATH, prodName)));
    }

    public WebElement getLogo(String prodName) {
        return waitForExpectedElement(By.xpath(String.format(LOGO_BY_PRODUCT_XPATH, prodName)));
    }

    public By compartmentToggleDropDownByLink(String prod) {
        return By.xpath("//div[@id='navigation_left']//ul[@class='co_dropDownMenuList']//a[text()='"+prod+"']");
    }

    public WebElement compartmentDropDownDisplayName() {
        return waitForExpectedElement(By.xpath("//button[contains(@class,'co_dropDownAnchor') and @title='Switch product']"));
    }

    public List<WebElement> infoDropListItems(){
        return waitForExpectedElements(By.xpath("//*[@id='co_search_advancedSearch_tncLegendList']/dd"));
    }

    public WebElement infoDropList(){
        return waitForExpectedElement(By.className("co_infoBox_message"));
    }

    public boolean isInfoDropListDisplayed(){
        return isElementDisplayed(By.className("co_infoBox_message"));
    }

    public WebElement infoDropListHeader(){
        return waitForExpectedElement(By.cssSelector(".co_infoBox_message > h2"));
    }

    public By practicalLawByLogo() {
        return By.xpath("//a[@id='logo']//img[@alt='Practical Law']");
    }

    public By westLawByLogo() {
        return By.xpath("//a[@id='logo']//img[@alt='Westlaw UK']");
    }

    public By commentaryByLogo() {
        return By.xpath("//a[@id='logo']//img[@alt='Library']");
    }

    public WebElement firmCentralUserMenuToggle() {
        return waitForExpectedElement(By.xpath("//a[@id='userMenuToggle']"));
    }

    public boolean isViewingLabelDisplayed(){
        return isElementDisplayed(By.xpath("//label[@id='compartment_switching_label']"));
    }

    public WebElement viewingLabel(){
        return waitForExpectedElement(By.xpath("//label[@id='compartment_switching_label']"));
    }
    
    public boolean isFFHPopUpPresent(String title){
    	return isElementDisplayed(By.xpath("//div[@class='co_dropdownBoxExpanded']//*[contains(text(),'" + title + "')]"));
    }
    
    public WebElement favouriteFoldersHistoryDropDownArrow(String link){
    	return waitForExpectedElement(By.xpath(String.format(FFH_DROPDOWN, link)));
    }
    
    public boolean isFavouriteFoldersHistoryDropDownArrowDisplayed(String link){
    	return isElementDisplayed(By.xpath(String.format(FFH_DROPDOWN, link)));
    }

    public WebElement myHomeLink(){
        return waitForExpectedElement(By.xpath("//*[@id='co_myHome']/a"));
    }

    public boolean isMyHomeLinkPresent(){
        return isElementDisplayed(By.xpath("//*[@id='co_myHome']/a"));
    }

    public WebElement alertsLinkWLUK() { return waitForExpectedElement(By.linkText("Alerts"), 10); }
    
	public void clickHeaderLinkByName(String linkName) {
		waitForPageToLoad();
		waitForPageToLoadAndJQueryProcessing();
		switch (linkName) {
		case "Folders":
			foldersLink().click();
			break;
		case "History":
			historyLink().click();
			break;
		case "Favourites":
			favouritesLink().click();
			break;
        case "Alerts":
            alertsLinkWLUK().click();
            break;
		default:
			throw new UnsupportedOperationException("The linkname '" + linkName
					+ "' is undefined. Only Folders, History, Favourites and Alerts page were identified. Please add your page with expected result");
		}
		waitForPageToLoad();
		waitForPageToLoadAndJQueryProcessing();
	}
}