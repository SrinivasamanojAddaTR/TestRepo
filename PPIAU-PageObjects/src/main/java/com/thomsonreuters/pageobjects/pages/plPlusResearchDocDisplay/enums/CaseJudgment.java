package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.enums;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by UC186961 on 04/03/2015.
 */
public enum CaseJudgment {
    /**
     * Case Jusgment Details will display the below links as 1 or more
     | Citation Ref  |
     | Hearing dates |
     | Respondents   |
     */
    CITATION_REF("Citation Ref", By.cssSelector("")),
    HEARING_DATES("Hearing dates", By.cssSelector("")),
    RESPONDENTS("Respondents", By.cssSelector("")),

    /**
     *  Nore or more below section elements present on Case Judgment Docoment.
     | Appellate History  |
     | Counsel Appellants |
     */
    APPELLATE_HISTORY("Appellate History", By.cssSelector("")),
    COUNSEL_APPELLANTS("Counsel Appellants", By.cssSelector(""));
    private final static Map<String, By> map = new HashMap<String, By>();

    static {
        for (CaseJudgment caseJudgmentLink : CaseJudgment.values()) {
            map.put(caseJudgmentLink.judgmentSectionName, caseJudgmentLink.linkLocator);
        }
    }

    private final String judgmentSectionName;
    private final By linkLocator;

    public String getJudgmentSectionName() {
        return this.judgmentSectionName;
    }

    public By getLinkLocator() {
        return this.linkLocator;
    }

    private CaseJudgment(String judgmentSectionName, By linkLocator) {
        this.judgmentSectionName = judgmentSectionName;
        this.linkLocator = linkLocator;
    }

    public static By getLocator(String judgmentSectionName) {
        return map.get(judgmentSectionName);
    }
}
