package com.thomsonreuters.pageobjects.utils.searchWhatsMarket;

import com.thomsonreuters.pageobjects.pages.search.WhatsMarketComparisonReportPage;

public class SearchWMUtils {
	
    private WhatsMarketComparisonReportPage whatsMarketComparisonReportPage = new WhatsMarketComparisonReportPage();
	
    public void deleteReportProfile(String report) throws Throwable {
      whatsMarketComparisonReportPage.deleteReportProfile().click();
      whatsMarketComparisonReportPage.reportProfileOnDeleteProfilePopup(report).click();
      whatsMarketComparisonReportPage.deleteButtonOnDeleteProfilePopup().click();
      whatsMarketComparisonReportPage.waitForPageToLoadAndJQueryProcessing();
    }
    
}
