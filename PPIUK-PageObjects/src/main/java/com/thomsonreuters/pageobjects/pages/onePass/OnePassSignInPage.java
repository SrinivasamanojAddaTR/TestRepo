package com.thomsonreuters.pageobjects.pages.onePass;


import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by Stephanie Armytage on 23/10/2014. This is the One Pass Sign In page which provides options
 * to use different registration keys for the forthcoming session
 */

public class OnePassSignInPage extends AbstractPage {

    public OnePassSignInPage() {

    }

    public WebElement continueButton() {


        return waitForExpectedElement(By.id("SignIn"), 10);
    }


}
