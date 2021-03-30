package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.enums;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

/**
 * This enum holds CssLocators and names for document navigation primary and secondary links
 * <p/>
 * Created by UC186961 on 06/01/2015.
 */
public enum DocumentPrimaryLink {

    /**
     * Provision Document Primary Links
     */
    PROVISION("PROVISION", By.cssSelector("")),
    PROVISION_DETAILS("PROVISION DETAILS", By.cssSelector("")),
    PRIMARY_REFERENCES("PRIMARY REFERENCES", By.cssSelector("")),
    COMMENTARY("COMMENTARY", By.cssSelector("")),

    /**
     * Case Document Primary Links
     */
    CASE_ANALYSIS("CASE ANALYSIS", By.cssSelector("")),
    JUDGMENT("JUDGMENT", By.cssSelector("")),
    LAW_REPORTS("LAW REPORTS", By.cssSelector("")),
    CASES_PRIMARY_REFERENCES("PRIMARY REFERENCES", By.cssSelector("")),
    CASES_COMMENTARY("COMMENTARY", By.cssSelector("")),

    /**
     * Arrangement of Act
     */
    ARRANGEMENT_OF_ACT("Arrangement of Act", By.cssSelector("")),
    ACT_DETAILS("Act Details", By.cssSelector(""));

    private final String linkName;
    private final By cssLocator;

    private final static Map<String, DocumentPrimaryLink> map = new HashMap<String, DocumentPrimaryLink>();

    static {
        for (DocumentPrimaryLink documentLink : DocumentPrimaryLink.values()) {
            map.put(documentLink.linkName, documentLink);
        }
    }

    public String getLinkName() {
        return this.linkName;
    }

    public By getCssLocator() {
        return this.cssLocator;
    }

    private DocumentPrimaryLink(String linkName, By cssLocator) {
        this.linkName = linkName;
        this.cssLocator = cssLocator;
    }

    public static DocumentPrimaryLink getLink(String linkName) {
        return map.get(linkName);
    }

}