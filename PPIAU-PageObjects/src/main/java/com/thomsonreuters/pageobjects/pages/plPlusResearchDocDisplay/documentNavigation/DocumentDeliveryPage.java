package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * This page object is to identify the Document Delivery elements and depicts the document delivery functionality.
 * <p/>
 */

public class DocumentDeliveryPage extends DocumentNavigationPage {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DocumentDeliveryPage.class);

	public static final By ADD_TO_FOLDER_LOCATOR = By.xpath(".//*[@id='co_docToolbarSaveToWidget']/div/a");

    private static final By EMAIL_ICON = By.id("co_docToolbarDeliveryWidgetEmail");
    private static final By PRINT_ICON = By.id("co_docToolbarDeliveryWidgetPrint");
    private static final By ADD_TO_FOLDER_ICON = By.id("co_docToolbarSaveToWidget");
    private static final By DONWLOAD_ICON = By.id("co_docToolbarDeliveryWidgetDownload");
    private static final By DOCUMENT_DELIVERY_ICONS = By.xpath("//ul[@id='co_docToolbarVerticalMenuRight']/li");
    private static final By NEW_ANNOTATION = By.xpath("//*[@id='co_AddNewAnnotationWidget']//a");
    private static final By SHOW_HIDE_ANNOTATION = By.xpath("//*[@id='co_ToggleAnnotationWidget']");

    private CommonMethods commonMethods;

    public DocumentDeliveryPage(){
        commonMethods = new CommonMethods();
    }

    public enum Links {
        NEW_ANNOTATION(By.xpath("//*[@id='co_AddNewAnnotationWidget']"),"New Annotation"),
        SHOW_HIDE_ANNOTATIONS(By.xpath("//*[@id='co_ToggleAnnotationWidget']"),"Show Annotation/Hide Annotation");
        private By locator;
        private String toolTipText;

        private Links(By locator, String toolTipText) {
            this.locator = locator;
            this.toolTipText = toolTipText;
        }

        public By getLocator() {
            return this.locator;
        }

        public String getToolTipText() {
            return this.toolTipText;
        }
    }
    public boolean isAnnotationLinkPresent(){
        return isExists(By.cssSelector(".kh_icon.icon-add-note"));
    }


    /**
     * This is to find out the links presence on delivery toolbar.
     *
     * @param links
     * @return boolean
     */
    public boolean isLinkPresent(Links links) {
        waitForPageToLoad();
        waitForPageToLoadAndJQueryProcessing();
        return isElementDisplayed(links.getLocator());
    }

    /**
     * This is to find out the links clickable or not on delivery toolbar.
     *
     * @param links
     * @return boolean
     */
    public boolean isLinkClickable(Links links){
        try {
            waitForElementsClickable(links.getLocator());
            return true;
        } catch (TimeoutException pe) {
        }
        return false;
    }

    public void mouseOverOnLink(Links link) {
        commonMethods.mouseOver(waitForExpectedElement(link.getLocator()));
    }

    public boolean isToolTipDisplayed(Links link){
        try{
            String[] toolTips = link.getToolTipText().split("/");
            String tooltip1 = toolTips[0];
            String tooltip2 = null;
            if(toolTips.length>1){
                tooltip2 = toolTips[1];
            }
            String actualTip = getTootlTipText();
            return (actualTip.equals(tooltip1) || ((!StringUtils.isEmpty(tooltip2)) && actualTip.equals(tooltip2))) ;
        }catch(Exception te){
            return false;
        }
    }

    public String getTootlTipText() {
        return waitForExpectedElement(By.xpath("//div[not(contains(@class,'is-visually-hidden'))]//div[@class='a11yTooltip-pointer']//following-sibling::div")).getText();
    }

    public void clickOnLink(Links link) {
        waitForExpectedElement(link.getLocator()).click();
    }

    public WebElement newAnnotationButton(){
        return waitForExpectedElement(NEW_ANNOTATION);
    }

    public WebElement showHideAnnotationButton(){
        return waitForExpectedElement(SHOW_HIDE_ANNOTATION);
    }
    /**
     * This method is used to verify the AddToFolder link is present in Document Delivery and retursn the boolean value accordingly.
     *
     * @return boolean
     */
    public boolean isDeliveryWidgetPresent(){
        return isExists(By.cssSelector("#co_docToolbarVerticalMenuRight"));
    }
    public boolean isAddToFolderLinkPresent() {
    	return isElementDisplayed(ADD_TO_FOLDER_LOCATOR);
    }

    /**
     * This method does the selecting the Add To Folder link
     */
    public void clickOnAddToFolderLink() {
        waitForExpectedElement(ADD_TO_FOLDER_LOCATOR).click();
    }

    public boolean isNewAnnotationButtonPresent(){
        return isExists(NEW_ANNOTATION);
    }
    public boolean isShowHideAnnotationButtonPresent(){
        return isExists(SHOW_HIDE_ANNOTATION);
    }

    /**
     * This does the finding of Add to folder link element and returns the same element.
     *
     * @return WebElement
     */
    private WebElement getAddToFolderLink() {
        try {
            return waitForExpectedElement(ADD_TO_FOLDER_LOCATOR,10);
        } catch (TimeoutException pe) {
            LOG.info("context", pe);
            throw new PageOperationException("Exceeded Time to find the Add To Folder link.");
        }
    }

	public boolean isNewAnnotationButtonDisplayed(){
	    return  isElementDisplayed(By.xpath("//*[@id='co_AddNewAnnotationWidget']//a"));
    }
    public boolean isShowHideAnnotationButtonDisplayed(){
        return  isElementDisplayed(By.id("co_ToggleAnnotationCount"));
    }
    public boolean isNewAnnotationTooltipDisplayed(String tooltip){
        return  isElementDisplayed(By.xpath("//*[contains(., '"+tooltip+"')]"));
    }
    public boolean isShowHideAnnotationTooltipDisplayed(String tooltip){
        return  isElementDisplayed(By.id("//*[@id='co_ShowHideAnnotationWidgetTextSpan' and contains(., '"+tooltip+"')]"));
    }

    public List<WebElement> deliveryIcons() {
        return waitForExpectedElements(DOCUMENT_DELIVERY_ICONS);
    }

    public WebElement printIcon() {
        return waitForExpectedElement(PRINT_ICON);
    }

    public WebElement emailIcon() {
        return waitForExpectedElement(EMAIL_ICON);
    }

    public WebElement addToFolderIcon() {
        return waitForExpectedElement(ADD_TO_FOLDER_ICON);
    }

    public WebElement downloadIcon() {
        return waitForExpectedElement(DONWLOAD_ICON);
    }

    /**
     * Get website request id for file download request
     * IMPORTANT: Use only when download options window is present
     * @return Request id or empty string if any errors occurred
     */
    public String getWebsiteRequestId() {
        try {
            return waitForElementPresent(By.id("websiteRequestId")).getAttribute("value");
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.warn("Unable to get website request id", e);
            return "";
        }
    }
}