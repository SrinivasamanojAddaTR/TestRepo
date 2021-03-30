package com.thomsonreuters.pageobjects.folders;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FolderOptionsPage extends AbstractPage {

    public FolderOptionsPage() {
    }

    /**
     * This is the link entitled New Folder
     */
    public WebElement newFolderLink() {
        return waitForExpectedElement(By.xpath("//a[contains(@class,'co_saveToNewFolder')][text()='New Folder']"));
    }


    /**
     * This is the Save button
     */
    public WebElement saveButton() {
        return waitForExpectedElement(By.xpath("//input[contains(@class,'co_saveToDoSave')]"));
    }

    /**
     * This is the cancel button
     */
    public WebElement cancelButton() {
        return waitForExpectedElement(By.xpath("//a[@id='co_deliveryCancelButton']/strong[contains(text(),'Cancel')]"));
    }

    /**
     * This is a folder document link
     */
    public WebElement folderDocumentLink(String documentLinkText) {
        return waitForExpectedElement(By.linkText(documentLinkText),10);
    }
    
    /**
     * This is a drag drop box. It can appear if user would try to drag and drop any document to folder.
     */
    
    public By dragDropBoxElementLocator() {
        return By.id("co_dragDropBox_countText");
    }
    
    /**
     * This is a box with Recent Folders. It can appear if user would try to drag and drop any document to folder.
     */
    public WebElement dropdownBoxRecentFoldersElement() {
        return findElement(By.id("co_recentItems"));
    }
   


}
