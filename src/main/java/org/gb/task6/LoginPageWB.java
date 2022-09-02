package org.gb.task6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPageWB extends BasePageWB {
    public LoginPageWB(WebDriver driver) {
            super(driver);
    }

    private final String phoneInputXPathLocator = "//input[@class='input-item']";
    private final String getCodeButtonIDLocator = "requestCode";
    private final String loginErrorMessageXPathLocator = "//span[contains(@class, 'j-error-full-phone')]";

    // поле ввода номера телефона
    @FindBy(xpath = phoneInputXPathLocator)
    public WebElement phoneInput;

    // кнопка "Получить код"
    @FindBy(id = getCodeButtonIDLocator)
    public WebElement getCodeButton;

    // соообщение об ошибке
    @FindBy(xpath = loginErrorMessageXPathLocator)
    public WebElement loginErrorMessage;


    @Step("Ввод номера телефона")
    // ввод значения в поле ввода для номера телефона
    public LoginPageWB enterPhone(String phone) {
        // ожидание, пока на странице прогрузится форма входа
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(phoneInputXPathLocator)));
        // заполняем поле для номера телефона заданным значением
        phoneInput.sendKeys(phone);
        return this;
    }

    @Step("Нажатие кнопки Получить код")
    // нажатие кнопки "Получить код"
    public LoginPageWB clickGetCodeButton() {
        getCodeButton.click();
        return this;
    }

    @Step("Проверка текста сообщения об ошибке")
    // проверка соответствия текста сообщения об ошибке заданному тексту
    public LoginPageWB checkLoginErrorMessage(String loginErrorMessageExpected) {
        // ожидание, пока появится сообщение об ошибке
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginErrorMessageXPathLocator)));
        // проверяем появившееся сообщение ошибке
        Assertions.assertEquals(loginErrorMessageExpected, loginErrorMessage.getText());
        return this;
    }

}
