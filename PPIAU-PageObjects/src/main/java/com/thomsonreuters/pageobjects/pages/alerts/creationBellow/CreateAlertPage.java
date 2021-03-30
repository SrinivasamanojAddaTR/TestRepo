package com.thomsonreuters.pageobjects.pages.alerts.creationBellow;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.StringUtils;

/**
 * This class describe elements and actions on 'Create Alert' page, that are not specific to particular bellow.
 * Created by Olga_Nadenenko on 12/29/2016.
 */
public class CreateAlertPage extends AbstractPage {
    private static final By CREATE_ALERT_BREADCRUMB = By.xpath("//div[@id='breadcrumb' and contains(.,'Create Alert')]");
    private static final By CREATE_ALERT_TITLE = By.xpath("//h1[contains(@class,'co_alertWizardTitle')]");

    public boolean isCreateAlertBreadcrumbDisplayed() {
        return isElementDisplayed(CREATE_ALERT_BREADCRUMB);
    }

    public WebElement alertCreationTitle() {
        return waitForExpectedElement(CREATE_ALERT_TITLE);
    }

    public boolean isOpenedBellowDisplayed(String bellowName) {
        return isElementDisplayed(By.xpath("//li[@id='" + StringUtils.uncapitalize(bellowName) + "Section' and not(contains(@class,'co_collapsed'))]"));
    }

    public boolean isCollapsedBellowDisplayed(String bellowName) {
        return isElementDisplayed(By.xpath("//li[@id='" + StringUtils.uncapitalize(bellowName) + "Section' and contains(@class,'co_collapsed')]"));
    }

    public boolean isBellowTitleDisplayed(String bellowName) {
        return isElementDisplayed(By.xpath("//h2[contains(.,'" + bellowName + "')]"));
    }

    public WebElement bellowContinueButton(String bellowName) {
        return waitForExpectedElement(By.id("co_button_continue_" + bellowName));
    }

    public boolean isBellowContinueButtonDisplayed(String bellowName) {
        return isElementDisplayed(By.id("co_button_continue_" + bellowName));
    }


}
