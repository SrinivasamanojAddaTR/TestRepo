package com.thomsonreuters.pageobjects.pages.adestra;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionPreferencePage extends AbstractPage {
    
    private static final String SPECIFIED_CHECKBOX = "//div[@id='%s']//td[text()=\"%s\"]/following-sibling::td[%s]/input";
    private static final String SPECIFIED_SERVICE = "//div[@id='%s']//td[text()=\"%s\"]";
    private static final String SPECIFIED_REQUEST_TRIAL_LINK = "//div[@id='%s']//td[text()=\"%s\"]/following-sibling::td[@class='product-trial-link']/a";
    private static final String GLOBAL = "Global";
    private static final String CANADA = "Canada";

    private Map<String, By> serviceTabMap = new HashMap<>();
    private Map<String, String> regionTableID = new HashMap<>();
    private Map<String, String> frequencyCheckBoxIndex = new HashMap<>();

    public SubscriptionPreferencePage() {
        serviceTabMap.put("US", By.linkText("US services"));
        serviceTabMap.put("UK", By.linkText("UK services"));
        serviceTabMap.put("EU", By.linkText("EU services"));
        serviceTabMap.put(GLOBAL, By.linkText("Global services"));
        serviceTabMap.put(CANADA, By.linkText("Canada services"));
        serviceTabMap.put("AU", By.linkText("Australia services"));

        regionTableID.put("US", "coid_categoryBoxTabPanel4");
        regionTableID.put("UK", "coid_categoryBoxTabPanel1");
        regionTableID.put("EU", "coid_categoryBoxTabPanel2");
        regionTableID.put(GLOBAL, "coid_categoryBoxTabPanel3");
        regionTableID.put(CANADA, "coid_categoryBoxTabPanel5");
        regionTableID.put("AU", "coid_categoryBoxTabPanel1");

        frequencyCheckBoxIndex.put("D", "1");
        frequencyCheckBoxIndex.put("W", "2");
        frequencyCheckBoxIndex.put("M", "3");
        frequencyCheckBoxIndex.put("A", "4");

    }

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
    		LOG.info("Checkbox for region: {} service: {} with frequency: {} is not selected, trying to select...", region , service, frequency);
    	} else LOG.info("Checkbox for region: {} service: {} with frequency: {} is selected, trying to deselect...", region , service, frequency);
    	scrollIntoViewAndClick(waitForElementToBeClickable(getSpecifiedCheckBox(service, frequency, region)));
    	waitForPageToLoadAndJQueryProcessing();
    	LOG.info("Checkbox state after click for region: {} service: {} with frequency: {} is selected = {}", region , service, frequency, getSpecifiedCheckBox(service, frequency, region).isSelected());
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
        if (region.equals(GLOBAL))
            regionIndex = "2";
        if (region.equals(CANADA))
            regionIndex = "4";
        JavascriptExecutor jse = (JavascriptExecutor) getDriver;
        return (String) jse.executeScript("return $(\"table:eq(" + regionIndex + ") td\").filter(function() { return $.text([this]) == '" + serviceName + "';}).parent().css(\"backgroundColor\")");
    }

    public void createSubscriptions(String region, String service, List<String> frequencies) {
        for (String frequency : frequencies) {
        	getSpecifiedCheckBoxAndClickOnIt(service, frequency, region);
        }
        saveButton().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void createSubscriptionsANZ(String region, String service, List<String> frequencies) {
        for (String frequency : frequencies) {
            getSpecifiedCheckBoxAndClickOnIt(service, frequency, region);
        }
        saveButtonANZ().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void createSubscription(String region, String service, String frequency) {
    	getSpecifiedCheckBoxAndClickOnIt(service, frequency, region);
        saveButton().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void unsubscribeAll() {
        if (!doNotSendMeLUCheckbox().isSelected()) {
            doNotSendMeLUCheckbox().click();
        }
        saveButton().click();
        waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(90);
    }

    public void unsubscribeAllANZ() {
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
            LOG.info("{} tab is selected", region);
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