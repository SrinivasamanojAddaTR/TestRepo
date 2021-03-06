package com.thomsonreuters.pageobjects.other_pages;

import com.thomsonreuters.driver.configuration.Hosts;
import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;

import com.thomsonreuters.utils.CommonStringMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class NavigationCobalt  {
    
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(NavigationCobalt.class);

    public static final By HOME_PAGE_CSS_SELECTOR = By.xpath("//*[@id='headerLogo']//a");
    public static final By WLN_HOME_PAGE_CSS_SELECTOR = By.id("coid_website_logo");
    private static final String PLUK_FULL_BASE_URL = System.getProperty("plukFullBaseUrl", "");
    private static final String PLAU_FULL_BASE_URL = System.getProperty("plauFullBaseUrl", "");
    private static final String PROD_A="prodA";
    private static final String PROD="prod";
    private static final String QED_A="qedA";
    private static final String QED_B="qedB";
    private static final String PROD_B="prodB";

    String baseUrl = System.getProperty("base.url");
    String baseLegacyUrl = System.getProperty("base.legacy.url");
    
    private HomePage homePage;
    private WebDriver driver;
    private Hosts hosts;

    public NavigationCobalt() {
        super();
        homePage = new HomePage();
        this.driver = homePage.getDriver;
        hosts = Hosts.getInstance();
    }
    
    // for URLs -  https://practicallaw.thomsonreuters.co.uk - practicallaw.demo.thomsonreuters.co.uk - practicallaw.qed.thomsonreuters.co.uk	
    public void navigateToPracticalLaw(String page) {
    	homePage.navigate(hosts.getPracticallawProductBase() + baseUrl + hosts.getPlcukDomain() + page);
    }
    
    public void navigateToPracticalLaw() {
    	navigateToPracticalLaw("");
    }
    
    // for URLs - https://westlawuk.thomsonreuters.co.uk  - https://westlawuk.demo.thomsonreuters.co.uk - https://westlawuk.qed.thomsonreuters.co.uk
    public void navigateToWestlawUk(String page) {
    	homePage.navigate(hosts.getWestlawukProductBase() + baseUrl + hosts.getPlcukDomain()+ page);
    } 
    
    public void navigateToWestlawUk() {
    	navigateToWestlawUk("");
    }  
    
    // for URLs - https://books.thomsonreuters.co.uk -  https://books.demo.thomsonreuters.co.uk  - https://books.qed.thomsonreuters.co.uk    
    public void navigateToBooks() {
    	 homePage.navigate(hosts.getBooksProductBase() + baseUrl + hosts.getPlcukDomain());
    }
    
    public void navigateToWestlawNext() {
        homePage.navigate(hosts.getWlnProductBase() + baseUrl + hosts.getWlnDomain());
    }

    public void navigateToWestlawNextBrowsePreview() {
        homePage.navigate(hosts.getWlnProductBase() + baseUrl + hosts.getWlnDomain() + "/BrowsePreview/Home");
    }

    public void navigateToWLNSpecificURL(String sitePage) {
        homePage.navigate(hosts.getWlnProductBase() + baseUrl + hosts.getWlnDomain() + sitePage);
    }

    /**
     * This method is to navigate to the temporary routing page needed to log
     * onto demo as at 21/01/15
     */
    public void navigateToTempRoutingPage() {
        homePage.navigate(hosts.getPlcukProductBase() + baseUrl + hosts.getPlcukDomain() + "/routing?SessionStartEventProductNameOverride=PLCUK");
    }

    /**
     * This method is to navigate to the temporary routing page needed to log
     * onto demo as at 03/02/15
     */
    public void navigateToNewTempRoutingPage() {
        homePage.navigate(hosts.getPlcukProductBase() + baseUrl + hosts.getPlcukDomain()
                + "/routing?routingOptions=%5B%7B%22WebContentCollectionSet%22%3A%22w_cb_wcmstst_cs%22%7D%2C%7B%22CategoryPageCollectionSet%22%3A%22w_plplus_catpagestst_cs%22%7D%2C%7B%22includeSignOnClick%22%3Atrue%7D%5D");
    }

    public void navigateToWLNGlossaryPage() {
        homePage.navigate(hosts.getPlcukProductBase() + baseUrl + hosts.getPlcukDomain() + "/Glossary/UKPracticallaw");
    }

    public void navigateToANZSpecificResourcePage(String sitePage) {
        if (!PLAU_FULL_BASE_URL.trim().isEmpty()) {
            driver.get(PLAU_FULL_BASE_URL+ sitePage);
            return;
        }
        switch (baseUrl) {
            case PROD:
                homePage.navigate(hosts.getPlcauProductBase() + hosts.getPlcukDomain()+ sitePage);
                break;
            case PROD_A:
                homePage.navigate(hosts.getAuProdA() + hosts.getPlcukProdDomain()+ sitePage);
                break;
            case PROD_B:
                homePage.navigate(hosts.getAuProdB() + hosts.getPlcukProdDomain()+ sitePage);
                break;
            case QED_A:
                homePage.navigate(hosts.getAuQedA() + baseUrl + hosts.getPlcukDomain()+ sitePage);
                break;
            case QED_B:
                homePage.navigate(hosts.getAuQedB() + baseUrl + hosts.getPlcukDomain()+ sitePage);
                break;
            default:
                homePage.navigate(hosts.getPlcauProductBase() + baseUrl + hosts.getPlcukDomain() + sitePage);
                break;
        }
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
            case PROD:
                homePage.navigate(hosts.getPlcukProductBase() + hosts.getPlcukProdDomain() + page);
                break;
            case PROD_A:
                homePage.navigate(hosts.getUkProdA() + hosts.getPlcukProdDomain() + page);
                break;
            case PROD_B:
                homePage.navigate(hosts.getUkProdB() + hosts.getPlcukProdDomain() + page);
                break;
            case QED_A:
                homePage.navigate(hosts.getUkQedA() + baseUrl + hosts.getPlcukDomain() + page);
                break;
            case QED_B:
                homePage.navigate(hosts.getUkQedB() + baseUrl + hosts.getPlcukDomain() + page);
                break;
            default:
                homePage.navigate(hosts.getPlcukProductBase() + baseUrl + hosts.getPlcukDomain() + page);
                break;
        }
    }
    
    public void navigateToPLANZPlus() {
        if (!PLAU_FULL_BASE_URL.trim().isEmpty()) {
            driver.get(PLAU_FULL_BASE_URL );
            return;
        }
        switch (baseUrl) {
            case PROD:
                homePage.navigate(hosts.getPlcauProductBase() + hosts.getPlcukDomain());
                break;
            case PROD_A:
                homePage.navigate(hosts.getAuProdA() + hosts.getPlcukProdDomain());
                break;
            case PROD_B:
                homePage.navigate(hosts.getAuProdB() + hosts.getPlcukProdDomain());
                break;
            case QED_A:
                homePage.navigate(hosts.getAuQedA() + baseUrl + hosts.getPlcukDomain());
                break;
            case QED_B:
                homePage.navigate(hosts.getAuQedB() + baseUrl + hosts.getPlcukDomain());
                break;
            default:
                homePage.navigate(hosts.getPlcauProductBase() + baseUrl + hosts.getPlcukDomain());
                break;
        }

    }

    public void navigateToFirmCentral() {
        homePage.navigate(hosts.getFirmCentralProductBase() + baseUrl + CommonStringMethods.DOT + hosts.getPlcukProdDomain());
    }

    public void navigateToPLCLegacy() {
        homePage.navigate(hosts.getPlcLegacyProductBase() + baseLegacyUrl + hosts.getPlcLegacyDomain());
    }

    public void navigateToTempHomePage() {
        homePage.navigate(hosts.getPlcukProductBase() + baseUrl + hosts.getPlcukDomain() + "/Browse/Home/Practice/Home?transitionType=Default&contextData=(sc.Default)&CobaltRefresh=52840&firstPage=true&bhcp=1");
        homePage.waitForPageToLoad();
    }

	public void navigateToPLCANZSpecificURL(String sitePage) {
        navigateToANZSpecificResourcePage(sitePage);

	}

    public void navigateToPLCUKPlusWithRouting(String routingString) {
        homePage.navigate(hosts.getPlcukProductBase() + baseUrl + hosts.getPlcukDomain() + routingString);
    }

    public void navigateToRelativeURL(String relativeUrl) {
        String currUrl = driver.getCurrentUrl();
        String baseURL;
        URL url;

        try {
            url = new URL(currUrl);
            baseURL = url.getProtocol() + "://" + url.getHost();

            if (relativeUrl.startsWith("/")) {
                homePage.navigate(baseURL + relativeUrl);
            } else {
                homePage.navigate(baseURL + "/" + relativeUrl);
            }
        } catch (MalformedURLException e) {
            logger.info("context", e);
        }

    }

    /**
     * This method takes back to to the home page of PLC UK.
     */
    public void navigateToHomePage() {
    	homePage.waitForExpectedElement(HOME_PAGE_CSS_SELECTOR).click();
    	homePage.waitForPageToLoad();
    }

    public void navigateToWLNHomePage() {
    	homePage.waitForExpectedElement(WLN_HOME_PAGE_CSS_SELECTOR).click();
    	homePage.waitForPageToLoad();
    }

    /**
     * This method is to open the History navigation menu by placing mouse over on it.
     */
    public void navigateToHistoryMenu() {
        try {
            WebElement ele = homePage.waitForExpectedElement(By.cssSelector("a[title='Open Recent History']"));
            ele.click();
        } catch (TimeoutException ne) {
            throw new PageOperationException(ne.getMessage());
        }
    }

    /**
     * This method is to open the My Folders Page by clicking on Folders menu.
     */
    public void navigateToFoldersPage() {
        try {
        	homePage.waitForExpectedElement(By.cssSelector("#co_recentFoldersContainer a.co_dropdownBoxanchorLabel")).click();
        } catch (TimeoutException ne) {
            throw new PageOperationException(ne.getMessage());
        }
    }

}