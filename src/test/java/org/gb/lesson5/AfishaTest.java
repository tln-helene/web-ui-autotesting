package org.gb.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AfishaTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    private final static String AFISHA_BASE_URL = "https://afisha.ru/msk";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15)); // обычно 5 секунд
        actions = new Actions(driver);
        driver.get(AFISHA_BASE_URL);  // если начинаем каждый тест с одной и той же страницы (например, главной)
    }

    @Test
    void likeMovieTest() {
        // ждём, пока в списке элементов по XPath появится больше 0 элементов
        webDriverWait
                .until(d -> d.findElements(By.xpath("//a[@data-test='LINK ITEM-NAME ITEM-URL' and contains(@href, 'movie')]/h2")).size() > 0);

        // получаем список фильмов и ищем в нём фильм по названию
        List<WebElement> filmsList = driver.findElements(
                By.xpath("//a[@data-test='LINK ITEM-NAME ITEM-URL' and contains(@href, 'movie')]/h2"));
        //filmsList.stream().filter(f -> f.getText().contains("Жена Художника")).findFirst().get().click();
        // если всё равно, на какой элемент выборки кликать, можно использовать первый из них  get(0)
        filmsList.get(0).click();

        // ждём появление элемента на странице
        // не visibilityOf, а visibilityOfElementLocated, т.к. элемента изначально не было на странице
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")));
        driver.findElement(
                By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")).click();

        // переключение во фрейм
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'login')]")));

        // ждём появления кнопки логина
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        // Assertions.assertEquals(driver.findElement(By.id("login")).isDisplayed(), true);
        Assertions.assertTrue(driver.findElement(By.id("login")).isDisplayed());
    }

    @Test
    void hoverCinemaButtonAndClockOkkoLinkTest() throws InterruptedException {

        // строим последовательность действий
        actions.moveToElement(driver.findElement(By.xpath("//a[.='КИНО']")))
                .clickAndHold(driver.findElement(By.xpath("//a[.='КИНО']")))
                .build()
                .perform();
        // кликаем на заданный заголовок
        driver.findElement(By.xpath("//header//a[.='Скоро онлайн в Okko']")).click();
        // проверяем, что открылась нужная страница
        Assertions.assertEquals(driver.getCurrentUrl(), "https://www.afisha.ru/movie/okko-soon/");



        // помимо сути теста, посмотрим удаление элемента - рекламы, чтобы не мешал (предварительно тестируем в браузере в Console в DevTools)
/*
        let element = document.evaluate("//div[@data-test='HONEY-AD AD-ad_2']", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null)
        element.singleNodeValue.remove()
 */
        // приводим драйвер к типу JavascriptExecutor, который позволит выполнить JS код
        ((JavascriptExecutor) driver).executeScript("let element = document.evaluate(\"//div[@data-test='HONEY-AD AD-ad_2']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null)\n" +
                "element.singleNodeValue.remove()");
        Thread.sleep(5000);



        // помимо сути теста, посмотрим на скроллинг
        // приводим драйвер к типу JavascriptExecutor, который позволит выполнить JS код
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[.='Подписаться']")));
        Thread.sleep(5000);

    }


    @AfterEach
    void quitBrowser() {
        driver.quit();
    }

}
