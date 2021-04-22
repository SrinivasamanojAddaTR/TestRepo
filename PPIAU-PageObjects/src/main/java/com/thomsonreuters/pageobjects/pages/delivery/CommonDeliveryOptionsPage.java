package com.thomsonreuters.pageobjects.pages.delivery;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.utils.delivery.DeliveryFormField;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Common Page Objects for Email, Print and Download forms
 */

public class CommonDeliveryOptionsPage extends AbstractPage {
	
	private static final String COUNTRY_SELECTOR_TEXT = "Insert jurisdiction-specific material";

    public WebElement basicTab() {
        return waitForExpectedElement(By.cssSelector("#co_deliveryOptionsTab1 .co_tabLink"));
    }

    public WebElement advancedTab() {
        return findElement(By.cssSelector("#co_deliveryOptionsTab2 .co_tabLink"));
    }

    /**
     * Very bad hack but could not figure out a better way
     */
    public void waitTillAdvancedTabIsClickable() throws InterruptedException {
        List<WebElement> elementList;
        int counter = 10;
        do {
            elementList = findElements(By.cssSelector(".co_tabLeft.co_tabInactive.ddcdisplaymodefulltext.ddcdisplaymodelist"));
            counter--;
            Thread.sleep(2000);
        }
        while (!(elementList.isEmpty()) && counter >= 0);
    }

    public WebElement cancelButton() {
        return findElement(By.id("co_deliveryCancelButton"));
    }

    public WebElement submitButton () { return findElement(By.xpath("//input[contains(@class,'co_primaryBtn')]")); }

    public WebElement tableOfContents() {
        return findElement(DeliveryFormField.TABLE_OF_CONTENTS.getBy());
    }

    public WebElement annotations() {
        return findElement(By.id("coid_chkDdcContentAnnotations"));
    }

    public WebElement deliverDocument() {
        return findElement(DeliveryFormField.DOCUMENT.getBy());
    }

    public WebElement deliverOnlyDraftingNotes() {
        return findElement(DeliveryFormField.ONLY_DRAFTING_NOTES.getBy());
    }

    public WebElement deliverDocumentAndDraftingNotes() {
        return findElement(DeliveryFormField.DOCUMENT_AND_DRAFTING_NOTES.getBy());
    }

    public WebElement expandedMarginForNotes() {
        return waitForExpectedElement(DeliveryFormField.EXPAND_MARGIN_FOR_NOTES.getBy());
    }

    public WebElement coverPage() {
        return findElement(DeliveryFormField.COVER_PAGE.getBy());
    }

    public WebElement coverPageComment() {
        return findElement(DeliveryFormField.COVER_PAGE_COMMENT.getBy());
    }

    public WebElement links() {
        return findElement(DeliveryFormField.LINKS.getBy());
    }

    public WebElement underline() {
        return findElement(DeliveryFormField.UNDERLINE.getBy());
    }

    public WebElement fontSize() {
        return findElement(DeliveryFormField.FONTSIZE.getBy());
    }
    
	public boolean isJurisdictionSelectorDisabled() {
		return isExists(By.xpath("//div[contains(.,'" + COUNTRY_SELECTOR_TEXT
				+ "') and contains(@class,'kh_deliveryCountry')]/select[@id='kh_delivery_country_list' and @disabled]"));
	}
	
	public WebElement invalidStarPagesMessage() {
		return waitForExpectedElement(By.id("co_deliveryInvalidStarPagesMessage"));
	}

	public WebElement popupMessageOKButton() {
		return waitForExpectedElement(By.id("coid_continue_selectionButton"));
	}

    public boolean isClientIDPopupPresent() {
        return isElementDisplayed(By.xpath("//*[contains(@class, 'co_accessibleClientIdEntry')]"));
    }
}
