package org.gb.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TShirtsPage extends BasePage {
    public TShirtsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Size']/ancestor::div[@class='layered_filter']//a")
    private List<WebElement> sizesList;

    public TShirtsPage selectSize(String size) {
        sizesList.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();
        // кликаем на нужный размер (их несколько в фильтрах)
        return this;  // после фильтра остаемся на той же странице
    }

    @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    // на данном сайте карточка товара одна, если бы несколько - тоже использовали бы List
    private WebElement productElement;

    @FindBy(xpath = "//span[.='Add to cart']")
    private WebElement addToCartButton;

    public SuccessAddToCardPage moveMouseToProductAndAddToCart() {
        actions.moveToElement(productElement)
                .build()
                .perform();
        addToCartButton.click();
        return new SuccessAddToCardPage(driver);
    }

}
