package com.thomsonreuters.pageobjects.pages.plPlusResearchSearch;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

/**
 * This page Object is created to navigate to Cases Search Page
 */

public class CasesPage extends AbstractPage {

    public CasesPage() {
    }

    public WebElement ukCasesClick() {
        return waitForExpectedElement(By.cssSelector("a[href*='UKCASES']"));
    }

    public WebElement displayCaseResults() {
        return retryingFindElement(By.id("cobalt_search_ukCase_results"));
    }

    public WebElement ukFacetExpand() {
        return waitForExpectedElement(By.cssSelector("a[id^='jurisdictionSummary']"));
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
            } else if (facetGroup.equals("Court")) {
                xpath.append(".//div[@id='facet_div_casesCourtSummary']/ul/li");
            } else if (facetGroup.equals("Status")) {
                xpath.append(".//div[@id='facet_div_casesStatusSummary']/ul/li");
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

    public boolean isJurisdictiondisplayed() {
    	return isElementDisplayed(By.cssSelector(".co_facet_tree>li>label"));
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
            } else if (facetGroup.equals("Court")) {
                xpath.append(".//div[@id='facet_div_casesCourtSummary']/ul/li");
            } else if (facetGroup.equals("Status")) {
                xpath.append(".//div[@id='facet_div_casesStatusSummary']/ul/li");
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

    /**
     * This is the generic method to verify the given facet is available under the given facet group and returns the boolean value accordingly.
     *
     * @param facetGroup
     * @param facetNames
     * @return boolean
     */
    public boolean isFacetDisplayed(String facetGroup, String[] facetNames) {
        String tempStr = "/label[text()='";
        try {
            StringBuilder xpath = new StringBuilder();
            if (facetGroup.equals("Jurisdiction")) {
                xpath.append(".//div[@id='facet_div_jurisdictionSummary']/ul/li");
            } else if (facetGroup.equals("Topic")) {
                xpath.append(".//div[@id='facet_div_topicSummary']/ul/li");
            } else if (facetGroup.equals("Court")) {
                xpath.append(".//div[@id='facet_div_casesCourtSummary']/ul/li");
            } else if (facetGroup.equals("Status")) {
                xpath.append(".//div[@id='facet_div_casesStatusSummary']/ul/li");
            }

            for (int i = 0; i < facetNames.length - 1; i++) {
                WebElement checkbox = retryingFindElement(By.xpath(xpath + tempStr + facetNames[i] + "']/../a"));
                if (checkbox.getAttribute("class").equals("co_facet_expand")) {
                    checkbox.click();
                }
                xpath.append("/div/ul/li");
            }
            xpath.append("/label[text()='%s']/../input");
            return retryingFindElement(By.xpath(String.format(xpath.toString(), facetNames[facetNames.length - 1]))).isDisplayed();
        } catch (TimeoutException te) {
            LOG.info("context", te);
            throw new PageOperationException("Exceeded time to find the facet");
        } catch (PageOperationException p) {
            LOG.info("context", p);
            return false;
        }
    }

    public int getCasesCount() {
        int Size = Integer.valueOf(retryingFindElement(By.cssSelector(".co_search_titleCount")).getText().replace("(", "").replace(")", "").trim());
        return Size;
    }

}