package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.driver.framework.AbstractPage;
import com.thomsonreuters.pageobjects.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DashboardPage extends AbstractPage {

    private final static String PROJECT_DASHBOARD_LABEL = "//a[@class='brand' and text()='Project dashboard']";
    private final static String CREATE_NEW_PROJECT_BUTTON = "//a[@href='newproject.zevon']";
    private final static String PROJECT = "//tr[@data-title='%s']";
    private final static String ACTIONS_BUTTON = PROJECT + "//a[text()=' Actions ']";
    private final static String ACTIONS_BUTTON_NUMBER = "(//a[text()=' Actions '])[%s]";
    private final static String DELETE_PROJECT_BUTTON = PROJECT + "//a[@class='project-delete']";
    private final static String DELETE_BUTTON_NUMBER = "(//a[@class='project-delete'])[%s]";
    private final static String RENAME_PROJECT_BUTTON = PROJECT + "//a[@class='project-rename']";
    private final static String ARCHIVE_PROJECT_BUTTON = PROJECT + "//a[@class='project-archive']";
    private final static String UNARCHIVE_PROJECT_BUTTON = PROJECT + "//a[@class='project-unarchive']";
    private final static String CANCEL = "//*[@id='ConfirmActionModal']//*[text()='Cancel']";

    private CommonMethods comMethods = new CommonMethods();

    public void checkFastDraftDashboardPresents() {
    	WebElement dashboard = comMethods.waitForElementToBeVisible(By.xpath(PROJECT_DASHBOARD_LABEL), 10000);
		Assert.assertNotNull(dashboard, "Fast Draft dashboard absents");
    }

    public WebElement createNewProject() {
        return comMethods.waitForElementToBeVisible(By.xpath(CREATE_NEW_PROJECT_BUTTON), 10000);
    }

    public WebElement openProject(String project) {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[text()='" + project + "']"), 10000);
    }

    public boolean isProjectPresent(String projectName) {
        waitForPageToLoad();
        return isElementDisplayed(By.xpath(String.format(PROJECT, projectName)));
    }

    public WebElement confirmationDelete() {
        return waitForExpectedElement(By.xpath("//*[@id='ConfirmActionModal']//*[@id='actionButton' and text()='Yes, delete it']"));
    }

    public WebElement delete(String projectName) {
        return waitForExpectedElement(By.xpath(String.format(DELETE_PROJECT_BUTTON, projectName)));
    }

    public WebElement delete(int projectPosition) {
        return waitForExpectedElement(By.xpath(String.format(DELETE_BUTTON_NUMBER, projectPosition)));
    }

    public WebElement rename(String projectName) {
        return waitForExpectedElement(By.xpath(String.format(RENAME_PROJECT_BUTTON, projectName)));
    }

    public WebElement confirmationArchive() {
        return waitForExpectedElement(By.xpath("//*[@id='ConfirmActionModal']//*[text()='Yes, archive it']"));
    }

    public WebElement viewingCurrentProjects() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[text()=' Viewing Current Projects ']"), 10000);
    }

    public WebElement switchToArchive() {
        return waitForExpectedElement(By.xpath("//a[text()='Switch to Archive']"));
    }

    public WebElement switchToCurrentProjects() {
        return waitForExpectedElement(By.xpath("//a[text()='Switch to current projects']"));
    }

    public WebElement viewingArchive() {
        return comMethods.waitForElementToBeVisible(By.xpath("//a[text()=' Viewing Archive ']"), 10000);
    }

    public WebElement archive(String projectName) {
        return waitForExpectedElement(By.xpath(String.format(ARCHIVE_PROJECT_BUTTON, projectName)));
    }

    public WebElement unarchive(String projectName) {
        return waitForExpectedElement(By.xpath(String.format(UNARCHIVE_PROJECT_BUTTON, projectName)));
    }

    public WebElement confirmationActivate() {
        return waitForExpectedElement(By.xpath("//*[@id='ConfirmActionModal']//*[text()='Yes, activate']"));
    }

    public WebElement cancel() {
        return waitForExpectedElement(By.xpath(CANCEL));
    }

    public WebElement actions(String projectName) {
        return comMethods.waitForElementToBeVisible(By.xpath(String.format(ACTIONS_BUTTON, projectName)), 10000);
    }

    public WebElement actions(int projectPosition) {
        return waitForExpectedElement(By.xpath(String.format(ACTIONS_BUTTON_NUMBER, projectPosition)));
    }

    public int getProjectsCount() {
        int count = 0;
        try {
            comMethods.waitForElementToBeVisible(By.xpath("//tr[contains(@class,'gradeX project-listing')]"), 10000);
            count = waitForExpectedElements(By.xpath("//tr[contains(@class,'gradeX project-listing')]")).size();
        } catch (TimeoutException e) {
            LOG.info("Project count is 0");
        }
        return count;
    }

    public WebElement getProjectNameByPosition(int position) {
        return comMethods.waitForElementToBeVisible(By.xpath("(//tr[contains(@class,'gradeX project-listing')])[" + position + "]//a[@onclick]"), 10000);
    }

    public boolean isProjectAbsent(String projectName) {
        return waitForElementToDissappear(By.xpath(String.format(PROJECT, projectName)));
    }

    public boolean isNoProjectPresent() {
        return waitForElementToDissappear(By.xpath("//tr[contains(@class,'gradeX project-listing')]"));
    }

}
