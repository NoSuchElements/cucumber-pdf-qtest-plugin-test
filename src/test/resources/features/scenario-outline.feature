@QTEST_TC_1205
Feature: Scenario And Scenario Outline
  
  Scenario: Regular scenario
    Given the system is initialized
    When the user performs an action
    Then validation passes
  
  Scenario Outline: Parameterized scenario
    Given a value of <input>
    When multiplied by <multiplier>
    Then result is <output>
    
    Examples:
      | input | multiplier | output |
      | 2     | 3          | 6      |
      | 5     | 4          | 20     |
      | 10    | 2          | 20     |
