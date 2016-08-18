package com.thomsonreuters.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-htmlreport/AnnotationsCommonReport", "junit:target/junit_cucumber.xml", "json:target/json-files/RunAnnotationsCommonTest.json"},
        features = "src/test/resources/com/thomsonreuters/features/annotations/common",
        tags = {"~@wip", "~@manual"},
        glue = {"com.thomsonreuters.step_definitions"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE)
public class RunAnnotationsCommonTest {
    private static final Logger LOG = LoggerFactory.getLogger(RunAnnotationsCommonTest.class);

    @BeforeClass
    public static void reporting() {
        if (System.getProperty("username").equals("None")) {
            System.setProperty("username", "auAnnotationUser1");
            System.setProperty("password", "Password1");
            LOG.info("The credentials have been set");
        } else {
            LOG.info("Username is pre-defined in the Run Command as: " + System.getProperty("username"));
        }
    }
}