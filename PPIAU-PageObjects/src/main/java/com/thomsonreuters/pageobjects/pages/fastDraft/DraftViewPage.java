package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DraftViewPage extends AbstractPage {

    private CommonMethods comMethods = new CommonMethods();

    public void checkFieldHasValue(String field, String value) {
        comMethods.waitForElementToBeVisible(By.xpath("//a[contains(.,(" + field + ")) and text()='" + value + "']"), 10000);
    }

    public WebElement wordDocument() {
        return waitForElementToBeClickable(waitForExpectedElement(By.xpath("//a[text()=' Word document']")));
    }

    public WebElement export() {
        return waitForExpectedElement(By.xpath("//*[text()=' Export ']"));
    }

    public WebElement exportEditablePDF() {
        return waitForElementToBeClickable(waitForExpectedElement(By.xpath("//a[text()='Export as editable form (PDF)']")));
    }

    public WebElement exportPrintablePDF() {
        return waitForElementToBeClickable(waitForExpectedElement(By.xpath("//a[text()='Export as printable form (PDF)']")));
    }

}
