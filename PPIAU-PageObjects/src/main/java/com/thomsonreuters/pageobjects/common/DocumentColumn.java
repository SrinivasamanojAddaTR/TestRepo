package com.thomsonreuters.pageobjects.common;

/**
 * Enumeration which describes columns of table on the folder view page (with documents in it)
 */
public enum DocumentColumn {

    TITLE("Title"),
    CONTENT("Content"),
    DATE_ADDED("Date Added");

    private String name;

    DocumentColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DocumentColumn getByName(String name) {
        for (DocumentColumn oneDocCol : DocumentColumn.values()) {
            if (oneDocCol.getName().equalsIgnoreCase(name)) {
                return oneDocCol;
            }
        }
        throw new UnsupportedOperationException("There is no column with name '" + name + "'.");
    }

}
