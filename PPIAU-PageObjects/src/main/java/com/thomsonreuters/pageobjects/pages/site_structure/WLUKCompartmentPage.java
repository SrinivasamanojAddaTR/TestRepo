package com.thomsonreuters.pageobjects.pages.site_structure;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WLUKCompartmentPage extends AbstractPage {

    public WebElement getDocOnTopicPageByGuid(String guid) {
        return waitForExpectedElement(By.xpath("//div[contains(@class,'co_artifactContent')]//a[contains(@href,'" + guid + "')]"));
    }

    public List<WebElement> getTheListOfTheDocsOnTopicPage() {
        return waitForExpectedElements(By.xpath("//div[contains(@class,'co_artifactContent')]//a"));
    }
}
