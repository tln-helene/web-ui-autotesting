package org.gb.task8;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainMenuBlockWB {

    // @Step("Нажатие пункта меню каталога")
    // нажатие пункта меню каталога с заданным названием
    public MainPageWB clickCatalogItemLink(String sCatalogSection) {
        // собираем xPath для пункта меню верхнего раздела каталога
        String xpathCatalogItem = "//a[contains(.,'" + sCatalogSection + "') and contains(@class, 'menu-burger__main-list-link')]";
        // выбор раздела каталога во всплывающем меню: клик по выбранному разделу
        SelenideElement sCatalogItem = $(By.xpath(xpathCatalogItem));
        sCatalogItem.click();
        return page(MainPageWB.class);
    }

}
