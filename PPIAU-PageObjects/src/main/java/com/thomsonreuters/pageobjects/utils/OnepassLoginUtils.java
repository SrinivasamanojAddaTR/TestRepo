package com.thomsonreuters.pageobjects.utils;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.other_pages.WelcomePage;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.login.OnepassLogin;
import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.one_pass.OnePassLogoutPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnepassLoginUtils {

    private OnepassLogin onepassLogin = new OnepassLogin();
    private OnePassLogoutPage onePassLogoutPage = new OnePassLogoutPage();
    private WelcomePage welcome = new WelcomePage();
    private WLNHeader wlnHeader = new WLNHeader();
    protected CommonMethods comMethods = new CommonMethods();

    protected static final Logger LOG = org.slf4j.LoggerFactory.getLogger(OnepassLoginUtils.class);
    private static String userName;
    private static String password;

    public OnepassLoginUtils(){
     onepassLogin = new OnepassLogin();
        welcome = new WelcomePage();
        comMethods = new CommonMethods();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void loginToCobalt(String username, String password) {
        this.userName = username;
        this.password = password;

        enterUserNameAndPassword(username, password);
        //to avoid problem when click on sign didn't performed directly after typing password 
        onepassLogin.usernameTextField().click();
        comMethods.moveToElementUsingLocation(onepassLogin.signOnButton());
        onepassLogin.signOnButton().click();
    }

    public void loginToCobaltWithSRM(String username, String password) {
        this.userName = username;
        this.password = password;

        enterUserNameAndPassword(username, password);
        onepassLogin.usernameTextField().click();
        if (!onepassLogin.rememeberMeCheckBox().isSelected()) {
            comMethods.clickElementUsingJS(onepassLogin.rememeberMeCheckBox());
        }
        onepassLogin.signOnButton().click();
    }

    public void enterUserNameAndPassword(String username, String password) {
        this.userName = username;
        this.password = password;

        onepassLogin.usernameTextField().clear();
        onepassLogin.usernameTextField().sendKeys(username);
        onepassLogin.passwordTextField().clear();
        onepassLogin.passwordTextField().sendKeys(password);
        onepassLogin.usernameTextField().click();
        comMethods.moveToElementUsingLocation(onepassLogin.signOnButton());
    }

    public void loginToOnePassSettings(String username, String password) {
        onepassLogin.usernameTextField().clear();
        onepassLogin.usernameTextField().sendKeys(username);
        onepassLogin.passwordTextField().clear();
        onepassLogin.passwordTextField().sendKeys(password);
        onepassLogin.signOnButton().click();
    }

    public void loginWithClientId(String username, String password, String clientID) {
        loginToCobalt(username, password);
        welcome.clientID().clear();
        welcome.clientID().sendKeys(clientID);
        welcome.continueButton().click();
    }

    public void loginWithDefaultClientId() {
        welcome.continueButton().click();
    }

    public void chooseRegKey(String regKey) {
        onepassLogin.regKeyRadioButtonByRegKey(regKey).click();
        onepassLogin.signOnButton().click();
    }

    public void openSuperRememberMeHintPopUp() {
        onepassLogin.superRememberMeHintLink().click();
    }

	public boolean isSignBackOnButtonPresent() {
		try {
			return onePassLogoutPage.signOffPageSignOnButton().isDisplayed();
		} catch (TimeoutException | NoSuchElementException nse) {
			LOG.info("context", nse);
			return false;
		}
	}

	public boolean isLogOutBrandingLogoPresent() {
		try {
			return onePassLogoutPage.logOutBrandingLogo().isDisplayed();
		} catch (PageOperationException poe) {
			LOG.info("context", poe);
			return false;
		}
	}

    public boolean isLoginBoxPresent() {
        try {
            return onepassLogin.loginBox().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

	public boolean isSessionSummaryBoxPresent() {
		try {
			return onePassLogoutPage.sessionSummaryBox().isDisplayed();
		} catch (TimeoutException | NoSuchElementException nse) {
			LOG.info("context", nse);
			return false;
		}
	}

    public boolean isSessionSummaryTimeCorrect(String timeZone, String sessionSummary) {
        try {
            Pattern timePattern = Pattern.compile(".*?(\\d{1,2} \\w+ \\d{4}) at (\\d{1,2}:\\d{2} \\w{2}) \\- (\\d{1,2}:\\d{2} \\w{2}).*");

            Matcher matcher = timePattern.matcher(sessionSummary);
            matcher.find();


            String sessionStart = matcher.group(1) + " " + matcher.group(2);
            String sessionEnd = matcher.group(1) + " " + matcher.group(3);

            SimpleDateFormat format = new SimpleDateFormat("d MMMM yyyy hh:mm a");
            format.setTimeZone(TimeZone.getTimeZone(timeZone));

            Date sessionStartTime = format.parse(sessionStart);
            Date sessionEndTime = format.parse(sessionEnd);

            Date now = new Date();

            if (sessionEndTime.getTime() - sessionStartTime.getTime() > 15 * 60 * 1000
                    || sessionEndTime.getTime() - sessionStartTime.getTime() < 1 * 60 * 1000) {
                return false; // session length within range 1-15 minutes
            }

            if (now.getTime() - sessionEndTime.getTime() > 5 * 60 * 1000 || now.getTime() - sessionEndTime.getTime() < -5 * 60 * 1000) {
                return false; // end time is within range of now +-5 minutes
            }
            return true;

        } catch (TimeoutException | NoSuchElementException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSessionDetailTimeCorrect(String timeZone, String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            format.setTimeZone(TimeZone.getTimeZone(timeZone));

            Date eventTime = format.parse(time);
            Date now = new Date();
            if (now.getTime() - eventTime.getTime() > 15 * 60 * 1000 || now.getTime() - eventTime.getTime() < -5 * 60 * 1000) {
                return false; // event time is within range of now -15 - +5 minutes
            }
            return true;
        } catch (TimeoutException | NoSuchElementException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

//	public String getSessionTableCellByDescriptionAndHeader(String descriptionContains, String header) {
//		WebElement table = onePassLogoutPage.sessionDetailsTable();
//		return comMethods.getTableCellByOtherColumnValueAndHeader(table, "Description", descriptionContains, header);
//	}

    public boolean isUserNameTextFieldPresent() {
        try {
            return onepassLogin.usernameTextField().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isPasswordTextFieldPresent() {
        try {
            return onepassLogin.passwordTextField().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isForgotMyUsernameOrPasswordLinkPresent() {
        try {
            return onepassLogin.forgotMyUsernameOrPasswordLink().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isSuperRememberMeHitnPopUpPresent() {
        return onepassLogin.isSuperRememberMeHintPopUpPresent();
    }

    public boolean isCreateNewOnePassProfileLinkPresent() {
        try {
            return onepassLogin.createNewOnePassProfileLink().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isUpdateExistingOnePassProfileLinkPresent() {
        try {
            return onepassLogin.updateExistingOnePassProfileLink().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

    public boolean isLearnMoreAboutOnePassLinkPresent() {
        try {
            return onepassLogin.learnMoreAboutOnePassLink().isDisplayed();
        } catch (PageOperationException poe) {
            LOG.info("context", poe);
            return false;
        }
    }

	public boolean isResumeAsCurrentUserLinkPresent() {
		try {
			return onePassLogoutPage.isElementDisplayed(onePassLogoutPage.resumeAsCurrentUserLinkText());
		} catch (PageOperationException poe) {
			LOG.info("context", poe);
			return false;
		}
	}

    public void selectSaveMyUsernameCheckBox() {
        if (onepassLogin.saveMyUsernameCheckBox().isSelected()) {
            LOG.info("Save my Username is selected");
        } else {
            comMethods.clickElementUsingJS(onepassLogin.saveMyUsernameCheckBox());
        }
    }

    public void selectSaveMyUsernameAndPasswordCheckBox() {
        if (onepassLogin.saveMyUsernameAndPasswordCheckBox().isSelected()) {
            LOG.info("Save my Username is selected");
        } else {
            comMethods.clickElementUsingJS(onepassLogin.saveMyUsernameAndPasswordCheckBox());
        }
    }

	public boolean isSignInWithDifferentAccountLinkPresent() {
		try {
			return onePassLogoutPage.signInWithDifferentAccountLink().isDisplayed();
		} catch (PageOperationException poe) {
			LOG.info("context", poe);
			return false;
		}
	}
	
	public void loginWithAthens(String username, String password){
    	onepassLogin.usernameAthens().sendKeys(username);
    	onepassLogin.passwordAthens().sendKeys(password);
    	onepassLogin.singInAthensButton().click();
    }
    
    public void loginWithInstitution(String username, String password){
    	onepassLogin.usernameShiboleth().sendKeys(username);
    	onepassLogin.passwordShiboleth().sendKeys(password);
    	onepassLogin.singInShibolethButton().click();
    }

    public CobaltUser removeSRMOption() throws IOException, InterruptedException {
        //TODO: this is a workaround to click sign off twice before 'Return to Sign In' Link appears it is by design at the moment
        //confirmed by Robert Foster and Jacqueline Auma
        wlnHeader.signOff();
        onepassLogin.waitForPageToLoad();
        if (!isResumeAsCurrentUserLinkPresent()) {
            onePassLogoutPage.signOffPageSignOnButton().click();
            onepassLogin.waitForPageToLoad();
            wlnHeader.signOff();
        }
        onePassLogoutPage.signInWithDifferentAccountLink().click();
        return new CobaltUser();
    }

    public void scrollToTheTopOfTheDocument() {
        wlnHeader.executeScript("window.scrollTo(0,0);");
    }
}
