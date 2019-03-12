package lesson_4_5_hw_tests.hotline_tests;

import core.lesson_4_5_hw.hotline_pages.HotlineComparisonPage;
import core.lesson_4_5_hw.hotline_pages.HotlineMainPage;
import core.lesson_4_5_hw.hotline_pages.HotlineSmartphoneDetailedInfoPage;
import core.lesson_4_5_hw.hotline_pages.HotlineSmartphonesPage;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HotlineTestSuite extends BaseTest{

    @Test
    public void verifySmartphonesProducerFiltering() throws InterruptedException {
        webDriver.get("https://www.hotline.ua");

        final HotlineMainPage hotlineMainPage = new HotlineMainPage(webDriver);
        final HotlineSmartphonesPage smartphonesPage = hotlineMainPage.navigateToSmartphonesPage();

        String expectedUrl = "https://hotline.ua/mobile/mobilnye-telefony-i-smartfony/";
        String actualUrl = smartphonesPage.getSmartphonesPageUrl();
        String failedMessage = String.format("Expected to have page url: %s but got: %s", expectedUrl, actualUrl);
        Assert.assertEquals(failedMessage, expectedUrl, actualUrl);

        String producer = "Apple";
        smartphonesPage.selectSmartphoneProducer(producer);
        List<String> filterResults = smartphonesPage.getSmartphonesListItemsText();
        Assert.assertFalse("List is empty", filterResults.isEmpty());

        filterResults.forEach(itemText -> {
            String failedFiltersMessage = String.format("No occurrence of 'Apple' word in menu item: [%s]", itemText);
            Assert.assertTrue(failedFiltersMessage, itemText.contains(producer));
        });
    }

    @Test
    public void verifySellersPricesForMobilePhone() {
        webDriver.get("https://www.hotline.ua");

        final HotlineMainPage hotlineMainPage = new HotlineMainPage(webDriver);
        final HotlineSmartphonesPage smartphonesPage = hotlineMainPage.navigateToSmartphonesPage();

        String expectedUrl = "https://hotline.ua/mobile/mobilnye-telefony-i-smartfony/";
        String actualUrl = smartphonesPage.getSmartphonesPageUrl();
        String failedMessage = String.format("Expected to have page url: %s but got: %s", expectedUrl, actualUrl);
        Assert.assertEquals(failedMessage, expectedUrl, actualUrl);

        final HotlineSmartphoneDetailedInfoPage smartphoneDetailedInfoPage = smartphonesPage.selectFirstSmartphoneInList();
        smartphoneDetailedInfoPage.selectSellersOffersTab();
        List<Integer> priceRangeForPhone = smartphoneDetailedInfoPage.getMinMaxPriceValues();
        Assert.assertEquals("List has incorrect size", 2, priceRangeForPhone.size());
        List<Integer> sellersOffersPrices = smartphoneDetailedInfoPage.getSellersOffersPrices();
        Assert.assertFalse("List is empty", sellersOffersPrices.isEmpty());

        sellersOffersPrices.forEach(price -> {
            String failedPriceMessage = String.format("Price %d is not in given range", price);
            Assert.assertTrue(failedPriceMessage, isPriceInRange(price, priceRangeForPhone));
        });
    }

    @Test
    public void compareTwoProducts() {
        webDriver.get("https://www.hotline.ua");

        final HotlineMainPage hotlineMainPage = new HotlineMainPage(webDriver);
        final HotlineSmartphonesPage smartphonesPage = hotlineMainPage.navigateToSmartphonesPage();

        String expectedUrl = "https://hotline.ua/mobile/mobilnye-telefony-i-smartfony/";
        String actualUrl = smartphonesPage.getSmartphonesPageUrl();
        String failedMessage = String.format("Expected to have page url: %s but got: %s", expectedUrl, actualUrl);
        Assert.assertEquals(failedMessage, expectedUrl, actualUrl);

        String producer = "Apple";
        smartphonesPage.selectSmartphoneProducer(producer);
        String firstToCompare = "Apple iPhone X 64GB Space Gray (MQAC2)";
        String secondToCompare = "Apple iPhone X 64GB Silver (MQAD2)";
        smartphonesPage.selectProductToCompare(firstToCompare);
        smartphonesPage.selectProductToCompare(secondToCompare);

        final HotlineComparisonPage comparisonPage = smartphonesPage.navigateToComparisonPage(secondToCompare);

        List<String> firstPhoneDisplayProperties = comparisonPage.getPhoneDisplayProperties(comparisonPage.phoneDisplayProperties("2"));
        List<String> secondPhoneDisplayProperties = comparisonPage.getPhoneDisplayProperties(comparisonPage.phoneDisplayProperties("3"));

        Assert.assertFalse("List is empty", firstPhoneDisplayProperties.isEmpty());
        Assert.assertFalse("List is empty", secondPhoneDisplayProperties.isEmpty());
        Assert.assertEquals("The properties of two phones are not equal", firstPhoneDisplayProperties, secondPhoneDisplayProperties);
    }
}
