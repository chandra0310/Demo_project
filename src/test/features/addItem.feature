#Author: Chandra Srivastava
#Date: 06/12/2022
Feature: to add records to Todo List App using API

@ClearPoint_Demo
#API Test 1
  Scenario Outline: Validate that Item can be added from API
    Given I use the post method to add item '<Description>' to the Todo List App
    And I verify the status code after posting the item
    Then I validate that item has been added to the Todo List App using get method

  Examples:
    | Description   | Comment |
    | CSDemo1       |         |
    | CSDemo2       |         |
    | CSDemo3       |         |
    | CSDemo3       | Duplicate value - to test failure|
