package core.fe.lesson_7_hw.sportcheck_pages;

import core.fe.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Optional;

public class SportcheckSelectedProductPage extends AbstractPage {

    @FindBys({
            @FindBy(xpath = "//a[@data-control-type='color']")
    })
    private List<WebElement> productColours;

    @FindBy(xpath = "//input[@id='qty-selector']")
    private WebElement currentQty;

    @FindBy(xpath = "//div[contains(@class, 'number-spin-btn--down')]")
    private WebElement decreaseQtyButton;

    @FindBy(xpath = "//div[contains(@class, 'number-spin-btn--up')]")
    private WebElement increaseQtyButton;

    @FindBy(xpath = "//button[contains(@class, 'add-cart')] ")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='header-cart__confirmation-message_text']")
    private WebElement confirmationMessage;

    @FindBy(xpath = "//*[contains(text(), 'These items have been added to your cart.')]")
    private WebElement confirmationMessageText;

    @FindBy(xpath = "//span[@id='header-cart__count']")
    private WebElement cartIcon;

    @FindBy(xpath = "//div[@id='header-cart__container']")
    private WebElement cartInfoContainer;

    @FindBy(xpath = "//h1[@class='global-page-header__title']")
    private WebElement productTitle;

    @FindBy(xpath = "//h2[@class='cart-item__title']/a")
    private WebElement cartItemTitle;

    @FindBy(xpath = "//dd[@class='cart-item__detail__description']")
    private WebElement cartItemQty;

    public SportcheckSelectedProductPage(final WebDriver driver) {
        super(driver);
    }

    private WebElement productColour(String colour) {
        return driver.findElement(By.xpath("//a[@data-control-type='color' and contains(@title, '" +colour+ "')]"));
    }

    private WebElement productSize(String size) {
        return driver.findElement(By.xpath("//a[@data-control-type='size' and @title = '" +size+ "']"));
    }

    private boolean isItemSelected(WebElement item) {
        return item.getAttribute("class").contains("selected");
    }

    // added from classwork -> database check
    @FindBy(xpath = "//a[@data-control-type='color' and not(contains(@class, 'selected'))]")
    private WebElement color;

    @FindBys({
            @FindBy(xpath = "//span[@class='option-tiles__item-title']")
    })
    private List<WebElement> sizes;

    public String getCurrentQty() {
        return currentQty.getAttribute("value");
    }

    public void selectColourWithOptions(String mainColour, String optionalColour) {
        wait.until(ExpectedConditions.visibilityOf(productColour(mainColour)));
        if (isItemSelected(productColour(mainColour))) {
            productColour(optionalColour).click();
        }
        else {
            productColour(mainColour).click();
        }
        waitForJsToLoad();
    }

    public void selectSizeWithOptions(String mainSize, String optionalSize) {
        wait.until(ExpectedConditions.visibilityOf(productSize(mainSize)));
        if (isItemSelected(productSize(mainSize))) {
            productColour(optionalSize).click();
        }
        else {
            productSize(mainSize).click();
        }
        waitForJsToLoad();
    }

    public void setProductQuantity(int quantity) {
        wait.until(ExpectedConditions.visibilityOf(currentQty));
        actions.moveToElement(currentQty);
        Integer currentQuantity = Integer.valueOf(getCurrentQty());
        setQuantity(quantity, currentQuantity, decreaseQtyButton, increaseQtyButton);
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public String getItemAddedText() {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        return confirmationMessage.getText().toLowerCase();
    }

    public void openCartInfo() {
        //waits for element 'confirmationMessage' to disappear
        //doesn't work if the element is passed directly, eg: wait.until(ExpectedConditions.invisibilityOf(confirmationMessage)
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='header-cart__confirmation-message_text']"))));
        actions.moveToElement(cartIcon).perform();
        wait.until(ExpectedConditions.visibilityOf(cartInfoContainer));
    }

    public String getProductTitle() {
        return productTitle.getText().replaceAll("\"", "");
    }

    public String getCartItemTitle() {
        return cartItemTitle.getText();
    }

    public String getCartItemQty() {
        return cartItemQty.getText();
    }

    // added from classwork -> database check
    public void selectRequiredSize(final String size) {
        Optional<WebElement> requiredSize = sizes.stream()
                .filter(item -> item.getText().equals(size))
                .findFirst();
        if(requiredSize.isPresent()) {
            final WebElement element = requiredSize.get();
            element.click();
        }
    }

    public SportcheckShopppingCartPage proceedToShoppingCartPage() {
        waitForJsToLoad();
        cartIcon.click();
        return new SportcheckShopppingCartPage(driver);
    }

}
