package com.thomsonreuters.pageobjects.pages.delivery;

import com.thomsonreuters.pageobjects.rest.model.request.delivery.initiateDelivery.InitiateDelivery;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Option for download delivery
 */
public class DownloadOptionsPage extends CommonDeliveryOptionsPage {

    /**
     * This is the checkbox for selecting when wishing to deliver only pages with terms
     */
    public WebElement checkboxForPagesWithTerms() {
        return waitForExpectedElement(By.id("coid_chkDdcLayoutOnlyPagesWithSearchTerms"));
    }

    /**
     * This is the format dropdown
     */
	public WebElement formatDropdown() {
		return waitForExpectedElement(By.xpath(".//*[@id='co_delivery_format_fulltext']"));
	}

	public WebElement formatDropdownForGraphicalHistory() {
		return waitForExpectedElement(By.xpath(".//*[@id='co_delivery_format_list']"));
	}

    /**
     * This is the download button for submitting the request
     */
    public WebElement downloadButton() {
		return waitForElementToBeClickable(waitForExpectedElement(By.id("co_deliveryDownloadButton")));
    }

    public WebElement exportButton() {
		return waitForElementToBeClickable(waitForExpectedElement(By.id("co_deliveryExportButton")));
    }

    public WebElement confirmDownloadButton() {
		return waitForExpectedElement(By.id("coid_deliveryWaitMessage_downloadButton"), 20);
    }

    /**
     * Block with what to deliver radio-buttons
     *
     * @return Element with block or null if element not exists. WITHOUT EXCEPTIONS
     */
    public WebElement getWhatToDeliverBlock() {
        By locator = By.id("co_delivery_whatToDeliverContainer");
        return isExists(locator) ? findElement(locator) : null;
    }

    /**
     * Get radio button for what to deliver option
     *
     * @param name Radio button text from UI (e.g., "Document", "Document and Drafting Notes")
     * @return Element with radio-button
     */
    public WebElement getWhatToDeliverRadioButton(String name) {
        InitiateDelivery.WhatToDeliverOption whatToDeliverOption = InitiateDelivery.WhatToDeliverOption.getByUiName("name");
        if (whatToDeliverOption == null) {
            return findChildElement(getWhatToDeliverBlock(), By.xpath("//label[contains(., '" + name + "')]"));
        } else {
            throw new IllegalArgumentException("Unknown delivery option: '" + name + "'. It should be one of: "
                    + Arrays.asList(InitiateDelivery.WhatToDeliverOption.values()));
        }
    }

    /**
     * Get Preparing / Ready For Download window element (e.g., for checking it text content)
     *
     * @return Element with Preparing / Ready For Download window
     */
    public WebElement getReadyForDownloadWindow() {
        return waitForExpectedElement(By.id("co_deliveryWaitMessage"));
    }

    /**
     * Get Download button from Ready For Download window
     *
     * @return Element with Download button
     */
    public WebElement getDownloadButtonWhenDocReadyToDownload() {
        return waitForExpectedElement(By.id("coid_deliveryWaitMessage_downloadButton"));
    }

    /**
     * Wait while delivery options or delivery waiting / preparing popup won't be absent
     */
    public void waitWhileDeliveryPopupsAbsent() {
        waitForElementAbsent(By.id("co_deliveryLightbox"));
        waitForElementAbsent(By.id("co_deliveryWaitMessage"));
    }

    /**
     * Wait while delivery progress image won;t be absent
     */
    public void waitWhileProgressImageBeAbsent() {
        waitForElementAbsent(By.id("co_deliveryWaitProgress"));
    }
}
