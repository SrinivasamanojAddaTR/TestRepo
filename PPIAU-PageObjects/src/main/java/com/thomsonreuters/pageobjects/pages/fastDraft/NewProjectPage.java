package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewProjectPage extends AbstractPage {

    private final static String PROJECT_TYPE = "//*[@class='zevon-pots']//a[text()='%s']";

    private CommonMethods comMethods = new CommonMethods();

    public WebElement selectProjectType(String projectType) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(PROJECT_TYPE, projectType)), 10000);
    }

}
