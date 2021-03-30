package com.thomsonreuters.pageobjects.pages.urls.plcuk;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PLCUKPage extends AbstractPage {

    public List<WebElement> getAllLinks() {
        return findElements(By.xpath("//a[@href]"));
    }

    public Set<String> getAllLinksMatches(String regex) {
        HashSet<String> result = new HashSet<String>();
        for (WebElement webElement : findElements(By.xpath("//a[@href]"))) {
            if (webElement.getAttribute("href").matches(regex)) {
                result.add(webElement.getAttribute("href"));
            }
        }
        return result;
    }

    public List<WebElement> getAllLinksWhichContainStringInHref(String partLink) {
        return findElements(By.xpath("//a[contains(@href, '" + partLink + "')]"));
    }

}