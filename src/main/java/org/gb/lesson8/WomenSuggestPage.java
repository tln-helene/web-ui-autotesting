package org.gb.lesson8;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class WomenSuggestPage {
    // @FindBy(xpath = "//ul[contains(@class,'submenu')]//a[@title='T-shirts']")
    // private WebElement tShirtsButton;
    private SelenideElement tShirtsButton = $(By.xpath("//ul[contains(@class,'submenu')]//a[@title='T-shirts']"));

    public TShirtsPage clickTShirtsButton() {
        tShirtsButton.click();
        // return new TShirtsPage(driver);
        return page(TShirtsPage.class);  // указываем класс той страницы, на которую попадаем в итоге
    }

}
