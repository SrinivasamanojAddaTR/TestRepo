package com.thomsonreuters.pageobjects.other_pages;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PLCLegacyBooksPage extends AbstractPage{

    public WebElement getPageHead() {
        return waitForElementVisible(By.tagName("h1"));
    }

}
