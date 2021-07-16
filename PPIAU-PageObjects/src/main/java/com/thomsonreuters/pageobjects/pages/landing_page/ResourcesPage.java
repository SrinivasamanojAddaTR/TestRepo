package com.thomsonreuters.pageobjects.pages.landing_page;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;

public class ResourcesPage extends AbstractPage {

    private static final String WHATSMARKET_LINK = "//div[@id='coid_website_browseMainColumn']//div[@class='co_scrollWrapper co_categoryBoxTabContents']//a[contains(text(),\"%s\")]";
    private static final String RESOURCE_LINKS = "//*[contains(@id,'coid_website_widget_managedContentContainer')]//a[contains(text(),'%s')]";

    public WebElement plcMagazineLink() {
        return waitForExpectedElement(By.linkText("PLC Magazine"));
    }

    public WebElement glossaryLink() {
        return waitForExpectedElement(By.linkText("Glossary"));
    }

    public WebElement whatsMarketLink(String title) {
        return waitForExpectedElement(By.xpath(format(WHATSMARKET_LINK, title)));
    }
    public boolean isWhatsMarketLinkPresent(String title) {
        return isExists(By.xpath(format(WHATSMARKET_LINK, title)));
    }
    public WebElement emailArchiveLink() {
        return waitForExpectedElement(By.linkText("Email archive"));
    }

    public WebElement resourceLinks(String text) {
		return waitForExpectedElement(By.xpath(format(RESOURCE_LINKS,text)));
    }

    public WebElement legalUpdatesLink() {
        return waitForExpectedElement(By.xpath("//*[@id='coid_legal_updates']"));
    }

}
