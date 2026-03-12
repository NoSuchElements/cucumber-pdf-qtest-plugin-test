@QTEST_TC_1204
Feature: Long long feature & Scenario Names
  
  Scenario: This is an extremely long scenario name that tests how the PDF report handles very lengthy scenario titles that might extend beyond normal display boundaries
    Given the system is initialized
    When the user performs an action
    Then validation passes
  
  Scenario: Another scenario with a considerably extended name to verify consistent handling of lengthy text in report generation
    Given a user is on the home page
    When processing 5 items
    Then the system state is valid
