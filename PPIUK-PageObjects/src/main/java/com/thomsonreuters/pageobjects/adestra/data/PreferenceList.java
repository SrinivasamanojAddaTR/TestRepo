package com.thomsonreuters.pageobjects.adestra.data;

public class PreferenceList {
    private String name;
    private Integer id;
    private String description;

    public PreferenceList() {
    }

    public PreferenceList(String name, Integer id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Sub Pref: \n");
        sb.append(" - Description: " + getDescription() + ",\n");
        sb.append(" - Name: " + getName() + ",\n");
        sb.append(" - ID: " + getId() + ",\n");
        return sb.toString();
    }

}
