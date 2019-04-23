package lesson_4_5_hw_tests;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BaseTest {

    protected WebDriver webDriver;

    @Before
    public void driverSetUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @After
    public void driverTearDown() {
        webDriver.close();
        webDriver.quit();
    }

    protected boolean isPriceInRange(Integer price, List<Integer> rangeValues) {
        int min = rangeValues.get(0);
        int max = rangeValues.get(rangeValues.size() - 1);
        return price >= min && price <= max;
    }

    protected boolean isStringListSorted(final List<String> listToCheck) {
        List <String> listToSort = new ArrayList<>(listToCheck);
        Collections.sort(listToSort);
        return listToSort.equals(listToCheck);
    }

    protected boolean isDatesListSorted(final List<LocalDateTime> listToCheck) {
        List <LocalDateTime> listToSort = new ArrayList<>(listToCheck);
        Collections.sort(listToSort);
        return listToSort.equals(listToCheck);
    }

    private LocalDateTime convertStringToDate(String dateToConvert) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        return LocalDateTime.parse(dateToConvert, formatter);
    }

    protected List<String> getStringValues(List<WebElement> webElementValues) {
        return webElementValues
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    protected List<LocalDateTime> getDateTimeValues(List<String> dates) {
        return dates
                .stream()
                .map(this::convertStringToDate)
                .collect(Collectors.toList());
    }

    protected String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    protected String convertStringToHash(String toConvert) {
        return DigestUtils.md5Hex(toConvert);
    }

}
