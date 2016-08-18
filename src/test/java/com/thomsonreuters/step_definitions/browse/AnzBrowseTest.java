package com.thomsonreuters.step_definitions.browse;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesResultsPage;
import com.thomsonreuters.pageobjects.pages.legalUpdates.LegalUpdatesWidget;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.GlossaryPage;
import com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources.TopicPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowDocumentPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class AnzBrowseTest extends BaseStepDef {

	private KnowHowDocumentPage knowHowDocumentPage = new KnowHowDocumentPage();
    private WLNHeader wlnHeader = new WLNHeader();
    private HomePage homePage = new HomePage();
    private CommonMethods commonMethods = new CommonMethods();
    private LegalUpdatesWidget legalUpdatesWidget = new LegalUpdatesWidget();
    private LegalUpdatesResultsPage legalUpdatesResultsPage = new LegalUpdatesResultsPage();
    private TopicPage topicPage = new TopicPage();
    private GlossaryPage glossaryPage = new GlossaryPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private CategoryPage categoryPage = new CategoryPage();

    @Then("^user navigates directly to url \"(.*)\"$")
	public void userNavigatesDirectlyToUrl(String url) throws Throwable {
		getDriver().navigate().to(url);
	}

	@Then("^user verifies the \"(.*)\" page$")
    public void userShouldseethePage(String pageTitle) throws Throwable {
       if(pageTitle.equalsIgnoreCase("Country Q&A")){
             assertTrue(pageTitle + " page Title is Not matching..!", knowHowDocumentPage.getFullText().contains(pageTitle));

        }else {
           assertTrue(pageTitle + " page Title is Not matching..!", wlnHeader.pageHeaderLabel().getText().toLowerCase().contains(pageTitle.toLowerCase()));
           if (wlnHeader.getCurrentUrl().contains("Browse/Home")) {
        	   //assert was added to test story 827223 - Add the page header into PLCAU
        	   assertTrue("Category page controller does not to return true from ShouldHeaderBeAboveContent", wlnHeader.isBrowseHeaderWithinPageHeader());
           }           
        }
    }

    @Then("^user verifies the \"(.*)\" page Title in Open Web$")
    public void userShouldseethePageTitleInOpenWeb(String pageTitle) throws Throwable {
//        if(pageTitle.equalsIgnoreCase("Country Q&A")){
            assertTrue(pageTitle + " page Title is Not matching..!", knowHowDocumentPage.getFullText().contains(pageTitle));

//        }else {
//            assertTrue(pageTitle+" page Title is Not matching..!", wlnHeader.pageHeaderLabel().getText().contains(pageTitle));
//            if (wlnHeader.getCurrentUrl().contains("Browse/Home")) {
//                //assert was added to test story 827223 - Add the page header into PLCAU
//                assertTrue("Category page controller does not to return true from ShouldHeaderBeAboveContent", wlnHeader.isBrowseHeaderWithinPageHeader());
//            }
//        }
    }

    @Then("^user verifies the following tabs are displayed$")
    public void userVerifiesTheFollowingTabsAreDisplayed(List<String> tabs) throws Throwable {
        for(String tab : tabs){
            assertTrue(tab + " is not visible..!", homePage.specificTab(tab).isDisplayed());
        }
    }

    @Then("^user verifies the following practice area links are displayed$")
    public void userVerifiesTheFollowingPracticeAreaLinksAreDisplayed(List<String> linksText) throws Throwable {
        for(String linkText : linksText){
            assertTrue(linkText + " is not visible..!", homePage.waitForExpectedElement(By.linkText(linkText)).isDisplayed());
        }
    }
    @Given("^user navigates to PA page \"(.*?)\"$")
    public void userNavigatesToPAPage(String link) throws Throwable {
        homePage.waitForExpectedElement(By.linkText(link)).click();
    }

    @When("^user selects the following tab and see the relative links or content$")
    public void userSelectsTheFollowingTabAndSeeTheRelativeLinksOrContent(DataTable dataTable) throws Throwable {
        Map<String, String> tableMap = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : tableMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("International")) {
                String linksArray[] = entry.getValue().split(",");
                homePage.specificTab(entry.getKey()).click();
                for (String link : linksArray) {
                    assertTrue(entry.getValue() + " not present..!", homePage.tabSubFeatureHeadings(link.trim()).isDisplayed());
                }
            }else if (!entry.getKey().equalsIgnoreCase("Tab")){
                String linksArray[] = entry.getValue().split(",");
                homePage.specificTab(entry.getKey()).click();
                for (String link : linksArray) {
                    assertTrue(entry.getValue() + " not present..!", commonMethods.waitElementByLinkText(link.trim()).isDisplayed());
                }
            }
        }
    }

    @And("^the user clicks the 'View all' button on the practice area widget$")
    public void theUserHasClickedTheViewAllLegalUpdatesLinkOnThePracticeAreaWidget() throws Throwable {
        legalUpdatesWidget.veiwAllUpdatesLink().click();
        legalUpdatesResultsPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^user verifies the \"(.*?)\" sections are displayed on topic page in alphabetical order$")
    public void userVerifiesTheFollowingResourcesSectionsAreDisplayedOnTopicPageInAlphabeticalOrder(String resources) throws Throwable {
       if(commonMethods.waitForElementToBeVisible(searchResultsPage.searchByTitleAndCount(),2000)==null) {
           String resourceList[] = resources.split(",");
           for (String resourceName : resourceList) {
               assertTrue(resourceName + " is not displayed..!", topicPage.resourceHeading(resourceName.trim()).isDisplayed());
           }
       }
    }

    @Then("^user verifies the \"(.*?)\" facets are displayed on the topic page$")
    public void userVerifiesTheFollowingFacetsAreDisplayedOnTheTopicPage(String resources) throws Throwable {
        String facetList[] =resources.split(",");
        boolean isPresent=false;
        if(commonMethods.waitForElementToBeVisible(searchResultsPage.searchByTitleAndCount(),2000)==null) {
            for (String facetName : facetList) {
                for (WebElement facet : topicPage.facetNameLinksList()) {
                    String actualFacetText = facet.getText().toLowerCase().trim();
                    String expectFacetText = facetName.toLowerCase().trim();
                    if (actualFacetText.contains(expectFacetText)) {
                        isPresent = true;
                        break;
                    }
                }
                assertTrue(facetName + " is not displayed..!", isPresent);
                isPresent = false;
            }
        }
    }

    @Given("^the user verifies the topic facet \"(.*?)\" count is equivalent to no\\. of results displayed$")
    public void theUserVerifiesTheTopicFacetCountIsEquivalentToNoOfResultsDisplayed(String facetName) throws Throwable {
         commonMethods.waitForElement(topicPage.resourceDocByTitle(), 5000);
         int docRowCount= topicPage.resourceDocTitleAllList().size();
        int resourceFacetCount= Integer.parseInt(topicPage.specificFacetCount(facetName).getText());
         assertTrue(docRowCount + " & " + resourceFacetCount + " are not matching..!", docRowCount == resourceFacetCount);
    }
    @Then("^user should see following countries with \"(.*?)\" and \"(.*?)\" tabs$")
    public void userShouldSeeFollowingCountriesWithAndTabs(String countryQnATab, String resourceTab, List<String> countriesList) throws Throwable {
       for(String country : countriesList){
            if (country.equalsIgnoreCase("Russian Federation")){
               homePage.countryLink("russian_federation").click();
           }else {
               commonMethods.waitElementByLinkText(country).isDisplayed();
               commonMethods.waitElementByLinkText(country).click();
               assertTrue(country + " resources tab is not matching..!", homePage.specificTab("All " + country + " resources").isDisplayed());
           }
           assertTrue(country + " page is not displayed..!", wlnHeader.pageHeaderLabel().getText().contains(country));
           assertTrue(country + " tab is not matching..!", homePage.specificTab(countryQnATab).isDisplayed());
           getDriver().navigate().back();
           homePage.waitForPageToLoad();
           homePage.specificTab("International").click();

       }
     }

    @Then("^user selects following links and should see their respective pages$")
    public void userSelectsFollowingLinksAndShouldSeeTheirRespectivePages(List<String> linksList) throws Throwable {
           homePage.specificTab("International").click();
           for(String linkText : linksList){
               if(linkText.equalsIgnoreCase("Country Q&A comparison tool")){
                   commonMethods.waitElementByLinkText("Start comparing").click();
                   assertTrue(linkText + " page is not displayed..!", commonMethods.waitForElementToBeVisible(knowHowDocumentPage.getDocumentTitle(),1000)
                                                                      .getText().trim().contains("comparison tool"));
               }else {
                   commonMethods.waitElementByLinkText(linkText).click();
                   assertTrue(linkText + " page is not displayed..!", wlnHeader.pageHeaderLabel().getText().contains(linkText));
               }
               getDriver().navigate().back();
               homePage.specificTab("International").click();

           }
    }

    @Then("^user should see following countries with \"(.*?)\" and \"(.*?)\" tabs through BrowseMenu$")
    public void userShouldSeeFollowingCountriesWithAndTabsThroughBrowseMenu(String countryQnATab, String resourceTab, List<String> countriesList) throws Throwable {
        int count;
        wlnHeader.browseMenuButton().click();
        for(String country : countriesList){
            count=0;
            do{  count++;
                 wlnHeader.browseMenuButton().click();
            } while ((commonMethods.waitForElement(wlnHeader.browseMenuByPopup(), 1000)==null) && count < 4);
            commonMethods.getElementByLinkText("International").click();
            if(country.equalsIgnoreCase("Australia")  ){
                homePage.countryBrowseMenuLink(country).click();
                assertTrue(country + " resources tab is not matching..!", homePage.specificTab("All " + country + " resources").isDisplayed());
            }else  if(country.equalsIgnoreCase("Russian Federation") ){
                homePage.countryBrowseMenuLink(country).click();
                assertTrue(country + " resources tab is not matching..!", homePage.specificTab("All Russia resources").isDisplayed());
            }else{
                commonMethods.waitElementByLinkText(country).isDisplayed();
                commonMethods.waitElementByLinkText(country).click();
                assertTrue(country + " resources tab is not matching..!", homePage.specificTab("All " + country + " resources").isDisplayed());
            }
            assertTrue(country + " page is not displayed..!", wlnHeader.pageHeaderLabel().getText().contains(country));
            assertTrue(country + " tab is not matching..!", homePage.specificTab(countryQnATab).isDisplayed());
        }
    }

    @Then("^user selects following links and should see their respective pages through BrowseMenu$")
    public void userSelectsFollowingLinksAndShouldSeeTheirRespectivePagesThroughBrowseMenu(List<String> linksList) throws Throwable {
        int count;
        for(String linkText : linksList){
            count=0;
            do{  count++;
                wlnHeader.browseMenuButton().click();
            } while ((commonMethods.waitForElement(wlnHeader.browseMenuByPopup(), 1000)==null) && count < 4);
            commonMethods.getElementByLinkText("International").click();
            commonMethods.waitElementByLinkText(linkText).click();
            assertTrue(linkText + " page is not displayed..!", wlnHeader.pageHeaderLabel().getText().contains(linkText));
        }
    }
    @Then("^user verifies following Resource Types with presence of all Practice Areas$")
    public void userVerifiesFollowingResourceTypesWithPresenceOfAllPracticeAreas(DataTable dataTable) throws Throwable {
         String practiceAreas[]=null;
         Map<String,String> resourceMap=dataTable.asMap(String.class,String.class);
         for(Map.Entry<String,String> entry : resourceMap.entrySet() ){
             if(entry.getKey().equalsIgnoreCase("Glossary")){
				commonMethods.waitElementByLinkText("Glossary").click();
				glossaryPage.glossaryHeading().isDisplayed();
				getDriver().navigate().back();
             }else if(!entry.getKey().equalsIgnoreCase("Resource Types")){
                 practiceAreas=entry.getValue().split(",");
                 glossaryPage.waitForPageToLoad();
                 commonMethods.waitElementByLinkText(entry.getKey()).click();
                 for(String pa : practiceAreas) {
                     String linkText = pa.trim();
                     LOG.warn("Resource type: " + entry.getKey() + "; Practice area: " + linkText);
                     glossaryPage.waitForPageToLoad();
                     glossaryPage.waitForExpectedElement(By.linkText(linkText)).click();
                     assertTrue(pa.trim() + "-" + entry.getKey() + " page is not displayed..!",
                             wlnHeader.pageHeaderLabel().getText().equalsIgnoreCase(pa.trim() + " " + entry.getKey()));
                     glossaryPage.waitForPageToLoad();
                     getDriver().navigate().back();
                     glossaryPage.waitForExpectedElement(By.linkText(linkText));
                 }
                 wlnHeader.companyLogo().click();
                 homePage.specificTab("Resources").click();
             }
         }
    }

	@When("^the user navigates to resource page \"(.*?)\" filtered by \"(.*?)\" practice area$")
	public void theUserNavigatesToPracticeAreaFilteredByTopicPage(String resource, String pa) throws Throwable {
		homePage.selectLinkPresentOnTab(resource);
		commonMethods.waitElementByLinkText(pa).click();
		homePage.waitForPageToLoad();
	}


    @When("^user clicks on \"(.*?)\" in International subscriptions$")
    public void userClicksOnCountryOnInternationalSubscriptions(String country) throws Throwable {
    	homePage.internationalSubCountryLink(country).click();
    }
    
    @Then("^user verifies that there is no metadata and summary on the page$")
    public void userVerifiesThatThereAreNoMetadataAndSummary() throws Throwable {
         Assert.assertFalse("metadata" + " is displayed..!", topicPage.noMetadataDisplayed());
         Assert.assertFalse("summary" + " is displayed..!", topicPage.noSummaryDisplayed());         
    }

	@When("^user navigates to resource type \"(.*?)\" for practice area \"(.*?)\"$")
	public void userNavigatesToResourceTypeForPracticeArea(String resourceType, String practiceArea) {
        commonMethods.waitElementByLinkText("Resources").click();
        commonMethods.waitElementByLinkText(resourceType).click();
        commonMethods.waitElementByLinkText(practiceArea).click();
	}

    @And("^user opens \"(.*)\" link")
    public void userOpensLink(String textLink) throws Throwable {
        categoryPage.openPageByText(textLink);
    }
}