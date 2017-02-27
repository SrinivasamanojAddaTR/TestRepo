package com.thomsonreuters.pageobjects.common;

/**
 * Created by Pavel_Ardenka on 16/12/2016.
 * Enumeration with HTML element attributes
 */
public enum Attribute {

    //TODO We can use HTML.Attribute instead of this enum.
    // Need to rename Attribute to CustomAttribute, delete TITLE,VALUE and resolve all dependencies
    TITLE("title"),
    VALUE("value"),
    DOC_GUID("docguid");

    private String name;

    Attribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
