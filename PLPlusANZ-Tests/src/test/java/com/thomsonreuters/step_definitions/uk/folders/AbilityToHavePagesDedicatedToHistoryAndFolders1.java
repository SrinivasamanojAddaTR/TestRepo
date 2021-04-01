package com.thomsonreuters.step_definitions.uk.folders;

import com.thomsonreuters.pageobjects.pages.folders.HistoryTabs;
import com.thomsonreuters.pageobjects.utils.folders.HistoryUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.And;

public class AbilityToHavePagesDedicatedToHistoryAndFolders1 extends BaseStepDef {

    private HistoryUtils historyUtils = new HistoryUtils();

    @And("^All tabs present on History page$")
    public void historyTabsPresent() throws Throwable {
        for (HistoryTabs tabs : HistoryTabs.values()) {
            historyUtils.openHistoryTab(tabs);
        }
    }

}