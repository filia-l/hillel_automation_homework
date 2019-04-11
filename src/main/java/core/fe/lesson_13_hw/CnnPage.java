package core.fe.lesson_13_hw;

import core.be.dto.Result;
import core.fe.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.stream.Collectors;

public class CnnPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='search-button']")
    private WebElement searchIcon;

    @FindBy(xpath = "//input[@class='search__input-field']")
    private WebElement searchField;

    @FindBys({
            @FindBy(xpath = "//div[@class='cnn-search__result-contents']")
    })
    private List<WebElement> searchResults;

    public CnnPage(WebDriver driver) {
        super(driver);
    }

    public void inputSearchText(String searchText) {
        searchIcon.click();
        searchField.sendKeys(searchText, Keys.ENTER);
        waitForJsToLoad();
    }

    public List<Result> getSearchResults() {
        final String articleTitleLocator = "./h3[@class='cnn-search__result-headline']";
        final String articleTextLocator = "./div[@class='cnn-search__result-body']";
        return searchResults.stream()
                .map(article -> {
                    final String title = article.findElement(By.xpath(articleTitleLocator)).getText();
                    final String text = article.findElement(By.xpath(articleTextLocator)).getText();
                    return new Result(title, text);
                }).collect(Collectors.toList());
    }
}
