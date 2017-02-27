package com.thomsonreuters.pageobjects.rest;

import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceLinkBuilderImpl;

/**
 * Created by Pavel_Ardenka on 12/12/2016.
 */
public class LinkBuilderRestUtils {

    private RestServiceLinkBuilderImpl restServiceLinkBuilder = new RestServiceLinkBuilderImpl();

    public String getSearchResultsGeneratedLink() {
        return restServiceLinkBuilder.getGenerateLinkResponse().getUrl();
    }

}
