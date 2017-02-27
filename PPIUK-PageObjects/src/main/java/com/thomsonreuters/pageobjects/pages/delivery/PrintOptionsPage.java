package com.thomsonreuters.pageobjects.pages.delivery;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Steph Armytage on 07/01/2015. This is the pop up which appears when the user
 * selects the option for print delivery
 */

public class PrintOptionsPage extends CommonDeliveryOptionsPage {

    /**
     * This is the print button for submitting the request
     */
     public WebElement printButton() {
         return waitForElementToBeClickable(waitForExpectedElement(By.id("co_deliveryPrintButton")));
     }

}
