package com.thomsonreuters.pageobjects.utils.screen_shot_hook;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.utils.CobaltUser;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.springframework.util.ReflectionUtils;


public class BaseStepDef {

	protected static final Logger LOG = org.slf4j.LoggerFactory.getLogger(BaseStepDef.class);
	protected final CobaltUser currentUser = CobaltUser.firstUser();
	private HomePage homePage = new HomePage();

	protected void resetCurrentUser() {
		this.currentUser.reset();
	}

	protected CobaltUser cloneCurrentUserObject() {
		CobaltUser user = new CobaltUser();
		ReflectionUtils.shallowCopyFieldState(this.currentUser, user);
		return user;
	}

	protected String getSessionIDFromUI() {
		String js = "var serverData=window['Server/PageData'];return serverData == undefined ? '' : serverData.KnowhowPreHeaderScripts==undefined ? '' : serverData.KnowhowPreHeaderScripts.SessionId";
		return (String)this.homePage.executeScript(js);
	}

	protected WebDriver getDriver() {
		return (new CommonMethods().getDriver());
	}

}