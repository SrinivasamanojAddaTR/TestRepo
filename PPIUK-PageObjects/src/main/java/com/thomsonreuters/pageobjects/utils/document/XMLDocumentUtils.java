package com.thomsonreuters.pageobjects.utils.document;

import java.util.ArrayList;
import java.util.List;

import com.thomsonreuters.pageobjects.utils.document.content.Section;
import com.thomsonreuters.pageobjects.utils.document.metadata.Jurisdiction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.globalPage.GlobalCategoryPage;
import com.thomsonreuters.pageobjects.utils.globalPage.GlobalPageUtils;
import com.thomsonreuters.pageobjects.utils.novus.NovusTools;

public class XMLDocumentUtils {

	private GlobalPageUtils globalPageUtils = new GlobalPageUtils();
	private GlobalCategoryPage globalCategoryPage = new GlobalCategoryPage();

	private static final String VELMA_DOCUMENT_XML_URL = "http://legaltechtools.int.thomsonreuters.com/Velma/Novus/Document?guid=";
	private static final String RAWXML_URL = "http://document.int.next.%s.westlaw.com/document/v1/rawxml/%s?websitehost=www.%s.lab.westlaw.com";

	public List<WebElement> getWebElementsOfDocumentFromXMLByTag(String tag, String guid, NovusTools tool) {
		openDocumentXML(guid, tool);
		return globalCategoryPage.getDriver.findElements(By.tagName(tag));
	}

	public WebElement getWebElementOfDocumentFromXMLByTag(String tag, String guid, NovusTools tool) {
		openDocumentXML(guid, tool);
		return globalCategoryPage.getDriver.findElement(By.tagName(tag));
	}
	
	public WebElement getWebElementOfDocumentFromXMLByXpath(String xpath, String guid, NovusTools tool) {
		openDocumentXML(guid, tool);
		return globalCategoryPage.getDriver.findElement(By.xpath(xpath));
	}

	private void openDocumentXML(String guid, NovusTools tool) {
		String environment = System.getProperty("base.url");
		String url = null;
		switch (tool) {
		case VELMA:
			url = VELMA_DOCUMENT_XML_URL + guid;
			break;
		case RAWXML:
			url = String.format(RAWXML_URL, environment, guid, environment);
			break;
		default:
			throw new UnsupportedOperationException("Please specify Novus XML tool");
		}
		globalCategoryPage.getDriver.get(url);
	}

	public List<String> getValuesOfTagFromXMLOfTheDocument(String tag, String guid, NovusTools tool) {
		globalCategoryPage.waitForPageToLoad();
		List<WebElement> topics = getWebElementsOfDocumentFromXMLByTag(tag, guid, tool);
		return globalPageUtils.getLinkNamesFromWebElementList(topics);
	}

	/**
	 * Get list with product names from the product list, which got from Legacy Fatwire
	 *
	 * @param products List with {@link }s
	 * @return List with product names
	 */
	public List<String> getProductNamesFromXml(List<com.thomsonreuters.pageobjects.utils.document.metadata.Product> products) {
		List<String> result = new ArrayList<>();
		for (com.thomsonreuters.pageobjects.utils.document.metadata.Product product : products) {
			result.add(product.getName());
		}
		return result;
	}

	/**
	 * Get list with section titles from the section list, which got from Legacy Fatwire
	 *
	 * @param sections List with {@link Section}s
	 * @return List with section titles
	 */
	public List<String> getSectionTitlesFromXml(List<Section> sections) {
		List<String> result = new ArrayList<>();
		for (Section section : sections) {
			result.add(section.getTitle());
		}
		return result;
	}

	/**
	 * Get list with jurisdiction names from the jurisdiction list, which got from Legacy Fatwire
	 *
	 * @param jurisdictions List with {@link Jurisdiction}s
	 * @return List with jurisdiction names
	 */
	public List<String> getJurisdictionNamesFromXml(List<Jurisdiction> jurisdictions) {
		List<String> result = new ArrayList<>();
		for (Jurisdiction jurisdiction : jurisdictions) {
			result.add(jurisdiction.getName());
		}
		return result;
	}
}
