package com.thomsonreuters.pageobjects.pages.researchBrowse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

/**
 * Still WIP. RB wireframe 14
 * All the pageobjects methods have been moved to RBCommonPage.java
 * This is a Sample Journals Page for Research Browse Project
 * Note that this may not be representation of the Page that will be created in the production
 */

public class RBContentTypePage extends RBCommonPage {

    // Recently Viewed
    public List<WebElement> recentlyViewedDocuments() {
        return findElements(By.cssSelector("div#co_rightColumn div#co_recentDocuments ul li.ng-scope"));
    }

}
