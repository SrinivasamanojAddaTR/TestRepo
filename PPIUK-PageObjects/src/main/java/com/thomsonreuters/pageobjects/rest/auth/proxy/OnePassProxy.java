package com.thomsonreuters.pageobjects.rest.auth.proxy;


import com.thomsonreuters.pageobjects.rest.auth.model.PostForRegKeysResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OnePassProxy {
    private static final String HOST = "http://onepassservicesqa.int.westlaw.com";
    private RestTemplate restTemplate = new RestTemplate();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(OnePassProxy.class);

    /**
     * POST request to Onepass module with endpoint '/onepass/v2/authenticate/onepass'
     * retrieve user information included reg keys
     *
     * @param userName  user name for login to website
     * @param password  password for login to website
     * @param productID for cobalt 'CBT'
     * @return json object
     */
    public PostForRegKeysResponse postForRegKeys(String userName, String password, String productID) {
        HttpHeaders httpHeaders = configureHeaders();
        String requestStr = "{\"Header\":{\"ProductIdentifier\":\"" + productID + "\"},\"Username\":\"" + userName + "\",\"Password\":\"" + password + "\",\"IncludeProfile\":true}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestStr, httpHeaders);
        String requestTo = HOST + "/onepass/v2/authenticate/onepass";
        LOG.info("postForRegKeys REQUEST '" + requestEntity.toString() + "', TO: " + requestTo);
        return restTemplate.postForObject(requestTo, requestEntity, PostForRegKeysResponse.class);
    }

    private HttpHeaders configureHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=utf-8");
        return httpHeaders;
    }
}
