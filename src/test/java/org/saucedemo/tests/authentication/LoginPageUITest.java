package org.saucedemo.tests.authentication;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.saucedemo.base.BaseTest;
import org.saucedemo.pages.login.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.saucedemo.testdata.provider.LoginTestData.FAILED_LOGIN_ERROR_MESSAGE;
import static org.saucedemo.testdata.provider.LoginTestData.INVALID_USERNAME_USER;

@Epic("Authentication")
@Feature("Login Page UI Consistency")
@Severity(SeverityLevel.MINOR)
public class LoginPageUITest extends BaseTest {

    @Story("User tries to log in using incorrect password, and the login form retains information" +
            " entered by the user.")
    @Description("Validates that the user is unable to log in with incorrect password, they are " +
            "being shown an error with the expected description, and both the username and " +
            "password field retain the same data entered by user before they clicked login button.")
    @Test
    public void shouldKeepFieldsStateAfterUnsuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(INVALID_USERNAME_USER);
        assertEquals(FAILED_LOGIN_ERROR_MESSAGE, loginPage.getErrorMessage());
        assertEquals(INVALID_USERNAME_USER.username(), loginPage.getUsernameInputValue());
        assertEquals(INVALID_USERNAME_USER.password(), loginPage.getPasswordInputValue());
    }
}
