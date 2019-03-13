package core.lesson_4_5_hw.google_pages;

import core.BaseUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

@BaseUrl(value = "https://www.google.com/")
public class GoogleMainPage {

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;

    @FindBys({
            @FindBy(xpath = "//li[not(@id='sbt') and @class='sbct']")
    })
    private List<WebElement> searchDropdownResults;

    private WebDriver driver;

    public GoogleMainPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchInDropdown(final String text) {
        searchField.sendKeys(text);
    }

    public List<String> getSearchDropdownItems() {
        return searchDropdownResults
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}