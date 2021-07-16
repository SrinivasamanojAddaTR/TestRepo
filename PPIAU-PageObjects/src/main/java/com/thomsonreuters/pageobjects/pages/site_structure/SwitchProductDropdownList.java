package com.thomsonreuters.pageobjects.pages.site_structure;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SwitchProductDropdownList extends AbstractPage {
    public static final String productMenuPath = "//div[@id='navigation_left']//*[@class='co_dropDownMenuList']/li";

    /**
     * Getting all product names from the switch product dropdown list
     */
    public List<WebElement> getProductNamesList() {
        return waitForExpectedElements(By.xpath(productMenuPath + "/*"));
    }

    /**
     * Getting current product name as WebElement
     */
    public WebElement getCurrentProduct() {
        return waitForExpectedElement(By.xpath(productMenuPath + "/span"));
    }

    /**
     * Getting a list of products except current product
     */
    public List<WebElement> getListOfAvailableProducts() {
        return waitForExpectedElements(By.xpath(productMenuPath + "/a"));
    }
}
