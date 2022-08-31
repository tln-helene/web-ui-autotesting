package org.gb.lesson7;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

import static org.openqa.selenium.OutputType.BYTES;

public class AdditionalLogger implements WebDriverListener {
    private static Logger logger = LoggerFactory.getLogger(AdditionalLogger.class);

    public void beforeFindElement(WebDriver driver, By locator) {
        logger.info("Ищем элемент по локатору " + locator);  // вывод сообщения в лог
        Allure.step("Ищем элемент по локатору " + locator);   // добавление соответсвующего шага в отчет
    }

    public void beforeQuit(WebDriver driver) {
        // будем выводить в отчет скриншот в конце каждого теста
        // создаем скриншот из потока байтов
        Allure.addAttachment("Скриншот перед закрытием браузера",
                new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(BYTES)));
    }

}
