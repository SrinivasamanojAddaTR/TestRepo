package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PracticalLawHomepage extends AbstractPage {

    private static final By CLOSE_CONSENT_MESSAGE_XPATH = By.xpath("//div[@id='CookieConsentMessage']//a[text()='Close']");
	private CommonMethods comMethods;
	private static final By FLASH_SCREEN = By.xpath("//div[@id='co_welcomeCenterLightbox']/div[2]/div/div/div/ul/li/a");
	private static final By CLOSE_FLASH_SCREEN_ID = By.id("undefined");


    public PracticalLawHomepage() {

        comMethods = new CommonMethods();
    }

    public WebElement signOnButton() {
        return waitForExpectedElement(By.id("coid_website_signOffRegion"));
    }

    public WebElement signOffButton() {
        return waitForExpectedElement(By.id("coid_website_signOffRegion"));
    }

    public WebElement unitedKingdomContentLink() {
        return waitForExpectedElement(By.linkText("United Kingdom"));
    }

    public WebElement practiceAreaLink(String practiceArea) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_website_browseMainColumn']/div[1]//a[contains(text(),'" + practiceArea + "')]"));
    }

    public WebElement collectionLink(String link) {
		return waitForExpectedElement(By.xpath("//*[contains(@id,'coid_website_widget_managedContentContainer')]//a[contains(text(),'"
				+ link + "')]"));
    }

    public WebElement legalUpdatesContentLink() {
        return waitForExpectedElement(By.id("coid_legal_updates"));
    }

    public WebElement plcHeadingLabel() {
        return waitForExpectedElement(By.id("co_browsePageLabel"));
    }

    public WebElement browseWidget() {
        return waitForExpectedElement(By.id("co_browseWidget"));
    }

    public WebElement browseHeadingTitle() {
        return waitForExpectedElement(By.id("categoryPageScope"));
    }

    public WebElement researchBrowseHomeLink() {
        return waitForExpectedElement(By.linkText("RBHome"));
    }

    public WebElement requestATrialButton() {
        return waitForExpectedElement(By.xpath("//*[@id='footer-request-trial']//*[contains(text(), 'Request')]"), 90);
    }

    public WebElement practiceLink() {
        return waitForExpectedElement(By.linkText("Practice"));
    }

    public WebElement researchFoldersWidgetLink() {
        return waitForExpectedElement(By.xpath("//span[@id='co_dockTitle' and contains(text(), 'Research')]"));
    }

    public WebElement welcomeBox() {
        return comMethods.waitForElementToBeVisible(By.id("coid_lightboxOverlay"), 1000);
    }

    public WebElement timeoutPopUp(int waitForPopUptimeOutInSeconds) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_lightboxOverlay']/div[@id='co_websiteTimeoutWarning']"), waitForPopUptimeOutInSeconds);
    }
    public void waitSignInLink(int waitForSignInLink) {
        waitForExpectedElement(By.id("coid_website_signOffRegion"), waitForSignInLink);
    }

    public WebElement continueSessionButton() {
        return waitForExpectedElement(By.xpath("//input[@type='button' and @value='Continue']"));
    }
  
    public boolean isTimeoutPopUpPresent(int waitForPopUptimeOutInSeconds) {
    	return isElementDisplayed(By.xpath("//div[@id='coid_lightboxOverlay']/div[@id='co_websiteTimeoutWarning']"));
    }

    public WebElement resourcesLink() {
        return waitForExpectedElement(By.partialLinkText("Resources"));
    }

    public WebElement internationalLink() {

        return waitForExpectedElement(By.partialLinkText("International"));
    }

	public WebElement practiceAreasLink() {

		return waitForExpectedElement(By.partialLinkText("Practice areas"));
	}

    public void closeCookieConsentMessage(){
        waitForPageToLoad();
        waitForPageToLoadAndJQueryProcessing();
        if (isElementDisplayed(CLOSE_CONSENT_MESSAGE_XPATH)) {
            waitForExpectedElement(CLOSE_CONSENT_MESSAGE_XPATH).click();
        }
    }

    public WebElement cookieConsentMessage(){
        return waitForExpectedElement(By.id("CookieConsentMessage"));
    }

    public WebElement cookieConsentLink(String link){
        return waitForExpectedElement(By.xpath("//div[@id='CookieConsentMessage']//a[text()='" + link + "']"));
    }

    public WebElement practicalLawTRLogo() {
        return waitForExpectedElement(By.xpath("//a[@id='logo'][@title='Practical Law Home']"),5);
    }

    public WebElement unreleasedCategoryPagesLink() {
        return waitForExpectedElement(By.id("coid_unreleased_category_pages"));
    }

    public void flashScreenPL(){
        try{
             if(isElementDisplayed(FLASH_SCREEN))
             {
                    waitForExpectedElement(CLOSE_FLASH_SCREEN_ID).click();
             }
             
         }catch(Exception e){
               
                        }
    }



}
