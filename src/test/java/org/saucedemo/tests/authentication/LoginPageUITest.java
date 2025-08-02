package org.saucedemo.tests.authentication;

import org.junit.jupiter.api.Test;
import org.saucedemo.base.BaseTest;
import org.saucedemo.pages.login.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.saucedemo.testdata.provider.LoginTestData.FAILED_LOGIN_ERROR_MESSAGE;
import static org.saucedemo.testdata.provider.LoginTestData.INVALID_USERNAME_USER;

public class LoginPageUITest extends BaseTest {

    @Test
    public void shouldKeepFieldsStateAfterUnsuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsInvalidUser(INVALID_USERNAME_USER);
        assertEquals(FAILED_LOGIN_ERROR_MESSAGE, loginPage.getErrorMessage());
        assertEquals(INVALID_USERNAME_USER.username(), loginPage.getUsernameInputValue());
        assertEquals(INVALID_USERNAME_USER.password(), loginPage.getPasswordInputValue());
    }
}
