package org.gb.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AfishaTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.afisha.ru/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));  // если элемент пока не появился, это обеспечит ожидание до 3 секунд

        driver.findElement(By.xpath("//input[@placeholder='Событие, актер, место']")).sendKeys("Брат"); // ищем фильм "Брат"
        // надо подождать динамической подгрузки контента
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));  // MAX время ожидания 5с
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Брат']")));  // если элемента не было, но появился, нужно брать этот вариант
        // другая запись - похожий метод, но с другим параметром (локатор/элемент)
        // webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[.='Брат']"))));  // если элемент не найден - упадём

        // кликнем по найденному
        driver.findElement(By.xpath("//div[.='Брат']")).click();

        Thread.sleep(5000);
        driver.quit();
    }
}
