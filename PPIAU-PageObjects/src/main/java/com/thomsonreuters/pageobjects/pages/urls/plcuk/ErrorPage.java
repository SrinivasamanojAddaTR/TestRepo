package com.thomsonreuters.pageobjects.pages.urls.plcuk;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class ErrorPage extends AbstractPage {
    private static final String ERROR_PAGE_XPATH = "//*[text()='Page not found']";

    public boolean isErrorPageBlockPresent() {
        waitForPageToLoadAndJQueryProcessing();
        return isElementDisplayed(By.xpath(ERROR_PAGE_XPATH));
    }

    public boolean isErrorPageBlockPresentAU() {
        waitForPageToLoadAndJQueryProcessing();
        return !findElements(By.id("co_404Box")).isEmpty();
    }

    public boolean isLogoOnTheErrorMessagePresent(String logo) {
        return isElementDisplayed(By.xpath("//*[@alt='"+logo+"']"));
    }

    public WebElement goToOutHomePageButton(){
    	return waitForExpectedElement(By.xpath("//*[contains(text(), 'Go to our homepage')]"));
    }

    public boolean isTrLogoDisplayed(){
        return isElementDisplayed(By.xpath("//*[@alt='TR Logo']"));
    }

    public WebElement returnToTheHomepageLink(){
        return waitForExpectedElement(By.xpath("//div[@class='pl-errorMessage']//a[contains(.,'return to the homepage')]"));
    }
}
