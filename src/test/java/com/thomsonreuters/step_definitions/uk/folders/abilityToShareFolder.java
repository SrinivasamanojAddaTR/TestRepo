package com.thomsonreuters.step_definitions.uk.folders;

import com.thomsonreuters.pageobjects.pages.folders.*;
import com.thomsonreuters.pageobjects.utils.email.EmailMessageUtils;
import com.thomsonreuters.pageobjects.utils.email.Mailbox;
import com.thomsonreuters.pageobjects.utils.email.MailboxFactory;
import com.thomsonreuters.pageobjects.utils.email.MailinatorMethods;
import com.thomsonreuters.pageobjects.utils.folders.FoldersUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;

import javax.mail.Message;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class abilityToShareFolder extends BaseStepDef {

    private ResearchOrganizerPage researchOrganizerPage = new ResearchOrganizerPage();
    private BaseFoldersBehaviour baseFoldersBehavior = new BaseFoldersBehaviour();
    private ShareFolderPopup shareFolderPopup = new ShareFolderPopup();
    private MailinatorMethods mailinatorMethods = new MailinatorMethods();
    private ShareFolderContactsPopup shareFolderContactsPopup = new ShareFolderContactsPopup();
    private ShareFolderRolesPopup shareFolderRolesPopup = new ShareFolderRolesPopup();
    private FoldersUtils foldersUtils = new FoldersUtils();
    private NewFolderPopup newFolderPopup = new NewFolderPopup();
    private abilityToCreateFolder abilityToCreateFolder = new abilityToCreateFolder();
	private EmailMessageUtils emailMessageUtils = new EmailMessageUtils();

	private String EMAIL_SUBJECT = "would like to share folders with you";

    @When("^the user '(.+)' share the folder \"([^\"]*)\" with the user '(.+)' via email$")
    public void shareFolderViaEmail(String owner, String folderName, String userNameToShare) throws Throwable {
    	foldersUtils.shareFolderViaEmail(owner, folderName, userNameToShare);
    }

	@When("^the user \"(.*?)\" shares the folder \"([^\"]*)\" with the email \"(.*?)\"")
	public void shareFolderViaEmailByEmail(String owner, String folderName, String emailToShare) throws Throwable {
		foldersUtils.shareFolderViaEmailByEmail(owner, folderName, emailToShare);
	}

	@Then("^invitation email is received at \"(.*?)\" with link to PL(AU|UK)$")
	public void emailIsReceivedWithLink(String email, String country) throws Throwable {
		Mailbox mailbox = MailboxFactory.getMailboxByEmail(email);
		Message message = mailbox.waitForMessageWithTitle(EMAIL_SUBJECT, 120, 10);
		String expectedUrl = country.toLowerCase() + ".practicallaw." + System.getProperty("base.url") + ".thomsonreuters.com";

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(emailMessageUtils.isEmailContainsText(message, expectedUrl))
				.overridingErrorMessage("Email does not contain expected link: %s", expectedUrl).isTrue();
		softly.assertAll();
	}

    @When("^the user unshare the folder \"([^\"]*)\"$")
    public void unshareFolder(String folderName) throws Throwable {
    	foldersUtils.shareFolder(folderName);
        shareFolderPopup.endShareFolder().click();
        shareFolderPopup.endSharingConfirmation().click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("^the user '(.+)' recieve invitation from '(.+)' in specified email inbox$")
    public void checkFolderSharingInvitation(String userName, String sender) throws Throwable {
        mailinatorMethods.navigateToMailinatorSite();
		assertTrue("Email has not been recieved ", mailinatorMethods.openEmailBoxAndcheckUserMail(userName, sender, EMAIL_SUBJECT));
    }

    @When("the user share the folder \"([^\"]*)\" with contact '(.+)' as \"([^\"]*)\" and contact '(.+)' as \"([^\"]*)\"")
    public void shareFolderWithContact(String folderName, String contactNameToShare, String role, String contactNameToShare2, String role2) throws Throwable {
    	foldersUtils.shareFolder(folderName);
        shareFolderPopup.contacts().click();
        shareFolderContactsPopup.contact(contactNameToShare).click();
        shareFolderContactsPopup.contact(contactNameToShare2).click();
        shareFolderContactsPopup.insert().click();
        shareFolderPopup.continueButton().click();
        shareFolderContactsPopup.selectDropDownByVisibleText(shareFolderContactsPopup.selectRole(contactNameToShare), role);
        shareFolderContactsPopup.selectDropDownByVisibleText(shareFolderContactsPopup.selectRole(contactNameToShare2), role2);
        shareFolderRolesPopup.shareButton().click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }
    
    @When("the user share the folder \"([^\"]*)\" (with|without) subfolders with contact '(.+)' as \"([^\"]*)\"")
    public void shareFolderWithOneContact(String folderName, String shareSubfoldersAction, String contactNameToShare, String role) throws Throwable {
		foldersUtils.shareFolder(folderName);
		shareFolderPopup.contacts().click();
		shareFolderContactsPopup.contact(contactNameToShare).click();
		shareFolderContactsPopup.insert().click();
		if ("without".equals(shareSubfoldersAction)) {
			shareFolderContactsPopup.shareSubFoldersCheckBox().click();
		}
		shareFolderPopup.continueButton().click();
		shareFolderContactsPopup.selectDropDownByVisibleText(shareFolderContactsPopup.selectRole(contactNameToShare), role);
		shareFolderRolesPopup.shareButton().click();
		researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }
    
    @When("the user adds contact '(.+)' as \"([^\"]*)\" to shared folder \"([^\"]*)\" (with|without) subfolders")
    public void userAddsContactToSharedFolder(String contactNameToShare, String role, String folderName, String shareSubfoldersAction) throws Throwable {
		foldersUtils.shareFolder(folderName);
		if ("without".equals(shareSubfoldersAction)) {
			shareFolderContactsPopup.shareSubFoldersCheckBox().click();
		}
		shareFolderPopup.add().click();
		shareFolderPopup.contacts().click();
		shareFolderContactsPopup.contact(contactNameToShare).click();
		shareFolderContactsPopup.insert().click();
		shareFolderPopup.continueButton().click();
		shareFolderContactsPopup.selectDropDownByVisibleText(shareFolderContactsPopup.selectRoleOnAddPeopleGroupsForm(contactNameToShare), role);
		shareFolderRolesPopup.addButton().click();
		researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
		closeSharePopup();
    }
    
    @When("the user removes contact '(.+)' from shared folder \"([^\"]*)\"")
    public void userRemovesContactFromSharedFolder(String contactNameToShare, String folderName) throws Throwable {
		foldersUtils.shareFolder(folderName);
		shareFolderPopup.deleteContact(contactNameToShare).click();
		researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
		closeSharePopup();
    }

    @Then("new message about the folder \"([^\"]*)\" sharing is displayed")
    public void checkFolderIsNowSharedMessage(String folderName) throws Throwable {
        researchOrganizerPage.checkFolderIsNowSharedMessage(folderName);
    }

    @Then("new message about the folder \"([^\"]*)\" is no longer shared")
    public void checkFolderIsNoLongerSharedMessage(String folderName) throws Throwable {
        researchOrganizerPage.checkFolderIsNoLongerSharedMessage(folderName);
    }

    @Then("the user closes share folder popup")
    public void closeSharePopup() throws Throwable {
        shareFolderPopup.closeFolderPopUp().click();
        shareFolderPopup.waitForPageToLoadAndJQueryProcessing();
    }

    @Then("the folder \"([^\"]*)\" appears in shared folders")
    public void checkFolderInSharedFolders(String folderName) throws Throwable {
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        if (!researchOrganizerPage.isFolderPresentInSharedFolders(folderName)) {
            throw new RuntimeException("Folder '" + folderName + "' is absent in Shared with me folders");
        }
    }
    
    @Then("user is (not able|able) to expand shared folder \"([^\"]*)\"")
    public void checkUserIsNotAbleToExpandSharedFolder(String isUserAbleToExpandFolderAction,String folderName) throws Throwable {
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        if("not able".equals(isUserAbleToExpandFolderAction)) {
        	assertFalse("User is able to expand shared folder: " + folderName, researchOrganizerPage.isUserAbleToExpandSharedFolder(folderName));
        } else if("able".equals(isUserAbleToExpandFolderAction)) {
        	researchOrganizerPage.expandSharedFolder(folderName).click();
        	researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        	checkFolderInSharedFolders(folderName);
        }
    }
    
    @Then("^user with role \"(.*?)\" is (not able|able) to create new folder \"(.*?)\" in shared folder \"(.*?)\"$")
    public void abilityToCreateNewFolderInSharedFolder(String userRole, String isUserAbleToCreateNewFolderAction,String newFolderName, String folderName) throws Throwable {
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        SoftAssertions softly = new SoftAssertions();
        if("not able".equals(isUserAbleToCreateNewFolderAction)) {
        	foldersUtils.openFolder(folderName);
            researchOrganizerPage.createNewFolderButton().click();
            newFolderPopup.waitForPageToLoad();
            newFolderPopup.waitForPageToLoadAndJQueryProcessing();
            softly.assertThat(newFolderPopup.save().getAttribute("disabled").equals("true")).overridingErrorMessage("Save button is not disabled for user role <%s> and user is able to create new folder", userRole).isTrue();
            softly.assertThat(newFolderPopup.folderRoleInformation().getText().contains(userRole)).overridingErrorMessage("User role is incorrect in information message").isTrue();
            newFolderPopup.clickCancel().click();
        } else if("able".equals(isUserAbleToCreateNewFolderAction)) {
        	foldersUtils.openFolder(folderName);
            researchOrganizerPage.createNewFolderButton().click();
            newFolderPopup.waitForPageToLoad();
            newFolderPopup.waitForPageToLoadAndJQueryProcessing();
            softly.assertThat(newFolderPopup.folderRoleInformation().getText().contains(userRole)).overridingErrorMessage("User role is incorrect in information message").isTrue();
            baseFoldersBehavior.createNewFolder(newFolderName, folderName);
            abilityToCreateFolder.checkFolderPresent(newFolderName,folderName);
        }
        
        softly.assertAll();
    }
    
    @When("the user changes contact '(.+)' permissions to \"([^\"]*)\" for shared folder \"([^\"]*)\"")
    public void userChangesPermissionsForSharedFolder(String contactNameToShare, String role, String folderName) throws Throwable {
		foldersUtils.shareFolder(folderName);
		shareFolderContactsPopup.selectDropDownByVisibleText(shareFolderPopup.contactRoleSelect(contactNameToShare), role);
		researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
		closeSharePopup();
    }

    @Then("the folder \"([^\"]*)\" is absent in shared folders")
    public void checkFolderIsAbsentInSharedFolders(String folderName) throws Throwable {
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
        if (researchOrganizerPage.isFolderPresentInSharedFolders(folderName)) {
            throw new RuntimeException("Folder '" + folderName + "' presents in Shared with me folders");
        }
    }
    
    @When("^the user share the folder \"([^\"]*)\" with new group \"([^\"]*)\" and member '(.+)' as \"([^\"]*)\"$")
    public void shareFolderViaEmail(String folderName, String groupName, String member, String role) throws Throwable {
    	foldersUtils.shareFolder(folderName);
        shareFolderPopup.contacts().click();
        shareFolderPopup.waitForPageToLoadAndJQueryProcessing();
        shareFolderPopup.newGroup().click();
        shareFolderPopup.waitForPageToLoadAndJQueryProcessing();
        foldersUtils.createNewGroupToShareFolder(groupName, member);
        shareFolderContactsPopup.waitForPageToLoadAndJQueryProcessing();
        shareFolderContactsPopup.waitForPageToLoad();
        shareFolderContactsPopup.group(groupName).click();
        shareFolderContactsPopup.insert().click();
        shareFolderContactsPopup.waitForPageToLoadAndJQueryProcessing();
        shareFolderPopup.continueButton().click();
        shareFolderContactsPopup.selectDropDownByVisibleText(shareFolderContactsPopup.selectRole(groupName), role);
        shareFolderRolesPopup.shareButton().click();
        researchOrganizerPage.waitForPageToLoadAndJQueryProcessing();
    }

}