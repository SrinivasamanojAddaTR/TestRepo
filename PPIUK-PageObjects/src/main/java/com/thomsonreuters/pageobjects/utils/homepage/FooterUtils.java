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
}
