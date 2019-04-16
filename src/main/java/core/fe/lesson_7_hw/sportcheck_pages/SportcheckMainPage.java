package core.fe.lesson_7_hw.sportcheck_pages;

import core.fe.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SportcheckMainPage extends AbstractPage {

    @FindBy(xpath = "(//div[@class='header-account__trigger'])[1]")
    private WebElement signInMenuItem;

    @FindBy(xpath = "(//a[@class='account-menu__link'])[1]")
    private WebElement myAccountMenuItem;

    @FindBy(xpath = "(//a[@data-action='register'])[1]")
    private WebElement registerMenuItem;

    @FindBy(xpath = "//section[@id='register-modal']//input[@type='email'][@name='login']")
    private WebElement modalEmailField;

    @FindBy(xpath = "//section[@id='register-modal']//input[@type='email'][@name='loginConfirmation']")
    private WebElement modalConfirmEmailField;

    @FindBy(xpath = "//section[@id='register-modal']//input[@type='password'][@name='password']")
    private WebElement modalPassField;

    @FindBy(xpath = "//section[@id='register-modal']//input[@type='password'][@name='confirmPassword']")
    private WebElement modalConfirmPassField;

    @FindBy(xpath = "//section[@id='register-modal']//input[@type='password'][@name='confirmPassword']")
    private WebElement modalRegisterButton;

    @FindBy(xpath = "//section[@id='register-modal']//iframe")
    private WebElement iFrame;

    @FindBy(xpath = "//div[@class='recaptcha-checkbox-checkmark']")
    private WebElement captchaCheckbox;

    @FindBy(xpath = "//section[@id='auth-modal']//input[@class='sign-up-message-form__submit button']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//li[@id='account-settings__item_email']/div[@class='account-settings__value']")
    private WebElement registeredEmailField;

    public SportcheckMainPage(final WebDriver driver) {
        super(driver);
    }

    public void registerUser(String email, String password) {
        signInMenuItem.click();
        registerMenuItem.click();
        modalEmailField.sendKeys(email);
        modalConfirmEmailField.sendKeys(email);
        modalPassField.sendKeys(password);
        modalConfirmPassField.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(iFrame));
        driver.switchTo().frame(iFrame);
        captchaCheckbox.click();
        waitForJsToLoad();
        waitForJsToLoad();
        modalRegisterButton.click();
        waitForJsToLoad();
        continueShoppingButton.click();
    }

    public void goToMyAccount() {
        signInMenuItem.click();
        myAccountMenuItem.click();
        waitForJsToLoad();
    }

    public String getRegisteredEmail() {
        return registeredEmailField.getText();
    }
}
