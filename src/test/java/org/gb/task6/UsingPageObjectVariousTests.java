package org.gb.task6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsingPageObjectVariousTests {

    WebDriver driver;

    private final static String BASE_WB_URL = "https://wildberries.ru";  // основная ссылка тестируемого сайта

    @BeforeAll
    static void registerDriver1() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get(BASE_WB_URL);
        //  объекты WebDriverWait, Actions, PageFactory создаются при создании элемпляра класса страницы
    }


    @Test
    void loginRequestCodePOTest() {
    /*
    Тест: после нажатия на кнопку Войти на главной странице открывается форма входа,
    при попытке нажать кнопку Получить код с незаполненным номером телефона появляется сообщение "Введите номер телефона"
    */
        new MainPageWB(driver).clickLoginButton()
                .enterPhone("")
                .clickGetCodeButton()
                .checkLoginErrorMessage("Введите номер телефона!");
    }


    @ParameterizedTest
    @CsvSource({  // тестовые данные - верхние разделы каталога и соответствующие им ссылки
            "Женщинам, https://www.wildberries.ru/catalog/zhenshchinam",
            "Мужчинам, https://www.wildberries.ru/catalog/muzhchinam",
            "Детям, https://www.wildberries.ru/catalog/detyam"
    })
    void openCatalogSectionFromMenuPOTest(String sCatalogSection, String sLink) {
    /*
    Тест: после нажания на кнопку "гамбургер" открывается список разделов каталога,
    в списке найти заданный раздел, после клика на который должна открыться соответствующая ссылка
    */

        new MainPageWB(driver).clickHamburgerButton()
                .clickCatalogItemLink(sCatalogSection)
                .checkOpenedLink(sLink);
    }


    @ParameterizedTest
    @CsvSource({  // тестовые данные - артикулы, присутствующие в каталоге
            "123456",
            "4567890",
            "111222333"
    })
    void searchGoodByArticleTestPO(String sArticle) {
    /*
    Тест: ввести в строку поиска артикул товара,
    после клика на кнопку поиска должна открыться карточка товара с указанным артикулом
    */

        new MainPageWB(driver).searchBlock.enterSearchString(sArticle)
                .clickSearch()
                .checkArticle(sArticle);
    }


    @AfterEach
    void quitBrowser() {
        driver.quit();
    }
}
