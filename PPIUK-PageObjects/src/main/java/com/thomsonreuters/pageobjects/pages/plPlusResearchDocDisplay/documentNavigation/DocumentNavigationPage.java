package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.DocumentDisplayAbstractPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.enums.DocumentPrimaryLink;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.enums.DocumentSecondaryLink;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.enums.ExpandAndCollapse;
import com.thomsonreuters.driver.exception.PageOperationException;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This page object is to identify the Document navigation elements and depicts the document navigation functionality.
 * <p/>
 */

public class DocumentNavigationPage extends DocumentDisplayAbstractPage {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DocumentNavigationPage.class);

    public static final By RIGHT_CAROSAL_LOCATOR = By.xpath(".//*[@id='co_rightPageFlipper']/a/span");
    public static final By LEFT_CAROSAL_LOCATOR = By.xpath(".//*[@id='co_leftPageFlipper']/a/span");
    public static final By PREVIOUS_CAROSAL_LOCATOR = By.xpath(".//span[text()='Previous Document']");
    public static final By NEXT_CAROSAL_LOCATOR = By.xpath(".//span[text()='Next Document']");
    public static final By LEFT_CAROSAL_TEXT_LOCATOR = By.xpath(".//*[@id='co_leftPageFlipper']/span/a[@class='co_pageFlipperNav']");
    public static final By RIGHT_CAROSAL_TEXT_LOCATOR = By.xpath(".//*[@id='co_rightPageFlipper']/span/a[@class='co_pageFlipperNav']");
    public static final By PREVIOUS_RESULT_NAVIGATION_LINK_LOCATOR = By.id("co_documentFooterSearchTermNavigationPrevious");
    public static final By NEXT_RESULT_NAVIGATION_LINK_LOCATOR = By.id("co_documentFooterSearchTermNavigationNext");
    public static final By COUNTER_ON_SEARCH_TOGLE_LOCATOR = By.cssSelector(".plplus_searchTermCounter");
    public static final By CURRENT_TERM_INDEX = By.id("plplus_currentTermIndex");
    public static final By BACK_TO_RESULTS_LINK_LOCATOR = By.cssSelector(".kh_icon.icon-reply-arrow");
    public static final By RESULTS_NAVIGATION_LINKS_LOCATOR = By.cssSelector("#coid_searchTermNavigation .co_dropDownAnchor span");
    private static final By PRIMARY_LINKS_LOCATOR = By.cssSelector(""); //TODO: Need to add the css Selector
    private static final By CHILD_LINKS_LOCATOR = By.cssSelector("#docRelatedInfo .subSelected"); //TODO: Need to add the locator
    private static final By SECONDARY_ELEMENTS_UNDERNEATH_PRIMARY_LOCATOR = By.cssSelector(""); //TODO: Need to add the locator


    CommonMethods commonMethods;

    /**
     * This method returns true if right carosal link is present on doucment otherwise false.
     *
     * @return boolean
     */
    public boolean isRightCarosalLinkAvailable() {
    	return isElementDisplayed(RIGHT_CAROSAL_LOCATOR);
    }

    /**
     * This does the finding of right carosal link element and returns the same element.
     *
     * @return WebElement
     */
    private WebElement getRightCarosalLink() {
        try {
            return waitFluentForElement(RIGHT_CAROSAL_LOCATOR);
        } catch (TimeoutException pe) {
            LOG.info("context", pe);
            throw new PageOperationException("Exceeded Time to find the Right carosal link.");
        }
    }

    /**
     * This does the finding of left carosal link element and returns the same element.
     *
     * @return WebElement
     */
    private WebElement getLeftCarosalLink() {
        try {
            return waitFluentForElement(LEFT_CAROSAL_LOCATOR);
        } catch (TimeoutException pe) {
            LOG.info("context", pe);
            throw new PageOperationException("Exceeded Time to find the Left carosal link.");
        }
    }

    /**
     * This method returns true if left carosal link is present on doucment otherwise false.
     *
     * @return boolean
     */
    public boolean isLeftCarosalLinkAvailable() {
    	return isElementDisplayed(LEFT_CAROSAL_LOCATOR);
    }

    /**
     * This method does the clicking on left carosal link
     */
    public void clickOnLeftCarosalLink() {
        getLeftCarosalLink().click();
    }

    /**
     * This method does the clicking on right carosal link
     */
    public void clickOnRightCarosalLink() {
        getRightCarosalLink().click();
    }

    /**
     * This method gets the link text present in left carosal link.
     *
     * @return String
     */
    public String getDocumentNamePresentOnLeftCarosal() {
        try {
            commonMethods.mouseOver(waitFluentForElement(PREVIOUS_CAROSAL_LOCATOR));
            waitForExpectedElement(By.cssSelector(".co_pageFlipper.is-active"));
            return waitFluentForElement(LEFT_CAROSAL_TEXT_LOCATOR).getText();
        } catch (TimeoutException nse) {
            LOG.warn("Unable to find the document name in left carosal link", nse);
            //The next catch was made because of strange NPE on Production site in previous try block
        } catch  (NullPointerException npe) {           	           
        	return findElement(LEFT_CAROSAL_TEXT_LOCATOR).getAttribute("textContent");
        }
        return StringUtils.EMPTY;
    }

    /**
     * This method gets the link text present in right carosal link.
     *
     * @return String
     */
    public String getDocumentNamePresentOnRightCarosal() {
        try {
            commonMethods.mouseOver(waitFluentForElement(NEXT_CAROSAL_LOCATOR));
            waitForExpectedElement(By.cssSelector(".co_pageFlipper.is-active"));
            return waitFluentForElement(RIGHT_CAROSAL_TEXT_LOCATOR).getText();
        } catch (TimeoutException nse) {
            logger.warn("Unable to find the document name in right carosal link", nse);
        //The next catch was made because of strange NPE on Production site in previous try block
        } catch  (NullPointerException npe) {        	
        	return findElement(RIGHT_CAROSAL_TEXT_LOCATOR).getAttribute("textContent");
        }
        return StringUtils.EMPTY;
    }
    
    /**
     * This method does the finding of a counter on a search toggle widget and returns it as a webelement.
     *
     * @return WebElement
     */
    public WebElement getCounterOnSearchTogle() {
    	return waitForExpectedElement(COUNTER_ON_SEARCH_TOGLE_LOCATOR);
    }
    
    public WebElement getCurentTermIndex() {
    	return waitForExpectedElement(CURRENT_TERM_INDEX);
    }
    

    /**
     * This method verifies the Previous Results Link is present, if yes returns boolean otherwise false.
     *
     * @return boolean
     */
    public boolean isPreviousResultLinkPresentOnResultsNavigationWidget() {
    	return isElementDisplayed(PREVIOUS_RESULT_NAVIGATION_LINK_LOCATOR);
    }   

    /**
     * This private mathod does the finding of the Previous result link present on navigation gadget and returns as webelement.
     *
     * @return WebElement
     */
    public WebElement getPreviousResultLink() {
        try {
            return waitFluentForElement(PREVIOUS_RESULT_NAVIGATION_LINK_LOCATOR);
        } catch (TimeoutException pe) {
            LOG.info("context", pe);
            throw new PageOperationException("Exceeded Time to find the Previous link.");
        }
    }

    /**
     * This method verifies the Next Results Link is present, if yes returns boolean otherwise false.
     *
     * @return boolean
     */
    public boolean isNextResultLinkPresentOnResultsNavigationWidget() {
    	return isElementDisplayed(NEXT_RESULT_NAVIGATION_LINK_LOCATOR);
    }

    /**
     * This private mathod does the finding of the Next result link present on navigation gadget and returns as webelement.
     *
     * @return WebElement
     */
    public WebElement getNextResultLink() {
        try {
            return waitFluentForElement(NEXT_RESULT_NAVIGATION_LINK_LOCATOR);
        } catch (TimeoutException pe) {
            LOG.info("context", pe);
            throw new PageOperationException("Exceeded Time to find the Next link.");
        }
    }

    /**
     * This method is used to verify the back to results button is present on Results Navigation Widget on document.
     *
     * @return boolean
     */
    public boolean isBackToResultsLinkPresentOnResultsNavigationWidget() {
    	return isElementDisplayed(BACK_TO_RESULTS_LINK_LOCATOR);
    }

    /**
     * This method verifies the Previous Results Link is Enabled, if yes returns boolean otherwise false.
     *
     * @return boolean
     */
    public boolean isPreviousResultLinkEnabledOnResultsNavigationWidget() {
        try {
            return (!(getPreviousResultLink().getAttribute("class").contains("disabled")));
        } catch (PageOperationException pe) {
            LOG.warn("context", pe);
        }
        return false;
    }

    /**
     * This method verifies the Next Results Link is Enabled, if yes returns boolean otherwise false.
     *
     * @return boolean
     */
    public boolean isNextResultLinkEnabledOnResultsNavigationWidget() {
        try {
            return (!(getNextResultLink().getAttribute("class").contains("disabled")));
        } catch (PageOperationException pe) {
            LOG.info("context", pe);
        }
        return false;
    }

    /**
     * This method does the click on the Previous result link.
     */
    public void clickOnPreviousResultLinkOnResultsNavigationWidget() {
        getPreviousResultLink().click();
    }

    /**
     * This method does the click on the Previous result link.
     */
    public void clickOnNextResultLinkOnResultsNavigationWidget() {
        getNextResultLink().click();
    }

    /**
     * This method does waiting for results navigation links present on document.
     */
    public void waitForResultsNavigationLinksRendering() {
        try {
            waitAndFindElements(RESULTS_NAVIGATION_LINKS_LOCATOR);
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
    }

    /**
     * This method is to verify the previous link present and enabled on document header.
     *
     * @return boolean
     */
    public boolean isPreviousLinkPresentAndEnabled() {
        return (isPreviousResultLinkPresentOnResultsNavigationWidget() && isPreviousResultLinkEnabledOnResultsNavigationWidget());
    }

    /**
     * This method is to verify the next link present and enabled on document header.
     *
     * @return boolean
     */
    public boolean isNextLinkPresentAndEnabled() {
        return (isNextResultLinkPresentOnResultsNavigationWidget() && isNextResultLinkEnabledOnResultsNavigationWidget());
    }

    /**
     * This is to verify the next / previous links on results navigation present on document header, are clickable or not.
     *
     * @return
     */
    public boolean isNavigationChildLinksAreClickable() {
        try {
            return (!(getNextResultLink().getAttribute("class").contains("disabled")));
        } catch (Exception pe) {
            LOG.warn("context", pe);
        }
        return false;
    }

    /**
     * This method returns the list of primary link names as string values.
     *
     * @return List<String>
     */
    public List<String> getPrimaryLinkNames() {
        List<String> list = new ArrayList<String>();
        for (WebElement element : getPrimaryLinkElements()) {
            list.add(element.getText());
        }
        return list;
    }

    /**
     * This method is to find out the primary link webelements present on left hand navigation of the document.
     *
     * @return List<WebElement>
     */
    List<WebElement> getPrimaryLinkElements() {
        List<WebElement> list = new ArrayList<WebElement>();
        try {
            list = waitAndFindElements(PRIMARY_LINKS_LOCATOR);
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        logger.info("PrimaryLinks : " + list.toString());
        return list;
    }

    /**
     * This method is to find out the secondary link webelements present on left hand navigation of the document.
     *
     * @return List<WebElement>
     */
    protected List<WebElement> getSecondaryLinkElements(String primaryLink) {
        WebElement primaryElement = null;
        for (WebElement element : getPrimaryLinkElements()) {
            if (element.getText().equalsIgnoreCase(primaryLink)) {
                primaryElement = element;
            }
        }
        List<WebElement> list = new ArrayList<WebElement>();
        try {
            list = primaryElement.findElements(SECONDARY_ELEMENTS_UNDERNEATH_PRIMARY_LOCATOR);
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        logger.info("SecondaryLinks : " + list.toString());
        return list;
    }

    /**
     * This method returns the list of primary link names as string values.
     *
     * @param primaryLink
     * @return List<String>
     */
    public List<String> getChilidMenuLinkNames(String primaryLink) {
        if (StringUtils.isEmpty(primaryLink)) {
            throw new IllegalArgumentException(" Primary link name is required.");
        }
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement element : getSecondaryLinkElements(primaryLink)) {
                list.add(element.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        logger.info("ChildLinks : " + list.toString());
        return list;
    }

    /**
     * This method does clicking on the given child link present on left hand side navigation of the document.
     *
     * @param name
     */
    public void selectChildMenuLink(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException(" Secondary link name is required.");
        }
        try {
            waitFluentForElement(DocumentSecondaryLink.getLink(name)).click();
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException(te.getMessage());
        }
    }

    /**
     * This method is used to find the primary menu based on the given name and verifies whether it is selected or not
     *
     * @param name
     * @return boolean
     */
    public boolean isPrimaryMenuSelected(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException(" Primary link name is required.");
        }
        try {
            return waitFluentForElement(PRIMARY_MENU_SELECTION_LOCATOR).getText().contains(name);
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return false;
    }

    /**
     * This method is used to find the primary menu based on the given DocumentPrimaryLink and verifies whether it is expanded or not
     *
     * @param primaryLink
     * @return boolean
     */
    public boolean isPrimaryMenuExpanded(DocumentPrimaryLink primaryLink) {
        if (primaryLink == null) {
            throw new IllegalArgumentException(" Primary link is required.");
        }
        try {
            return waitFluentForElement(PRIMARY_MENU_EXPANDED_LOCATOR).getText().contains(primaryLink.getLinkName());
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return false;
    }

    /**
     * This method is used to find the primary menu based on the given DocumentPrimaryLink and verifies whether it is selected or not
     *
     * @param primaryLink
     * @return boolean
     */
    public boolean isPrimaryMenuSelected(DocumentPrimaryLink primaryLink) {
        if (primaryLink == null) {
            throw new IllegalArgumentException(" Primary link is required.");
        }
        try {
            return waitFluentForElement(PRIMARY_MENU_SELECTION_LOCATOR).getText().contains(primaryLink.getLinkName());
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return false;
    }

    /**
     * This method is used to find the other primary menus are collapsed except selected menu.
     *
     * @param primaryLink
     * @return boolean
     */
    public boolean isOtherPrimaryMenuAreCollapsed(DocumentPrimaryLink primaryLink) {
        if (primaryLink == null) {
            throw new IllegalArgumentException(" Primary link is required.");
        }
        boolean bool = false;
        boolean actualLinkBoolean = false;
        for (String primaryMenuName : getPrimaryLinkNames()) {
            if (!primaryMenuName.equals(primaryLink)) {
                bool = isPrimaryMenuExpanded(DocumentPrimaryLink.getLink(primaryMenuName));
                if (bool) {
                    return false;
                }
            } else {
                actualLinkBoolean = isPrimaryMenuExpanded(DocumentPrimaryLink.getLink(primaryMenuName));
            }
        }
        return actualLinkBoolean;
    }

    /**
     * This method is to select the given primary link name under the left hand navigation of the document.
     *
     * @param primaryMenuName
     */
    public void selectPrimaryMenuLink(String primaryMenuName) {
        if (StringUtils.isEmpty(primaryMenuName)) {
            throw new IllegalArgumentException(" Primary link is required.");
        }
        waitFluentForElement(DocumentPrimaryLink.getLink(primaryMenuName).getCssLocator()).click();
    }

    /**
     * This method is to verify the document navigation links section is present on the left hand side of the document.
     *
     * @return boolean
     */
    public boolean isLinksAreDisplayedInLeftHandSideNavigation() {
        try {
            /**
             * TODO Need to be changed once it is available for testing
             */
            return waitForExpectedElement(By.cssSelector("")).getAttribute("style").contains("left");
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return false;
    }

    /**
     * This method is to verify the given primary menu child links are jump links are not.
     *
     * @param primaryMenu
     * @return boolean
     */
    public boolean isSecondaryMenusAreJumpLinks(String primaryMenu) {
        if (org.springframework.util.StringUtils.isEmpty(primaryMenu)) {
            throw new IllegalArgumentException(" Primary menu name is required.");
        }
        try {
            for (WebElement childElement : getSecondaryLinkElements(primaryMenu)) {
                waitForElementToBeClickable(childElement);
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
            return false;
        }
        return true;
    }

    
    
    /**
     * This method is to verify the given child link is present underneath the primary link.
     *
     * @param childLink
     * @param primaryLink
     * @return boolean
     */
    public boolean isChildLinkPresentUnderGivenPrimaryMenu(String childLink, String primaryLink) {
        if (org.springframework.util.StringUtils.isEmpty(primaryLink) || org.springframework.util.StringUtils.isEmpty(childLink)) {
            throw new IllegalArgumentException(" Primary/Secondary link name is required.");
        }
        WebElement primaryWebElement = null;
        try {
            for (WebElement primaryElement : getPrimaryLinkElements()) {
                if (primaryElement.getText().equals(primaryLink)) {
                    primaryWebElement = primaryElement;
                }
            }
            //TODO: need to add correct child xpath
            return (primaryWebElement.findElement(By.xpath("child element finding by xpath ")).isDisplayed() && waitForElementVisible(DocumentSecondaryLink.getLink(childLink)).isDisplayed());
        } catch (TimeoutException te) {
            logger.warn("Exceeded time to find the child element:" + childLink + " under Primary element:" + primaryLink, te);
        }
        return false;
    }

    /**
     * This method verifies the given primary link is present or not on the left hand navigation menu.
     *
     * @param primaryLink
     * @return boolean
     */
    public boolean isPrimaryMenuPresent(String primaryLink) {
        if (StringUtils.isEmpty(primaryLink)) {
            throw new IllegalArgumentException(" Primary link name is required.");
        }
        return isElementDisplayed(DocumentPrimaryLink.getLink(primaryLink).getCssLocator());
    }

    public void selectExpandAndCollapseOnPrimaryNavigationLink(String primaryMenu, ExpandAndCollapse expand) {
        //Write pageobjects logic to do expand and collapse on given primary menu
    }

    public WebElement signInButton() {
        return findElement(By.xpath("//a[@class='co_primaryBtn th_flat' and text()='Sign in']"));
    }

    public boolean isSignInButtonPresent() {
    	return isElementDisplayed(By.xpath("//a[@class='co_primaryBtn th_flat' and text()='Sign in']"));
    }

    public WebElement linkInAlsoFoundInSection(){
        return waitForExpectedElement(By.xpath("//div[@class='co_topics']//a"));
    }

    /**
     * Get element for the highlighted term navigation
     *
     * @return WebElement for highlighted term navigation
     */
    public WebElement getTermNavigationElement() {
        return waitForExpectedElement(By.id("coid_searchTermNavigation"));
    }

    /**
     * Get highlighted focused term element from the document
     *
     * @return WebElement for highlighted focused term element from the document
     */
    public WebElement getFocusedHighlightedTerm() {
        return waitAndFindElement(By.cssSelector(".co_currentSearchTerm"));
    }

    /**
     * Get all highlighted terms from the page
     *
     * @return List of elements with highlighted terms
     */
    public List<WebElement> getAllHighlightedTerms() {
        return waitForExpectedElements(By.cssSelector(".co_searchTerm"));
    }

}