package com.thomsonreuters.pageobjects.utils.folders;

import com.thomsonreuters.pageobjects.common.DocumentColumn;
import com.thomsonreuters.pageobjects.common.SortOptions;
import com.thomsonreuters.pageobjects.pages.folders.NewFolderPopup;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.folders.SaveToPopup;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.DocumentDeliveryPage;
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
    public void openFolder(String folder) {
        super.openFolder(folder);
    }

    @Override
    public boolean doesFolderExist(String folder) {
        return super.doesFolderExist(folder);
    }

    @Override
    public void shareFolder(String folderName) {
        super.shareFolder(folderName);
    }

    @Override
    public void shareFolderViaEmail(String owner, String folderName, String userNameToShare) {
        super.shareFolderViaEmail(owner, folderName, userNameToShare);
    }

    @Override
    public void shareFolderViaEmailByEmail(String owner, String folderName, String emailToShare) {
        super.shareFolderViaEmailByEmail(owner, folderName, emailToShare);
    }

    @Override
    public void createNewGroupToShareFolder(String groupName, String member) {
        super.createNewGroupToShareFolder(groupName, member);
    }

    @Override
    public boolean isDocSortedBy(DocumentColumn column, SortOptions sortDirection) {
        return super.isDocSortedBy(column, sortDirection);
    }

    @Override
    public void moveToOriginalFolder(String folderName) {
        super.moveToOriginalFolder(folderName);
    }

    @Override
    public void waitAjaxIfNecessary() {
        super.waitAjaxIfNecessary();
    }

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

    @Override
    public void deleteFolder(String folderName) {
        super.deleteFolder(folderName);
    }

    @Override
    public void renameFolderDoubleClick(String folderName) {
        super.renameFolderDoubleClick(folderName);
    }

    @Override
    public void renameFolder(String folderName) {
        super.renameFolder(folderName);
    }

    /**
     * Add document to folder if necessary
     *
     * @param newFolderName The folder name where document should be saved. Pass this as null or empty if you don't
     *                      need to add document to create a folder
     * @param parentFolder The parent folder name where folder with "newFolderName" should be created or exists. Pass
     *                     this as null or empty if don't need to add document to folder at all
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
