package com.thomsonreuters.pageobjects.utils.delivery;

/**
 * Created by Pavel_Ardenka on 06/10/2016.
 */
public enum DeliveryType {

    DOWNLOAD("The items are being prepared for download","The items are ready to download"),
    PRINT("The items are being prepared for printing", "Loading print request...\n" +
            "Printer options will appear when loading is complete."),
    EMAIL("The items are being prepared for email", "The items will be emailed");

    String preparingConfirmationMessage;

    String readyConfirmationMessage;

    DeliveryType(String preparingMessage, String readyMessage) {
        this.preparingConfirmationMessage = preparingMessage;
        this.readyConfirmationMessage = readyMessage;
    }

    public String getReadyConfirmationMessage() {
        return readyConfirmationMessage;
    }

    public String getPreparingConfirmationMessage() {
        return preparingConfirmationMessage;
    }
}
