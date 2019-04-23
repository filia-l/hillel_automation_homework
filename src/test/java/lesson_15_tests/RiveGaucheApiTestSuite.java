package lesson_15_tests;

import core.be.RiveGaucheApi;
import core.fe.lesson_15_hw.RiveGaucheMainPage;
import core.fe.lesson_15_hw.RiveGaucheShoppingCartPage;
import io.restassured.response.Response;
import lesson_4_5_hw_tests.BaseTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;

public class RiveGaucheApiTestSuite extends BaseTest {

    @Test
    public void checkAddingItemToCartWithAuthUser() {

        webDriver.get("https://shop.rivegauche.ru");
        final RiveGaucheMainPage riveGaucheMainPage = new RiveGaucheMainPage(webDriver);
        riveGaucheMainPage.loginWithUser("filia@direct-mail.info", "123456");
        final String productId = "908293";

        final RiveGaucheApi riveGaucheApi = new RiveGaucheApi();
        final Response response = riveGaucheApi.addItemToCart(productId, getSessionCookies());

        Assert.assertEquals("Incorrect status returned!", 200, response.getStatusCode());

        webDriver.navigate().refresh();
        final RiveGaucheShoppingCartPage cartPage = riveGaucheMainPage.openShoppingCart();

        final Document shoppingCart = Jsoup.parse(riveGaucheApi.getShoppingCartInfo(getSessionCookies()));
        final Element product = shoppingCart.select("div.cart__img-box_img > a > img").first();

        final String expectedProductTitle = product.attr("title");
        final String actualProductTitle = cartPage.getAddedItemTitle();

        Assert.assertEquals("Added product title is incorrect!", expectedProductTitle, actualProductTitle);
    }
}
