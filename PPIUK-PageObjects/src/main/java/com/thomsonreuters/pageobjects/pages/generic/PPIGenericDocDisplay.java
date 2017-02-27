package com.thomsonreuters.pageobjects.pages.generic;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

/**
 * Created by uc087619 on 24/11/2015.
 */

public class PPIGenericDocDisplay extends AbstractPage {

    public List<WebElement> ppiTermNavigationHitMarkupCheckTermsAsList() {
        return waitForExpectedElements((By.xpath("//span[contains(@class,'searchTerm')]")));
    }

    // This is the left to right tabbed menu with "Practice areas" "Resources" and "International"
    public WebElement categoryTab() {
        return waitForExpectedElement((By.xpath("//ul[@class='co_tabs co_categoryTabs']")),15);
    }

    public WebElement categoryTabSelection(String menuHeading) {
        return waitForExpectedElement((By.xpath("//div[@class='co_categoryBoxTabs']//a[@class='co_tabLink'][text()='" + menuHeading + "']")),15);
    }

    public WebElement categoryLinkAfterHeading(String linkHeading,
                                               String linkText) {
        return waitForExpectedElement((By.xpath("//div[@id='coid_website_browseMainColumn']//span[text()='" + linkHeading + "']//ancestor::div[@class='co_genericBox']//a[text()='" + linkText + "']")),15);
    }

    public WebElement categoryLink(String linkText) {
        return waitForExpectedElement((By.xpath("//div[@id='co_featureBoxInner']//a[text()='" + linkText + "']")),15);
    }

    public boolean isCategoryBoxTabContentsDisplayed() {
        return isElementDisplayed(By.xpath("//div[@class='co_scrollWrapper co_categoryBoxTabContents']"));
    }

    public WebElement searchPageLabel() {
        return waitForExpectedElement((By.xpath("//h1[@id='co_browsePageLabel']")),30);
    }

    public WebElement rightColumn() {
        return waitForExpectedElement((By.xpath("//div[@id='co_rightColumn']")),20);
    }


    public WebElement titleNoToc()  {
        return waitForExpectedElement(By.xpath("//div[@id='co_docHeaderContainer']//h1[contains(@class,'noTOC')]"));
    }

    public WebElement pageTitle() {
        return waitForExpectedElement((By.id("co_browsePageLabel")), 30);
    }

    public WebElement glossaryHeader() {
        return waitForExpectedElement((By.xpath("//div[@id='co_docHeaderContainer']")),30);
    }

    public WebElement plGlobalHeader () {
        return waitForExpectedElement((By.xpath("//h1")),30);
    }

    public String mainDocumentFullText() {
        WebElement fullText = waitForExpectedElement(By.xpath("//*[@id='coid_website_documentWidgetDiv']"));
        return fullText.getText();
    }
}
