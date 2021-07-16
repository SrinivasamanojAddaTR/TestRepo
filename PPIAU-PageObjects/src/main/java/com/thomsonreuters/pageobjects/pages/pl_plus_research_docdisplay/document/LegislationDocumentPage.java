package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

public class LegislationDocumentPage extends DocumentDisplayAbstractPage {

	private static final By LEGIS_AOP_HEADER_XPATH = By
			.xpath("//h2[@class='co_printHeading']//a[contains(.,'Whole Document')]");
	private static final By LEGIS_PROVISION_TEXT_LABEL_XPATH = By
			.xpath("//*[@id='kh_tocContainer']//li[contains(.,'Provision')]//li[contains(.,'Text')]");
	private static final String LEGIS_CHAPTER_HEADER_XPATH = "//*[@class='co_printHeading']//strong/a[contains(., '%s')]";
	public static final By VERSIONS_TABLE = By.className("co_provisionVersionsTable");
	private static final By LEGIS_VERSION_DROPDOWN_LINK = By.xpath("//*[@class='co_dropDownAnchor']");
	private static final String LEGIS_PROVISION_NAVIGATION_LINK = ".//*[@class='co_provisionNavigation']//*[contains(text(), '%s')]";
	private static final String NAVIGATION_LINK_HOVER_OVER = ".//*[@class='co_provisionNavigation']//*[@class='tooltip-content' and contains(text(),'%s')]";
	private static final String STATUTORY_ANNOTATIONS_SECTION = ".//*[@id='co_AnnotationDocument']";
	private static final String TABLE_WITHIN_THE_SUBSECTION = ".//h4[contains(text(), '%s')]/following-sibling::div//table";
	private static final String LEGISLATION_DOC_WIDGET = ".//h2[text()='%s']/..";

	public List<WebElement> amendmentsNumbers() {
		return waitForExpectedElements(By.xpath(".//*[@id='co_footnoteSection']//*[@class='co_footnoteNumber']//a"));
	}

	public List<WebElement> amendmentsDescription() {
		return waitForExpectedElements(By.xpath(".//*[@id='co_footnoteSection']//*[@class='co_footnoteBody']"));
	}

	public WebElement footnoteReference(String number) {
		return waitForExpectedElement(By.xpath(".//*[@id='co_footnoteReference_" + number + "']/a"));
	}

	public WebElement footnoteText(String text) {
		return waitForExpectedElement(By.xpath(".//*[@class='co_footnoteHoverTitle']/*[text()='" + text + "']"));
	}

	public WebElement textInSection(String section, String text) {
		return contentBody().findElement(
				By.xpath(".//h2[contains (.,'" + section + "')]/following-sibling::div[contains (.,'" + text + "')]"));
	}

	public WebElement oneSectionAfterAnother(String section1, String section2) {
		return contentBody().findElement(
				By.xpath(".//h2[contains (.,'" + section1 + "')]/../following-sibling::div/h2[contains (.,'" + section2
						+ "')]"));
	}

	public boolean isSubsectionTitleExists() {
		return isExists(By.xpath(".//*[@id='co_docContentBody']//div[@class='co_paragraph']/h2/strong"));
	}

	public List<WebElement> paragraphsInSection(String section) {
		return waitForExpectedElements(By.xpath(".//*[contains(text(), '" + section
				+ "')]/following-sibling::*//*[@class='co_paragraph']"));
	}

	public WebElement sectionInTheDocument(String section) {
		return waitForExpectedElement(By.xpath(".//*[@id='co_docContentBody']//*[contains(text(), '" + section + ")]"));
	}

	public List<WebElement> linksInSection(String section) {
		return waitForExpectedElements(By.xpath(String.format(".//*[contains(text(), '%s')]/../following-sibling::a",
				section)));
	}

	public WebElement actSITitle() {
		return findChildElement(documentTitle(), By.xpath(".//a"));

	}

	public void waitUntilLoadIndicatorDisappears() {
		waitForElementToDissappear(By.xpath("//*[@class='co_loading co_clear']"));
	}

