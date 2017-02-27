package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.enums;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

/**
 * This enum holds CssLocators and names for document navigation primary and secondary links
 * <p/>
 * Created by UC186961 on 06/01/2015.
 */
public enum DocumentSecondaryLink {

    /**
     * Provision Section Child menu links
     */
    ENGLAND_WALES_SCOTLAND("England, Wales & Scotland", By.cssSelector("")),
    NORTHERN_IRELAND("Northern Ireland", By.cssSelector("")),
    AMENDMENTS("Amendments", By.cssSelector("")),
    MODIFICATIONS("Modifications", By.cssSelector("")),
    ANNOTATIONS("Annotations", By.cssSelector("")),

    /**
     * Provision Details Section Child menu links
     */
    TABLE_OF_AMENDMENTS("Table of Amendments", By.cssSelector("")),
    COMMENCEMENT_EXTENT("Commencement & Extent", By.cssSelector("")),
    SIS_MADE_UNDER_ACT("SIs Made Under Act", By.cssSelector("")),

    // Primary References Child menu links
    KEY_CASES_CITING("Key cases citing", By.cssSelector("")),
    ALL_CASES_CITING("All cases citing", By.cssSelector("")),
    LEGISLATION_CITED("Legislation cited", By.cssSelector("")),

    /**
     * Commentary Child menu links
     */
    JOURNALS("JOURNALS", By.cssSelector("")),
    BOOKS("BOOKS", By.cssSelector("")),

    /**
     * Cases Case Anlysis Section Child menu links
     */
    CASE_DIGEST("Case Digest", By.cssSelector("")),
    APPELLATE_HISTORY("Appellate History", By.cssSelector("")),
    RELATED_CASES("Related Cases", By.cssSelector("")),

    /**
     * Cases Primary References Section Child menu links
     */
    CASES_KEY_CASE_CITED("Key cases Cited", By.cssSelector("")),
    CASES_ALL_CASES_CITED("All Cases Cited", By.cssSelector("")),
    CASES_KEY_CASES_CITING("Key Cases Citing", By.cssSelector("")),
    CASES_ALL_CASES_CITING("All Cases Citing", By.cssSelector("")),
    CASES_LEGISLATION_CITED("Legislation cited", By.cssSelector("")),

    /**
     * Cases Commentary Child menu links
     */
    CASES_JOURNALS("JOURNALS", By.cssSelector("")),
    CASES_BOOKS("BOOKS", By.cssSelector("")),
    CASES_INSIGHT("INSIGHT", By.cssSelector(""));

    private final String linkName;
    private final By cssLocator;

    private final static Map<String, DocumentSecondaryLink> map = new HashMap<String, DocumentSecondaryLink>();

    static {
        for (DocumentSecondaryLink documentLink : DocumentSecondaryLink.values()) {
            map.put(documentLink.linkName, documentLink);
        }
    }

    public String getLinkName() {
        return this.linkName;
    }

    public By getCssLocator() {
        return this.cssLocator;
    }

    private DocumentSecondaryLink(String linkName, By cssLocator) {
        this.linkName = linkName;
        this.cssLocator = cssLocator;
    }

    public static By getLink(String linkName) {
        return map.get(linkName).getCssLocator();
    }

}