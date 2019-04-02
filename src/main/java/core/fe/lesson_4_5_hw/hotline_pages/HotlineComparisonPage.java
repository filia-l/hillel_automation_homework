package core.fe.lesson_4_5_hw.hotline_pages;

import core.fe.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class HotlineComparisonPage extends AbstractPage {

    public HotlineComparisonPage(final WebDriver driver) {
        super(driver);
    }

    public List<WebElement> phoneDisplayProperties(String numInQueue) {
        return driver.findElements(By.xpath("((//div[@class='matrix-body'])[" +numInQueue+ "]/div[@data-compare-row='17458']/following-sibling::div)[position() >0 and position() <7]/p"));
    }

    public List<String> getPhoneDisplayProperties(List<WebElement> properties) {
        return properties
                .stream()
                .map(WebElement::getText)
                .sorted()
                .collect(Collectors.toList());
    }
}
