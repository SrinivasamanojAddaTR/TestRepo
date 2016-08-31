package com.thomsonreuters.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-htmlreport/ANZLoginReport", "junit:target/junit_cucumber.xml", "json:target/json-files/RunLoginTest.json"},
        features = "src/test/resources/com/thomsonreuters/features/login",
        tags = {"~@wip", "~@manual"},
        glue = {"com.thomsonreuters.step_definitions"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE)
public class RunLoginTest {

    @BeforeClass
    public static void reporting() {
        if (System.getProperty("username").equals("None")) {
            System.setProperty("username", "ANZtestuser2");
            System.setProperty("password", "Password1");
        }
        else {
            System.out.println("Username is pre-defined in the Run Command as: " + System.getProperty("username"));
        }
    }
}