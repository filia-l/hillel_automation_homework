package core.fe.lesson_15_hw;

import core.fe.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RiveGaucheMainPage extends AbstractPage {

    @FindBy(xpath = "//span[@class='header__login-btn']")
    private WebElement signInMenuField;

    @FindBy(xpath = "//input[@name='j_username']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='j_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class, 'login-button')]")
    private WebElement submitButton;

    @FindBy(xpath = "//span[contains(@class, 'full-cart')]")
    private WebElement shoppingCartIcon;

    public RiveGaucheMainPage(final WebDriver driver) {
        super(driver);
    }

    public void loginWithUser(String email, String pass) {
        signInMenuField.click();
        emailField.sendKeys(email);
        passwordField.sendKeys(pass);
        submitButton.click();
        waitForJsToLoad();
    }

    public RiveGaucheShoppingCartPage openShoppingCart() {
        shoppingCartIcon.click();
        waitForJsToLoad();
        return new RiveGaucheShoppingCartPage(driver);
    }
}
