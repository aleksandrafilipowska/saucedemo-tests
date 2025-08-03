package org.saucedemo.tests.authentication;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.saucedemo.base.BaseTest;
import org.saucedemo.pages.login.LoginPage;
import org.saucedemo.pages.products.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.saucedemo.testdata.provider.LoginTestData.*;
import static org.saucedemo.testdata.provider.TextConstants.PRODUCTS_PAGE_HEADER;

@Epic("Authentication")
@Feature("Login Functionality")
@Severity(SeverityLevel.CRITICAL)
public class AuthenticationTest extends BaseTest {

    @Story("User logs in using valid credentials.")
    @Description("Validates that the user is able to log in using valid credentials.")
    @Test
    public void shouldLoginSuccessfullyWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginAsValidUser(STANDARD_USER);
        assertEquals(PRODUCTS_PAGE_HEADER, productsPage.getSecondaryHeaderText());
    }

    @Story("User tries to log in using incorrect password.")
    @Description("Validates that the user is unable to log in with incorrect password, and is " +
            "being shown an error with the expected description.")
    @Test
    public void shouldShowErrorWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(INVALID_PASSWORD_USER);
        assertEquals(FAILED_LOGIN_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

    @Story("User tries to log in without entering any credentials.")
    @Description("Validates that the user is unable to log in without entering neither username " +
            "nor password, and is being shown an error with the expected description.")
    @Test
    public void shouldShowErrorWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        assertEquals(EMPTY_CREDENTIALS_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

    @Story("User tries to log in using an account that was locked out of access to the site.")
    @Description("Validates that the user is unable to log in with credentials assigned to a user" +
            " whose account has been locked out of access to the site, and is being shown an error" +
            " with the expected description.")
    @Test
    public void shouldShowErrorWhenUsingLockedAccount() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(LOCKED_OUT_USER);
        assertEquals(LOCKED_OUT_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

    @Story("User tries to log in using an account that doesn't exist in the database.")
    @Description("Validates that user is unable to log in using a username of an account that " +
            "doesn't exist in the application's database, and is being shown an error with the " +
            "expected description.")
    @Test
    public void shouldShowErrorWhenUsingNonexistentAccount() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(NONEXISTENT_USER);
        assertEquals(FAILED_LOGIN_ERROR_MESSAGE, loginPage.getErrorMessage());
    }

}
