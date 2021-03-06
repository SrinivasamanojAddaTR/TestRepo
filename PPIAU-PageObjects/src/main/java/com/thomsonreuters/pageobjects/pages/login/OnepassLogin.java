package com.thomsonreuters.pageobjects.pages.login;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OnepassLogin extends AbstractPage {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public boolean isUsernameValidationErrorDisplayed(String usernameValidationError) {
		return isTextPresent(By.xpath("//span[@id='Username_validationMessage']/span"), usernameValidationError);
	}
    
    public boolean isOnePassValidationErrorDisplayed(String usernameValidationError) {
        return isTextPresent(By.xpath("//span[contains(@class, 'Icon-wrapper')]//following-sibling::span"), usernameValidationError);
	}
    
    public WebElement manageOnePassLoginFormTitle() {
    	return waitForExpectedElement(By.xpath("//div[@class='signin']//h2"));
    }
    
    public WebElement usernameTextField() {
        return waitForExpectedElement(By.name("Username"));
    }

    public WebElement passwordTextField() {
        return waitForExpectedElement(By.name("Password"));
    }

    public WebElement signOnButton() {
        return waitForExpectedElement(By.xpath("//*[text()='Sign in' or text()='Sign In' or @id='SignIn']"));
    }

    public WebElement signOffLink() {
        return waitForExpectedElement(By.linkText("Sign Off"));
    }

    public WebElement rememeberMeCheckBox() {
        return waitForElementPresent(By.name("SuperRememberMe"));
    }

    public WebElement loginBox() {
        return waitForExpectedElement(By.className("Form-content"));
    }

    public WebElement forgotMyUsernameOrPasswordLink() {
        return waitForExpectedElement(By.partialLinkText("Forgot my username or password"));
    }

    public WebElement saveMyUsernameCheckBox() {
        return waitForElementPresent(By.name("SaveUsername"));
    }

    public WebElement saveMyUsernameAndPasswordCheckBox() {
        return waitForElementPresent(By.name("SaveUsernamePassword"));
    }

    private void selectCheckBoxUsingActions(WebElement element) {
        if (!element.isSelected()) {
            moveToAndClickElement(element);
        }
    }


    @Override
    public boolean isCheckBoxSelectable(WebElement checkBox) {
        boolean initialState = checkBox.isSelected();
        selectCheckBoxUsingActions(checkBox);
        boolean finalState = checkBox.isSelected();
        return initialState != finalState;
    }

    public WebElement superRememberMeHintLink() {
        return waitForExpectedElement(By.xpath("//*[contains(@class,'Button-rememberMe') or @id='superRememberMeHint']"));
    }

    public WebElement superRememberMeHintPopUp() {
        return waitForExpectedElement(By.id("superRememberMeHintTooltip"));
    }
    
    public boolean isSuperRememberMeHintPopUpPresent() {
        return isExists(By.xpath(".//*[@id='srmdialog']/div"));
    }
    
    public WebElement createNewOnePassProfileLink() {
        return waitForExpectedElement(By.partialLinkText("Create OnePass profile"));
    }

    public WebElement updateExistingOnePassProfileLink() {
        return waitForExpectedElement(By.xpath("//a[contains(.,'Update an existing OnePass profile') or contains(.,'Update OnePass profile')]"));
    }

    public WebElement learnMoreAboutOnePassLink() {
        return waitForExpectedElement(By.partialLinkText("Learn about OnePass"));
    }

    public WebElement manageOnepassTitle() {
        return waitForElementVisible(By.xpath("//div[@class='PageHeader']//*[@title='Thomson Reuters OnePass']"));
    }

    public boolean isManageOnePassTitleDisplayed() {
        return isElementDisplayed(By.xpath("//*[contains(@class,'PageHeader')]//*[contains(@title,'Thomson Reuters OnePass')]"));
    }
      
    public WebElement regKeyRadioButtonByRegKey(String regKey) {
    	 return waitForExpectedElement(By.xpath("//label[contains(text(),'" + regKey + "')]//preceding-sibling::input[contains(@id,'SelectedRegistrationKey')]"));
    }
    
    public List<WebElement> marketingLinks(){
        return waitForExpectedElements(By.xpath("//div[@class='marketing']//a"));
    }

    public List<WebElement> marketingImages(){
        return waitForExpectedElements(By.xpath("//div[@class='marketing']//a/img"));
}
    
    public List<WebElement> marketingImagesOnCompartments(){
    	return waitForExpectedElements(By.xpath("//div[@class='fullwidth']//img"));
    }
    
	public WebElement logoImage() {
		return waitForExpectedElement(By.xpath("//div[@class='productimage']/img"));
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
    	return waitForExpectedElement(By.name(USERNAME));
    }
    
    public boolean isUsernameAthensPresent(){
    	return isExists(By.name(USERNAME));
    }
    
    public WebElement passwordAthens(){
    	return waitForExpectedElement(By.name(PASSWORD));
    }
    
    public WebElement singInAthensButton(){
    	return waitForExpectedElement(By.xpath("//*[@type='submit' and contains(text(),'Sign in')]"));
    }
    
    public WebElement usernameShiboleth(){
    	return waitForExpectedElement(By.id(USERNAME));
    }
    
    public boolean isUsernameShibolethPresent(){
    	return isExists(By.id(USERNAME));
    }
    
    public WebElement passwordShiboleth(){
    	return waitForExpectedElement(By.id(PASSWORD));
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
