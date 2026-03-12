@QTEST_TC_1201
Feature: DataTable And DocString
  
  Scenario: Using DataTable
    Given the following data:
      | Name    | Age | City       |
      | Alice   | 30  | New York   |
      | Bob     | 25  | London     |
      | Charlie | 35  | Paris      |
    When the user performs an action
    Then validation passes
  
  Scenario: Using DocString
    Given the following content:
      """
      This is a multi-line string
      that demonstrates DocString functionality
      in Cucumber feature files.
      
      It can contain multiple paragraphs
      and preserve formatting.
      """
    When processing 3 items
    Then the system state is valid
