package com.thomsonreuters.pageobjects.utils.annotations;

/**
 * Created by UC186961 on 02/09/2015.
 */
public class User {
    private String firstName;
    private String lastName;

    public User(String annUserFirstName, String annUserLastName) {
        this.firstName = annUserFirstName;
        this.lastName = annUserLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName+" "+lastName;
    }
}
