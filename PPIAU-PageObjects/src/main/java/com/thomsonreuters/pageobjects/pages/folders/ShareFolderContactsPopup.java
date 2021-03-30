package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ShareFolderContactsPopup extends AbstractPage {

	private PageActions pageActions = new PageActions();

	public WebElement contact(String contactName) {
		return waitForExpectedElement(By.xpath("//a[@class='co_contacts_selectable co_checkbox_unselected' and contains(text(),'" + contactName +"')]"));
	}

	public WebElement shareSubFoldersCheckBox() {
		return waitForExpectedElement(By.id("co_folderingShareFolderInclSubfolders"));
	}

	public WebElement selectRole(String contactName) {
		return waitForExpectedElement(By.xpath("(//td[text()='" + contactName + "']/parent::tr/td)[2]/select"));
	}

	public WebElement selectRoleOnAddPeopleGroupsForm(String contactName) {
		return waitForExpectedElement(By.xpath("//form[@class='co_shareFolder_collaboratorsAndRolesForm']//td[text()='" + contactName + "']//following-sibling::td[@class='co_detailsTable_roles']/select"));
	}

}
