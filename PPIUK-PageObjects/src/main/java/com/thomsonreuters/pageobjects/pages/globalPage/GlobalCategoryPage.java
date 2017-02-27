package com.thomsonreuters.pageobjects.pages.globalPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class GlobalCategoryPage extends AbstractPage {
	
	private static final String DASHBOARD_DESCRIPTION = "Select transaction, countries and topics for at-a-glance view of risks and complexities, and access to relevant know-how and market research.";

    public List<WebElement> header() {
        return findElements(By.id("header"));
    }

    public List<WebElement> globalPageHeader() {
        return findElements(By.id("co_pageHeader"));
    }

    public List<WebElement> globalPageBody() {
        return findElements(By.id("co_body"));
    }

    public List<WebElement> globalPageFooter() {
        return findElements(By.id("co_footerContainer"));
    }

    public WebElement countryInCoutriesTab(String country) {
        return retryingFindElement(By.xpath(".//div[@id='coid_categoryBoxTabPanel2']//a[contains(text(), '" + country
                + "')]"));
    }

    public List<WebElement> jurisdictionsInTheLeftHandSide() {
        return findElements(By.xpath(".//h4[contains(text(), 'Jurisdiction')]/following-sibling::ul//label"));
    }

    public List<WebElement> jurisdictionsInTheReturnedSearchResults() {
        return findElements(By.xpath(".//strong[contains(text(), 'Jurisdiction')]/.."));
    }

    public List<WebElement> resourceTypes() {
        return findElements(By.xpath(".//strong[contains(text(), 'Document Type')]/.."));
    }

    public List<WebElement> returnedSearchResults() {
        return findElements(By.xpath(".//ol[@class='co_searchResult_list']//div[@class='co_searchContent']"));
    }

    public WebElement checkBox(String facetGroup, String facetName) {
        return retryingFindElement(By.xpath(".//h4[contains(text(), '" + facetGroup
                + "')]/following-sibling::ul//label[contains(text(), '" + facetName + "')]"));
    }

    public WebElement linkInSection(String linkText, String section) {
        return retryingFindElement(By.xpath("//h3[contains(., '" + section
                + "')]/following-sibling::div//a[contains(.,'" + linkText + "')]"));
    }

    public WebElement linkInInterSubscriptionsSectionInBrowseMenu(String linkText, String sectionName) {
        return retryingFindElement(By.xpath(".//div[contains(@class, 'column') and .//*[contains(., '" + sectionName
                + "')]]//a[contains(., '" + linkText + "')]"));
    }

    public List<WebElement> theFollowingItemsForTheFirstSixInTheSSDD() {
        return findElements(By.xpath(".//li[@class='co_separator'][2]/following-sibling::li//a"));
    }

    public List<WebElement> fullListOfContriesInTheSSDD() {
        return findElements(By.xpath(".//a[contains(text(), 'All Global Content')]/../following-sibling::li//a"));
    }

    public WebElement moreJurisdiction() {
        return retryingFindElement(By.linkText("More Jurisdictions"));
    }

    public WebElement continueButton() {
        return retryingFindElement(By.xpath(".//*[contains(@id, 'JurisdictionSummary_continueButton')]"));
    }

    public WebElement linksHeader(String header) {
        return retryingFindElement(By.xpath(".//*[contains(text(), '" + header + "')]"));
    }

    public boolean isRelatedPracticeAreasHeaderPresent(){
        return isExists(By.xpath("//h3[@class='co_genericBoxHeader']/span[text()='Related practice areas']"));
    }

    public List<WebElement> countriesTabLinks() {
        return findElements(By.xpath("//div[contains(@id, 'coid_categoryBoxTab2SubPanel')]//a"));
    }

    public List<WebElement> globalGuides() {
        return findElements(By.xpath("//div[contains(@id, 'coid_categoryBoxTab3SubPanel')]//a"));
    }

    public WebElement linkOnLegalUpdateWidget(int linkNumber, String widget) {
        return retryingFindElement(By.xpath(".//h3[contains(text(), '" + widget
                + "')]/following-sibling::div[@class='co_genericBoxContent']//li[" + linkNumber + "]//a"));
    }
    public WebElement linkToJointVenturesInternationalTransactionGuide(String link){
        return waitForExpectedElement(By.xpath("//a[text() = '"+link+"']"));
    }
    public WebElement linkToTheDocumentWithGuid(String guid){
        return waitForExpectedElement(By.xpath("//*[contains(@href, '"+guid+"')]"));
    }
    public WebElement linkForMaximizingCountry(String country){
        return waitForExpectedElement(By.xpath("//*[@title='Maximize "+country+"']"));
    }
    public WebElement linkToArea(String guid){
        return waitForExpectedElement(By.xpath("//*[@id='coid_categoryBoxTabContents']//*[text()='"+guid+"']"));
    }
    public WebElement linkToTheCountryInGlobalGuides(String country){
        return waitForExpectedElement(By.id(country.toLowerCase()));
    }
    
	public boolean isDashboardDescriptionDisplayed() {
		return isElementDisplayed(By
				.xpath("//div[@id='coid_website_browseRightColumn']/*[contains(.,'Cross-border dashboard') and contains(.,'" + DASHBOARD_DESCRIPTION + "')]"));
	}

	public void clickDashboard() {
		retryingFindElement(By.xpath("//a[text()='View my dashboard']")).click();
		waitForPageToLoad();
		waitForPageToLoadAndJQueryProcessing();
	}

    public List<WebElement> listFeaturedLinks() {
        return waitForExpectedElements(By.xpath(".//div[@class='main-featured']//div[@class='co_featureBoxInner']//a"));
    }

    public WebElement specifiedLink(String linkText) {
        return waitForExpectedElement(By.linkText(linkText));
    }
}
