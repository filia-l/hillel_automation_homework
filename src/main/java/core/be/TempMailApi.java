package core.be;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TempMailApi extends AbstractApi{

    final private String GET_EMAIL_LIST_PARTIAL_LINK = "/request/mail/id/";
    final private String KEY_NAME = "X-RapidAPI-Key";
    final private String KEY_VALUE = "*****";

    @Override
    protected String setUpBaseUrl() {
        return "https://privatix-temp-mail-v1.p.mashape.com";
    }

    public String getEmailsList(String hashId) {
        return RestAssured.given()
                .header(KEY_NAME, KEY_VALUE)
                .contentType(ContentType.HTML)
                .get(GET_EMAIL_LIST_PARTIAL_LINK + hashId + "/format/html/")
                .asString();
    }
}
