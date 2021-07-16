package com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.utils.TimeoutUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static java.lang.String.format;

public class StandardDocumentPage extends DocumentDisplayAbstractPage {

    private static final String DRAFT_MESSAGE_FOR_OPEN_WEB = "Answer a series of questions upfront and create a first draft in half the time with our free drafting tool.";
    private static final String DRAFT_MESSAGE_FOR_PA = "Answer a series of questions and create a first draft in half the time with our free drafting tool.";
    private static final String DRAFT_MESSAGE_FOR_IP = "Answer a series of questions upfront and create a first draft in half the time with our free drafting tool. You will need an individual username and password to access this tool.";
    private static final String LEARN_MORE_BUTTON_LOCATOR = "//button[@id='co_fastdraft_learnmore' and text()='Learn more']";
    private static final By FASTDRAFT_REDIRECTING_LOCATOR = By.xpath("//div[@aria-label='Redirecting to FastDraft...']//a[@id='co_fastdraftCloseLink']");

    private CommonMethods comMethods = new CommonMethods();

    public enum ResourceType {
        PRACTICE_NOTES("Practice notes");

        private String name;

        ResourceType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public WebElement secondLevelHeadingWithSpecifiedText(String text) {
        return waitForExpectedElement(By.xpath(String.format("//h2[contains(text(),'%s')]", text)));
    }

    public void checkDocumentHasFastDratLogo(String documentName) {
    	waitForElementPresent(By.xpath("//a[text()='" + documentName
                    + "']/ancestor::li[last()]//img[contains(@src,'Id36da025850e11e38578f7ccc38dcbee')]"));
    }

	public void checkStartDraftingButtonPresents() {
		WebElement button = comMethods.waitForElementToBeVisible(
				By.xpath("//*[text()='Start drafting' or @id='fastdraft_message_go']"));
		if (button == null) {
			throw new PageOperationException("Start Drafting button absents");
		}
	}

    public WebElement startDraftingButton() {
        return waitForExpectedElement(By.xpath("//*[@id='co_fastdraft_startdrafting' and text()='Start drafting']"));
    }

    
    public boolean isFirmStyleButtonPresent() {
    	return isElementDisplayed(By.id("co_docToolbarDownloadInFirmStyle"));
    }

    public WebElement firmStyle() {
        return waitForExpectedElement(By.id("co_docToolbarDownloadInFirmStyle"));
    }

    
    public void checkDownloadboxAppiars() {
    	waitForElementPresent(By.xpath("//*[text()='Preparing For Download']"));
    }

    
    public void checkLearnMoreButtonPresents() {
    	waitForElementPresent(By.xpath(LEARN_MORE_BUTTON_LOCATOR));
    }

    public WebElement learnMoreButton() {
        return waitForExpectedElement(By.xpath(LEARN_MORE_BUTTON_LOCATOR));
    }

    
    public void checkDraftMessagePresents() {
    	waitForElementPresent(By.xpath("//div[contains(.,'" + DRAFT_MESSAGE_FOR_OPEN_WEB + "') "
                    + "and contains(.,'Draft document')]" + LEARN_MORE_BUTTON_LOCATOR));
    }

    public void checkDraftMessageForIPUsersPresents() {
    	waitForElementPresent(By.xpath("//div[@class='pluk-document-fastdraft-body co_excludeAnnotations' and contains(.,'" + DRAFT_MESSAGE_FOR_IP
                    + "') and contains(.,'Draft document')]"));
    }

    
	public void checkLoginAsSingleUserButtonPresents() {
		WebElement button = comMethods.waitForElementToBeVisible(
				By.xpath("//button[@id='co_fastdraft_login' and text()='Log in as single user']"));
		if (button == null) {
			throw new PageOperationException("Login as single user button absents");
		}
	}

    public WebElement loginAsSingleUserButton() {
        return waitForExpectedElement(By.xpath("//button[@id='co_fastdraft_login' and text()='Log in as single user']"));
    }

