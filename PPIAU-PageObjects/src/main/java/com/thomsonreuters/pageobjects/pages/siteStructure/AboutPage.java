package com.thomsonreuters.pageobjects.pages.siteStructure;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;

public class AboutPage extends AbstractPage {

    public boolean isTitlePresent(String name) {
        return isExists(By.xpath("//*[@id='co_browsePageLabel' and contains(., \"" + name + "\")]"));
    }
}
