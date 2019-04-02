package core.fe.lesson_13_hw;

import core.be.dto.CnnSearchResultsModel;
import core.be.dto.Result;
import core.fe.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class CnnPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='search-button']")
    private WebElement searchIcon;

    @FindBy(xpath = "//input[@class='search__input-field']")
    private WebElement searchField;

    @FindBys( {
            @FindBy(xpath = "//div[@class='cnn-search__result-contents']/h3[@class = 'cnn-search__result-headline']")
    })
    private List<WebElement> titles;

    @FindBys( {
            @FindBy(xpath = "//div[@class='cnn-search__result-contents']/div[@class = 'cnn-search__result-body']")
    })
    private List<WebElement> texts;

    public CnnPage(WebDriver driver) {
        super(driver);
    }

    public void inputSearchText(String searchText) {
        searchIcon.click();
        searchField.sendKeys(searchText, Keys.ENTER);
        waitForJsToLoad();
    }

    public CnnSearchResultsModel getSearchResults() {
        CnnSearchResultsModel resultsModel = new CnnSearchResultsModel();
        Result[] results = new Result[titles.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = new Result(titles.get(i).getText(), texts.get(i).getText());
        }
        resultsModel.setResult(results);
        return resultsModel;
    }
}
