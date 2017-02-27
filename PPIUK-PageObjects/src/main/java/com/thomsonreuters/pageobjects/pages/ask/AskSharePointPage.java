package com.thomsonreuters.pageobjects.pages.ask;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;


public class AskSharePointPage extends AbstractPage {

    public void navigateToSharePointSite() {
        try {
            String userName = URLEncoder.encode(System.getProperty("winusername"), "UTF-8");
            String password = URLEncoder.encode(System.getProperty("winpassword"), "UTF-8");
            String url = String.format("http://%1$s:%2$s@apps-uat.practicallaw.com/Ask/SitePages/Home.aspx", userName, password);
            getDriver.get(url);
            waitForPageToLoad();
        } catch (UnsupportedEncodingException e) {
            LOG.info("Error occurred at SharePoint URL building. Check the -Dwinusername and -Dwinpassword variables. ", e);
            throw new RuntimeException("Error occurred at SharePoint URL building");
        }
    }

    public WebElement feedbackTeamLink(String feedbackTeam) {
        return waitForExpectedElement(By.xpath("//a[contains(@href,'" + feedbackTeam + "')]"));
    }

    public WebElement subscriberLink() {
        return waitForExpectedElement(By.linkText("Subscriber"));
    }

    public WebElement webFormDetailsFirstNameText() {
        return waitForExpectedElement(By.id("ctl00_PlaceHolderMain_lblWebFormFirstName"));
    }

    public WebElement webFormDetailsLastNameText() {
        return findElement(By.id("ctl00_PlaceHolderMain_lblWebFormLastName"));
    }

    public WebElement webFormDetailsEmailText() {
        return findElement(By.id("ctl00_PlaceHolderMain_lblWebFormEmail"));
    }

    public WebElement webFormDetailsOrgTypeText() {
        return findElement(By.id("ctl00_PlaceHolderMain_lblWebFormOrgType"));
    }

    public WebElement webFormDetailsPositionText() {
        return findElement(By.id("ctl00_PlaceHolderMain_lblWebFormSubscriberPosition"));
    }

    public WebElement webFormDetailsPlcDocumentIdText() {
        return findElement(By.id("ctl00_PlaceHolderMain_lblWebFormSourcePLCRef"));
    }

    public WebElement webFormDetailsDocumentUrlText() {
        return findElement(By.id("ctl00_PlaceHolderMain_lblDocumentUrl"));
    }

    public WebElement statusAndActionsReferenceText() {
        return findElement(By.id("ctl00_PlaceHolderMain_headerPLCRefLabel"));
    }

    public WebElement statusAndActionsSubscriberText() {
        return findElement(By.id("ctl00_PlaceHolderMain_subscriberStatusLabel"));
    }

    public WebElement statusAndActionsAssignedServiceText() {
        return findElement(By.id("ctl00_PlaceHolderMain_headerPLCServiceLabel"));
    }

    public WebElement statusAndActionsAnsweringStatusDropDown() {
        return findElement(By.name("ctl00$PlaceHolderMain$ddlAnsweringStatus"));
    }

    public List<WebElement> unAssignedItems() {
        return waitForExpectedElements(By.cssSelector("div[class='ms-webpart-zone ms-fullWidth'] > div:nth-of-type(1) > div:nth-of-type(1) tbody tr td table .AskQuestionsListRow"));
    }

    private By getBySelectorWithinRow(int index){
        return By.cssSelector("td:nth-of-type(" + index + ")");
    }

    public By getSelectorWithinRowForRefid(){
        return getBySelectorWithinRow(2);
    }

    public By getSelectorWithinRowForDate(){
        return getBySelectorWithinRow(3);
    }

    public By getSelectorWithinRowForName(){
        return getBySelectorWithinRow(5);
    }

    public By getSelectorWithinRowForPosition(){
        return getBySelectorWithinRow(6);
    }

    public String getCurrentValueInDropDownList(WebElement element) {
        String value = "";
        Select selectedOption = new Select(element);
        try {
            value = selectedOption.getFirstSelectedOption().getText();
        } catch (NoSuchElementException e) {
            LOG.info("context", e);
        }
        return value;
    }

    public void sendTabKeyThroughWebdriver() {
        Actions action = new Actions(getDriver);
        action.sendKeys(Keys.TAB).build().perform();
    }


    public void pressKeyTabsAndPressEnterThroughWebdriver(int noOfTabs, int delayInMsecs) throws Throwable {
        for (int i = 0; i < noOfTabs; i++) {
            Thread.sleep(delayInMsecs);
            sendTabKeyThroughWebdriver();
        }
        Thread.sleep(2000);
        sendKeyEventThroughWebdriver(Keys.ENTER);
    }

    public void sendKeyEventThroughWebdriver(Keys keyEvent) {
        Actions action = new Actions(getDriver);
        action.sendKeys(keyEvent).build().perform();
    }

    public void sendTextThroughWebdriver(String text) {
        Actions builder = new Actions(getDriver);
        String upperCase = text.toUpperCase();
        for (int i = 0; i < upperCase.length(); i++) {
            String letter = Character.toString(upperCase.charAt(i));
            builder.sendKeys(letter.toUpperCase()).build().perform();
        }

    }

    public class LoginWindow implements Runnable {
        private String userName;
        private String password;

        public LoginWindow(String userName, String password) {
        this.userName=userName;
        this.password=password;
        }

        @Override
        public void run() {
            try {
                login();
            } catch (Exception ex) {
                System.out.println("Error in Login Thread: " + ex.getMessage());
            }
        }

        public void login() throws Exception {

            //wait - increase this wait period if required
            Thread.sleep(10000);

            //create robot for keyboard operations
            Robot rb = new Robot();

            //Enter user name by ctrl-v
            StringSelection username = new StringSelection(userName);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);

            //tab to password entry field
            rb.keyPress(KeyEvent.VK_TAB);
            rb.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(2000);

            //Enter password by ctrl-v
            StringSelection pwd = new StringSelection(password);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);

            //press enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);

            //wait
            Thread.sleep(5000);
        }
    }


}
