package com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.LegislationDocumentPage;

public class LegislationDocumentPageUtils {

	private LegislationDocumentPage legislationDocumentPage = new LegislationDocumentPage();
	protected static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);


	public boolean isTheSectionPresent(String section) {
		try {
			return legislationDocumentPage.sectionInTheDocument(section).isDisplayed();
		} catch (PageOperationException poe) {
			LOG.info("context", poe);
			return false;
		}
	}
}
