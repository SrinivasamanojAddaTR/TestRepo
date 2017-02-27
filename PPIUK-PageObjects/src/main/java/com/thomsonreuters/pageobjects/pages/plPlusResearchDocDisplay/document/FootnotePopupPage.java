package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FootnotePopupPage extends AbstractPage {

	private static final By POPUP_LOCATOR = By.id("co_footnote_popup");

	public WebElement footnotePopup() {
		return retryingFindElement(POPUP_LOCATOR);
	}

	public boolean isFootnotePopupVisible() {
		return isExists(POPUP_LOCATOR) && waitForElementPresent(POPUP_LOCATOR).isDisplayed();
	}

	public WebElement closeButton() {
		return retryingFindElement(By.id("co_footnote_popup_closeButton"));
	}

	public WebElement footnoteText() {
		return footnotePopup().findElement(
				By.xpath(".//div[@class='co_footNoteText']/div | .//div[@class='co_footNoteText']"));
	}

	public WebElement linkInFootnoteText(String linkText) {
		return footnoteText().findElement(By.xpath(".//a[contains(text(),'" + linkText + "')]"));
	}

}
