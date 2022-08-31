package org.gb.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
/*
всё это вынесено в отдельный класс BasePage, чтобы не дублировать для каждой страницы

    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait =  new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);  // обеспечивает:
                        // при обращении к элементам страницы всегда будет использоваться актуальное состояние
    }
 */
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInButton;  // private - чтобы были видны только уникальные методы для действий с элементом
    // можно оставить публичным

    public MainMenuBlock mainMenuBlock; // на странице есть блок меню

    public LoginPage clickSingInButton() { // возвращаем ту страницу, которая в итоге открывается
        signInButton.click();
        return new LoginPage(driver);
    }



}