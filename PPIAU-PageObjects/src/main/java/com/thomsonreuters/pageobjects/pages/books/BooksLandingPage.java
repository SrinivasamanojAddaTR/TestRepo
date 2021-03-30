package com.thomsonreuters.pageobjects.pages.books;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonStringMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by U6038385 on 11/01/2017.
 */
public class BooksLandingPage extends AbstractPage {

    private CommonStringMethods commonStringMethods = new CommonStringMethods();

    public WebElement bookLefthandTitle(String title) {
        return waitForExpectedElement(By.xpath("//div[@id='co_leftColumn']//preceding-sibling::div[@id='co_contentWrapper']//h1[text()='" + title + "']"));
    }

    public WebElement bookTitleLink(String linkTitle) {
        return waitForExpectedElement(By.xpath("//div[@class='co_searchContent']//a[contains(@id,'commentaryLibrary')][contains(text(),'" + linkTitle + "')]"));
    }

    public WebElement booksPageHeading(String heading) {
        return waitForExpectedElement(By.xpath("//div[@class='co_browseHeader']//h1[@id='co_browsePageLabel'][contains(.,'" + heading + "')]"));
    }

    public Integer booksPageHeadingCount() {
        String textCount;
        WebElement countElement;
        countElement = waitForExpectedElement(By.xpath("//div[@class='co_browseHeader']//h1/span[@class='co_search_titleCount']"),15);
        textCount = countElement.getText();
        // Strip the surrounding ( and )
        textCount = commonStringMethods.getBetweenStrings(textCount,"(",")",false);
        // Convert the value which is now a string, into an Integer
        return Integer.parseInt(textCount);
    }

    public WebElement booksLogo() {
        return waitForExpectedElement(By.xpath("//div[@class='header_content']//img[contains(@src,'books_logo.png')]"));
    }

    public WebElement publicationFilter(String heading) {
        return waitForExpectedElement(By.xpath("//div[@id='coid_website_searchResults']//h2[@id='co_libraryIsFilteredText'][text()='" + heading + "']"),15);
    }




}
