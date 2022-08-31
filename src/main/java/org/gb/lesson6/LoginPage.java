package org.gb.lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
            super(driver);
    }

    private final String emailIdLocator = "email";

    @FindBy(id = emailIdLocator)
    public WebElement emailField;

    @FindBy(id = "passwd")
    public WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    public WebElement signInButton;

    public MainPage login(String login, String password) {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailField));
        // разница между visibilityOf и visibilityOfElementLocated: 1 - элемент есть, но скрыт, 2 - элемент подгружается динамически
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(emailIdLocator)));
        emailField.sendKeys(login);
        passwordField.sendKeys(password);
        signInButton.click();
        return new MainPage(driver);
    }


}
