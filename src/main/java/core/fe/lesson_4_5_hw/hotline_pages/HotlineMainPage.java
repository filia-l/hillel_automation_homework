package core.fe.lesson_4_5_hw.hotline_pages;

import core.fe.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotlineMainPage extends AbstractPage {

    @FindBy(xpath = "//span[@class='box-in']")
    private WebElement logInIcon;

    @FindBy(xpath = "//span[@class='name ellipsis']")
    private WebElement loggedInUser;

    @FindBy(xpath = "//li[@class='level-1 mobile']")
    private WebElement mainMenuSmartphoneItem;

    @FindBy(xpath = "//span[@class='telefony-i-garnitury']")
    private WebElement intermediateMenuSmartphoneItem;

    @FindBy(xpath = "//li[@class='level-3']/a[@class='mobilnye-telefony-i-smartfony']")
    private WebElement subMenuSmartphoneItem;

    public HotlineMainPage (final WebDriver driver) {
        super(driver);
    }

    public HotlineSmartphonesPage navigateToSmartphonesPage() {
        actions.moveToElement(mainMenuSmartphoneItem).perform();
        wait.until(ExpectedConditions.visibilityOf(intermediateMenuSmartphoneItem));
        intermediateMenuSmartphoneItem.click();
        subMenuSmartphoneItem.click();

        return new HotlineSmartphonesPage(driver);
    }

    public void clickLogInButton() {
        logInIcon.click();
        waitForJsToLoad();
    }

    public String getLoggedInUserName() {
        return loggedInUser.getText();
    }
}
