package core.test.api;


import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PokemonController {

  public Response getPokemonData(String pokemonName) {
    pokemonName = pokemonName.toLowerCase();
    Response response = given().baseUri("https://pokeapi.co").basePath("/api")
        .pathParam("pokemonName", pokemonName)
        .log().all()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON).get("/v2/pokemon/{pokemonName}");
    response.getBody().prettyPrint();
    return response;
  }
}
