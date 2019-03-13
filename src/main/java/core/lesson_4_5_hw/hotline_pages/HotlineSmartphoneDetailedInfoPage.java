package core.lesson_4_5_hw.hotline_pages;

import core.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HotlineSmartphoneDetailedInfoPage extends AbstractPage {

    @FindBy(xpath = "//span[@class='price-lg']/span[@class='value']")
    private WebElement phonePriceRange;

    @FindBy(xpath = "//li[@data-id='prices']/span")
    private WebElement sellersOffersTab;

    @FindBy(xpath = "//span[@class='filter-item-offers']")
    private WebElement filterSellersOffersIcon;

    @FindBys({
            @FindBy(xpath = "//*[@class='price-lg']/span[@class='price-format']/*[@class='value']")
    })
    private List<WebElement> sellersOffersPrices;

    public HotlineSmartphoneDetailedInfoPage(final WebDriver driver) {
        super(driver);
    }

    public void selectSellersOffersTab() {
        sellersOffersTab.click();
        wait.until(ExpectedConditions.visibilityOf(filterSellersOffersIcon));
    }

    public List<Integer> getMinMaxPriceValues() {
        List<Integer> prices = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(phonePriceRange.getText().replaceAll(" ", ""));
        while(m.find()) {
            prices.add(Integer.parseInt(m.group()));
        }
        return prices;
    }

    public List<Integer> getSellersOffersPrices() {
        return  sellersOffersPrices
                .stream()
                .map(price -> price.getText().replaceAll(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
