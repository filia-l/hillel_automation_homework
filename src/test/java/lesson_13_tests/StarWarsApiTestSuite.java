package lesson_13_tests;

import com.google.gson.Gson;
import core.be.StarWarsApi;
import core.be.dto.StarWarsCharacterModel;
import core.utils.UrlBuilder;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;
import java.io.File;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class StarWarsApiTestSuite {

    @Test
    public void checkStarWarsCharacterData() throws IOException {

        final String personUri = "people/1/";

        final Gson gson = new Gson();
        final String jsonPath = UrlBuilder.getPropertyValue("star.wars.character");
        final String characterData = FileUtils.readFileToString(
                new File(jsonPath), Charset.defaultCharset()
        );
        final StarWarsCharacterModel expectedCharacterData = gson.fromJson(characterData, StarWarsCharacterModel.class);

        final StarWarsApi starWarsApi = new StarWarsApi();
        final StarWarsCharacterModel actualCharacterData = starWarsApi.getCharacterData(personUri);

        ReflectionAssert.assertReflectionEquals("Character data is not equal!", expectedCharacterData, actualCharacterData);
    }
}
