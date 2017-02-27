package com.thomsonreuters.pageobjects.pages.ask;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class OrphanTestPage extends AbstractPage {

    public WebElement contentBlockNo(String blockId) {
        return waitForExpectedElement(By.id(blockId));
    }



}
