package core.lesson_4_5_hw.hotline_pages;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;


public class HotlineSmartphonesPage extends AbstractPage {

    @FindBy(xpath = "(//p[@class='h4']/a)[1]")
    private WebElement firstSmartphoneInList;

    @FindBys ({
            @FindBy(css = "[class='m_b-md'] .type-checkbox.plus")
    })
    private List<WebElement> filterSmartphoneProducers;

    @FindBys ({
            @FindBy(css = ".item-info>.h4>a")
    })
    private List<WebElement> smartphonesListItems;

    public HotlineSmartphonesPage(final WebDriver driver) {
        super(driver);
    }


    public String getSmartphonesPageUrl() {
        return driver.getCurrentUrl();
    }

    public void selectSmartphoneProducer(String producer) {
         filterSmartphoneProducers
                .stream()
                .filter(element -> element.getText().contains(producer))
                .forEach(WebElement::click);
         waitForJsToLoad();
    }

    public List<String> getSmartphonesListItemsText() {

        return smartphonesListItems
                .stream()
                .peek(element -> {
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
                })
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public HotlineSmartphoneDetailedInfoPage selectFirstSmartphoneInList() {
        wait.until(ExpectedConditions.visibilityOf(firstSmartphoneInList));
        firstSmartphoneInList.click();
        return new HotlineSmartphoneDetailedInfoPage(driver);
    }

    //use any product name to find checkbox by xpath: //a[contains(text(), 'Apple iPhone X 64GB Space Gray (MQAC2)')]/../../..//label[@class='type-checkbox checkbox-compare']
    public void selectProductToCompare(String productName) {
        WebElement productCheckbox = driver.findElement(By.xpath("//a[contains(text(), '" +productName+ "')]/../../..//label[@class='type-checkbox checkbox-compare']"));
        productCheckbox.click();
    }

    //use any product name to find comparison link by xpath: (//a[contains(text(), 'Apple iPhone X 64GB Space Gray (MQAC2)')]/../../..//a[@rel='nofollow'])[1]
    public HotlineComparisonPage navigateToComparisonPage(String productName){
        WebElement productCompareLink = driver.findElement(By.xpath("(//a[contains(text(), '" +productName+ "')]/../../..//a[@rel='nofollow'])[1]"));
        productCompareLink.click();

        for(String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        return new HotlineComparisonPage(driver);
    }
}
