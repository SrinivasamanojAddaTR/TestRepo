package com.thomsonreuters.pageobjects.pages.folders;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;




public class RenameFolderPopup extends AbstractPage {


	private CommonMethods comMethods;

	public WebElement renameFolder() {
		return waitForExpectedElement(By.id("cobalt_ro_folder_action_textbox"));
	}

	public WebElement save() {
		return waitForElementVisible(By.xpath("//*[@type='button' and @value='OK']"));
	}

}
