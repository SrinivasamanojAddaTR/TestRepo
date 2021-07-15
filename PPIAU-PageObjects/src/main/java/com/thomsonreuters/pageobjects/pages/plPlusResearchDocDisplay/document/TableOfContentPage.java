package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import com.thomsonreuters.driver.framework.AbstractPage;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

public class TableOfContentPage extends AbstractPage {

	public static final String FIRST_LEVEL_TOC_LOCATOR = "//ol[contains(@class,'kh_toc-list')]/li";
	public static final String EXPAND_COLLAPSE_LOCATOR = FIRST_LEVEL_TOC_LOCATOR
			+ "/*[text()='%s']/following-sibling::input[contains(@class,'toggleArrow')]";

	public static final String CURRENT_SUBSECTION = "//span[@id='co_currentLhNSubsection']";
	public static final String CURRENT_SECTION_ID = "co_currentLhNSection";

	public WebElement tocCurrentSection() {
		return waitForExpectedElement(By.xpath("//span[@id='" + CURRENT_SECTION_ID + "']"));
	}

	public WebElement tocCurrentSubsection() {
		return waitForExpectedElement(By.xpath(CURRENT_SUBSECTION));
	}

	public WebElement tocSecondCurrentSubsection() {
		return waitForExpectedElements(By.xpath(CURRENT_SUBSECTION)).get(1);
	}

	public boolean isTocCurrentSubsectionPresent() {
		return isElementDisplayed(By.xpath(CURRENT_SUBSECTION));
	}

	public List<WebElement> tocSectionLinks() {
		return findElements(By.xpath(FIRST_LEVEL_TOC_LOCATOR + "/a"));
	}

	public WebElement tocSectionLink(String title) {
		return waitForExpectedElement(By.xpath(FIRST_LEVEL_TOC_LOCATOR + "/a[text()='" + title + "']"));
	}

	public List<WebElement> tocSections() {
		List<WebElement> allSectionsLi = findElements(By.xpath(FIRST_LEVEL_TOC_LOCATOR));
		List<WebElement> allSections = new ArrayList<>();
		for (WebElement sectionLi : allSectionsLi) {
			List<WebElement> elements = sectionLi.findElements(By.xpath("a"));
			if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
				allSections.add(elements.get(0));
			} else {
				allSections.add(sectionLi.findElement(By.id(CURRENT_SECTION_ID)));
			}
		}
		return allSections;
	}

	public List<WebElement> tocSubsectionLinks() {
		return findElements(By.xpath(FIRST_LEVEL_TOC_LOCATOR + "/ol[not(@style='display: none;')]/li/a[not(@style='display: none;')]"));
	}

	public WebElement tocSubsectionLink(String title) {
		return waitForExpectedElement(By.xpath(FIRST_LEVEL_TOC_LOCATOR + "/ol/li/a[text()='" + title + "']"));
	}

	public List<WebElement> tocAllLinks() {
		List<WebElement> allItems = tocSectionLinks();
		allItems.addAll(tocSubsectionLinks());
		return allItems;
	}

	public WebElement tocExpandCollapseIcon(String title) {
		return waitForExpectedElement(By.xpath(String.format(EXPAND_COLLAPSE_LOCATOR, title)));
	}

	public boolean isTocExpandCollapseIconPresent(String title) {
		return isExists(By.xpath(String.format(EXPAND_COLLAPSE_LOCATOR, title)));
	}

	public List<WebElement> tocLiTagsInFullTextSection() {
		return findElements(By.xpath(FIRST_LEVEL_TOC_LOCATOR + "/ol/li"));
	}

	/**
	 * Get table of contents visible links
	 *
	 * @return List with ToC links
	 */
	public List<WebElement> getVisibleTocLinks() {
		return waitForExpectedElements(By.xpath("//ol[contains(@class, 'toc-list')]//li/a[not(contains(@style, 'none')) and not(contains(@class, 'hide'))]"));
	}

	/**
	 * Get element with a document section level-independent link
	 *
	 * @param sectionName Section name
	 * @return Element with a given section name link
	 */
	public WebElement getAnyLevelSectionLink(String sectionName) {
		return waitForExpectedElement(
				By.xpath("//ol[contains(@class, 'toc-list')]//li/a[not(contains(@style, 'none')) and not(contains(@class, 'hide')) and contains(., " + Quotes.escape(sectionName) + ")]"));
	}

}
