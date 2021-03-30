package com.thomsonreuters.pageobjects.rest.service.impl;

import com.google.common.base.Splitter;
import com.thomsonreuters.pageobjects.rest.service.RestService;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Map;
import java.util.Set;

/**
 * Created by Pavel_Ardenka on 06/02/2017.
 */
public class RestServiceSecurityHeadersImpl extends RestServiceImpl implements RestService {

    private static final Logger LOG = LoggerFactory.getLogger(RestServiceSecurityHeadersImpl.class);
    private final String securityHeadersUrl = webDriverDiscovery.getCurrentRootAddress(true) + "/V1/SecurityHeaders?sessionToken=";
    private static final String CO_SESSION_TOKEN_COOKIE_NAME = "Co_SessionToken";
    private static final String SESSION_ID_PARAM_NAME = "x-cobalt-security-sessionid";
    private static final String PARAMS_DIVIDER = System.getProperty("line.separator");
    private static final String KEY_VALUE_DIVIDER = ": ";

    @Override
    public String getCurrentSession() {
        try {
            LOG.info("-------------------BEGIN--------------------");
            HttpHeaders httpHeaders = configureHeaders();
            String requestTo = securityHeadersUrl + getCoSessionTokenFromCookies();
            LOG.info("TO: " + requestTo);
            LOG.info("HEADERS: " + httpHeaders);
            HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
            LOG.info("REQ: " + requestEntity.toString());
            HttpEntity<String> response = getRestTemplate().exchange(requestTo, HttpMethod.GET, requestEntity, String.class);
            LOG.info("RESP: " + response.toString());
            LOG.info("-------------------END--------------------");
            String responseString = response.getBody();
            return getParametersFromResponse(responseString).get(SESSION_ID_PARAM_NAME);
        } catch (Exception ex) {
            LOG.info("Unable to get session info via Security Headers request.", ex);
            LOG.info("Attempt to get session ID via UDS...");
            return super.getCurrentSession();
        }
    }

    @Override
    public HttpHeaders configureHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Accept-Encoding", "identity");
        httpHeaders.set("Accept-Language", "en-GB");
        httpHeaders.set("Cookie", webDriverDiscovery.getBrowserCookiesAsString());
        httpHeaders.set("Connection", "keep-alive");
        httpHeaders.set("Host", webDriverDiscovery.getCurrentRootAddress(false));
        return httpHeaders;
    }

    /**
     * Get the value of {@link #CO_SESSION_TOKEN_COOKIE_NAME} cookie from browser cookies
     *
     * @return The value of cookie
     */
    private String getCoSessionTokenFromCookies() {
        Set<Cookie> cookies = webDriverDiscovery.getWebDriver().manage().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CO_SESSION_TOKEN_COOKIE_NAME)) {
                return cookie.getValue();
            }
        }
        throw new RuntimeException("Unable to obtain cookie with name " + CO_SESSION_TOKEN_COOKIE_NAME);
    }

    /**
     * Get map with keys and values from security header response. The response should be in the format:
     * Key1: value1
     * Key2: value2
     * ...
     *
     * @param response The string representation of the response in required format
     * @return Map with keyas and values from the text responce
     */
    private Map<String, String> getParametersFromResponse(String response) {
        return Splitter.on(PARAMS_DIVIDER).withKeyValueSeparator(KEY_VALUE_DIVIDER).split(response);
    }
}
