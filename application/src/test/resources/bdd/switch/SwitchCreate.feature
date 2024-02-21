@SwitchCreate
Feature: Can I create new switches?
  Scenario: Create a new switch
    Given I provide all required data to create a switch
    Then A new switch is created
