package com.thomsonreuters.pageobjects.utils.ask_re_write.data_manager;


import com.google.common.collect.Lists;
import com.thomsonreuters.pageobjects.exceptions.QueryNotInitialized;
import com.thomsonreuters.pageobjects.utils.ask_re_write.ask_query.NewAskQuery;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.ArrayList;
import java.util.List;


public class AskQueryDataYamlReader {
    private static final String ASK_QUERIES_DATA_FILE = "/askQueriesData.yaml";
    private List<Object> queriesDataSets;

    /**
     * reads the data from the specified .yaml file and passes it into
     * the {@code Constructor} for initialising the desired Object
     */
    public AskQueryDataYamlReader() {
        queriesDataSets = new ArrayList<>();
        Constructor constructorForAskFormData = new Constructor(NewAskQuery.class);
        Iterable<Object> formData = new Yaml(constructorForAskFormData).loadAll(AskQueryDataYamlReader.class.getResourceAsStream(ASK_QUERIES_DATA_FILE));
        queriesDataSets = Lists.newArrayList(formData);
    }

    /**
     * @return the new {@link NewAskQuery} initialised from the .yaml file that was read
     * in {@link AskQueryDataYamlReader} and has the indicated {@code description} in it
     *
     * @param description
     *        the {@link String} representation of the description in which the search
     *        will be carried out among a number of {@link NewAskQuery} new Ask Queries
     */
    public NewAskQuery getNewAskQuery(String description) {
        for (Object oneQueryData : queriesDataSets) {
            NewAskQuery newAskQuestion = (NewAskQuery) oneQueryData;
            if (newAskQuestion.getDescription().contains(description)) {
                return newAskQuestion;
            }
        }
        throw new QueryNotInitialized("New Ask Query was not initialised from file: " +
                ASK_QUERIES_DATA_FILE + " by description: " + description);
    }
}