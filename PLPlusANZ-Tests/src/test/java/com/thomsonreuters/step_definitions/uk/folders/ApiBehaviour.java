package com.thomsonreuters.step_definitions.uk.folders;

import com.thomsonreuters.pageobjects.rest.FolderBaseUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import com.thomsonreuters.step_definitions.CommonLoginNaviagtionSteps;
import cucumber.api.java.en.When;

public class ApiBehaviour extends BaseStepDef {

    private FolderBaseUtils restSteps = new FolderBaseUtils();
    private CommonLoginNaviagtionSteps commonLoginNaviagtionSteps = new CommonLoginNaviagtionSteps();

    @When("^API creates folder with name '(.+)' with parent '(.+)'$")
    public void apiCreatesFolderWithNameWithParent(String newFolderName, String parentFolderName) throws Throwable {
        restSteps.createFolder(newFolderName);
    }

    //After using super delete user should relogin.
    @When("^API cleans all folders and history$")
    public void apiCleansAllFoldersAndHistory() throws Throwable {
        restSteps.doSuperDelete();
    }
    
    @When("^API cleans all folders and history and user relogs in$")
    public void apiCleansAllFoldersAndHistoryAndUserRelogsIn() throws Throwable {
        restSteps.doSuperDelete();
        commonLoginNaviagtionSteps.userRelogsIn();
    }
    
}