package core.fe.lesson_15_hw;

import core.fe.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RiveGaucheShoppingCartPage extends AbstractPage {

    @FindBy(xpath = "//a[@class='cart__descr-box_name']")
    private WebElement addedItemTitle;

    public RiveGaucheShoppingCartPage(final WebDriver driver) {
        super(driver);
    }

    public String getAddedItemTitle() {
        return addedItemTitle.getText();
    }
}
