package lesson_15_tests;

import core.be.RiveGaucheApi;
import core.fe.lesson_15_hw.RiveGaucheMainPage;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import lesson_4_5_hw_tests.BaseTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RiveGaucheApiTestSuite extends BaseTest {

    @Test
    public void checkAddingItemToCartWithAuthUser() {

        webDriver.get("https://shop.rivegauche.ru");
        final RiveGaucheMainPage riveGaucheMainPage = new RiveGaucheMainPage(webDriver);
        riveGaucheMainPage.loginWithUser("filia@direct-mail.info", "123456");
        Set<Cookie> seleniumCookies = webDriver.manage().getCookies();

        // This is where the Cookies will live going forward
        List restAssuredCookies = new ArrayList();

        // Simply pull all the cookies into Rest-Assured
        for (Cookie cookie : seleniumCookies) {
            restAssuredCookies.add(new io.restassured.http.Cookie.Builder(cookie.getName(), cookie.getValue()).build());
        }

        final String productId = "908293";

        final RiveGaucheApi riveGaucheApi = new RiveGaucheApi();
        Response response = riveGaucheApi.addItemToCart(productId, restAssuredCookies);

        Assert.assertEquals("Incorrect status returned", 200, response.getStatusCode());

        webDriver.navigate().refresh();

        String expected = "Liu Jo Milano Eau De Parfum";

        //Document shoppingCart = Jsoup.parse(riveGaucheApi.getShoppingCartInfo());
        Document shoppingCart = Jsoup.parse("https://shop.rivegauche.ru/cart");
        //System.out.println("ShopCart " + shoppingCart);
        Element image = shoppingCart.select("#cart").first();
        System.out.println("Image: " + image);

        //final String actualAddedItemTitle = image.attr("title");

        //Assert.assertEquals(expected, actualAddedItemTitle);

    }

}
