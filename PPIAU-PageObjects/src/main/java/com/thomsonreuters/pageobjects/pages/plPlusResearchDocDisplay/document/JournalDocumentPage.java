package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import com.thomsonreuters.pageobjects.common.PageActions;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class JournalDocumentPage extends DocumentDisplayAbstractPage {

	PageActions pageActions;

	private static final By FOOTNOTE_SECTION = By.id("co_footnoteSection");
	private static final By LIGHT_BOX = By.id("co_footnoteHoverDiv");
	private static final By LINK_IN_LIGHTBOX = By.xpath(".//div[2]/div/div/a");
	private static final By JOURNAL_ABSTRACT_HEADER_XPATH = By.xpath("//*[@id='co_docContentBody']//div[contains(text(),'Abstract')]");

	public WebElement footnoteSection() {
		return waitForElementVisible(FOOTNOTE_SECTION);
	}

	public WebElement lightBox() {
		return waitForElementVisible(LIGHT_BOX);
	}

	public WebElement linkInLightBox() {
		return findChildElement(lightBox(), LINK_IN_LIGHTBOX);
	}

	public String clickOnLinkInLightBox() {
		String firstTitle = getPageTitle();
		linkInLightBox().click();
		return firstTitle;
	}

	public WebElement footnoteNumberInFootnotesSection(String number) {
		return footnoteSection().findElement(
				By.xpath(".//span[@id='co_footnote_" + number + "']/a | .//span[@id='FN" + number + "']"));
	}

	public boolean isFootnoteNumberInFootnotesSectionDisplayed(String number) {
		return isElementDisplayed(By.xpath(".//span[@id='co_footnote_" + number + "']/a"));
	}

	public WebElement fullFootnoteInFootnotesSection(String number) {
		return findChildElement(footnoteSection(), By.xpath("./div[" + number + "]"));
	}

	public WebElement linkInFootnoteBodyInFootnotesSection(String number) {
		return findChildElement(fullFootnoteInFootnotesSection(number),
				By.xpath(
						".//div[@class='co_footnoteBody']/div/a | .//div[@class='co_footnoteBody']/a | .//div[@class='co_footnoteBody']/em/a"));
	}

	public WebElement linkInFootnoteBodyInFootnotesSection(String linkText, String number) {
		return findChildElement(fullFootnoteInFootnotesSection(number),
				By.xpath(".//div[@class='co_footnoteBody']/div/a[contains(text(), \"" + linkText
						+ "\")] | .//div[@class='co_footnoteBody']/a[contains(text(), \"" + linkText + "\")]"));
	}

	public WebElement footnoteNumberInDocument(String number) {
		return waitForExpectedElement(By.xpath(".//sup[@id='co_footnoteReference_" + number + "']/a"));
	}

	public WebElement footnoteNumberInStickyTitle() {
		return waitForElementVisible(By.xpath(".//div[@id='co_docStickyHeader']//h1/sup/a"));
	}
	
	public WebElement stickyHeader(){
		return waitForExpectedElement(By.id("co_docStickyHeader"));
	}

	public WebElement documentSubsection(String subsection) {
		return waitForExpectedElement(By.xpath(".//*[@id='co_docContentBody']//*[contains(text(), \"" + subsection
				+ "\")]"));
	}

	public WebElement valueOfSubsection(String subsection) {
		return waitForExpectedElement(By.xpath(".//*[@id='co_docContentBody']//*[contains(text(), \"" + subsection
				+ "\")]/.."));
	}

	public WebElement providedByWlukIcon() {
		return waitForExpectedElement(By.xpath(".//*[@id='co_docContentHeader']//img"));
	}

	public WebElement linkInSubsection(String linkText, String subsection) {
		return waitForExpectedElement(By.xpath(".//*[contains(text(), '" + subsection
				+ "')]/following-sibling::*//a[contains(text(), '" + linkText + "')]"));
	}

	public WebElement journalDocumentType() {
		return findChildElement(metaContent(), By.xpath(".//*[@class='co_docContentMetaInfoArticleType']/span"));
	}

	public WebElement fieldInMetadata(String field) {
		return findChildElement(metaContent(), By.xpath("//*[contains(text(), '" + field + "')]"));
	}

	public WebElement valueOfFieldInMetadata(String field) {
		return findChildElement(metaContent(),
				By.xpath("//*[contains(text(), '" + field + "')]/following-sibling::span"));
	}

	public WebElement publicationLinkInMetadata() {
		return findChildElement(metaContent(), By.xpath("//*[@class='co_docContentMetaInfoJournalIndexed']//a"));
	}

	public WebElement citationLinkInMetadata() {
		return findChildElement(metaContent(), By.xpath("//*[@class='co_docContentMetaInfoJournalArticle']//a"));
	}

	public WebElement citationTextInMetadata() {
		return findChildElement(metaContent(), By.xpath("//*[@class='co_docContentMetaInfoCitation']/span"));
	}

	public List<WebElement> imagesInDocContent() {
		return contentBody().findElements(By.xpath(".//img"));
	}

	public WebElement captionBelowTheImage(String text) {
		return contentBody().findElement(By.xpath(".//span[@class='co_docFigureDescription' and contains(.,'" + text + "')]"));
	}

	public List<WebElement> starPageText() {
		return findElements(By.xpath(".//*[@id='co_docContentBody']//*[@class='co_starPage']"));
	}

	public List<WebElement> nonDisplaybleImages() {
		return findElements(By.className("co_nonDisplayableImage"));
	}

	public List<WebElement> fieldsOfTheSubsectionColumn() {
		return findElements(By
				.xpath(".//div[@class = 'co_docRowTableDisplay']/div[contains(@class, 'docCellTableDisplay')]"));
	}

	public List<WebElement> fieldsOfTheColumnOfSubsectionValues() {
		return findElements(By
				.xpath(".//div[@class='co_docRowTableDisplay']/div[contains(@class, 'docCellTableDisplay')]/following-sibling::div"));
	}

	public WebElement journalAbstractHeading() {

		return waitForExpectedElement(By.xpath("//*[@id='co_docContentBody']//div[contains(text(),'Abstract')]"));
	}

	public boolean isJournalAbstractHeaderDisplayed() {
		return isElementDisplayed(JOURNAL_ABSTRACT_HEADER_XPATH);
	}


}
