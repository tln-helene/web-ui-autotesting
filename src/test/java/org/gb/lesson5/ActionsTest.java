package org.gb.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class ActionsTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15)); // обычно 5 секунд
        actions = new Actions(driver);
        // driver.get("    ");  // если начинаем каждый тест с одной и той же страницы
    }

    @Test
    void dragAndDropTest() {
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop.html");
        // проверим действие - перетаскивание drag-n-drop
        actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable")))
                .build()
                .perform();
        // проверяем соответствие тексту
        Assertions.assertEquals(driver.findElement(By.id("droppable")).getText(), "Dropped!");
    }

    @Test
    void tabsTest() throws InterruptedException {
        driver.get("https://google.com");

        // запустим JS код для создания алерта (чтобы поработать с ним)
        ((JavascriptExecutor)driver).executeScript("alert('text')");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();  // переключаемся в алерт и соглашаемся (это простой алерт с одной кнопкой)
        Thread.sleep(2000);


        driver.switchTo().newWindow(WindowType.TAB); // переключение в новую вкладку
        Thread.sleep(2000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());  // список табов
        driver.switchTo().window(tabs.get(1)); // переходим во вкладку с индексом 1

        driver.get("https://ya.ru");  // откроем ссылку в текущей вкладке
        Thread.sleep(2000);
        driver.close();  // закрываем текущую вкладку
        Thread.sleep(2000);

    }

    @AfterEach
    void quitBrowser() {
        driver.quit();
    }

}
