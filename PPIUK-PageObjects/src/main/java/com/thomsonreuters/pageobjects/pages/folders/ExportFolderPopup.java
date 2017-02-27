package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class ExportFolderPopup extends AbstractPage{
	

	public WebElement exportPopup() {
		return waitForExpectedElement(By.id("co_deliveryFolderExport_folderView"));
	}
	
	public boolean isExportPopupPresent() {
		return isElementPresent(By.id("co_deliveryFolderExport_folderView"));
	}

	public WebElement expandFolder() {
		return waitForExpectedElement(By.xpath("//*[@class='co_deliveryFolderExport_tree']//a[contains(@class,'co_tree_expand')]"));
	}

	public boolean isFolderVisible(String folderName) {
		return isElementPresent(By.xpath("//*[@class='co_deliveryFolderExport_tree']//a[contains(.,'"+folderName+" (')]"));
	}

	public WebElement checkBox(String folderName) {
		return waitForExpectedElement(By.xpath("//*[@class='co_deliveryFolderExport_tree']//a[contains(.,'"+folderName+" (')]/ancestor::div/input"));
	}

	public WebElement next() {
		return waitForExpectedElement(By.id("coid_deliveryExportNext"));
	}
	
	public WebElement cancel() {
		return waitForExpectedElement(By.id("coid_deliveryExportCancel"));
	}

	public WebElement back() {
		return waitForExpectedElement(By.id("co_deliveryExportBackButton"));
	}

}


