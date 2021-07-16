package com.thomsonreuters.pageobjects.pages.ask;

import com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources.CommonResourcePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class AskResourcePage extends CommonResourcePage {

    private static final String COMMENT_PATTERN = "#co_docContentBody div:nth-of-type(%d) %s";

    public WebElement askDisclaimerText() {
        return waitForExpectedElement(By.className("ask_disclaimer"));
    }

    public WebElement askErrorText() {
        return waitForExpectedElement(By.cssSelector("#co_contentColumn"));
    }

    // comments (1st comment is the response)
    public int numberOfComments() {
        if (isExists(commentsLocator())) {
            return waitForExpectedElements(commentsLocator()).size();
        }
        return 0;
    }

    public By commentsLocator() {
        return By.cssSelector("#co_docContentBody .askCommentResponse");
    }

    public WebElement commentResponseElementForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format("#co_docContentBody div[class='askCommentResponse']:nth-of-type(%d)", n + 1)));
    }

    public WebElement askPLCScopeAndRulesLink(String linkName) {
    	return waitForExpectedElement(By.xpath("//a[@class='commentLinks' and text()='" + linkName + "']"));
    }

    public WebElement displayNameForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askCommentDisplayName")));
    }
    
    public WebElement inputName() {
        return waitForExpectedElement(By.id("userFullName"));
    }

    public WebElement displayServiceForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askCommentDisplayService")));
    }
    
    public WebElement inputService() {
        return waitForExpectedElement(By.id("userFirmName"));
    }

    public WebElement postedDateForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askCommentPostedDate")));
    }

    public WebElement bodyForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askCommentBody")));
    }

    public WebElement textForNthComment(int n){
        return findElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askCommentBody")));
    }

    public WebElement reportThisPostForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askReportThisPost>a")));
    }

    public WebElement replyForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askReplyToThisPost>a")));
    }

    public WebElement editForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askEditComment>a")));
    }

    public WebElement deleteForNthComment(int n) {
        return waitForExpectedElement(By.cssSelector(String.format(COMMENT_PATTERN, n + 1, "> .askDeleteComment>a")));
    }


    // Reply
    public By addReplyTextAreaLocator() {
        return By.cssSelector("div.commentFieldsContainer >textarea");
    }

    public WebElement addReplyTextArea() {
        return waitForExpectedElement(addReplyTextAreaLocator());
    }

    public WebElement displayNameAndOrgCheckbox() {
        return waitForExpectedElement(By.cssSelector("div.commentActionContainer div:nth-of-type(2)>label>input"));
    }

    public WebElement agreeToRulesCheckbox() {
        return waitForExpectedElement(By.className("acceptTerms"));
    }

    public WebElement submitComment() {
        return waitForExpectedElement(By.className("submitReply"));
    }

    public WebElement errorMessage() {
        return waitForExpectedElement(By.className("validate-required"));
    }

    //Action Widget

    public WebElement overlayHeader() {
        return waitForExpectedElement(By.cssSelector(".co_overlayBox_headline div h3"));
    }

    public WebElement downloadWidget() {
        return waitForExpectedElement(By.cssSelector("#co_DownloadWidget a"));
    }

    public WebElement printWidget() {
        return waitForExpectedElement(By.cssSelector("#co_PrintWidget a"));
    }

    public WebElement emailWidget() {
        return waitForExpectedElement(By.cssSelector("#co_EmailWidget a"));
    }

    public WebElement overlayClickButton() {
        return waitForElementToBeClickable(waitForExpectedElement(By.cssSelector(".co_overlayBox_optionsBottom ul li:nth-of-type(1) input")));
    }

    public WebElement downloadInReadyForDownloadOverlayButton() {
        return waitForExpectedElement(By.cssSelector("#coid_deliveryWaitMessage_download input"));
    }

    public WebElement readyMessageOverlayHeader() {
        return waitForExpectedElement(By.xpath("//div[@id='co_headerMessage' and contains(text(),'Ready For')]"));
    }

    public WebElement prepareMessageOverlayHeader() {
        return waitForExpectedElement(By.xpath("//div[@id='co_headerMessage' and contains(text(),'Preparing For')]"));
    }

    public WebElement addReplyNextToHeaderLink() {
        return waitForExpectedElement(By.xpath("//div[@id='co_docHeaderContainer']//a[text()='Add reply']"));
    }
}
