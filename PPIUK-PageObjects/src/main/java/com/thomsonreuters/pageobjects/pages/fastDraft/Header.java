package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//class represents the header of the Fast Draft main page http://d100-infra.dev.practicallaw.com:8080/da/
//used in Fast Draft project
//name- HeaderFastDraftDashboard
public class Header extends AbstractPage {

    private CommonMethods comMethods = new CommonMethods();

    public WebElement myProjects() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[contains(@href,'projects.zevon')]"), 10000);
    }

    public WebElement viewDraft() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[contains(@href,'saveAndDraft()')]"), 10000);
    }

    public WebElement viewQuestions() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[@href='qs/firstquestionpage']"), 10000);
    }

    public WebElement addressBook() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[@href='deals/mastercontacts']"), 10000);
    }

}
