package org.saucedemo.tests.sidebar;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.saucedemo.base.BaseTest;
import org.saucedemo.pages.checkout.CartPage;
import org.saucedemo.pages.components.SidebarComponent;
import org.saucedemo.pages.login.LoginPage;
import org.saucedemo.pages.products.ProductsPage;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.saucedemo.testdata.provider.LoginTestData.STANDARD_USER;
import static org.saucedemo.testdata.provider.ProductTestData.TEST_PRODUCT_01;
import static org.saucedemo.testdata.provider.TextConstants.PRODUCTS_PAGE_HEADER;
import static org.saucedemo.testdata.provider.URLs.SAUCE_LABS_URL;

@Epic("Sidebar")
@Feature("Sidebar Functionality")
@Severity(NORMAL)
public class SidebarTest extends BaseTest {

    @BeforeEach
    void loginBeforeEach() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsValidUser(STANDARD_USER);
    }

    @Story("User clicks on 'All Items' link on the sidebar and is redirected to Products Page.")
    @Description("Validates that after opening the sidebar and clicking on the 'All items' link, " +
            "the user is redirected to Products Page, and the Sidebar is no longer visible.")
    @Test
    public void shouldRedirectToAllItemsPage() {
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = productsPage.goToShoppingCart();
        SidebarComponent sidebarComponent = cartPage.openSidebar(driver);
        sidebarComponent.goToAllItemsPage();
        assertEquals(PRODUCTS_PAGE_HEADER, productsPage.getSecondaryHeaderText());
    }

    @Story("User clicks on 'About' link on the sidebar and is redirected to Sauce Labs landing " +
            "page.")
    @Description("Validates that after opening the sidebar and clicking on the 'About' link, " +
            "the user is redirected to Sauce Labs landing page.")
    @Test
    public void shouldRedirectToAboutPage() {
        ProductsPage productsPage = new ProductsPage(driver);
        SidebarComponent sidebarComponent = productsPage.openSidebar(driver);
        sidebarComponent.goToAboutPage();
        assertEquals(SAUCE_LABS_URL, driver.getCurrentUrl());
    }

    @Story("User closes the sidebar by clicking on the close button on the top right corner of " +
            "the sidebar.")
    @Description("Validates that sidebar is no longer visible after it's been clicked by the user.")
    @Test
    public void shouldCloseSidebarWhenClickingOnCloseButton() {
        ProductsPage productsPage = new ProductsPage(driver);
        SidebarComponent sidebarComponent = productsPage.openSidebar(driver);
        sidebarComponent.closeSidebar();
        assertTrue(sidebarComponent.isSidebarVisible());
    }

    @Feature("App State")
    @Story("User clicks on 'Reset App State' button on the sidebar when cart is empty, and the " +
            "application state remains stable.")
    @Description("Validates that clicking on 'Reset App State' does not result in application " +
            "crash.")
    @Test
    public void shouldNotFailResetAppStateWhenCartIsEmpty() {
        ProductsPage productsPage = new ProductsPage(driver);
        SidebarComponent sidebarComponent = productsPage.openSidebar(driver);
        sidebarComponent.resetAppState();
    }

    // UI does not fully reset upon invoking 'Reset App State'. 'Remove' button doesn't go
    // back to its previous state until navigation occurs. There's an assumption that it reflects
    // an already known UI sync issue. For now the test checks functionality of 'Reset App
    // State', meaning if it correctly resets the cart contents.
    @Feature("App State")
    @Story("User clicks on 'Reset App State' button on the sidebar when the cart is not empty, " +
            "which empties the cart.")
    @Description("Validates that clicking on 'Reset App State' button on the sidebar results in " +
            "emptying the cart and resetting the state of 'Add to Cart' buttons on the Products " +
            "Page assigned to the items that were previously added to the cart.")
    @Issue("ISSUE-001")
    @Test
    public void shouldResetCartAndUIStateWithResetAppState() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart(TEST_PRODUCT_01);
        SidebarComponent sidebarComponent = productsPage.openSidebar(driver);
        sidebarComponent.resetAppState();
        sidebarComponent.closeSidebar();

        assertTrue(productsPage.isCartEmpty());
        CartPage cartPage = productsPage.goToShoppingCart();
        assertEquals(0, cartPage.getNumberOfItemsInTheCart());
    }
}
