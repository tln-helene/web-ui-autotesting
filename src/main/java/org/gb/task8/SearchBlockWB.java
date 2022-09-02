package org.gb.task8;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SearchBlockWB {

    private SelenideElement searchInput = $(By.xpath("//input[@id='searchInput']"));
    private SelenideElement searchButton = $(By.id("applySearchBtn"));


    // @Step("Ввод строки поиска")
    // ввод значения в поле ввода строки поиска
    public SearchBlockWB enterSearchString(String searchString) {
        // заполнение поля поиска тестовым значением
        searchInput.sendKeys(searchString);
        return page(SearchBlockWB.class);
    }

    // @Step("Нажатие кнопки поиска")
    // нажатие кнопки поиска
    public GoodCardPageWB clickSearch() {
        searchButton.click();
        return page(GoodCardPageWB.class);
    }

}
