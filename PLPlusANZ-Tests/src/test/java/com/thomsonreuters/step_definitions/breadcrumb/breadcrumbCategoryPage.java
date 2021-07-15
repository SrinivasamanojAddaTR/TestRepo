package com.thomsonreuters.step_definitions.breadcrumb;


import com.thomsonreuters.pageobjects.common.BaseCommonLoginNavigation;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.KeyphraseState;
import com.thomsonreuters.pageobjects.other_pages.NavigationCobalt;
import com.thomsonreuters.pageobjects.pages.folders.DeleteFolderPopup;
import com.thomsonreuters.pageobjects.pages.folders.FavouritesPage;
import com.thomsonreuters.pageobjects.pages.folders.ResearchOrganizerPage;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import com.thomsonreuters.pageobjects.pages.landingPage.SearchScopeControl;
import com.thomsonreuters.pageobjects.pages.pageCreation.HomePage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.StandardDocumentPage;
import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.documentNavigation.DocumentDeliveryPage;
import com.thomsonreuters.pageobjects.pages.search.SearchResultsPage;
import com.thomsonreuters.pageobjects.pages.searchBrowse.WLUKChildTopicPage;
import com.thomsonreuters.pageobjects.pages.siteStructure.BreadCrumbPage;
import com.thomsonreuters.pageobjects.pages.siteStructure.WLUKCompartmentPage;
import com.thomsonreuters.pageobjects.pages.widgets.CategoryPage;
import com.thomsonreuters.pageobjects.rest.FolderBaseUtils;
import com.thomsonreuters.pageobjects.utils.document.Document;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.folders.HistoryUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.pageobjects.utils.sitestructure.SiteStructureUtils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.List;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.Is;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class breadcrumbCategoryPage extends BaseStepDef{
	public static WebDriver driver;
    private CategoryPage categoryPage = new CategoryPage();
    private BreadCrumbPage breadCrumbPage = new BreadCrumbPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private HomePage homePage = new HomePage();
    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private SearchScopeControl searchScopeControl = new SearchScopeControl();
    private StandardDocumentPage standardDocumentPage = new StandardDocumentPage();
    private CommonMethods commonMethods = new CommonMethods();
    private WLUKCompartmentPage wlukCompartmentPage = new WLUKCompartmentPage();
    private FolderBaseUtils restSteps = new FolderBaseUtils();
    private Document singleDocument = new Document();
    private DocumentDeliveryPage documentDeliveryPage = new DocumentDeliveryPage();
    private FoldersUtils foldersUtils = new FoldersUtils();
    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private FavouritesPage favouritesPage = new FavouritesPage();
    private HistoryUtils historyUtils = new HistoryUtils();
    private NavigationCobalt navigationCobalt = new NavigationCobalt();
    private SiteStructureUtils siteStructureUtils = new SiteStructureUtils();
    private BaseCommonLoginNavigation baseCommonLoginNavigation = new BaseCommonLoginNavigation();
    private WLUKChildTopicPage wlukChildTopicPage = new WLUKChildTopicPage(); 
    private DeleteFolderPopup deleteFolderPopup = new DeleteFolderPopup();

    @When("^the user open '(.+)' link$")
    public void openLinks(String linkName) throws Throwable {
        homePage.waitForPageSourceChangedAfterClick(wlukChildTopicPage.topicLink(linkName));
        if(!baseCommonLoginNavigation.isHomePage()){
        	breadCrumbPage.waitForBreadcrumbToBeDisplayed();
        }
        homePage.waitForPageToLoadAndJQueryProcessing();
    }



    @Then("^the user verifies that the complete breadcrumb is \"(.*)\"$")
    public void theUserVerifiesThatThePathIsEqualTo(String expectedBreadcrumb){
    	breadCrumbPage.waitForPageToLoadAndJQueryProcessing();
    	breadCrumbPage.waitForBreadcrumbToBeDisplayed();
        String actualBreadcrumb = breadCrumbPage.breadCrumbCurrent().getText();
        assertTrue("Expected breadcrumb is " + expectedBreadcrumb + ", but actual is " + actualBreadcrumb,
                expectedBreadcrumb.replaceAll("\\s","").equals(actualBreadcrumb.replaceAll("\\s","")));
    }
    @When ("^the user refreshes current page")
    public void theUserRefreshesCurrentPage(){
        homePage.refreshPage();
        homePage.waitForPageToLoadAndJQueryProcessing();
    }


    @When("^the user selects the document '(.*)'$")
    public void theUserSelectTheDocument(String name){
    	searchResultsPage.scrollIntoView(searchResultsPage.getDocumentLinkByName(name), false);
    	searchResultsPage.getDocumentLinkByName(name).click();
        //searchResultsPage.waitForPageSourceChangedAfterClick(searchResultsPage.getDocumentLinkByName(name));
        searchResultsPage.waitForPageToLoadAndJQueryProcessing();
        breadCrumbPage.waitForBreadcrumbToBeDisplayed();
        standardDocumentPage.waitForDocumentTitle();
    }

  @When("^the user clicks on the '(.+)' link in the doc$")
    public void clickDocumentLink(String linkName) {
    	breadCrumbPage.waitForBreadcrumbToBeDisplayed();
        categoryPage.waitForPageSourceChangedAfterClick(categoryPage.getLinkInText(linkName));
        categoryPage.waitForPageToLoadAndJQueryProcessing();
        breadCrumbPage.waitForBreadcrumbToBeDisplayed();
    }

    @When("^the user clicks on the Related Content '(.+)' link in the doc$")
    public void clickRelatedContentDocumentLink(String linkName) {
        breadCrumbPage.waitForBreadcrumbToBeDisplayed();
        categoryPage.waitForPageSourceChangedAfterClick(categoryPage.getRelatedContentDocumentLinkInText(linkName));
        categoryPage.waitForPageToLoadAndJQueryProcessing();
        breadCrumbPage.waitForBreadcrumbToBeDisplayed();
    }
  
    @When("^the user clicks on the '(.+)' link in the breadcrumb$")
    public void clickLinkInTheBreadcrumb(String linkName) throws Throwable {
    	breadCrumbPage.waitForPageSourceChangedAfterClick(breadCrumbPage.breadCrumbLink(linkName));
        breadCrumbPage.waitForPageToLoadAndJQueryProcessing();
        breadCrumbPage.waitForBreadcrumbToBeDisplayed();
    }

    
    @When("^the user clicks on the home link in the breadcrumb$")
    public void clickHomeLinkInTheBreadcrumb() throws Throwable {
    	breadCrumbPage.waitForPageSourceChangedAfterClick(breadCrumbPage.breadcrumbHomeLink());
        breadCrumbPage.waitForPageToLoadAndJQueryProcessing();   
    }
    
    @When("^the user opens the document '(.+)' link$")
    public void openTheLastSearch(String number) {
    	researchOrganizerPage.waitForPageSourceChangedAfterClick(researchOrganizerPage.getLinkToDocumentAtRowPosition(number));
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        breadCrumbPage.waitForBreadcrumbToBeDisplayed();
    }
    @Then("^the user is presented with a page with title \"(.*?)\"$")
    public void theUserIsPresentedWithAPageWithTitle(String titleName) throws Throwable {
    	System.out.println("name" +titleName);
        System.out.println(" Captured Title is " + practicalLawHomepage.plcHeadingLabel().getText());
    	assertTrue("Page title is not correct",practicalLawHomepage.plcHeadingLabel().getText().equals(titleName));
    
    }

    @And("^the user adds the current document to new \"([^\"]*)\" folder with parent folder \"([^\"]*)\"$")
    public void addDocumentToFolderFromDocumentView(String folder, String parentFolder) throws Throwable {
    	 
    	documentDeliveryPage.clickOnAddToFolderLink();
        foldersUtils.saveToNewFolder(folder, parentFolder);
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }
    
  

    @And("^user store document's title and guid$")
    public void userStoreDocumentTitleAndGuid() {
        singleDocument = new Document();
        singleDocument.setTitle(standardDocumentPage.documentTitle().getText());
        singleDocument.setGuid(siteStructureUtils.getDocumentGUIDFromURL(getDriver().getCurrentUrl()));
    }
    @Then("^check document present in the \"([^\"]*)\" folder$")
  	public void checkDocumentPresent(String folder) throws Throwable {
  		assertTrue("Document is '" + singleDocument.getTitle() + "' absent", checkDocumentPresence(folder));
  	}
    
    private boolean checkDocumentPresence(String folder) {
		foldersUtils.openFolder(folder);
		researchOrganizerPage.waitForPageToLoad();
		System.out.println("GUID" +singleDocument.getGuid());
		System.out.println("TITLE" +singleDocument.getTitle());
		return researchOrganizerPage.linkToDocument(singleDocument.getGuid(), singleDocument.getTitle()).isDisplayed();
	}
  
    @When("^user clicks on document moved to folder$")
    public void userClicksOnDocumentMovedToFolder() throws Throwable {
        //researchOrganizerPage.waitForPageSourceChangedAfterClick(researchOrganizerPage.linkToDocument(singleDocument.getGuid(), singleDocument.getTitle()));
        researchOrganizerPage.linkToDocument(singleDocument.getGuid(), singleDocument.getTitle()).click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }
 
    @When("^the user clicks on the dropdown arrow at the end of the breadcrumb$")
    public void theUserClickOnBreadcrumbDropdown() throws Throwable {
 	     breadCrumbPage.waitForPageSourceChangedAfterClick(breadCrumbPage.breadCrumbDropdownArrow());
    	 breadCrumbPage.waitForPageToLoadAndJQueryProcessing();
    }
    @When("^the user clicks on a \"(.*)\" in the dropdown$")
    public void theUserCanClickDocument(String documentName) throws Throwable {
    	breadCrumbPage.breadCrumbDropdownDocument(documentName).click();
    	standardDocumentPage.waitForStartPageLoading();
    	standardDocumentPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^the user verifies a dropdown arrow at the end of the breadcrumb$")
    public void theUserVerifiesDropdownArrowAtTheEndOfTheBreadcrumb(){
        assertTrue("Breadcrumb dropdown arrow is not displayed", breadCrumbPage.isBreadCrumbDropdownArrowPresent());
    }

    @When("^the user clicks on the dropdown arrow$")
    public void theUserClicksOnTheDropdownArrow(){
    	breadCrumbPage.waitForPageSourceChangedAfterClick(breadCrumbPage.breadCrumbDropdownArrow());
        breadCrumbPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^the user verifies that the dropdown list is appeared$")
    public void theUserVerifiesThatTheDropdownListIsAppeared(){
        assertTrue("Breadcrumb dropdown list is not present", breadCrumbPage.isBreadCrumbDropdownListPresent());
    }
    @When("^the user views a list of the documents I viewed$")
    public void theUserCanSeeListOfDocuments(List<String> expectedInfoItemsNames) throws Throwable {
    	List<WebElement> infoItems = breadCrumbPage.breadCrumbDropdownList();
        List<String> infoItemsNames = commonMethods.getTextsFromWebElements(infoItems);
        assertTrue("Info items aren't ordered according to the list", expectedInfoItemsNames.equals(infoItemsNames));
    }
    
@When("^the user should not view the current document in the dropdown$")
public void theUserShouldNotViewCurrentDocument(List<String> expectedInfoItemsNames) throws Throwable 
{
	List<WebElement>Items = breadCrumbPage.breadCrumbDropdownList();
	List<String>infoItemsNames = commonMethods.getTextsFromWebElements(Items);
	assertFalse("Info items aren't ordered according to the list", expectedInfoItemsNames.equals(infoItemsNames));
	String text= breadCrumbPage.documentName().getText();
	System.out.println("TEXT" + text);
	assertFalse("Expected Text is present in the list",infoItemsNames.contains(text));
} 	

@When("^the user goes back in browser$")
  public void theUserClicksOnBackPage()
  {
   commonMethods.navigateBack();
  }
@When("^the user views The top list of the documents$")
public void theUserCanSeeTopListOfDocuments(List<String> expectedInfoItemsNames) throws Throwable {
	List<WebElement> infoItems = breadCrumbPage.breadCrumbDropdownList();
    List<String> infoItemsNames = commonMethods.getTextsFromWebElements(infoItems);
    System.out.println("Size" +infoItems.size());
    for(int i=0;i<infoItems.size();i++)
   {
    	String TopList=infoItemsNames.get(i);
   
        System.out.println("Top List" +TopList);
        assertTrue("Info items do match the first item in list", expectedInfoItemsNames.get(i).equals(TopList));
   }
      
    String firstItem=infoItemsNames.get(0).trim();
    System.out.println("first Item" +firstItem);
  
 String secondItem=infoItemsNames.get(1).trim();
    System.out.println("Second Item" +secondItem);
    System.out.println(expectedInfoItemsNames);
 System.out.println(expectedInfoItemsNames);
    assertTrue("Info items do match the first item in list", expectedInfoItemsNames.get(0).equals(firstItem));
    assertTrue("Info items do match the second item in list", expectedInfoItemsNames.get(1).equals(secondItem));
   
   
}

@Then("the user verifies, that breadcrumb is (displayed|closed)")
public void theUserVerifiesThatMyHomeLinkIs (KeyphraseState status){
    assertThat("Breadcrumb link is " + status.getPhrase(), breadCrumbPage.isBreadCrumbPresent(), Is.is(status.isTrue()));
}

@Then("^user clicks on View All button$")
public void theUserclicksonViewlAllbutton()
{
	
	assertTrue("ViewAll link is not present",breadCrumbPage.isViewAllLinkPresent());
	breadCrumbPage.ViewAllLinkPresent().click();


}


}
   
    

