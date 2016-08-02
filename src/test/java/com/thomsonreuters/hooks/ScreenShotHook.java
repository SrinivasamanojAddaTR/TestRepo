package com.thomsonreuters.hooks;


import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseScreenShotHook;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class ScreenShotHook extends BaseScreenShotHook {

	/**
	 * Takes screen-shot if the scenario fails
	 *
	 * @param scenario
	 */
	@After(order = 99999)
	public void afterTest(Scenario scenario) throws InterruptedException {
		super.afterTest(scenario);
	}

}