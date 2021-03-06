package com.thomsonreuters.step_definitions.browse;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.legal_updates.LegalUpdatesResultsPage;
import com.thomsonreuters.pageobjects.pages.legal_updates.LegalUpdatesWidgetPage;
import com.thomsonreuters.pageobjects.pages.page_creation.HomePage;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.GlossaryPage;
import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.TopicPage;
import com.thomsonreuters.pageobjects.pages.search.KnowHowDocumentPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.utils.OnepassLoginUtils;
import com.thomsonreuters.pageobjects.utils.homepage.FooterUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.search.SearchUtils;
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

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class AnzBrowseTestSteps extends BaseStepDef {

    private KnowHowDocumentPage knowHowDocumentPage = new KnowHowDocumentPage();
    private WLNHeader wlnHeader = new WLNHeader();
    private HomePage homePage = new HomePage();
    private LegalUpdatesWidgetPage legalUpdatesWidget = new LegalUpdatesWidgetPage();
    private LegalUpdatesResultsPage legalUpdatesResultsPage = new LegalUpdatesResultsPage();
    private TopicPage topicPage = new TopicPage();
    private GlossaryPage glossaryPage = new GlossaryPage();
    private CategoryPage categoryPage = new CategoryPage();
    private FooterUtils footerUtils = new FooterUtils();
    private CommonMethods commonMethods = new CommonMethods();
    private SearchUtils searchUtils = new SearchUtils();
    private OnepassLoginUtils onepassLoginUtils = new OnepassLoginUtils();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Then("^user navigates directly to url \"(.*)\"$")
    public void userNavigatesDirectlyToUrl(String url) throws Throwable {
        getDriver().navigate().to(url);
    }

    @Then("^user verifies the \"(.*)\" page$")
    public void userShouldseethePage(String pageTitle) throws Throwable {
        if (pageTitle.equalsIgnoreCase("Country Q&A")) {
            assertTrue(pageTitle + " page Title is Not matching..!", knowHowDocumentPage.getFullText().contains(pageTitle));

        } else {
            assertTrue(pageTitle + " page Title is Not matching..!", wlnHeader.pageHeaderLabel().getText().toLowerCase().contains(pageTitle.toLowerCase()));
            if (wlnHeader.getCurrentUrl().contains("Browse/Home")) {
                assertTrue("Category page controller does not to return true from ShouldHeaderBeAboveContent", wlnHeader.isBrowseHeaderWithinPageHeader());
            }
        }
    }

    @When("^the user selects (single|multiple) facet selection mode$")
    public void theUserSelectsSingleOrMultipleFacetSelectionMode(SearchUtils.FacetSelectionMode mode) {
        searchUtils.chooseSingleOrMultipleFacetSelectionMode(mode);
    }


    @Then("^user verifies the \"(.*)\" page Title in Open Web$")
    public void userShouldseethePageTitleInOpenWeb(String pageTitle) throws Throwable {
        assertTrue(pageTitle + " page Title is Not matching..!", knowHowDocumentPage.getFullText().contains(pageTitle));
    }

    @Then("^user verifies the following tabs are displayed$")
    public void userVerifiesTheFollowingTabsAreDisplayed(List<String> tabs) throws Throwable {
        for (String tab : tabs) {
            assertTrue(tab + " is not visible..!", homePage.specificTab(tab).isDisplayed());
        }
    }

    @Then("^user verifies the following practice area links are displayed$")
    public void userVerifiesTheFollowingPracticeAreaLinksAreDisplayed(List<String> linksText) throws Throwable {
        for (String linkText : linksText) {
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
                String linksArray[] = entry.getValue().split(";");
                homePage.specificTab(entry.getKey()).click();
                assertThat(linksArray)
                    .allSatisfy(
                        link -> assertThat(homePage.tabSubFeatureHeadings(link.trim()).isDisplayed())
                            .overridingErrorMessage("%s not present!", entry.getValue())
                            .isTrue());
            } else if (!entry.getKey().equalsIgnoreCase("Tab")) {
                String linksArray[] = entry.getValue().split(";");
                homePage.specificTab(entry.getKey()).click();
                assertThat(linksArray)
                    .allSatisfy(
                        link -> assertThat(homePage.isElementDisplayed(homePage.getElementByLinkText(link.trim())))
                            .overridingErrorMessage("%s not present!", entry.getValue())
                            .isTrue());
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
        topicPage.waitForPageToLoad();
        topicPage.waitForPageToLoadAndJQueryProcessing();
        String resourceList[] = resources.split(",");
        for (String resourceName : resourceList) {
            boolean checkTopicHeading = topicPage.isResourceHeadingDisplayed(resourceName.trim());
            if (!checkTopicHeading) {
                while (searchResultsPage.isSelectNextPageDisplayed()) {
                    searchResultsPage.selectNextPage().click();
                    assertThat(topicPage.isResourceHeadingDisplayed(resourceName.trim())).overridingErrorMessage("%s is not displayed..!",resourceName).isTrue();
                }
            }
        }
    }


    @Then("^user expands the \"(.*?)\" facets group$")
    public void expandFacetGroup(String type) {
        topicPage.waitForPageToLoad();
        topicPage.waitForPageToLoadAndJQueryProcessing();
        if (!topicPage.facetGroupCollapsedOrExpanded(type))
            topicPage.expandFacetGroup(type);
    }

    @Then("^user verifies the \"(.*?)\" facets are displayed on the topic page$")
    public void userVerifiesTheFollowingFacetsAreDisplayedOnTheTopicPage(String resources) throws Throwable {
        String facetList[] = resources.split(",");
        boolean isPresent = false;
        topicPage.waitForPageToLoad();
        topicPage.waitForPageToLoadAndJQueryProcessing();
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

    @Given("^the user verifies the topic facet \"(.*?)\" count is equivalent to no\\. of results displayed$")
    public void theUserVerifiesTheTopicFacetCountIsEquivalentToNoOfResultsDisplayed(String facetName) throws Throwable {
        topicPage.waitForPageToLoad();
        topicPage.waitForPageToLoadAndJQueryProcessing();
        int docRowCount = topicPage.resourceDocTitleAllList().size();
        int resourceFacetCount = Integer.parseInt(topicPage.specificFacetCount(facetName).getText());
        assertThat(docRowCount).as(docRowCount + " & " + resourceFacetCount + " are not matching..!").isEqualTo(resourceFacetCount);
    }

    @Then("^user should see following countries with \"(.*?)\" and \"(.*?)\" tabs$")
    public void userShouldSeeFollowingCountriesWithAndTabs(String countryQnATab, String resourceTab, List<String> countriesList) throws Throwable {
        for (String country : countriesList) {
            if (country.equalsIgnoreCase("Russian Federation")) {
                homePage.countryLink("russian_federation").click();
            } else {
                homePage.waitForPageToLoad();
                homePage.waitForPageToLoadAndJQueryProcessing();
                homePage.getElementByLinkText(country).click();
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
        for (String linkText : linksList) {
            footerUtils.closeDisclaimerMessage();
            if (linkText.equalsIgnoreCase("Country Q&A comparison tool")) {
                homePage.waitForPageToLoad();
                homePage.getElementByLinkText("Start comparing").click();
                assertTrue(linkText + " page is not displayed..!", homePage.waitForElementVisible(knowHowDocumentPage.getDocumentTitle())
                        .getText().trim().contains("comparison tool"));
            } else {
                homePage.waitForPageToLoad();
                homePage.getElementByLinkText(linkText).click();
                assertTrue(linkText + " page is not displayed..!", wlnHeader.pageHeaderLabel().getText().contains(linkText));
            }
            getDriver().navigate().back();
            homePage.specificTab("International").click();
        }
    }

    @Then("^user should see following countries with \"(.*?)\" and \"(.*?)\" tabs through BrowseMenu$")
    public void userShouldSeeFollowingCountriesWithAndTabsThroughBrowseMenu(String countryQnATab, String resourceTab, List<String> countriesList) throws Throwable {
        wlnHeader.browseMenuButton().click();
        for (String country : countriesList) {
            wlnHeader.browseMenuButton().click();
            wlnHeader.getElementByLinkText("International").click();
            if (country.equalsIgnoreCase("Australia")) {
                homePage.countryBrowseMenuLink(country).click();
                assertTrue(country + " resources tab is not matching..!", homePage.specificTab("All " + country + " resources").isDisplayed());
            } else if (country.equalsIgnoreCase("Russian Federation")) {
                homePage.countryBrowseMenuLink(country).click();
                assertTrue(country + " resources tab is not matching..!", homePage.specificTab("All Russia resources").isDisplayed());
            } else {
                homePage.waitForPageToLoad();
                homePage.getElementByLinkText(country).click();
                assertTrue(country + " resources tab is not matching..!", homePage.specificTab("All " + country + " resources").isDisplayed());
            }
            assertTrue(country + " page is not displayed..!", wlnHeader.pageHeaderLabel().getText().contains(country));
            assertTrue(country + " tab is not matching..!", homePage.specificTab(countryQnATab).isDisplayed());
        }
    }

    @Then("^user selects following links and should see their respective pages through BrowseMenu$")
    public void userSelectsFollowingLinksAndShouldSeeTheirRespectivePagesThroughBrowseMenu(List<String> linksList) throws Throwable {
        for (String linkText : linksList) {
            wlnHeader.browseMenuButton().click();
            wlnHeader.getElementByLinkText("International").click();
            wlnHeader.waitForPageToLoad();
            wlnHeader.getElementByLinkText(linkText).click();
            assertTrue(linkText + " page is not displayed..!", wlnHeader.pageHeaderLabel().getText().contains(linkText));
        }
    }

    @Then("^user verifies following Resource Types with presence of all Practice Areas$")
    public void userVerifiesFollowingResourceTypesWithPresenceOfAllPracticeAreas(DataTable dataTable) throws Throwable {
        String practiceAreas[] = null;
        Map<String, String> resourceMap = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("Glossary")) {
                glossaryPage.waitForPageToLoad();
                glossaryPage.waitForPageToLoadAndJQueryProcessing();
                glossaryPage.getElementByLinkText("Glossary").click();
                getDriver().navigate().back();
            } else if (!entry.getKey().equalsIgnoreCase("Resource Types")) {
                practiceAreas = entry.getValue().split(",");
                glossaryPage.waitForPageToLoad();
                glossaryPage.waitForPageToLoadAndJQueryProcessing();
                glossaryPage.getElementByLinkText(entry.getKey()).click();
                for (String pa : practiceAreas) {
                    String linkText = pa.trim();
                    LOG.warn("Resource type: " + entry.getKey() + "; Practice area: " + linkText);
                    glossaryPage.waitForPageToLoad();
                    glossaryPage.waitForExpectedElement(By.linkText(linkText)).click();
                    glossaryPage.waitForPageToLoadAndJQueryProcessing();
                    assertThat(wlnHeader.pageHeaderLabel().getText())
                            .as("%s-%s page is not displayed..!", pa.trim(), entry.getKey()).
                            containsIgnoringCase(pa.trim() + " " + entry.getKey());
                    glossaryPage.waitForPageToLoad();
                    getDriver().navigate().back();
                    glossaryPage.waitForExpectedElement(By.linkText(linkText));
                }
                onepassLoginUtils.scrollToTheTopOfTheDocument();
                wlnHeader.companyLogo().click();
                wlnHeader.waitForPageToLoad();
                homePage.specificTab("Resources").click();
            }
        }
    }

    @When("^the user navigates to resource page \"(.*?)\" filtered by \"(.*?)\" practice area$")
    public void theUserNavigatesToPracticeAreaFilteredByTopicPage(String resource, String pa) throws Throwable {
        homePage.selectLinkPresentOnTab(resource);
        homePage.waitForPageToLoad();
        commonMethods.clickElementUsingJS(homePage.getElementByLinkText(pa));
        homePage.waitForPageToLoadAndJQueryProcessing();
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
        categoryPage.getElementByLinkText("Resources").click();
        categoryPage.waitForPageToLoad();
        categoryPage.getElementByLinkText(resourceType).click();
        categoryPage.waitForPageToLoad();
        commonMethods.clickElementUsingJS(categoryPage.getElementByLinkText(practiceArea));
        categoryPage.waitForPageToLoadAndJQueryProcessing();
    }

    @And("^user opens \"(.*)\" link")
    public void userOpensLink(String textLink) throws Throwable {
        categoryPage.openPageByText(textLink);
    }

    @Then("^user verifies title \"(.*)\" page$")
    public void userShouldseetitlePage(String pageTitle) throws Throwable {
        assertTrue(pageTitle + " page Title is Not matching..!", wlnHeader.pageHeaderLabel().getText().toLowerCase().contains(pageTitle.toLowerCase()));
    }
}
