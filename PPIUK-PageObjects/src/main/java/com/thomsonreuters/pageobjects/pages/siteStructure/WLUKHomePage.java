package com.thomsonreuters.pageobjects.pages.siteStructure;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Pavel_Ardenka on 02/12/2016.
 * The page object class for WLUK compartment home page
 * https://law.demo.thomsonreuters.co.uk/Browse/Home/WestlawUk?transitionType=Default&contextData=(sc.Default)&comp=wluk
 */
public class WLUKHomePage extends AbstractPage {

    public enum Tab {
        BROWSE_BY_CONTENT("Browse by content type"),
        BROWSE_BY_TOPIC("Browse by topic"),
        SEARCH_AND_BROWSE("Search and browse"),
        SITE_STRUCTURE("Site structure");

        private String name;

        Tab(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public WebElement getTab(Tab tab) {
        return waitForExpectedElement(By.xpath("//ul[contains(@class, 'categoryTabs')]//a[contains(., '" + tab.getName() + "')]"));
    }

    public WebElement getSearchAndBrowseTopicLevel1() {
        return waitForExpectedElement(By.xpath("//a[@id='coid_westlawuktopic1'][contains(text(),'WestlawUktopic1')]"));
    }

}
