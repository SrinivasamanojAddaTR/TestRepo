package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewGroupPopup extends AbstractPage {

	public WebElement groupName() {
		return waitForExpectedElement(By.id("coid_contacts_newGroupName"));
	}
	
	public WebElement people(String name) {
		return waitForExpectedElement(By.xpath("//a[text()='" + name + "']"));
	}
	
	public WebElement saveGroup() {
		return waitForExpectedElement(By.id("coid_contacts_newGroup_saveButton"));
	}
}
