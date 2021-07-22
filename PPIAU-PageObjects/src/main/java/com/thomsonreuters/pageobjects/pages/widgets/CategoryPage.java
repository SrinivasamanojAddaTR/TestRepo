package com.thomsonreuters.pageobjects.pages.widgets;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.folders.AddToFavouritesPopup;

public class CategoryPage extends AbstractPage {

	private AddToFavouritesPopup addToFavouritesPopup = new AddToFavouritesPopup();
    private CommonMethods comMethods = new CommonMethods();

	private WebElement activeElement;
	private LayoutGroup activeLayoutGroup;
	private int layoutGroupCount;
	private static final String LINK_TEXT_OF_CLAUSE_PATTERN = "//*[@id='co_body']//a[contains(.,%s)]";
	public enum Column {
		LEFT(By.id("coid_website_browseMainColumn")), MAIN(By.id("coid_website_browseMainColumn")), RIGHT(By
				.id("coid_website_browseRightColumn"));

		private By searchId;

		Column(By searchId) {
			this.searchId = searchId;
		}

		public By getSearchId() {
			return searchId;
		}
	}

	public enum LayoutGroup {
		NONE(""), TAB("div#coid_categoryBoxTabs ul#coid_categoryTabs li#coid_categoryTab%d"), ACCORDION(
				"ul#coid_accordion li#coid_accordionBox%d");

		private String searchBy;

		LayoutGroup(String searchBy) {
			this.searchBy = searchBy;
		}

		public By getSearchBy(int count) {
			return By.cssSelector(String.format(searchBy, count));
		}

		public By getSubPanel(LayoutGroup activeLayoutGroup, int layoutGroupCount, int count) {
			if (activeLayoutGroup == LayoutGroup.TAB) {
				return By.cssSelector(String.format("div#coid_categoryBoxTabPanel%d div#coid_categoryBoxTab%dSubPanel%d", layoutGroupCount,
						layoutGroupCount, count));
			} else if (activeLayoutGroup == LayoutGroup.ACCORDION) {
				return By.cssSelector(String.format("div#coid_accordionBoxPanel%d div#coid_accordionBox%dSubPanel%d", layoutGroupCount,
						layoutGroupCount, count));
			} else {
				return null;
			}
		}
	}

	private static final String CSS_TO_TAB_FROM_PANEL = "div#coid_categoryBoxTabs ul#coid_categoryTabs li#coid_categoryTab%d a.co_tabLink";
	private static final String SUB_PANEL_CONTENT = "div.co_genericBoxContent";
	private static final String ADD_TO_FAVORITES = "//*[@id='co_foldering_categoryPage' and @class='co_website_browsePageAddToFavorites']";
	private static final String EDIT_FAVORITES = "//*[@id='co_foldering_categoryPage' and @class='co_website_browsePageEditFavorites']";
	private static final String MAKE_THIS_MY_START_PAGE = "//*[@class='co_website_browsePageAddHomepage']";
	private static final String REMOVE_THIS_MY_START_PAGE = "//*[@class='co_website_browsePageRemoveAsHomepage']";
	private static final String SUB_PANEL_PLACEHOLDER_TEXT = "//div[@class='co_genericBoxContent']//p";

	public WebElement subPanelContent() {
		return waitForExpectedElement(By.cssSelector(SUB_PANEL_CONTENT));
	}
	
	
	public boolean isSubPanelPlaceholderTextDisplayed(String text) {
		return isElementDisplayed(By.xpath("//div[@class='co_genericBoxContent']/p[contains(text(),'" + text + "')]"));
	}
	
	public WebElement subPanelPlaceholderText() {
		return waitForExpectedElement(By.xpath(SUB_PANEL_PLACEHOLDER_TEXT));
	}
	
    public boolean isSectionPresent(String section) {
        return isElementDisplayed(By.xpath("//h3/span[contains(text(), '" + section + "')]"));
    }

