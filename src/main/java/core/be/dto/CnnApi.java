package core.be.dto;

import core.be.AbstractApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CnnApi extends AbstractApi {

    public CnnSearchResultsModel getSearchResults(String searchResultsUri) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(searchResultsUri)
                .as(CnnSearchResultsModel.class);
    }
}
