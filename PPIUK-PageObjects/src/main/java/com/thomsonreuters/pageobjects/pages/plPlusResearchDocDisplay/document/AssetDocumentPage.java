package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AssetDocumentPage extends DocumentDisplayAbstractPage {

	private static final By PARTY_NAMES = By.xpath(".//div[@id='co_docHeaderContainer']//*[contains(@class, 'co_title')]");
	private static final By LINK_TO_LEGAL_UPDATES = By
			.xpath(".//div[@id='co_legalUpdates']/ul[@class='co_assetList']//a");
	private static final By CASE_PAGE_TEXT = By
			.xpath(".//div[@id='co_docContentMetaInfo']//span[contains(text(),'Case page')]");
	private static final By CASE_META_DATA = By.id("co_docContentMetaInfo");
	private static final By DOWNLOAD_ICON = By.id("deliveryLinkRowDownload");
	private static final By DOWNLOAD_WINDOW = By.id("co_deliveryLightbox");
	private static final By DOWNLOAD_BUTTON = By.id("co_deliveryDownloadButton");
	private static final By MANE_OF_FILE_FOR_DOWNLOAD = By.xpath(".//div[@id='co_deliveryWaitMessageTitle']/em");
	private static final By DOWNLOAD_BUTTON_IN_READY_FOR_DOWNLOAD_WINDOW = By
			.id("coid_deliveryWaitMessage_downloadButton");
	private static final By WHERE_REPORDED_LIST = By.className("whereReportedList");
	private static final By CONTENT_BODY = By.xpath("//div[contains(@id,'co_docContentBody')]");
	private static final By TOP_BUTTON = By.xpath("//span[contains(text(),'Top')]");
	private static final By TABLE_OF_CONTENTS_SECTION = By.className("kh_toc-content");
	private static final By CASE_ASSET_DOC_CLASS = By.id("co_document_0");
	private static final By VALUE_OF_SPECIALIST_COURT = By
			.xpath(".//div[@class='co_docContentMetaInfoSpecialistCourt']/span");
	private static final By PRACTICE_NOTES_SECTION = By.className("ng-scope");
	private static final By DROPDOWN_BOX_WITH_THE_FORMAT_TO_DOWNLOAD_THE_DOCUMENT = By
			.id("co_delivery_format_fulltext");
	private static final By CHECKBOX_TABLE_OF_CONTENT = By.xpath(".//label[@for='coid_chkDdcLayoutTableOfContents']");
	
	public WebElement linkInAssetPage(String linkText) {
		return retryingFindElement(By.linkText(linkText));
	}

	public WebElement partyNames() {
		return retryingFindElement(PARTY_NAMES);
	}

	public WebElement bailiiLink(String bailiiLink) {
		return retryingFindElement(By.linkText(bailiiLink));
	}

	public WebElement linkToLegalUpdates() {
		return waitForExpectedElement(LINK_TO_LEGAL_UPDATES);
	}

	public WebElement hardcodedLink(String linkText) {
		return retryingFindElement(By.partialLinkText(linkText));
	}

	public WebElement celexLink(String celexLinkText) {
		return retryingFindElement(By.linkText(celexLinkText));
	}

	public WebElement westlawUkLink(String westlawUkLinkText) {
		return waitFluentForElement(By.linkText(westlawUkLinkText));
	}

	public WebElement jumpLink(String jumpLinkText) {
		return retryingFindElement(By.partialLinkText(jumpLinkText));
	}

	public WebElement casePageText() {
		return retryingFindElement(CASE_PAGE_TEXT);
	}

	public WebElement caseMetadata() {
		return retryingFindElement(CASE_META_DATA);
	}

	public WebElement metaDataField(String text) {
		return retryingFindElement(By.xpath(".//*[@id='co_docContentMetaInfo']//*[contains(text(), '" + text + "')]"));
	}

	public WebElement junpLinkSection(String jumpLinkText) {
		return retryingFindElement(By.xpath(".//h4[contains(text(),'" + jumpLinkText + "')]"));
	}

	public WebElement downloadIcon() {
		return retryingFindElement(DOWNLOAD_ICON);
	}

	public WebElement downloadWindow() {
		return retryingFindElement(DOWNLOAD_WINDOW);
	}

	public boolean isDeliveryWindowAbsent() {
		return waitForElementAbsent(DOWNLOAD_WINDOW);
	}

	public WebElement downloadButton() {
		return retryingFindElement(DOWNLOAD_BUTTON);
	}

	public WebElement readyForDownloadWindow(String readyForDownloadText) {
		return retryingFindElement(By.xpath(".//div[contains(text()," + "'" + readyForDownloadText + "'" + ")]"));
	}

	public WebElement nameOfFileForDownload() {
		return retryingFindElement(MANE_OF_FILE_FOR_DOWNLOAD);
	}

	public WebElement downloadButtonInReadyForDownloadWindow() {
		return retryingFindElement(DOWNLOAD_BUTTON_IN_READY_FOR_DOWNLOAD_WINDOW);
	}

	public WebElement whereReportedList() {
		return retryingFindElement(WHERE_REPORDED_LIST);
	}

	public WebElement contentBody() {
		return retryingFindElement(CONTENT_BODY);
	}

	public String allContentBodyElementsText() {
		List<WebElement> elements = retryingFindElements(CONTENT_BODY);
		String text = "";
		for (WebElement element : elements) {
			text = text + element.getText();
		}
		return text;
	}

	public WebElement topButton() {
		return retryingFindElement(TOP_BUTTON);
	}

	public WebElement tableOfContentButton(String tableOfContentText) {
		return retryingFindElement(By.xpath(".//span[contains(text()," + "'" + tableOfContentText + "'" + ")]"));
	}

	public WebElement tableOfContentSection() {
		return retryingFindElement(TABLE_OF_CONTENTS_SECTION);
	}

	public WebElement caseAssetDocClass() {
		return retryingFindElement(CASE_ASSET_DOC_CLASS);
	}

	public WebElement valueOfSpecialistCourt() {
		return retryingFindElement(VALUE_OF_SPECIALIST_COURT);
	}

	public WebElement practiceNotesSection() {
		return retryingFindElement(PRACTICE_NOTES_SECTION);
	}

	public WebElement practiceNotes(String practiceNotesText) {
		return findChildElement(practiceNotesSection(),
				By.xpath("//h3[contains(text()," + "'" + practiceNotesText + "'" + ")]"));
	}

	public WebElement linksInPracticeNotesSection(String linkText) {
		return findChildElement(practiceNotesSection(), By.partialLinkText(linkText));
	}

	public WebElement contentToAppendTab(String contentToAppendText) {
		return waitForExpectedElement(
				By.xpath("//li[not(contains(@class, 'Inactive')) and contains(@id, 'deliveryOptions')]//a[contains(., '" + contentToAppendText +"')]"));
	}

	public boolean isTabDisplayed(String tab) {
		return isElementDisplayed(By.xpath("//li[contains(@id, 'deliveryOptions')]//a[contains(., '" + tab + "')]"));
	}

	public WebElement relatedAssetPageCheckBox(String relatedAssetPageText) {
		return retryingFindElement(By.xpath("//strong[contains(text()," + "'" + relatedAssetPageText + "'" + ")]"));
	}

	public WebElement linkInLegalUpdatesSection(String linkText, String sectionName) {
		return waitForExpectedElement(By.xpath(".//h2[contains(text(),'" + sectionName
				+ "')]/following-sibling::*//a[contains(text(), '" + linkText + "')]"));
	}

	public WebElement tabInDownloadWindow(String tabName) {
		return retryingFindElement(By.xpath(".//ul[@class='co_tabs']//a[contains(text(), '" + tabName + "')]"));
	}

	public WebElement dropdownBoxWithTheFormatToDownloadTheDocument() {
		return retryingFindElement(DROPDOWN_BOX_WITH_THE_FORMAT_TO_DOWNLOAD_THE_DOCUMENT);
	}

	public WebElement formatInDropdownBox(String formatName) {
		return retryingFindElement(By.xpath(".//*[@id='co_delivery_format_fulltext']/option[contains(text(), '"
				+ formatName + "')]"));
	}

	public WebElement checkboxTableOfContent() {
		return retryingFindElement(CHECKBOX_TABLE_OF_CONTENT);
	}

	public WebElement inputCheckboxTableOfContent() {
		return findChildElement(checkboxTableOfContent(), By.id("coid_chkDdcLayoutTableOfContents"));
	}

	public WebElement sectionHeader(String headerName) {
		return retryingFindElement(By.xpath(".//h2[contains(text(), '" + headerName + "')]"));
	}
	
	public List<WebElement> listOfLinksInLegalUpdatesSection() {
		return findElements(By.xpath(".//div[@id='co_legalUpdates']/ul[@class='co_assetList']//a"));
	}
	
	public List<WebElement> listOfLinksInLinksToThisPageSection() {
		return findElements(By.xpath(".//div[@id='co_linksToPage']/ul[@class='co_assetList']//a"));
	}
	
	public WebElement linkInLegalApdatesSection(String linkText, String sectionName) {
		return retryingFindElement(By.xpath(".//h2[contains(text(),'" + sectionName
				+ "')]/following-sibling::ul//a[contains(text(), '" + linkText + "')]"));
	}
}
