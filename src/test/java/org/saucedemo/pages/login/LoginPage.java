package org.saucedemo.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.products.ProductsPage;
import org.saucedemo.testdata.model.UserCredentials;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LoginPage extends BasePage {
    private final By usernameInput = id("user-name");
    private final By passwordInput = id("password");
    private final By loginButton = id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(usernameInput));
    }

    public ProductsPage loginAsValidUser(UserCredentials userCredentials) {
        fillOutLoginForm(userCredentials);
        acts.click(loginButton);
        return new ProductsPage(driver);
    }

    public void loginAsInvalidUser(UserCredentials userCredentials) {
        fillOutLoginForm(userCredentials);
        acts.click(loginButton);
    }

    public void fillOutLoginForm(UserCredentials userCredentials) {
        acts.sendKeys(usernameInput, userCredentials.username());
        acts.sendKeys(passwordInput, userCredentials.password());
    }

    public void clickLoginButton() {
        acts.click(loginButton);
    }

    public String getUsernameInputValue() {
        return acts.getValue(usernameInput);
    }

    public String getPasswordInputValue() {
        return acts.getValue(passwordInput);
    }

}
