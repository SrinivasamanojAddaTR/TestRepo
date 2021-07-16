package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document;

import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.common.WindowHandler;
import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.enums.DocumentSecondaryLink;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.enums.Jurisdiction;
import com.thomsonreuters.pageobjects.utils.document.DateFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


import org.springframework.util.StringUtils;

/**
 * This Page object is to identify the Provision document elements and depicts
 * the page functionality.
 * <p/>
 */

public class ProvisionPage extends DocumentDisplayAbstractPage {

	public static final By DOC_TITLE = By.xpath("//div/*[starts-with(@class,'co_title')]");

	private static final By MODIFICATIONS_HEADER_LOCATOR = By
			.xpath(".//h2[contains(@class,'co_printHeading') and contains(text(),'Modifications')]");
	private static final By MODIFICATIONS_SECTION_LOCATOR = By
			.cssSelector(".//h2[contains(@class,'co_printHeading') and contains(text(),'Modifications')]/../..");
	private static final By TITLE_OF_THE_ACT_LOCATOR = By.cssSelector("");
	private static final By STATUS_LOCATOR = By.cssSelector("");
	private static final By PART_OF_THE_ACT_LOCATOR = By.cssSelector("");
	private static final By YEAR_OF_THE_ACT_LOCATOR = By.cssSelector("");
	private static final By INDIVIDUAL_SECTION_OF_THE_ACT_LOCATOR = By
			.cssSelector("");
	private static final By PROVISION_VERSION_LOCATOR = By.cssSelector("");
	private static final By PROVISION_EFFECTIVE_DATE_LOCATOR = By
			.cssSelector("");
	private static final By BACK_TO_DOCUMENT_LINK_LOCATOR = By.cssSelector("");
	private static final By SHOW_HIDE_BUTTON = By.cssSelector("");
	public static final String LEGISLATION_DOC_TYPE_LOCATOR = "div.co_title+div";
	private static final String CHILD_LINK_MESSAGE = " ChildLink name is required.";
	private static final String JURISDICTION_NAME_MESSAGE = "Jurisdiction name is required.";
	private static final String CONTEXT = "context";

	private static final By JURISDICTION_TEXT = By
			.xpath(".//*[@id='co_docContentBody']/div[@class='co_paragraph co_underline']/h2/strong");

	private static final By DOC_CONTENT = By.className("co_footnoteSection");

	private static final By DOCUMENT_META_INFO = By.id("co_docContentMetaInfo");

	protected WindowHandler windowHandler;

	protected DateFormat dateFormat;

	protected PageActions pageActions;

	/**
	 * This method verifies given title is present in document header and
	 * returns the boolean value.
	 *
	 * @param title
	 * @return boolean
	 */

	@Override
	public boolean isDocumentHeaderPresent(String title) {
		if (StringUtils.isEmpty(title)) {
			throw new IllegalArgumentException(" Document Title is required.");
		}
		try {
			return waitForExpectedElement(DOC_TITLE).getText().contains(title);
		} catch (TimeoutException te) {
			LOG.info(CONTEXT, te);
		}
		return false;
	}

	/**
	 * This method to identify whether modifications present for the particular
	 * provision or not.
	 *
	 * @return boolean
	 */
	public boolean isModificationsAvailable() {
		return (isModificationsHeaderPresent() && isModificationsSectionPresent());
	}

	/**
	 * This method does the verification of Modifications Section presence and
	 * returns the boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isModificationsSectionPresent() {
		return isElementDisplayed(MODIFICATIONS_SECTION_LOCATOR);
	}
	

	/**
	 * This method does the verification of Modifications Header presence and
	 * returns the boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isModificationsHeaderPresent() {
		return isElementDisplayed(MODIFICATIONS_HEADER_LOCATOR);
	}

	/**
	 * This method verifies the provision's title is displayed and returns the
	 * boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isTitleOfTheActDisplayed() {
		return isElementDisplayed(TITLE_OF_THE_ACT_LOCATOR);
	}

	/**
	 * This method verifies the provision's Year of the act is displayed and
	 * returns the boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isYearOfTheActDisplayed() {
		return isElementDisplayed(YEAR_OF_THE_ACT_LOCATOR);
	}

	/**
	 * This method verifies the provision's Part of the Act is displayed and
	 * returns the boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isPartOfTheActDisplayed() {
		return isElementDisplayed(PART_OF_THE_ACT_LOCATOR);
	}

	/**
	 * This method verifies the provision's Individual Section of the Act is
	 * displayed and returns the boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isIndividualSectionOfTheActDisplayed() {
		return isElementDisplayed(INDIVIDUAL_SECTION_OF_THE_ACT_LOCATOR);
	}

	/**
	 * This method verifies the provision's Status is displayed and returns the
	 * boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isStatusDisplayed() {
		return isElementDisplayed(STATUS_LOCATOR);
		
	}

	/**
	 * This method verifies the provision's Version is displayed and returns the
	 * boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isVersionDisplayed() {
		return isElementDisplayed(PROVISION_VERSION_LOCATOR);
	}

	/**
	 * This method verifies the provision's Effective Date is displayed and
	 * returns the boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isProvisionEffectiveDateDisplayed() {
		return isElementDisplayed(PROVISION_EFFECTIVE_DATE_LOCATOR);
	}

	/**
	 * This method is to verify the given Jurisdiction links and blocks are
	 * present in the navigation and document as well.
	 *
	 * @param jurisdiction
	 * @return boolean
	 */
	public boolean isJuridictionsDisplayed(Jurisdiction jurisdiction) {
		if (jurisdiction == null) {
			throw new IllegalArgumentException(JURISDICTION_NAME_MESSAGE);
		}

		if (isJurisdictionNavigationLinkDisplayed(jurisdiction)) {
			return (isJurisdictionBlockPresent(jurisdiction) && isJurisdictionDocumentLinksPresent(jurisdiction));
		} else {
			return isDocumentHeaderPresent(jurisdiction
					.getJurisdictionLinkName());
		}
	}

