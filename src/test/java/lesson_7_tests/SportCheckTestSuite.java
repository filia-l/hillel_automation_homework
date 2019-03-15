package lesson_7_tests;

import core.lesson_7_hw.sportcheck_pages.SportcheckSelectedProductPage;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

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

}
