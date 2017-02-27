package com.thomsonreuters.pageobjects.pages.researchBrowse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Still WIP. RB Wireframe 7
 * All the pageobjects methods have been moved to RBCommonPage.java
 * This is a Sample PLC Home Page for Research Browse Project. Only web elements require by Research browse project will be created here.
 * Note that this may not be representation of the Page that will be created in the production
 */

public class RBPLHomePage extends RBCommonPage {

    // resources tab and research content links
    public WebElement resourcesTab() {
       return waitAndFindElement(By.linkText("Resources"));
    }

}
