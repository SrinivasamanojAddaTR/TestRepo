package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;




public class SaveToPopup extends AbstractPage {

	private PageActions pageActions;
	private CommonMethods commonMethods = new CommonMethods();

    public SaveToPopup(){
        pageActions = new PageActions();
    }

	public WebElement save() {
		return waitForExpectedElement(By.xpath("//*[contains(@class, 'saveToDoSave')]"), 30);
	}

	public WebElement rootFolder() {
		return waitForExpectedElement(By
				.xpath("//div[contains(@class, 'co_saveTo_folderAction')]//div[contains(@class, 'co_listItem co_tree_position--0--') and not(contains(@class,'co_shared'))]//a[contains(@class, 'co_tree_name')]"));
	}

	public WebElement expandRootFolder() {
		return findElement(By.xpath("//*[@class='co_tree_toggle co_tree_position--0-- co_tree_expand']"));
	}
	
	public WebElement expandRootFolderWait() {
		return waitForExpectedElement(By.xpath("//*[@class='co_tree_toggle co_tree_expand co_tree_position--0--']"));
	}

	public boolean isRootFolderExpanded(){
		return isElementDisplayed(By.xpath("//*[@class='co_tree_toggle co_tree_expand co_tree_position--0--']"));
	}

	public WebElement newFolder() {
		return waitForExpectedElement(By.xpath("//div[contains(@class, 'Overlay') and not(contains(@class, 'hide'))]//a[contains(@class, 'saveToNewFolder')]"),15);
	}
	
	public WebElement selectFolder(String folder) {
		return findElement(By.xpath("(//a[text()='" + folder + "'])[last()]"));
	}
	
	public WebElement selectFolderWait(String folder) {
		return waitForExpectedElement(By.xpath("(//a[.='" + folder + "'])[last()]"));
	}

	public WebElement getPopUp() {
		return waitForExpectedElement(By
				.xpath("//div[@class='co_overlayBox_container co_folderAction co_saveTo_folderAction']"));
	}

	public boolean isPopUpDispayed() {
		return commonMethods.isElementDisplayed(getPopUp());
	}

	public void waitFolderSelected(String folderName) {
		try {
			waitForElementVisible(By
					.xpath("//div[@class='co_myFolders']//div[contains(@class,'co_treeFocus')]//a[.='" + folderName + "']"));
		} catch (TimeoutException e) {
			throw new RuntimeException("New folder is not selected on Save to Folder popup");
		}
	}

	public WebElement saveButton() {
		return waitForExpectedElement(By.xpath("//li/input[contains(@class,'saveToDoSave')][@type='button']"),15);
	}

	public WebElement cancelButton() {
		return waitForExpectedElement(By.xpath("//li/a[contains(@class,'co_dropdownBox_cancel')]"));
	}
	
	public void clickCancelButtonIfPresent() {
		By xpath = By.xpath("//li/a[contains(@class,'co_dropdownBox_cancel')]");
		if(isExists(xpath)){
			waitForExpectedElement(xpath,15).click();
		}
	}

}
