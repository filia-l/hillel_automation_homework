package lesson_4_5_hw_tests.google_tests;

import core.fe.lesson_4_5_hw.google_pages.GoogleMainPage;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class GoogleSearchDropdownTest extends BaseTest {

    @Test
    public void checkGoogleSubMenuTestOop() {
        webDriver.get("https://www.google.com");
        GoogleMainPage mainPage = new GoogleMainPage(webDriver);
        mainPage.searchInDropdown("hillel");
        List<String> searchResults = mainPage.getSearchDropdownItems();
        Assert.assertFalse("List is empty", searchResults.isEmpty());

        searchResults.forEach(itemText -> {
            String failedMessage = String.format("No occurrence of 'hillel' word in sub-menu item: [%s]", itemText);
            Assert.assertTrue(failedMessage, itemText.contains("hillel"));
        });
    }
}
