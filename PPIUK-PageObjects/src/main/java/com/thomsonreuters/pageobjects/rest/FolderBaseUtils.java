package com.thomsonreuters.pageobjects.rest;

import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceFFHImpl;

public class FolderBaseUtils {

    private RestServiceFFHImpl restService = new RestServiceFFHImpl();

    public void createFolder(String newFolderName, String parentFolderName) {
        String rootFolderID = getRootFolderID();
        restService.postCreateFolder(newFolderName, rootFolderID);
    }

    public void doSuperDelete() {
        restService.deleteDoSuperDelete();
    }

    public void deleteStartPage() {
        restService.deleteStartPage();
    }

    @Deprecated
    public void wlnDoSuperDelete() {
        restService.wlnDeleteDoSuperDelete();
    }

    private String getRootFolderID() {
        return restService.getRootAncestors()[0].getCategoryId();
    }
}
