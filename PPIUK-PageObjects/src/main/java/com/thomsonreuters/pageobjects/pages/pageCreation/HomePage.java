package com.thomsonreuters.pageobjects.pages.pageCreation;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AbstractPage {

    public enum PlukTab {
        PRACTICE_AREAS("Practice areas"),
        RESOURCES("Resources"),
        INTERNATIONAL("International");

        private String tabName;

        PlukTab(String tabName) {
            this.tabName = tabName;
        }

        public String getTabName() {
            return tabName;
        }
    }

    private static final By MEET_THE_TEAM_LINK = By.xpath("//*[@id='categoryPageScopeLinks']/a | //*[@class='browseHeader-meetTeamButton']");
    public WebElement browseMenuPopUp() {

        return waitForExpectedElement(By.xpath("//div[@class='browseMenu_container']"),20);
    }

    public WebElement browseMenuLink(String linkText) {

        return waitForExpectedElement(By.xpath("//span[@class='co_floatLeft'][text()=\"" + linkText + "\"]/parent::a"),30);
    }

    public WebElement menuColumnLink(String linkText) {
        // \" to deal with What's Market having an apostrophe
        return waitForExpectedElement(By.xpath("//div[@class='menu-item-children co_2Column']//a[text()=\"" + linkText + "\"]"),30);
    }

    public WebElement homepageTabHeadingLink(String linkText) {
        // \" to deal with What's Market having an apostrophe
        return waitForExpectedElement(By.xpath("//a[@class='co_tabLink'][text()=\"" + linkText + "\"]"),30);
    }

    public WebElement homepageTabLink(String linkText) {

        return waitForExpectedElement(By.xpath("//div[contains(@id,'categoryBoxTabPanel')][contains(@class,'co_tabShow')]//a[text()=\"" + linkText + "\"]"),30);
    }

    public WebElement practiceAreasTab() {
        return findElement(By.id("coid_categoryTab1_main_0"));
    }

    public WebElement resourcesTab() {
        return waitForExpectedElement(By.id("coid_categoryTab2_main_0"));
    }

    public WebElement internationalTab() {
        return waitForExpectedElement(By.id("coid_categoryTab3_main_0"));
    }

    public WebElement practiceAreasLink() {
        return waitForExpectedElement(By.linkText("Practice areas"));
    }

    public WebElement resourcesLink() {
        return waitForExpectedElement(By.linkText("Resources"));
    }

    public WebElement internationalLink() {
        return waitForExpectedElement(By.linkText("International"));
    }

    public WebElement askAQuestionLink() {
        return waitForExpectedElement(By.linkText("Ask a question"));
    }

    public WebElement whatsMarketLink() {
        return waitForExpectedElement(By.id("coid_what_s_market"));
    }

    public WebElement activeTab() {
        return waitForExpectedElement(By.cssSelector("ul[class='co_tabs co_categoryTabs'] li[class*='co_tabLeft co_tabActive']"));
    }

    public WebElement activeLink() {
        return waitForExpectedElement(By.cssSelector("a[class='browseMenu_parent menuSelected']"));
    }

    public WebElement getPracticeAreasBrowseMenuContainer() {
        return waitForExpectedElement(By.cssSelector("div[id='main-menu'] ul li[id='Practice areas'] div[id='menu-item-children']"));
    }

    private List<WebElement> getPracticeAreasWebElementLinks() {
        return waitForExpectedElements(By.xpath("//div[@id='coid_categoryBoxTabPanel1']//a"));
    }

    private List<WebElement> getTopicWebElementLinks() {
        return waitForExpectedElements(By.cssSelector("div[class='co_scrollWrapper co_categoryBoxTabContents'] div[class*='co_tabHide co_tabShow'] a"));
    }

    public List<String> getPracticeAreasLinks() {
        return getOnlyLinkNamesFromWebElementList(getPracticeAreasWebElementLinks());
    }

    public List<String> getTopicsLinks() {
        return getOnlyLinkNamesFromWebElementList(getTopicWebElementLinks());
    }

    public List<String> getPracticeAreasLinksInBrowseMenu() {
        return getOnlyLinkNamesFromWebElementList(getPracticeAreasWebElementLinksInBrowseMenu());
    }

    public List<String> getLinksInBrowseMenu(String subMenuLink) {
        return getOnlyLinkNamesFromWebElementList(getWebElementLinksInBrowseMenu(subMenuLink));
    }
    private List<WebElement> getPracticeAreasWebElementLinksInBrowseMenu() {
        //return mergeTwoWebElementList(By.cssSelector("div[id='Practice areas0'] ul li a"), By.cssSelector("div[id='Practice areas1'] ul li a"));
        return waitForExpectedElements(By.xpath("//li[@id='Practice areas']//a"),30);
    }

    private List<WebElement> getWebElementLinksInBrowseMenu(String subMenuLink) {
        //return mergeTwoWebElementList(By.cssSelector("div[id='Practice areas0'] ul li a"), By.cssSelector("div[id='Practice areas1'] ul li a"));
        return waitForExpectedElements(By.xpath("//li[@id='" + subMenuLink + "']//a"),30);
    }

    public List<String> getLinksInHomepageMainMenu() {
        return getOnlyLinkNamesFromWebElementList(getWebElementLinksInHomepageMainMenu());
    }

    private List<WebElement> getWebElementLinksInHomepageMainMenu() {
        return waitForExpectedElements(By.xpath("//div[@class='co_scrollWrapper " +
                "co_categoryBoxTabContents']//div[contains(@class,'co_tabShow')]//a"),30);
    }

    public void openMeetTheTeamPage(){
        getMeetTheTeamLink().click();
    }

    public WebElement getMeetTheTeamLink(){
        return waitForExpectedElement(By.cssSelector("#categoryPageScopeLinks"));
    }

    private List<String> getOnlyLinkNamesFromWebElementList(List<WebElement> elementList) {
        String currentLink;
        List<String> links = new ArrayList<String>();
        for (WebElement webElementLink : elementList) {
            currentLink = webElementLink.getText();
            //System.out.println("The menu contains the link: " + currentLink);
            links.add(currentLink);
        }
        return links;
    }

    public WebElement homepageLink(String homepageMenuLink) {
        return waitForExpectedElement(By.xpath("//div[contains(@class,'co_tabShow')]//li//a[text()=\""+homepageMenuLink+"\"]"),30);
    }

    public WebElement tabLinkAfterHeader (String headingText,String linkText) {
        return waitForExpectedElement(By.xpath("//h3[contains(.,'" + headingText + "')]/parent::div/parent::div//a[text()=\""+linkText+"\"]"),30);
    }

   public WebElement browseTabLinkAfterHeader (String headingText,String linkText) {
        return waitForExpectedElement(By.xpath("//ul[@id='browseMenu_list']//div[@class='menuBarSectionDisplayName'][text()=\"" + headingText + "\"]/following-sibling::div//a[text()=\"" + linkText + "\"]"),30);
    }

    public List <WebElement> recentHistory () {
        return waitForExpectedElements(By.xpath("//div[@id='co_recentDocuments']//ul//li"),5);
    }

    public WebElement recentHistoryHeader (){
        return waitForExpectedElement(By.xpath("//div/span[@class='center co_title ng-binding'][text()='Recent history']"));
    }

    public WebElement recentHistoryNoBrowse () {
        return waitForExpectedElement(By.xpath("//div[@class='recentlyViewedNoDocs ng-isolate-scope']"));
    }

    public WebElement recentHistoryNoBrowseText () {
        return waitForExpectedElement(By.xpath("//div[@class='recentlyViewedNoDocs ng-isolate-scope']/span[@class='ng-binding'][text()='You have not browsed content recently.']"));
    }

    public WebElement legalUpdatesContentLink() {
        return waitForExpectedElement(By.id("coid_legal_updates"));
    }

    /**
     * Selects the Resource tab present on Home page.
     */
    public void selectResourceTab() {
        waitForExpectedElement(By.linkText("Resources")).click();
    }

    /**
     * Selects the International tab present on Home page.
     */
    public void selectInternationalTab() {
        waitForExpectedElement(By.linkText("International")).click();
    }

    /**
     * This method selects the link by given linkname.
     *
     * @param linkName
     */
    public void selectLinkPresentOnTab(String linkName) {
        waitForExpectedElement(By.linkText(linkName)).click();
    }

    public WebElement selectAccessNowButtonForOpenWebBrowsing() {
        return waitForExpectedElement(By.linkText("Access now"));
    }

    public WebElement specificTab(String tabName) {
        return waitForExpectedElement(By.xpath("//a[@class='co_tabLink' and text()='" + tabName + "']"));
    }

    public WebElement specificTab(PlukTab tab) {
        return waitForExpectedElement(By.xpath("//a[@class='co_tabLink' and text()='" + tab.getTabName() + "']"));
    }
    
    public List<WebElement> allTabs() {
        return waitForExpectedElements(By.xpath("//a[@class='co_tabLink']"));
    }

    public WebElement homePageStartComparingButton() {
        return waitForElementPresent(By.id("coid_start_comparing"));
    }

    public List<WebElement> selectQuestionsPageCheckboxList() {
        return waitForExpectedElements(By.xpath("//div[contains(@class,'co_comparisonTool')]//li//input[@type='checkbox']"));
    }

    public WebElement selectQPageSelectJurisdictionButton() {
        return waitForElementPresent(By.xpath("//div[@class='co_actionBtns']//a[normalize-space(.)='Select Jurisdictions']"));
    }

    public WebElement selectJurisdictionCheckbox(String countryName) {
        return waitForExpectedElement(By.xpath("//label[normalize-space(.)='" + countryName + "']//input"));
    }

    public WebElement jurisdictionPageCompareButton() {
        return waitForElementPresent(By.xpath("//div[@class='co_actionBtns']//a[normalize-space(.)='Compare']"));
    }

    public List<WebElement> comparePagCountryHeadingsList() {
        return waitForExpectedElements(By.xpath("//div[@id='co_docContentBody']//div[@class='co_headtext ng-binding']"));
    }

    public WebElement comparePageCountryEditButton() {
        return waitForExpectedElement(By.id("jurisdictionEditButtonLink"));
    }

    public WebElement editPopupSaveChangesButton() {
       // return waitForExpectedElement(By.xpath("//div[@id='jurisdictionsPopup']//button[text()='Save Changes']"));
        return waitForExpectedElement(By.xpath("//div[@class='co_actionBtns']/a[contains(text(),'Save Changes')]"));
    }

    public WebElement comparePageLeftColumnCountryNameLink(String countryName) {
        return waitForExpectedElement(By.xpath("//ul[@class='kh_list']//input[@value='" + countryName + "']"));
    }

    public WebElement selectTopicPageTopicLink(String topicNameName) {
        return waitForExpectedElement(By.xpath("//div[@class='co_2Column']//li//a[normalize-space(.)='" + topicNameName + "']"));
    }

    public WebElement tabSubFeatureHeadings(String heading) {
        return waitForElementPresent(By.xpath("//h3[contains(.,'" + heading + "')]"));
    }

    public List<WebElement> getSelectedSectionLinks() {
        return waitForExpectedElements(By.cssSelector(".co_featureBoxInner a"));
    }

    /**
     * Get element list with all links of currently selected tab
     *
     * @return List with tab links
     */
    public List<WebElement> getActiveTabLinks() {
        return waitForExpectedElements(By.cssSelector(".co_categoryBoxTabContents > .co_tabShow a"));
    }

    /**
     * Get element for BrowseMenu International Link
     *
     * @return WebElement with tab links
     */
    public WebElement countryBrowseMenuLink(String country) {
        return waitForExpectedElement(By.xpath("//a[@class='menu-item-link' and contains(text(),'" + country + "')]"));
    }

    /**
     * Get element for country on HomePage International tab
     *
     * @return WebElement with tab links
     */
    public WebElement countryLink(String country) {
        return waitForExpectedElement(By.xpath("//a[contains(@id,'coid_" + country + "')]"));
    }

    /**
     * Get country label element for Global PL Country site
     *
     * @return WebElement with tab links
     */
    public WebElement globalPLCountrySiteLabel(String country) {
        return waitForExpectedElement(By.xpath("//div[@id='top-level']//span[text()='" + country + "']"));
    }


    /**
     * Get country link on Browse Menu International sub-menu for International subscription
     *
     * @return WebElement with tab links
     */
    public WebElement internationalSubCountryLink(String country) {
        return waitForExpectedElement(By.xpath("//div[@id='International1']//a[contains(text(),'" + country + "')]"));
    }

    /**
     * Get country header element for PL legacy China (etc.) site
     *
     * @return WebElement with tab links
     */

    public WebElement legacyPLCountryTitle(String country) {
        return waitForExpectedElement(By.xpath("//div[@class='page-heading']//h1[normalize-space(text())='" + country + "']"));
    }

    /**
     * Get link from currently active tab (Practice Area, Resource, ...)
     *
     * @param linkText Link text
     * @return Element with expected link
     */
    public WebElement getActiveTabLink(String linkText) {
        String escapedLinkText = Quotes.escape(linkText);
        return waitForExpectedElement(
                By.xpath("//div[contains(@class, 'categoryBoxTabContents')]//div[contains(@class, 'Show')]//a[contains(., " + escapedLinkText + ")] | " +
                        "//div[contains(@id, 'categoryBoxTabContents')]//a[contains(., " + escapedLinkText + ")]"));
    }

    public List<WebElement> getTopicLinksList(){
        return waitForExpectedElements(By.xpath("//*[@id='coid_categoryBoxTabPanel1']//a"));
    }

    // created by Phil Harper
    public WebElement PracticeAreaViaBrowse (String PracticeArea){
        return waitForExpectedElement(browseMenuItem(PracticeArea));

    }
    
    public By browseMenuItem (String link){
        return By.xpath("//a[@class='menu-item-link'][contains(.,\"" + link + "\")]");
    }
    
    public By menuBarSectionDisplayName(String name){
    	return By.xpath("//div[@class='menuBarSectionDisplayName'][contains(.,'" + name + "')]");
    }
    
    public WebElement getTitle(){
        return waitForExpectedElement(By.className("co_title"));
    }

    public WebElement waitForExpectedElement(By by){
    	return super.waitForExpectedElement(by);
    }
    
    public boolean isSystemErrorPresent() {
		return isExists(By.xpath("//*[@id='co_blockedBox']//p[contains(.,'You do not have access to')]"));
	}

    public WebElement getMeetTeamLink() {
        return waitForExpectedElement(MEET_THE_TEAM_LINK);
    }

}
