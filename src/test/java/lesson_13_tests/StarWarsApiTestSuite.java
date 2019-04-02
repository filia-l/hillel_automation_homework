package lesson_13_tests;

import core.be.StarWarsApi;
import core.be.dto.StarWarsCharacterModel;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

public class StarWarsApiTestSuite {

    @Test
    public void checkStarWarsCharacterData() {
        final StarWarsCharacterModel expectedCharacterData = new StarWarsCharacterModel();
        final String[] films = {
             "The Empire Strikes Back",
             "Revenge of the Sith",
             "Return of the Jedi",
             "A New Hope",
             "The Force Awakens"
        };
        final String[] vehicles = {
             "https://swapi.co/api/vehicles/14/",
             "https://swapi.co/api/vehicles/30/"
        };
        final String[] starships = {
             "https://swapi.co/api/starships/12/",
             "https://swapi.co/api/starships/22/"
        };
        final String[] species = {
             "https://swapi.co/api/species/1/"
        };

        expectedCharacterData.setName("Luke Skywalker");
        expectedCharacterData.setHeight("172");
        expectedCharacterData.setBirth_year("19BBY");
        expectedCharacterData.setEye_color("blue");
        expectedCharacterData.setCreated("2014-12-09T13:50:51.644000Z");
        expectedCharacterData.setGender("male");
        expectedCharacterData.setEdited("2014-12-20T21:17:56.891000Z");
        expectedCharacterData.setHomeworld("https://swapi.co/api/planets/1/");
        expectedCharacterData.setHair_color("blond");
        expectedCharacterData.setMass("77");
        expectedCharacterData.setSkin_color("fair");
        expectedCharacterData.setUrl("https://swapi.co/api/people/1/");
        expectedCharacterData.setFilmsManually(films);
        expectedCharacterData.setSpecies(species);
        expectedCharacterData.setStarships(starships);
        expectedCharacterData.setVehicles(vehicles);

        final StarWarsApi starWarsApi = new StarWarsApi();
        final StarWarsCharacterModel actualCharacterData = starWarsApi.getCharacterData();

        ReflectionAssert.assertReflectionEquals("Character data is not equal!", expectedCharacterData, actualCharacterData);
    }
}
