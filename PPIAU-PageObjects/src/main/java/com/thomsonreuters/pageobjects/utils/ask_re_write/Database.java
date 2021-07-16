package com.thomsonreuters.pageobjects.utils.ask_re_write;

import com.thomsonreuters.pageobjects.utils.ask_re_write.data_manager.AskSQLproperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Database {
    private static final Logger LOG = LoggerFactory.getLogger(Database.class);
    //TODO Need to verify and remove
   //private static final String DB_DRIVER ="oracle.jdbc.driver.OracleDriver";
    private static final String DB_USERNAME = "askplc_test";
    private static final String DB_PASSWORD = "asktest";
    private static final String DB_URL = System.getProperty("base.legacy.url");

    private Connection connection;

    public Database() {
        createDBconnection();
    }

    private String getDBUrl(){
        String url = null;
        //TODO Need to verify and remove
        /*try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            LOG.info("Error loading driver: {}" , e.getMessage());
        }*/
        switch (DB_URL) {

            case "102":
                url = "jdbc:oracle:thin:@//ask-rewrite-dev.emea1.cis.trcloud:1521/xe";
                break;

            case "DEMO":
                url = "jdbc:oracle:thin:@//ask-rewrite-dev.emea1.cis.trcloud:1521/xe";
                break;

            case "100":
                url = "jdbc:oracle:thin:@//ask-rewrite-qa.emea1.cis.trcloud:1521/xe";
                break;
                default:break;

        }
        return url;
    }

    public String getDBusername(){
        return DB_USERNAME;
    }

    public String getDBpassword(){
        return DB_PASSWORD;
    }

    //TODO Need to verify and remove
    /*public String getDBdriver(){
        return DB_DRIVER;
    }*/

    private void createDBconnection() {
        try {
            connection = DriverManager.getConnection(getDBUrl(), DB_USERNAME, DB_PASSWORD);
        } catch (SQLException sqlException) {
            LOG.info("Exception has occured while connecting to DB {}",sqlException.getMessage());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            LOG.error(sqlException.getMessage());
        }
    }

    public ResultSet executeSQLQueryAndReturnResult(String sqlQuery) throws SQLException {
        try(ResultSet resultSet= connection.createStatement().executeQuery(sqlQuery)){
            return resultSet;
        }
    }

    public String returnResultFromFirstColumn(String sqlQuery, Object... parameter) throws SQLException {
        ResultSet result = executeSQLQueryAndReturnResult(AskSQLproperties.getSQLquery(sqlQuery, parameter));
        String selectResult = null;
        try {
            if (result.next()) {
                selectResult = result.getString(1);
            } else {
                throw new SQLException("There are no records in the database for query: " + sqlQuery);
            }
        } catch (SQLException sqlException) {
            LOG.error("{} Request: {}" ,sqlException.getMessage(),
                    AskSQLproperties.getSQLquery(AskSQLproperties.getSQLquery(sqlQuery, parameter)));
        }
        return selectResult;
    }
}