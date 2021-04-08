package com.thomsonreuters.hooks;


import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseScreenShotHookWithUsingExistingSession;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class ScreenShotHook extends BaseScreenShotHookWithUsingExistingSession {

	/**
	 * Takes screen-shot if the scenario fails
	 *
	 * @param scenario
	 */
	@After(order = 99999)
	public void afterTest(Scenario scenario) {
		super.afterTest(scenario);
	}

}