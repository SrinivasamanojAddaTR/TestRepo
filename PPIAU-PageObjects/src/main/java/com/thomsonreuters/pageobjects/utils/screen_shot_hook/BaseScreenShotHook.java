package com.thomsonreuters.pageobjects.utils.screen_shot_hook;

import com.thomsonreuters.driver.exception.SiteCookieException;
import com.thomsonreuters.driver.framework.ScreenShots;
import com.thomsonreuters.driver.framework.WebDriverDiscovery;
import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceFFHImpl;
import com.thomsonreuters.utils.TimeoutUtils;
import cucumber.api.Scenario;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public class BaseScreenShotHook extends BaseStepDef {

	protected static final Logger LOG = LoggerFactory.getLogger(BaseScreenShotHook.class);

	public static final String CHROME_START_URL = "data:,";
	private static final String BASE_ELEMENT_XPATH = "//body//div";
	private static final String SCENARIO_LOG_MESSAGE_PATTERN = "<br><b>%s:</b> %s";
	private static final int WAIT_FOR_PAGE_LOADING_TIMEOUT_SEC = 60;

	private RestServiceFFHImpl restServiceFFHImpl = new RestServiceFFHImpl();
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
	 */
	public void afterTest(Scenario scenario) {
		takeScreenshotAndLogSessionIfTestFailed(scenario);
		signOffIfTestFailed(scenario);
	}

	/**
	 * Takes screen-shot if the scenario fails
	 *
	 * @param scenario
	 */
	public void takeScreenshotAndLogSessionIfTestFailed(Scenario scenario) {
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
					if (!homePage.isExists(By.xpath(BASE_ELEMENT_XPATH)) && StringUtils.containsNone(CHROME_START_URL, homePage.getCurrentUrl())) {
						LOG.error("Page has no base element {}, probably loading operation is in progress. " +
							"Attempt to wait until all elements will be loaded", BASE_ELEMENT_XPATH);
						TimeoutUtils.sleepInSeconds(WAIT_FOR_PAGE_LOADING_TIMEOUT_SEC);
					}
					byte[] screenShot = homePage.getScreenshot();
					scenario.embed(screenShot, "image/png");
				}
				logCurrentSessionId(scenario);
				logSeleniumNodeHost(scenario);
				logBrowserConsole(scenario);
			} catch (WebDriverException e) {
				LOG.error(e.getMessage());
			}
		}
	}

	public void signOffIfTestFailed(Scenario scenario) {
		if (scenario.isFailed()) {
			signOffCobalt();
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
			String sessionIdFromUI = getSessionIDFromUI();
			String sessionId = StringUtils.isNotBlank(sessionIdFromUI)?sessionIdFromUI : restServiceFFHImpl.getCurrentSession();
			if (StringUtils.isNotBlank(sessionId)) {
				String logText = String.format(SCENARIO_LOG_MESSAGE_PATTERN, "Session ID", sessionId);
				LOG.info(logText);
				scenario.write(logText);
			} else {
				LOG.error("Session id is empty. Maybe last test step was not on PLC page");
			}
		} catch (HttpClientErrorException | HttpServerErrorException | SiteCookieException ex) {
			LOG.info("Error occurred at attempt to obtain Session Id of current user. Maybe he is not authorized now. Exception: ", ex);
		}
	}

	protected void logSeleniumNodeHost(Scenario scenario) {
		if (WebDriverDiscovery.isSeleniumGrid()) {
			String text = String.format(SCENARIO_LOG_MESSAGE_PATTERN, "Selenium Node Host", WebDriverDiscovery.getSeleniumNodeHost());
			scenario.write(text);
		}
	}

	protected void logBrowserConsole(Scenario scenario) {
		LOG.info("Browser logs: capturing was started");
		byte[] browserLogs = getDriver().manage().logs().get(LogType.BROWSER).getAll()
			.stream().map(LogEntry::getMessage).collect(Collectors.joining(StringUtils.LF)).getBytes();
		if (ArrayUtils.isNotEmpty(browserLogs)) {
			scenario.embed(browserLogs, "text/plain");
			LOG.info("Browser logs were attached to report");
		} else {
			LOG.info("Browser logs are empty, it is nothing to attach");
		}
		LOG.info("Browser logs: capturing was finished");
	}

	private void signOffCobalt() {
		LOG.info("Sign-off cobalt user");
		WebElement element = null;
		try {
			if (currentUser.getProduct() != null) {
				navigateToHomePage();
				homePage.waitForPageToLoad();
				switch (currentUser.getProduct()) {
				case WLN:
					element = homePage.findElement(By.linkText("Sign Off"));
					element.click();
					break;
				case PLC:
					header.signOff();
					LOG.info("The user is signed off from PLUK");
					break;
				case ANZ:
					header.signOff();
					LOG.info("The user is signed off from ANZ");
					break;
				case PLC_LEGACY:
					element = homePage.findElement(By.linkText("Log out"));
					element.click();
					break;
				default:
					break;
				}
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
		case PLC_LEGACY:
			navigationCobalt.navigateToPLCLegacy();
			break;
		default:
			break;
		}
	}
}