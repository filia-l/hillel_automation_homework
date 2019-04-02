package core.be;

import core.be.dto.StarWarsCharacterModel;
import core.be.dto.StarWarsMovieModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class StarWarsApi extends AbstractApi {

    public StarWarsMovieModel getMovieTitle(String movieUrl) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(movieUrl)
                .as(StarWarsMovieModel.class);
    }

    public StarWarsCharacterModel getCharacterData(String characterUri) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(characterUri)
                .as(StarWarsCharacterModel.class);
    }

}
