package com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by u0171568 (Asha Shetty) on 15/07/2015. This class is created to hold delivery page objects on the
 * list of items pages (For eg: Topic Pages and Search results page)
 */

public class ListOfItemsDeliveryOptionsPage extends AbstractPage {

    public WebElement emailLink() {
        return findElement(By.cssSelector("#deliveryLinkRow1Email span[class='delivery_icon th_flat_icon icon_email']"));
    }
    public WebElement printLink() {
        return findElement(By.cssSelector("#deliveryLinkRow1Print span[class='delivery_icon th_flat_icon icon_print']"));
    }
    public WebElement downloadLink() {
        return findElement(By.cssSelector("#deliveryLinkRow1Download span[class='delivery_icon th_flat_icon icon_download']"));
    }
    public WebElement saveToFolderLink() {
        return findElement(By.xpath("//span[contains(@class,'th_flat_icon icon_folder') and not(contains(@class,'outline'))]"));
    }

	public boolean EnabledDeliveryOption(String option) {
		By locator = null;
		switch (option) {
		case "Email":
			locator = By.cssSelector("#deliveryLinkRow1Email span[class='delivery_icon th_flat_icon icon_email']");
			break;
		case "Print":
			locator = By.cssSelector("#deliveryLinkRow1Print span[class='delivery_icon th_flat_icon icon_print']");
			break;
		case "Download":
			locator = By.cssSelector("#deliveryLinkRow1Download span[class='delivery_icon th_flat_icon icon_download']");
			break;
		case "Save to Folder":
			locator = By.xpath("//span[contains(@class,'th_flat_icon icon_folder') and not(contains(@class,'outline'))]");
			break;
		default:
			throw new RuntimeException(String.format("Unknown option '%s'", option));
		}
		return isElementDisplayed(locator);
	}
    
	public boolean DisabledDeliveryOption(String option) {
		By locator = null;
		switch (option) {
		case "Email":
			locator = By.xpath("//span[contains(@class,'th_flat_icon icon_email') and (contains(@class,'outline'))]");
			break;
		case "Print":
			locator = By.xpath("//span[contains(@class,'th_flat_icon icon_print') and (contains(@class,'outline'))]");
			break;
		case "Download":
			locator = By.xpath("//span[contains(@class,'th_flat_icon icon_download') and (contains(@class,'outline'))]");
			break;
		case "Save to Folder":
			locator = By.xpath("//span[contains(@class,'th_flat_icon icon_folder') and (contains(@class,'outline'))]");
			break;
		default:
			throw new RuntimeException(String.format("Unknown option '%s'", option));
		}
		return isElementDisplayed(locator);
	}
	
}
