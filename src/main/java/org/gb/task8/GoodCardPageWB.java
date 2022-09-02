package org.gb.task8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

// класс страницы с карточкой товара
public class GoodCardPageWB {

    // поле Артикул
    private SelenideElement articleField = $(By.xpath("//span[@id='productNmId']"));


    //@Step("Проверка соответствия артикула в карточке товара")
    // проверка соответствия артикула на странице заданному значению
    public GoodCardPageWB checkArticle(String sArticle) {
        // сравнение тестового значения артикула с артикулом на открывшейся странице
        articleField.shouldHave(Condition.text(sArticle));
        return page(GoodCardPageWB.class);
    }

}
