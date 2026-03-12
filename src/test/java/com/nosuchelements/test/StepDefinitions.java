package com.nosuchelements.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import java.util.Map;
import java.util.List;

/**
 * Step definitions for QTest plugin test scenarios.
 * Provides implementations for all steps used across feature files.
 */
public class StepDefinitions {
    
    private Scenario currentScenario;
    private String lastResult;
    
    @Before
    public void setUp(Scenario scenario) {
        this.currentScenario = scenario;
        System.out.println("Starting scenario: " + scenario.getName());
    }
    
    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Finished scenario: " + scenario.getName() 
            + " - Status: " + scenario.getStatus());
    }
    
    // Basic Given steps
    @Given("a user is on the home page")
    public void userOnHomePage() {
        System.out.println("User navigated to home page");
    }
    
    @Given("the system is initialized")
    public void systemInitialized() {
        System.out.println("System initialization complete");
    }
    
    @Given("a feature with multiple scenarios")
    public void featureWithMultipleScenarios() {
        System.out.println("Feature setup complete");
    }
    
    // DataTable steps
    @Given("the following data:")
    public void followingData(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        System.out.println("Processing " + data.size() + " data rows");
        for (Map<String, String> row : data) {
            System.out.println("Row: " + row);
        }
    }
    
    // DocString steps
    @Given("the following content:")
    public void followingContent(String docString) {
        System.out.println("Content length: " + docString.length());
        System.out.println("Content: " + docString);
    }
    
    // When steps
    @When("the user performs an action")
    public void userPerformsAction() {
        System.out.println("Action performed");
        lastResult = "SUCCESS";
    }
    
    @When("the user clicks {string}")
    public void userClicksElement(String element) {
        System.out.println("User clicked: " + element);
    }
    
    @When("an exception occurs")
    public void exceptionOccurs() {
        System.out.println("Simulating exception scenario");
        lastResult = "EXCEPTION_HANDLED";
    }
    
    @When("a step fails")
    public void stepFails() {
        System.out.println("Simulating step failure");
        throw new AssertionError("Intentional test failure for failure.feature");
    }
    
    @When("processing {int} items")
    public void processingItems(Integer count) {
        System.out.println("Processing " + count + " items");
    }
    
    // Then steps
    @Then("the result should be {string}")
    public void resultShouldBe(String expected) {
        System.out.println("Expected: " + expected + ", Actual: " + lastResult);
        if (!expected.equals(lastResult) && lastResult != null) {
            throw new AssertionError("Result mismatch");
        }
    }
    
    @Then("the page displays {string}")
    public void pageDisplays(String content) {
        System.out.println("Page displays: " + content);
    }
    
    @Then("validation passes")
    public void validationPasses() {
        System.out.println("Validation successful");
    }
    
    @Then("the system state is valid")
    public void systemStateValid() {
        System.out.println("System state validated");
    }
    
    // Screenshot simulation steps
    @When("taking a screenshot")
    public void takingScreenshot() {
        System.out.println("Screenshot captured");
    }
    
    // Scenario Outline steps
    @Given("a value of {int}")
    public void valueOf(Integer value) {
        System.out.println("Value: " + value);
    }
    
    @When("multiplied by {int}")
    public void multipliedBy(Integer multiplier) {
        System.out.println("Multiplier: " + multiplier);
    }
    
    @Then("result is {int}")
    public void resultIs(Integer expected) {
        System.out.println("Expected result: " + expected);
    }
}
