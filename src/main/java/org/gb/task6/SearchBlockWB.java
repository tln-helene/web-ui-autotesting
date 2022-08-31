package org.gb.task6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchBlockWB extends BasePageWB {
    public SearchBlockWB(WebDriver driver) {
        super(driver);
    }

    private final String searchInputXPathLocator = "//input[@id='searchInput']";
    private final String searchButtonIdLocator = "applySearchBtn";

    // поле поиска
    @FindBy(xpath = searchInputXPathLocator)
    private WebElement searchInput;

    // кнопка поиска
    @FindBy(id = searchButtonIdLocator)
    private WebElement searchButton;

    @Step("Ввод строки поиска")
    // ввод значения в поле ввода строки поиска
    public SearchBlockWB enterSearchString(String searchString) {
        // ожидание, пока прогрузится поле поиска
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath(searchInputXPathLocator)));
        // заполнение поля поиска тестовым значением
        searchInput.sendKeys(searchString);
        return this;
    }

    @Step("Нажатие кнопки поиска")
    // нажатие кнопки поиска
    public GoodCardPageWB clickSearch() {
        searchButton.click();
        return new GoodCardPageWB(driver);
    }

}
