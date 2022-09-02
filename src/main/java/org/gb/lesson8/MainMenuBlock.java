package org.gb.lesson8;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainMenuBlock {
    // @FindBy(xpath = "//li//a[@title='Women']")
    // private WebElement womenButton;
    private SelenideElement womenButton = $(By.xpath("//li//a[@title='Women']"));

    public WomenSuggestPage hoverWomenButton() {
        /*actions.moveToElement(womenButton)
                .build()
                .perform();*/
        womenButton.hover();
        // return new WomenSuggestPage(driver);
        return page(WomenSuggestPage.class);  // указываем класс той страницы, на которую попадаем в итоге
    }
}
