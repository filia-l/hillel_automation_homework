package core.fe.lesson_4_5_hw.hotline_pages;

import core.fe.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotlineRegistrationPage extends AbstractPage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='login']")
    private WebElement nicknameField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='submit-button']")
    private WebElement registerButton;

    public HotlineRegistrationPage(final WebDriver driver) {
        super(driver);
    }

    public void registerNewUser(String email, String password, String nickname) {
        emailField.sendKeys(email);
        nicknameField.sendKeys(nickname);
        passwordField.sendKeys(password);
        registerButton.click();
        waitForJsToLoad();
    }
}
