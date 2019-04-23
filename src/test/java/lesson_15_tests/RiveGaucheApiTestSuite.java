package lesson_15_tests;

import core.be.RiveGaucheApi;
import core.fe.lesson_15_hw.RiveGaucheMainPage;
import core.fe.lesson_15_hw.RiveGaucheShoppingCartPage;
import io.restassured.response.Response;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class RiveGaucheApiTestSuite extends BaseTest {

    @Test
    public void checkAddingItemToCartWithAuthUser() {

        webDriver.get("https://shop.rivegauche.ru");
        final RiveGaucheMainPage riveGaucheMainPage = new RiveGaucheMainPage(webDriver);
        riveGaucheMainPage.loginWithUser("filia@direct-mail.info", "123456");
        final Set<Cookie> seleniumCookies = webDriver.manage().getCookies();

        final ArrayList restAssuredCookies = new ArrayList();
        for (Cookie cookie : seleniumCookies) {
            restAssuredCookies.add(new io.restassured.http.Cookie.Builder(cookie.getName(), cookie.getValue()).build());
        }

        final String productId = "908293";

        final RiveGaucheApi riveGaucheApi = new RiveGaucheApi();
        final Response response = riveGaucheApi.addItemToCart(productId, restAssuredCookies);

        Assert.assertEquals("Incorrect status returned", 200, response.getStatusCode());

        webDriver.navigate().refresh();

        final RiveGaucheShoppingCartPage cartPage = riveGaucheMainPage.openShoppingCart();

        final String expectedProductTitle = "Liu Jo Milano Eau De Parfum";
        final String actualProductTitle = cartPage.getAddedItemTitle();

        Assert.assertEquals("Added product title is incorrect!", expectedProductTitle, actualProductTitle);
    }
}
