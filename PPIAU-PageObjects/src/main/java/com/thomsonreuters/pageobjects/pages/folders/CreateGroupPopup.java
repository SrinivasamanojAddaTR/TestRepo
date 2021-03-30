package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class CreateGroupPopup extends AbstractPage {
	
	public WebElement groupName() {
		return waitForExpectedElement(By.id("co_foldering_favorites_createGroup_groupName"));
	}

	public WebElement save() {
		return waitForExpectedElement(By.id("co_foldering_favorites_createGroup_saveButton"));
	}

}
