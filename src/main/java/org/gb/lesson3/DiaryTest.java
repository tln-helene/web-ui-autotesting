package org.gb.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class DiaryTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
/*
        driver.get("https://diary.ru/user/login");  // открыть страницу

        WebElement loginForm = driver.findElement(By.id("loginform-username"));
        loginForm.sendKeys("spartalex");
        driver.findElement(By.id("loginform-password")).sendKeys("123456");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));  // переключиться во фрейм iframe, найденный по XPath
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click(); // кликнуть на элемент (здесь - капча), найденный по XPath
        driver.switchTo().parentFrame();  // переключиться на родительский фрейм
        driver.findElement(By.id("login_btn")).click();  // кликнуть на элемент (здесь - кнопку), найденный по id
*/

        // залогиниться с помощью куки
        driver.get("https://diary.ru");
        Cookie cookie = new Cookie("_identity_", "83668234c30812b14c46bac1deda1a240770255504032650b424a75038c00b3aa%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3545507%2C%22E_QJqRaNdBrepyeVN7uNXi5Dz9tjGpfX%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);  // добавить созданную куку в браузер
        driver.navigate().refresh();   // обновить страницу
        //driver.manage().getCookieNamed("_identity_");  // удаление куки


        driver.findElement(By.id("writeThisDiary")).click(); // кликнуть на элемент, найденный по id
        String postTile = "test" + new Random().nextInt(1000);   // сгенерируем случайное тестовое значение
        driver.findElement(By.id("postTitle")).sendKeys(postTile);  // вставляем сгенерированный заголовок (поле, найденное по id)


        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));  // переключаемся во фрейм iframe для сообщения
        driver.findElement(By.id("tinymce")).sendKeys("test text for a message");  // вставляем какой-то текст сообщения
        driver.switchTo().parentFrame(); // выходим из фрейма
        driver.findElement(By.id("rewrite")).click();  // запостим сообщение

        // найдем опубликованное сообщение

        // нехороший способ
        // driver.findElement(By.xpath(String.format("//a[text()='%s']", postTile))).click();  // ищем элемент, у которого текст равен значению переменной postTile

        // хороший способ - получить список и отфильтровать по искомому
        // List<WebElement> postTitles = driver.findElements(By.xpath("//a[@class='title']"));  // получаем весь список заголовков
        // postTitles.stream().filter(p -> p.getText().contains(postTile)).findFirst().get().click();  // фильтруем с помощью стрима, выбираем первый их них, кликаем

        // другой способ - найти 1й эелемент (последний пост оботражается в начале списка постов)
        driver.findElement(By.xpath("//div[@class='item  first'][1]//a[@class='title']")).click();

        Thread.sleep(20000);
        driver.quit();

    }
}
