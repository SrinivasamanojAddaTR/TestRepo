package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewDocumentPage extends AbstractPage {

    private final static String DOCUMENT = "//*[@class='zevon-templates']//a[text()='%s']";

    private CommonMethods comMethods = new CommonMethods();

    public WebElement selectDocument(String document) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(DOCUMENT, document)), 10000);
    }

}
