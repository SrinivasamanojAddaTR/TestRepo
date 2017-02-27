package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.*;
import org.slf4j.LoggerFactory;


import java.util.List;

/**
 * This page object is to get the elements of My Folders page in OOB.
 * <p/>
 */

public class MyFolderPage extends AbstractPage {

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyFolderPage.class);

    private static final By FOLDER_TREE_NAVIGATION_LOCATOR = By.cssSelector("#cobalt_ro_myFolders_folderTree div.co_tree_element.co_tree_closedFolder a");
    private static final By ROOT_FOLDER_LOCATOR = By.cssSelector(".co_tree_selectable.co_tree_name.co_tree_position--0--");
    private static final By CO_FOLDERING_PROGRESS_INDICATOR = By.className("co_foldering_progress_indicator");

    /**
     * This method is to wait until the My folders are loaded fully.
     */
    public void waitUntilFoldersLoadedFully() {
        try {
            retryingFindElements(FOLDER_TREE_NAVIGATION_LOCATOR);
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to load the History hover menu :" + te.getMessage());
        }
    }

    /**
     * This method verifies the given document name is present in folders list and the document name
     *
     * @param docName
     * @return boolean
     */
    public boolean isDocumentNamePresentInFolder(String docName, List<String> folderPath) {
        if (folderPath.size() <= 0) {
            throw new PageOperationException("Add to folder name is required");
        }
        selectFolderPath(folderPath);
        try {
        	return isElementDisplayed(By.partialLinkText(docName));
        } catch (StaleElementReferenceException | ElementNotVisibleException ste) {
            LOG.info("context", ste);
            return isDocumentNamePresentInFolder(docName, folderPath);
        }
    }

    /**
     * This method is used to select the folder under given folder path.
     *
     * @param folderPath
     */
    public void selectFolderPath(List<String> folderPath) {
        String folderName = null;
        if (folderPath.size() <= 0) {
            throw new PageOperationException("folder name is required");
        }
        selectRootFolderName();
        try {
            if (folderPath.size() > 1) {
                for (WebElement ele : retryingFindElements(FOLDER_TREE_NAVIGATION_LOCATOR)) {
                    if (ele.getText().equalsIgnoreCase(folderPath.get(1))) {
                        ele.click();
                    }
                }
            }
        } catch (NoSuchElementException nte) {
            LOG.info("context", nte);
        } catch (TimeoutException te) {
            LOG.info("context", te);
        } catch (StaleElementReferenceException | ElementNotVisibleException ste) {
            LOG.info("context", ste);
            selectFolderPath(folderPath);
        }
    }

    /**
     * This method does the selecting the user parent folder in folders page.
     */
    private void selectRootFolderName() {
        try {
            waitFluentForElement(ROOT_FOLDER_LOCATOR).click();
            waitForElementInvisible(CO_FOLDERING_PROGRESS_INDICATOR);
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the Root folder in New folder creation" + te.getMessage());
        } catch (StaleElementReferenceException | ElementNotVisibleException ste) {
            LOG.info("context", ste);
            selectRootFolderName();
        }
    }

    /**
     * This method verifies and click on the given document name is present in folder.
     *
     * @param docName
     */
    public void clickOnDocumentName(String docName) {
        try {
            retryingFindElement(By.partialLinkText(docName)).click();
        } catch (PageOperationException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the DocumentName" + te.getMessage());
        }
    }

}