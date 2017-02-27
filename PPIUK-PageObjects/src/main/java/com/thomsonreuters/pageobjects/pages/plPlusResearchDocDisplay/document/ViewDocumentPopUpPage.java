package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


/**
 * This page object is to get the elements of View Document popup in Document display.
 * <p/>
 */

public class ViewDocumentPopUpPage extends AbstractPage {

    public static final By VIEW_OK_BUTTON = By.id("co_WarnViewOkButton");
    public static final By CANCEL_BUTTON = By.id("co_WarnViewCancelButton");
    public static final By POP_UP_CONTAINER = By.id("co_docWarning_text");
    public static final By POPUP_BUTTONS = By.cssSelector(".co_overlayBox_container li");
    public static final By POPUP_HEADLINE = By.className("co_overlayBox_headline");

    public void waitUntilPopupPageLoaded() {
        waitForElementPresent(POP_UP_CONTAINER);
        waitForElementPresent(POPUP_BUTTONS);
        waitForElementPresent(POPUP_HEADLINE);
    }

    /**
     * This method finds the View Document button is present or not and returns the boolean value
     *
     * @return boolean
     */
    
    public boolean isViewDocumentButtonPresent() {
    	return isElementDisplayed(VIEW_OK_BUTTON);
    }

    /**
     * This method finds the Cancel button is present or not and returns the boolean value
     *
     * @return boolean
     */
    
    public boolean isCancelButtonPresent() {
    	return isElementDisplayed(CANCEL_BUTTON);
    }

    private WebElement getViewDocumentButtonElement() {
        return findElement(VIEW_OK_BUTTON);
    }

    private WebElement getCancelButtonElement() {
        return waitFluentForElement(CANCEL_BUTTON);
    }

    /**
     * This method does the clicking on View Document button
     */
    public void clickOnViewDocumentButton() {
        try {
            getViewDocumentButtonElement().click();
            waitForElementInvisible(VIEW_OK_BUTTON);
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the View Document button : " + te.getMessage());
        }
    }

    /**
     * This method does the clicking on Cancel button
     */
    public void clickOnCancelButton() {
        try {
            getCancelButtonElement().click();
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the Cancel button : " + te.getMessage());
        }
    }

}