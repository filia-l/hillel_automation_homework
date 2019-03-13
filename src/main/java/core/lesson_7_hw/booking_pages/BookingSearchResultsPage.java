package core.lesson_7_hw.booking_pages;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class BookingSearchResultsPage extends AbstractPage {

    @FindBy(xpath = "//input[contains(@class,'sb-destination')]")
    private WebElement destinationField;

    @FindBys({
            @FindBy(xpath = "//div[@class='sr_property_block_main_row']//i[contains(@class, 'star_track')]/span[@class='invisible_spoken']")
    })
    private List<WebElement> hotelStarsInfo;

    @FindBy(xpath = "//button[@id='sortbar_dropdown_button']")
    private WebElement sortOptionsButton;

    @FindBy(xpath = "//a[@data-sortname='class_asc'] ")
    private WebElement ascendingStarsSortOption;

    @FindBy(xpath = "//a[@data-sortname='class'] ")
    private WebElement descendingStarsSortOption;

    private WebElement getFilterCriterion(String criterion) {
        return driver.findElement(By.xpath("//div[contains(@class, 'filteroptions')]/a/div/span[contains(text(), '" +criterion+ "')]"));
    }

    private WebElement getSortOptionsField(String sortOption) {
        return driver.findElement(By.xpath("//a[@class='sort_option '][contains(text(), '" +sortOption+ "')]"));
    }


    public BookingSearchResultsPage(final WebDriver driver) {
        super(driver);
    }

    public String getCurrentDestination() {
        return destinationField.getAttribute("value");
    }

    public void selectFilterCriterion(String criterion) {
        WebElement elementToSelect = getFilterCriterion(criterion);
        actions.moveToElement(elementToSelect).perform();
        elementToSelect.click();
        waitForJsToLoad();
    }

    public List<String> getHotelStarsInfo() {
        return hotelStarsInfo
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectSortOption(String sortOption) {
        wait.until(ExpectedConditions.visibilityOf(sortOptionsButton));
        sortOptionsButton.click();
        if (sortOption.equals("Звезды")) {
            getSortOptionsField(sortOption).click();
            ascendingStarsSortOption.click();
        }
        else {
            getSortOptionsField(sortOption).click();
        }
        waitForJsToLoad();
    }
}
