package org.gb.lesson8;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.PageLoadStrategy;

import static com.codeborne.selenide.Selenide.open;

public class MyStepdefs {
    @Given("Пользователь зашел на сайт")
    public void пользовательЗашелНаСайт() {
        Configuration.timeout = 20000;
        Configuration.pageLoadStrategy = "none";
        open("http://automationpractice.com/index.php");   // Selenide.open
    }

    @When("Наводим кнопку мыши на Women")
    public void наводимКнопкуМышиНаWomen() {
        new MainMenuBlock().hoverWomenButton();
    }

    @And("Кликаем на кнопку T-Shirts")
    public void кликаемНаКнопкуTShirts() {
        new WomenSuggestPage().clickTShirtsButton();
    }

    @And("Выбираем размер S")
    public void выбираемРазмерS() {
        new TShirtsPage().selectSize("S");
    }

    @And("Наводим курсор мыши на карточку товара и добавляем в корзину")
    public void наводимКурсорМышиНаКарточкуТовараИДобввляемВКорзину() {
        new TShirtsPage().moveMouseToProductAndAddToCart();
    }

    @Then("Проверяем итоговую сумму")
    public void проверяемИтоговуюСумму() {
        new SuccessAddToCardPage().checkTotalSumma("18.51");
    }

    @When("Пользователь логинится на сайте")
    public void пользовательЛогинитсяНаСайте() {
        new MainPage().clickSingInButton();
        new LoginPage().login("spartalex93@test.test", "123456");
    }

    @And("Выбираем размер {string}")
    public void выбираемРазмерSize(String size) {
        new TShirtsPage().selectSize(size);
    }

    @After(value = "@close")
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

}
