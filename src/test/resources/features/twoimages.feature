@QTEST_TC_1208
Feature: Two Screenshots
  
  Scenario: Capture two distinct screenshots
    Given a user is on the home page
    When taking a screenshot
    And the user clicks "Next"
    And taking a screenshot
    Then the page displays "Complete"
