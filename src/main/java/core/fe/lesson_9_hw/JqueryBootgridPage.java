package core.fe.lesson_9_hw;

import core.fe.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;

public class JqueryBootgridPage extends AbstractPage {

    @FindBys({
            @FindBy(xpath = "//*[@id='grid-selection']/thead/tr/th")
    })
    private List<WebElement> columnHeaderNames;

    @FindBy(xpath = "//button[@id='init-selection']")
    private WebElement startExampleButton;

    @FindBy(xpath = "(//button[@data-toggle='dropdown'])[1]")
    private WebElement qtyOfRowsDropdownBtn;

    @FindBy(xpath = "(//button[@data-toggle='dropdown'])[2]")
    private WebElement colFilterDropdownBtn;

    @FindBy(xpath = "(//input[@class='search-field form-control'])[1]")
    private WebElement searchField;

    public JqueryBootgridPage(final WebDriver driver) {
        super(driver);
    }

    private List<WebElement> columnValuesElements(int index) {
        return driver.findElements(By.xpath("//*[@id='grid-selection']/tbody/tr/td[" +index+ "]"));
    }

    private WebElement dropdownRowsItem(String rowsQty) {
        return driver.findElement(By.xpath("(//a[contains(@class,'dropdown-item') and contains(text(), '" +rowsQty+ "')])[1]"));
    }

    private  WebElement dropdownColumnItem(String columnName) {
        return driver.findElement(By.xpath("//label[@class='dropdown-item']/input[contains(@name, '" +columnName.toLowerCase()+ "')]"));
    }

    private WebElement sortColumnButton(String columnName) {
        return driver.findElement(By.xpath("(//span[contains(text(), '" +columnName+ "')]/parent::a[contains(@class, 'sortable')])[1]"));
    }

    public void formTable() {
        startExampleButton.click();
        waitForJsToLoad();
    }

    public void selectRowsQty(String quantity) {
        qtyOfRowsDropdownBtn.click();
        dropdownRowsItem(quantity).click();
        waitForJsToLoad();
    }

    public void selectColumn(String columnName) {
        colFilterDropdownBtn.click();
        dropdownColumnItem(columnName).click();
        waitForJsToLoad();
    }

    public void sortColumn(String columnName) {
        sortColumnButton(columnName).click();
        waitForJsToLoad();
    }

    public List<WebElement> getColumnValuesElements(String columnName) {
        int columnIndex = 1;
        List <WebElement> resultsList = new ArrayList<>();

        for (WebElement colName : columnHeaderNames) {
            if (colName.getText().equals(columnName)) {
                resultsList = columnValuesElements(columnIndex);
                break;
            }
            columnIndex++;
        }
        return resultsList;
    }

    public boolean hasColumn(String columnName) {
        return columnHeaderNames
                .stream()
                .anyMatch(colName -> colName.getText().equals(columnName));
    }

    public void searchBy(String searchInput) throws InterruptedException {
        searchField.sendKeys(searchInput);
        Thread.sleep(500);
    }

    public boolean hasCorrectSearchResults(String columnSearched, String searchInput) {
        return getColumnValuesElements(columnSearched)
                .stream()
                .allMatch(result -> result.getText().contains(searchInput));
    }

}
