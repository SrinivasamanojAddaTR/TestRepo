package com.thomsonreuters.pageobjects.pages.login;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * For the Page right after the user login
 */

public class WLNextHomePage extends AbstractPage {

    public WLNextHomePage() {
    }

    public WebElement signOffLink() {
        return waitForExpectedElement(By.id("co_signOffContainer"));
    }

    public By signOffByLink() {
        return By.id("coid_website_signOffRegion");
    }

    public WebElement footerWidget() {
        return waitForExpectedElement(By.id("co_footer"));
    }

    public WebElement headerWidget() {
        return waitForExpectedElement(By.id("co_header"));
    }

    public WebElement browseWidget() {
        return waitForExpectedElement(By.id("co_browseWidget"));
    }

    public WebElement viewPracticalLawLink() {
        return waitForExpectedElement(By.id("View Practical Law"));
    }

    public WebElement browsePageTitleHeading() {
        return waitForExpectedElement(By.id("co_browsePageLabel"));
    }

    public WebElement customerServiceHeading() {
        return waitForExpectedElement(By.xpath("//h2[@class='co_contactUs_subtitle' and text()='Customer Service ']"));
    }

    public WebElement copyrightsTitleHeading() {
        return waitForExpectedElement(By.xpath("//div[@id='co_subHeader']//h1"));
    }

}
