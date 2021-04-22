package com.thomsonreuters.pageobjects.utils;

import com.thomsonreuters.pageobjects.common.ExcelFileReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.util.ReflectionUtils;

import static org.apache.commons.lang3.StringUtils.LF;

public class CobaltUser {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(CobaltUser.class);

    private Product product;
    private String userName;
    private String additionalUserName;
    private String additionalEmail;
    private String role;
    private String regKey;
    private Routing routing;
    private String clientId;
    private boolean newSession;
    private String loginRequired;
    private String mandatoryRouting;
    private String email;
    private String sessionId;
    private boolean skipHandleClientIdImplicitly;

    private static ThreadLocal<CobaltUser> firstUser = new ThreadLocal<>();

    public CobaltUser() {
        this.product = Product.PLC;
        this.role = "DEFAULT_USER";
        this.userName = null;
        this.routing = Routing.DEFAULT;
        this.clientId = "TEST01";
        this.regKey = null;
        this.skipHandleClientIdImplicitly = false;
    }

    public static void removeFirstUser() {
        firstUser.remove();
    }

    public static CobaltUser firstUser() {
        if (firstUser.get()==null){
            CobaltUser cobaltUser = new CobaltUser();
            cobaltUser.reset();
            firstUser.set(cobaltUser);
        }
        return firstUser.get();
    }

    public void setCurrentUser(CobaltUser user) {
        CobaltUser userDest = firstUser.get();
        ReflectionUtils.shallowCopyFieldState(user, userDest);
    }

    public void reset() {
        setRole(null);
        setProduct(null);
        setUserName(null);
        setRouting(null);
        setClientId(null);
        setEmail(null);
        setRegKey(null);
        setAdditionalUserName(null);
        setSessionId(null);
    }

    public static CobaltUser updateMissingFields(CobaltUser scenarioUser) {
        if (null == scenarioUser.getProduct()) {
            scenarioUser.setProduct(Product.PLC);
        }
        if (null == scenarioUser.getEmail()) {
            scenarioUser.setEmail(!StringUtils.EMPTY.equals(ExcelFileReader.getCobaltEmail(scenarioUser.getUserName())) ? ExcelFileReader
                    .getCobaltEmail(scenarioUser.getUserName()) : "test_user@mailinator");
        }
        if (null == scenarioUser.role) {
            scenarioUser.setRole("DEFAULT_USER");
        }
        if (null == scenarioUser.regKey) {
            scenarioUser.setRegKey(System.getProperty("regKey", ""));
        }
        if (null == scenarioUser.getRouting()) {
            scenarioUser.setRouting(Routing.DEFAULT);
        }
        if (null == scenarioUser.getClientId()) {
            scenarioUser.setClientId("TEST01");
        }

        if (null == scenarioUser.getLoginRequired()) {
            scenarioUser.setLoginRequired("YES");
        }
        if (null == scenarioUser.getMandatoryRouting()) {
            scenarioUser.setMandatoryRouting("NO");
        }
        if (null == scenarioUser.getAdditionalUserName()) {
            scenarioUser.setAdditionalUserName(ExcelFileReader.getAdditionalUserName(scenarioUser.getUserName()));
        }
        return scenarioUser;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public String getUserName() {
        return ExcelFileReader.AdditionalUserColumns.isUseAdditionalUserEnabled() ? StringUtils.defaultIfBlank(getAdditionalUserName(), userName) : userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Routing getRouting() {
        return routing;
    }

    public void setRouting(final Routing routing) {
        this.routing = routing;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public boolean isNewSession() {
        return newSession;
    }

    public void setNewSession(final boolean newSession) {
        this.newSession = newSession;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getRegKey() {
        return regKey;
    }

    public void setRegKey(final String regKey) {
        this.regKey = regKey;
    }

    public static boolean isUserFirstUser(CobaltUser that) {
        return null == that.getUserName() && null == that.getProduct() && null == that.getClientId() && that.role == null;
    }

    public String getLoginRequired() {
        return loginRequired;
    }

    public void setLoginRequired(String loginRequired) {
        this.loginRequired = loginRequired;
    }


    public String getMandatoryRouting() {
        return mandatoryRouting;
    }

    public void setMandatoryRouting(String mandatoryRouting) {
        this.mandatoryRouting = mandatoryRouting;
    }

    public boolean equalTo(CobaltUser that) {

        LOG.info("Current Scenario User = {}", this);
        LOG.info("Previous Scenario User = {}", that);

        if (isUserFirstUser(that)) {
            LOG.info("User is First Time User");
            return false;
        }

        if (!this.role.equalsIgnoreCase(that.getRole())) {
            LOG.info("Users Roles don't match");
            return false;
        } else {
            if (null == this.getUserName()) {
                this.setUserName(that.getUserName());
            }
        }

        if (!this.getRegKey().equalsIgnoreCase(that.getRegKey())) {
            LOG.info("The Registration Key doesn't match");
            return false;
        }

        if (!this.getUserName().equalsIgnoreCase(that.getUserName())) {
            LOG.info("Users don't match");
            return false;
        }

        if (this.getProduct() != that.getProduct()) {
            LOG.info("Users Products don't match");
            return false;
        }

        if (this.routing != that.routing) {
            LOG.info("Users routing don't match");
            return false;
        }

        if (!this.getClientId().equalsIgnoreCase(that.getClientId())) {
            LOG.info("Users ClientId don't match");
            return false;
        }

        if (!this.isSkipHandleClientIdImplicitly() == that.isSkipHandleClientIdImplicitly()) {
            LOG.info("Users skipHandleClientIdImplicitly don't match");
            return false;
        }

        return !this.isNewSession();
    }


    public String toString() {
        StringBuilder user = new StringBuilder();
        user.append("{" + LF)
                .append("product: " + product + LF)
                .append("userName: " + getUserName() + LF)
                .append("role: " + role + LF)
                .append("regKey: " + regKey + LF)
                .append("routing: " + routing + LF)
                .append("clientId: " + clientId + LF)
                .append("email: " + email + LF)
                .append("newSession: " + newSession + LF)
                .append("loginRequired: " + loginRequired + LF)
                .append("mandatoryRouting: " + mandatoryRouting + LF)
                .append("skipHandleClientIdImplicitly: " + skipHandleClientIdImplicitly + LF)
                .append("Session ID : ").append(StringUtils.defaultIfBlank(sessionId, "session yet to be created for the user")).append(LF)
                .append("}");
        return user.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAdditionalUserName() {
        return additionalUserName;
    }

    public void setAdditionalUserName(String additionalUserName) {
        this.additionalUserName = additionalUserName;
    }

    public String getAdditionalEmail() {
        return additionalEmail;
    }

    public void setAdditionalEmail(String additionalEmail) {
        this.additionalEmail = additionalEmail;
    }

    public boolean isSkipHandleClientIdImplicitly() {
        return skipHandleClientIdImplicitly;
    }

    public void setSkipHandleClientIdImplicitly(boolean skipHandleClientIdImplicitly) {
        this.skipHandleClientIdImplicitly = skipHandleClientIdImplicitly;
    }

}
