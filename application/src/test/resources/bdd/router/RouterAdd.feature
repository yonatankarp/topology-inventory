@RouterAdd
Feature: Can I add an edge router to a core router?
  Scenario: Adding an edge router to a core router
    Given I have an edge router
    And I have a core router
    Then I add an edge router to a core router
  Scenario: Adding a core router to another core router
    Given I have a core router
    And I have anotehr core router
    Then I add this core router to the other core router