	/**
	 * This is the text "Whole Document" which is present on all arrangement
	 * documents
	 */

	public WebElement wholeDocumentTextOnAOP() {
		return waitForExpectedElement(By.xpath("//*[@id='co_link_N100C0'][contains(text(),'Whole Document')]"));
	}

	/**
	 * This is the text "Provision text" which is present on provision documents
	 * in the table of contents pane
	 */

	public WebElement provisionTextLabelinTOC() {
		return waitForExpectedElement(By.xpath("//*[@id='kh_tocContainer']//span[contains(text(),'Provision text')]"));
	}

	/**
	 * Individual search result legislation chapter level - this is an object
	 * representing the chapter heading on a page listing the provisions
	 */

	public WebElement chapterHeader(String text) {

		return waitForExpectedElement(By.xpath("//*[@class='co_printHeading']//strong/a[contains(text(),'" + text
				+ "')]"));
	}

	public boolean isLegisAOPOrSIHeaderDisplayed() {
		return isElementDisplayed(LEGIS_AOP_HEADER_XPATH);
	}

	public boolean isLegisProvisionTextLabelDisplayed() {
		return isElementDisplayed(LEGIS_PROVISION_TEXT_LABEL_XPATH);
	}

	public boolean isChapterHeaderDisplayed(String text) {
		return isElementDisplayed(By.xpath(String.format(LEGIS_CHAPTER_HEADER_XPATH, text)));
	}

	public By provisionVersion(String version) {
		return By.xpath(".//*[@class='co_docContentMetaVersion' and contains(., 'Version') and contains(., '" + version
				+ "')]");
	}

	public By provisionVersionDate(String date) {
		return By.xpath(".//*[@class='co_docContentMetaDateProvision']/span[text()='" + date + "']");
	}

	public WebElement versionsTable() {
		return waitForExpectedElement(VERSIONS_TABLE);
	}

	public WebElement versionsTableRow(String rowNumber) {
		return findChildElement(versionsTable(), By.xpath(".//tbody/tr[" + rowNumber + "]"));
	}

	/**
	 * object representing just the word "Version" on the page in the metadata
	 * section
	 */

	public boolean isVersionTextDisplayed() {
		return isElementDisplayed(By.xpath("//*[@class='co_docContentMetaVersion']//b[contains(text(),'Version')]"));
	}

	/**
	 * object representing the date for a version within the metadata section of
	 * a provision - no date passed through as a variable in order to use this
	 * to check for suppression of this information
	 */

	public boolean isVersionDateDisplayed() {
		return isElementDisplayed(By.xpath("//*[@class='co_docContentMetaVersion']//span"));
	}

	public List<WebElement> versionsTableColumns() {
		return versionsTable().findElements(By.xpath(".//thead//th"));
	}

	public List<WebElement> versionsTableRows() {
		return versionsTable().findElements(By.xpath(".//tbody/tr"));
	}

	public List<WebElement> versionsTableDates() {
		return versionsTable().findElements(By.xpath(".//tbody/tr/td[2]"));
	}

	public List<WebElement> provisionVersionsTableRowValues(String rowNumber) {
		return versionsTable().findElements(By.xpath(".//tbody/tr[" + rowNumber + "]/td"));
	}

	public By dropdownVersionsIconExpanded() {
		return By
				.xpath(".//*[contains(@class, 'co_provisionVersionsWidget') and contains(@class, 'expanded')]//span[contains(@class, 'icon_down_arrow_blue')]");
	}

	public WebElement closeButton() {
		return waitForExpectedElement(By.xpath(".//a[contains(@class, 'co_provisionVersionsWidgetClose')]/span"));
	}

	/**
	 * Object representing version table dropdown link in the metadata section
	 */

	public boolean isLegisMetaVersionDropdownLinkDisplayed() {
		return isElementDisplayed(LEGIS_VERSION_DROPDOWN_LINK);
	}

