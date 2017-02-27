package com.thomsonreuters.pageobjects.pages.globalPage;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ChinaCategoryPage extends AbstractPage {

	public List<WebElement> linksUnderTheHeadersInTheResourcesTab(String header) {
		return retryingFindElements(By.xpath("//h3[contains(., '" + header + "')]/following-sibling::*//a"));
	}

    public List<WebElement> resourceTypesInTopicPage() {
        return findElements(By.cssSelector("#ukplc_topic_facet_links li a"));
    }

    public List<WebElement> jurisdictionsTopicPage() {
        return findElements(By.xpath(".//div[@id='facet_div_knowHowJurisdictionSummary']//label"));
    }

    public List<WebElement> getAllDatesFromResultListOfLegalUpdates() {
        return retryingFindElements(By.xpath(".//span[@class='co_greenStatus']"));
    }

    public List<WebElement> resourceHeadings() {
        return retryingFindElements(By.xpath("//div[@id='cobalt_search_knowHowTopicPlc_results']//h2"));
    }

    public List<WebElement> chinaTopicsInThePracticeArea(String section, String country) {
        return retryingFindElements(By.xpath(".//*[contains(text(), '" + section
                + "')]/following-sibling::div[contains(., '" + country + "')]/following-sibling::*//label"));
    }

    public List<WebElement> linksInSection(String section) {
        return retryingFindElements(By.xpath(".//*[contains(text(), '" + section + "')]/following-sibling::ul//a"));
    }

}
