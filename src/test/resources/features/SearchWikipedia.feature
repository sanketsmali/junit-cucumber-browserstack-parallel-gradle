@Web @NeatGifTest
Feature: Search Wikipedia

  @Pikachu
  Scenario: Search and compare wikipedia data with poke API data
    Given User open google.com
    And User search "Pikachu Wikipedia Indonesia"
    And User select first search result
    Then User see pokemon data for "Pikachu" (pokemon number and name) are same with the poke API data


  @Charizard
  Scenario: Search and compare wikipedia data with poke API data
    Given User open google.com
    And User search "Charizard Wikipedia Indonesia"
    And User select first search result
    Then User see pokemon data for "Charizard" (pokemon number and name) are same with the poke API data


  @Bulbasaur
  Scenario: Search and compare wikipedia data with poke API data
    Given User open google.com
    And User search "Bulbasaur Wikipedia Indonesia"
    And User select first search result
    Then User see pokemon data for "Bulbasaur" (pokemon number and name) are same with the poke API data


  @Mewtwo
  Scenario: Search and compare wikipedia data with poke API data
    Given User open google.com
    And User search "Mewtwo Wikipedia Indonesia"
    And User select first search result
    Then User see pokemon data for "Mewtwo" (pokemon number and name) are same with the poke API data

