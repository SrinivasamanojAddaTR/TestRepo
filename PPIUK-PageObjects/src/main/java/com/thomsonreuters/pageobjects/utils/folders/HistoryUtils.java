package com.thomsonreuters.pageobjects.utils.folders;


import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thomsonreuters.pageobjects.pages.folders.HistoryTabs;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;


public class HistoryUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FoldersUtils.class);
    
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();

    public void openHistoryTab(HistoryTabs tab) {
        researchOrganizerPage.waitForPageToLoad();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(60);
        WebElement historyTabNonClicked = researchOrganizerPage.findElement(tab.getId());
        WebElement historyTabClicked = researchOrganizerPage.findElement(tab.getIdClickable());
        if (historyTabNonClicked.isDisplayed()) {
            historyTabNonClicked.click();
            researchOrganizerPage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(60);
        }
        if (historyTabClicked.isDisplayed()) {
            researchOrganizerPage.waitForPageToLoadAndJQueryProcessingWithCustomTimeOut(60);
            researchOrganizerPage.waitForElementPresent(tab.getPageHeader());
        } else {
            throw new RuntimeException("History tab '" + tab.getName() + "' absent!");
        }
    }
}