	public By dropdownVersionsIcon() {
		return By
				.xpath(".//*[contains(@class, 'co_provisionVersionsWidget')]//span[contains(@class, 'icon_down_arrow_blue')]");
	}

	public WebElement provisionNavigationLink(String link) {
		return waitForExpectedElement(By.xpath(String.format(LEGIS_PROVISION_NAVIGATION_LINK, link)));
	}

	public boolean isProvisionNavigationLinkDisplayed(String link) {
		return isElementDisplayed(By.xpath(String.format(LEGIS_PROVISION_NAVIGATION_LINK, link)));
	}

	public boolean isNavigationLinkHoverOverDisplayed(String link) {
		return isElementDisplayed(By.xpath(String.format(NAVIGATION_LINK_HOVER_OVER, link)));
	}

	public boolean isThePrevNavigationButtonDisplayedBelowTheNextButton() {
		return isElementDisplayed(By
				.xpath(".//a[contains(text(), '> Next')]/../../following-sibling::li//a[contains(text(), '< Prev')]"));
	}

	public Boolean isviewBillAmendedVersionLinkPresent(String text) {

		return isElementDisplayed(By.xpath("//div[@id='co_docContentMetaInfo']//a[contains(.,'" + text + "')]"));
	}

	public WebElement viewBillAmendedVersionLink(String text) {

		return waitForExpectedElement(By.xpath("//div[@id='co_docContentMetaInfo']//a[contains(.,'" + text + "')]"));
	}

	public WebElement statutoryAnnotationsSection() {
		return waitForExpectedElement(By.xpath(STATUTORY_ANNOTATIONS_SECTION));
	}

	public boolean isStatutoryAnnotationsHeaderDisplayed() {
		return isElementDisplayed(By.xpath(STATUTORY_ANNOTATIONS_SECTION + "//h2[text()='Statutory Annotations']"));
	}

	public boolean isStatutoryAnnotationsTextDisplayed(String text) {
		return isElementDisplayed(By.xpath(STATUTORY_ANNOTATIONS_SECTION
				+ "//div[@id='co_AnnotationDocumentSource']//*[contains(text(), '" + text + "')]"));
	}

	public WebElement expandCollapseAnnotationSectionIcon() {
		return waitForExpectedElement(By.xpath(STATUTORY_ANNOTATIONS_SECTION
				+ "//a[@id='co_toggleLegislationAnnotation']/span"));
	}

	public WebElement linkWithinTheSASection(String linkText) {
		return waitForExpectedElement(By.xpath(STATUTORY_ANNOTATIONS_SECTION + "/div//a[text()='" + linkText + "']"));
	}

	public boolean isSubsectionWithTextDisplayedWithinTheSASection(String subsection, String text) {
		return isElementDisplayed(By.xpath(STATUTORY_ANNOTATIONS_SECTION + "//*[contains(., '" + subsection
				+ "')]/following-sibling::div[contains(., '" + text + "')]"));
	}

	public WebElement billDocumentMessage() {
		return waitForExpectedElement(By
				.xpath(".//*[@id='co_docContentBody']//div[contains(@class, 'co_billShadowVersion')]"));
	}

	public boolean isSubsectionWithTextDisplayed(String subsection, String text) {
		return isElementDisplayed(By.xpath("//h4[contains(., '" + subsection
				+ "')]/following-sibling::div[contains(., '" + text + "')]"));
	}

	public WebElement tableWithinTheSubsection(String subsection) {
		return waitForExpectedElement(By.xpath(String.format(TABLE_WITHIN_THE_SUBSECTION, subsection)));
	}

	public boolean isTableDisplayedWithinTheSubsection(String subsection) {
		return isElementDisplayed(By.xpath(String.format(TABLE_WITHIN_THE_SUBSECTION, subsection)));
	}

	public List<WebElement> tableColumnTextWithinTheSubsection(String column, String subsection) {
		return tableWithinTheSubsection(subsection).findElements(By.xpath("//td[" + column + "]"));
	}

