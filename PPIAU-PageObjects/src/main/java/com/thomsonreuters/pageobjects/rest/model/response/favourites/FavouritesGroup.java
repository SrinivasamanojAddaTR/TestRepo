package com.thomsonreuters.pageobjects.rest.model.response.favourites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Pavel_Ardenka on 09/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouritesGroup {

    @JsonProperty("id")
    private String id;

    @JsonProperty("favorites")
    private List<Favourite> favourites;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }
}
