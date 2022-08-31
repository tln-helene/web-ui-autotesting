package org.gb.task6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPageWB extends BasePageWB {

    public MainMenuBlockWB mainMenuBlock; // в шапке страницы есть блок меню
    public SearchBlockWB searchBlock;   // в шапке страницы есть блок поиска

    public MainPageWB(WebDriver driver) {
        super(driver);
        this.mainMenuBlock = new MainMenuBlockWB(driver);
        this.searchBlock = new SearchBlockWB(driver);
    }

    private final String loginButtonXPathLocator = "//a[@data-wba-header-name='Login']";
    private final String hamburgerButtonXPathLocator = "//button[contains(@class,'j-menu-burger-btn')]";

    // кнопка логина
    @FindBy(xpath = loginButtonXPathLocator)
    private WebElement loginButton;

    // кнопка-"гамбургер" (меню)
    @FindBy(xpath = hamburgerButtonXPathLocator)
    private WebElement hamburgerButton;

    // нажатие кнопки логина
    public LoginPageWB clickLoginButton() { // возвращает страницу, которая открывается по клику на кнопку логина
        // ожидание, пока прогрузится кнопка входа
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginButtonXPathLocator)));
        // клик по кнопке входа
        loginButton.click();
        return new LoginPageWB(driver);
    }

    // нажатие кнопки-"гамбургера" (меню)
    public MainMenuBlockWB clickHamburgerButton() { // возвращает блок меню, который откроется по клику на кнопку-"гамбургер"
        // ожидание, пока прогрузится кнопка меню
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(hamburgerButtonXPathLocator)));
        // клик по кнопке "гамбургер"
        hamburgerButton.click();
        return this.mainMenuBlock;
    }

    // проверка соответствия текущей открытой ссылки заданному значению ссылки
    public MainPageWB checkOpenedLink(String sLinkExpected) {
        // сравнение тестового значения ссылки со ссылкой текущей страницы
        Assertions.assertEquals(sLinkExpected, driver.getCurrentUrl());
        return this;
    }

}
