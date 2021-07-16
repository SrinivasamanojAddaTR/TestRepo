package com.thomsonreuters.pageobjects.pages.fast_draft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewDocumentPage extends AbstractPage {

    private static final String DOCUMENT = "//*[@class='zevon-templates']//a[text()='%s']";

    private CommonMethods comMethods = new CommonMethods();

    public WebElement selectDocument(String document) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(DOCUMENT, document)));
    }

}
