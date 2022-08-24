package org.gb.task5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VariousTests {


    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    private final static String BASE_URL = "https://wildberries.ru";  // основная ссылка тестируемого сайта

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15)); // таймаут ожидания загрузки - 15 секунд
        actions = new Actions(driver);
        driver.get(BASE_URL);  // начинаем каждый тест с одной и той же страницы (главной)
    }

    @Test
    void loginRequestCodeTest() {
    /*
    Тест: после нажатия на кнопку Войти на главной странице открывается форма входа,
    при попытке нажать кнопку Получить код с незаполненным номером телефона появляется сообщение "Введите номер телефона"
    */

        // ожидание, пока прогрузися кнопка входа
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@data-wba-header-name='Login']")));
        // клик по кнопке входа
        driver.findElement(By.xpath("//a[@data-wba-header-name='Login']")).click();

        // ожидание, пока на странице прогрузися форма входа
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@class='input-item']")));
        // поле для номера телефона оставляем пустым
        driver.findElement(By.xpath("//input[@class='input-item']")).sendKeys(""); // очищаем, чтобы наверняка
        driver.findElement(By.id("requestCode")).click();

        // ожидание, пока появится сообщение об ошибке
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class, 'j-error-full-phone')]")));
        // проверяем появившееся сообщение ошибке
        Assertions.assertEquals("Введите номер телефона!",
                driver.findElement(By.xpath("//span[contains(@class, 'j-error-full-phone')]")).getText());
    }


    @ParameterizedTest
    @CsvSource({  // тестовые данные - вехние разделы каталога и соответствующие им ссылки
            "Женщинам, https://www.wildberries.ru/catalog/zhenshchinam",
            "Мужчинам, https://www.wildberries.ru/catalog/muzhchinam",
            "Детям, https://www.wildberries.ru/catalog/detyam"
    })
    void openCatalogSectionFromMenuTest(String sCatalogSection, String sLink) {
    /*
    Тест: после нажания на кнопку "гамбургер" открывается список разделов каталога,
    в списке найти заданный раздел, после клика на который должна открыться соответствующая ссылка
    */

        // ожидание, пока прогрузися кнопка меню
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(@class,'j-menu-burger-btn')]")));
        // клик по кнопке "гамбургер"
        driver.findElement(By.xpath("//button[contains(@class,'j-menu-burger-btn')]")).click();


        // xPath для пункта меню верхнего раздела каталога   //a[contains(.,'Женщинам') and contains(@class, 'menu-burger__main-list-link')]
        String xpathCatalogItem = "//a[contains(.,'" + sCatalogSection + "') and contains(@class, 'menu-burger__main-list-link')]";
        // выбор раздела каталога во всплывающем меню
        // ожидание, пока прогрузися всплывающее меню
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCatalogItem)));
        // клик по выбранному разделу
        driver.findElement(By.xpath(xpathCatalogItem)).click();

        // сравнение тестового значения ссылки со ссылкой открывшейся страницы
        Assertions.assertEquals(sLink, driver.getCurrentUrl());
    }


    @ParameterizedTest
    @CsvSource({  // тестовые данные - артикулы, присутсвующие в каталоге
            "123456",
            "4567890",
            "111222333"
    })
    void searchGoodByArticleTest(String sArticle) {
    /*
    Тест: ввести в строку поиска артикул товара,
    после клика на кнопку поиска должна открыться карточка товара с указанным артикулом
    */

        // ожидание, пока прогрузися поле поиска
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='searchInput']")));
        // заполнение поля поиска тестовым значением (существующий артикул)
        driver.findElement(By.id("searchInput")).sendKeys(sArticle);
        driver.findElement(By.id("applySearchBtn")).click();

        // ожидание, пока прогрузися артикул на новой странице
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@id='productNmId']")));

        // сравнение тестового значения артикула с артикулом на открывшейся странице
        Assertions.assertEquals(sArticle, driver.findElement(By.xpath("//span[@id='productNmId']")).getText());
    }

    @AfterEach
    void quitBrowser() {
        driver.quit();
    }
}
