package core.be;

import core.be.dto.StarWarsCharacterModel;
import core.be.dto.StarWarsMovieModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class StarWarsApi extends AbstractApi {

    private static final String STAR_WARS_CHARACTER = "https://swapi.co/api/people/1/";

    public StarWarsMovieModel getMovieTitle(String movieUrl) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(movieUrl)
                .as(StarWarsMovieModel.class);
    }

    public StarWarsCharacterModel getCharacterData() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(STAR_WARS_CHARACTER)
                .as(StarWarsCharacterModel.class);
    }

}
