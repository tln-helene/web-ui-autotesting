package org.gb.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    // @FindBy(xpath = "//a[@class='login']")
    // private WebElement signInButton;
    private SelenideElement signInButton = $(By.xpath("//a[@class='login']"));

    public MainMenuBlock mainMenuBlock; // на странице есть блок меню

    @Step("Клик на кнопку логина")
    public LoginPage clickSingInButton() { // возвращаем ту страницу, которая в итоге открывается
        signInButton.click();
        // return new LoginPage(driver);
        return page(LoginPage.class);  // указываем класс той страницы, на которую попадаем в итоге
    }



}