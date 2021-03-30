package com.thomsonreuters.pageobjects.pages.contactexpress;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContractExpressHeader extends AbstractPage {

    public boolean iscontractExpressTitlePresent() {
        return isElementDisplayed(By.xpath(".//*[@id='cecTopActionsTitle']/span[2]"));
    }

    public WebElement contractExpressTitle() {
        return findElement(By.xpath(".//*[@id='cecTopActionsTitle']//span[not(contains(@class,'icon'))]"));
    }

    public boolean ishelpiconPresent() {
        return isElementDisplayed(By.xpath("//a[@title='Help']/span[@class='icon-help']"));
    }

    public boolean isuserDropdownPresent() {
        return isElementDisplayed(By.xpath("//a[@class='nav-username']"));
    }

    public boolean isWelcomeCheckboxPresent() {
        return isElementDisplayed(By.xpath(".//*[@id='cecShowWelcome']"));
    }

    public WebElement userNoticeAndPrivacyStatement() {
        return waitForExpectedElement(By.linkText("User Notice and Privacy Statement"));
    }

    public WebElement cEHeaderTitle() {
        return waitForExpectedElement(By.xpath(".//*[@id='cecTopActionsTitle']/span[2]"));
    }

    public void waitUntilAddressIconDisplayed() {
        waitForPageToLoadAndJQueryProcessing();
        waitForElementsVisible(By.xpath("//*[@id='cecTopActionsTitle']/span[contains(@class, 'icon-address-book')]"));
    }

    public boolean isFilterSearchDisplayed() {
        return isElementDisplayed(By.xpath(".//*[@id='filter_search']"));

    }
}

