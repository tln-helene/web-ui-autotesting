package org.gb.lesson4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gb.lesson4.Functions.isPalindrome;

public class FunctionsTest {

    // уровни логирования TRACE, DEBUG, INFO, WARN, ERROR
    // уровень логирования задается в root level в файле logback.xml
    // TRACE - самый подробный, ERROR - только ошибки

    // создание логгера
    private static Logger logger = LoggerFactory.getLogger("FunctionsTest");  // имя логгера любое, но лучше со отсылкой к классу

    @BeforeAll
    static void beforeAll() {
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");  // подобные вещи можно выносить в общую часть
        System.out.println("Метод выполнится 1 раз перед всеми тестами класса");  // не нужно, если используем логгер

        logger.info("Метод выполнится 1 раз перед всеми тестами класса");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Метод выполняется перед каждым тестом класса");
        //WebDriver driver = new ChromeDriver();

        // логирование ошибки - здесь просто для примера описания
        logger.error("Метод выполняется перед каждым тестом класса - ТАК В ЛОГЕ ВЫГЛЯДИТ ОШИБКА");
    }

    @Test
//    @Disabled             // тест запускаться не будет (если нужно пропустить)
//    @Disabled("broken")   // тест запускаться не будет, и будет указана причина
//    @Tag("smoke")         // пометка, что тест относится к определенному набору, можно запустить именно этот набор
    @DisplayName("Метод проверки палиндрома c нечетным количеством символов")
    void givenPalindromeWhenCallIsPalindromeMethodThenTrue() {
        //boolean result = isPalindrome("1234321");
        //Assertions.assertTrue(result);
        // напишем иначе, используя библиотеку AssertJ
        assertThat(isPalindrome("1234321")).isTrue();
        ////Assertions.assertEquals(false, result);
    }

    @Test
    @DisplayName("Метод проверки палиндрома c четным количеством символов")
    void givenPalindromeWhenCallIsPalindromeMethodThenTrue1() {
        boolean result = isPalindrome("123321");
        Assertions.assertTrue(result);
    }

    // те же тесты в параметризованном виде, код написан всего 1 раз
    @ParameterizedTest
    @ValueSource(strings = {"1234321", "123321"})
    void isPalindromeTestWithDataProvider(String word) {
        boolean result = isPalindrome(word);
        Assertions.assertTrue(result);
    }

    // параметризованный тест с результатами
    @ParameterizedTest
    @CsvSource({"123, false", "123321, true"})
    void csvTest(String word, boolean expectedResult) {
        boolean actualResult = isPalindrome(word);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("catAndAgeDataProvider")
    void catEqualAgeTest(Cat cat, int age) {
        Assertions.assertEquals(cat.getAge(), age);
    }

    // метод для генерации тестовых данных
    private static Stream<Arguments> catAndAgeDataProvider() {
        return  Stream.of(
            Arguments.of(new Cat("Test1",10), 10),
            Arguments.of(new Cat("Test1",11), 11),
            Arguments.of(new Cat("Test1",12), 12)
        );
    }



    @AfterEach
    void afterEach() {
        // driver.quit();
        System.out.println("Метод выполняется после каждого теста класса");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Метод выполнится 1 раз после всех тестов");
    }

}

