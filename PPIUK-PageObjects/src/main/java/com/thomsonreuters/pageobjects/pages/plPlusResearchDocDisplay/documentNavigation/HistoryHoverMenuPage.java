package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.*;
import org.slf4j.LoggerFactory;



public class HistoryHoverMenuPage extends AbstractPage {

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(HistoryHoverMenuPage.class);

    public static final By ALL_HISTORY_DOCUMENTS_LOCATOR = By.cssSelector("#co_recentHistoryContainer .co_recentResearch_item>a");
    public static final By RECENTLY_ACCESSED_DOCUMENTS_LOCATOR = By.cssSelector("#co_recentDocumentsList li>div>a");

    /**
     * This method is to wait until the History hover menu is loaded fully.
     */
    public void waitUntilMenuLoadedFully() {
        try {
            retryingFindElements(ALL_HISTORY_DOCUMENTS_LOCATOR);
        } catch (PageOperationException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to load the History hover menu :" + te.getMessage());
        }
    }

    /**
     * This method verifies the given document name is present in history documents list and name is clickable
     *
     * @param docName
     * @return boolean
     */
    public boolean isDocumentNamePresentAndClickableInHistory(String docName) {
        try {
            for (WebElement ele : retryingFindElements(RECENTLY_ACCESSED_DOCUMENTS_LOCATOR)) {
                if (ele.getText().contains(docName)) {
                    return true;
                }
            }
        } catch (PageOperationException pe) {
            LOG.info("context", pe);
        } catch (StaleElementReferenceException | ElementNotVisibleException | NoSuchElementException sen) {
            LOG.info("context", sen);
            isDocumentNamePresentAndClickableInHistory(docName);
        }
        return false;
    }

}