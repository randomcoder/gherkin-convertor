@basic @feature @demo
Feature: The Feature Reader should be able to read basic feature files that have simple scenario outlines in.
  In order to be able to parse feature details from a file
  As a person developing the library
  I want to be able to read details from a file

  @scenario-outline-tag-1
  Scenario Outline: A simple scenario outline that has single line steps
    Given a simple precondition <condition>
    When I do something easy
    Then I get the result I expected of <result>

    Examples:
      | condition    | result   |
      | it's running | it works |
