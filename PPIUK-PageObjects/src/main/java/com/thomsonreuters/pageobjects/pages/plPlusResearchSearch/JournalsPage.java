package com.thomsonreuters.pageobjects.pages.plPlusResearchSearch;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This page Object is created to navigate to Journals Search Page
 */
public class JournalsPage extends AbstractPage {

    public JournalsPage() {
    }

    public WebElement UKJournalsClick() {
        return waitForExpectedElement(By.cssSelector("a[href*='UKJOURNALS']"));
    }

    public WebElement DisplayJournalsResults() {
        return retryingFindElement(By.id("cobalt_search_ukJournal_results"));
    }

    public WebElement UKFacetExpand() {
        return waitForExpectedElement(By.cssSelector("a[id^='jurisdictionSummary']"));
    }

    public int getFacetCount(String jurisdiction) {
        try {
            return Integer.valueOf(waitFluentForElement(By.xpath(".//label[text()='" + jurisdiction + "']/../span[@class='co_facetCount']")).getText());
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the facet count for : " + jurisdiction);
        }
    }

    public boolean isJurisdictiondisplayed() {
    	return isElementDisplayed(By.cssSelector(".co_facet_tree>li>label"));
    }

    public List<String> getMainJurisdictionFacets() {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : retryingFindElements(By.cssSelector(".co_facet_tree>li>label[for^='facet_hierarchy_jurisdictionSummary']"))) {
                list.add(facet.getText());
            }
        } catch (PageOperationException te) {
            LOG.info("context", te);
            list = new ArrayList<String>();
        }
        return list;
    }

    public List<String> getUKJurisdictionFacets() {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement facet : retryingFindElements(By.xpath("//label[text()='UK']/..//ul[contains(@id,'jurisdiction')]//li//label"))) {
                list.add(facet.getText());
            }
        } catch (TimeoutException te) {
            LOG.info("context", te);
        }
        return list;
    }

    public int getFacetCount(String facetGroup, String... facetNames) {
        String temp = "";
        String tempStr = "/label[text()='";
        try {
            StringBuilder xpath = new StringBuilder();
            if (facetGroup.equals("Jurisdiction")) {
                xpath.append(".//div[@id='facet_div_jurisdictionSummary']/ul/li");
            } else if (facetGroup.equals("Topic")) {
                xpath.append(".//div[@id='facet_div_topicSummary']/ul/li");
            } else if (facetGroup.equals("Document Type")) {
                xpath.append(".//div[@id='facet_div_journalsDocumentTypeSummary']/ul/li");
            } else if (facetGroup.equals("Availability")) {
                xpath.append(".//div[@id='facet_div_journalsAvailabilitySummary']/ul/li");
            }
            temp = xpath.toString();

            for (int i = 0; i < facetNames.length - 1; i++) {
                WebElement checkbox = retryingFindElement(By.xpath(xpath + tempStr + facetNames[i] + "']/../a"));
                if (checkbox.getAttribute("class").equals("co_facet_expand")) {
                    checkbox.click();
                }
                xpath.append("/div/ul/li");
            }
            xpath.append("/label[text()='%s']/../span[@class='co_facetCount']");
            int size = Integer.valueOf(retryingFindElement(By.xpath(String.format(xpath.toString(), facetNames[facetNames.length - 1]))).getText());

            for (int i = facetNames.length - 2; i >= 0; i--) {
                String extraURL = "";
                int j = i;
                while (j > 0) {
                    extraURL += "/div/ul/li";
                    j--;
                }
                if (extraURL.length() > 0) {
                    retryingFindElement(By.xpath(temp + extraURL + "/label[text()='" + facetNames[i] + "']/../a")).click();
                } else {
                    retryingFindElement(By.xpath(temp + "/label[text()='" + facetNames[i] + "']/../a")).click();
                }
            }
            return size;
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the facet count for : ");
        }
    }

    public WebElement checkBoxByLabelName(String label) {
        WebElement findlabel = retryingFindElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[text()='" + label + "']"));
        String labelFor = findlabel.getAttribute("for");
        return waitForExpectedElement(By.id(labelFor));
    }

    public WebElement childCheckBoxByLabelName(String label) {
        String path = "div[id^='facet_hierarchy_childrenjurisdictionSummary'] ul li";
        List<WebElement> webElements = findElements(By.cssSelector(path));
        for (WebElement webElement : webElements) {
            WebElement labelElement = webElement.findElement(By.cssSelector(path + ">label"));
            WebElement checkBox = webElement.findElement(By.cssSelector(path + ">input"));
            if (labelElement.getText().trim().equalsIgnoreCase(label)) {
                return checkBox;
            }
        }
        return null;
    }

    public void selectCheckBoxByLabelName(String facetGroup, String... facetNames) {
        getCheckBox(facetGroup, facetNames).click();
    }

    public boolean isCheckBoxSelected(String facetGroup, String... facetNames) {
        try {
            waitForElementPresent(By.id("co_facetHeaderjurisdictionSummary")).click();
            return getCheckBox(facetGroup, facetNames).isSelected();
        } catch (Exception e) {
            LOG.info("context", e);
            return false;
        }
    }

    private WebElement getCheckBox(String facetGroup, String[] facetNames) {
        String tempStr = "/label[text()='";
        try {
            StringBuilder xpath = new StringBuilder();
            if (facetGroup.equals("Jurisdiction")) {
                xpath.append(".//div[@id='facet_div_jurisdictionSummary']/ul/li");
            } else if (facetGroup.equals("Topic")) {
                xpath.append(".//div[@id='facet_div_topicSummary']/ul/li");
            } else if (facetGroup.equals("Document Type")) {
                xpath.append(".//div[@id='facet_div_journalsDocumentTypeSummary']/ul/li");
            } else if (facetGroup.equals("Availability")) {
                xpath.append(".//div[@id='facet_div_journalsAvailabilitySummary']/ul/li");
            }

            for (int i = 0; i < facetNames.length - 1; i++) {
                WebElement checkbox = retryingFindElement(By.xpath(xpath + tempStr + facetNames[i] + "']/../a"));
                if (checkbox.getAttribute("class").equals("co_facet_expand")) {
                    checkbox.click();
                }
                xpath.append("/div/ul/li");
            }
            xpath.append("/label[text()='%s']/../input");
            return retryingFindElement(By.xpath(String.format(xpath.toString(), facetNames[facetNames.length - 1])));
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the facet count for : ");
        }
    }

    public int getJournalsCount() {
        int Size = Integer.valueOf(retryingFindElement(By.cssSelector(".co_search_titleCount")).getText().replace("(", "").replace(")", "").replace(",", "").trim());
        return Size;
    }

    /**
     * Object representing any know how facet checkbox as identified by facet name
     */
    public WebElement journalsFacetCheckbox(String facetName) {
        try {
            WebElement findlabel = retryingFindElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[text()='" + facetName + "']"));
            String labelFor = findlabel.getAttribute("for");
            return waitForExpectedElement(By.id(labelFor));
        } catch (StaleElementReferenceException se) {
            LOG.info("context", se);
            return journalsFacetCheckbox(facetName);
        }
    }

    /**
     * expand a facet
     */
    public WebElement expandFacet(String facetname) {
        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetname + "')]/../a[@class='co_facet_expand']"));
    }

    /**
     * collapse a facet
     */
    public WebElement collapseFacet(String facetname) {
        return waitForExpectedElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetname + "')]/../a[@class='co_facet_collapse']"));
    }

    /**
     * this is the facet name - pass in the facet name as a string e.g. Standard clauses
     */
    public WebElement facetName(String Name) throws Exception {
        return retryingFindElement(By.xpath("//div[contains(@id,'narrowResultsBy')]//label[contains(text(),'" + Name + "')]"));
    }

    /**
     * This is an object representing the Apply Filters button
     */
    public WebElement applyFiltersButton() {
        return retryingFindElement(By.linkText("Apply Filters"));
    }

    /**
     * This method does the waiting until results to be loaded on Search results page.
     */
    public void waitForSearchResults() {
        try {
            try {
                waitForElementVisible(By.cssSelector(".co_search_ajaxLoading"));
                try {
                    waitForElementInvisible(By.cssSelector(".co_search_ajaxLoading"));
                } catch (TimeoutException te) {
                    LOG.info("context", te);
                    throw new PageOperationException("Page loading issue...." + te.getMessage());
                }
            } catch (TimeoutException | NoSuchElementException te) {
                LOG.info("context", te);
            }
            waitForExpectedElements(By.cssSelector("#co_fermiSearchResult_data div"));
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to locate the results on search results page" + te.getMessage());
        }
    }

    /**
     * This is an object representing the facet count associated with each facet (any facet on the journals page)
     */
    public WebElement facetCount(String facetname) {
        return retryingFindElement(By.xpath("//div[@id='co_narrowResultsBy']//label[contains(text(),'" + facetname + "')]/../span[@class='co_facetCount']"));
    }

}