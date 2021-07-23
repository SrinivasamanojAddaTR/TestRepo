package com.thomsonreuters.pageobjects.utils.folders;

import com.thomsonreuters.pageobjects.common.DocumentColumn;
import com.thomsonreuters.pageobjects.common.SortOptions;
import com.thomsonreuters.pageobjects.pages.folders.NewFolderPopup;
import com.thomsonreuters.pageobjects.pages.folders.SaveToPopup;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document_navigation.DocumentDeliveryPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;

/**
 * Class to replace {@link FoldersUtils} without JQuery waiters.
 * Sometimes we can observe the situation when jQuery.active > 0 even if content is fully loaded. Due to this,
 * {@link com.thomsonreuters.driver.framework.AbstractPage#waitForPageToLoadAndJQueryProcessing} wait will false fail.
 */
public class FoldersUtilsWithoutJquery extends FoldersUtils {

    private SaveToPopup saveToPopup = new SaveToPopup();
    private NewFolderPopup newFolderPopup = new NewFolderPopup();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private DocumentDeliveryPage documentDeliveryPage = new DocumentDeliveryPage();


    @Override
    public String saveToFolder(String folder) {
        String folderName = null;
        if (folder.equals("root")) {
            saveToPopup.rootFolder().click();
            folderName = saveToPopup.rootFolder().getText();
        } else {
            try {
                saveToPopup.expandRootFolderWait().click();
                saveToPopup.selectFolderWait(folder).click();
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Folder '" + folder + "'doesn't present");
            }
        }
        saveToPopup.save().click();
        // Wait until popup with message about document saving appears
        searchResultsPage.waitWhileFolderingPopupMessageBeVisible();
        return folderName;
    }

    @Override
    public String saveToNewFolder(String newFolderName, String parentFolder) {
        String folderName = null;
        saveToPopup.newFolder().click();
        folderName = createNewFolder(newFolderName, parentFolder);
        saveToPopup.waitFolderSelected(newFolderName);
        saveToPopup.save().click();
        // Wait until popup with message about document saving appears
        searchResultsPage.waitWhileFolderingPopupMessageBeVisible();
        return folderName;
    }

    @Override
    public String createNewFolder(String newFolderName, String parentFolder) {
        newFolderPopup.newFolderInput().sendKeys(newFolderName);
        if (parentFolder.equals("root")) {
            newFolderPopup.selectRootFolder().click();
            parentFolder = newFolderPopup.selectRootFolder().getText();
        } else {
            newFolderPopup.selectFolder(parentFolder).click();
        }
        newFolderPopup.waitForPageToLoad();
        newFolderPopup.save().click();
        newFolderPopup.waitPopUpToBeAbsent();
        return parentFolder;
    }

    /**
     * Add document to folder if necessary
     *
     * @param newFolderName The folder name where document should be saved. Pass this as null or empty if you don't
     *                      need to add document to create a folder
     * @param parentFolder  The parent folder name where folder with "newFolderName" should be created or exists. Pass
     *                      this as null or empty if don't need to add document to folder at all
     */
    public void addDocumentToFolder(String newFolderName, String parentFolder) {
        if (StringUtils.isNotEmpty(parentFolder)) {
            documentDeliveryPage.clickOnAddToFolderLink();
            if (StringUtils.isNotEmpty(newFolderName)) {
                saveToNewFolder(newFolderName, parentFolder);
            } else {
                saveToFolder(parentFolder);
            }
        }
    }
}
