package core.be;

import core.be.dto.PetModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreApi extends AbstractApi {

    @Override
    protected String setUpBaseUrl() {
        return "https://petstore.swagger.io";
    }

    private static final String ADD_NEW_PET_PARTIAL_LINK = "/v2/pet";

    public PetModel addNewPet(final String petBody) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petBody)
                .post(ADD_NEW_PET_PARTIAL_LINK)
                .as(PetModel.class);
    }

    public PetModel updateAddedPet(final String petBody) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petBody)
                .put(ADD_NEW_PET_PARTIAL_LINK)
                .as(PetModel.class);
    }

    public Response deleteAddedPet(final long petId) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .delete(ADD_NEW_PET_PARTIAL_LINK + "/" + petId);
    }

    public PetModel getAddedPetById(final long petId) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(ADD_NEW_PET_PARTIAL_LINK +"/"+ petId)
                .as(PetModel.class);
    }

    public Response getDeletedPetById(final long petId) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(ADD_NEW_PET_PARTIAL_LINK +"/"+ petId);
    }
}
