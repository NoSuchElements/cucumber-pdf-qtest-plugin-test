Feature: No tag feature
  
  Scenario: Scenario without tags
    Given the system is initialized
    When the user performs an action
    Then validation passes
  
  Scenario: Another untagged scenario
    Given a user is on the home page
    When the user clicks "Submit"
    Then the page displays "Success"
