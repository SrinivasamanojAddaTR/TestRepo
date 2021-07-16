package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.enums.DocumentSecondaryLink;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * This is the base class for document display page objects to contain the common functionality in it.
 * <p/>
 */
public abstract class DocumentDisplayAbstractPage extends AbstractPage {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DocumentDisplayAbstractPage.class);

    private static final String DOCUMENT_HEADER_LOCATOR = "div.co_document>div";
    protected static final By DOCUMENT_LOADING_LOCATOR = By.className("co_document");
    protected static final By PRIMARY_MENU_SELECTION_LOCATOR = By.cssSelector("#docRelatedInfo .selected");
    protected static final By PRIMARY_MENU_EXPANDED_LOCATOR = By.cssSelector("");
    protected static final By THREE_COLUMN_LAYOUT_SELECTOR = By.cssSelector(".co_t3.th_lightGray");
    protected static final By DOCUMENTS_CSS_SELECTOR = By.cssSelector("td");
    protected static final By BACK_TO_RESULTS_LOCATOR = By.cssSelector("#coid_backToResults a");
    public static final By PARENT_DOCUMENT_LINK_LOCATOR = By.cssSelector("");
    private static final By RESOURCE_TYPE = By.xpath(".//div[@class='co_documentType']/span");
    private static final By END_OF_DOCUMENT = By.id("co_endOfDocument");
    private static final By CONTENT_BODY = By.id("co_docContentBody");
    private static final By DELIVERY_OPTIONS = By.id("co_docToolbarVerticalMenuRight");
    private static final By META_CONTENT = By.id("co_docContentMetaInfo");
	private static final By COPYRIGHT = By.xpath("//div[@class='co_center']/div[@class='co_copyright']"); //./div[contains(@class,'co_copyright co_center')]
    private static final String CONTEXT = "context";
    

    /**
     * This boolean method verifies the document header with the given name is present or not.
     *
     * @param title
     * @return boolean
     */
    public boolean isDocumentHeaderPresent(String title) {
        if (StringUtils.isEmpty(title)) {
            throw new IllegalArgumentException(" Document Title is required.");
        }
        try {
            return waitForExpectedElement(By.cssSelector(DOCUMENT_HEADER_LOCATOR)).getText().contains(title);
        } catch (TimeoutException te) {
            logger.warn("Exceeded time to find the document header.", te);
        }
        return false;
    }

    /**
     * This method does the waiting to load the parent document completely.
     */
    public void waitUntilPageLoaded() {
        try {
            waitForExpectedElement(DOCUMENT_LOADING_LOCATOR);
        } catch (TimeoutException te) {
            throw new PageOperationException(te.getMessage());
        }
    }

    /**
     * This method verifies the document layout is displayed in three column or not and returns the boolean value.
     *
     * @return boolean.
     */
    public boolean isDocumentDisplayedInThreeColumnLayout() {
    	return isElementDisplayed(THREE_COLUMN_LAYOUT_SELECTOR);
    }

    /**
     * This method does the waiting for all document elements gets loaded successfully.
     */
    public void documentDisplayedFully() {
        try {
            waitForExpectedElements(DOCUMENTS_CSS_SELECTOR);
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the document element :" + te.getMessage());
        }
    }

    /**
     * This method is used to click on the back to results button on the document.
     */
    public void goBackToResults() {
        try {
            waitForPageToLoadAndJQueryProcessing();
            scrollIntoViewAndClick(waitForExpectedElement(BACK_TO_RESULTS_LOCATOR));
        } catch (TimeoutException te) {
            throw new PageOperationException(te.getMessage());
        }
    }

    /**
     * This method does verfication of navigation section is present on left hand side of the document and returns the boolean value accordingly.
     *
     * @return boolean
     */
    public boolean isNavigationLinksSectionPresentOnLeftHandSideOfTheDoc() {
        try {
            return waitForExpectedElement(By.cssSelector("")).getAttribute("class").contains("left");
        } catch (TimeoutException te) {
            LOG.info(CONTEXT, te);
        }
        return false;
    }

    public void signOff() {
        try {
            waitForExpectedElement(By.linkText("Sign Off"), 10).click();
        } catch (TimeoutException | ElementNotVisibleException nse) {
            LOG.error("Sign-Off link not found", nse);
        }
    }

    /**
     * This method clicks on the given parent link present on displayed document.
     *
     * @param link
     */
    public void clickOnParentDocumentLink(String link) {
        getParentDocumentLink(link).click();
    }

    /**
     * This private method to get the parent element based on given parent document name
     *
     * @param link
     * @return WebElement
     */
    private WebElement getParentDocumentLink(String link) {
        if (StringUtils.isEmpty(link)) {
            throw new IllegalArgumentException(" Parent document name is required.");
        }
        try {
            return waitForExpectedElement(PARENT_DOCUMENT_LINK_LOCATOR);
        } catch (TimeoutException te) {
            throw new PageOperationException(te.getMessage());
        }
    }

    /**
     * This method select the internal/external link based on given link and link in section
     *
     * @param link
     * @param secondaryLinkSection
     */
    public void selectLinkInSection(String link, DocumentSecondaryLink secondaryLinkSection) {
        if (StringUtils.isEmpty(link)) {
            throw new IllegalArgumentException(" Internal/External link name is required.");
        }
        getDisplayedBlockElement(secondaryLinkSection).findElement(By.linkText(link)).click();
    }

    /**
     * This method select the internal/external link based on given link and link in any part of the Document
     *
     * @param link
     */
    public void selectLinkPresentInDocument(String link) {
        if (StringUtils.isEmpty(link)) {
            throw new IllegalArgumentException(" Internal/External link name is required.");
        }
        scrollIntoView(findElement(By.linkText(link)), false);
        findElement(By.linkText(link)).click();
    }

    /**
     * This common method retuns the webelemnt of the section in the document based on given section name.
     *
     * @param secondaryLink
     * @return WebElement
     */
    protected WebElement getDisplayedBlockElement(DocumentSecondaryLink secondaryLink) {
        if (secondaryLink == null) {
            throw new IllegalArgumentException(" Child link name is required.");
        }
        try {
            return waitForElementVisible(By.xpath(".//h2[contains(@class,'co_printHeading') and contains(text(),'" + secondaryLink.getLinkName() + "')]/../.."));
        } catch (TimeoutException te) {
            LOG.info(CONTEXT, te);
            throw new PageOperationException("Exceeded time to find the selected secondary menu block :" + secondaryLink);
        }
    }

    public WebElement resourceType() {
        return waitForExpectedElement(RESOURCE_TYPE);
    }

    public WebElement documentStatus() {
        return waitForExpectedElement(By.xpath("//div[@id='co_docContentMetaInfo']/span[contains(@class,'Status')]"));
    }

    public WebElement documentTitle() {
        // Pages can at times take a long time to render
        return waitForExpectedElement(By.xpath("//div[@id='co_docHeaderContainer']//*[contains(@class,'co_title')]"),60);
    }

	public WebElement documentTitlePanel() {
		return waitForExpectedElement(By.id("co_docHeaderContainer"));
	}

    public WebElement documentMetaInfo(){
        return waitForElementPresent(By.xpath("//*[contains(@id,'co_document_metaInfo')]"));
    }

    public WebElement contentBody() {
        return waitForElementPresent(CONTENT_BODY);
    }

    public WebElement endOfDocument() {
        return findChildElement(contentBody(), END_OF_DOCUMENT);
    }

    public WebElement author() {
        return waitForExpectedElement(By.cssSelector("#co_docHeaderContainer .co_productname"));
    }

    public WebElement jurisdictionLabel() {
        return waitForExpectedElement(By.cssSelector("#co_docContentMetaInfoJurisdictions b"));
    }

    public WebElement resourceTypeText(){
        return waitForExpectedElement(By.cssSelector("#co_docContentMetaInfo div.co_documentType>span"));
    }

    public WebElement getResourceId() {
        return waitForExpectedElement(By.className("co_documentId"));
    }

    public WebElement deliveryOptions() {
        return waitForElementPresent(DELIVERY_OPTIONS);
    }

    public WebElement returnToSearchLink() {
        return waitForExpectedElement(By.xpath("//span[@class='kh_icon icon-reply-arrow']"));
    }

    public boolean isRelatedContentHeadingDisplayed() {
        return waitForViewScrollingToElement(By.xpath("//div[@id='co_relatedContent']//h2[text()='Related Content']"));
    }

	public WebElement buttonInDocumentWithValue(String value) {
		return findChildElement(contentBody(), By.xpath(".//input[contains(@value,'" + value + "')]"));
	}

	public WebElement buttonWithText(String text) {
		return waitForElementPresent(By.xpath("//button[contains(text(),'" + text + "')] | //a[contains(text(),'" + text + "')]"));
	}
	
	public WebElement metaContent() {
		return waitForElementVisible(META_CONTENT);
    }

	public WebElement copyright() {
		return findChildElement(contentBody(), COPYRIGHT);
	}
	
	public boolean isCopyrightExists(String copyright) {
		return isExists(By.xpath(
				"//div[@class='co_center']/div[@class='co_copyright' and contains (text(),'" + copyright + "')]"));
	}
	
	public WebElement textWithinDocumentBody(String text) {
		return findChildElement(contentBody(), By.xpath("//*[contains(text(), '" + text + "')]"));
	}
}