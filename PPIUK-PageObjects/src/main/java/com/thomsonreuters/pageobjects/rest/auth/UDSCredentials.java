package com.thomsonreuters.pageobjects.rest.auth;

public class UDSCredentials {
    String coSessionToken;
    String sessionID;
    String userGuid;
    String workProductToken;

    public UDSCredentials(String coSessionToken, String sessionID, String userGuid, String workProductToken) {
        this.coSessionToken = coSessionToken;
        this.sessionID = sessionID;
        this.userGuid = userGuid;
        this.workProductToken = workProductToken;
    }

    public String getWorkProductToken() {
        return workProductToken;
    }

    public void setWorkProductToken(String workProductToken) {
        this.workProductToken = workProductToken;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getCoSessionToken() {
        return coSessionToken;
    }

    public void setCoSessionToken(String coSessionToken) {
        this.coSessionToken = coSessionToken;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}
