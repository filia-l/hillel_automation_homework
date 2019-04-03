package lesson_13_tests;

import core.be.StarWarsApi;
import core.be.dto.StarWarsCharacterModel;
import io.restassured.RestAssured;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

public class StarWarsApiTestSuite {

    @Test
    public void checkStarWarsCharacterData() {

        RestAssured.baseURI = "https://swapi.co/api/";
        final String personUri = "people/1/";

        final StarWarsCharacterModel expectedCharacterData = new StarWarsCharacterModel();
        final String[] films = {
             "The Empire Strikes Back",
             "Revenge of the Sith",
             "Return of the Jedi",
             "A New Hope",
             "The Force Awakens"
        };

        expectedCharacterData.setName("Luke Skywalker");
        expectedCharacterData.setHeight("172");
        expectedCharacterData.setBirth_year("19BBY");
        expectedCharacterData.setEye_color("blue");
        expectedCharacterData.setGender("male");
        expectedCharacterData.setHomeworld("https://swapi.co/api/planets/1/");
        expectedCharacterData.setHair_color("blond");
        expectedCharacterData.setMass("77");
        expectedCharacterData.setSkin_color("fair");
        expectedCharacterData.setFilms(films);

        final StarWarsApi starWarsApi = new StarWarsApi();
        final StarWarsCharacterModel actualCharacterData = starWarsApi.getCharacterData(personUri);
        actualCharacterData.setFilms(starWarsApi.convertFilms(actualCharacterData.getFilms()));

        ReflectionAssert.assertReflectionEquals("Character data is not equal!", expectedCharacterData, actualCharacterData);
    }
}
