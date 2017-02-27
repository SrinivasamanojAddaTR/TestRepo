package com.thomsonreuters.pageobjects.pages.landingPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResourcesPage extends AbstractPage {

    public ResourcesPage() {
    }

    public WebElement plcMagazineLink() {
        return waitForExpectedElement(By.linkText("PLC Magazine"));
    }

    public WebElement glossaryLink() {
        return waitForExpectedElement(By.linkText("Glossary"));
    }

    public WebElement whatsMarketLink(String title) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_website_browseMainColumn']//div[@class='co_scrollWrapper co_categoryBoxTabContents']//a[contains(text(),\"" + title + "\")]"));
    }
    public boolean isWhatsMarketLinkPresent(String title) {
        return isExists(By.xpath("//div[@id='coid_website_browseMainColumn']//div[@class='co_scrollWrapper co_categoryBoxTabContents']//a[contains(text(),\"" + title + "\")]"));
    }
    public WebElement emailArchiveLink() {
        return waitForExpectedElement(By.linkText("Email archive"));
    }

    public WebElement resourceLinks(String text) {
		return waitForExpectedElement(By.xpath("//*[contains(@id,'coid_website_widget_managedContentContainer')]//a[contains(text(),'"
				+ text + "')]"));
    }

    public WebElement legalUpdatesLink() {
        return waitForExpectedElement(By.xpath("//*[@id='coid_legal_updates']"));
    }

}
