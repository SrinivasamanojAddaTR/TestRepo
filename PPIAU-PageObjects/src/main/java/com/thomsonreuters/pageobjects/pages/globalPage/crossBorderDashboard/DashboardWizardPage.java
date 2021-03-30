package com.thomsonreuters.pageobjects.pages.globalPage.crossBorderDashboard;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;

import com.thomsonreuters.driver.framework.AbstractPage;

public class DashboardWizardPage extends AbstractPage {

	private static final String TRANSACTION_GUIDE = "//*[starts-with(text(),'Step 1') and contains(text(),'Cross border transaction guide')]";
	private static final String COUNTRIES = "//*[starts-with(text(),'Step 2') and contains(text(),'Countries')]";
	private static final String AREA_OF_ENQUIRY = "//*[starts-with(text(),'Step 3') and contains(text(),'Areas of enquiry')]";
	private static final String STEP = "/ancestor::div[@class='step']";
	private static final String TRANSACTION_GUIDE_STEP = TRANSACTION_GUIDE + STEP;
	private static final String COUNTRIES_STEP = COUNTRIES + STEP;
	private static final String AREA_OF_ENQUIRY_STEP = AREA_OF_ENQUIRY + STEP;
	private static final String PLEASE_SELECT_TRANSACTION_GUIDE_TEXT = "Please select a transaction guide";
	private static final String PLEASE_SELECT_COUNTRT_TEXT = "Please select a country";
	private static final String PLEASE_SELECT_AOE_TEXT = "Please select an area of enquiry";
	private static final String WIZARD_DESCRIPTION = "Complete the following steps to start comparing country specific guidance on international transactions across multiple countries";

	public boolean isHeaderPresent() {
		return isElementDisplayed(By.xpath("//*[@class='wizardTitle']/h1[text()='Find relevant resources']"));
	}

	public boolean isFirstStepDescriptionPresent() {
		return isElementDisplayed(By.xpath("//legend[text()='Choose one transaction guide']"));
	}

	public boolean isSecondStepDescriptionPresent() {
		return isElementDisplayed(By.xpath("//legend[text()='Choose up to three countries']"));
	}

	public boolean isThirdStepDescriptionPresent() {
		return isElementDisplayed(By.xpath("//legend[text()='Choose up to ten areas of enquiry']"));
	}

	public boolean isDescriptionPresent() {
		return isElementDisplayed(By.xpath("//*[@class='wizard']//*[text()='" + WIZARD_DESCRIPTION + "']"));
	}

	public List<String> transactionGuideNameList() {
		waitForPageToLoadAndJQueryProcessing();
		List<String> transactionGuideList = new ArrayList<String>();
		for (WebElement transactionGuide : waitForExpectedElements(By.xpath(TRANSACTION_GUIDE_STEP + "//select/option"))) {
			transactionGuideList.add(transactionGuide.getText());
		}
		transactionGuideList.remove(PLEASE_SELECT_TRANSACTION_GUIDE_TEXT);
		return transactionGuideList;
	}

	public void selectTransactionGuide(String transactionGuide) {
		new Select(waitForExpectedElement(By.xpath(TRANSACTION_GUIDE_STEP + "//select/option[text()='"
				+ PLEASE_SELECT_TRANSACTION_GUIDE_TEXT + "']/parent::select"))).selectByVisibleText(transactionGuide);
	}

	public boolean isTransactionGuideSelectedPresent(String transactionGuide) {
		return isElementDisplayed(By.xpath(
				TRANSACTION_GUIDE + "/following-sibling::span[contains(@class,'selectedOptions') and contains(text(),"
						+ Quotes.escape(transactionGuide) + ")]"));
	}

	public int getTransactionGuideDropDownCount() {
		return waitForExpectedElements(By.xpath(TRANSACTION_GUIDE_STEP + "//select")).size();
	}

	public int getCountriesDropDownCount() {
		return waitForExpectedElements(By.xpath(COUNTRIES_STEP + "//select")).size();
	}

	public WebElement removeSelectedParameterButton(String paramName) {
		String jQuerySelector = "$('select').filter(function() { return $(this).val() === $(this).find(\"option:contains('"
				+ paramName + "')\").val(); }).siblings('a')";
		return (WebElement) executeScript("return $(" + jQuerySelector + ").get(0);");
	}

	public void removeSelectedParameter(String paramName) {
		removeSelectedParameterButton(paramName).click();
	}

