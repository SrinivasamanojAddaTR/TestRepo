package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


import java.util.List;

/**
 * This page object is to get the elements of Add To Folder popup in Document display.
 * <p/>
 */

public class AddToFolderPage extends AbstractPage {

    private static final By ADD_TO_FOLDER_SAVE_BUTTON = By.cssSelector(".co_lightboxOverlay .co_dropdownBox_ok.co_saveToDoSave");
    private static final By ADD_TO_FOLDER_CANCEL_BUTTON = By.cssSelector(".co_lightboxOverlay .co_dropdownBox_cancel");
    private static final By ADD_TO_FOLDER_HEADER_CLOSE_BUTTON = By.className("co_overlayBox_closeButton");
    private static final By ADD_TO_FOLDER_CONTAINER = By.cssSelector(".co_lightboxOverlay .co_tree_element.co_tree_position--0--");
    private static final By ADD_TO_FOLDER_EXPAND_BUTTON_LOCATOR = By.cssSelector(".co_lightboxOverlay .co_tree_toggle.co_tree_position--0--.co_tree_expand");
    private static final By NEW_FOLDER_LINK_LOCATOR = By.className("co_saveToNewFolder");
    private static final By NEW_FOLDER_SAVE_BUTTON_LOCATOR = By.cssSelector(".co_new_folderAction .co_dropdownBox_ok");
    private static final By NEW_FOLDER_TEXT_BOX_LOCATOR = By.cssSelector("#cobalt_ro_folder_action_textbox");
    private static final By NEW_FOLDER_CONTAINER_LOCATOR = By.cssSelector(".co_new_folderAction a");
    private static final By FOLDERS_LIST_LOCATOR = By.cssSelector(".co_lightboxOverlay ul fieldset a.co_tree_selectable");
    private static final By ROOT_FOLDER_LOCATOR = By.cssSelector(".co_lightboxOverlay .co_tree_name.co_tree_position--0--");

    public void waitUntilAddToFolderOptionsLoaded() {
        waitForElementsVisible(ADD_TO_FOLDER_HEADER_CLOSE_BUTTON);
        waitForExpectedElements(ADD_TO_FOLDER_CONTAINER);
        waitForElementsVisible(ADD_TO_FOLDER_SAVE_BUTTON);
        waitForElementsVisible(ADD_TO_FOLDER_CANCEL_BUTTON);
        waitForElementsVisible(NEW_FOLDER_LINK_LOCATOR);
    }

    private void waitUntilNewFolderOptionsPresent() {
        waitForElementsVisible(NEW_FOLDER_CONTAINER_LOCATOR);
        waitForElementsVisible(NEW_FOLDER_SAVE_BUTTON_LOCATOR);
        waitForElementsVisible(NEW_FOLDER_TEXT_BOX_LOCATOR);
    }

    /**
     * This method does the clicking on Save to AddToFolder button
     */
    public void clickSaveButton() {
        try {
            waitFluentForElement(ADD_TO_FOLDER_SAVE_BUTTON).click();
            waitForElementInvisible(ADD_TO_FOLDER_SAVE_BUTTON);
            waitAndFindElement(By.cssSelector("#co_docToolbarSaveToWidget .co_dropdownTitle.is-active"));
        } catch (TimeoutException te) {
            LOG.info("Time out on trying to click the Add To Folder button", te);
            throw new PageOperationException("Exceeded time to find the Save to AddToFolder button : " + te.getMessage());
        }
    }

    /**
     * This method does the clicking on Create New Folder link from add to folder popup
     */
    private void clickOnCreateNewFolder() {
        try {
            waitFluentForElement(NEW_FOLDER_LINK_LOCATOR).click();
        } catch (TimeoutException te) {
            LOG.info("Time out on trying to click the Create New Folder button", te);
            throw new PageOperationException("Exceeded time to find the CreateNewFolder button : " + te.getMessage());
        }
    }

    /**
     * This method will be invoked when the expected folder is not present on add to folder popup, it will create the new folder under parent folder.
     *
     * @param folderPath
     * @param folderName
     */
    private void createNewFolder(List<String> folderPath, String folderName) {
        clickOnCreateNewFolder();
        waitUntilNewFolderOptionsPresent();
        enterNewFolderName(folderName);
        selectRootFolderName();
        saveNewFolder();
    }

    /**
     * This method does the clicking on save or ok button to save the new folder name.
     */
    private void saveNewFolder() {
        try {
            waitFluentForElement(NEW_FOLDER_SAVE_BUTTON_LOCATOR).click();
            waitForElementInvisible(NEW_FOLDER_SAVE_BUTTON_LOCATOR);
        } catch (TimeoutException te) {
            LOG.info("Time out on trying to click the Save To Folder button", te);
            throw new PageOperationException("Exceeded time to find and select the Save new folder button " + te.getMessage());
        }
    }

    /**
     * This method does the selecting the parent/my folder in add to folder popup
     */
    private void selectRootFolderName() {
        try {
            waitFluentForElement(ROOT_FOLDER_LOCATOR).click();
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the Root folder in New folder creation" + te.getMessage());
        }
    }

    /**
     * This method enters the given folder name into the new folder text box.
     *
     * @param folderName
     */
    private void enterNewFolderName(String folderName) {
        if (folderName == null) {
            throw new PageOperationException("Folder name is required");
        }
        try {
            waitFluentForElement(NEW_FOLDER_TEXT_BOX_LOCATOR).sendKeys(folderName);
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the New folder text box " + te.getMessage());
        }
    }

    /**
     * This method is used to select the folder to add the document under given folder path.
     *
     * @param folderPath
     */
    public void selectFolderToAddTheDocument(List<String> folderPath) {
        if (folderPath.size() <= 0) {
            throw new PageOperationException("Add to folder name is required");
        }
        try {
            if (folderPath.size() > 1) {
                waitAndFindElement(ADD_TO_FOLDER_EXPAND_BUTTON_LOCATOR).click();
                for (WebElement folder : getExistingSubFolders()) {
                    if (folder.getText().equalsIgnoreCase(folderPath.get(1))) {
                        folder.click();
                        return;
                    }
                }
                createNewFolder(folderPath, folderPath.get(1));
            } else {
                selectRootFolderName();
            }
        } catch (NoSuchElementException te) {
            LOG.info("context", te);
            throw new PageOperationException("Unable to find the folder text " + te.getMessage());
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the expand folder list " + te.getMessage());
        }
    }

    /**
     * This method gets the existing sub folders under user folder.
     *
     * @return List<WebElement>
     */
    private List<WebElement> getExistingSubFolders() {
        try {
            return waitAndFindElements(FOLDERS_LIST_LOCATOR);
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the folders list " + te.getMessage());
        }
    }

}