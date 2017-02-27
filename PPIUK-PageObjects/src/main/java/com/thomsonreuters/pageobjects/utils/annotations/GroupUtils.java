package com.thomsonreuters.pageobjects.utils.annotations;

import com.thomsonreuters.pageobjects.pages.annotations.SharedAnnotationsPage;

/**
 * Created by Viacheslav_Didenko on 10/13/2016.
 */
public class GroupUtils {

    private SharedAnnotationsPage sharedAnnotationsPage = new SharedAnnotationsPage();

    public void addUsersToGroup() {
        sharedAnnotationsPage.getAddGroupOption().click();
        sharedAnnotationsPage.waitForPageToLoad();
        sharedAnnotationsPage.waitForPageToLoadAndJQueryProcessing();
        sharedAnnotationsPage.getParticipantFromUserList(1).click();
        sharedAnnotationsPage.getParticipantFromUserList(2).click();
    }
}
