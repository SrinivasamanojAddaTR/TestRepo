package com.thomsonreuters.pageobjects.otherPages;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class NavigationCobalt  {
    
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(NavigationCobalt.class);

    public static final By HOME_PAGE_CSS_SELECTOR = By.xpath("//*[@id='header_lower_logo']//a");
    public static final By WLN_HOME_PAGE_CSS_SELECTOR = By.id("coid_website_logo");
    private static final String PLUK_FULL_BASE_URL = System.getProperty("plukFullBaseUrl", "");

    String baseUrl = System.getProperty("base.url");
    String baseLegacyUrl = System.getProperty("base.legacy.url");
    
    private HomePage homePage;
    private RemoteWebDriver driver;

    public NavigationCobalt() {
        super();
        homePage = new HomePage();
        this.driver = HomePage.getDriver;
    }
    
    // for URLs -  https://practicallaw.thomsonreuters.co.uk - practicallaw.demo.thomsonreuters.co.uk - practicallaw.qed.thomsonreuters.co.uk	
    public void navigateToPracticalLaw(String page) {
    	driver.get(homePage.getHostsObject().getPracticallawProductBase() + baseUrl + homePage.getPlcukDomain() + page);
    }
    
    public void navigateToPracticalLaw() {
    	navigateToPracticalLaw("");
    }
    
    // for URLs - https://westlawuk.thomsonreuters.co.uk  - https://westlawuk.demo.thomsonreuters.co.uk - https://westlawuk.qed.thomsonreuters.co.uk
    public void navigateToWestlawUk(String page) {
    	driver.get(homePage.getHostsObject().getWestlawukProductBase() + baseUrl + homePage.getPlcukDomain()+ page);
    } 
    
    public void navigateToWestlawUk() {
    	navigateToWestlawUk("");
    }  
    
    // for URLs - https://books.thomsonreuters.co.uk -  https://books.demo.thomsonreuters.co.uk  - https://books.qed.thomsonreuters.co.uk    
    public void navigateToBooks() {
    	 driver.get(homePage.getHostsObject().getBooksProductBase() + baseUrl + homePage.getPlcukDomain());
    }
    
    public void navigateToWestlawNext() {
        driver.get(homePage.getWlnProductBase() + baseUrl + homePage.getWlnDomain());
    }

    public void navigateToWestlawNextBrowsePreview() {
        driver.get(homePage.getWlnProductBase() + baseUrl + homePage.getWlnDomain() + "/BrowsePreview/Home");
    }

    public void navigateToWLNSpecificURL(String sitePage) {
        driver.get(homePage.getWlnProductBase() + baseUrl + homePage.getWlnDomain() + sitePage);
    }

    /**
     * This method is to navigate to the temporary routing page needed to log
     * onto demo as at 21/01/15
     */
    public void navigateToTempRoutingPage() {
        driver.get(homePage.getPlcukProductBase() + baseUrl + homePage.getPlcukDomain() + "/routing?SessionStartEventProductNameOverride=PLCUK");
    }

    /**
     * This method is to navigate to the temporary routing page needed to log
     * onto demo as at 03/02/15
     */
    public void navigateToNewTempRoutingPage() {
        driver.get(homePage.getPlcukProductBase() + baseUrl + homePage.getPlcukDomain()
                + "/routing?routingOptions=%5B%7B%22WebContentCollectionSet%22%3A%22w_cb_wcmstst_cs%22%7D%2C%7B%22CategoryPageCollectionSet%22%3A%22w_plplus_catpagestst_cs%22%7D%2C%7B%22includeSignOnClick%22%3Atrue%7D%5D");
    }

    public void navigateToWLNGlossaryPage() {
        driver.get(homePage.getPlcukProductBase() + baseUrl + homePage.getPlcukDomain() + "/Glossary/UKPracticallaw");
    }

    /**
     * @deprecated use {@link #navigateToPLUKPlus(String)}
     * @param page
     */
    @Deprecated
    public void navigateToWLNSpecificResourcePage(String page) {
        navigateToPLUKPlus(page);
    }
    
    public void navigateToANZSpecificResourcePage(String page) {
        driver.get(homePage.getPlcauProductBase() + baseUrl + homePage.getPlcukDomain() + page);
    }

    /**
     * Navigate to PL+ UK home page
     */
    public void navigateToPLUKPlus() {
        navigateToPLUKPlus("");
    }

    /**
     * Navigate to PL+ UK page
     *
     * @param page Page URI without domain name
     */
    public void navigateToPLUKPlus(String page) {
        if (!PLUK_FULL_BASE_URL.trim().isEmpty()) {
            driver.get(PLUK_FULL_BASE_URL + page);
            return;
        }
        switch (baseUrl) {
            case "prod":
                driver.get(homePage.getPlcukProductBase() + homePage.getPlcukProdDomain() + page);
                break;
            case "prodA":
                driver.get(homePage.getUkProdA() + homePage.getPlcukProdDomain() + page);
                break;
            case "prodB":
                driver.get(homePage.getUkProdB() + homePage.getPlcukProdDomain() + page);
                break;
            case "qedA":
                driver.get(homePage.getUkQedA() + baseUrl + homePage.getPlcukDomain() + page);
                break;
            case "qedB":
                driver.get(homePage.getUkQedB() + baseUrl + homePage.getPlcukDomain() + page);
                break;
            default:
                driver.get(homePage.getPlcukProductBase() + baseUrl + homePage.getPlcukDomain() + page);
                break;
        }
    }
    
    public void navigateToPLANZPlus() {
            driver.get(homePage.getPlcauProductBase() + baseUrl + homePage.getPlcukDomain());
    }

    public void navigateToFirmCentral() {
        //TODO: replace hardcoded string after Sergey's changes in AbstractPage and HOSTS
        driver.get(homePage.getHostsObject().getFirmCentralProductBase() + baseUrl + "." + homePage.getPlcukProdDomain());
    }

    public void navigateToPLCLegacy() {
        driver.get(homePage.getPlcLegacyProductBase() + baseLegacyUrl + homePage.getPlcLegacyDomain());
    }

    public void navigateToTempHomePage() {
        driver.get(homePage.getPlcukProductBase() + baseUrl + homePage.getPlcukDomain() + "/Browse/Home/Practice/Home?transitionType=Default&contextData=(sc.Default)&CobaltRefresh=52840&firstPage=true&bhcp=1");
        waitForPageToLoad();
    }

    /**
     * @deprecated use {@link #navigateToPLUKPlus(String)}
     * @param page
     */
    @Deprecated
    public void navigateToPLCUKPlusSpecificURL(String page) {
        navigateToPLUKPlus(page);
    }

	public void navigateToPLCANZSpecificURL(String sitePage) {
		driver.get(homePage.getPlcauProductBase() + baseUrl + homePage.getPlcukDomain() + sitePage);
	}

    public void navigateToPLCUKPlusWithRouting(String routingString) {
        driver.get(homePage.getPlcukProductBase() + baseUrl + homePage.getPlcukDomain() + routingString);
    }

    public void navigateToRelativeURL(String relativeUrl) {
        String currUrl = driver.getCurrentUrl();
        String baseURL = "";
        URL url = null;

        try {
            url = new URL(currUrl);
        } catch (MalformedURLException e) {
            logger.info("context", e);
        }
        baseURL = url.getProtocol() + "://" + url.getHost();

        if (relativeUrl.startsWith("/")) {
            driver.get(baseURL + relativeUrl);
        } else {
            driver.get(baseURL + "/" + relativeUrl);
        }
    }

    /**
     * This method takes back to to the home page of PLC UK.
     */
    public void navigateToHomePage() {
    	homePage.waitFluentForElement(HOME_PAGE_CSS_SELECTOR).click();
    	homePage.waitForPageToLoad();
    }

    public void navigateToWLNHomePage() {
    	homePage.waitFluentForElement(WLN_HOME_PAGE_CSS_SELECTOR).click();
    	homePage.waitForPageToLoad();
    }

    /**
     * This method is to open the History navigation menu by placing mouse over on it.
     */
    public void navigateToHistoryMenu() {
        try {
            WebElement ele = homePage.retryingFindElement(By.cssSelector("a[title='Open Recent History']"));
            ele.click();
        } catch (TimeoutException ne) {
            logger.info("context", ne);
            throw new PageOperationException(ne.getMessage());
        }
    }

    /**
     * This method is to open the My Folders Page by clicking on Folders menu.
     */
    public void navigateToFoldersPage() {
        try {
        	homePage.retryingFindElement(By.cssSelector("#co_recentFoldersContainer a.co_dropdownBoxanchorLabel")).click();
        } catch (TimeoutException ne) {
            logger.info("context", ne);
            throw new PageOperationException(ne.getMessage());
        }
    }
    
    @Deprecated
    public void waitForPageToLoad() {
    	homePage.waitForPageToLoad();
    }
    
    @Deprecated
    public void waitForPageToLoadAndJQueryProcessing() {
    	homePage.waitForPageToLoadAndJQueryProcessing();
    }
    
    @Deprecated
    public WebElement waitForElementPresent(By by) {
    	return homePage.waitForElementPresent(by);
    }
    
    @Deprecated
    public String getWindowHandle() {
    	return homePage.getWindowHandle();
    }
    
    @Deprecated
    public void navigate(String url) {
    	homePage.navigate(url);
    }
    
    @Deprecated
    public void waitForPageTitle(String url) {
    	homePage.waitForPageTitle(url);
    }
    
    @Deprecated
    public String getPageTitle() {
    	return homePage.getPageTitle();
    }
    

}