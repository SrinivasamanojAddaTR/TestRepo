package com.thomsonreuters.pageobjects.ask_re_write;


import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class AskRewriteDashboardPage extends AbstractPage {
    private static final By DASHBOARD_TABLE_ROW = By.cssSelector(".ui-grid-canvas .ui-grid-row");
    private static final String DASHBOARD_TAB = "//li[contains(@class, 'nav-item')]//*[contains(text(), '%s')]";

    public WebElement askDashboardTabs(String tabName) {
        return waitForExpectedElement(By.xpath(String.format(DASHBOARD_TAB, tabName)));
    }

    public List<WebElement> tableOfRowsOnDashboard() {
        waitForPageToLoadAndJQueryProcessing();
        return waitForExpectedElements(DASHBOARD_TABLE_ROW);
    }

    public WebElement getTableRowOnDashboardByText(String searchText) {
        WebElement tableRowOnDashboard = null;
        for (WebElement oneRow :  tableOfRowsOnDashboard()) {
            if (oneRow.getText().contains(searchText)) {
                tableRowOnDashboard = oneRow;
                break;
            }
        }
        return tableRowOnDashboard;
    }
}