/*package com.thomsonreuters.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-htmlreport/ANZBrowseReport", "junit:target/junit_cucumber.xml", "json:target/json-files/RunBreadCrumbTest.json"},
        features = "src/test/resources/com/thomsonreuters/features/breadcrumb",
        tags = {"~@wip", "~@manual"},
        glue = {"com.thomsonreuters.step_definitions", "com.thomsonreuters.hooks"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE)
public class RunBreadCrumbTest {

    private static final Logger LOG = LoggerFactory.getLogger(RunBreadCrumbTest.class);

    @BeforeClass
    public static void reporting() {
        if (System.getProperty("username").equals("None")) {
            System.setProperty("username", "AUtestuser3");
            System.setProperty("password", "Password1");
        } else {
            LOG.info("Username is pre-defined in the Run Command as: " + System.getProperty("username"));
        }
    }
}*/