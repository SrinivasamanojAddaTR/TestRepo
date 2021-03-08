package com.thomsonreuters.pageobjects.utils.screen_shot_hook;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.utils.CobaltUser;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;


public class BaseStepDef {

	protected static final Logger LOG = org.slf4j.LoggerFactory.getLogger(BaseStepDef.class);
	protected static CobaltUser currentUser = CobaltUser.firstUser();
	private HomePage homePage = new HomePage();

	protected static void resetCurrentUser() {
		currentUser = CobaltUser.firstUser();
	}

	protected WebDriver getDriver() {
		return homePage.getDriver;
	}

}