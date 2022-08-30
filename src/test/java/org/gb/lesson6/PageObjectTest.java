package org.gb.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObjectTest {
    WebDriver driver;
/*
    // всё это будет создаваться c использованием переданного драйвера
    WebDriverWait webDriverWait;
    Actions actions;
*/

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
/*
        // всё это будет создаваться c использованием переданного драйвера
        webDriverWait =  new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
*/
    }


    @Test
    void putTShirtToCartTest() throws InterruptedException {
/*
        // если вызовем конструктор при исходном состоянии страницы и потом будем к нему обращаться,
        // поля инициализируются тем, что есть, и можем не найти элементы, которое появляется динамически (null)

        // создаем экземпляр MainPage и обращаемся к полю (кнопке), которая нас интересует
        MainPage mainPage = new MainPage(driver);
        // mainPage.signInButton.click();  // элемент сделаем приватным, теперь только вызов метода-действия
        mainPage.clickSingInButton();

        /// другая запись
        //new MainPage(driver).signInButton.click();
        new MainPage(driver).clickSingInButton(); // можно сразу вызвать метод из класса
        ///

        new LoginPage(driver).login("spartalex93@test.test", "123456");
        new WomenSuggestPage(driver).
*/

        new MainPage(driver).clickSingInButton()
                .login("spartalex93@test.test", "123456")
                .mainMenuBlock.hoverWomenButton()
                .clickTShirtsButton()
                .selectSize("S")
                .moveMouseToProductAndAddToCart()
                .checkTotalSumma("$18.51");

        Thread.sleep(5000);
    }


    @AfterEach
    void killBrowser() {
        driver.quit();
    }
}
