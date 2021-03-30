package com.thomsonreuters.pageobjects.pages.adestra;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionPreferencePage extends AbstractPage {
    
    private final String SPECIFIED_CHECKBOX = "//div[@id='%s']//td[text()=\"%s\"]/following-sibling::td[%s]/input";
    
    private final String SPECIFIED_SERVICE = "//div[@id='%s']//td[text()=\"%s\"]";
    
    private final String SPECIFIED_REQUEST_TRIAL_LINK = "//div[@id='%s']//td[text()=\"%s\"]/following-sibling::td[@class='product-trial-link']/a";

    public SubscriptionPreferencePage() {
    }

   private final Map<String, By> serviceTabMap = new HashMap<String, By>(){{
        put("US", By.linkText("US services"));
        put("UK", By.linkText("UK services"));
        put("EU", By.linkText("EU services"));
        put("Global", By.linkText("Global services"));
        put("Canada", By.linkText("Canada services"));
        put("AU", By.linkText("Australia services"));
    }};

    private final Map<String, String> regionTableID = new HashMap<String, String>(){{
        put("US", "coid_categoryBoxTabPanel4");
        put("UK", "coid_categoryBoxTabPanel1");
        put("EU", "coid_categoryBoxTabPanel2");
        put("Global", "coid_categoryBoxTabPanel3");
        put("Canada", "coid_categoryBoxTabPanel5");
        put("AU", "coid_categoryBoxTabPanel1");
    }};
    
    private final Map<String, String> frequencyCheckBoxIndex = new HashMap<String, String>(){{
    	put("D", "1");
    	put("W", "2");
    	put("M", "3");
    	put("A", "4");
    }};
    

    public WebElement specifiedServiceTabLink(String region) {
        return waitForExpectedElement(serviceTabMap.get(region), 90);
    }

    public WebElement usServiceTabLink() {
        return waitForExpectedElement(By.linkText("US services"), 90);
    }

    public WebElement ukServiceTabLink() {
        return waitForExpectedElement(By.linkText("UK services"), 90);
    }

    public WebElement euServiceTabLink() {
        return waitForExpectedElement(By.linkText("EU services"), 90);
    }

    public WebElement globalServiceTabLink() {
        return waitForExpectedElement(By.linkText("Global services"), 90);
    }

    public WebElement canadaServiceTabLink() {
        return waitForExpectedElement(By.linkText("Canada services"), 90);
    }

    public WebElement htmlRadioButton() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_tabShow')]//*[@id = 'email-format-html']"), 90);
    }

    public WebElement htmlRadioButtonANZ() {
        return waitForExpectedElement(By.xpath("//input[@id = 'email-format-html']"));
    }

    public WebElement htmlRadioButtonLabel() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_tabShow')]//following-sibling::label[@for='email-format-html']"), 90);
    }

    public WebElement htmlRadioButtonLabelANZ() {
        return waitForExpectedElement(By.xpath("//label[@for='email-format-html']"));
    }

    public WebElement textOnlyRadioButton() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_tabShow')]//*[@id = 'email-format-plain']"), 90);
    }

    public WebElement textOnlyRadioButtonANZ() {
        return waitForExpectedElement(By.xpath("//input[@id = 'email-format-plain']"));
    }

    public WebElement textOnlyRadioButtonLabel() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_tabShow')]//following-sibling::label[@for='email-format-plain']"), 90);
    }

    public WebElement textOnlyRadioButtonLabelANZ() {
        return waitForExpectedElement(By.xpath("//label[@for='email-format-plain']"));
    }

    public WebElement recieveNoNewItemsCheckBox() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_tabShow')]//*[@id = 'supress-nothingtoreport']"), 90);
    }

    public WebElement recieveNoNewItemsCheckBoxANZ() {
        return waitForExpectedElement(By.xpath("//input[@id = 'supress-nothingtoreport']"));
    }

    public WebElement doNotSendMeLUCheckbox() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_tabShow')]//*[@id ='supress-products-yes']"), 90);
    }

    public WebElement doNotSendMeLUCheckboxANZ() {
        return waitForExpectedElement(By.id("supress-products-yes"), 90);
    }

    public WebElement saveButton() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_tabShow')]//*[text()='Save']"), 90);
    }

    public WebElement saveButtonANZ() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_primaryBtn')]//*[text()='Save']"), 90);
    }

    public WebElement cancelButton() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_tabShow')]//*[text() = 'Cancel']"), 90);
    }

    public WebElement cancelButtonANZ() {
        return waitForExpectedElement(By.xpath("//*[contains(@class, 'co_cancelBtn')]"));
    }

    public WebElement getSpecifiedCheckBox(String service, String frequency, String region) {
        return waitForExpectedElement(By.xpath(String.format(SPECIFIED_CHECKBOX, regionTableID.get(region), service, frequencyCheckBoxIndex.get(frequency))), 90);
    }
    
    
    public void getSpecifiedCheckBoxAndClickOnIt(String service, String frequency, String region) {
    	if(!getSpecifiedCheckBox(service, frequency, region).isSelected()) {
    		LOG.info(String.format("Checkbox for region: %s service: %s with frequency: %s is not selected, trying to select...", region , service, frequency));
    	} else LOG.info(String.format("Checkbox for region: %s service: %s with frequency: %s is selected, trying to deselect...", region , service, frequency));
    	scrollIntoViewAndClick(waitForElementToBeClickable(getSpecifiedCheckBox(service, frequency, region)));
    	waitForPageToLoadAndJQueryProcessing();
    	LOG.info(String.format("Checkbox state after click for region: %s service: %s with frequency: %s  is selected = %s", region , service, frequency, getSpecifiedCheckBox(service, frequency, region).isSelected()));
    }

    
	public boolean isSpecifiedCheckBoxDisplayed(String service, String frequency, String region) {
		return isExists(By.xpath(String.format(SPECIFIED_CHECKBOX, regionTableID.get(region), service, frequencyCheckBoxIndex.get(frequency))));
	}

    public WebElement getSpecifiedServiceField(String serviceName, String region) {
        return waitForExpectedElement(By.xpath(String.format(SPECIFIED_SERVICE, regionTableID.get(region), serviceName)), 90);
    }

    public WebElement getSpecifiedServiceRequestTrialLink(String serviceName, String region) {
        return waitForExpectedElement(By.xpath(String.format(SPECIFIED_REQUEST_TRIAL_LINK, regionTableID.get(region), serviceName)), 90);
    }

    public WebElement emailAddressInput() {
        return waitForExpectedElement(By.name("email"), 90);
    }

    public WebElement enabledContinueButton() {
        return waitForExpectedElement(By.xpath("//button[@class='email-preferences-button enabled-button']"), 90);
    }

    public String getRowBackGroundColor(String serviceName, String region) {
        String regionIndex = "";
        if (region.equals("US"))
            regionIndex = "3";
        if (region.equals("UK"))
            regionIndex = "0";
        if (region.equals("EU"))
            regionIndex = "1";
        if (region.equals("Global"))
            regionIndex = "2";
        if (region.equals("Canada"))
            regionIndex = "4";
        JavascriptExecutor jse = (JavascriptExecutor) getDriver;
        return (String) jse.executeScript("return $(\"table:eq(" + regionIndex + ") td\").filter(function() { return $.text([this]) == '" + serviceName + "';}).parent().css(\"backgroundColor\")");
    }

    public void createSubscriptions(String region, String service, List<String> frequencies) throws InterruptedException {
        for (String frequency : frequencies) {
        	getSpecifiedCheckBoxAndClickOnIt(service, frequency, region);
        }
        saveButton().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void createSubscriptionsANZ(String region, String service, List<String> frequencies) throws InterruptedException {
        for (String frequency : frequencies) {
            getSpecifiedCheckBoxAndClickOnIt(service, frequency, region);
        }
        saveButtonANZ().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void createSubscription(String region, String service, String frequency) throws InterruptedException {
    	getSpecifiedCheckBoxAndClickOnIt(service, frequency, region);
        saveButton().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void unsubscribeAll() throws InterruptedException {
        if (!doNotSendMeLUCheckbox().isSelected()) {
            doNotSendMeLUCheckbox().click();
        }
        saveButton().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void unsubscribeAllANZ() throws InterruptedException {
        if (!doNotSendMeLUCheckboxANZ().isSelected()) {
            doNotSendMeLUCheckboxANZ().click();
        }
        saveButtonANZ().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void removeUnsubscribeAllOption() {
        if (doNotSendMeLUCheckbox().isSelected()) {
            doNotSendMeLUCheckbox().click();
            saveButton().click();
            waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
        }
    }

    public void removeUnsubscribeAllOptionANZ() {
        if (doNotSendMeLUCheckboxANZ().isSelected()) {
            doNotSendMeLUCheckboxANZ().click();
            saveButtonANZ().click();
            waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
        }
    }

    public void openSpecifiedServiceTab(String region) {
        if (waitForElementPresent(By.xpath("//div[@id='" + regionTableID.get(region) + "']")).getAttribute("class").contains("co_tabShow")) {
            LOG.info(region + " tab is selected");
        } else {
            specifiedServiceTabLink(region).click();
            
        }
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }
    public boolean isSaveButtonPresent() {
        return isExists(By.xpath("//*[contains(@class, 'co_tabShow')]//*[text()='Save']"));
    }

    public boolean isCancelButtonPresent(){
        return isExists(By.xpath("//*[contains(@class, 'co_tabShow')]//*[text() = 'Cancel']"));
    }

    public void loginViaIpAuth(String emailAddress) {
        emailAddressInput().sendKeys(emailAddress);
        enabledContinueButton().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

}