@QTEST_TC_1202
Feature: Exception
  
  Scenario: Handle expected exception
    Given the system is initialized
    When an exception occurs
    Then the result should be "EXCEPTION_HANDLED"
  
  Scenario: Exception recovery
    Given a user is on the home page
    When an exception occurs
    Then the system state is valid