    public void checkDraftMessageForPAPresents() {
        if(!isExists(By.xpath("//div[contains(.,'" + DRAFT_MESSAGE_FOR_PA
                + "') and contains(.,'Draft document')]"))){
        	throw new PageOperationException("Draft message absents");
        }
        String learnMoreLink = "/About/PracticalLawTools";
        waitForExpectedElement(By.xpath("//a[@id='co_fastdraft_learnmore' and contains(@href,'" + learnMoreLink + "')]"));
    }

    public void clickOnViewDocumentButton() {
        try {
            WebElement viewDocumentButton = findElement(By.id("co_WarnViewOkButton"));
            viewDocumentButton.click();
        } catch (NoSuchElementException ex) {
            LOG.info("context", ex);
        }
    }

    public void checkRedirectingToFastDraftPopupPresents() throws AWTException {
        //Cancel request button will appear after 10 secs
        TimeoutUtils.sleepInSeconds(12);
        stopBrowserExecution();
        waitForElementPresent(FASTDRAFT_REDIRECTING_LOCATOR);
        LOG.info("Redirecting to FastDraft popup presents");
    }

    private void stopBrowserExecution() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public void checkCancelRequestButtonPresents() {
        waitForElementPresent(By
                    .xpath("//div[@aria-label='Redirecting to FastDraft...']//input[@id='co_fastdraftCancelButton']"));
    }

    public WebElement cancelRequestButton() {
        return waitForExpectedElement(
                By.xpath("//div[@aria-label='Redirecting to FastDraft...']//input[@id='co_fastdraftCancelButton']"));
    }

    public void checkRedirectingToFastDraftPopupAbsents() {
        waitForElementPresent(FASTDRAFT_REDIRECTING_LOCATOR);
    }

    public boolean isRegisterForFreeAccessPresent() {
    	return isElementDisplayed(By.xpath("//a[contains(text(),'Request a free trial')]"));
    }
    
    
    public boolean isDocumentTitlePanelPresent() {
    	return isElementDisplayed(By.id("co_docHeaderContainer"));
    }

    public WebElement regesterForFreeAccess() {
        return waitForExpectedElement(By.xpath("//a[contains(text(),'Request a free trial')]"), 30);
    }
    
    public WebElement closeRedirectingToFastDraftPopup() {
        return waitForExpectedElement(FASTDRAFT_REDIRECTING_LOCATOR);
    }

    public WebElement downloadFSFile(String linkName) {
        return waitForExpectedElement(By.xpath("//a[@href='" + linkName + "']"));
    }

    public WebElement homePage() {
        return waitForExpectedElement(By.xpath("//a[@href='/Search/Home.html']"));
    }

    public WebElement getDocumentSummary() {
        return waitForExpectedElement(By.className("kh_abstract"));
    }

    public WebElement getFullDocumentBody() {
        return waitForExpectedElement(By.id("co_docContentBody"));
    }

    public WebElement getCopyright() {
        return waitForExpectedElement(By.className("co_endOfDocCopyright"));
    }

    public WebElement getLinkInRelatedContent(String title) {
        return waitForExpectedElement(By.xpath(format(
                ".//div[@id='co_relatedContent']//li[not(contains(@class,'ng-hide'))]/a[contains(., '%s')]", title)));
    }

    public WebElement getStatusForLinkInRelatedContent(String title) {
        WebElement link = getLinkInRelatedContent(title);
        return link.findElement(By.xpath("../span[contains(@class, 'ng-binding')]"));
    }

    public WebElement headerOnTheDocument(String header) {
		return waitForExpectedElement(By.xpath("//*[@id='co_document']//*[contains(text(), '" + header + "')]"));
	}
    
    /**
     * Check if resource type of current opened document is equals to expected
     *
     * @param resourceType Resource type of opened document
     * @return True - if check passed, otherwise - false.
     * @see ResourceType
     */
    public boolean isResourceTypeEquals(ResourceType resourceType) {
        return waitForElementVisible(By.cssSelector(".co_documentType>span")).getText().equals(resourceType.getName());
    }

