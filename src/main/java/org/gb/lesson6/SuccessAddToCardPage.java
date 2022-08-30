package org.gb.lesson6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SuccessAddToCardPage extends BasePage {
    public SuccessAddToCardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    private WebElement totalSumma;

    private final String iconOkXpathLocator = "//i[@class='icon-ok']";

    @FindBy(xpath = iconOkXpathLocator)
    private WebElement iconOk;

    public /*SuccessAddToCartPage*/ void checkTotalSumma(String expectedSumma) {
        // ожидаем подгрузки поля с суммой
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconOkXpathLocator)));
        // проверяем совпадение ожидаемой суммы с отображаемой
        Assertions.assertEquals(expectedSumma, totalSumma.getText());
        //return this; // не переходим никуда, остаемся на той же странице
    }
}
