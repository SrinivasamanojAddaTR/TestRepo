package com.thomsonreuters.pageobjects.rest.model.request.favourites;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thomsonreuters.pageobjects.rest.model.request.ABaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel_Ardenka on 09/12/2016.
 */
public class DeleteFavouritesRequest extends ABaseRequest {

    private static final String FAVOURITE_IDS = "favoriteIds";

    private List<String> favouriteIds = new ArrayList<>();

    public List<String> getFavouriteIds() {
        return favouriteIds;
    }

    public void setFavouriteIds(List<String> favouriteIds) {
        this.favouriteIds = favouriteIds;
    }

    @Override
    public ObjectNode getNode() {
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        jsonFactory.setCodec(objectMapper);
        ObjectNode deleteFavouritesNode = objectMapper.createObjectNode();
        deleteFavouritesNode.set(FAVOURITE_IDS, objectMapper.valueToTree(favouriteIds));
        return deleteFavouritesNode;
    }
}
