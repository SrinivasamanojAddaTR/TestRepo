package com.thomsonreuters.pageobjects.utils.ask_re_write.data_manager;

import com.thomsonreuters.pageobjects.exceptions.PropertyFileReaderException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AskSQLproperties {

    private AskSQLproperties()
    {

    }

    private static final Properties properties;

    static {
        properties = new Properties();
        InputStream propertiesStream = AskSQLproperties.class.getResourceAsStream("/sqlQueries.properties");
        try {
            properties.load(propertiesStream);
        } catch (IOException exception) {
            throw new PropertyFileReaderException(exception.getMessage());
        }
    }

    public static String getSQLquery(String tableAndColumn) {
        return properties.getProperty(tableAndColumn);
    }

    public static String getSQLquery(String tableAndColumn, Object... parameter) {
        return String.format(properties.getProperty(tableAndColumn), parameter);
    }
}
