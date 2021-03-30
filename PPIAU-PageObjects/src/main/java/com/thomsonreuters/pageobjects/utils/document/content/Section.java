package com.thomsonreuters.pageobjects.utils.document.content;

/**
 * Created by Pavel_Ardenka on 10/12/2015.
 */
public class Section {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Section{" +
                "title='" + title + '\'' +
                '}';
    }
}
