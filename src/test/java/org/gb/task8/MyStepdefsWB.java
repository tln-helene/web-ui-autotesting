package org.gb.task8;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.gb.lesson8.SuccessAddToCardPage;

import static com.codeborne.selenide.Selenide.open;

public class MyStepdefsWB {
    @Given("Пользователь зашел на сайт WB")
    public void пользовательЗашелНаСайтWB() {
        Configuration.timeout = 60000;             // в мс
        //Configuration.pageLoadStrategy = "none";   //  без этой опции и достаточно большого таймаута тесты не работали
        open("http://wildberries.ru");
    }

    @When("Нажать кнопку Войти")
    public void нажатьКнопкуВойти() {
        new MainPageWB().clickLoginButton();
    }

    @And("Ввести пустой номер телефона")
    public void ввестиПустойНомерТелефона() {
        new LoginPageWB().enterPhone("");
    }

    @And("Нажать кнопку Получить код")
    public void нажатьКнопкуПолучитьКод() {
        new LoginPageWB().clickGetCodeButton();
    }

    @Then("Проверить текст сообщения об ошибке")
    public void проверитьТекстСообщенияОбОшибке() {
        new LoginPageWB().checkLoginErrorMessage("Введите номер телефона!");
    }

    @When("Нажать кнопку гамбургер")
    public void нажатьКнопкуГамбургер() {
        new MainPageWB().clickHamburgerButton();
    }

    @And("Кликнуть заданный раздел {string} в каталоге")
    public void кликнутьЗаданныйРазделSectionВКаталоге(String section) {
        new MainMenuBlockWB().clickCatalogItemLink(section);
    }

    @Then("Проверить совпадение url открывшейся страницы с {string}")
    public void проверитьСовпадениеUrlОткрывшейсяСтраницыСLink(String link) {
        new MainPageWB().checkOpenedLink(link);
    }

    @When("Ввести в строку поиска артикул товара {string}")
    public void ввестиВСтрокуПоискаАртикулТовараArticle(String article) {
        new SearchBlockWB().enterSearchString(article);
    }

    @And("Кликнуть кнопку поиска")
    public void кликнутьКнопкуПоиска() {
        new SearchBlockWB().clickSearch();
    }

    @Then("Проверить артикул {string} в открывшейся карточке товара")
    public void проверитьАртикулArticleВОткрывшейсяКарточкеТовара(String article) {
        new GoodCardPageWB().checkArticle(article);
    }

    @After(value = "@close")
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

}
