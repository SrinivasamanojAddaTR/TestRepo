package com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources;

import com.thomsonreuters.driver.exception.PageOperationException;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;


public class KHResourcePage extends CommonResourcePage {
	
	private final static By SIGN_IN_LINK_LOCATOR = By.xpath("//div[@id='co_loggedOutContentLogin']//a[text()='Sign in']");
	private final static By REQUEST_A_FREE_TRIAL_LOCATOR = By.xpath("//div[@id='co_loggedOutContentRight']//a[text()='Request a free trial']");
	private final static By REGISTER_FOR_FREE_ACCESS_LOCATOR = By.linkText("Request a free trial");

    public WebElement whatsMarketLastViewedTag() {
        return findElement(By.id("co_lastViewInfo"));
    }

    public WebElement firstCollapsedDraftingNotes() {
        return findElement(By.cssSelector("#co_docContentBody>div.co_notesIcon"));
    }

    public WebElement activeDraftingNoteHeading() {
        return findElement(By.cssSelector("#co_docContentBody>div.co_notesContent.kh_box.is-active>h2"));
    }

    public WebElement closeDraftingNote() {
        return findElement(By.cssSelector("#co_docContentBody>div.co_notesContent.kh_box.is-active a.close-btn>span.icon-cross"));
    }

    public boolean isThereAnyExpandedDraftingNote() {
        return findElements(By.cssSelector("#co_docContentBody>div.co_notesContent.kh_box.is-active")).size() > 0;
    }

    public WebElement backToTop() {
        return waitForExpectedElement(By.cssSelector(".kh_icon.icon-up-pointer"));
    }

    public WebElement relatedContentSection() {
        return findElement(By.id("co_relatedContent"));
    }

    public List<WebElement> visibleResourceHistoryList() {
        List<WebElement> allLinks = findElements(By.cssSelector(".co_docRevHisoryDetail ul li"));
        List<WebElement> visibleLinks = new ArrayList<>();
        for (WebElement link : allLinks) {
            if (!link.getAttribute("class").equalsIgnoreCase("NoShow")) {
                visibleLinks.add(link);
            }
        }
        return visibleLinks;
    }

    public WebElement viewAllAndLatestResourceHistoryLink() {
        return findElement(By.id("co_docContentResourceHistoryAllButton"));
    }

    public Boolean viewAllResourceHistoryLink() {
        return findElements(By.id("co_docContentResourceHistoryAllButton")).size() > 0;
    }

    public WebElement showHideIndividualCasesLink(String linkName) {
        return findElement(By.linkText(linkName));
    }
    
    public void openLinkInNewTab(String linkName){
        WebElement link = getDriver.findElement(By.linkText(linkName));
        //Open the link in new tab
        link.sendKeys(Keys.CONTROL, Keys.ENTER);
    }


    public List<WebElement> visibleEmbeddedResources() {
        List<WebElement> allResources = findElements(By.xpath("//div[@id='co_docContentBody']//div[@class='kh_division co_embeddedResourceCol']"));
        List<WebElement> visibleResources = new ArrayList<>();
        for (WebElement link : allResources) {
            if (link.isDisplayed()) {
                visibleResources.add(link);
            }
        }
        return visibleResources;
    }

    public WebElement embeddedResourceLink(String linkName) {
        return waitForExpectedElement(By.linkText(linkName));
    }

    public WebElement signInLink() {
        return waitForExpectedElement(SIGN_IN_LINK_LOCATOR);
    }
    
    public boolean isSignInLinkDisplayed() {
        return isElementDisplayed(SIGN_IN_LINK_LOCATOR);
    }

    public WebElement requestAFreeTrial() {
    	return waitForExpectedElement(REQUEST_A_FREE_TRIAL_LOCATOR);
    }
    
    public boolean isRequestAFreeTrialDisplayed() {
        return isElementDisplayed(REQUEST_A_FREE_TRIAL_LOCATOR);
    }    
        
    public WebElement upgradeText() {
        return waitForExpectedElement(By.id("co_unsubscribedContentUpgrade"));
    }

    public WebElement registerForFreeAccess() {
        return findElement(REGISTER_FOR_FREE_ACCESS_LOCATOR);
    }
    
    public boolean isRegisterForFreeAccess() {
        return isElementDisplayed(REGISTER_FOR_FREE_ACCESS_LOCATOR);
    }

    public void clickOnSuspendBillingContinueButton() {
        try {
            waitForExpectedElement(By.id("co_suspendBillingContinueButton")).click();
        } catch (TimeoutException te) {
            LOG.info("Session pause button is no more visible");
        }
    }

    public void selectShowAndHideDraftingNotesLink() {
        try {
            waitForExpectedElement(By.cssSelector("#co_AnchorToggleDraftingNotesWidget"), 10).click();
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the Show And Hide Drafting Notes link" + te.getMessage());
        }
    }

