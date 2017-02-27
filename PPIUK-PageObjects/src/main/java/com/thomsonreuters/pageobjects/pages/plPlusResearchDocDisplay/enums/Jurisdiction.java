package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.enums;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public enum Jurisdiction {

    /**
     * Jurisdiction Links
     */
    ENGLAND_SCOTLAND_AND_WALES("England, Scotland and Wales", By.cssSelector(""), By.cssSelector(""), By.cssSelector("")),
    NORTHERN_IRELAND("Northern Ireland", By.cssSelector(""), By.cssSelector(""), By.cssSelector("")),
    WALES("Wales", By.cssSelector(""), By.cssSelector(""), By.cssSelector("")),
    SCOTLAND("Scotland", By.cssSelector(""), By.cssSelector(""), By.cssSelector(""));

    private final static Map<String, Jurisdiction> map = new HashMap<String, Jurisdiction>();

    static {
        for (Jurisdiction jurisdictionLink : Jurisdiction.values()) {
            map.put(jurisdictionLink.jurisdictionLink, jurisdictionLink);
        }
    }

    private final String jurisdictionLink;
    private final By jurisdictionNavigationLinkLocator;
    private final By jurisdictionBlockLocator;
    private final By jurisdictionDocumentLinkLocator;

    public String getJurisdictionLinkName() {
        return this.jurisdictionLink;
    }

    public By getJurisdictionNavigationLinkLocator() {
        return this.jurisdictionNavigationLinkLocator;
    }

    public By getJurisdictionBlockLocator() {
        return this.jurisdictionBlockLocator;
    }

    public By getJurisdictionDocumentLinkLocator() {
        return this.jurisdictionDocumentLinkLocator;
    }

    private Jurisdiction(String jurisdictionLink, By jurisdictionNavigationLinkLocator, By jurisdictionBlockLocator, By jurisdictionDocumentLinkLocator) {
        this.jurisdictionLink = jurisdictionLink;
        this.jurisdictionNavigationLinkLocator = jurisdictionNavigationLinkLocator;
        this.jurisdictionBlockLocator = jurisdictionBlockLocator;
        this.jurisdictionDocumentLinkLocator = jurisdictionDocumentLinkLocator;
    }

    public static Jurisdiction get(String jurisdictionLink) {
        return map.get(jurisdictionLink);
    }

}
