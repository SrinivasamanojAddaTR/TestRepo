package com.thomsonreuters.pageobjects.rest.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public interface RestService {

    HttpHeaders configureHeaders();
    RestTemplate getRestTemplate();
    String getCookies();
    String getCurrentBaseUrl();
    String getUserName();
    String getProtocol();

}