    public List<String> getNotesOptions() {
        List<String> options = new ArrayList<String>();
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector("#co_draftingNotesOptionsModalContainer li a"))) {
                options.add(element.getText());
            }
        } catch (TimeoutException te) {
        }
        return options;
    }

    public void selectOptionFromDraftingNotes(DraftingNotes showAndHideNotesOptions) {
        if (!isDraftingNotesOptionsDisplayed()) {
            selectShowAndHideDraftingNotesLink();
        }
        try {
            waitForExpectedElement(By.xpath("//div[@id='co_draftingNotesOptionsModalContainerInner']//a[text()='" + showAndHideNotesOptions.getOptionName() + "']")).click();
        } catch (TimeoutException te) {
            throw new PageOperationException("Exceeded time to find the drafting Notes option : " + showAndHideNotesOptions + " : " + te.getMessage());
        }
    }

    public boolean isDraftingNotesOptionsDisplayed() {
        try {
            String notes = waitForExpectedElement(By.id("co_draftingNotesOptionsModalContainerInner")).getAttribute("class");
            if (notes.equals("")) {
                return true;
            } else {
                return false;
            }
        } catch (TimeoutException te) {
        }
        return false;
    }

    public int getNotesIconsCount() {
        int count = 0;
        try {
            count = waitForExpectedElements(By.cssSelector(".co_notesIcon")).size();
        } catch (TimeoutException te) {
        }
        return count;
    }

    public int getDisplayedNotesCount() {
        int count = 0;
        try {
            count = waitForExpectedElements(By.cssSelector(".co_notesContent.kh_box.is-active"), 10).size();
        } catch (TimeoutException te) {
        }
        return count;
    }

    public boolean isContentParagraphsDisplayed() {
        try {
            for (WebElement element : waitForExpectedElements(By.cssSelector(".co_paragraphText.co_indentParagraph"), 10)) {
                if (element.isDisplayed()) {
                    return true;
                }
            }
        } catch (TimeoutException te) {
        }
        return false;
    }

    public WebElement draftingNotesDeliveryIcon() {
        return waitForExpectedElement(By.id("co_AnchorToggleDraftingNotesWidget"));
    }

    public boolean isDraftingNotesDeliveryIconExist() {
        return isExists(By.id("co_AnchorToggleDraftingNotesWidget"));
    }

    public List<WebElement> collapsedDraftingNotesList() {
        return waitForExpectedElements(By.className("co_notesIcon"));
    }

    public List<WebElement> expandedDraftingNotesList() {
        return waitForExpectedElements(By.xpath("//div[contains(@id,'co_anchor') and contains(@class,'is-active')]"));
    }

    public WebElement draftingNoteCloseIconByTitle(String title) {
        return waitForExpectedElement(By.xpath("//div[contains(@class,'kh_box is-active')]//h2[text()='" + title + "']/..//span[@class='kh_icon icon-cross']"));
    }

    public WebElement collapsedDraftingNoteTitle(String title) {
        return waitForExpectedElement(By.xpath("//div[@class='co_notesIcon']//span[@class='co_blueTxt' and text()='"+title+"']"));
    }

    public WebElement expandedDraftingNoteTitle(String title) {
        return waitForExpectedElement(By.xpath("//div[contains(@class,'kh_box is-active')]//h2[text()='"+title+"']"));
    }

    public WebElement jumplink(String anchor) {
        return findElement(jumplinkLocator(anchor));
    }
    
    public By jumplinkLocator(String anchor) {
        return By.xpath("//*[@id='co_anchor_"+anchor+"']");
    }

    public WebElement showHidePopupSelectedOption() {
        return waitForExpectedElement(By.xpath("//li[contains(@id,'co_draftingNotes') and @class='co_active']//a"));
    }

    public WebElement tocCrossIcon() {
        return waitForExpectedElement(By.xpath("//div[@id='kh_tocContainer']//a[contains(@class,'icon-cross')]"));
    }

    public WebElement tocMenuTogglCollapsedButton() {
        return waitForElementPresent(By.xpath("//div[@id='kh_tocContainer']//a[@class='menu-toggle']"));
    }

    public WebElement tocHiddenTitle() {
        return waitForElementPresent(By.xpath("//div[@id='kh_tocContainer']//span[@class='co_hideState' and text()='Table of Contents']"));
    }

    public WebElement sectionOfDocument(){
		return findElement(By.xpath("//a[@id='co_anchor_a440005']/parent::div"));
	}

	public void scrollIntoViewOfSectionOfDocument(WebElement element) {
		scrollIntoView(element, true);
		((JavascriptExecutor) getDriver).executeScript("arguments[0].scrollIntoView({block: 'start'})", element);
	}
}