	public void setActiveElement(Column column, LayoutGroup layoutGroup, int count) {

		activeLayoutGroup = layoutGroup;
		layoutGroupCount = count;

		if (LayoutGroup.NONE == layoutGroup) {
			activeElement = findElement(column.getSearchId());
		} else {
			activeElement = findElement(column.getSearchId()).findElement(layoutGroup.getSearchBy(count));
		}
	}

	public void navigateToActiveElement() {
		activeElement.click();
	}

	public WebElement tabLink(Column column, int tabNo) {
		return findElement(column.getSearchId()).findElement(By.cssSelector(String.format(CSS_TO_TAB_FROM_PANEL, tabNo)));
	}

	private WebElement subPanelOnTab(int tabNo, int subPanelNo) {
		return findElement(By.id("coid_categoryBoxTabPanel" + tabNo))
				.findElement(By.id("coid_categoryBoxTab" + tabNo + "SubPanel" + subPanelNo));
	}

	public WebElement subPanelHeadingOnTab(int tabNo, int subPanelNo) {
		return subPanelOnTab(tabNo, subPanelNo).findElement(By.className("co_genericBoxHeader"));
	}

	public List<WebElement> subPanelContentOnTab(int tabNo, int subPanelNo) {
		return subPanelOnTab(tabNo, subPanelNo).findElements(By.className("co_searchContent"));
	}

	public WebElement activeSubPanelHeading(int count) {
		return findElement(activeLayoutGroup.getSubPanel(activeLayoutGroup, layoutGroupCount, count)).findElement(
				By.className("co_genericBoxHeader"));
	}

	public List<WebElement> activeSubPanelContent(int count) {
		return findElement(activeLayoutGroup.getSubPanel(activeLayoutGroup, layoutGroupCount, count)).findElements(
				By.className("co_searchContent"));
	}

	public void addToFavourites(String groupName) {
		LOG.info("Add the Category page to {} favourites group", groupName);
		comMethods.clickElementUsingJS(waitForExpectedElement(By.xpath("//*[@id='co_foldering_categoryPage']")));
		waitForPageToLoad();
		waitForPageToLoadAndJQueryProcessing();
		selectFavouritesGroup(groupName);
        addToFavouritesPopup.save().click();
		waitForPageToLoad();
	}
	
	public void removeFromFavourites(String groupName) {
		LOG.info("Remove the Category page from '{} favorites group.", groupName);
		waitForExpectedElement(By.xpath(EDIT_FAVORITES)).click();
		waitForPageToLoad();
		deSelectFavouritesGroup(groupName);
        addToFavouritesPopup.save().click();
		waitForPageToLoad();
	}

	public void selectFavouritesGroup(String groupName) {
		if (!addToFavouritesPopup.isGroupPresent(groupName)) {
			addToFavouritesPopup.createGroupLink().click();
			addToFavouritesPopup.waitForPageToLoad();
			addToFavouritesPopup.newGroupInput().sendKeys(groupName);
			addToFavouritesPopup.waitForPageToLoad();
			addToFavouritesPopup.createGroupButton().click();
			addToFavouritesPopup.waitForPageToLoad();
		}
		WebElement groupCheckbox = addToFavouritesPopup.groupCheckbox(groupName);
		if (!groupCheckbox.isSelected()) {
			groupCheckbox.click();
		}
		addToFavouritesPopup.waitForPageToLoad();
	}
	
	public void deSelectFavouritesGroup(String groupName) {
		WebElement groupCheckbox = addToFavouritesPopup.groupCheckbox(groupName);
		if (groupCheckbox.isSelected()) {
			groupCheckbox.click();
		}
		addToFavouritesPopup.waitForPageToLoad();
	}

	public void checkPageIsNotInFavourites() {
		LOG.info("Check page is not in Favourites");
		waitForPageToLoad();
		findElement(By.xpath(ADD_TO_FAVORITES));
	}

