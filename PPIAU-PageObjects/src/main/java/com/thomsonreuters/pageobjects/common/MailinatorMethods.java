package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.pages.common.CommonMailinatorPage;
import com.thomsonreuters.pageobjects.pages.mailinator.MailinatorPage;
import org.openqa.selenium.WebElement;
import static junit.framework.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

/**
 * Created by Ian Hudson uc087619 on 16/03/2016.
 */
public class MailinatorMethods {

    private CommonMailinatorPage commonMailinatorPage = new CommonMailinatorPage();

    public static String mailinatorURL = "https://mailinator.com/";
    public static String accountURL = "/inbox2.jsp?public_to=";

    List<WebElement> emailList;

    public void navigatesToMailinatorEmail(
        String emailURL) throws Throwable {
        commonMailinatorPage.navigate(mailinatorURL + accountURL + emailURL + "#/#public_maildirdiv");
    }

    public void validateMoreThanEmails(Integer emailResultsExpected) throws Throwable {
        Integer emailCount = commonMailinatorPage.displayedEmailCount();
        assertTrue(emailCount >= emailResultsExpected);
        }

    public void validateMostRecentEmailSubject(
        String emailSubject) throws Throwable {
        String onclickId = commonMailinatorPage.firstEmailOnclickId();
        assertTrue(commonMailinatorPage.emailListSubjectOnclick(onclickId).getText().contains(emailSubject));
    }

    public void clickMostRecentEmail() throws Throwable {
        String onclickId = commonMailinatorPage.firstEmailOnclickId();
        commonMailinatorPage.emailListSubjectOnclick(onclickId).click();
        }

    public void validateEmailIsTo(String emailTo) throws Throwable {
        assertTrue(commonMailinatorPage.emailDisplayTo().getText().contains(emailTo));
    }

    public void validateEmailIsFrom(String fromEmail) throws Throwable {
        //Get e-mail and strip the whitespace
        String fromEmailDisplayed = commonMailinatorPage.emailDisplayFrom().getText().replaceAll("/[^A-Za-z0-9 ]/", "");
        assertTrue(fromEmailDisplayed.equals(fromEmail));
        }

    public void validateEmailSubjectIs(
        String subject) throws Throwable {
        assertTrue(commonMailinatorPage.emailDisplaySubject().getText().contains(subject));
        }

    public void validateEmailContainsText(String emailText) throws Throwable {
        assertTrue(commonMailinatorPage.getEmailText().contains(emailText));
        }

    public void validateEmailContainsLink(String emailLink) throws Throwable {
        // Switch to the e-mail frame to be able to read the e-mail
        commonMailinatorPage.switchToIframe(commonMailinatorPage.emailMainTextFrame());
        commonMailinatorPage.emailMainTextLink(emailLink).isDisplayed();
        // Switch back to the full web page
        commonMailinatorPage.switchToMainWindow();
        }

    public void validateEmailContainsLinkWithTitle(
        String linkTitle,
        String emailLink) throws Throwable {
        // Switch to the e-mail frame to be able to read the e-mail
        commonMailinatorPage.switchToIframe(commonMailinatorPage.emailMainTextFrame());
        WebElement elementLink = commonMailinatorPage.emailMainTextLink(emailLink);
        elementLink.isDisplayed();
        assertTrue(elementLink.getText().equals(linkTitle));
        // Switch back to the full web page
        commonMailinatorPage.switchToMainWindow();
        }

    public String generatesUniqueEmailAddress() throws Throwable {
        String uuid = UUID.randomUUID().toString().replaceAll("[^A-Za-z0-9]", "");
        return uuid.substring(0, Math.min(uuid.length(), 25));
    }

}
