package com.thomsonreuters.pageobjects.pages.widgets;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class BrowsePreviewPage extends AbstractPage {

    public WebElement previewXml() {
        return findElement(By.id("XML"));
    }

    public WebElement submit() {
        return findElement(By.id("browsePreviewForm_submitButton"));
    }

}
