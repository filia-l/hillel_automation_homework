package core.fe.lesson_15_hw;

import core.fe.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TempMailCreateEmailPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='mail'][@class='form-control']")
    private WebElement loginField;

    @FindBy(xpath = "//select[@name='domain']/option[1]")
    private WebElement domainField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@id='mail'][@onclick='select(this);']")
    private WebElement createdEmailField;

    public TempMailCreateEmailPage(final WebDriver driver) {
        super(driver);
    }

    public String getDefaultDomainName() {
        return domainField.getText();
    }

    public void createNewEmail(String login) {
        loginField.sendKeys(login);
        submitButton.click();
        waitForJsToLoad();
    }

    public String getCreatedEmail() {
        return createdEmailField.getAttribute("value");
    }
}
