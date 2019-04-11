package core.be;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TempMailApi extends AbstractApi{

    final String GET_EMAIL_LIST_PARTIAL_LINK = "/request/mail/id/";

    @Override
    protected String setUpBaseUrl() {
        return "https://privatix-temp-mail-v1.p.mashape.com";
    }

    public Response getEmailsList(String hashId) {
        return RestAssured.given()
                .contentType(ContentType.HTML)
                .get(GET_EMAIL_LIST_PARTIAL_LINK + hashId);
    }
}
