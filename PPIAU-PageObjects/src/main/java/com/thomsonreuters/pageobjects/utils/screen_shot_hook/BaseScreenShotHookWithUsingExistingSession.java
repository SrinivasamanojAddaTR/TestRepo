package com.thomsonreuters.pageobjects.utils.screen_shot_hook;

import cucumber.api.Scenario;
import org.apache.commons.lang3.StringUtils;

public class BaseScreenShotHookWithUsingExistingSession extends BaseScreenShotHook {
    @Override
    protected void logCurrentSessionId(Scenario scenario) {
        if (StringUtils.isNotBlank(currentUser.getSessionId())) {
            String logText = "<br><b>Session ID:</b> " + currentUser.getSessionId() + "";
            LOG.info(logText);
            scenario.write(logText);
        } else {
            super.logCurrentSessionId(scenario);
        }
    }
}
