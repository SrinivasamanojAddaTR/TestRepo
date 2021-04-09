package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.pageobjects.pages.common.CommonMailinatorPage;
import org.openqa.selenium.WebElement;

import static junit.framework.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

/**
 * Created by Ian Hudson uc087619 on 16/03/2016.
 */
public class MailinatorMethods {

    private CommonMailinatorPage commonMailinatorPage = new CommonMailinatorPage();

    public static final String MAILINATOR_URL = "https://mailinator.com/";
    public static final String ACCOUNT_URL = "/inbox2.jsp?public_to=";

    List<WebElement> emailList;

    public void navigatesToMailinatorEmail(
            String emailURL) {
        commonMailinatorPage.navigate(MAILINATOR_URL + ACCOUNT_URL + emailURL + "#/#public_maildirdiv");
    }

    public void validateMoreThanEmails(Integer emailResultsExpected) {
        Integer emailCount = commonMailinatorPage.displayedEmailCount();
        assertTrue(emailCount >= emailResultsExpected);
    }

    public void validateMostRecentEmailSubject(
            String emailSubject) {
        String onclickId = commonMailinatorPage.firstEmailOnclickId();
        assertTrue(commonMailinatorPage.emailListSubjectOnclick(onclickId).getText().contains(emailSubject));
    }

    public void clickMostRecentEmail() {
        String onclickId = commonMailinatorPage.firstEmailOnclickId();
        commonMailinatorPage.emailListSubjectOnclick(onclickId).click();
    }

    public void validateEmailIsTo(String emailTo) {
        assertTrue(commonMailinatorPage.emailDisplayTo().getText().contains(emailTo));
    }

    public void validateEmailIsFrom(String fromEmail) {
        //Get e-mail and strip the whitespace
        String fromEmailDisplayed = commonMailinatorPage.emailDisplayFrom().getText().replaceAll("/[^A-Za-z0-9 ]/", "");
        assertTrue(fromEmailDisplayed.equals(fromEmail));
    }

    public void validateEmailSubjectIs(
            String subject) {
        assertTrue(commonMailinatorPage.emailDisplaySubject().getText().contains(subject));
    }

    public void validateEmailContainsText(String emailText) {
        assertTrue(commonMailinatorPage.getEmailText().contains(emailText));
    }

    public void validateEmailContainsLink(String emailLink) {
        // Switch to the e-mail frame to be able to read the e-mail
        commonMailinatorPage.switchToIframe(commonMailinatorPage.emailMainTextFrame());
        commonMailinatorPage.emailMainTextLink(emailLink).isDisplayed();
        // Switch back to the full web page
        commonMailinatorPage.switchToMainWindow();
    }

    public void validateEmailContainsLinkWithTitle(
            String linkTitle,
            String emailLink) {
        // Switch to the e-mail frame to be able to read the e-mail
        commonMailinatorPage.switchToIframe(commonMailinatorPage.emailMainTextFrame());
        WebElement elementLink = commonMailinatorPage.emailMainTextLink(emailLink);
        elementLink.isDisplayed();
        assertTrue(elementLink.getText().equals(linkTitle));
        // Switch back to the full web page
        commonMailinatorPage.switchToMainWindow();
    }

    public String generatesUniqueEmailAddress() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^A-Za-z0-9]", "");
        return uuid.substring(0, Math.min(uuid.length(), 25));
    }

}
