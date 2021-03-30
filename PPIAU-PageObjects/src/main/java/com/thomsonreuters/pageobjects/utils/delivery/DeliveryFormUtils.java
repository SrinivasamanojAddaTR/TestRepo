package com.thomsonreuters.pageobjects.utils.delivery;

import com.google.common.base.Function;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.delivery.DownloadOptionsPage;
import com.thomsonreuters.pageobjects.pages.delivery.EmailOptionsPage;
import com.thomsonreuters.pageobjects.pages.delivery.PrintOptionsPage;
import com.thomsonreuters.pageobjects.rest.DeliveryBaseUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by Pavel_Ardenka on 06/10/2016.
 */
public class DeliveryFormUtils {

    private DownloadOptionsPage download = new DownloadOptionsPage();
    private PrintOptionsPage print = new PrintOptionsPage();
    private EmailOptionsPage email = new EmailOptionsPage();
    private DeliveryBaseUtils deliveryBaseUtils = new DeliveryBaseUtils();

    private static final String READY_TO_DOWNLOAD_MESSAGE_PART = "ready to download";
    private static final Logger LOG = LoggerFactory.getLogger(DeliveryFormUtils.class);

    public void selectWhatToDeliverOptionInDeliveryOptionsWindow(String whatToDeliverOptionString) {
        if (download.getWhatToDeliverBlock() != null && whatToDeliverOptionString != null && !whatToDeliverOptionString.isEmpty()) {
            download.getWhatToDeliverRadioButton(whatToDeliverOptionString).click();
        }
    }

    public void clickPrintInDeliveryOptionsWindow() {
        download.waitForElementToBeClickable(print.printButton());
        print.printButton().click();
    }

    public void clickDownloadInDeliveryOptionsWindow() {
        download.waitForElementToBeClickable(download.downloadButton());
        download.downloadButton().click();
    }

    public void clickEmailInDeliveryOptionsWindow() {
        download.waitForElementToBeClickable(email.emailButton());
        email.emailButton().click();
    }

    public void clickCorrespondingDeliveryButton(DeliveryType deliveryType){
        switch (deliveryType) {
            case DOWNLOAD:
                clickDownloadInDeliveryOptionsWindow();
                break;
            case PRINT:
                clickPrintInDeliveryOptionsWindow();
                break;
            case EMAIL:
                clickEmailInDeliveryOptionsWindow();
                break;
            default:
                throw new UnsupportedOperationException("Delivery for type " + deliveryType + " is not implemented");
        }
    }

    public File downloadDocumentAndGetFile(DeliveryType deliveryType) {
        return downloadDocumentAndGetFile(deliveryType, "");
    }

    public File downloadDocumentAndGetFile(DeliveryType deliveryType, String whatToDeliverOptionString) {
        selectWhatToDeliverOptionInDeliveryOptionsWindow(whatToDeliverOptionString);
        clickCorrespondingDeliveryButton(deliveryType);
        download.waitWhileProgressImageBeAbsent();
        return (deliveryType != DeliveryType.EMAIL) ?
                deliveryBaseUtils.downloadAndGetDocument(deliveryType == DeliveryType.PRINT) : null;
    }

    public boolean isReadyToDownload() {
        return isReadyToDownloadHeaderContains(READY_TO_DOWNLOAD_MESSAGE_PART);
    }

    private boolean isReadyToDownloadHeaderContains(final String headerTitle) {
        try {
            WebElement readyToDownloadPopup = download.getReadyForDownloadWindow();
            Function<WebElement, Boolean> readyToDownloadCondition = new Function<WebElement, Boolean>() {
                @Override
                public Boolean apply(WebElement webElement) {
                    return webElement.getText().contains(headerTitle);
                }
            };
            return AbstractPage.waitFor(readyToDownloadCondition, readyToDownloadPopup);
        } catch (TimeoutException e) {
            LOG.info("Delivery is not successful");
            return false;
        }
    }

}
