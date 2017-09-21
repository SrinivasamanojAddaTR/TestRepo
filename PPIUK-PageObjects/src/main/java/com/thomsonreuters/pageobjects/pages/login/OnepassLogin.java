package com.thomsonreuters.pageobjects.pages.login;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OnepassLogin extends AbstractPage {

    public OnepassLogin() {
    }

    public boolean isUsernameValidationErrorDisplayed(String usernameValidationError) {
		return isTextPresent(By.xpath("//span[@id='Username_validationMessage']/span"), usernameValidationError);
	}
    
    public boolean isOnePassValidationErrorDisplayed(String usernameValidationError) {
		return isTextPresent(By.xpath("//div[@id='errorMessageContainer']/span"), usernameValidationError);
	}
    
    public WebElement manageOnePassLoginFormTitle() {
    	return retryingFindElement(By.xpath("//div[@class='signin']//h2"));
    }
    
    public WebElement usernameTextField() {
        return waitForExpectedElement(By.id("Username"));
    }

    public WebElement passwordTextField() {
        return waitForExpectedElement(By.id("Password"));
    }

    public WebElement signOnButton() {
        return waitForExpectedElement(By.id("SignIn"));
    }

    public WebElement signOffLink() {
        return waitForExpectedElement(By.linkText("Sign Off"));
    }

    public WebElement rememeberMeCheckBox() {
    	return waitForExpectedElement(By.id("SuperRememberMeLabel"));
    }

    public WebElement loginBox() {
        return retryingFindElement(By.className("Form-content"));
    }

    public WebElement forgotMyUsernameOrPasswordLink() {
        return retryingFindElement(By.partialLinkText("Forgot my username or password"));
    }

    public WebElement saveMyUsernameCheckBox() {
        return retryingFindElement(By.xpath(".//*[@id='form0']/div/ul[2]/li[3]/div[1]/label)");
    }

    public WebElement saveMyUsernameAndPasswordCheckBox() {
         return retryingFindElement(By.xpath(".//*[@id='form0']/div/ul[2]/li[3]/div[2]/label"));
    }

    public WebElement superRememberMeHintLink() {
        return retryingFindElement(By.id("superRememberMeHint"));
    }

    public WebElement superRememberMeHintPopUp() {
        return retryingFindElement(By.id("superRememberMeHintTooltip"));
    }
    
    public boolean isSuperRememberMeHintPopUpPresent() {
        return isExists(By.xpath(".//*[@id='srmdialog']/div"));
    }
    
    public WebElement createNewOnePassProfileLink() {
        return retryingFindElement(By.partialLinkText("Create a new OnePass profile"));
    }

    public WebElement updateExistingOnePassProfileLink() {
        return retryingFindElement(By.partialLinkText("Update an existing OnePass profile"));
    }

    public WebElement learnMoreAboutOnePassLink() {
        return retryingFindElement(By.partialLinkText("Learn more about OnePass"));
    }
    
    public WebElement manageOnepassTitle() {
        return waitForElementVisible(By.xpath("//h2[text()='manage onepass']"));
    }
      
    public WebElement regKeyRadioButtonByRegKey(String regKey) {
    	 return retryingFindElement(By.xpath("//label[contains(text(),'" + regKey + "')]//preceding-sibling::input[contains(@id,'SelectedRegistrationKey')]"));
    }
    
    public List<WebElement> marketingLinks(){
        return retryingFindElements(By.xpath("//div[@class='marketing']//a"));
    }

    public List<WebElement> marketingImages(){
        return retryingFindElements(By.xpath("//div[@class='marketing']//a/img"));
}
    
    public List<WebElement> marketingImagesOnCompartments(){
    	return waitForExpectedElements(By.xpath("//div[@class='fullwidth']//img"));
    }
    
	public WebElement logoImage() {
		return retryingFindElement(By.xpath("//div[@class='productimage']/img"));
	}

    public boolean isForgotUsernameLinkPresent() {
        return isElementDisplayed(By.partialLinkText("Forgot username?"));
    }

    public boolean isForgotPasswordLinkPresent() {
        return isElementDisplayed(By.partialLinkText("Forgot password?"));
    }

    public String getCompartmentLogoName(){
    	return logoImage().getAttribute("alt");
    }
    
    public WebElement athensLogin(){
    	return waitForExpectedElement(By.xpath("//*[contains(text(),'Log in with OpenAthens')]"));
    }
    
    public WebElement institutionLogin(){
    	return waitForExpectedElement(By.xpath("//*[contains(text(),'Select your Institution')]"));
    }
    
    public WebElement usernameAthens(){
    	return waitForExpectedElement(By.name("username"));
    }
    
    public boolean isUsernameAthensPresent(){
    	return isElementPresent(By.name("username"));
    }
    
    public WebElement passwordAthens(){
    	return waitForExpectedElement(By.name("password"));
    }
    
    public WebElement singInAthensButton(){
    	return waitForExpectedElement(By.xpath("//*[@type='submit' and contains(text(),'Sign in')]"));
    }
    
    public WebElement usernameShiboleth(){
    	return waitForExpectedElement(By.id("username"));
    }
    
    public boolean isUsernameShibolethPresent(){
    	return isElementPresent(By.id("username"));
    }
    
    public WebElement passwordShiboleth(){
    	return waitForExpectedElement(By.id("password"));
    }
    
    public WebElement singInShibolethButton(){
    	return waitForExpectedElement(By.xpath("//*[@type='submit' and contains(text(),'Login')]"));
    }
    
    public WebElement selectAnInstituionDropdown(){
    	return waitForExpectedElement(By.id("SelectedProvider"));
    }
    
    public WebElement acceptInShibolethButton(){
    	return waitForExpectedElement(By.xpath("//input[@name='_eventId_proceed' and @value='Accept']"));
    }
    
    public boolean isAcceptInShibolethButtonPresent(){
    	return isExists(By.xpath("//input[@name='_eventId_proceed' and @value='Accept']"));
    }
    
    public WebElement continueButton(){
    	return waitForExpectedElement(By.xpath("//*[@type='submit' and contains(text(),'Continue')]"));
    }
}
