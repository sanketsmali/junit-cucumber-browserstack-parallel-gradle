@Web
Feature: Search Wikipedia2

  @Pikachu
  Scenario: Search and compare wikipedia data with poke API data
    Given User open google.com
    And User search "Pikachu Wikipedia Indonesia"
    And User select first search result
    Then User see pokemon data for "Pikachu" (pokemon number and name) are same with the poke API data



