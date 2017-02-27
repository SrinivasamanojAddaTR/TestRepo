package com.thomsonreuters.pageobjects.rest.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderPermissions extends JsonObject {

    private boolean addContainersAllowed;
    private boolean addItemsAllowed;
    private boolean copyAllowed;
    private boolean deleteAllowed;
    private boolean editDescriptionAllowed;
    private boolean moveAllowed;
    private boolean renameAllowed;

    public void setAddContainersAllowed(boolean addContainersAllowed) {
        this.addContainersAllowed = addContainersAllowed;
    }

    public void setAddItemsAllowed(boolean addItemsAllowed) {
        this.addItemsAllowed = addItemsAllowed;
    }

    public void setCopyAllowed(boolean copyAllowed) {
        this.copyAllowed = copyAllowed;
    }

    public void setDeleteAllowed(boolean deleteAllowed) {
        this.deleteAllowed = deleteAllowed;
    }

    public void setEditDescriptionAllowed(boolean editDescriptionAllowed) {
        this.editDescriptionAllowed = editDescriptionAllowed;
    }

    public void setMoveAllowed(boolean moveAllowed) {
        this.moveAllowed = moveAllowed;
    }

    public void setRenameAllowed(boolean renameAllowed) {
        this.renameAllowed = renameAllowed;
    }

    public boolean isAddContainersAllowed() {
        return addContainersAllowed;
    }

    public boolean isAddItemsAllowed() {
        return addItemsAllowed;
    }

    public boolean isCopyAllowed() {
        return copyAllowed;
    }

    public boolean isDeleteAllowed() {
        return deleteAllowed;
    }

    public boolean isEditDescriptionAllowed() {
        return editDescriptionAllowed;
    }

    public boolean isMoveAllowed() {
        return moveAllowed;
    }

    public boolean isRenameAllowed() {
        return renameAllowed;
    }

}
