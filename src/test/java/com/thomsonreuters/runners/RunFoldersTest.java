package com.thomsonreuters.runners;

import com.thomsonreuters.pageobjects.common.BaseCucumberTestRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-htmlreport/ANZFoldersReport", "junit:target/junit_cucumber.xml", "json:target/json-files/RunFoldersTest.json", "com.epam.reportportal.cucumber.ScenarioReporter"},
        features = "src/test/resources/com/thomsonreuters/features/folders",
        tags = {"~@wip", "~@manual"},
        glue = {"com.thomsonreuters.step_definitions", "com.thomsonreuters.hooks"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE)
public class RunFoldersTest extends BaseCucumberTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(RunFoldersTest.class);

    @BeforeClass
    public static void reporting() {
        if (System.getProperty("username").equals("None")) {
            System.setProperty("username", "ANZtestuser2");
            System.setProperty("password", "Password1");
        }
        else {
            LOG.info("Username is pre-defined in the Run Command as: " + System.getProperty("username"));
        }
    }
}