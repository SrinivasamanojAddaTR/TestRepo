package com.thomsonreuters.pageobjects.utils.homepage;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.pages.company.MeetTheTeam;
import com.thomsonreuters.pageobjects.pages.footer.AdvisoryBoardsPage;
import com.thomsonreuters.pageobjects.pages.landingPage.PracticalLawHomepage;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian Hudson uc087619 on 16/02/2016.
 */
public class HomePageUtils {

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CommonMethods.class);

    private PracticalLawHomepage practicalLawHomepage = new PracticalLawHomepage();
    private AdvisoryBoardsPage consultationBoardsPage = new AdvisoryBoardsPage();
    private MeetTheTeam meetTheTeam = new MeetTheTeam();

    public void clickThePracticalLawHomepageLogo() {
        practicalLawHomepage.practicalLawTRLogo().click();
    }

    public List<String> getPAnamesFromList() {
        ArrayList<String> paNamesFromList = new ArrayList<String>();
        List<WebElement> paLinksInList = consultationBoardsPage.paNamesInList();
        for (WebElement element : paLinksInList) {
            paNamesFromList.add(element.getText());
        }
        return paNamesFromList;
    }

    public List<String> getPAnamesFromTOC() {
        ArrayList<String> paNamesFromTOC = new ArrayList<String>();
        List<WebElement> practiceAreaTabs = meetTheTeam.tabsInTableOfContents();
        for(WebElement element: practiceAreaTabs) {
            paNamesFromTOC.add(element.getText());
        }
        return paNamesFromTOC;
    }

    public List<String> getPeopleNamesFromAdvisoryBoard() {
        ArrayList<String> peopleNamesFromAdvisoryBoard = new ArrayList<>();
        List<WebElement> peopleNameElements = consultationBoardsPage.peopleNames();
        for (WebElement element : peopleNameElements) {
            String nameAndOrganization = element.getText();
            peopleNamesFromAdvisoryBoard.add(nameAndOrganization);
        }
        return peopleNamesFromAdvisoryBoard;
    }
}
