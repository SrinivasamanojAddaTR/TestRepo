package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class DeleteFolderPopup extends AbstractPage {

	public WebElement clickCancel() {
		return waitForExpectedElement(By.xpath("(//*[@class='co_dropdownBox_cancel'])[last()]"));
	}

	public WebElement clickOK() {
		return waitForExpectedElement(By.xpath("//*[@type='button' and @value='OK']"));
	}

	public WebElement getDeletedMessage() {
		return waitForExpectedElement(By
				.xpath("//*[contains(@class, 'co_delete_folderAction')]//*[@class='co_overlayBox_content']"));
	}
	
}
