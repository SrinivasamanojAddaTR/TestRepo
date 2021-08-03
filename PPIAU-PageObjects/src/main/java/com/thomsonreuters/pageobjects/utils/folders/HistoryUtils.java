package com.thomsonreuters.pageobjects.utils.folders;


import com.thomsonreuters.pageobjects.exceptions.PLAUException;
import com.thomsonreuters.pageobjects.pages.folders.HistoryTabs;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import org.openqa.selenium.WebElement;


public class HistoryUtils {

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
            throw new PLAUException("History tab '" + tab.getName() + "' absent!");
        }
    }
}
