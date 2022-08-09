package org.gb.task4;

import org.gb.lesson4.BoxIsEmptyException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.gb.task4.Triangle.sTriangle;

public class TriangleTest {

    // создание логгера для тестирования
    private static Logger logger = LoggerFactory.getLogger("TriangleTest");

    @BeforeAll
    static void beforeAll() {
        // логирование - информация о начале тестирования в логе
        logger.info("Testing started.");
    }


    // ТЕСТЫ ДЛЯ КОРРЕКТНО ЗАДАННЫХ НЕВЫРОЖДЕННЫХ ТРЕУГОЛЬНИКОВ
    @ParameterizedTest      // параметризованный тест с результатами
    @Tag("positive")
    @CsvSource({  // тестовые данные - три стороны и площадь
           "3, 4, 5, 6",        // прямоугольный треугольник;  a,b,c > 0    c = sqrt(a*a + b*b)
           "13, 15, 14, 84",    // остроугольный треугольник;  a,b,c > 0
           "5, 10, 14, 17.605041891458253"         // тупоугольный треугольник;   a,b,c > 0
    })
    void correctTriangleTest(int a, int b, int c, double expectedResult) throws TriangleSideIsNegativeException, TriangleSidesAreIncorrectException, TriangleImpossibleToCalcException {
        double actualResult = sTriangle(a, b, c);
        Assertions.assertEquals(expectedResult, actualResult);
        logger.trace("a = " + a +  ", b = " + b + ", c = " + c + ", ordinary triangle");
    }


    // ТЕСТЫ ДЛЯ КОРРЕКТНО ЗАДАННЫХ ВЫРОЖДЕННЫХ ТРЕУГОЛЬНИКОВ ("складывающихся" на одну линию, то есть с площадью 0)
    @ParameterizedTest      // параметризованный тест, результат 0
    @Tag("positive")
    @CsvSource({  // тестовые данные - три стороны
            "3, 2, 5",   // a + b = c;  a,b,c > 0
            "2, 5, 3",   // a + c = b;  a,b,c > 0
            "5, 3, 2",   // b + c = a;  a,b,c > 0
            "0, 4, 4",   // a + b = c;  a = 0,  b,c > 0
            "4, 0, 4",   // b + c = a;  b = 0,  a,c > 0
            "4, 4, 0"    // a + c = b;  c = 0,  a,b > 0
    })
    void correctDegenerateTriangleTest(int a, int b, int c) throws TriangleSideIsNegativeException, TriangleSidesAreIncorrectException, TriangleImpossibleToCalcException {
        double actualResult = sTriangle(a, b, c);
        Assertions.assertEquals(0.0, actualResult);
        logger.trace("a = " + a +  ", b = " + b + ", c = " + c + ", degenerate triangle");
    }


    // ТЕСТЫ ДЛЯ НЕКОРРЕКТНО ЗАДАННЫХ ТРЕУГОЛЬНИКОВ (ЕСТЬ ОТРИЦАТЕЛЬНАЯ ДЛИНА СТОРОНЫ)
    @ParameterizedTest      // параметризованный тест
    @Tag("negative")
    @CsvSource({  // тестовые данные - три стороны
            "-3, 3, 3",   // a < 0
            "2, -2, 2",   // b < 0
            "5, 5, -5"    // c < 0
    })
    void incorrectTriangleWithNegativeSideException(int a, int b, int c) {
        Assertions.assertThrows(TriangleSideIsNegativeException.class, () -> sTriangle(a,b,c));
        logger.trace("a = " + a +  ", b = " + b + ", c = " + c + " => Triangle has a negative side");
    }


    // ТЕСТЫ ДЛЯ НЕКОРРЕКТНО ЗАДАННЫХ ТРЕУГОЛЬНИКОВ
    @ParameterizedTest      // параметризованный тест
    @Tag("negative")
    @CsvSource({  // тестовые данные - три стороны
            "1, 2, 10",      // a + b < c;  a,b,c > 0   - сумма двух сторон меньше третьей
            "3, 8, 1",      // a + c < b;  a,b,c > 0
            "9, 3, 2",      // b + c < a;  a,b,c > 0
            "0, 0, 5",      // a,b = 0;  c > 0          - две стороны 0, третья > 0
            "0, 7, 0",      // a,c = 0;  b > 0
            "4, 0, 0",      // b,c = 0;  a > 0
            "0, 1, 5",      // a=0;  b <> c             - две строны разные, третья = 0
            "2, 0, 4",      // b=0;  a <> c
            "7, 3, 0"       // c=0;  a <> b
    })
    void incorrectTriangleException(int a, int b, int c) {
        Assertions.assertThrows(TriangleSidesAreIncorrectException.class, () -> sTriangle(a,b,c));
        logger.trace("a = " + a +  ", b = " + b + ", c = " + c + " => Incorrect triangle sides");
    }

    @AfterAll
    static void afterAll() {
        // логирование - информация об окончании тестирования в логе
        logger.info("Testing finished.");
    }

}
