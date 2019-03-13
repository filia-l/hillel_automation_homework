package lesson_7_tests;

import core.lesson_7_hw.booking_pages.BookingMainPage;
import core.lesson_7_hw.booking_pages.BookingSearchResultsPage;
import core.utils.UrlBuilder;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;


public class BookingTestSuite extends BaseTest {

    @Test
    public void checkBookingSearchResults() {
        webDriver.get(UrlBuilder.mainUrl(BookingMainPage.class));

        final String destination = "Италия";
        final String startDate = "24, Май 2019";
        final String finishDate = "10, Июнь 2019";
        final int adultsQuantity = 1;

        final BookingMainPage bookingMainPage = new BookingMainPage(webDriver);
        bookingMainPage.selectDestination(destination);
        bookingMainPage.selectDatesRange(startDate, finishDate);
        bookingMainPage.setAdultsQuantity(adultsQuantity);
        final BookingSearchResultsPage searchResultsPage = bookingMainPage.submitSearchData();

        Assert.assertEquals("Search destination is incorrect", destination, searchResultsPage.getCurrentDestination());

        final String filterCriterion5Star = "5 звезд";
        searchResultsPage.selectFilterCriterion(filterCriterion5Star);

        final String expected5Stars = "5-звездочный отель 5 звезд";
        searchResultsPage.getHotelStarsInfo().forEach(hotelInfo -> {
            String failMessage = String.format("Expected to have stars rating: %s, but got: %s", expected5Stars, hotelInfo);
            Assert.assertTrue(failMessage, expected5Stars.contains(hotelInfo));
        });

        final String filterCriterion3Star = "3 звезды";
        final String sortOption = "Звезды";
        searchResultsPage.selectFilterCriterion(filterCriterion3Star);
        searchResultsPage.selectSortOption(sortOption);

        final String expected3Stars = "3-звездочный отель 3 звезд";
        searchResultsPage.getHotelStarsInfo().forEach(hotelInfo -> {
            String failMessage = String.format("Expected to have stars rating: %s, but got: %s", expected3Stars, hotelInfo);
            Assert.assertTrue(failMessage, expected3Stars.contains(hotelInfo));
        });
    }

}
