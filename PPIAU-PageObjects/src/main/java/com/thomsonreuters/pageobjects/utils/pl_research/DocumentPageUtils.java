package com.thomsonreuters.pageobjects.utils.pl_research;

import java.util.List;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_research_docdisplay.document.JournalDocumentPage;
import com.thomsonreuters.pageobjects.utils.document.XMLDocumentUtils;
import com.thomsonreuters.pageobjects.utils.novus.NovusTools;

public class DocumentPageUtils {

	private AssetDocumentPage assetDocumentPage = new AssetDocumentPage();
	private JournalDocumentPage journalDocumentPage = new JournalDocumentPage();
	private XMLDocumentUtils xmlDocumentUtils = new XMLDocumentUtils();
	private CommonMethods commonMethods = new CommonMethods();
	private static final String XML_TAG = "abstract";

	public boolean isMetadataPresent() {
		try {
			return assetDocumentPage.caseMetadata().isDisplayed();
		} catch (PageOperationException e) {
			return false;
		}
	}

	public boolean isSubsectionDisplayed(String subsection) {
		try {
			return journalDocumentPage.documentSubsection(subsection).isDisplayed();
		} catch (PageOperationException e) {
			return false;
		}
	}

	public boolean isXmlOfTheDocumentContainsTheCaseDigestSection() {
		List<String> tagValues = xmlDocumentUtils.getValuesOfTagFromXMLOfTheDocument(XML_TAG, commonMethods.getDocumentGUIDFromCurrentURL(),
				NovusTools.VELMA);
		return !tagValues.isEmpty();
	}

}
