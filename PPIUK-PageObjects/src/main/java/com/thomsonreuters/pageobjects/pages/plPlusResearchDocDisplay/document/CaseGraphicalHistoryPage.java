package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import com.thomsonreuters.driver.framework.AbstractPage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CaseGraphicalHistoryPage extends AbstractPage {

	private static final By GRAPHICAL_HISTORY_POPUP = By.id("co_graphicalHistory_lightbox");

	public WebElement graphicalHistoryPopup() {
		return waitForExpectedElement(GRAPHICAL_HISTORY_POPUP);
	}

	public boolean isGraphicalHistoryPopupDisplayed() {
		return isElementDisplayed(GRAPHICAL_HISTORY_POPUP);
	}

	public WebElement graphicalHistoryDeliveryButton(String deliveryOption) {
		return waitForExpectedElement(By.id("deliveryLinkRow1" + deliveryOption));
	}

	public boolean isEmailDeliveryOptionsPanelDisplayed() {
		return isElementDisplayed(By.id("co_gh_emailOptions"));
	}

	public WebElement graphicalHistoryTitle() {
		return graphicalHistoryPopup().findElement(By.tagName("h3"));
	}


	public WebElement graphicalHistoryImage() {
		return waitForElementVisible(By.cssSelector("div#co_gh_svg>svg"));
	}

	public WebElement link(int number) {
		List<WebElement> links = findElements(By.cssSelector("div#co_gh_svg>svg a"));
		return links.get(number - 1);
	}

	public WebElement closeButton() {
		return graphicalHistoryPopup().findElement(By.id("co_graphicalHistory_lightbox_closeButton"));
	}
}
