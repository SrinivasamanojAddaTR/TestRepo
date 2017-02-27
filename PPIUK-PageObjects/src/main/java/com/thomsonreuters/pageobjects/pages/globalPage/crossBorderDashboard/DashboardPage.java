package com.thomsonreuters.pageobjects.pages.globalPage.crossBorderDashboard;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;

public class DashboardPage extends CategoryPage {

	public boolean isHeaderPresent() {
		return isElementDisplayed(By.xpath("//h1[text()='Cross-border dashboard']"));
	}

	public boolean isTransactionGuidePresentInViewSelections(String transactionGuide) {
		return isElementDisplayed(
				By.xpath("//*[@class='selections-info']//span[@id='selectionTransactionGuide' and text()="
						+ Quotes.escape(transactionGuide) + "]"));
	}

	public boolean isCountryPresentInViewSelections(String country) {
		return isElementDisplayed(
				By.xpath("//*[@class='selections-info']//span[@id='selectionCountries' and contains(text(),'" + country
						+ "')]"));
	}

	public boolean isAreaOfEnquiryPresentInViewSelections(String areaOfEnquiry) {
		return isElementDisplayed(
				By.xpath("//*[@class='selections-info']//span[@id='selectionAreasOfEnquiry' and contains(text(),"
						+ Quotes.escape(areaOfEnquiry) + ")]"));
	}

	public void clickEditSelectionsButton() {
		retryingFindElement(By.id("dashboardEdit")).click();
		waitForPageToLoad();
	}

	public WebElement generalTab() {
		return waitForExpectedElement(By.linkText("General"));
	}

	public boolean isGeneralTabActive() {
		return isElementDisplayed(By.xpath("//a[text()='General']/ancestor::li[contains(@class,'tabActive')]"));
	}

	public boolean isGeneralTabHeaderDisplayed(String transactionGuide) {
		return isElementDisplayed(By.xpath("//h2[text()=" + Quotes.escape(transactionGuide) + "]"));
	}

	public boolean isItgCountryHeaderDisplayed(String transactionGuide, String countryName) {
		return isElementDisplayed(
				By.xpath("//h2[text()=" + Quotes.escape(transactionGuide + " for " + countryName) + "]"));
	}

	public WebElement itgCountryTab(String countryName) {
		return waitForExpectedElement(
				By.xpath("//*[@class='crossborder-dashboard-itg']//a[text()='" + countryName + "']"));
	}

	public WebElement itgCountryTabActive(String countryName) {
		return retryingFindElement(
				By.xpath("//a[text()='" + countryName + "']/ancestor::li[contains(@class,'tabActive')]"));
	}

	public List<WebElement> itgResourceWidgetDocumentLinks() {
		return retryingFindElements(By.xpath("//div[contains(@id,'itg-content') and contains(@style,'block')]//a[@href]"));
	}

	public void clickItgCountryTab(String country) {
		itgCountryTab(country).click();
		waitForPageToLoad();
	}

	public WebElement legendIcon(String name) {
		return findElement(
				By.xpath("//*[@class='ti-key-name' and text()='" + name + "']/parent::td//a[@title='" + name + "']"));
	}

	public boolean isLegendIconTextDisplayed(String name, String message) {
		return isElementDisplayed(By.xpath("//*[@class='ti-key-name' and contains(text(),'" + name
				+ "')]/parent::td//li[contains(text(),'" + message + "')]"));
	}

	public boolean isLegendDisplayed() {
		return isElementDisplayed(
				By.xpath("//*[@class='ti-key-text' and contains(text(),'lick on the icons for more related content')]"));
	}

	public boolean isTransactionIndicatorCountryHeaderDisplayed(String country) {
		return isElementDisplayed(By.xpath("//th[text()='" + country + "']"));
	}

	public WebElement transactionIndicatorElement(String areaOfEnquiry, String country) {
		return findElement(By.xpath("//td[contains(text()," + Quotes.escape(areaOfEnquiry)
				+ ")]/parent::tr//a[contains(@title," + Quotes.escape(country + " | " + areaOfEnquiry) + ")]"));
	}

	public boolean isTransactionIndicatorNotAvailable(String areaOfEnquiry, String country) {
		return isElementDisplayed(By.xpath("//td[contains(text()," + Quotes.escape(areaOfEnquiry)
				+ ")]/parent::tr//a[contains(@title," + Quotes.escape(country + " | " + areaOfEnquiry)
				+ ")]/span[contains(@class,'icon_indicator_na')]"));
	}

	public List<WebElement> transactionIndicatorLinks(String areaOfEnquiry, String country) {
		return findElements(By.xpath("//td[contains(text()," + Quotes.escape(areaOfEnquiry)
				+ ")]/parent::tr//a[contains(@title," + Quotes.escape(country + " | " + areaOfEnquiry)
				+ ")]/ancestor::td//div[@class='co_dropDownMenuContent']//a"));
	}

	public List<WebElement> marketSurveysPictures() {
		return retryingFindElements(
				By.xpath("//div[contains(@class,'market-surveys-images')]//img[contains(@src,'jpg')]"));
	}

	public boolean isMarketSurveysBigPictureDisplayed() {
		return isElementDisplayed(By.xpath("//div[contains(@class, 'market-surveys-full-image')]//img[@src]"));
	}

	public void closeMarketSurveysPopup() {
		findElement(By.xpath("//div[contains(@class, 'market-surveys-full-image')]//a[text()='Close']")).click();
	}

	public boolean isContentIsCommingSoonMessagePresent(String areaOfEnquiry, String country) {
		return isElementDisplayed(By.xpath("//td[contains(text()," + Quotes.escape(areaOfEnquiry)
				+ ")]/parent::tr//a[contains(@title," + Quotes.escape(country + " | " + areaOfEnquiry)
				+ ")]/ancestor::td//div[@class='co_dropDownMenuContent']//li[contains(text(),'Content is coming soon')]"));
	}

	public boolean isNoRelatedContentMessagePresent(String areaOfEnquiry, String country) {
		return isElementDisplayed(By.xpath("//td[contains(text()," + Quotes.escape(areaOfEnquiry)
				+ ")]/parent::tr//a[contains(@title," + Quotes.escape(country + " | " + areaOfEnquiry)
				+ ")]/ancestor::td//div[@class='co_dropDownMenuContent']//li[contains(text(),'No related content at this time')]"));
	}

	public boolean isContributingFirmsIncludeWidgetDispalyed() {
		return isElementDisplayed(
				By.xpath("//*[text()='Contributing firms include']/parent::div//*[@class='co_sliderContainer']"));
	}

	public boolean isBrexitWidgetDispalyed() {
		return isElementDisplayed(
				By.xpath("//a[text()='Brexit: the legal implications ' and contains(@href, 'Brexit')]"));
	}

	public boolean isCompareWhatsMarketAcrossJurisdictionsWidgetDispalyed() {
		return isElementDisplayed(By.xpath("//a[text()=" + Quotes.escape("Compare What's Market across jurisdictions")
				+ " and contains(@href,'/Document/Icb2a3903d2a011e698dc8b09b4f043e0')]"));
	}

	public boolean isCompareLawAcrossJurisdictionsWidgetDispalyed() {
		return isElementDisplayed(
				By.xpath("//*[text()='Start comparing' and contains(@href, '/QACompare/Builder/ITG')]"));
	}

	public int getCrossBorderTransactionGuidesWidgetLinksCount() {
		return findElements(By.xpath("//*[text()='Cross-border Transaction Guides']/parent::div//a")).size();
	}

}
