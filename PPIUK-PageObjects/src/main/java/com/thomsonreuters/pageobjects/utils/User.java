package com.thomsonreuters.pageobjects.utils;

/**
 * Created by UC186961 on 02/09/2015.
 */
public class User {
    private String userName;
    private String password;
    private static ThreadLocal<User> users = new ThreadLocal<>();

    public static User getInstance() {
        if (users.get() == null) {
            users.set(new User());
        }

        return users.get();
    }

    public void remove() {
        users.remove();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
