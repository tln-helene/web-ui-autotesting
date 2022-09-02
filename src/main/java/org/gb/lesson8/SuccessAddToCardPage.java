package org.gb.lesson8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SuccessAddToCardPage {
    // @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    // private WebElement totalSumma;
    private SelenideElement totalSumma = $(By.xpath("//span[@class='ajax_block_cart_total']"));

    // @FindBy(xpath = "//i[@class='icon-ok']")
    // private WebElement iconOk;
    private SelenideElement iconOk = $(By.xpath("//i[@class='icon-ok']"));

    public SuccessAddToCardPage checkTotalSumma(String expectedSumma) {
        // проверяем совпадение ожидаемой суммы с отображаемой
        // Assertions.assertEquals(expectedSumma, totalSumma.getText());
        totalSumma.shouldHave(Condition.text(expectedSumma));
        return page(SuccessAddToCardPage.class);  // указываем класс той страницы, на которую попадаем в итоге
    }
}
