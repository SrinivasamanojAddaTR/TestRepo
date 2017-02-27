package com.thomsonreuters.pageobjects.pages.researchBrowse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * All the pageobjects methods have been moved to RBCommonPage.java
 * Specific methods will be created in Iteration 2
 */

public class RBContentTypePAPage extends RBCommonPage {


    public WebElement topicLink(String link) {
        return waitAndFindElement(By.linkText(link));
    }


    public Map<String, List> listOfTopicsWithHeadings() {

        Map<String, List> actualTopicsMap = new LinkedHashMap<>();

        String xPath = "ul.plcukTopicHierarchyList >li";
        String xPath2 = "ul.plcukTopicHierarchyList li:nth-child(%d) div.subjectAreaHeader";
        String xPath3 = "ul.plcukTopicHierarchyList li:nth-child(%d) ul.subjectAreaList li a";

        //ul.plcukTopicHierarchyList li
        //ul.plcukTopicHierarchyList li div.subjectAreaHeader
        //ul.plcukTopicHierarchyList li:nth-child(1) div.subjectAreaHeader

        List<WebElement> topicGroupList = findElements(By.cssSelector(xPath));

        for (int i = 1; i <= topicGroupList.size(); i++) {
            String topicHeading = findElement(By.cssSelector(String.format(xPath2, i))).getText().trim();
            List<String> topicLinks = toText(findElements(By.cssSelector(String.format(xPath3, i))));
            LOG.info("Topic " + i + " = " + topicHeading + " and " + topicLinks);
            actualTopicsMap.put(topicHeading, topicLinks);

        }

        return actualTopicsMap;
    }

    private List<String> toText(List<WebElement> webElementList) {
        List<String> textList = new ArrayList<>();
        for (WebElement element : webElementList) {
            textList.add(element.getText().trim());
        }
        return textList;
    }

    public WebElement primaryTab() {
        return findElement(By.cssSelector("#coid_categoryTab1_main_2 a.co_tabLink"));
    }

    public List<WebElement> primaryFeaturedLegislationResources(){
        return findElements(By.cssSelector("#coid_categoryBoxTab1SubPanel1-2-main h3>a"));
    }

    public WebElement secondaryTab() {
        return findElement(By.cssSelector("#coid_categoryTab1_main_2 a.co_tabLink"));
    }

    public List<WebElement> secondaryFeaturedLegislationResources(){
        return findElements(By.cssSelector("#coid_categoryBoxTab1SubPanel1-2-main h3>a"));
    }

    public WebElement eUAndEFTATab() {
        return findElement(By.cssSelector("#coid_categoryTab1_main_2 a.co_tabLink"));
    }

    public WebElement codeOfPracticeTab() {
        return findElement(By.cssSelector("#coid_categoryTab1_main_2 a.co_tabLink"));
    }

}
