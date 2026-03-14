@QTEST_TC_1201
@tabledoc
Feature: DataTable And DocString
  DataTable And DocString

  Background: 
    Given Hello background

  Scenario: DataTable Scenario
    Given Customer orders the dishes
      | one cell Customer orders the dishes | two cell Customer orders the dishes | three cell Customer orders the dishes | four cell | five cell | six cell | seven cell | eight cell | nine cell | ten cell | eleven cell | twelve cell | thirteen cell | fourteen cell | fifteen cell |
      |                                   1 |                                   2 |                                     3 |         4 |         5 |        6 |          7 |          8 |         9 |       10 |          11 |          12 |            13 |            14 |           15 |
      |                                   1 |                                   2 |                                     3 |         4 |         5 |        6 |          7 |          8 |         9 |       10 |          11 |          12 |            13 |            14 |           15 |
      |                                   1 |                                   2 |                                     3 |         4 |         5 |        6 |          7 |          8 |         9 |       10 |          11 |          12 |            13 |            14 |           15 |
      |                                   1 |                                   2 |                                     3 |         4 |         5 |        6 |          7 |          8 |         9 |       10 |          11 |          12 |            13 |            14 |           15 |
      |                                   1 |                                   2 |                                     3 |         4 |         5 |        6 |          7 |          8 |         9 |       10 |          11 |          12 |            13 |            14 |           15 |
      |                                   1 |                                   2 |                                     3 |         4 |         5 |        6 |          7 |          8 |         9 |       10 |          11 |          12 |            13 |            14 |           15 |
      |                                   1 |                                   2 |                                     3 |         4 |         5 |        6 |          7 |          8 |         9 |       10 |          11 |          12 |            13 |            14 |           15 |

  # And Customer orders the dishes
  #   | Taial Cuidjsj             | Taial Cuidjsj Snopqr | Abcef Ghij Klmnopqrs Tuv Wxyz1 Abcef | Abcdefghi Jklmn (Opqrstu Vwxyz) | Abcd Tfdgh Ijkl Mnopq rs Tuvwx (Yz12345) | abcdefg hijklmn opqrstuvwx |
  #   | {IND Country Description} |                    2 |                                    3 |                               4 |                                        5 |                          6 |
  #   | {BEL Country Description} |                    2 |                                    3 |                               4 |                                        5 |                          6 |
  Scenario: Doc String to Custom Object
    Given the doc string is
      """
      Hello there how r u? Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily 
      
      Doing great. Spicy Meatballs for food and drink italian sicily
      Whats new? Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Nothing much. Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Nothing much. Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
       Hello there how r u? Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily 
      
      Doing great. Spicy Meatballs for food and drink italian sicily
      Whats new? Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Nothing much. Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Nothing much. Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      
       Hello there how r u? Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily 
      
      Doing great. Spicy Meatballs for food and drink italian sicily
      Whats new? Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Nothing much. Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Nothing much. Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily Spicy Meatballs for food and drink italian sicily
      
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u? Hello there how r u?
      """
