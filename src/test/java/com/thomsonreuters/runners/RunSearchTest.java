package com.thomsonreuters.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-htmlreport/ANZSearchReport", "junit:target/junit_cucumber.xml", "json:target/json-files/RunSearchTest.json"},
        features = "src/test/resources/com/thomsonreuters/features/search",
        tags = {"~@wip", "~@manual"},
        glue = {"com.thomsonreuters.step_definitions"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE)
public class RunSearchTest {

    @BeforeClass
    public static void reporting() {
        if (System.getProperty("username").equals("None")) {
            System.setProperty("username", "AUtestuser2");
            System.setProperty("password", "Password1");
        }
        else {
            System.out.println("Username is pre-defined in the Run Command as: " + System.getProperty("username"));
        }
    }
}