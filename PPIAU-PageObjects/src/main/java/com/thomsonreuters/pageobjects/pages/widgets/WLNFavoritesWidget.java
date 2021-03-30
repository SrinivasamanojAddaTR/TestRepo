package com.thomsonreuters.pageobjects.pages.widgets;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;



public class WLNFavoritesWidget extends AbstractPage {

    public void checkCategoryPageAbsent(String linkName, String groupName) {
        String locator = "//span[text()='" + groupName + "']/ancestor::li//a[contains(@title, '" + linkName + "')]";
        if (groupName.contains("Frequently")) {
            locator = "//*[@id='co_frequentlyUsed_listRoot']//a[contains(@title, '" + linkName + "')]";
        }
        try {
            findElement(By.xpath(locator));
        } catch (NoSuchElementException e) {
            LOG.warn("context", e);
            return;
        }
        throw new RuntimeException("The page '" + linkName + "' is present in '" + groupName + "' group");
    }

}