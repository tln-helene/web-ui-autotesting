package org.gb.task6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.gb.lesson7.AdditionalLogger;
import org.gb.lesson7.TestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

////////////
import java.io.ByteArrayInputStream;
////////////
import static org.openqa.selenium.OutputType.BYTES;

@Story("Открытая часть сайта WB")  // название просто для примера
public class UsingPageObjectVariousTests {

    WebDriver driver;

    @RegisterExtension
    TestExtension watcher = new TestExtension();

    private final static String BASE_WB_URL = "https://wildberries.ru";  // основная ссылка тестируемого сайта

    @BeforeAll
    static void registerDriver1() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        // driver = new ChromeDriver();
        driver = new EventFiringDecorator(new AdditionalLogger()).decorate(new ChromeDriver()); // добавляем логирование
        // драйвер будет работать с логированием, эти дополнительные действия описаны в классе Additional logger
        driver.get(BASE_WB_URL);
        //  объекты WebDriverWait, Actions, PageFactory создаются при создании элемпляра класса страницы
    }


    @Test
    @Feature("Логин")
    @TmsLink("test-111")    // пример для некой TMS, ссылка на которую указывается
    @DisplayName("Нажать кнопку [Получить код] при пустом номере телефона на форме входа")
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
    @Feature("Каталог")
    @DisplayName("Открыть секцию каталога")
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
    @Feature("Поиск")
    @DisplayName("Найти товар по артикулу")
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
        watcher.setScreenStream(new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(BYTES)));
        // таким образом скриншот сохраняется до того, как браузер будет закрыт, чтобы включить скриншот в отчет

        // сохраняем браузерные логи
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logEntries) {
            Allure.addAttachment("Элемент лога браузера", log.getMessage());
        }

        // без логирования и отчетов достаточно просто закрыть браузер
        driver.quit();
    }
}
