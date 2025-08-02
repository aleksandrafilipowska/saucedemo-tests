package org.saucedemo.tests.authentication;

import org.junit.jupiter.api.Test;
import org.saucedemo.base.BaseTest;
import org.saucedemo.pages.login.LoginPage;
import org.saucedemo.pages.products.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.saucedemo.testdata.provider.LoginTestData.*;
import static org.saucedemo.testdata.provider.TextConstants.PRODUCTS_PAGE_HEADER;

public class AuthenticationTest extends BaseTest {

    @Test
    public void shouldLoginSuccessfullyWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginAsValidUser(STANDARD_USER);
        assertEquals(PRODUCTS_PAGE_HEADER, productsPage.getSecondaryHeaderText());
    }

    @Test
    public void shouldShowErrorWithInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(INVALID_USERNAME_USER);
        assertEquals(FAILED_LOGIN_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

    @Test
    public void shouldShowErrorWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(INVALID_PASSWORD_USER);
        assertEquals(FAILED_LOGIN_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

    @Test
    public void shouldShowErrorWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        assertEquals(EMPTY_CREDENTIALS_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

    @Test
    public void shouldShowErrorWhenUsingLockedAccount() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(LOCKED_OUT_USER);
        assertEquals(LOCKED_OUT_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

    @Test
    public void shouldShowErrorWhenUsingNonexistentAccount() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(NONEXISTENT_USER);
        assertEquals(FAILED_LOGIN_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

}
