package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewContactPopup extends AbstractPage {

    private CommonMethods comMethods = new CommonMethods();

    public void selectPersonContactType() {
        selectDropDownByVisibleText(comMethods.waitForElementToBeVisible(By.xpath("//select[@class='qsListBox']")), "a person");
    }

    public WebElement salutationComboButton() {
        return comMethods.waitForElementToBeVisible(By.xpath("//span[contains(@id,'salutation_combobutton')]"));
    }

    public void selectSalutation(String title) {
        selectDropDownByVisibleText(waitForExpectedElement(By.xpath("//select[contains(@id,'salutation_comboList')]")), title);
    }

    public WebElement firstName() {
        return waitForExpectedElement(By.xpath("//input[contains(@name,'givenNames')]"));
    }

    public WebElement secondName() {
        return waitForExpectedElement(By.xpath("//input[contains(@name,'familyName')]"));
    }

    public WebElement email() {
        return waitForExpectedElement(By.xpath("(//input[contains(@name,'.address.email')])[1]"));
    }

    public WebElement save() {
        return waitForElementVisible(By.xpath("//button[text()='Save']"));
    }

}
