package com.thomsonreuters.pageobjects.pages.plcLegacy;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Pavel_Ardenka on 24/11/2015.
 * Describes http://global.practicallaw.com/resources/uk-publications/books page and pageobjects attributes for child pages
 */

public class PLCLegacyBooksPage extends AbstractPage{

    public WebElement getPageHead() {
        return waitForElementVisible(By.tagName("h1"));
    }

}
