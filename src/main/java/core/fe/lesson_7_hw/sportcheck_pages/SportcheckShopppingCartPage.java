package core.fe.lesson_7_hw.sportcheck_pages;

import core.fe.AbstractPage;
import core.fe.dto.ProductDetailsDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SportcheckShopppingCartPage extends AbstractPage {

    @FindBy(xpath = "//a[@class='sc-product__title-link']")
    private WebElement title;

    @FindBy(xpath = "(//span[@class='sc-product__property__value'])[1]")
    private WebElement color;

    @FindBy(xpath = "(//span[@class='sc-product__property__value'])[2]")
    private WebElement size;

    @FindBy(xpath = "//input[@class='sc-product__qty']")
    private WebElement qty;

    @FindBy(xpath = "//span[@class='sc-product__price']")
    private WebElement price;

    @FindBy(xpath = "//span[contains(@class, 'sc-product__price ')]")
    private WebElement total;

    public SportcheckShopppingCartPage(final WebDriver driver) {
        super(driver);
    }

    public ProductDetailsDTO getProductInfo() {
        final ProductDetailsDTO detailsDTO = new ProductDetailsDTO();
        detailsDTO.setTitle(title.getText());
        detailsDTO.setColor(color.getText().replaceAll("Colour:", ""));
        detailsDTO.setQty(qty.getAttribute("value"));
        detailsDTO.setSize(size.getText().replaceAll("Size:", ""));
        detailsDTO.setPrice(price.getText());
        detailsDTO.setTotal(total.getText());
        return detailsDTO;
    }
}
