package org.gb.lesson8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class TShirtsPage {
    // @FindBy(xpath = "//span[.='Size']/ancestor::div[@class='layered_filter']//a")
    // private List<WebElement> sizesList;
    private ElementsCollection sizesList = $$(By.xpath("//span[.='Size']/ancestor::div[@class='layered_filter']//a"));

    public TShirtsPage selectSize(String size) {
        // sizesList.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();
        // кликаем на нужный размер одежды (их несколько в фильтрах)
        sizesList.findBy(Condition.text(size)).click();
        return this;  // после фильтра остаемся на той же странице
    }

    // @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    // на данном сайте карточка товара одна, если бы несколько - тоже использовали бы List
    // private WebElement productElement;
    private SelenideElement productElement = $(By.xpath("//ul[@class='product_list grid row']/li"));

    // @FindBy(xpath = "//span[.='Add to cart']")
    // private WebElement addToCartButton;
    private SelenideElement addToCartButton = $(By.xpath("//span[.='Add to cart']"));

    public SuccessAddToCardPage moveMouseToProductAndAddToCart() {
        productElement.hover();
        addToCartButton.click();
        // return new SuccessAddToCardPage(driver);
        return page(SuccessAddToCardPage.class);  // указываем класс той страницы, на которую попадаем в итоге
    }

}
