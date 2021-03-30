package com.thomsonreuters.pageobjects.pages.header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WLUKHeader extends WLNHeader {

    private final String CONTENT_LINK= "//nav[@id='menubar_nav']//*[contains(text(), '%s')]";
    private final String ACCOUNT_LINK="//ul[@class='co_dropDownMenuList']//*[contains(.,'%s')]";

    public enum ContentLink {
        CASES("Cases"),
        LEGISLATION("Legislation"),
        JOURNALS("Journals"),
        TOPICS("Topics"),
        MORE("More");

        private String linkText;

        ContentLink(String linkText) {
            this.linkText = linkText;
        }

        public String getLinkText() {
            return linkText;
        }
    }
    
    public boolean isCategoryPageLinkOnWLUKInMoreDropDownPresent(String categoryPageName) {
        return isExists(By.xpath("//*[contains(@class,'co_dropDownMenuContent')]//*[contains(text(),'" + categoryPageName + "')]"));
    }
    
    public boolean isMoreDropwdownOnWLUKHeaderPresent() {
        return isExists(By.xpath("//nav[@id='menubar_nav']//li[contains(., 'More')]//span[contains(@class, 'icon_down_arrow_blue')]"));
    }
    
    public WebElement categoryPageLinkOnWLUKInMoreDropDown(String categoryPageName) {
        return waitForExpectedElement(By.xpath("//*[contains(@class,'co_dropDownMenuContent')]//*[contains(text(),'" + categoryPageName + "')]"));
    }
    
    public boolean isCategoryPageLinkTitlePresent(String categoryPageTitle) {
        return isExists(By.xpath("//*[@id='co_browsePageLabel' and contains(text(), '" + categoryPageTitle + "')]"));
    }
    
    public WebElement contentsLinksOnWLUK(String contentTitle) {
        return waitForExpectedElement(By.xpath(String.format(CONTENT_LINK, contentTitle)));
    }

    public WebElement contentsLinksOnWLUK(ContentLink contentLink) {
        return waitForExpectedElement(By.xpath(String.format(CONTENT_LINK, contentLink.getLinkText())));
    }
    
    public boolean isContentsLinksOnWLUKDisplayed(String contentTitle) {
        return isElementDisplayed(By.xpath(String.format(CONTENT_LINK, contentTitle)));
    }

    public boolean isContentsLinksOnWLUKDisplayed(ContentLink contentLink) {
        return isElementDisplayed(By.xpath(String.format(CONTENT_LINK, contentLink.getLinkText())));
    }
    
    public boolean isLinksInTheUserProfileDropdownPresent(String link){
    	return isExists(By.xpath(String.format(ACCOUNT_LINK, link)));
    }

    public WebElement linksInTheUserProfileDropdown(String link){
    	return waitForExpectedElement(By.xpath(String.format(ACCOUNT_LINK, link)));
    }
    
    public WebElement usernamePopUpOnHoverOnUserProfileDropdown(){
    	return waitForExpectedElement(By.xpath("//div[@id='preferences-dropdown']/a[contains(@class,'co_dropDownAnchor')]"));
    }

    public boolean isAccountLabelDisplayed(){
        return isElementDisplayed(By.xpath("//li[@id='user_preferences']//a[contains(.,'Account')]"));
    }
}