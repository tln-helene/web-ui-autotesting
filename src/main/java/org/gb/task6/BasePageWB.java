package org.gb.task6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageWB {  // базовый класс для всех страниц/блоков
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    public BasePageWB(WebDriver driver) {
        this.driver = driver;
        webDriverWait =  new WebDriverWait(driver, Duration.ofSeconds(30));
        // для ожидания выставлено большое значение, позволяющее успешно пройти тесты на медленном окружении
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

}
