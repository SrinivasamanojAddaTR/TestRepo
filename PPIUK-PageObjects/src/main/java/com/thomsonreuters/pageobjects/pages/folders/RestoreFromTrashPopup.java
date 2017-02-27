package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Pavel_Ardenka on 29.10.2015.
 * Ideas: all popups has the pageobjects logic which can be placed in one base class
 */

public class RestoreFromTrashPopup extends AbstractPage {

    private String dialogXpath = "//div[contains(@class, 'Overlay') and not(contains(@class, 'hide'))]";

    /**
     * Select folder
     * @param targetFolderName Folder name which should be selected
     */
    public WebElement selectFolder(String targetFolderName) {
        return waitForExpectedElement(By.xpath(dialogXpath + "//a[.='" + targetFolderName + "']"));
    }

    /**
     * Click Move Button
     */
    // Common XPath for every active button except "Cancel": dialogXpath +  //input[contains(@class, 'saveTo') and not(contains(@class, 'hide'))]
    public WebElement moveButton() {
        return waitForExpectedElement(By.xpath(dialogXpath + "//input[@value='Move']"));
    }

}
