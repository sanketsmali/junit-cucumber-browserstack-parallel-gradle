package core.test.steps;


import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import org.junit.Assert;

import core.test.api.PokemonController;



public class WikipediaStepDefinitions {

  ArticlePage articlePage = new ArticlePage();
  PokemonController pokemonController = new PokemonController();

  @Then("User see pokemon data for {string} \\(pokemon number and name) are same with the poke API data")
  public void userSeePokemonDataForPokemonNumberAndNameAreSameWithThePokeAPIData(
      String pokemonName) {
    Response response = pokemonController.getPokemonData(pokemonName);
    int actualPokemonNumberApi = response.path("id");
    String actualPokemonNameApi = response.path("name");
    Assert.assertTrue(actualPokemonNameApi.equalsIgnoreCase(articlePage.getPokemonName()));
    Assert.assertEquals(Integer.parseInt(articlePage.getPokemonNumber()), actualPokemonNumberApi);
  }

}
