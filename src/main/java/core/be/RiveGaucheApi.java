package core.be;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RiveGaucheApi extends AbstractApi {

    public String setUpBaseUrl() {
        return "https://shop.rivegauche.ru";
    }

    public Response addItemToCart(String body) {
        return RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .contentType(ContentType.JSON)
                .body(body)
                .post("/cart/add");
    }
}
