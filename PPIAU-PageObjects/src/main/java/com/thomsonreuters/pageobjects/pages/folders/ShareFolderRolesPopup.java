package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ShareFolderRolesPopup extends AbstractPage {

    public WebElement shareButton() {
        return waitForExpectedElement(By.id("co_folderingShareFolderCommit"));
    }

    public WebElement addButton() {
        return waitForExpectedElement(By.xpath("//input[@id='co_folderingShareFolderCommit' and @value='Add']"));
    }

    public WebElement cancel() {
        return waitForExpectedElement(By.id("co_folderingShareFolderCancel"));
    }

    public WebElement closeFolderPopUp() {
        return waitForExpectedElement(By.id("co_folderingShareFolderClose"));
    }

}
