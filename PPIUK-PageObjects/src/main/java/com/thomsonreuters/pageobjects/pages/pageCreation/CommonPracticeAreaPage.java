package com.thomsonreuters.pageobjects.pages.pageCreation;

import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.DocumentDisplayAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommonPracticeAreaPage extends DocumentDisplayAbstractPage {
    public static final By TABLE_OF_CONTENTS_SECTION = By.className("kh_toc-content");

    public WebElement addTpFavoritesButton() {
        return waitForExpectedElement(By.id("coid_website_browsePageAddToFavorites"));
    }

    public WebElement backToHomepageButton() {
        return findElement(By.id("coid_website_browsePageSelectAsHomepage"));
    }

    public WebElement askTab() {
        return waitForExpectedElement(By.linkText("Ask"));
    }

    public WebElement tocMenuTogglCollapsedButton() {
        return waitForElementPresent(By.xpath("//div[@id='kh_tocContainer']//a[@class='menu-toggle']"));
    }

    public WebElement contentMetaInfo() {
        return waitForExpectedElement(By.xpath("//*[@id='co_docContentMetaInfo']/span"));
    }

    public WebElement tableOfContentSection() {
        return waitForExpectedElement(TABLE_OF_CONTENTS_SECTION);
    }
}