	public void makeThisMyStartPage() {
		LOG.info("Make the Category page as My Start Page");
		waitForPageToLoad();
		waitForPageToLoadAndJQueryProcessing();
		addToFavouritesPopup.waitForExpectedElement(By.xpath(MAKE_THIS_MY_START_PAGE)).click();
		waitForPageToLoad();
		addToFavouritesPopup.waitForExpectedElement(By.xpath(REMOVE_THIS_MY_START_PAGE));
	}

	public void openPageByText(String pageName) {
		waitForPageToLoadAndJQueryProcessing();
		LOG.info("Open page '{}'", pageName);
		waitForPageToLoadAndJQueryProcessing();
		String text = Quotes.escape(pageName);
		scrollIntoViewAndClick(waitForExpectedElement(By.xpath(String.format(LINK_TEXT_OF_CLAUSE_PATTERN, text))));
		waitForPageToLoadAndJQueryProcessing();
	}
	
	public void openPageByTextFromResources(String pageName) {
		LOG.info("Open page '{}'", pageName);
		waitForPageToLoadAndJQueryProcessing();
		String text = Quotes.escape(pageName);
		List<WebElement> documentLinks=findElements(By.xpath("//*[@id='co_body']//a[text()=" + text + "]"));
		scrollIntoViewAndClick(documentLinks.get(0));
		waitForPageToLoadAndJQueryProcessing();
	}

	public WebElement addToFavoritesLink() {
		return waitForExpectedElement(By.xpath(ADD_TO_FAVORITES), 10);
	}
	
	public WebElement openLinkByName(String text){
		return waitForExpectedElement(By.xpath("//*[@id='co_body']//a[text()='" + text + "']"));
	}

    public Boolean addToFavouritesLinkPresent() {
    	return isExists(By.xpath(ADD_TO_FAVORITES));
    }
    
	public boolean checkPageIsInFavourites() {
		waitForPageToLoad();
		return isExists(By.xpath(EDIT_FAVORITES));
	}

	public WebElement makeThisMyStartPageLink() {
		return addToFavouritesPopup.waitForExpectedElement(By.xpath(MAKE_THIS_MY_START_PAGE), 10);
	}

	public Boolean makeThisMyStartPageLinkPresent() {
		return isElementDisplayed(By.xpath(MAKE_THIS_MY_START_PAGE));
	}

	public void openTab(String tabName) {
		LOG.info("Open tab '{}'", tabName);
		waitForPageToLoad();
		String text = "'" + tabName + "'";
		if (tabName.contains("'")) {
			tabName = "\"" + tabName + "\"";
			text = tabName;
		}
		waitForExpectedElement(By.xpath("//*[@class='co_tabLink' and text()=" + text + "]")).click();
		waitForPageToLoad();
	}

	public WebElement formE() {
		return comMethods.waitForElementToBeVisible(By.xpath("//*[contains(@href,'/About/FormE')]"));
	}

	public void removeThisAsMyStartPage() {
		LOG.info("Remove the Category page as My Start Page");
		waitForPageToLoadAndJQueryProcessing();
		waitForExpectedElement(By.xpath(REMOVE_THIS_MY_START_PAGE)).click();
		waitForPageToLoadAndJQueryProcessing();
		waitForExpectedElement(By.xpath(MAKE_THIS_MY_START_PAGE));
	}

	public WebElement getLinkInText(String linkName){
        String text = Quotes.escape(linkName);
        return waitForExpectedElement(By.xpath("//*[@id='co_docContentBody']//a[.=" + text + "]"));
    }

    public WebElement getRelatedContentDocumentLinkInText(String linkName){
		String text = Quotes.escape(linkName);
		return waitForExpectedElement(By.xpath("//*[@id='co_docContentBody']//a[contains(.," + text + ") and contains(@href,'Document')]"));
	}
}