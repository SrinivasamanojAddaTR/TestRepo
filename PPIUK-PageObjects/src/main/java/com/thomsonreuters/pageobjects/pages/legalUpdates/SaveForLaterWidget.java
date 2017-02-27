package com.thomsonreuters.pageobjects.pages.legalUpdates;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class SaveForLaterWidget extends AbstractPage {
	
	public SaveForLaterWidget() {
		
	}
	
	public WebElement rootResearchFolder() {
		return findElement(By.xpath("//a[@class='co_tree_selectable co_tree_name co_tree_position--0--' and contains(text(),'Research')]"));
	}
	
	public WebElement saveButton() {
		return findElement(By.xpath("//input[@class='co_dropdownBox_ok co_saveToDoSave' and @type='button']"));
	}
	
	public void saveDocumentToRootFolder(){
		rootResearchFolder().click();
		saveButton().click();
		waitForPageToLoadAndJQueryProcessing();
	}

}
