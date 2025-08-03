package org.saucedemo.tests.authentication;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.saucedemo.base.BaseTest;
import org.saucedemo.pages.components.SidebarComponent;
import org.saucedemo.pages.login.LoginPage;
import org.saucedemo.pages.products.ProductsPage;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.saucedemo.testdata.provider.LoginTestData.*;
import static org.saucedemo.testdata.provider.URLs.*;

@Epic("Authentication")
@Feature("Session Security")
@Severity(CRITICAL)
public class SessionTest extends BaseTest {

    @Story("User that has not been successfully authenticated does not have the access to " +
            "protected resources.")
    @Description("Validates that unauthenticated user cannot access protected resources.")
    @Test
    public void shouldNotAllowAccessForUnauthenticatedUser() {
        driver.get(INVENTORY_URL);
        LoginPage loginPage = new LoginPage(driver);
        assertEquals(getNoUnauthenticatedAccessErrorMessage(INVENTORY_URL), loginPage.getErrorMessage());
    }

    @Story("User that logged in successfully is being logged out the moment their session expires.")
    @Description("Validates that logged in user is logged out when their session expires, and is " +
            "redirected to login page where they're being shown an expected error message.")
    @Test
    public void shouldRedirectToLoginPageWhenSessionExpires() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsValidUser(STANDARD_USER);
        driver.manage().deleteCookie(new Cookie(SESSION_COOKIE_NAME, SESSION_COOKIE_STANDARD_USER_VALUE));
        driver.get(CART_URL);
        assertEquals(getNoUnauthenticatedAccessErrorMessage(CART_URL), loginPage.getErrorMessage());
        assertEquals(BASE_URL, driver.getCurrentUrl());
    }

    @Story("User is logged out of the site after clicking log out button on the sidebar, and does" +
            " not have access to protected resources anymore.")
    @Description("Validates that the user is logged out after clicking on logout button, the user" +
            " is successfully logged out, and does not retain access to protected resources anymore.")
    @Test
    public void shouldLogoutSuccessfullyAndLoseAccess() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginAsValidUser(STANDARD_USER);
        SidebarComponent sidebarComponent = productsPage.openSidebar(driver);
        sidebarComponent.logout();
        assertEquals(BASE_URL, driver.getCurrentUrl());
        driver.get(CART_URL);
        assertEquals(getNoUnauthenticatedAccessErrorMessage(CART_URL), loginPage.getErrorMessage());
    }
}