	/**
	 * This method does the verification of given jurisdiction navigation link
	 * is displayed or not.
	 *
	 * @param jurisdiction
	 * @return boolean
	 */
	
	public boolean isJurisdictionNavigationLinkDisplayed(
			Jurisdiction jurisdiction) {
		if (jurisdiction == null) {
			throw new IllegalArgumentException(JURISDICTION_NAME_MESSAGE);
		}
		return isElementDisplayed(jurisdiction.getJurisdictionNavigationLinkLocator());
	}
	
	
	

	/**
	 * This method does the verification of given jurisdiction block is present
	 * or not in the document.
	 *
	 * @param jurisdiction
	 * @return boolean
	 */
	
	public boolean isJurisdictionBlockPresent(Jurisdiction jurisdiction) {
		if (jurisdiction == null) {
			throw new IllegalArgumentException(JURISDICTION_NAME_MESSAGE);
		}
		return isElementDisplayed(jurisdiction.getJurisdictionBlockLocator());
	}

	/**
	 * This method does the verification of given jurisdiction document link is
	 * present or not in the document.
	 *
	 * @param jurisdiction
	 * @return boolean
	 */
	
	public boolean isJurisdictionDocumentLinksPresent(Jurisdiction jurisdiction) {
		if (jurisdiction == null) {
			throw new IllegalArgumentException(JURISDICTION_NAME_MESSAGE);
		}
		return isElementDisplayed(jurisdiction.getJurisdictionDocumentLinkLocator());
	}

	/**
	 * This method verifies the given internal/external link is present or not
	 * inside the Annotations block and returns the boolean value accordingly.
	 *
	 * @param link
	 * @return boolean
	 */
	public boolean isLinkPresentInAnnotations(String link) {
		if (StringUtils.isEmpty(link)) {
			throw new IllegalArgumentException(
					" Internal/External link name is required.");
		}
		try {
			return getDisplayedBlockElement(DocumentSecondaryLink.ANNOTATIONS)
					.findElement(By.linkText("link")).isDisplayed();
		} catch (PageOperationException pe) {
			LOG.info(CONTEXT, pe);
		}
		return false;
	}
	

	/**
	 * This method does the selection of the given internal/external present in
	 * the Annotations block.
	 *
	 * @param link
	 */
	public void selectLinkInAnnotations(String link) {
		if (StringUtils.isEmpty(link)) {
			throw new IllegalArgumentException(
					" Internal/External link name is required.");
		}
		getDisplayedBlockElement(DocumentSecondaryLink.ANNOTATIONS)
				.findElement(By.linkText(link)).click();
	}

	/**
	 * This method verifies the New Browser is opened with the given link name
	 * and returns the boolean value accordingly. As an extra step, this method
	 * is closing the new window to make sure the functionality continues with
	 * the actual window session.
	 *
	 * @param link
	 * @return
	 */
	public boolean isNewBrowserOpenedWithExternalLink(String link) {
		if (StringUtils.isEmpty(link)) {
			throw new IllegalArgumentException(
					" External link name is required.");
		}

		try {
			windowHandler.switchWindow(link);
			windowHandler.closeWindow(link);
			return true;
		} catch (Exception t) {
			LOG.info(CONTEXT, t);
		}
		return false;
	}

	/**
	 * This method is to verify the given child link's section is expanded or
	 * not on the right hand side of the document area.
	 *
	 * @param childLink
	 * @return boolean
	 */
	public boolean iSectionExpanded(String childLink) {
		if (StringUtils.isEmpty(childLink)) {
			throw new IllegalArgumentException(CHILD_LINK_MESSAGE);
		}
		DocumentSecondaryLink.getLink(childLink);
		return false;
	}

