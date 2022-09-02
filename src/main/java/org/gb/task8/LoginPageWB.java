package org.gb.task8;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPageWB {

    // поле ввода номера телефона
    private SelenideElement phoneInput = $(By.xpath("//input[@class='input-item']"));
    // кнопка "Получить код"
    private SelenideElement getCodeButton = $(By.id("requestCode"));
    // соообщение об ошибке
    private SelenideElement loginErrorMessage = $(By.xpath("//span[contains(@class, 'j-error-full-phone')]"));


    // @Step("Ввод номера телефона")
    // ввод значения в поле ввода для номера телефона
    public LoginPageWB enterPhone(String phone) {
        // заполняем поле для номера телефона заданным значением
        phoneInput.sendKeys(phone);
        return page(LoginPageWB.class);
    }

    // @Step("Нажатие кнопки Получить код")
    // нажатие кнопки "Получить код"
    public LoginPageWB clickGetCodeButton() {
        getCodeButton.click();
        return page(LoginPageWB.class);
    }

    // @Step("Проверка текста сообщения об ошибке")
    // проверка соответствия текста сообщения об ошибке заданному тексту
    public LoginPageWB checkLoginErrorMessage(String loginErrorMessageExpected) {
        // проверяем появившееся сообщение ошибке
        Assertions.assertEquals(loginErrorMessageExpected, loginErrorMessage.getText());
        return page(LoginPageWB.class);
    }

}
