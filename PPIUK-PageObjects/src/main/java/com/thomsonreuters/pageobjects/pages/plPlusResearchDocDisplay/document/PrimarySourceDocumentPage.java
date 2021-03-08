package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;


public class PrimarySourceDocumentPage extends DocumentDisplayAbstractPage {

	private static final By DOCUMENT_TITLE = By
			.xpath(".//div[@id='co_docHeaderContainer']/div[@class='co_paragraph']/h1");
	private static final By JURISDICTION_OF_COURT = By.xpath(".//div[@class='co_docContentMetaInfoJurisdiction']/span");
	private static final By SPECIFIC_PROVISION_COVERAGE_SECTION = By.className("co_specificProvisionCoverage");
	private static final By RELATED_CONTENT = By.id("co_relatedContent");
	private static final By NUMBER_OF_LINKS_FOUND_RESULT = By.xpath(".//div[@id='co_relatedContent']/span");

	public WebElement documentTitle() {
		return waitForExpectedElement(DOCUMENT_TITLE);
	}

	public WebElement legislationLink(String legislationLinkText) {
		return waitForExpectedElement(By.linkText(legislationLinkText));
	}

	public WebElement justiceLink(String justiceLinkText) {
		return waitForExpectedElement(By.linkText(justiceLinkText));
	}

	public WebElement contentRefferingSection(String contentText) {
		return waitForExpectedElement(By.xpath(".//h2[contains(text(), '" + contentText + "')]"));
	}

	public WebElement jurisdictionOfCourt() {
		return waitForExpectedElement(JURISDICTION_OF_COURT);
	}

	public WebElement linkInPrimarySource(String linkText) {
		return waitForExpectedElement(By.linkText(linkText));
	}

	public WebElement linkInLinksToThisCaseSection(String linkText) {
		return findElement(By.xpath(".//a[@target='_blank' and contains(text(), '" + linkText + "')]"));
	}

	public WebElement parentOflinkInLinksToThisCaseSection(String linkText) {
		return waitForExpectedElement(By.xpath(".//a[@target='_blank' and contains(text(), '" + linkText + "')]/.."));
	}

	public WebElement specificProvisionCoverageSection() {
		return waitForExpectedElement(SPECIFIC_PROVISION_COVERAGE_SECTION);
	}

	public WebElement specificProvisionCoverageText(String provisionSectionText) {
		return findChildElement(specificProvisionCoverageSection(),
				By.xpath("//h2[contains(text(), '" + provisionSectionText + "')]"));
	}

	public WebElement linkInSpecificProvisionCoverageSection(String provisionSectionTextlinkText) {
		return findChildElement(specificProvisionCoverageSection(), By.partialLinkText(provisionSectionTextlinkText));
	}

	public WebElement relatedContent() {
		return waitForExpectedElement(RELATED_CONTENT);
	}

	public WebElement contentRefferingToThisCase(String contentRefferingText) {
		return findChildElement(relatedContent(),
				By.xpath(".//h2[contains(text(), '" + contentRefferingText + "')]"));
	}

	public WebElement showMoreLink(String showMoreLinkText) {
		return findChildElement(relatedContent(), By.linkText(showMoreLinkText));
	}

	public WebElement documentType(String documentTypeText) {
		return findChildElement(relatedContent(), By.xpath("//h3[contains(text(), '" + documentTypeText + "')]"));
	}

	public WebElement typeDocumentSection(String documentTypeText) {
		return waitForExpectedElement(By.xpath(".//h3[contains(text(), '" + documentTypeText
				+ "')]/following-sibling::ul[@class='co_assetList']"));
	}

	public WebElement linkOfSpecificDocumentType(String linkText, String documentTypeText) {
		return findChildElement(typeDocumentSection(documentTypeText), By.partialLinkText(linkText));
	}

	public WebElement typeOfDocumentInContentReferringSection(String documentTypeText) {
		return waitForExpectedElement(By.xpath(".//h3[contains(text(), '" + documentTypeText + "')]"));
	}

	public WebElement typeOfDocumentBelowPreviousDocumentType(String nextDocumentTypeText, String documentTypeText) {
		return waitForExpectedElement(By.xpath(".//h3[contains(text(), '" + documentTypeText
				+ "')]/..//following-sibling::li[@class='ng-scope']/h3[contains(text(), '" + nextDocumentTypeText
				+ "')]"));
	}

	public List<WebElement> listOfLinksByDocumentType(String documentTypeText) {
		return findElements(By.xpath(".//h3[contains(text(), '" + documentTypeText
				+ "')]/following-sibling::ul[@class='co_assetList']/li/a[@class='ng-binding']"));
	}

	public WebElement numberOfLinksFoundResult() {
		return waitForExpectedElement(NUMBER_OF_LINKS_FOUND_RESULT);
	}

	public List<WebElement> listOfLinksInContentRefferingSection() {
		return findElements(By.xpath(".//a[@class='ng-binding']"));
	}

	public WebElement contentOfMetaDataFields(String metaDataText) {
		return waitForExpectedElement(By.xpath(".//b[contains(text(), '" + metaDataText + "')]/following-sibling::span"));
	}

	public WebElement otherProvisionsSection(String otherProvisionsText) {
		return waitForExpectedElement(By.xpath(".//div[contains(text(), '" + otherProvisionsText + "')]"));
	}

	public WebElement linkInOtherProvisionsSection(String linkText, String otherProvisionsText) {
		return waitForExpectedElement(By.xpath(".//div[contains(text(), '" + otherProvisionsText
				+ "')]/../following-sibling::ul//a[contains(text(), '" + linkText + "')]"));
	}

	public WebElement specificProvisionSectionUnderOtherProvisions(String specificProvisionText,
			String otherProvisionsText) {
		return waitForExpectedElement(By.xpath(".//div[contains(text(), '" + otherProvisionsText
				+ "')]/../..//h2[contains(text(), '" + specificProvisionText + "')]"));
	}

	public WebElement otherProvisionStyle(String sectionNameText) {
		return waitForExpectedElement(By.xpath(".//div[contains(text(), '" + sectionNameText + "')]/../.."));
	}

}
