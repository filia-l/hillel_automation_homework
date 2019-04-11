package lesson_14_tests;

import com.google.gson.Gson;
import core.be.PetStoreApi;
import core.be.dto.Category;
import core.be.dto.PetModel;
import core.be.dto.Tag;
import core.utils.UrlBuilder;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class PetStoreTestSuite {

    @Test
    public void checkAddNewPetApi() {
        final PetModel requestBody = new PetModel();
        final Tag[] tags = {
                new Tag(13, "MyTestTag")
        };
        requestBody.setId((long) 1554137067);
        requestBody.setCategory(new Category(12, "MyTestCategory"));
        requestBody.setTags(tags);
        requestBody.setName("Hillel_Pet");
        requestBody.setPhotoUrls(new String[] {"file"});
        final Gson gson = new Gson();
        final String requestBodyString = gson.toJson(requestBody);
        final PetStoreApi petStoreApi = new PetStoreApi();
        final PetModel response = petStoreApi.addNewPet(requestBodyString);
        ReflectionAssert.assertReflectionEquals("There is incorrect pet added!",
                requestBody, response);
    }

    @Test
    public void checkAddNewPetApiUsingJsonFile() throws IOException {
        final long id = System.currentTimeMillis();
        final Gson gson = new Gson();
        final String jsonPath = UrlBuilder.getPropertyValue("create.new.pet.json");
        final String addNewPetRequestBody = FileUtils.readFileToString(
                new File(jsonPath), Charset.defaultCharset()
        );
        final PetModel petModel = gson.fromJson(addNewPetRequestBody, PetModel.class);
        petModel.setId(id);
        final PetStoreApi petStoreApi = new PetStoreApi();
        final PetModel response = petStoreApi.addNewPet(gson.toJson(petModel));
        ReflectionAssert.assertReflectionEquals("There is incorrect pet added!",
                petModel, response);
    }

    @Test
    public void ckeckDeleteAddedPet() throws IOException {
        final long id = System.currentTimeMillis();
        final Gson gson = new Gson();
        final String jsonPath = UrlBuilder.getPropertyValue("create.new.pet.json");
        final String addNewPetRequestBody = FileUtils.readFileToString(
                new File(jsonPath), Charset.defaultCharset()
        );
        final PetModel petModel = gson.fromJson(addNewPetRequestBody, PetModel.class);
        petModel.setId(id);
        final PetStoreApi petStoreApi = new PetStoreApi();

        petStoreApi.addNewPet(gson.toJson(petModel));
        final PetModel addedPet = petStoreApi.getAddedPetById(id);
        ReflectionAssert.assertReflectionEquals("There is incorrect pet added!",
                petModel, addedPet);

        final Response deletePet = petStoreApi.deleteAddedPet(id);
        Assert.assertEquals(200, deletePet.getStatusCode());

        final Response getDeletedPet = petStoreApi.getDeletedPetById(id);
        Assert.assertEquals(404, getDeletedPet.getStatusCode());
        Assert.assertTrue("Incorrect message received!", getDeletedPet.asString().contains("Pet not found"));
    }

    @Test
    public void checkUpdatingAddedPet() throws IOException {
        final long id = System.currentTimeMillis();
        final Gson gson = new Gson();
        final String jsonPath = UrlBuilder.getPropertyValue("create.new.pet.json");
        final String addNewPetRequestBody = FileUtils.readFileToString(
                new File(jsonPath), Charset.defaultCharset()
        );
        final PetModel petModel = gson.fromJson(addNewPetRequestBody, PetModel.class);
        petModel.setId(id);
        final PetStoreApi petStoreApi = new PetStoreApi();

        petStoreApi.addNewPet(gson.toJson(petModel));
        final PetModel addedPet = petStoreApi.getAddedPetById(id);
        ReflectionAssert.assertReflectionEquals("There is incorrect pet added!",
                petModel, addedPet);

        petModel.setName("Kittie");
        petModel.setStatus("Taken");

        petStoreApi.updateAddedPet(gson.toJson(petModel));
        final PetModel updatedPet = petStoreApi.getAddedPetById(id);
        ReflectionAssert.assertReflectionEquals("Updated information is not correct!",
                petModel, updatedPet);
    }
}
