package core.fe.lesson_4_5_hw.hotline_pages;

import core.fe.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotlineLoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public HotlineLoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginToHotmail(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();
        waitForJsToLoad();
    }
}
