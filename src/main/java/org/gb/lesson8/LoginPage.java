package org.gb.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    // @FindBy(id = "email")
    // private WebElement emailField;  // можно было и private
    private SelenideElement emailField = $(By.id("email"));

    // @FindBy(id = "passwd")
    // public WebElement passwordField;
    private SelenideElement passwordField = $(By.id("passwd"));

    // @FindBy(id = "SubmitLogin")
    // public WebElement signInButton;
    private SelenideElement signInButton = $(By.id("SubmitLogin"));

    @Step("Login")
    public MainPage login(String login, String password) {
        // пример: emailField.getWrappedElement().click();  // можно обратиться к WebElement как [SelenideElement].getWrappedElement()
        emailField.sendKeys(login);
        passwordField.sendKeys(password);
        signInButton.click();
        // return new MainPage(driver);
        return page(MainPage.class);  // указываем класс той страницы, на которую попадаем в итоге
    }


}
