package com.thomsonreuters.pageobjects.utils.ask_re_write.data_manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AskSQLproperties {
    private static final Logger LOG = LoggerFactory.getLogger(AskSQLproperties.class);

    private static final Properties properties;

    static {
        properties = new Properties();
        InputStream propertiesStream = AskSQLproperties.class.getResourceAsStream("/sqlQueries.properties");
        try {
            properties.load(propertiesStream);
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public static String getSQLquery(String tableAndColumn) {
        return properties.getProperty(tableAndColumn);
    }

    public static String getSQLquery(String tableAndColumn, Object... parameter) {
        return String.format(properties.getProperty(tableAndColumn), parameter);
    }
}
