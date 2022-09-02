package org.gb.task8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
//import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.WebDriverConditions.*;

public class MainPageWB {

    public MainMenuBlockWB mainMenuBlock; // в шапке страницы есть блок меню
    public SearchBlockWB searchBlock;   // в шапке страницы есть блок поиска

//    public MainPageWB() {
//        this.mainMenuBlock = new MainMenuBlockWB(driver);
//        this.searchBlock = new SearchBlockWB(driver);
//    }


    // кнопка логина
    private SelenideElement loginButton = $(By.xpath("//a[@data-wba-header-name='Login']"));
    // кнопка-"гамбургер" (меню)
    private SelenideElement hamburgerButton = $(By.xpath("//button[contains(@class,'j-menu-burger-btn')]"));


    // @Step("Нажатие кнопки логина")
    // нажатие кнопки логина
    public LoginPageWB clickLoginButton() { // возвращает страницу, которая открывается по клику на кнопку логина
        // клик по кнопке входа
        loginButton.click();
        return page(LoginPageWB.class);
    }

    // @Step("Нажатие кнопки-гамбургера")
    // нажатие кнопки-"гамбургера" (меню)
    public MainMenuBlockWB clickHamburgerButton() { // возвращает блок меню, который откроется по клику на кнопку-"гамбургер"
        // клик по кнопке "гамбургер"
        hamburgerButton.click();
        return page(MainMenuBlockWB.class);
    }

    // @Step("Проверка соответствия открытой ссылки")
    // проверка соответствия текущей открытой ссылки заданному значению ссылки
    public MainPageWB checkOpenedLink(String sLinkExpected) {
        // сравнение тестового значения ссылки со ссылкой текущей страницы
        webdriver().shouldHave(url(sLinkExpected));
        return page(MainPageWB.class);
    }

}
