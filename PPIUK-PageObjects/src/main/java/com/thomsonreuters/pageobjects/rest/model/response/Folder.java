package com.thomsonreuters.pageobjects.rest.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thomsonreuters.pageobjects.rest.model.JsonObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Folder extends JsonObject {
    private String categoryId;
    private boolean isLeaf;
    private boolean isRoot;
    private boolean isShared;
    private String label;
    private String labelStyle;
    private FolderPermissions permissions;
    private String type;

    private List<Folder> children;

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public void setShared(boolean isShared) {
        this.isShared = isShared;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLabelStyle(String labelStyle) {
        this.labelStyle = labelStyle;
    }

    public void setPermissions(FolderPermissions permissions) {
        this.permissions = permissions;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChildren(List<Folder> children) {
        this.children = children;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public boolean isShared() {
        return isShared;
    }

    public String getLabel() {
        return label;
    }

    public String getLabelStyle() {
        return labelStyle;
    }

    public FolderPermissions getPermissions() {
        return permissions;
    }

    public String getType() {
        return type;
    }

    public List<Folder> getChildren() {
        return children;
    }

}
