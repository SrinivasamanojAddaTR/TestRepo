package com.thomsonreuters.pageobjects.utils.homepage;

import com.thomsonreuters.pageobjects.pages.annotations.SharedAnnotationsPage;

public class FooterUtils {

    private SharedAnnotationsPage sharedAnnotationsPage = new SharedAnnotationsPage();

    public void closeDisclaimerMessage(){
        if (sharedAnnotationsPage.isDisclaimerPresent()) {
            sharedAnnotationsPage.getCloseDisclaimer().click();
            sharedAnnotationsPage.waitForDisclaimerAbsent();
        }
    }
    
  //cookies policy
  public void ourCookiesPolicy(){
      try{
          if(sharedAnnotationsPage.isCookiesPolicyPresent())
          {
              System.out.println("entered the loop");
              sharedAnnotationsPage.closeCookiesPolicy().sendKeys(Keys.ENTER);
          }
      }
      catch (Exception e) {

          System.out.println("Cookies Policy is not displayed");
      }
  }

    
 }
