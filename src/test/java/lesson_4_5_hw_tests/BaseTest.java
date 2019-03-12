package lesson_4_5_hw_tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
}
