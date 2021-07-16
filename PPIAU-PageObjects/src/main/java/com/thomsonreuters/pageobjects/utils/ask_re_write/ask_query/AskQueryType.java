package com.thomsonreuters.pageobjects.utils.ask_re_write.ask_query;


import java.util.regex.Pattern;

public enum AskQueryType {
    QUESTION,
    COMMENT,
    FOLLOWUP;

    public static AskQueryType getQueryTypeByDescription(String description) {
        description = description.replaceAll(Pattern.compile("^\\w+").matcher(description).replaceAll(""), "");
        for (AskQueryType type : AskQueryType.values()) {
            if (type.name().toLowerCase().equals(description)) {
                return type;
            }
        }
        throw new RuntimeException("There is no such Query type for the description: " + description);
    }
}

