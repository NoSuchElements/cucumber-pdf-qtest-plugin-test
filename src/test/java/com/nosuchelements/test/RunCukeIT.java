package com.nosuchelements.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestNG-based Cucumber runner for integration tests.
 * Generates JSON output that will be consumed by the PDF QTest plugin.
 */
@CucumberOptions(
    features = "src/test/resources/features/",
    glue = "com.nosuchelements.test",
    plugin = {
        "json:target/cucumber-json.json",
        "html:target/cucumber-reports.html",
        "pretty",
        "summary"
    },
    monochrome = true
)
public class RunCukeIT extends AbstractTestNGCucumberTests {
    // TestNG will automatically discover and run all scenarios
    // JSON report will be generated at target/cucumber-json.json
    // Plugin will process this JSON in post-integration-test phase
}
