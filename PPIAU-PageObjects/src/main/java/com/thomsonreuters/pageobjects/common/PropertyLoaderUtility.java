package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.driver.exception.PageOperationException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public final class PropertyLoaderUtility {

    private PropertyLoaderUtility(){
    }

    public static Properties loadProperties(String propertiesFile){
        try{
            return PropertiesLoaderUtils.loadAllProperties(propertiesFile);
        } catch(IOException exception){
            throw new PageOperationException("Cannot load properties file");
        }
    }
}
