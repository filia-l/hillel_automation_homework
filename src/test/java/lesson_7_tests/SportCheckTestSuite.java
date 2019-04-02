package lesson_7_tests;

import core.fe.dto.ProductDetailsDTO;
import core.fe.lesson_7_hw.sportcheck_pages.SportcheckSelectedProductPage;
import core.fe.lesson_7_hw.sportcheck_pages.SportcheckShopppingCartPage;
import core.utils.DbHelper;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.sql.SQLException;

public class SportCheckTestSuite extends BaseTest {

    @Test
    public void checkProductPurchaseProcess() {
        webDriver.get("https://www.sportchek.ca/product/332649096.html#332649096=332649110");

        SportcheckSelectedProductPage selectedProductPage = new SportcheckSelectedProductPage(webDriver);
        selectedProductPage.selectColourWithOptions("STORM PINK", "BURGANDY CRUSH");
        selectedProductPage.selectSizeWithOptions("S", "M");
        selectedProductPage.setProductQuantity(3);
        selectedProductPage.addToCart();

        String expectedMessage = "These items have been added to your cart.";
        Assert.assertEquals("Confirmation message is incorrect", expectedMessage.toLowerCase(), selectedProductPage.getItemAddedText());

        selectedProductPage.openCartInfo();
        Assert.assertEquals("Product title in cart doesn't match title on product page",
                selectedProductPage.getProductTitle(), selectedProductPage.getCartItemTitle());
        Assert.assertEquals("Product q-yu in cart doesn't match set quantity",
                selectedProductPage.getCurrentQty(), selectedProductPage.getCartItemQty());
    }

    @Test
    public void checkShoppingCartTest() throws SQLException {
        final String url = "https://www.sportchek.ca/categories/men/jackets-coats-vests/winter-jackets/ski-snowboard-jackets/product/the-north-face-mens-harway-insulated-jacket-332339730.html";
        webDriver.get(url);

        final SportcheckSelectedProductPage detailsPage = new SportcheckSelectedProductPage(webDriver);
        detailsPage.selectRequiredSize("M");
        detailsPage.addToCart();

        final SportcheckShopppingCartPage sportcheckShopppingCartPage = detailsPage.proceedToShoppingCartPage();
        final ProductDetailsDTO frontendData = sportcheckShopppingCartPage.getProductInfo();

        final ProductDetailsDTO backendData = DbHelper.executeQuery("select * from SportCheck");
        ReflectionAssert.assertReflectionEquals("There are incorrect 'Shopping Cart Data' displayed!", backendData, frontendData);
        Assert.assertEquals("There is incorrect 'Title'", frontendData.getTitle(), backendData.getTitle());
    }

}
