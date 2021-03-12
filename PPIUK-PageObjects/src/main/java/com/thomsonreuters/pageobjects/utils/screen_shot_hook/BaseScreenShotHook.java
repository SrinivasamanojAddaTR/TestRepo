package com.thomsonreuters.pageobjects.utils.screen_shot_hook;

import com.thomsonreuters.driver.framework.ScreenShots;
import com.thomsonreuters.pageobjects.common.PageActions;
import com.thomsonreuters.pageobjects.otherPages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.fastDraft.Header;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceFFHImpl;
import com.thomsonreuters.pageobjects.utils.TimeoutUtils;
import cucumber.api.Scenario;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public class BaseScreenShotHook extends BaseStepDef {

	protected static final Logger LOG = LoggerFactory.getLogger(BaseScreenShotHook.class);

	private static final String BASE_ELEMENT_XPATH = "//body//div";

	private static final int WAIT_FOR_PAGE_LOADING_TIMEOUT_SEC = 60;

	private RestServiceFFHImpl restServiceFFHImpl = new RestServiceFFHImpl();
	private PageActions pageActions = new PageActions();
	private NavigationCobalt navigationCobalt = new NavigationCobalt();
	private HomePage homePage = new HomePage();
	private WLNHeader header = new WLNHeader();

	public void deleteCookies() {
		LOG.info("Deleting cookies");
		homePage.deleteAllCookies();
		resetCurrentUser();
	}

	/**
	 * Performs necessary after steps if test failed
	 * 
	 * @param scenario
	 * @throws InterruptedException
	 */
	public void afterTest(Scenario scenario) throws InterruptedException {
		takeScreenshotAndLogSessionIfTestFailed(scenario);
		signOffIfTestFailed(scenario);
	}

	/**
	 * Takes screen-shot if the scenario fails
	 *
	 * @param scenario
	 */
	public void takeScreenshotAndLogSessionIfTestFailed(Scenario scenario) throws InterruptedException {
		LOG.info("Taking screenshot if test failed");
		if (!System.getProperty("driverType").equalsIgnoreCase("browserStack")) {
			try {
				Map<String, Object> screenShots = ScreenShots.getScreenShotsForCurrentTest();
				for (Map.Entry<String, Object> screenShot : screenShots.entrySet()) {
					scenario.write(screenShot.getKey());
					scenario.embed((byte[]) screenShot.getValue(), "image/png");
				}
				ScreenShots.tidyUpAfterTestRun();

				if (scenario.isFailed()) {
					LOG.info("Test is failed - taking screenshot");
					scenario.write(homePage.getCurrentUrl());
					//if blank page is present  - log error and wait
					if (!homePage.isExists(By.xpath(BASE_ELEMENT_XPATH))) {
						LOG.error(String.format("Page has no base element (%s), probably loading operation is in progress."
								+ " Attempt to wait until all elements will be loaded", BASE_ELEMENT_XPATH));
						TimeoutUtils.sleepInSeconds(WAIT_FOR_PAGE_LOADING_TIMEOUT_SEC);
					}
					byte[] screenShot = ((TakesScreenshot) homePage.getDriver).getScreenshotAs(OutputType.BYTES);
					scenario.embed(screenShot, "image/png");
					logCurrentSessionId(scenario);

				}
			} catch (WebDriverException e) {
				LOG.error(e.getMessage());
			}
		}
	}

	public void signOffIfTestFailed(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				signOffCobalt();
			} catch (IOException | InterruptedException e) {
				LOG.error(e.getMessage());
			}
			resetCurrentUser();
		}
	}

	/**
	 * Log session ID of current user to report and console output. If some
	 * server errors will occurred, then exception stack trace will be print to
	 * output and nothing to report.
	 *
	 * @param scenario
	 *            Current test scenario
	 */
	protected void logCurrentSessionId(Scenario scenario) {
		try {
			String sessionId = restServiceFFHImpl.getCurrentSession();
			if (StringUtils.isNotBlank(sessionId)) {
				String logText = "<br><b>Session ID:</b> " + sessionId + "";
				LOG.info(logText);
				scenario.write(logText);
			} else {
				LOG.error("Session id is empty. Maybe last test step was not on PLC page");
			}
		} catch (HttpClientErrorException | HttpServerErrorException ex) {
			LOG.info("Error occurred at attempt to obtain Session Id of current user. Maybe he is not authorized now. Exception: ", ex);
		}
	}

	private void signOffCobalt() throws IOException, InterruptedException {
		LOG.info("Sign-off cobalt user");
		WebElement element = null;
		try {
			if (currentUser.getProduct() != null) {
				navigateToHomePage();
				navigationCobalt.waitForPageToLoad();

				switch (currentUser.getProduct()) {
				case WLN:
					element = homePage.findElement(By.linkText("Sign Off"));
					break;
				case PLC:
					homePage.findElement(By.xpath("//*[@id='preferences-dropdown']")).click();
					element = homePage.findElement(By.linkText("Sign out"));
					break;
				case ANZ:
					header.signOff();
					LOG.info("The user is signed off from ANZ");
					break;
				case PLC_lEGACY:
					element = homePage.findElement(By.linkText("Log out"));
					break;
				default:
					break;
				}
				element.click();
			} else {
				LOG.error("Current user's product is null");
			}
		} catch (NoSuchElementException | ElementNotVisibleException nse) {
			LOG.error("Sign-Off link not found");
		} finally {
			deleteCookies();
		}
	}

	public boolean newSession() {
		return System.getProperty("newSession").equalsIgnoreCase("true");
	}

	private void navigateToHomePage() {
		switch (currentUser.getProduct()) {
		case WLN:
			navigationCobalt.navigateToWestlawNext();
			break;
		case PLC:
			navigationCobalt.navigateToPLUKPlus();
			break;
		case ANZ:
			navigationCobalt.navigateToPLANZPlus();
			break;
		case PLC_lEGACY:
			navigationCobalt.navigateToPLCLegacy();
			break;
		default:
			break;
		}
	}
}