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
        bookingMainPage.selectRussianlanguage();
        bookingMainPage.selectDestination(destination);
        bookingMainPage.selectDatesRange(startDate, finishDate);
        bookingMainPage.setAdultsQuantity(adultsQuantity);
        final BookingSearchResultsPage searchResultsPage = bookingMainPage.submitSearchData();

        Assert.assertEquals("Search destination is incorrect", destination, searchResultsPage.getCurrentDestination());

        final String filterCriterion5Star = "5 звезд";
        searchResultsPage.selectFilterCriterion(filterCriterion5Star);

        final String expected5Stars = "5";
        searchResultsPage.getStarsQtyForHotel().forEach(hotelStar -> {
            String failMessage = String.format("Expected to have stars rating: %s, but got: %s", expected5Stars, hotelStar);
            Assert.assertEquals(failMessage, expected5Stars, hotelStar);
        });

        final String filterCriterion3Star = "3 звезды";
        final String sortOption = "Звезды asc";
        searchResultsPage.selectFilterCriterion(filterCriterion3Star);
        searchResultsPage.selectSortOption(sortOption);

        final String expected3Stars = "3";
        searchResultsPage.getStarsQtyForHotel().forEach(hotelStar -> {
            String failMessage = String.format("Expected to have stars rating: %s, but got: %s", expected3Stars, hotelStar);
            Assert.assertEquals(failMessage, expected3Stars, hotelStar);
        });
    }

    @Test
    public void checkSearchResultSorting() {
        webDriver.get(UrlBuilder.mainUrl(BookingMainPage.class));

        final String destination = "Флоренция";
        final String startDate = "13, Март 2019";
        final String finishDate = "14, Март 2019";
        final int adultsQuantity = 4;

        final BookingMainPage bookingMainPage = new BookingMainPage(webDriver);
        bookingMainPage.selectRussianlanguage();
        bookingMainPage.selectDestination(destination);
        bookingMainPage.selectDatesRange(startDate, finishDate);
        bookingMainPage.setAdultsQuantity(adultsQuantity);
        final BookingSearchResultsPage searchResultsPage = bookingMainPage.submitSearchData();

        Assert.assertEquals("Search destination is incorrect", destination, searchResultsPage.getCurrentDestination());

        searchResultsPage.selectFilterCriterion("Показать только доступные варианты");
        final String sortOption = "Звезды asc";
        searchResultsPage.selectSortOption(sortOption);
        Assert.assertTrue("Hotels shown on page are not sorted", isSorted(searchResultsPage.getStarsQtyForHotel()));
    }

}
