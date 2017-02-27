package com.thomsonreuters.pageobjects.rest.service.impl;

import com.thomsonreuters.pageobjects.rest.model.request.favourites.DeleteFavouritesRequest;
import com.thomsonreuters.pageobjects.rest.model.request.linkbuilder.GenerateLinkRequest;
import com.thomsonreuters.pageobjects.rest.model.response.linkbuilder.GenerateLinkResponse;
import com.thomsonreuters.pageobjects.rest.service.RestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by Pavel_Ardenka on 12/12/2016.
 */
public class RestServiceLinkBuilderImpl extends RestServiceImpl implements RestService {

    public GenerateLinkResponse getGenerateLinkResponse() {
        LOG.info("------------------- BEGIN getGenerateLinkResponse --------------------");
        HttpHeaders httpHeaders = configureHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        String requestTo = webDriverDiscovery.getCurrentRootAddress(true) + "/V1/SharedLink/Generate";
        LOG.info("TO: " + requestTo);
        LOG.info("HEADERS: " + httpHeaders);
        GenerateLinkRequest generateLinkRequest = new GenerateLinkRequest();
        generateLinkRequest.setClientId(getUserClientId());
        generateLinkRequest.setResultGuid(getSearchResultsId());
        generateLinkRequest.setType(getSearchContentType());
        HttpEntity<String> requestEntity = new HttpEntity<>(generateLinkRequest.getNode().toString(), httpHeaders);
        LOG.info("BODY: " + requestEntity.getBody());
        HttpEntity<GenerateLinkResponse> response = getRestTemplate().exchange(requestTo, HttpMethod.POST, requestEntity, GenerateLinkResponse.class);
        LOG.info("RESP: " + response.toString());
        LOG.info("------------------- END getGenerateLinkResponse --------------------");
        return response.getBody();
    }

    /**
     * Get user's search result id via Java Script
     * WARNING! Perform a search before invocation of this method
     *
     * @return Search results id
     */
    private String getSearchResultsId() {
        String jsScript = "return Cobalt.Search.PageContext.SearchResult.getGuid();";
        return (String) webDriverDiscovery.getRemoteWebDriver().executeScript(jsScript);
    }
    /**
     * Get content type of current search result id via Java Script
     * WARNING! Perform a search before invocation of this method
     *
     * @return Search content type
     */
    private String getSearchContentType() {
        String jsScript = "return Cobalt.Search.PageContext.SearchResult.getContentType();";
        return (String) webDriverDiscovery.getRemoteWebDriver().executeScript(jsScript);
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
        httpHeaders.set("Referer", webDriverDiscovery.getCurrentUrl());
        httpHeaders.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");
        httpHeaders.set("X-Requested-With", "XMLHttpRequest");
        httpHeaders.set("x-cobalt-exectype", "async");
        httpHeaders.set("x-cobalt-pcid", getXCobaltPcId());
        httpHeaders.set("x-cobalt-rtid", getXCobaltRtId());
        httpHeaders.set("x-cobalt-documentContentCacheKey", getXCobaltDocumentContentCacheKey());
        return httpHeaders;
    }
}
