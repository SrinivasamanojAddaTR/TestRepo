package com.thomsonreuters.pageobjects.rest.service.impl;

import com.thomsonreuters.pageobjects.rest.model.request.favourites.DeleteFavouritesRequest;
import com.thomsonreuters.pageobjects.rest.model.response.FavouriteGroupsResponse;
import com.thomsonreuters.pageobjects.rest.model.response.favourites.Favourite;
import com.thomsonreuters.pageobjects.rest.model.response.favourites.FavouritesGroup;
import com.thomsonreuters.pageobjects.rest.service.RestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 09/12/2016.
 */
@Service
public class RestServiceFavouritesImpl extends RestServiceImpl implements RestService {

    private FavouriteGroupsResponse getFavouritesGroups(HttpHeaders httpHeaders) {
        LOG.info("-------------------BEGIN getFavouritesGroups --------------------");
        String requestTo = webDriverDiscovery.getCurrentRootAddress(true) + "/Foldering/v7/" + getUserName() + "/categoryPage/groups/all?usageTypeDbValue=0";
        LOG.info("TO: {}", requestTo);
        LOG.info("HEADERS: {}", httpHeaders);
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        LOG.info("REQ: {}", requestEntity);
        HttpEntity<FavouriteGroupsResponse> response = getRestTemplate().exchange(requestTo, HttpMethod.GET, requestEntity, FavouriteGroupsResponse.class);
        LOG.info("RESP: {}", response);
        LOG.info("-------------------END getFavouritesGroups--------------------");
        return response.getBody();
    }

    public void deletePLFavourites() {
        HttpHeaders httpHeaders = configurePLHeaders();
        deleteFavourites(getFavouritesGroups(httpHeaders).getFavouritesGroups(), httpHeaders);
    }

    public void deleteWLNFavourites() {
        HttpHeaders httpHeaders = configureWLNHeaders();
        deleteFavourites(getFavouritesGroups(httpHeaders).getFavouritesGroups(), httpHeaders);
    }

    private void deleteFavourites(List<FavouritesGroup> favouritesGroups, HttpHeaders httpHeaders) {
        List<String> favouritesIds = new ArrayList<>();
        for (FavouritesGroup favouritesGroup : favouritesGroups) {
            for (Favourite favourite : favouritesGroup.getFavourites()) {
                favouritesIds.add(favourite.getId());
            }
        }
        if (!favouritesIds.isEmpty()) {
            LOG.info("-------------------BEGIN deleteFavourites --------------------");
            DeleteFavouritesRequest deleteFavouritesRequest = new DeleteFavouritesRequest();
            deleteFavouritesRequest.setFavouriteIds(favouritesIds);
            httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
            String requestTo = webDriverDiscovery.getCurrentRootAddress(true) + "/Foldering/v6/" + getUserName() + "/groups/favorites/delete";
            LOG.info("TO: {}", requestTo);
            LOG.info("HEADERS: {}", httpHeaders);
            HttpEntity<String> requestEntity = new HttpEntity<>(deleteFavouritesRequest.getNode().toString(), httpHeaders);
            LOG.info("BODY: {}", requestEntity.getBody());
            HttpEntity<String> response = getRestTemplate().exchange(requestTo, HttpMethod.POST, requestEntity, String.class);
            LOG.info("RESP: {}", response);
            LOG.info("-------------------END deleteFavourites--------------------");
        }
    }

    public void deletePLFavouriteGroups() {
        HttpHeaders httpHeaders = configurePLHeaders();
        deleteFavouriteGroups(getFavouritesGroups(httpHeaders).getFavouritesGroups(), httpHeaders);
    }

    public void deleteWLNFavouriteGroups() {
        HttpHeaders httpHeaders = configureWLNHeaders();
        deleteFavouriteGroups(getFavouritesGroups(httpHeaders).getFavouritesGroups(), httpHeaders);
    }

    private void deleteFavouriteGroups(List<FavouritesGroup> favouritesGroups, HttpHeaders httpHeaders) {
        for (FavouritesGroup favouritesGroup : favouritesGroups) {
            LOG.info("-------------------BEGIN deleteFavouriteGroups --------------------");
            String requestTo = webDriverDiscovery.getCurrentRootAddress(true) +
                    "/Foldering/v6/" + getUserName() + "/categoryPage/group/" + favouritesGroup.getId();
            LOG.info("TO : {}", requestTo);
            LOG.info("HEADERS : {}", httpHeaders);
            ResponseEntity<String> response = getRestTemplate().exchange(requestTo, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), String.class);
            LOG.info("RESP : {}", response);
            LOG.info("-------------------END deleteFavouriteGroups--------------------");
        }
    }

    public HttpHeaders configurePLHeaders() {
        HttpHeaders httpHeaders = configureHeaders();
        httpHeaders.set("Accept-Language", "en-GB");
        httpHeaders.set("x-cobalt-pcid", getXCobaltPcId());
        httpHeaders.set("x-cobalt-rtid", getXCobaltRtId());
        return httpHeaders;
    }

    public HttpHeaders configureWLNHeaders() {
        HttpHeaders httpHeaders = configureHeaders();
        httpHeaders.set("Accept-Language", "en-US");
        httpHeaders.set("x-cobalt-pcid", getWlnXCobaltPcId());
        httpHeaders.set("x-cobalt-rtid", getWlnXCobaltRtId());
        return httpHeaders;
    }

    public HttpHeaders configureHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Accept-Encoding", "identity");
        httpHeaders.set("Cookie", webDriverDiscovery.getBrowserCookiesAsString());
        httpHeaders.set("Connection", "keep-alive");
        httpHeaders.set("Host", webDriverDiscovery.getCurrentRootAddress(false));
        httpHeaders.set("Referer", webDriverDiscovery.getWebDriver().getCurrentUrl());
        httpHeaders.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");
        httpHeaders.set("X-Requested-With", "XMLHttpRequest");
        httpHeaders.set("x-cobalt-exectype", "async");
        httpHeaders.set("x-cobalt-host", "foldering.int.next." + getEnvironment() + ".westlaw.com");
        return httpHeaders;
    }
}
