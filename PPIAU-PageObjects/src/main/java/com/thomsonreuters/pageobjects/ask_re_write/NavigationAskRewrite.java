package com.thomsonreuters.pageobjects.ask_re_write;


import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class NavigationAskRewrite extends AbstractPage {
    private static final By SHOW_SIDE_MENU_BUTTON = By.className("bento-icon-hamburger-menu");

    private String getAskNewSystemUrl() {
        String url = "http://ask-rewrite-%s.emea1.cis.trcloud/ask/#/";
        switch (System.getProperty("base.legacy.url")) {
            case "102":
            case "CI":
                url = String.format(url, "dev");
                break;
            case "100":
            case "DEMO":
            default:
                url = String.format(url, "qa");
                break;
        }
        return url;
    }

    public void navigateToNewAsk() {
        navigate(getAskNewSystemUrl());
    }

    public void navigateToQueryViewByPlcRef(String plcRef) {
        navigate(getAskNewSystemUrl() + "query-view/" + plcRef);
    }

    public WebElement showSideMenuButton(){
        return waitForExpectedElement(SHOW_SIDE_MENU_BUTTON);
    }

    public WebElement sideMenuLinks(String menuItemText){
        return waitForExpectedElement(By.linkText(menuItemText));
    }
}