	public WebElement linkWithinBillDocumentMessage(String link) {
		return waitForExpectedElement(By.xpath(".//*[@class='co_paragraph co_billShadowVersion']/a[text()='" + link
				+ "']"));
	}

	public WebElement legislationDocWedget(String widget) {
		return waitForElementVisible(By.xpath(String.format(LEGISLATION_DOC_WIDGET, widget)));
	}

	public boolean isLegislationDocWidgetDisplayed(String widget) {
		return isElementDisplayed(By.xpath(String.format(LEGISLATION_DOC_WIDGET, widget)));
	}

	public WebElement tableOfAmendmentsStatusIcon(String section, String entry) {
		return findChildElement(legislationDocWedget(section),
				By.xpath("./div[" + entry + "]//span[contains(@class, 'co_ukReferenceStatusIcon')]"));

	}
	
	public boolean isWidgetStatusIconDisplayed(String entry, String widget) {
		return isElementDisplayed(By.xpath(String.format(LEGISLATION_DOC_WIDGET + "/div[" + entry
				+ "]//span[contains(@class, 'co_ukReferenceStatusIcon')]", widget)));
	}

	public List<WebElement> provisionTitles(String section, String entry) {
		return legislationDocWedget(section).findElements(
				By.xpath("./div[" + entry + "]//div[contains(@class, 'co_tableCellWidth160')]/*"));
	}

	public List<WebElement> amendmentNotes(String section, String entry) {
		return legislationDocWedget(section)
				.findElements(
						By.xpath("./div["
								+ entry
								+ "]//div[contains(@class, 'co_tableCellText')]//div[contains(@class, 'co_tableCellText')]/div/*[contains(@class,'ng-binding')]"));
	}

	public List<WebElement> effectiveDate(String section, String entry) {
		return legislationDocWedget(section).findElements(
				By.xpath("./div[" + entry
						+ "]/div[contains(@class, 'co_tableCellText')]//div[contains(@class, 'co_italic')]"));
	}

	public WebElement linkWithinSection(String section, String link) {
		return findChildElement(legislationDocWedget(section), By.xpath("//a[text()='" + link + "']"));
	}

	public boolean isTextDislayedUnderWidget(String widget, String entry, String text) {
		return isElementDisplayed(By.xpath(String.format(LEGISLATION_DOC_WIDGET + "/div[" + entry
				+ "]//div[contains(@class, 'co_tableCellWidth160')][contains(., \"" + text + "\")]", widget)));
	}

	public List<WebElement> linksWithinWidget(String widget, String entry) {
		return legislationDocWedget(widget).findElements(
				By.xpath("./div[" + entry + "]/div[contains(@class, 'co_tableCellText')]//a"));
	}

	public List<WebElement> textsWithinWidget(String widget, String entry) {
		return legislationDocWedget(widget).findElements(
				By.xpath("./div[" + entry
						+ "]/div[contains(@class, 'co_tableCellText')]//span[contains(@class, 'ng-binding')]"));
	}

	public List<WebElement> datesWithinTheWidgetInEntry(String widget, String entry) {
		return legislationDocWedget(widget).findElements(
				By.xpath("./div[" + entry
						+ "]/div[contains(@class, 'co_tableCellText')]//div[contains(@ng-class, 'isFutureDate')]"));
	}

	public List<WebElement> allDatesWithinTheWidget(String widget) {
		return legislationDocWedget(widget).findElements(
				By.xpath(".//div[contains(@class, 'co_tableCellText')]//div[contains(@ng-class, 'isFutureDate')]"));
	}

    public WebElement arrangementOfProvisionSubtext() {
        return waitForExpectedElement(By.xpath("//*[@id='co_docHeaderContainer']//h1"));
    }

    public boolean isArrangementOfProvisionSubtextALink(String text) {
        return isElementDisplayed(By.xpath("//div[@id='co_docHeaderContainer']//h1/a[contains(., " + Quotes.escape(text) + ")]"));
    }


}
