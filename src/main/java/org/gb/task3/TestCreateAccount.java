package org.gb.task3;

/*
Сценарий для сайта https://irecommend.ru/
- регистрация нового пользователя

На странице есть капча, сценарий отрабатывает до самого конца только тогда, когда проверка принимает просто флажок без демонстрации картинок.
В случае, когда капча с картинками (не всегда), обойти ей автоматически нельзя, сценарий завершается с ошибкой после капчи.
*/


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class TestCreateAccount {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        // открыть страницу входа на сайт
        driver.get("https://irecommend.ru/user/login?destination=home");

        // обеспечиваем возможность ожидания загрузки (для выбранного сайта загрузка может быть очень долгой)
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));  // MAX время ожидания 30с
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='button' and .='Регистрация']"))));

        // нажать кнопку "Регистрация" в текущем окне
        driver.findElement(By.xpath("//a[@class='button' and .='Регистрация']")).click();

        // должна открыться ссылка  https://irecommend.ru/user/register?destination=home
        // обеспечиваем возможность ожидания загрузки формы
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-name"))));

        // ввести тестовое значение в поле "Имя пользователя"
        String testName = "test" + new Random().nextInt(500);   // сгенерировать случайное тестовое значение
        WebElement signInForm = driver.findElement(By.id("edit-name"));  // найти элемент по id
        signInForm.sendKeys(testName);                 // заполнить поле тестовым значением

        // ввести тестовое значение в поле "E-mail адрес"
        driver.findElement(By.id("edit-mail")).sendKeys(testName + "@mail.ru");

        // ввести тестовое значение в поле "Пароль"
        String testPass = "IvanTest123";  // пусть это значение будет неслучайным, на сайте нетривиальные требования для пароля
        driver.findElement(By.id("edit-pass-pass1")).sendKeys(testPass);

        // ввести тестовое значение в поле "Повторите пароль", равное значению для поля "Пароль"
        driver.findElement(By.id("edit-pass-pass2")).sendKeys(testPass);

        // поставить флажок “Я принимаю пользовательское соглашение”
        driver.findElement(By.id("edit-reg-policy")).click(); // checkbox

        // поставить флажок проверки капчи (во вложенном фрейме)
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        driver.switchTo().parentFrame();
        // если капча - просто флажок без картинок, сценарий продолжает выполняться без ошибок,
        // если капча с картинками - обойти её нельзя, и на следующем шаге будет ошибка

        // нажать кнопку "Регистрация" в текущем окне
        driver.findElement(By.xpath("//a[@class='button' and .='Регистрация']")).click();

        Thread.sleep(10000); // ожидание - результат смотрим на экране
        driver.close();
        driver.quit();
    }
}
