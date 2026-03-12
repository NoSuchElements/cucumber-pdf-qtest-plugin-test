@QTEST_TC_1203
Feature: Hook and step failures
  
  Scenario: Intentional step failure
    Given the system is initialized
    When a step fails
    Then validation passes
  
  Scenario: Passing scenario after failure
    Given a user is on the home page
    When the user performs an action
    Then validation passes