	/**
	 * This methos is to verify the show/hide button present or not on the child
	 * link section on the document.
	 *
	 * @param childLink
	 * @return boolean
	 */
	public boolean iShowHideButtonPresent(String childLink) {
		if (StringUtils.isEmpty(childLink)) {
			throw new IllegalArgumentException(CHILD_LINK_MESSAGE);
		}

		try {
			return getDisplayedBlockElement(
					DocumentSecondaryLink.valueOf(childLink)).findElement(
					SHOW_HIDE_BUTTON).isDisplayed();
		} catch (TimeoutException pe) {
			LOG.info(CONTEXT, pe);
		}
		return false;
	}

	/**
	 * This method does the selection of the show/hide button present on child
	 * link section.
	 *
	 * @param childLink
	 */
	public void clickOnShowHideButton(String childLink) {
		if (StringUtils.isEmpty(childLink)) {
			throw new IllegalArgumentException(CHILD_LINK_MESSAGE);
		}

		try {
			getDisplayedBlockElement(DocumentSecondaryLink.valueOf(childLink))
					.findElement(SHOW_HIDE_BUTTON).click();
		} catch (TimeoutException pe) {
			throw new PageOperationException(
					"Exceeded time to find the ShowHideButton link on :"
							+ childLink);
		}
	}

	/**
	 * This method is to verify the Back to document link is present on the
	 * General notes section.
	 *
	 * @return boolean
	 */
	
	public boolean isBackToPrimaryLegislationDocumentFromNotesLink() {
		return isElementDisplayed(BACK_TO_DOCUMENT_LINK_LOCATOR);
	}

	/**
	 * This method is to select the Back to document link present on the General
	 * notes section.
	 */
	public void clickOnBackToMainDocument() {
		try {
			waitForElementPresent(BACK_TO_DOCUMENT_LINK_LOCATOR).click();
		} catch (TimeoutException pe) {
			throw new PageOperationException(
					"Exceeded time to find the BackToDocument link :"
							+ BACK_TO_DOCUMENT_LINK_LOCATOR);
		}
	}

	/**
	 * This method is to verify the given internal link is present on provision
	 * modifications section.
	 *
	 * @param link
	 * @return boolean
	 */
	public boolean isLinkPresentInModifications(String link) {
		if (StringUtils.isEmpty(link)) {
			throw new IllegalArgumentException(
					" Internal/External name is required.");
		}

		try {
			return getDisplayedBlockElement(DocumentSecondaryLink.MODIFICATIONS)
					.findElement(By.linkText(link)).isDisplayed();
		} catch (PageOperationException pe) {
			LOG.info(CONTEXT, pe);
		}
		return false;
	}

	/**
	 * This method is to click on given link present on the provision
	 * modifications section.
	 *
	 * @param link
	 */
	public void selectLinkInModifications(String link) {
		if (StringUtils.isEmpty(link)) {
			throw new IllegalArgumentException(
					" Internal/External name is required.");
		}

		getDisplayedBlockElement(DocumentSecondaryLink.MODIFICATIONS)
				.findElement(By.linkText(link)).click();
	}

	/**
	 * This method does the verification of Modifications Header presence and
	 * returns the boolean value accordingly.
	 *
	 * @return boolean
	 */
	
	public boolean isBlockPresentInDocument(String sectionName) {
		return isElementDisplayed(By
					.xpath(".//h2[contains(@class,'co_printHeading') and contains(text(),'"
							+ sectionName + "')]"));
	}

	public void clickOnBlockHeader(String sectionName) {
		if (StringUtils.isEmpty(sectionName)) {
			throw new IllegalArgumentException(" Section Name is required.");
		}
		try {
			waitForExpectedElement(
					By.xpath(".//h2[contains(@class,'co_printHeading') and contains(text(),'"
							+ sectionName + "')]")).click();
		} catch (TimeoutException pe) {
			LOG.info(CONTEXT, pe);
			throw new PageOperationException("Exceeded time to find the "
					+ sectionName + " Block in the document");
		}
	}

	/**
	 * This method verifies the given document is legislation type or not and
	 * returns the boolean value accordingly.
	 *
	 * @param docType
	 * @return boolean
	 */
	
	public boolean isProvisionDocument(String docType) {
		return isElementDisplayed(By.cssSelector(LEGISLATION_DOC_TYPE_LOCATOR))
				&& waitForExpectedElement(By.cssSelector(LEGISLATION_DOC_TYPE_LOCATOR)).getText().contains(docType);
	}

	/**
	 * This method verifies whether the image is displayed on provision or not.
	 *
	 * @return boolean
	 */
	
	public boolean isImageDisplayedOnProvision() {
		return isElementDisplayed(By.cssSelector("img[alt='inline image']"));
	}

	public WebElement jurisdictionText() {
		return waitForExpectedElement(JURISDICTION_TEXT);
	}

	public WebElement docContent() {
		return waitForExpectedElement(DOC_CONTENT);
	}

	public WebElement annotatedStatuesText(String annotatedStatusText) {
		return waitForExpectedElement(By.xpath("//h2[contains(text()," + "'" + annotatedStatusText + "'" + ")]"));
	}

	@Override
	public WebElement documentMetaInfo() {
		return waitForExpectedElement(DOCUMENT_META_INFO);
	}


	
}