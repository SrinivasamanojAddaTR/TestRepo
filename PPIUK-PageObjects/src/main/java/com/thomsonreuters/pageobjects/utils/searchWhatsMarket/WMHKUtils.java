package com.thomsonreuters.pageobjects.utils.searchWhatsMarket;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.pages.widgets.RecentHistoryWidget;
import com.thomsonreuters.pageobjects.utils.document.XMLDocumentUtils;
import com.thomsonreuters.pageobjects.utils.novus.NovusTools;

public class WMHKUtils {

	private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
	private CategoryPage categoryPage = new CategoryPage();
	private XMLDocumentUtils xmlDocumentUtils = new XMLDocumentUtils();
	private RecentHistoryWidget recentHistoryWidget = new RecentHistoryWidget();

	private static final String DEAL_TYPE_TAG = "plcmd.deal.type";
	private static final String DEAL_VALUE_TAG = "plcmd.deal.value";
	private static final String SEARCH_DATE_TAG = "plcmd.search.display.date";
	private static final String ANNOUNCEMENT_DATE_TAG = "plcmd.announce.date";

	public void checkWMHKMetadataInFolders(String guid) throws Throwable {
		String dealType = getDealTypeName(guid);
		String dealValue = getDealValue(guid);
		String date = formatDateForFFH(getDealDate(guid, dealType));
		AbstractPage.getDriver.navigate().back();
		categoryPage.waitForPageToLoad();
		categoryPage.waitForPageToLoadAndJQueryProcessing();
		assertTrue(
				"WM HK Metadata is incorrect in FFH, expected date is '" + date + "', deal type '" + dealType
						+ "', deal value is '" + dealValue + "'",
				researchOrganizerPage.isWMMetadataPresent(guid, dealType, dealValue, date));
	}

	public void checkWMHKMetadataInRecentHistory(String guid) throws Throwable {
		String dealType = getDealTypeName(guid);
		String date = formatDateForFFH(getDealDate(guid, dealType));
		AbstractPage.getDriver.navigate().back();
		categoryPage.waitForPageToLoad();
		categoryPage.waitForPageToLoadAndJQueryProcessing();
		assertTrue("WM HK Metadata is incorrect in Recent History, expected date is '" + date + "', deal type '"
				+ dealType + "'", recentHistoryWidget.isWMMetadataPresent(guid, dealType, date));
	}

	private String getDealDate(String guid, String dealType) throws ParseException {
		String date = null;
		switch (dealType) {
		case "IPOs: Main Board":
			date = xmlDocumentUtils.getWebElementOfDocumentFromXMLByTag(SEARCH_DATE_TAG, guid, NovusTools.VELMA)
					.getText();
			break;
		case "Secondary offerings":
			date = xmlDocumentUtils.getWebElementOfDocumentFromXMLByTag(ANNOUNCEMENT_DATE_TAG, guid, NovusTools.VELMA)
					.getText();
			break;
		default:
			throw new UnsupportedOperationException("The deal type name '" + dealType
					+ "' is undefined. Only 'IPOs: Main Board' or 'Secondary offerings' were identified. Please add your page with expected result");
		}
		return date;
	}

	private String formatDateForFFH(String dateToFormat) throws ParseException {
		return DateFormatUtils.format(DateUtils.parseDate(dateToFormat, "yyyyMMdd"), "MMMM d, yyyy", Locale.UK);
	}

	private String getDealTypeName(String guid) {
		return xmlDocumentUtils.getWebElementOfDocumentFromXMLByTag(DEAL_TYPE_TAG, guid, NovusTools.VELMA).getText();
	}

	private String getDealValue(String guid) {
		return xmlDocumentUtils.getWebElementOfDocumentFromXMLByTag(DEAL_VALUE_TAG, guid, NovusTools.VELMA).getText();
	}

}
