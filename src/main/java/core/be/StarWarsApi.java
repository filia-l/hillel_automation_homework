package core.be;

import core.be.dto.StarWarsCharacterModel;
import core.be.dto.StarWarsMovieModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.Arrays;

public class StarWarsApi extends AbstractApi {

    @Override
    protected String setUpBaseUrl() {
        return "https://swapi.co/api/";
    }

    public StarWarsCharacterModel getCharacterData(String characterUri) {
        final StarWarsCharacterModel starWarsCharacter = retrieveCharacterData(characterUri);
        final String[] movies = Arrays.stream(starWarsCharacter.getFilms())
                .map(movieLink -> RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get(movieLink)
                        .as(StarWarsMovieModel.class))
        .map(StarWarsMovieModel::getTitle).toArray(String[]::new);
        starWarsCharacter.setFilms(movies);
        return starWarsCharacter;
    }

    public StarWarsCharacterModel retrieveCharacterData(String characterUri) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(characterUri)
                .as(StarWarsCharacterModel.class);
    }
}
