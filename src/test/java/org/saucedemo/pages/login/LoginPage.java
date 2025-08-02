package org.saucedemo.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.products.ProductsPage;
import org.saucedemo.testdata.model.UserCredentials;

public class LoginPage extends BasePage {
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage loginAsValidUser(UserCredentials userCredentials) {
        fillOutLoginForm(userCredentials);
        click(loginButton);
        return new ProductsPage(driver);
    }

    public void loginAsInvalidUser(UserCredentials userCredentials) {
        fillOutLoginForm(userCredentials);
        click(loginButton);
    }

    public void fillOutLoginForm(UserCredentials userCredentials) {
        sendKeys(usernameInput, userCredentials.username());
        sendKeys(passwordInput, userCredentials.password());
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public String getUsernameInputValue() {
        return getValue(usernameInput);
    }

    public String getPasswordInputValue() {
        return getValue(passwordInput);
    }

}
