package com.thomsonreuters.pageobjects.rest;

import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceFavouritesImpl;

/**
 * Created by Pavel_Ardenka on 09/12/2016.
 */
public class FavouriteRestUtils {

    private RestServiceFavouritesImpl restServiceFavourites = new RestServiceFavouritesImpl();

    public void deletePLFavourites() {
        restServiceFavourites.deletePLFavourites();
        restServiceFavourites.deletePLFavouriteGroups();
    }

    public void deleteWLNFavourites() {
        restServiceFavourites.deleteWLNFavourites();
        restServiceFavourites.deleteWLNFavouriteGroups();
    }

}
