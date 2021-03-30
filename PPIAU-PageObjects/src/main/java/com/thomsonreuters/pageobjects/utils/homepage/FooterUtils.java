package com.thomsonreuters.pageobjects.utils.homepage;

import com.thomsonreuters.pageobjects.pages.annotations.SharedAnnotationsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FooterUtils {

    Logger LOG = LoggerFactory.getLogger(FooterUtils.class);
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
              LOG.info("Cookies policy is Present, Closing the banner");
              sharedAnnotationsPage.closeCookiesPolicy().click();
          }
      }
      catch (Exception e) {
          LOG.error("Cookies Policy is not displayed");
      }
  }

    
 }