	public WebElement goToCountriesNextButton() {
		return waitForExpectedElement(By.xpath(TRANSACTION_GUIDE_STEP + "//button[text()='Next']"));
	}

	public void clickGoToCountriesNextButton() {
		goToCountriesNextButton().click();
	}

	public WebElement goToAreasOfEnquiryNextButton() {
		return waitForExpectedElement(By.xpath(COUNTRIES_STEP + "//button[text()='Next']"));
	}

	public boolean isGoToCountriesNextButtonDisabled() {
		return isElementDisplayed(
				By.xpath(TRANSACTION_GUIDE_STEP + "//button[text()='Next' and @disabled='disabled']"));
	}

	public void selectCountries(List<String> countries) {
		for (String country : countries) {
			selectCountry(country);
		}
	}

	public void selectCountry(String country) {
		new Select(waitForExpectedElement(By
				.xpath(COUNTRIES_STEP + "//select/option[text()='" + PLEASE_SELECT_COUNTRT_TEXT + "']/parent::select")))
						.selectByVisibleText(country);
	}

	public List<String> countriesNameList() {
		List<String> countriesList = new ArrayList<String>();
		for (WebElement country : waitForExpectedElements(By.xpath(COUNTRIES_STEP + "//select/option[text()='"
				+ PLEASE_SELECT_COUNTRT_TEXT + "']/parent::select/option"))) {
			countriesList.add(country.getText());
		}
		countriesList.remove(PLEASE_SELECT_COUNTRT_TEXT);
		return countriesList;
	}

	public boolean isCountrySelectedPresent(String country) {
		return isElementDisplayed(
				By.xpath(COUNTRIES + "/following-sibling::span[contains(@class,'selectedOptions') and contains(text(),'"
						+ country + "')]"));
	}

	public void clickGoToAreasOfEnquiryNextButton() {
		goToAreasOfEnquiryNextButton().click();
	}

	public void selectAreasOfEnquiry(List<String> areasOfEnquiry) {
		for (String areaOfEnquiry : areasOfEnquiry) {
			selectAreaOfEnquiry(areaOfEnquiry);
		}
	}

	public List<String> areasOfEnquiryNameList() {
		List<String> areasOfEnquiryList = new ArrayList<String>();
		for (WebElement areaOfEnquiry : waitForExpectedElements(By.xpath(AREA_OF_ENQUIRY_STEP + "//select/option[text()='"
				+ PLEASE_SELECT_AOE_TEXT + "']/parent::select/option"))) {
			areasOfEnquiryList.add(areaOfEnquiry.getText());
		}
		areasOfEnquiryList.remove(PLEASE_SELECT_AOE_TEXT);
		return areasOfEnquiryList;
	}

	public void selectAreaOfEnquiry(String areaOfEnquiry) {
		new Select(waitForExpectedElement(By.xpath(
				AREA_OF_ENQUIRY_STEP + "//select/option[text()='" + PLEASE_SELECT_AOE_TEXT + "']/parent::select")))
						.selectByVisibleText(areaOfEnquiry);
	}

	public boolean isAreasOfEnquirySelectedPresent(String areaOfEnquiry) {
		return isElementDisplayed(By.xpath(
				AREA_OF_ENQUIRY + "/following-sibling::span[contains(@class,'selectedOptions') and contains(text(),"
						+ Quotes.escape(areaOfEnquiry) + ")]"));
	}

	public void clickClearButton() {
		clearButton().click();
	}

	public WebElement clearButton() {
		return waitForExpectedElement(By.xpath("//button[contains(text(),'Clear')]"));
	}

	public boolean isClearButtonDisabled() {
		return isElementDisplayed(By.xpath("//button[contains(text(),'Clear') and @disabled='disabled']"));
	}

	public void clickCancelButton() {
		waitForExpectedElement(By.xpath("//button[contains(text(),'Cancel')]")).click();
		waitForPageToLoad();
	}

	public WebElement submitButton() {
		return waitForExpectedElement(By.xpath("//button[contains(text(),'Submit')]"));
	}

	public boolean isSubmitButtonDisabled() {
		return isElementDisplayed(By.xpath("//button[contains(text(),'Submit') and @disabled='disabled']"));
	}

	public void clickSubmitButton() {
		submitButton().click();
		waitForPageToLoadAndJQueryProcessing();
	}

	public List<WebElement> selections() {
		return findElements(By.xpath("span[contains(@class,'selectedOptions') and text()]"));
	}

}
