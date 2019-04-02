package core.fe;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor jsExecutor;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }

    protected boolean waitForJsToLoad() {

        final String loadJsScript = "return document.readyState";
        final String loadJQueryScript = "return jQuery.active";

        ExpectedCondition<Boolean> jsLoad = driver ->
                jsExecutor.executeScript(loadJsScript).toString().equals("complete");

        ExpectedCondition<Boolean> jQueryLoad = driver ->
                (Long)jsExecutor.executeScript(loadJQueryScript) == 0;

        return wait.until(jsLoad) && wait.until(jQueryLoad);
    }

    protected static void setQuantity(int quantity, Integer currentQuantity, WebElement decreaseQtyButton, WebElement increaseQtyButton) {
        int count = quantity < currentQuantity ? currentQuantity - quantity : quantity - currentQuantity;
        while(count > 0) {
            if (quantity < currentQuantity) {
                decreaseQtyButton.click();
            }
            else {
                increaseQtyButton.click();
            }
            count--;
        }
    }
}
