package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.thomsonreuters.pageobjects.common.CommonMethods;

public class InternationalTransactionGuidancePage extends StandardDocumentPage {

	private static final String COUNTRY_SELECTOR_TEXT = "Insert jurisdiction-specific material";
	private static final String FIRST_SELECTOR_OPTION_TEXT = "Please select...";
	private static final String COUNTRY_SELECTOR_SELECTOR = "//*[@id='co_docContentBody']//select[contains(@class,'jurSelector')]";
	private static final String Q_AND_A_DOCUMENT_SELECTOR = "//*[text()=\"View Questions & Answers for \"]/a[contains(@id,'link_') and contains(text(),'%s')]";
	
	private CommonMethods commonMethods = new CommonMethods();

	public boolean isCountrySelectorPresent() {
		return isExists(By.xpath("(//div[contains(@class,'urSelectionBlock') and contains(.,'" + COUNTRY_SELECTOR_TEXT
				+ "')]/select/option)[1][text()='" + FIRST_SELECTOR_OPTION_TEXT + "']"));
	}

	public WebElement countrySelectorDropdown() {
		return waitForExpectedElement(By.xpath(COUNTRY_SELECTOR_SELECTOR));
	}

	public WebElement countrySelectorDropdownInStickyHeader() {
		return waitForExpectedElement(By.xpath("(//select[contains(@class,'jurSelector')])[1]"));
	}

	public boolean isLinkToViewAllQuestionsPresent(String countryName) {
		return isExists(By.xpath(String.format(Q_AND_A_DOCUMENT_SELECTOR, countryName)));
	}

	public WebElement linkToViewAllQuestions(String countryName) {
		return waitForExpectedElement(By.xpath(String.format(Q_AND_A_DOCUMENT_SELECTOR, countryName)));
	}

	public boolean isPNContributorPresent(String country, String contributorName) {
		return isExists(By.xpath("//div[text()=' " + contributorName + "']//span[contains(text(),'" + country
				+ "') and contains(text(),'contributor:')]"));
	}

	public boolean isSDContributorPresent(String contributorName) {
		return isExists(By.xpath("//span[@class='co_bold' and text()='PL Global based on the material provided by "
				+ contributorName + "']"));
	}

	public List<WebElement> getCountrySelectorCountryList() {
		return findElements(By.xpath(COUNTRY_SELECTOR_SELECTOR + "/option"));
	}

	public List<WebElement> getActualCountrySpecificNotesList(String country) {
		return findElements(By.xpath("//h2[text()='" + country + "' or contains(text(),'" + country + " ')]"));
	}

	public String getDocumentGUID() {
		return documentMetaInfo().getAttribute("id").split("_")[3];
	}
	
	public void selectCountryFromDropdown(String countryName) {
		new Select(countrySelectorDropdown()).selectByVisibleText(countryName);
	}
	
	public String selectCountryFromDropdownByIndex(int countryIndex) {
		new Select(countrySelectorDropdown()).selectByIndex(countryIndex);
		return new Select(countrySelectorDropdown()).getFirstSelectedOption().getText();
	}
	
	public void selectCountryFromDropdownInStickyHeader(String countryName) {
		waitForPageToLoad();
		new Select(countrySelectorDropdownInStickyHeader()).selectByVisibleText(countryName);
	}

	public List<String> getActualCountyListFromWebsite() {
		List<String> countries = commonMethods.getTextsFromWebElements(getCountrySelectorCountryList());
		countries.remove(FIRST_SELECTOR_OPTION_TEXT);
		return countries;
	}

	public String getSelectedCountryName() {
		return new Select(countrySelectorDropdown()).getFirstSelectedOption().getText();
	}

}
