package com.thomsonreuters.pageobjects.pages.footer;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;


import java.util.List;


public class WLNFooter extends AbstractPage {

    private static final String SIGN_IN_LINK = "Sign In";

    public WLNFooter() {
    }

    public WebElement footerFeedbackLink() {
        return findElement(By.id("PracticalLawFeedbackLink"));
    }
    
    public WebElement signInLink() {
        return waitForExpectedElement(By.linkText(SIGN_IN_LINK));
      }

    public WebElement footerWidget() {
        return waitForExpectedElement(By.id("footer-container"));
    }

    public WebElement requestTraining() {
        return waitForExpectedElement(By.xpath("//div[@id='footer-container']//a[text()='Request Training']"));
    }
    public WebElement userGuides() {
        return waitForExpectedElement(By.xpath("//div[@id='footer-container']//a[text()='User Guides']"));
    }

    public WebElement footerLink(String name, String group){
        return waitForExpectedElement(By.xpath(String.format("//div[@id='footer-container']//h3[.='%s']/following-sibling::ul/li/a[.='%s']",group, name)));
    }

    public WebElement requestTrial() {
        return waitForExpectedElement(By.xpath("//*[contains(text(),'Request Trial')]"));
    }

    //----------------------------------Footer first row-----------------------
    public WebElement preferenceLink() {
        return waitForExpectedElement(By.id("coid_websiteFooter_userSettings"));
    }

    public WebElement myContactsLink() {
        return waitForExpectedElement(By.id("coid_websiteFooter_contacts"));
    }

    public WebElement gettingStartedLink() {
        return waitForExpectedElement(By.id("coid_website_quickStartGuide"));
    }

    public WebElement helpLink() {
        return waitForExpectedElement(By.id("coid_websiteFooter_helplink"));
    }

    public WebElement twitterArrowLink() {
        return waitForExpectedElement(By.id("twitter-arrow"));
    }
    public WebElement twitterPopup() {
        return waitForExpectedElement(By.xpath("//div[@id='twitter-links']//ul[@class='co_dropDownMenuList']"));
    }

    //-----------------------------Footer second/last row------------------------------
    public List<WebElement> footerFirstRowLinks() {

        return waitForElementsVisible(By.xpath("//footer[@id='co_footer']//ul[@class='footer-main co_inlineList co_buttonList']//a"));
    }

    public List<WebElement> footerSecondRowLinks() {

        return waitForElementsVisible(By.xpath("//footer[@id='co_footer']//ul[@class='footer-sub co_inlineList co_buttonList']//a"));
    }

    public List<WebElement> footerSecondRowLabels() {

        return waitForElementsVisible(By.xpath("//footer[@id='co_footer']//ul[@class='footer-sub co_inlineList co_buttonList']//li"));
    }

    public WebElement offersLink() {
        return waitForExpectedElement(By.xpath("//a[text()='Offers']"));
    }
    //----------------------------Object Video Window-----------------
    public WebElement getStartVideoObject() {
        return waitForExpectedElement(By.id("co_gettingStartedGuide"));
    }

    //---------------------------For Offer Page Message----------------
    public WebElement offerPageMessage() {
        return waitForExpectedElement(By.xpath("//div[@class='co_infoBox_message']//p[contains(text(),'You are not currently eligible')]"));
    }
    
    public WebElement footerLink(String linkName) {
        return waitForExpectedElement(By.xpath("//div[@id='co_footer']//a[contains(.,"+ Quotes.escape(linkName) +")]"));
    }
    
    public WebElement footerLinkOnLoginPages(String linkName) {
        return waitForExpectedElement(By.xpath("//div[@class='contentpad']//li//*[contains(.," +  Quotes.escape(linkName) + ")]"));
    }
    
    public boolean isSignInLinkPresent() {
    	return isElementDisplayed(By.linkText(SIGN_IN_LINK));
    }
    
    public List<WebElement> footerByColumnLinks(String columnHeading) {
        return waitForExpectedElements(By.xpath("//div[@id='co_footer']//div[@class='co_column']//h3[text()='" + columnHeading + "']/..//li/a"));
    }

    //**   Thomson Reuters Career Page Title **
    public WebElement careerPageTitle() {
        return waitForExpectedElement(By.xpath("//span[@class='product-name']"));
    }

    //**   All the tab elements appearing on any Category Browse Page **
    public List<WebElement> pageTabLinks() {
        return waitForExpectedElements(By.xpath("//ul[@class='co_tabs co_categoryTabs']//h2//a"));
    }

	public void goToRequestTrialForm() {
		requestTrial().click();
		switchToOpenedWindow();
		waitForPageToLoad();	
	}
	
	public WebElement betaFeedbackLink() {
		return waitForExpectedElement(By.id("co_feedbackButton"));
	}

    public void clickOnFeedBackLink() {
        try{
            footerFeedbackLink().click();
            waitForElementsVisible(By.id("co_feedback"));
        }catch(TimeoutException te){
            throw new PageOperationException("Exceeded time to find the FeedBack button on footer block of the page.");
        }
    }

    public WebElement customerSupportInfoContainer(){
        return waitForExpectedElement(By.xpath("//div[@id='footer-container']/div/div[contains(@class,'co_80Percent')]"));
    }
    
    public WebElement copyrightText(){
    	return waitForExpectedElement(By.className("copyright"));
    }

    public WebElement emailLink(String text){
        return waitForExpectedElement(By.xpath("//*[contains(text(),'" + text + "')]"));
       }
    
    public WebElement usageFAQ(){
    	return waitForExpectedElement(By.xpath("//*[contains(.,'Usage FAQs')]"));
    }
    
    public WebElement twitterLink(){
    	return waitForExpectedElement(By.id("footer-twitter"));
    }
}
