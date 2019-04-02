package core.fe.lesson_7_hw.booking_pages;

import core.fe.AbstractPage;
import core.fe.BaseUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@BaseUrl(value = "https://www.booking.com/")
public class BookingMainPage extends AbstractPage {

    @FindBy(xpath = "//li[@data-id='language_selector']")
    private WebElement languageIcon;

    @FindBy(xpath = "(//li[contains(@class,'lang_ru')])[1]")
    private WebElement russianLanguageIcon;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement destinationField;

    @FindBy(xpath = "//div[@class='xp__dates-inner']")
    private WebElement selectDatesField;

    @FindBys({
            @FindBy(xpath = "//td[contains(@class, 'bui-calendar__date--selected')]")
    })
    private List<WebElement> selectedDates;

    @FindBys({
            @FindBy(xpath = "//div[@class='bui-calendar__month']")
    })
    private List<WebElement> currentMonthsOfYear;

    @FindBy(xpath = "//div[@data-bui-ref='calendar-next']")
    private WebElement calendarNextButton;

    @FindBy(xpath = "//*[@id='xp__guests__toggle']")
    private WebElement detailsField;

    @FindBy(xpath = "//div[contains(@class, 'field-adults')]//span[@data-bui-ref='input-stepper-value']")
    private WebElement adultsQuantityLbl;

    @FindBy(xpath = "//div[contains(@class, 'field-adults')]//button[contains(@class, 'subtract-button')]")
    private WebElement subtractAdultsButton;

    @FindBy(xpath = "//div[contains(@class, 'field-adults')]//button[contains(@class, 'add-button')]")
    private WebElement addAdultsButton;

    @FindBy(xpath = "//div[contains(@class,'submit-button')]")
    private WebElement submitButton;

    private List<WebElement> getDatesWithinMonth(String monthOfYear) {
        return driver.findElements(By.xpath("//div[@class='bui-calendar__month'][contains(text(), '" +monthOfYear+ "')]/..//td"));
    }

    public BookingMainPage(final WebDriver driver) {
        super(driver);
    }

    public void selectRussianlanguage() {
        languageIcon.click();
        russianLanguageIcon.click();
        waitForJsToLoad();
    }

    public void selectDestination(String destination) {
        wait.until(ExpectedConditions.visibilityOf(destinationField));
        destinationField.sendKeys(destination);
    }

    private void findMonthOfYearToSelect(String monthOfYear) {
        int count = 0;

        while(count < 14) {
            String monthLeft = currentMonthsOfYear.get(0).getText();
            String monthRight = currentMonthsOfYear.get(1).getText();
            if (monthLeft.equals(monthOfYear)
                     || monthRight.equals(monthOfYear)) {
                break;
            }
            else {
                calendarNextButton.click();
                count++;
            }
        }
    }

    private boolean hasSelectedDate(String dateToSelect) {
        if (selectedDates.size() != 0) {
            for (WebElement date : selectedDates) {
                if (date.getText().equals(dateToSelect)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void selectDate(String monthOfYear, String dateToSelect) {
        findMonthOfYearToSelect(monthOfYear);
        if (!hasSelectedDate(dateToSelect)) {
            getDatesWithinMonth(monthOfYear)
                    .stream()
                    .filter(date -> date.getText().equals(dateToSelect))
                    .forEach(WebElement::click);
        }
    }

    //Date in format "DD, Month Year" is split into: {"DD", " Month Year"}
    public void selectDatesRange(String start, String finish) {
        String startDate = start.split(",")[0];
        String startMonthOfYear = start.split(",")[1];
        String finishDate = finish.split(",")[0];
        String finishMonthOfYear = finish.split(",")[1];

        selectDatesField.click();
        selectDate(startMonthOfYear.trim(), startDate);
        selectDate(finishMonthOfYear.trim(), finishDate);
    }

    public void setAdultsQuantity(int quantity) {
        detailsField.click();
        Integer currentQuantity = Integer.valueOf(adultsQuantityLbl.getText());
        setQuantity(quantity, currentQuantity, subtractAdultsButton, addAdultsButton);
    }

    public BookingSearchResultsPage submitSearchData() {
        submitButton.click();
        return new BookingSearchResultsPage(driver);
    }
}
