package com.thomsonreuters.pageobjects.utils.sitestructure;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.login.WelcomePage;
import org.apache.commons.lang.math.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

public class SiteStructureUtils {

    private WLNHeader wlnHeader = new WLNHeader();
    private PageActions pageActions = new PageActions();
    private WelcomePage welcomePage = new WelcomePage();
    private WebDriverDiscovery webDriverDiscovery = new WebDriverDiscovery();
    private CommonMethods commonMethods = new CommonMethods();
    private static final String WLUK_COMPARTMENT_NAME = "Westlaw UK";
    private static final String DEFAULT_CLIENT_ID = System.getProperty("clientId", "PRACTICAL LAW");

    private static final Logger LOG = LoggerFactory.getLogger(SiteStructureUtils.class);

    public void openCompartmentInNewWindow(WebElement compartment) {
        pageActions.openInNewWindow(compartment);
        wlnHeader.switchToOpenedWindow();
    }

    public void switchToMainWindow() {
        wlnHeader.switchToMainWindow();
    }

    public WebElement compartmentByName(String prodName) {
        wlnHeader.compartmentToggleDropDownArrow().click();
        return wlnHeader.compartmentToggleDropDownLink(prodName);
    }

    public void selectWlUkCompartment() {
        compartmentByName(WLUK_COMPARTMENT_NAME).click();
        wlnHeader.waitForPageToLoad();
        wlnHeader.waitForPageToLoadAndJQueryProcessing();
        confirmClientID();
        wlnHeader.waitForPageToLoadAndJQueryProcessing();
    }

    public void confirmClientID() {
        confirmClientID(DEFAULT_CLIENT_ID);
    }

    public void confirmClientID(String clientId) {
        waitForClientIdPageOrHomePageDisplayed();
        if (welcomePage.isContinueButtonPresent()) {
            LOG.info("CLIENT ID SCREEN: The WestLaw Confirm Client ID screen is displayed - clicking 'Continue'");
            if (!clientId.equals("NOT_SET_CLIENT_ID")) {
                welcomePage.clientID().clear();
                welcomePage.clientID().sendKeys(!clientId.trim().isEmpty() ? clientId : DEFAULT_CLIENT_ID);
            }
            clickAndWaitConfirmation();
            welcomePage.waitForPageToLoad();
        }
    }

    public void clickAndWaitConfirmation() {
        Function<WebDriver, Boolean> waitCondition = driver -> {
            welcomePage.continueButton().click();
            return welcomePage.isContinueButtonLoading();
        };
        AbstractPage.waitFor(waitCondition, webDriverDiscovery.getWebDriver());
    }

    public String getNodeTextWithoutChild(WebElement element) {
        String text = element.getText();
        for (WebElement child : element.findElements(By.xpath("./*"))) {
            text = text.replaceFirst(child.getText(), "");
        }
        return text.replaceAll("\\r|\\n", "");
    }

    public void waitForClientIdPageOrHomePageDisplayed() {
        Function<WebDriver, Boolean> waitCondition = driver -> welcomePage.isContinueButtonPresent() || wlnHeader.isSignInLinkPresentWithoutWait() || wlnHeader.isHistoryLinkPresent();
        AbstractPage.waitFor(waitCondition, webDriverDiscovery.getWebDriver());
    }

    public String getDocumentGUIDFromURL(String baseUrl) {
        List<String> url = commonMethods.getRegExpGroupValue("(?<=Document\\/)[\\w\\d]*", baseUrl);
        LOG.info("Document guid: {}", url.get(0));
        return url.get(0);
    }

    public void clickRandomLinkFromTheList(List<WebElement> links) {
        int index = RandomUtils.nextInt(links.size());
        wlnHeader.scrollIntoViewAndClick(links.get(index));
        wlnHeader.waitForPageToLoad();
    }
}