    /**
     * Check if title of current opened document is equals to expected
     *
     * @param expectedTitle Expected document title
     * @return True - if check passed, otherwise - false.
     */
    public boolean isDocumentTitleEquals(String expectedTitle) {
        return waitForElementVisible(By.cssSelector(".co_title")).getText().equals(expectedTitle);
    }

    /**
     * Is user see resource history (is he was scrolled to it after click to "View Resource History" link for example)
     *
     * @return True, if user see resource history, otherwise - false.
     */
    public boolean isResourceHistoryDisplayed() {
        return waitForViewScrollingToElement(By.id("co_EodHistory"));
    }

    /**
     * Check if appropriate section displayed now for user
     *
     * @param sectionName Section name (headline)
     * @return True - if check passed, otherwise - false.
     */
    public boolean isDocumentSectionDisplayed(String sectionName) {
        return waitForViewScrollingToElement(By.xpath("//*[@class='co_headtext' and contains(., '" + sectionName + "')]"));
    }

    public boolean isTableOfContentPresent(){
        return isExists(By.cssSelector("div.kh_toc-header span.co_hideState"));
    }

    /**
     * Click on right-hand-side block link
     *
     * @param linkText Link text
     */
    public void clickOnRhsLink(String linkText) {
        waitForExpectedElement(By.xpath("//div[@id='co_docContentMetaInfo']//a[contains(., '" + linkText + "')]")).click();
    }

    /**
     * Click "View All" link in Resource History section at bottom of the page
     */
    public void clickViewAllResourceHistory() {
        waitForExpectedElement(By.id("co_docContentResourceHistoryAllButton")).click();
    }

    /**
     * Click on the link in Resource History section at bottom of the page
     *
     * @param linkText Link text
     */
    public void clickResourceHistoryLink(String linkText) {
        waitForExpectedElement(By.xpath("//div[@id='co_endOfDocument']//a[contains(., '" + linkText + "')]")).click();
    }

    /**
     * Click jn link in table of contents
     *
     * @param linkName Link name
     * @return Element with link from table of contents
     */
    public WebElement getLinkInTableOfContents(String linkName) {
        return waitForExpectedElement(By.xpath("//div[@class='kh_toc-content']//a[contains(., '" + linkName + "')]"));
    }
    
    /**
     *
     * 
     * @return Element Locator for numbers in table of contents
     */
    public By getNumberingInTableOfContents() {
        return By.xpath("//div[@class='kh_toc-content']//span[@class='co_number plcuk_standardDocument']");
    }

    /**
     * Get link from section of document
     *
     * @param sectionName Name of section which contains necessary link. Can be empty if there is not important where
     *                    link should be (in what section of document)
     * @param linkName    Link name in section
     * @return Element with link in section
     */
    public WebElement getLinkFromSection(String sectionName, String linkName) {
        return waitForExpectedElement(
                By.xpath("//div[@id='co_document']//div[.//*[@class='co_headtext' and contains(., '" +
                        sectionName + "')]]//a[contains(., '" + linkName + "')]")
        );
    }

    /**
     * Get elements with products inside metadata block
     *
     * @return Elements with product names
     */
    public List<WebElement> getProductsFromMetadata() {
        return waitForExpectedElements(By.cssSelector(".co_practiceAreaName li"));
    }

    /**
     * Check if document contains any section
     *
     * @return True -  if at least one section exists. Otherwise - false
     */
    public boolean isContainsSection() {
        return isExists(By.cssSelector("#co_docContentBody .co_headtext"));
    }

    public boolean isErrorMessageOnDocument() {
        return isElementDisplayed(By.xpath("//div[@class='co_genericBoxContent']/*[contains(text(),'not be found') or contains(text(),'not available')]"));
    }
	public boolean waitForDocumentTitle() {
		return isElementDisplayed(By.xpath("//div[@id='co_docHeaderContainer']/h1[@class='co_title noTOC']"));
	}
}