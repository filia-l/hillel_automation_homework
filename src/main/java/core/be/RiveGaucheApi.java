package core.be;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.List;

public class RiveGaucheApi extends AbstractApi {

    final String ADD_TO_CART = "/cart/add";
    final String GET_CART = "/cart";

    public String setUpBaseUrl() {
        return "https://shop.rivegauche.ru";
    }

    public Response addItemToCart(String productId, List cookies) {
        return RestAssured.given()
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParam("productCodePost", productId)
                .cookies(new Cookies(cookies))
                .post(ADD_TO_CART);
    }

    public String getShoppingCartInfo() {
        return RestAssured.given()
                .contentType(ContentType.HTML)
                .get(GET_CART)
                .asString();
    }
}
