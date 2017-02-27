package com.thomsonreuters.pageobjects.utils;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoutingPage extends AbstractPage {

	public WebElement showFeatureSelectionsLink() {
		return waitForExpectedElement(By.id("co_website_resourceInfoTypeLink"));
	}

	public boolean isHideFeatureSelectionsLinkPresent(){
		return isExists(By.xpath("//*[contains(text(), 'Hide Feature Selections')]"));
	}

	public WebElement practicalLawDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_PracticalLaw"));
	}

	public WebElement wlnByPass100KAncillaryDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_WLNByPass100KAncillary"));
	}

    public WebElement unreleasedCatPagesDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_UnreleasedCatPages"));
	}
    
    public WebElement blockWhatsMarketHongKongSearchDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_BlockWhatsMarketHongKongSearch"));
	}

	public WebElement smartBreadcrumbDisplay() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_SmartBreadcrumbDisplay"));
	}

	public WebElement preReleaseContentDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_PreReleaseContent"));
	}

	public WebElement enableAllContactsGroupDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_EnableAllContactsGroup"));
	}

	public WebElement linkbuilderDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_LinkBuilder"));
	}
	
	public WebElement novusStageDropdown() {
		return waitForExpectedElement(By
				.id("NovusStage"));
	}

	public WebElement ignoreAuthorizationBlocksDropdown() {
		return waitForElementVisible(findElement(By.id("co_website_resourceInfoTypes_IgnoreAuthorizationBlocks")),
				120);
	}

	public WebElement userInternalDropdown() {

		return waitForExpectedElement(By.xpath(".//*[@id='co_website_resourceInfoTypes_UserInternal']"));
	}

    public WebElement whatsMarketSearchResultsDropdown() {
        return waitForExpectedElement(By
                .id("co_website_resourceInfoTypes_WhatsMarketSearchResults"));
    }

	public WebElement askEditorDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_AskEditor"));
	}

	public WebElement firmStyleDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_FirmStyle"));
	}

	public WebElement socialPlatformDropdown() {
		return waitForExpectedElement(By
				.id("co_website_resourceInfoTypes_SocialPlatform"));
	}

	public WebElement isDataRoomRegionEnabledDropdown() {
		return waitForExpectedElement(By.id("DataRoomRegionEnabled"));
	}

	public WebElement skipAnonymousAuthenticationDropdown() {
		return waitForExpectedElement(By.id("SkipAnonymousAuthenticationKey"));
	}

	public WebElement anonymousRegistrationKeyTextBox() {
		return waitForExpectedElement(By.id("AnonymousRegistrationKey"));
	}

	public WebElement infrastructureAccessControls() {
		return waitForExpectedElement(By.id("InfrastructureAccessControls"));
	}

	public WebElement removedInfrastructureAccessControls() {
		return waitForExpectedElement(By.id("RemovedInfrastructureAccessControls"));
	}

	public WebElement saveChangesAndSignOnButton() {
		return waitForExpectedElement(By.id("coid_website_routingSaveButton"));
	}

	public WebElement infrastructureAccessTextArea() {
		return waitForElementVisible(By.id("InfrastructureAccessControls"));
	}

	public WebElement showFeatureSelectionLink() {
		return waitForElementVisible(By.id("co_website_resourceInfoTypeLink"));
	}

	public WebElement advisorTrialsDropdown() {
		return waitForElementVisible(By
				.id("co_website_resourceInfoTypes_AdvisorTrials"));
	}

	public WebElement webContentCollectionSetDropdown() {
		return waitForElementVisible(By.id("WebContentCollectionSet"));
	}

	public WebElement useContentReleaseControl() {
		return waitForElementVisible(By.id("UseContentReleaseControl"));
	}
	
	public WebElement useContentReleaseControlFalse() {
		return waitForElementVisible(By.xpath(".//*[@id='UseContentReleaseControl']/option[2]"));
	}
	
	public void setPMdDataVersion(String str) {
		waitForElementVisible(By.id("ProductMetadataDataVersion"))
				.sendKeys(str);
	}

	public void setCategoryPageCollectionSet(String collection) {
		waitForElementVisible(By.id("CategoryPageCollectionSet")).sendKeys(
				collection);
	}

	public WebElement firmStyleHost() {
		return waitForElementVisible(By.id("FirmStyle"));
	}

	public WebElement fastDraftHost() {
		return waitForElementVisible(By.id("FastDraft"));
	}

    public void selectToggleSupportedFeatures(){
        waitForExpectedElement(By.id("co_website_SupportedFeatures_button")).click();
    }

    public void selectSharedNotesCheckBox() {
        WebElement checkbox = waitForExpectedElement(By.id("co_SupportedFeature_SharedNotes"));
        if(!checkbox.isSelected()){
            checkbox.click();
        }
    }

    public WebElement userInternal() {
		return waitForElementVisible(By.id("co_website_resourceInfoTypes_UserInternal"));
	}

    public boolean isBlockShareNoteLinkPresent(){
		if(!isHideFeatureSelectionsLinkPresent()){
			showFeatureSelectionsLink().click();
		}
            return isElementDisplayed(By.id("co_website_resourceInfoTypes_BlockShareNoteLink"));
    }

    private WebElement getBlockShareNoteLink() {
       return waitForExpectedElement(By.id("co_website_resourceInfoTypes_BlockShareNoteLink"));
    }

	public WebElement AdestraUkWhatsMarketDropdown() {
		return waitForExpectedElement(By.id("co_website_resourceInfoTypes_AdestraUkWhatsMarket"));
	}
	
	public WebElement adestraHKWhatsMarketDropdown() {
		return waitForExpectedElement(By.id("co_website_resourceInfoTypes_AdestraWhatsMarketHongKong"));
	}

	public WebElement adestraAuEmploymentDropdown() {
		return waitForExpectedElement(By.id("co_website_resourceInfoTypes_AdestraAuEmployment"));
	}

	private List<String> getDropDownOptions(WebElement element){
        Select s = new Select(element);
        List<String> allOptions = new ArrayList<String>();
        try{
            for(WebElement webElement : s.getOptions()){
                allOptions.add(webElement.getText());
            }
        }catch(Exception e){}
        return allOptions;
    }

    public List<String> getFACDropdownOptionValues(String facName){
        if(facName.equals("BlockShareNoteLink")){
            return getDropDownOptions(getBlockShareNoteLink());
        }
        return Collections.EMPTY_LIST;
    }
    
	public WebElement searchTextArea() {
		return waitForExpectedElement(By.id("coid_website_routingTextbox_Search"));
	}
    
	public WebElement documentTextArea() {
		return waitForExpectedElement(By.id("coid_website_routingTextbox_Document"));
	}
	
	public WebElement documentGatherTextArea() {
		return waitForExpectedElement(By.id("coid_website_routingTextbox_DocumentGather"));
	}
	
	public WebElement gatherTextArea() {
		return waitForExpectedElement(By.id("coid_website_routingTextbox_Gather"));
	}
	
	public WebElement productMetadataTextArea() {
		return waitForExpectedElement(By.id("coid_website_routingTextbox_ProductMetadata"));
	}
	
	public WebElement udsTextArea() {
		return waitForExpectedElement(By.id("coid_website_routingTextbox_Uds"));
	}
	
	public WebElement getDropDownByLabel(String label){
		return waitForExpectedElement(By.xpath(String.format("//li[@id and contains(.,'%s')]//select[@id and @name]",label)));
	}
    

}
