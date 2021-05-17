package com.thomsonreuters.runners;

import com.thomsonreuters.pageobjects.common.BaseCucumberTestRunner;
import com.thomsonreuters.pageobjects.utils.User;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "junit:target/junit_cucumber.xml", "json:target/json-files/ReRunURL.json", "com.epam.reportportal.cucumber.ScenarioReporter"},
        features = "@target/ReRunURL.txt",
        glue = {"com.thomsonreuters.step_definitions", "com.thomsonreuters.hooks"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE)
public class ReRunURL extends BaseCucumberTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ReRunURL.class);

    @BeforeClass
    public static void reporting() {
        if (System.getProperty("username", "None").equals("None")) {
            User.getInstance().setUserName("AUtestuser11");
            User.getInstance().setPassword("Password1");
            LOG.info("The credentials have been set");
        }
        else {
            LOG.info("Username is pre-defined in the Run Command as: " + System.getProperty("username"));
        }
    }
}