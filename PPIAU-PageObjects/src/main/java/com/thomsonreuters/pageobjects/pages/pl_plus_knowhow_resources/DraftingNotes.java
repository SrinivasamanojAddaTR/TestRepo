package com.thomsonreuters.pageobjects.pages.pl_plus_knowhow_resources;

public enum DraftingNotes {
    SHOW_ALL("Show All"),
    HIDE_ALL("Hide All"),
    SHOW_NOTES_ONLY("Show Notes Only");

    private final String name;

    public String getOptionName() {
        return this.name;
    }

    private DraftingNotes(String name) {
        this.name = name;
    }
}
