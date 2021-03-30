package com.thomsonreuters.pageobjects.pages.login;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * For the Page right after the user login
 * Created by UC181137 on 04/08/2014.
 */

public class WLNHomePage extends AbstractPage {

    public WLNHomePage() {
    }

    //---------------------------Header Elements-----------------
    public WebElement signOffLink() {
        return waitForExpectedElement(By.id("co_signOffContainer"));
    }

    public By signOffByLink() {
        return By.id("co_signOffContainer");
    }

}
