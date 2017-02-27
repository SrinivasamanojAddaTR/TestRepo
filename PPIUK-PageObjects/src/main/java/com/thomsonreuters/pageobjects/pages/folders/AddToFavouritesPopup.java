package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;



public class AddToFavouritesPopup extends AbstractPage {

	public WebElement groupCheckbox(String newGroupName) {
		return waitForExpectedElement(By.xpath("//label[text()='" + newGroupName + "']/input[@type='checkbox']"));
	}

	public WebElement createGroupLink() {
		return waitForExpectedElement(By.id("createGroupLink"));
	}

	public WebElement createGroupButton() {
		return waitForExpectedElement(By.id("co_foldering_favorites_createGroup_saveButton"));
	}

	public WebElement newGroupInput() {
		return waitForExpectedElement(By.xpath("//input[@name='groupName']"));
	}

	public WebElement save() {
		return waitForExpectedElement(By.xpath("//input[@type='button' and @value='Save']"));
	}

	public boolean isGroupPresent(String groupName) {
		waitForPageToLoad();
		try {
			findElement(By.xpath("//label[text()='" + groupName + "']/input[@type='checkbox']"));
		} catch (NoSuchElementException e) {
			LOG.info("Group '" + groupName + "' is absent");
			return false;
		}
		return true;
	}

}
