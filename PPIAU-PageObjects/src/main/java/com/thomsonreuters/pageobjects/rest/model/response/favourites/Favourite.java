package com.thomsonreuters.pageobjects.rest.model.response.favourites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Pavel_Ardenka on 09/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Favourite {

    @JsonProperty("favoriteId")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
