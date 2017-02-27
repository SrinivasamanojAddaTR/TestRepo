package com.thomsonreuters.pageobjects.pages.urls.practcallaw;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.urls.DocumentPage;
import org.openqa.selenium.By;



public class KHDocumentPagePracticalLaw extends AbstractPage implements DocumentPage {

    public static final String BASE_DOMAIN = "http://uk.practicallaw.com";

    public String getDocumentTitle() {
        return findElement(By.xpath("//div[@id='resource']/h1")).getText().toLowerCase();
    }

    @Override
    public String getWebSiteName() {
        return "Old PracticalLaw Site";
    }
}
