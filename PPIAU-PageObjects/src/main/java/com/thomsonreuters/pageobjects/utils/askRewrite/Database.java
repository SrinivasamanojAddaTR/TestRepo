package com.thomsonreuters.pageobjects.utils.askRewrite;

import com.thomsonreuters.pageobjects.utils.askRewrite.dataManager.AskSQLproperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {
    private static final Logger LOG = LoggerFactory.getLogger(Database.class);

    private static final String DB_DRIVER ="oracle.jdbc.driver.OracleDriver";
    private static final String DB_USERNAME = "askplc_test";
    private static final String DB_PASSWORD = "asktest";
    private static final String DB_URL = System.getProperty("base.legacy.url");

    private Connection connection;

    public Database() {
        createDBconnection();
    }

    private String getDBUrl() throws Throwable{
        String url = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading driver: " + e);
        }
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

        }
        return url;
    }

    public String getDBusername() throws Throwable{
        return DB_USERNAME;
    }

    public String getDBpassword() throws Throwable{
        return DB_PASSWORD;
    }

    public String getDBdriver() throws Throwable{
        return DB_DRIVER;
    }

    private void createDBconnection() {
        try {
            connection = DriverManager.getConnection(getDBUrl(), DB_USERNAME, DB_PASSWORD);
        } catch (Throwable databaseConnection) {
            databaseConnection.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            LOG.error(sqlException.getMessage());
            sqlException.printStackTrace();
        }
    }

    public ResultSet executeSQLQueryAndReturnResult(String sqlQuery) {
        try {
            return connection.createStatement().executeQuery(sqlQuery);
        } catch (Throwable databaseConnection) {
            LOG.error(databaseConnection.getMessage());
            databaseConnection.printStackTrace();
        }
        return null;
    }

    public String returnResultFromFirstColumn(String sqlQuery, Object... parameter) {
        ResultSet result = executeSQLQueryAndReturnResult(AskSQLproperties.getSQLquery(sqlQuery, parameter));
        String selectResult = null;
        try {
            if (result.next()) {
                selectResult = result.getString(1);
            } else {
                throw new SQLException("There are no records in the database for query: " + sqlQuery);
            }
        } catch (SQLException sqlException) {
            LOG.error(sqlException.getMessage() + " Request: " +
                    AskSQLproperties.getSQLquery(AskSQLproperties.getSQLquery(sqlQuery, parameter)));
            sqlException.printStackTrace();
        }
        return selectResult;
    }
}