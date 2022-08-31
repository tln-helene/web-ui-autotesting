package org.gb.task6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainMenuBlockWB extends BasePageWB {
    public MainMenuBlockWB(WebDriver driver) {
        super(driver);
    }

//    @FindBy(xpath = //a[contains(.,'Женщинам') and contains(@class, 'menu-burger__main-list-link')
//    private WebElement catalogItem;
//    переменные использовать в аннтотации нельзя, можно так описать только конкретный элемент

    // нажатие пункта меню каталога с заданным названием
    public MainPageWB clickCatalogItemLink(String sCatalogSection) {
        // собираем xPath для пункта меню верхнего раздела каталога
        String xpathCatalogItem = "//a[contains(.,'" + sCatalogSection + "') and contains(@class, 'menu-burger__main-list-link')]";
        // выбор раздела каталога во всплывающем меню:
        // ожидание, пока прогрузится всплывающее меню
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCatalogItem)));
        // клик по выбранному разделу
        driver.findElement(By.xpath(xpathCatalogItem)).click();
        return new MainPageWB(driver);
    }

}
