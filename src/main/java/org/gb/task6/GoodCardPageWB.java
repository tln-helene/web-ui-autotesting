package org.gb.task6;

import org.gb.lesson6.BasePage;
import org.gb.lesson6.SuccessAddToCardPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

// класс страницы с карточкой товара
public class GoodCardPageWB extends BasePageWB {
    public GoodCardPageWB(WebDriver driver) {
        super(driver);
    }

    private final String articleFieldXPathLocator = "//span[@id='productNmId']";

    // поле Артикул
    @FindBy(xpath = articleFieldXPathLocator)
    private WebElement articleField;

    // проверка соответствия артикула на странице заданному значению
    public void /*GoodCardPageWB*/ checkArticle(String sArticle) {
        // ожидание, пока прогрузися артикул на новой странице
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(articleFieldXPathLocator)));
        // сравнение тестового значения артикула с артикулом на открывшейся странице
        Assertions.assertEquals(sArticle, articleField.getText());
        // return this;
    }

}
