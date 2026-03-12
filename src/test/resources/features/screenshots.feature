@QTEST_TC_1206
Feature: Screenshots
  
  Scenario: Capture single screenshot
    Given a user is on the home page
    When taking a screenshot
    Then validation passes
  
  Scenario: Multiple screenshots
    Given the system is initialized
    When taking a screenshot
    And taking a screenshot
    Then the system state is valid
