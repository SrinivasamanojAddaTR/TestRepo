package com.thomsonreuters.pageobjects.rest.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomsonreuters.pageobjects.rest.model.response.favourites.FavouritesGroup;

import java.util.List;

/**
 * Created by Pavel_Ardenka on 09/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteGroupsResponse extends ABaseResponse {

    @JsonProperty("nonMemberGroups")
    private List<FavouritesGroup> favouritesGroups;

    public List<FavouritesGroup> getFavouritesGroups() {
        return favouritesGroups;
    }

    public void setFavouritesGroups(List<FavouritesGroup> favouritesGroups) {
        this.favouritesGroups = favouritesGroups;
    }
}
