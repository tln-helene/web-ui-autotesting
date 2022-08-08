package org.gb.task3;

/*
Сценарий для сайта https://www.livemaster.ru/ - добавление товара определенного поставщика в корзину зарегистрированного пользователя:
- логин с помощью куки (3 шт.),
- поиск товаров,
- добавление одного товара з найденных в корзину,
- просмотр корзины.

Модальные поп-апы скрыты с помощью соответствующих куки (с информацией про использование куки)
или закрыты (уведомление о скидочном купоне, появляющееся после логина).
*/


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class TestAddToCart {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(chromeOptions);

        // открыть главную страницу
        driver.get("https://www.livemaster.ru/");

        // ----- ЛОГИН

        // залогиниться с помощью куки
        Cookie cookie1 = new Cookie("Code", "006954abcc41c27db48001dd1a2b2dbd");
        Cookie cookie2 = new Cookie("User", "10911396");
        Cookie cookie3 = new Cookie("_hwt_", "e5db00a0c21a59738b29134b95520baef5e1e916bdb14632302a96a754908b7bf8828e4c6f61b7c610ec14d75c1c9d3103d0d545b0bdf5d0175394742130e15e");
        driver.manage().addCookie(cookie1);  // добавить созданную куку в браузер
        driver.manage().addCookie(cookie2);  // добавить созданную куку в браузер
        driver.manage().addCookie(cookie3);  // добавить созданную куку в браузер
        // кука для неотображения поп-апа об использовании куки
        Cookie cookiePopupСookie = new Cookie("showCookieConsent", "false");
        driver.manage().addCookie(cookiePopupСookie);  // добавить созданную куку в браузер, чтобы НЕ показывать поп-ап

        driver.navigate().refresh();         // обновить страницу
        // должны залогиниться

        // закрыть модальный поп-ап про какой-то купон, если он есть
        WebElement buttonPopupCouponClose = driver.findElement(By.xpath("//button[contains(@class, 'modal__close')]"));
        if (buttonPopupCouponClose != null) {
            driver.findElement(By.xpath("//button[contains(@class, 'modal__close')]")).click();
        }

        // ----- ПОИСК

        // найти поиском товары конкретного продавца
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Diana Ulanova"); // ввести тестовую строку в поле поиска
        driver.findElement(By.xpath("//button[@class='ui-search-btn']")).click(); // нажать кнопку поиска

        // ожидание загрузки
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));  // MAX время ожидания 15с
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[contains(@class, 'js-add-to-cart')])[1]"))));

        // ----- ДОБАВЛЕНИЕ В КОРЗИНУ

        // добавить в корзину 1-й из показанных товаров, который можно положить в корзину
        // для этого найти первую кнопку соответствующего класса и c надписью "В корзину"
        // кнопка будет невидима, значит, не кликабельна, пока курсор не перейдет к тайлу с товаром с этой кнопкой

        String buttonAddToCartXPath = "(//button[contains(@class, 'js-add-to-cart') and contains(text(), 'В корзину')])[1]";
        // кнопка добавки в корзину
        WebElement buttonAddToCart1 = driver.findElement(By.xpath(buttonAddToCartXPath));
        // родительский блок, на который надо навести курсор, чтобы кнопка появилась
        WebElement cardAddToCart1 = driver.findElement(By.xpath("(//button[contains(@class, 'js-add-to-cart') and contains(text(), 'В корзину')])[1]/ancestor::div[contains(@class, 'item-preview__info-container')]"));

        Actions builder = new Actions(driver);
        builder.moveToElement(cardAddToCart1).build().perform();
        builder.moveToElement(buttonAddToCart1).build().perform();
        buttonAddToCart1.click();

        Thread.sleep(20000); // ожидание для пользователя - результат смотрим на экране (ожидается изменение количества товаров в корзине в правом верхнем углу)

        // ----- ПРОСМОТР КОРЗИНЫ

        // открыть корзину
        // для этого кликнуть на соответствующую ссылку на странице
        driver.findElement(By.xpath("//a[@href='cart']")).click(); // кликнуть на элемент, найденный по xpath

        Thread.sleep(20000); // ожидание для пользователя - результат смотрим на экране (ожидается отображение добавленного товара в корзине)
        driver.close();
        driver.quit();
    }
}
